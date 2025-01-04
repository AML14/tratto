import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.EnclosedExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.LiteralExpr;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithArguments;
import com.github.javaparser.ast.nodeTypes.NodeWithOptionalScope;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.EmptyStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.resolution.MethodUsage;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedReferenceType;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserMethodDeclaration;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JarTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import data.*;
import org.javatuples.Pair;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class provides the functionality for inserting oracles into test
 * prefixes.
 */
public class OracleInserter {
    /** A unique id for placeholder variable names when inserting oracles. */
    private static int variableID = 0;
    /** The current TOG under analysis. Used to resolve formatting inconsistencies. */
    private static TogType togUnderAnalysis = TogType.BASELINE;

    /** Private constructor to avoid creating an instance of this class. */
    private OracleInserter() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    private static Expression getOracleExpression(Statement statement) {
        // A method call or constructor invocation must be an instance of ExpressionStmt
        if (!statement.isExpressionStmt()) {
            return null;
        }
        // Get the expression from the current statement
        return statement.asExpressionStmt().getExpression();
    }

    /**
     * Removes type arguments from a parameterized type name.
     *
     * @param parameterizedType a type name
     * @return the base type without type arguments
     */
    private static String removeTypeParameters(String parameterizedType) {
        String regex = "<[^<>]*>";
        // repeatedly remove all type arguments.
        String previous;
        String current = parameterizedType;
        do {
            previous = current;
            current = previous.replaceAll(regex, "");
        } while (!current.equals(previous));
        return current;
    }

    private static List<String> getParametersFromMethodSignature(String methodSignature) {
        String parameters = methodSignature.substring(methodSignature.indexOf('(') + 1, methodSignature.indexOf(')'));
        if (parameters.length() == 0) {
            return new ArrayList<>();
        }
        return Stream.of(parameters.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    /**
     * Gets the variable names of all parameters from the method signature.
     *
     * @param methodSignature a method signature
     * @return all variable parameter names in the method signature
     */
    private static List<String> getParameterNamesFromMethodSignature(String methodSignature) {
        List<String> parameters = getParametersFromMethodSignature(methodSignature);
        return parameters
                .stream()
                .map(p -> {
                    String[] splitParam = p.trim().split(" ");
                    return splitParam[splitParam.length - 1].trim();
                })
                .toList();
    }

    /**
     * Gets the name of a method from the method signature.
     *
     * @param methodSignature a method signature
     * @return the method name
     */
    private static String getMethodName(String methodSignature) {
        return methodSignature.substring(0, methodSignature.indexOf('('));
    }

    /**
     * Gets a statement that initializes the variable returned by a given
     * expression. There are three possible cases depending on the type of
     * expression:
     * <ul>
     *     <li>Variable Declaration: Instantiate the variable using the given
     *     name, with no initial assignment</li>
     *     <li>Method Call (ignored return type): Instantiate a dummy
     *     placeholder variable</li>
     *     <li>Other: Do nothing</li>
     * </ul>
     * If the expression is not a variable declaration or method call, then
     * the relevant variable must already be initialized, and does not require
     * an additional initialization statement (e.g. {@code x += getCount();}).
     *
     * @param testExpr a Java expression with a method call
     * @param returnType the return type of the method call in
     *                   {@code testExpr}
     * @return a Java statement that initializes the return type of the
     * original expression. Returns an empty statement if a variable does not
     * need to be initialized.
     */
    private static Statement getInitStmt(
            Expression testExpr,
            Type returnType
    ) {
        if (testExpr.isVariableDeclarationExpr()) {
            VariableDeclarator variableDeclarator = testExpr.asVariableDeclarationExpr().getVariable(0);
            VariableDeclarationExpr variableDeclarationExpr = new VariableDeclarationExpr(
                    variableDeclarator.getType(),
                    variableDeclarator.getNameAsString()
            );
            return new ExpressionStmt(variableDeclarationExpr);
        } else if (testExpr.isMethodCallExpr()) {
            String placeholderName = "default" + variableID;
            VariableDeclarationExpr variableDeclarationExpr = new VariableDeclarationExpr(
                    returnType,
                    placeholderName
            );
            variableID++;
            return new ExpressionStmt(variableDeclarationExpr);
        } else {
            return new EmptyStmt();
        }
    }

    /**
     * Gets a statement that initializes the variable returned by the method
     * call of a given statement. If the method call has a void return type or
     * the variable affected by the statement is already initialized, then
     * this method returns an empty statement. If the return type of the
     * expression is ignored in the original statement, then a dummy variable
     * is initialized for any possible post-conditions.
     *
     * @param testStmt a Java statement with a method call
     * @return a Java statement that initializes a variable for the return
     * type of the original statement. Returns an empty statement if a
     * variable does not need to be initialized.
     */
    private static Statement getInitStmt(
            Expression oracleExpr,
            Statement testStmt
    ) {
        Type returnType = StaticJavaParser.parseType(oracleExpr.calculateResolvedType().describe());
        if (returnType.isVoidType() || !testStmt.isExpressionStmt()) {
            return new EmptyStmt();
        }
        Expression testExpr = testStmt.asExpressionStmt().getExpression();
        return getInitStmt(testExpr, returnType);
    }

    /**
     * Gets a statement that assigns the value returned by the method call
     * in a given statement under test, to the variable initialized in a given
     * Java initialization statement. The return type of the method call in
     * the test statement is equal to the type of the variable in the
     * initialization statement.
     *
     * @param initStmt a Java statement that initializes a variable for the
     *                 return type of the method call in {@code testStmt}
     * @param testStmt a Java statement with a method call
     * @return a Java statement that assigns the return type of the method
     * call in {@code testStmt} to the variable initialized by
     * {@code initStmt}. Returns {@code testStmt} if {@code initStmt} is
     * empty (e.g. void method).
     */
    private static Statement getPostStmt(
            Expression oracleExpr,
            Statement initStmt,
            Statement testStmt
    ) {
        if (initStmt.isEmptyStmt()) {
            // if no variable is initialized, then use original statement.
            return testStmt;
        }
        // get name  of test statement variable
        String name = initStmt.asExpressionStmt().getExpression()
                .asVariableDeclarationExpr()
                .getVariable(0)
                .getNameAsString();
        // get value of test statement variable
        Expression testExpr = testStmt.asExpressionStmt().getExpression();
        Expression initializer;
        if (testExpr.isVariableDeclarationExpr()) {
            initializer = testExpr.asVariableDeclarationExpr().getVariable(0)
                    .getInitializer().orElseThrow();
        } else if (testExpr.isAssignExpr()) {
            initializer = testExpr.asAssignExpr().getValue();
        } else {
            initializer = oracleExpr.clone();
        }
        AssignExpr assignExpr = new AssignExpr(
                new NameExpr(name),
                initializer,
                AssignExpr.Operator.ASSIGN
        );
        return new ExpressionStmt(assignExpr);
    }

    /**
     * Replaces all instances of original names in a given Java expression
     * with corresponding new names. Each entry in {@code originalNames} has a
     * corresponding entry in {@code newNames} at the same index. If a
     * variable name in {@code stringExpression} does not appear in
     * {@code originalNames}, then it is not modified.
     *
     * @param originalNames the original names
     * @param newNames the new names. May contain literal values.
     * @param stringExpression a Java expression
     * @return an equivalent Java expression String with new names
     * @throws IllegalArgumentException if {@code originalNames} and
     * {@code newNames} do not have equal sizes
     */
    private static String replaceNames(
            List<String> originalNames,
            List<String> newNames,
            String stringExpression
    ) {
        if (originalNames.size() != newNames.size()) {
            throw new IllegalArgumentException(originalNames + " and " + newNames + " are not equal sizes");
        }
        Expression jpExpression = StaticJavaParser.parseExpression(stringExpression);
        jpExpression.walk(NameExpr.class, name -> {
            int idx = originalNames.indexOf(name.getNameAsString());
            if (idx != -1) {
                name.replace(new NameExpr(newNames.get(idx)));
            }
        });
        // search for "this" keyword in Tratto output
        jpExpression.walk(ThisExpr.class, name -> {
            int idx = originalNames.indexOf("this");
            if (idx != -1) {
                name.replace(new NameExpr(newNames.get(idx)));
            }
        });
        return jpExpression.toString();
    }

    private static String getBoxingTypeName(LiteralExpr literalExpr) {
        if (literalExpr.isBooleanLiteralExpr()) {
            return "Boolean";
        } else if (literalExpr.isCharLiteralExpr()) {
            return "Character";
        } else if (literalExpr.isDoubleLiteralExpr()) {
            return "Double";
        } else if (literalExpr.isIntegerLiteralExpr()) {
            return "Integer";
        } else if (literalExpr.isLongLiteralExpr()) {
            return "Long";
        } else {
            return "Unknown";
        }
    }

    private static String getBoxingEnclosedTypeName(EnclosedExpr enclosedExpr) {
        Expression innerExpr = enclosedExpr.getInner();
        if (innerExpr.isLiteralExpr()) {
            String boxingTypeName = getBoxingTypeName(enclosedExpr.asLiteralExpr());
            if (boxingTypeName.equals("Unknown")) {
                return enclosedExpr.toString();
            }
            return boxingTypeName + ".valueOf" + enclosedExpr;
        } else if (innerExpr.isUnaryExpr()) {
            Expression unaryExpr = innerExpr.asUnaryExpr().getExpression();
            if (unaryExpr.isLiteralExpr()) {
                String boxingTypeName = getBoxingTypeName(unaryExpr.asLiteralExpr());
                if (boxingTypeName.equals("Unknown")) {
                    return enclosedExpr.toString();
                }
                return boxingTypeName + ".valueOf" + enclosedExpr;
            } else {
                return enclosedExpr.toString();
            }
        } else {
            return enclosedExpr.toString();
        }
    }

    private static String getParameterContextName(Expression expr) {
        if (expr.isCastExpr()) {
            return "(" + expr + ")";
        } else if (expr.isLiteralExpr()) {
            String boxingTypeName = getBoxingTypeName(expr.asLiteralExpr());
            if (boxingTypeName.equals("Unknown")) {
                return "(" + expr + ")";
            }
            return boxingTypeName + ".valueOf(" + expr + ")";
        } else if (expr.isEnclosedExpr()) {
            return getBoxingEnclosedTypeName(expr.asEnclosedExpr());
        } else {
            return expr.toString();
        }
    }

    /**
     * Replaces all parameter variable names in an oracle with names or
     * literal expressions from a method call of the method under test.
     *
     * @param oracleExpr a Java test statement
     * @param oracleOutput an oracle record
     * @return an equivalent oracle record with relevant parameter names or
     * literal values
     */
    private static OracleOutput getParameterID(
            Expression oracleExpr,
            OracleOutput oracleOutput
    ) {
        List<String> originalNames = getParameterNamesFromMethodSignature(oracleOutput.methodSignature());
        List<String> contextNames = ((NodeWithArguments<?>) oracleExpr).getArguments()
                .stream()
                .map(OracleInserter::getParameterContextName)
                .toList();
        String contextOracle = replaceNames(originalNames, contextNames, oracleOutput.oracle());
        return new OracleOutput(
                oracleOutput.className(),
                oracleOutput.methodSignature(),
                oracleOutput.oracleType(),
                contextOracle,
                oracleOutput.exception(),
                oracleOutput.testName(),
                oracleOutput.statementIndex()
        );
    }

    /**
     * Replaces all instances of "receiverObjectID" in an oracle with the
     * corresponding object name in source code. The "receiverObjectID"
     * corresponds to the name of the instance of the declaring class for the
     * method under test. If the method under test is a static method, then
     * the "receiverObjectID" does not exist, and the given oracle is not
     * modified.
     *
     * @param callableDeclaration the callable declaration associated to the oracle expression
     * @param oracleExpr the expression containing the method under test to which the oracles refer
     * @param initStmt a Java test statement
     * @param oracleOutput an oracle record
     * @return an equivalent oracle record with the relevant object name
     */
    private static OracleOutput getReceiverObjectID(
            CallableDeclaration<?> callableDeclaration,
            Expression oracleExpr,
            Statement initStmt,
            OracleOutput oracleOutput
    ) {
        if (callableDeclaration.isMethodDeclaration()) {
            if (callableDeclaration.asMethodDeclaration().isStatic()) {
                return oracleOutput;
            }
        }
        String originalName = togUnderAnalysis.equals(TogType.TRATTO) ? "this" : "receiverObjectID";
        String contextName;
        if (oracleExpr.isObjectCreationExpr()) {
            if (initStmt.isEmptyStmt()) {
                contextName = "";
            } else {
                Expression initExpr = initStmt.asExpressionStmt().getExpression();
                contextName = initExpr.asVariableDeclarationExpr().getVariable(0).getNameAsString();
            }
        } else {
            contextName = ((NodeWithOptionalScope<?>) oracleExpr).getScope().orElseThrow().toString();
        }
        String contextOracle = replaceNames(List.of(originalName), List.of(contextName), oracleOutput.oracle());
        return new OracleOutput(
                oracleOutput.className(),
                oracleOutput.methodSignature(),
                oracleOutput.oracleType(),
                contextOracle,
                oracleOutput.exception(),
                oracleOutput.testName(),
                oracleOutput.statementIndex()
        );
    }

    /**
     * Replaces all instances of "methodResultID" in an oracle with the
     * corresponding object name in source code. The "methodResultID"
     * corresponds to the name of the variable returned by the method under
     * test. If the method under test has a void return type (no corresponding
     * variable assignment), then the "methodResultID" does not exist, and the
     * given oracle is not modified.
     *
     * @param postStmt a Java statement that assigns the return value of the
     *                 method under test to a variable
     * @param oracleOutput the original oracle
     * @return an equivalent oracle record with the relevant object name
     */
    private static OracleOutput getMethodResultID(
            Statement postStmt,
            OracleOutput oracleOutput
    ) {
        if (!postStmt.isExpressionStmt()) {
            return oracleOutput;
        }
        Expression postExpr = postStmt.asExpressionStmt().getExpression();
        if (!postExpr.isAssignExpr()) {
            return oracleOutput;
        }
        String originalName = "methodResultID";
        String contextName = postExpr
                .asAssignExpr()
                .getTarget()
                .asNameExpr()
                .getNameAsString();
        String contextOracle = replaceNames(List.of(originalName), List.of(contextName), oracleOutput.oracle());
        return new OracleOutput(
                oracleOutput.className(),
                oracleOutput.methodSignature(),
                oracleOutput.oracleType(),
                contextOracle,
                oracleOutput.exception(),
                oracleOutput.testName(),
                oracleOutput.statementIndex()
        );
    }

    /**
     * Replaces all instances of original names in an oracle with their
     * corresponding instance names in a test statement. The original oracle
     * uses the parameter names from the method signature, "receiverObjectID"
     * for the instance of the declaring class, and "methodResultID" for the
     * instance of the method return type.
     *
     * @param callableDeclaration the callable declaration associated to the oracle expression
     * @param oracleExpr the expression containing the method under test to which the oracles refer
     * @param postStmt a Java statement that assigns the return value of the
     *                 method under test to a variable
     * @param initStmt a Java test statement
     * @param oracleOutput an original oracle record
     * @return an equivalent oracle record with contextual names from a test
     * statement
     */
    private static OracleOutput contextualizeOracle(
            CallableDeclaration<?> callableDeclaration,
            Expression oracleExpr,
            Statement initStmt,
            Statement postStmt,
            OracleOutput oracleOutput
    )  {
        oracleOutput = getParameterID(oracleExpr, oracleOutput);
        oracleOutput = getReceiverObjectID(callableDeclaration, oracleExpr, initStmt, oracleOutput);
        oracleOutput = getMethodResultID(postStmt, oracleOutput);
        return oracleOutput;
    }

    /** The statement used to indicate that a pre-condition has been failed. */
    private static final BlockStmt preConditionFailStmt = new BlockStmt(new NodeList<>(new ThrowStmt(
            new ObjectCreationExpr()
                    .setType(StaticJavaParser.parseClassOrInterfaceType("Error"))
                    .addArgument(new StringLiteralExpr("TrattoError: Precondition failed, invalid test."))
    )));

    /**
     * Gets all assertions corresponding to preconditions by wrapping oracles
     * as the condition of an {@code assertTrue} method call.
     *
     * @param oracles all relevant precondition oracles
     * @return all Java statements of JUnit assertions for the given oracles
     */
    private static Statement getPreConditions(
            List<OracleOutput> oracles
    ) {
        if (oracles.size() == 0) {
            return new EmptyStmt();
        }
        List<Expression> conditions = oracles
                .stream()
                .map(o -> (Expression) StaticJavaParser.parseExpression("!(" + o.oracle() + ")"))
                .toList();
        IfStmt ifStmt = new IfStmt(conditions.get(0), preConditionFailStmt, new BlockStmt());
        IfStmt currentIfStmt = ifStmt;
        for (int i = 1; i < conditions.size(); i++) {
            IfStmt nextIfStmt = new IfStmt(conditions.get(i), preConditionFailStmt, new BlockStmt());
            currentIfStmt.setElseStmt(nextIfStmt);
            currentIfStmt = nextIfStmt;
        }
        // wrap preconditions in a try/catch block
        Type exceptionType = StaticJavaParser.parseType("java.lang.Exception");
        Parameter exceptionParameter = new Parameter(exceptionType, "e");
        CatchClause catchClause = new CatchClause(exceptionParameter, preConditionFailStmt);
        return new TryStmt()
                .setTryBlock(new BlockStmt(new NodeList<>(ifStmt)))
                .setCatchClauses(new NodeList<>(catchClause));
    }

    /**
     * Gets a try/catch block corresponding to the body of an exceptional
     * axiomatic oracle. The try/catch block calls {@code postStmt} and
     * catches the expected exception. If the expected exception is not
     * thrown, then the test fails.
     *
     * @param postStmt a Java statement that calls the method under test and
     *                 assigns its output to a variable
     * @param oracleOutput an exceptional oracle
     * @return a try/catch block corresponding to the exceptional oracle
     */
    private static TryStmt getTryCatchBlock(
            Statement postStmt,
            OracleOutput oracleOutput
    ) {
        Type exceptionType = StaticJavaParser.parseType(oracleOutput.exception());
        Parameter exceptionParameter = new Parameter(exceptionType, "e");
        Comment comment = new LineComment(" Successfully thrown exception");
        BlockStmt catchBody = new BlockStmt();
        catchBody.addOrphanComment(comment);
        //catchBody.addStatement(new ReturnStmt());
        CatchClause catchClause = new CatchClause(exceptionParameter, catchBody);
        NodeList<Statement> tryBody = new NodeList<>(
                postStmt,
                StaticJavaParser.parseStatement("fail();")
        );
        return new TryStmt()
                .setTryBlock(new BlockStmt(tryBody))
                .setCatchClauses(new NodeList<>(catchClause));
    }

    /**
     * Gets an if-block representing all exceptional axiomatic oracles. Each
     * condition in an if (or else if) statement corresponds to a program
     * state in which an exception should be thrown. The body of each if
     * statement has a try/catch block corresponding to the expected exception
     * after the post statement is executed.
     *
     * @param postStmt a Java statement that calls the method under test and
     *                 assigns its output to a variable
     * @param oracles all relevant exceptional oracles
     * @return an if-block representing all exceptional oracles. Returns null
     * if there are no exceptional post conditions.
     */
    private static IfStmt getThrowsConditions(
            Statement postStmt,
            List<OracleOutput> oracles
    ) {
        if (oracles.size() == 0) {
            return null;
        }
        List<Expression> conditions = oracles
                .stream()
                .map(o -> (Expression) StaticJavaParser.parseExpression(o.oracle()))
                .toList();
        List<BlockStmt> tryStmts = oracles
                .stream()
                .map(o -> new BlockStmt().addStatement(getTryCatchBlock(postStmt, o)))
                .toList();
        assert conditions.size() == tryStmts.size();
        IfStmt ifStmt = new IfStmt(conditions.get(0), tryStmts.get(0), new BlockStmt());
        IfStmt currentIfStmt = ifStmt;
        for (int i = 1; i < conditions.size(); i++) {
            IfStmt nextIfStmt = new IfStmt(conditions.get(i), tryStmts.get(i), new BlockStmt());
            currentIfStmt.setElseStmt(nextIfStmt);
            currentIfStmt = nextIfStmt;
        }
        return ifStmt;
    }

    /**
     * Sets the body of the last "else" statement in an if-block.
     *
     * @param ifStmt an if-block
     * @param postConditions a list of Java statements
     */
    private static void setLastElseBranch(IfStmt ifStmt, NodeList<Statement> postConditions) {
        IfStmt currentStatement = ifStmt;
        while (currentStatement.getElseStmt().orElseThrow() instanceof IfStmt) {
            currentStatement = (IfStmt) currentStatement.getElseStmt().orElseThrow();
        }
        currentStatement.setElseStmt(new BlockStmt(postConditions));
    }

    /**
     * Gets a list of statements corresponding to all axiomatic post
     * conditions of a method under test. If the given base is null (e.g. no
     * exceptional post conditions), then this method returns all normal post
     * conditions as a list of assertions (similar to the pre-conditions).
     * Otherwise, this method appends the normal post conditions as the body
     * of the "else" branch in the base if-block.
     *
     * @param base an if-block representing all exceptional oracles
     * @param postStmt a Java statement that calls the method under test and
     *                 assigns its output to a variable
     * @param oracles all relevant normal post-condition oracles
     * @return an if-block representing all post-conditions (normal and
     * exceptional) or a list of normal post-conditions
     */
    private static NodeList<Statement> getPostConditions(
            IfStmt base,
            Statement postStmt,
            List<OracleOutput> oracles
    ) {
        NodeList<Statement> postConditions = new NodeList<>();
        postConditions.add(postStmt);
        postConditions.addAll(oracles
                .stream()
                .map(o -> StaticJavaParser.parseStatement("assertTrue(" + o.oracle() + ");"))
                .toList());
        if (base == null) {
            return postConditions;
        }
        setLastElseBranch(base, postConditions);
        return new NodeList<>(base);
    }

    /**
     * Gets all assertions from the relevant oracles of a given test
     * statement. The approach for inserting oracles varies based on the
     * oracle type:
     * <ul>
     *     <li>Pre-condition: added as assertions before the method under test
     *     is called</li>
     *     <li>Throws condition: added as if-statements to check the program
     *     state before the method under test is called</li>
     *     <li>Post-condition: added to the "else" block of the aforementioned
     *     if-statements, or added as assertions after the method under test
     *     is called (if no exceptional conditions are present)</li>
     * </ul>
     *
     * @param callableDeclaration the callable declaration associated to the oracle expression
     * @param oracleExpr the expression containing the method under test to which the oracles refer
     * @param testStmt a Java statement with a method call for the method
     *                 under test
     * @param oracles all oracles related to the method under test
     * @return a list of Java statements representing the axiomatic test
     * assertions
     */
    private static NodeList<Statement> getOracleStatements(
            CallableDeclaration<?> callableDeclaration,
            Expression oracleExpr,
            Statement testStmt,
            List<OracleOutput> oracles
    ) {
        NodeList<Statement> oracleStatements = new NodeList<>();
        Statement initStmt = getInitStmt(oracleExpr, testStmt);
        Statement postStmt = getPostStmt(oracleExpr, initStmt, testStmt);
        oracles = oracles
                .stream()
                .map(o -> contextualizeOracle(callableDeclaration, oracleExpr, initStmt, postStmt, o))
                .toList();
        List<OracleOutput> preConditions = oracles
                .stream()
                .filter(o -> o.oracleType().equals(OracleType.PRE))
                .toList();
        List<OracleOutput> throwsConditions = oracles
                .stream()
                .filter(o -> o.oracleType().equals(OracleType.EXCEPT_POST))
                .toList();
        List<OracleOutput> postConditions = oracles
                .stream()
                .filter(o -> o.oracleType().equals(OracleType.NORMAL_POST))
                .toList();
        Statement preBlock = getPreConditions(preConditions);
        IfStmt throwsBlock = getThrowsConditions(postStmt, throwsConditions);
        NodeList<Statement> postBlock = getPostConditions(throwsBlock, postStmt, postConditions);
        oracleStatements.add(preBlock);
        oracleStatements.add(initStmt);
        oracleStatements.addAll(postBlock);
        return new NodeList<>(oracleStatements
                .stream()
                .filter(stmt -> !stmt.isEmptyStmt())
                .toList());
    }

    private static String fqnToSimpleName(String fqn) {
        // remove type parameters
        String simpleName = removeTypeParameters(fqn);
        boolean isVarArgs = simpleName.endsWith("...");
        if (isVarArgs) {
            simpleName = simpleName.substring(0, simpleName.length() - 3);
        }
        // remove package
        int nameIdx = simpleName.lastIndexOf('.');
        simpleName = simpleName.substring(nameIdx + 1);
        if (isVarArgs) {
            simpleName += "...";
        }
        return simpleName;
    }

    private static String getTypeAsString(Parameter param) {
        Type paramType = param.getType();
        String paramTypeAsString = "";
        int arrayLevel = 0;
        if (param.isVarArgs()) {
            if (paramType.isTypeParameter() || removeTypeParameters(paramType.asString()).length() == 1) {
                return "java.lang.Object...";
            }
            return removeTypeParameters(param.getTypeAsString() + "...");
        }
        if (paramType.isArrayType()) {
            arrayLevel = paramType.getArrayLevel();
            while (paramType.isArrayType()) {
                paramType = paramType.asArrayType().getComponentType();
            }
        }
        if (param.getType().isTypeParameter() || removeTypeParameters(paramType.asString()).length() == 1) {
            return "java.lang.Object" + "[]".repeat(arrayLevel);
        }
        return removeTypeParameters(paramType.asString()) + "[]".repeat(arrayLevel);
    }

    private static List<OracleOutput> getRelatedOracles(List<OracleOutput> oracles, CallableDeclaration<?> targetMethod) {
        String targetMethodName = targetMethod.getNameAsString();
        List<String> targetMethodParameters = targetMethod
                .getParameters()
                .stream()
                .map(OracleInserter::getTypeAsString)
                .map(OracleInserter::fqnToSimpleName)
                .toList();
        return oracles
                .stream()
                .filter(o -> {
                    String oracleMethodName = getMethodName(o.methodSignature());
                    // if constructor, convert fully qualified name to simple name
                    if (oracleMethodName.contains(".")) {
                        oracleMethodName = fqnToSimpleName(oracleMethodName);
                    }
                    List<String> oracleMethodParameters = getParametersFromMethodSignature(o.methodSignature())
                            .stream()
                            .map(p -> getTypeAsString(StaticJavaParser.parseParameter(p)))
                            .map(OracleInserter::fqnToSimpleName)
                            .toList();
                    return oracleMethodName.equals(targetMethodName) && oracleMethodParameters.equals(targetMethodParameters);
                })
                .toList();
    }

    private static Expression boxExpression(Expression expression, String fieldDescriptor) {
        String boxingObject;
        switch (fieldDescriptor) {
            case "B" -> boxingObject = "Byte";
            case "C" -> boxingObject = "Character";
            case "D" -> boxingObject = "Double";
            case "F" -> boxingObject = "Float";
            case "I" -> boxingObject = "Integer";
            case "J" -> boxingObject = "Long";
            case "S" -> boxingObject = "Short";
            case "Z" -> boxingObject = "Boolean";
            default -> throw new Error("Unable to identify field descriptor " + fieldDescriptor);
        }
        return StaticJavaParser.parseExpression(boxingObject + ".valueOf(" + expression + ")");
    }

    private static void boxArguments(NodeWithArguments<?> methodOrConstructorUsage) {
        NodeList<Expression> arguments = methodOrConstructorUsage.getArguments();
        for (Expression argument : arguments) {
            ResolvedType resolvedType = argument.calculateResolvedType();
            if (resolvedType.isPrimitive()) {
                Expression boxedArgument = boxExpression(argument, resolvedType.toDescriptor());
                arguments.replace(argument, boxedArgument);
            }
        }
    }

    private static MethodDeclaration resolveMethodCall(
            TypeDeclaration<?> classUnderTest,
            MethodCallExpr methodCallExpr
    ) {
        // attempt to use JavaParser to resolve method call
        try {
            return (MethodDeclaration) methodCallExpr.resolve().toAst().orElse(new MethodDeclaration());
        } catch (UnsolvedSymbolException ignored) {
        }
        // attempt to use JavaParser to resolve boxed method call
        MethodCallExpr unboxedMethodCallExpr = methodCallExpr.clone();
        boxArguments(methodCallExpr);
        try {
            return (MethodDeclaration) methodCallExpr.resolve().toAst().orElse(new MethodDeclaration());
        } catch (UnsolvedSymbolException ignored) {
            methodCallExpr.setArguments(unboxedMethodCallExpr.getArguments());
        }
        // search class under test for candidate methods
        List<MethodUsage> methodUsages = classUnderTest.resolve().getAllMethods()
                .stream()
                .filter(m -> m.getDeclaration() instanceof JavaParserMethodDeclaration)
                .toList();
        if (methodUsages.isEmpty()) {
            return new MethodDeclaration();
        }
        throw new Error("Unable to match method call " + methodCallExpr);
    }

    private static ConstructorDeclaration resolveObjectCreation(
            TypeDeclaration<?> classUnderTest,
            ObjectCreationExpr objectCreationExpr
    ) {
        // attempt to use JavaParser to resolve method call
        try {
            return (ConstructorDeclaration) objectCreationExpr.resolve().toAst().orElse(new ConstructorDeclaration());
        } catch (UnsolvedSymbolException ignored) {
        }
        // attempt to use JavaParser to resolve boxed method call
        ObjectCreationExpr unboxedObjectCreationExpr = objectCreationExpr.clone();
        boxArguments(objectCreationExpr);
        try {
            return (ConstructorDeclaration) objectCreationExpr.resolve().toAst().orElse(new ConstructorDeclaration());
        } catch (UnsolvedSymbolException ignored) {
            objectCreationExpr.setArguments(unboxedObjectCreationExpr.getArguments());
        }
        // search class under test for candidate methods
        List<MethodUsage> methodUsages = classUnderTest.resolve().getAllMethods()
                .stream()
                .filter(m -> m.getDeclaration() instanceof JavaParserMethodDeclaration)
                .toList();
        if (methodUsages.isEmpty()) {
            return new ConstructorDeclaration();
        }
        throw new Error("Unable to match method call " + objectCreationExpr);
    }

    /**
     * Parse the expression and return a list of all the occurrences of the method calls and object creations defined in the expression.
     * @param expr the expression to parse
     * @return a list of pairs of expressions and the type of the expression (method or constructor)
     * @throws UnrecognizedExprException if the expression cannot be parsed
     */
    private static List<Pair<Expression,CallableExprType>> parseExpression(Expression expr) throws UnrecognizedExprException {
        List<Pair<Expression,CallableExprType>> methodCallsAndObjectCreationsList = new ArrayList<>();
        methodCallsAndObjectCreationsList.addAll(expr.findAll(MethodCallExpr.class).stream().map(e -> new Pair<>((Expression) e, CallableExprType.METHOD)).toList());
        methodCallsAndObjectCreationsList.addAll(expr.findAll(ObjectCreationExpr.class).stream().map(e -> new Pair<>((Expression) e, CallableExprType.CONSTRUCTOR)).toList());
        return methodCallsAndObjectCreationsList;
    }

    /**
     * Match the resolved method declaration with the most similar signature to the given method call expression. The method supports
     * the searchCandidateResolvedMethodDeclaration method in finding the most similar candidate method/constructor resolving the
     * method call expression, comparing the signature and the number and types of the parameters with the list of candidate
     * methods/constructors in the resolved class.
     *
     * @param focalMethodArgs the list of types of the arguments of the callable expression
     * @param candidates the list of candidate methods/constructors in the class file
     * @return the most similar candidate method/constructor to the method call expression in the class file
     * @throws CandidateCallableDeclarationNotFoundException if no candidate method/constructor fully match the signature
     *                                                       of the callable expression
     * @throws MultipleCandidatesException if multiple candidate methods/constructors are found, and it is impossible to
     *                                     discern the focal method
     */
    private static ResolvedMethodDeclaration matchResolvedMethodDeclaration(List<ResolvedType> focalMethodArgs, List<ResolvedMethodDeclaration> candidates) throws CandidateCallableDeclarationNotFoundException, MultipleCandidatesException {
        // Define the focal method.
        ResolvedMethodDeclaration focalMethod;
        // Initialize the number of parameters in common to -1 (no parameters in common)
        int bestParamsTypesInCommon = -1;
        // Initialize the boolean flag multipleCandidates to false (only one candidate method is
        // the most similar to the method call expression)
        boolean multipleCandidates = false;
        // Initialize the most similar candidate method to null
        ResolvedMethodDeclaration mostSimilar = null;
        // Iterate over the list of candidate methods/constructors
        for (ResolvedMethodDeclaration candidate : candidates) {
            // Initialize the number of parameters in common with the current candidate to 0
            int paramsTypesInCommon = 0;
            // Iterate over the list of parameters of the current candidate method/constructor
            for (int i = 0; i < candidate.getNumberOfParams(); i++) {
                // Get the i-th parameter
                ResolvedParameterDeclaration param = candidate.getParam(i);
                // Get the type of the i-th parameter of the focal method call in the test
                ResolvedType focalMethodArg = focalMethodArgs.get(i);
                String focalMethodTypeName = focalMethodArgs.get(i).describe();
                // Perform preliminary checks to avoid comparing incompatible types
                if (!param.getType().describe().equals("java.lang.Object") && !param.getType().isTypeVariable()) {
                    if (focalMethodArg.isArray() && !param.getType().isArray()) {
                        continue;
                    }
                    if (!focalMethodArg.isArray() && param.getType().isArray()) {
                        continue;
                    }
                    if (focalMethodArg.describe().startsWith("java.util.List") && !param.getType().describe().startsWith("List")) {
                        continue;
                    }
                    if (!focalMethodArg.describe().startsWith("java.util.List") && param.getType().describe().startsWith("List")) {
                        continue;
                    }
                    if (focalMethodArg.isArray() && (param.getType().isArray())) {
                        if (focalMethodArg.asArrayType().arrayLevel() != param.getType().asArrayType().arrayLevel()) {
                            continue;
                        }
                    }
                    // Get the type of the i-th parameter of the current candidate method, as a string
                    String paramTypeName = param.getType().describe();
                    boolean matchFound = false;
                    // Compare the parameters type names
                    if (focalMethodTypeName.contains(paramTypeName.replaceAll("<.*?>|List|\\[\\]|<|>", ""))) {
                        matchFound = true;
                    } else {
                        if (param.getType().isReferenceType() && focalMethodArg.isReferenceType()) {
                            List<String> paramAncestors = param.getType().asReferenceType().getAllAncestors().stream().map(ResolvedReferenceType::describe).collect(Collectors.toList());
                            for (String ancestor : paramAncestors) {
                                if (focalMethodTypeName.contains(ancestor.replaceAll("<.*?>|List|\\[\\]|<|>", ""))) {
                                    paramTypeName = ancestor;
                                    matchFound = true;
                                    break;
                                }
                            }
                            if (!matchFound) {
                                List<String> focalAncestors = focalMethodArg.asReferenceType().getAllAncestors().stream().map(ResolvedReferenceType::describe).collect(Collectors.toList());
                                for (String ancestor : focalAncestors) {
                                    if (paramTypeName.contains(ancestor.replaceAll("<.*?>|List|\\[\\]|<|>", ""))) {
                                        focalMethodTypeName = ancestor;
                                        matchFound = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    if (matchFound) {
                        // Pattern to capture content within < and >
                        Pattern pattern = Pattern.compile("<(.*?)>");
                        Matcher matcherFocalMethod = pattern.matcher(focalMethodTypeName);
                        if (matcherFocalMethod.find()) {
                            String genericTypeFocalMethod = matcherFocalMethod.group(1); // Save the captured content
                            Matcher matcherParam = pattern.matcher(paramTypeName);

                            if (genericTypeFocalMethod.equals("?") || genericTypeFocalMethod.length() == 1) {
                                paramsTypesInCommon += 1;
                            } else {
                                if (matcherParam.find()) {
                                    String genericTypeParam = matcherParam.group(1);
                                    if (genericTypeParam.equals("?") || genericTypeParam.length() == 1 || genericTypeFocalMethod.equals(genericTypeParam)) {
                                        paramsTypesInCommon += 1;
                                    }
                                } else {
                                    paramsTypesInCommon += 1;
                                }
                            }
                        } else {
                            // If the type names corresponds, increment the number of parameters in common
                            paramsTypesInCommon += 1;
                        }
                    }
                } else {
                    // Any type inherits from Object. Increment the number of parameters in common
                    paramsTypesInCommon += 1;
                }
            }
            // If the number of parameters in common with the current candidate method/constructor is
            // greater or equal to the best number of parameters in common found so far, update the best
            // number of parameters in common and assign the current candidate method/constructor to the
            // most similar candidate method/constructor
            if (paramsTypesInCommon >= bestParamsTypesInCommon) {
                if (paramsTypesInCommon == bestParamsTypesInCommon) {
                    if (mostSimilar.isAbstract() && !candidate.isAbstract()) {
                        mostSimilar = candidate;
                    } else if (candidate.getQualifiedName().startsWith("java.util.List") && mostSimilar.getQualifiedName().startsWith("java.util.Collection")) {
                        mostSimilar = candidate;
                    } else if (mostSimilar.getQualifiedName().startsWith("java.util.List") && candidate.getQualifiedName().startsWith("java.util.Collection")) {
                        continue;
                    } else if (mostSimilar.isAbstract() && candidate.isAbstract()) {
                        multipleCandidates = true;
                    }
                } else {
                    bestParamsTypesInCommon = paramsTypesInCommon;
                    multipleCandidates = false;
                    mostSimilar = candidate;
                }
            }
        }
        // If only one candidate method/constructor is the most similar to the focal method call expression, set
        // the focal method to the most similar candidate method
        if (!(multipleCandidates || mostSimilar == null)) {
            focalMethod = mostSimilar;
            if (bestParamsTypesInCommon < focalMethodArgs.size()) {
                // If the number of parameters in common is less than the number of parameters of
                // the method call expression, log a warning
                throw new CandidateCallableDeclarationNotFoundException("[WARNING] - No candidate method fully match the signature of the callable expression. No focal method found.");
            }
        } else {
            if (multipleCandidates) {
                // Multiple candidates, impossible to discern
                throw new MultipleCandidatesException(String.format("[WARNING] - Multiple candidate methods/constructors found. Impossible to discern the focal method."));
            } else {
                // No candidate available
                throw new CandidateCallableDeclarationNotFoundException("[WARNING] - No candidate method fully match the signature of the callable expression. No focal method found.");
            }
        }
        return focalMethod;
    }

    /**
     * Search the resolved method declaration corresponding to a given callable expression, by comparing the method call expression
     * with the list of methods in the resolved class file where the callable declaration is supposed to be defined (analyzing the
     * signature and the number and types of the parameters).
     * The method is useful when is not possible to resolve the method call expression to a method declaration by Java Symbol
     * Solver, and it is necessary to apply heuristics to find the callable declaration.
     * @param resolvedReferenceType the resolved reference declaration corresponding to the class where to search the
     *                              definition of the method or constructor (i.e. the callable expression) defined
     * @param methodCallExpr the callable expression to resolve
     * @return the corresponding {@link ResolvedMethodDeclaration} of the callable expression, if found. Otherwise, return null.
     * @throws MultipleCandidatesException if multiple candidates are found for the callable expression and the method
     *                                    or constructor cannot be resolved uniquely
     * @throws CandidateCallableDeclarationNotFoundException if the method or constructor corresponding to the callable
     *                                                       expression cannot be found and no candidates are available
     */
    public static ResolvedMethodDeclaration searchCandidateResolvedMethodDeclaration(ResolvedReferenceType resolvedReferenceType, MethodCallExpr methodCallExpr) throws MultipleCandidatesException, CandidateCallableDeclarationNotFoundException {
        // Define the list of arguments of the focal method. Initially empty.
        NodeList<Expression> focalMethodArgs = new NodeList<>();
        // Define the focal method. Initially null.
        ResolvedMethodDeclaration focalMethod = null;
        // Define a list of candidate methods/constructors (methods/constructors with the same name and number of parameters).
        // Initially empty.
        List<ResolvedMethodDeclaration> candidates = new ArrayList<>();

        List<ResolvedMethodDeclaration> classMethodsList = resolvedReferenceType.getAllMethods();
        String focalMethodName = methodCallExpr.getNameAsString();
        focalMethodArgs = methodCallExpr.getArguments();
        // Iterate over the list of methods/constructors in the class file
        for (ResolvedMethodDeclaration m : classMethodsList) {
            // Check if the method/constructor name is the same
            if (m.getName().equals(focalMethodName)) {
                // Check if the number of parameters is the same
                if (m.getNumberOfParams() == focalMethodArgs.size()) {
                    // Add method/constructor to candidates if the previous conditions are satisfied
                    candidates.add(m);
                }
            }
        }
        // If no candidate methods are found, throw an exception (focal method not found)
        if (candidates.size() == 0) {
            return focalMethod;
        } else if (candidates.size() == 1) {
            // If only one candidate method/constructor is found, set the focal method to the candidate method/constructor
            focalMethod = candidates.get(0);
        } else {
            List<ResolvedType> focalMethodResolvedTypes = focalMethodArgs.stream().map(a -> a.calculateResolvedType()).collect(Collectors.toList());
            focalMethod = matchResolvedMethodDeclaration(focalMethodResolvedTypes, candidates);
        }
        return focalMethod;
    }

    /**
     * Process the callable expression return the corresponding {@link CallableDeclaration}
     * @param typeDeclaration the type declaration corresponding to the class where to search the definition of the method
     *                        or constructor (i.e. the callable expression) defined
     * @param callExpr the callable expression to resolve
     * @param exprType the type of the callable expression (i.e. method or constructor)
     * @return the corresponding {@link CallableDeclaration} of the callable expression, if found. Otherwise, return null.
     * @throws MultipleCandidatesException if multiple candidates are found for the callable expression and the method
     *                                     or constructor cannot be resolved uniquely
     * @throws CandidateCallableDeclarationNotFoundException if the method or constructor corresponding to the callable
     *                                                       expression cannot be found and no candidates are available
     */
    public static CallableDeclaration resolveCallExpression(TypeDeclaration typeDeclaration, Expression callExpr, CallableExprType exprType) throws MultipleCandidatesException, CandidateCallableDeclarationNotFoundException {
        // Define the focal method. Initially null.
        CallableDeclaration focalMethod = null;
        try {
            if (exprType == CallableExprType.METHOD) {
                // Try to resolve the method call expression to a method declaration
                MethodCallExpr methodCallExpr = callExpr.asMethodCallExpr();
                focalMethod = (MethodDeclaration) methodCallExpr.resolve().toAst().orElseThrow(() -> new UnsolvedSymbolException(methodCallExpr.getNameAsString()));
                // Retrieve the method within the type declaration to avoid to lose the symbol solver functionality
                for (MethodDeclaration m : (List<MethodDeclaration>) typeDeclaration.getMethods()) {
                    if (m.getDeclarationAsString(true, true, true).equals(focalMethod.getDeclarationAsString(true, true, true))) {
                        return m;
                    }
                }
                //logger.error("Resolved method {} not found in type declaration {}", focalMethod.getNameAsString(), typeDeclaration.getNameAsString());
                return focalMethod;
            } else if (exprType == CallableExprType.CONSTRUCTOR) {
                // Try to resolve the constructor call expression to a constructor declaration
                ObjectCreationExpr constructorCallExpr = callExpr.asObjectCreationExpr();
                focalMethod = (ConstructorDeclaration) constructorCallExpr.resolve().toAst().orElseThrow(() -> new UnsolvedSymbolException(constructorCallExpr.getTypeAsString()));
                // Retrieve the method within the type declaration to avoid to lose the symbol solver functionality
                for (ConstructorDeclaration c : (List<ConstructorDeclaration>) typeDeclaration.getConstructors()) {
                    if (c.getDeclarationAsString(true, true, true).equals(focalMethod.getDeclarationAsString(true, true, true))) {
                        return c;
                    }
                }
                //logger.error("Resolved constructor {} not found in type declaration {}", focalMethod.getNameAsString(), typeDeclaration.getNameAsString());
                return focalMethod;
            }
        } catch (UnsolvedSymbolException e) {
            // The method call expression could not be resolved to a method declaration by
            // Java Symbol Solver
            focalMethod = null;
        }
        // If the method call expression could not be resolved to a method declaration by Java
        // Symbol Solver, try to find the method declaration by comparing the method call expression
        // with the list of methods in the class file (analyzing the signature and the number and types
        // of the parameters).
        if (focalMethod == null) {
            return searchCandidateCallableDeclaration(typeDeclaration, callExpr, exprType);
        }
        return focalMethod;
    }

    /**
     * Search the method declaration corresponding to a given callable expression, by comparing the method call expression
     * with the list of methods in the class file where the callable declaration is supposed to be defined (analyzing the
     * signature and the number and types of the parameters).
     * The method is useful when is not possible to resolve the method call expression to a method declaration by Java Symbol
     * Solver and it is necessary to apply heuristics to find the callable declaration.
     * @param typeDeclaration the type declaration corresponding to the class where to search the definition of the method
     *                        or constructor (i.e. the callable expression) defined
     * @param lastCallExpr the callable expression to resolve
     * @param exprType the type of the callable expression (i.e. method or constructor)
     * @return the corresponding {@link CallableDeclaration} of the callable expression, if found. Otherwise, return null.
     * @throws MultipleCandidatesException if multiple candidates are found for the callable expression and the method
     *                                    or constructor cannot be resolved uniquely
     * @throws CandidateCallableDeclarationNotFoundException if the method or constructor corresponding to the callable
     *                                                       expression cannot be found and no candidates are available
     */
    private static CallableDeclaration searchCandidateCallableDeclaration(TypeDeclaration typeDeclaration, Expression lastCallExpr, CallableExprType exprType) throws MultipleCandidatesException, CandidateCallableDeclarationNotFoundException {
        // Define the list of all the callable methods/constructors in the class file. Initially empty.
        List<? extends CallableDeclaration<?>> classCallableList = new ArrayList<>();
        // Define the name of the focal method. Initially null.
        String focalMethodName = null;
        // Define the list of arguments of the focal method. Initially empty.
        NodeList<Expression> focalMethodArgs = new NodeList<>();
        // Define the focal method. Initially null.
        CallableDeclaration focalMethod = null;
        // Define a list of candidate methods/constructors (methods/constructors with the same name and number of parameters).
        // Initially empty.
        List<CallableDeclaration> candidatesCallables = new ArrayList<>();

        if (exprType == CallableExprType.METHOD) {
            classCallableList = typeDeclaration.getMethods();
            focalMethodName = ((MethodCallExpr) lastCallExpr).getNameAsString();
            focalMethodArgs = ((MethodCallExpr) lastCallExpr).getArguments();
        } else if (exprType == CallableExprType.CONSTRUCTOR) {
            classCallableList = typeDeclaration.getConstructors();
            focalMethodName = ((ObjectCreationExpr) lastCallExpr).getTypeAsString();
            focalMethodArgs = ((ObjectCreationExpr) lastCallExpr).getArguments();
        }
        // Iterate over the list of methods/constructors in the class file
        for (CallableDeclaration callable : classCallableList) {
            // Check if the method/constructor name is the same
            if (callable.getNameAsString().equals(focalMethodName)) {
                // Check if the number of parameters is the same
                if (callable.getParameters().size() == focalMethodArgs.size()) {
                    // Add method/constructor to candidates if the previous conditions are satisfied
                    candidatesCallables.add(callable);
                }
            }
        }
        // If no candidate methods are found, throw an exception (focal method not found)
        if (candidatesCallables.size() == 0) {
            return focalMethod;
        } else if (candidatesCallables.size() == 1) {
            // If only one candidate method/constructor is found, set the focal method to the candidate method/constructor
            focalMethod = candidatesCallables.get(0);
        } else {
            List<ResolvedType> focalMethodResolvedTypes = focalMethodArgs.stream().map(a -> a.calculateResolvedType()).collect(Collectors.toList());
            focalMethod = matchCallableDeclaration(focalMethodResolvedTypes, candidatesCallables);
        }
        return focalMethod;
    }

    /**
     * Match the callable declaration with the most similar signature to the given method call expression. The method supports
     * the searchCandidateCallableDeclaration method in finding the most similar candidate method/constructor resolving the
     * method call expression, comparing the signature and the number and types of the parameters with the list of candidate
     * methods/constructors in the class file.
     *
     * @param focalMethodArgs the list of types of the arguments of the callable expression
     * @param candidatesCallables the list of candidate methods/constructors in the class file
     * @return the most similar candidate method/constructor to the method call expression in the class file
     * @throws CandidateCallableDeclarationNotFoundException if no candidate method/constructor fully match the signature
     *                                                       of the callable expression
     * @throws MultipleCandidatesException if multiple candidate methods/constructors are found, and it is impossible to
     *                                     discern the focal method
     */
    private static CallableDeclaration matchCallableDeclaration(List<ResolvedType> focalMethodArgs, List<CallableDeclaration> candidatesCallables) throws CandidateCallableDeclarationNotFoundException, MultipleCandidatesException {
        // Define the focal method.
        CallableDeclaration focalMethod;
        // Initialize the number of parameters in common to -1 (no parameters in common)
        int bestParamsTypesInCommon = -1;
        // Initialize the boolean flag multipleCandidates to false (only one candidate method is
        // the most similar to the method call expression)
        boolean multipleCandidates = false;
        // Initialize the most similar candidate method to null
        CallableDeclaration mostSimilar = null;
        // Iterate over the list of candidate methods/constructors
        for (CallableDeclaration callable : candidatesCallables) {
            // Get the list of parameters of the candidate method/constructor
            NodeList<com.github.javaparser.ast.body.Parameter> paramsList = callable.getParameters();
            // Initialize the number of parameters in common with the current candidate to 0
            int paramsTypesInCommon = 0;
            // Iterate over the list of parameters of the current candidate method/constructor
            for (int i = 0; i < paramsList.size(); i++) {
                // Get the i-th parameter
                com.github.javaparser.ast.body.Parameter param = paramsList.get(i);
                // Get the type of the i-th parameter of the focal method call in the test
                ResolvedType focalMethodArg = focalMethodArgs.get(i);
                String focalMethodTypeName = focalMethodArgs.get(i).describe();
                if (!param.getType().resolve().describe().equals("java.lang.Object") || !param.getType().resolve().isTypeVariable()) {
                    // Perform preliminary checks to avoid comparing incompatible types
                    if (focalMethodArg.isArray() && !(param.getType().isArrayType() || param.isVarArgs())) {
                        continue;
                    }
                    if (!focalMethodArg.isArray() && (param.getType().isArrayType() || param.isVarArgs())) {
                        continue;
                    }
                    if (focalMethodArg.describe().startsWith("java.util.List") && !param.getTypeAsString().startsWith("List")) {
                        continue;
                    }
                    if (!focalMethodArg.describe().startsWith("java.util.List") && param.getTypeAsString().startsWith("List")) {
                        continue;
                    }
                    if (focalMethodArg.isArray() && (param.getType().isArrayType() || param.isVarArgs())) {
                        if (param.isVarArgs() && (focalMethodArg.asArrayType().arrayLevel() > 1)) {
                            continue;
                        } else if (!param.isVarArgs() && (focalMethodArg.asArrayType().arrayLevel() != param.getType().asArrayType().getArrayLevel())) {
                            continue;
                        }
                    }
                    // Get the type of the i-th parameter of the current candidate method, as a string
                    String paramTypeName = param.getTypeAsString();
                    // Compare the parameters type names
                    if (focalMethodTypeName.contains(paramTypeName.replaceAll("<.*?>|List|\\[\\]|<|>", ""))) {
                        // Pattern to capture content within < and >
                        Pattern pattern = Pattern.compile("<(.*?)>");
                        Matcher matcherFocalMethod = pattern.matcher(focalMethodTypeName);
                        if (matcherFocalMethod.find()) {
                            String genericTypeFocalMethod = matcherFocalMethod.group(1); // Save the captured content
                            Matcher matcherParam = pattern.matcher(paramTypeName);

                            if (genericTypeFocalMethod.equals("?") || genericTypeFocalMethod.length() == 1) {
                                paramsTypesInCommon += 1;
                            } else {
                                if (matcherParam.find()) {
                                    String genericTypeParam = matcherParam.group(1);
                                    if (genericTypeParam.equals("?") || genericTypeParam.length() == 1 || genericTypeFocalMethod.equals(genericTypeParam)) {
                                        paramsTypesInCommon += 1;
                                    }
                                } else {
                                    paramsTypesInCommon += 1;
                                }
                            }
                        } else {
                            // If the type names corresponds, increment the number of parameters in common
                            paramsTypesInCommon += 1;
                        }
                    }
                } else {
                    // Any type inherits from Object. Increment the number of parameters in common
                    paramsTypesInCommon += 1;
                }
            }
            // If the number of parameters in common with the current candidate method/constructor is
            // greater or equal to the best number of parameters in common found so far, update the best
            // number of parameters in common and assign the current candidate method/constructor to the
            // most similar candidate method/constructor
            if (paramsTypesInCommon >= bestParamsTypesInCommon) {
                if (paramsTypesInCommon == bestParamsTypesInCommon) {
                    if ((mostSimilar.isAbstract() && !callable.isAbstract()) || (mostSimilar.isPrivate() && callable.isPublic())) {
                        mostSimilar = callable;
                    } else if (mostSimilar.isAbstract() && callable.isAbstract()) {
                        multipleCandidates = true;
                    }
                } else {
                    bestParamsTypesInCommon = paramsTypesInCommon;
                    multipleCandidates = false;
                    mostSimilar = callable;
                }
            }
        }
        // If only one candidate method/constructor is the most similar to the focal method call expression, set
        // the focal method to the most similar candidate method
        if (!(multipleCandidates || mostSimilar == null)) {
            focalMethod = mostSimilar;
            if (bestParamsTypesInCommon < focalMethodArgs.size()) {
                // If the number of parameters in common is less than the number of parameters of
                // the method call expression, log a warning
                throw new CandidateCallableDeclarationNotFoundException("[WARNING] - No candidate method fully match the signature of the callable expression. No focal method found.");
            }
        } else {
            if (multipleCandidates) {
                // Multiple candidates, impossible to discern
                throw new MultipleCandidatesException(String.format("[WARNING] - Multiple candidate methods/constructors found. Impossible to discern the focal method."));
            } else {
                // No candidate available
                throw new CandidateCallableDeclarationNotFoundException("[WARNING] - No candidate method fully match the signature of the callable expression. No focal method found.");
            }
        }
        return focalMethod;
    }

    /**
     * Search the {@link MethodUsage} corresponding to a given method call expression, by comparing the method call expression
     * with the list of methods in the class file where the method call expression is supposed to be defined (analyzing the
     * signature and the number and types of the parameters).
     * The method is useful when is not possible to resolve the method call expression to a method declaration by Java Symbol
     * Solver, and it is necessary to apply heuristics to find the method in the form of {@link MethodUsage}.
     * @param typeDeclaration the type declaration corresponding to the class where to search the definition of the method
     *                        defined in the method call expression
     * @param expr the method call expression to resolve
     * @return the corresponding {@link MethodUsage} of the method call expression, if found. Otherwise, return null.
     * @throws CandidateCallableMethodUsageNotFoundException if the method corresponding to the method call expression
     *                                                       cannot be found and no candidates are available
     * @throws MultipleCandidatesException if multiple candidates are found for the method call expression and the method
     *                                     cannot be resolved uniquely
     */
    public static MethodUsage searchCandidateMethodUsage(TypeDeclaration typeDeclaration, Expression expr) throws CandidateCallableMethodUsageNotFoundException, MultipleCandidatesException {
        try {
            return iterateOverCandidatesMethodUsage(typeDeclaration.resolve().getAllMethods(), expr.asMethodCallExpr());
        } catch (CandidateCallableMethodUsageNotFoundException | UnsolvedSymbolException e) {
            return null;
        }
    }

    private static MethodUsage iterateOverCandidatesMethodUsage(Set<MethodUsage> methods, MethodCallExpr methodCallExpr) throws CandidateCallableMethodUsageNotFoundException, MultipleCandidatesException {
        List<MethodUsage> methodNotConverted = new ArrayList<>();
        List<MethodDeclaration> methodConverted = new ArrayList<>();
        // Define the focal method. Initially null.
        MethodUsage focalMethod = null;
        // Define a list of candidate methods/constructors (methods/constructors with the same name and number of parameters).
        // Initially empty.
        List<MethodUsage> candidatesMethodUsage = new ArrayList<>();
        // Get the name of the focal method
        String focalMethodName = methodCallExpr.getNameAsString();
        // Get the list of arguments of the focal method
        NodeList<Expression> focalMethodArgs = methodCallExpr.getArguments();

        for(MethodUsage m : methods) {
            try {
                methodConverted.add((MethodDeclaration) m.getDeclaration().toAst().get());
            } catch(Exception e) {
                methodNotConverted.add(m);
            }
        }

        // Iterate over the list of methods/constructors in the class file
        for (MethodUsage methodUsage : methodNotConverted) {
            // Check if the method/constructor name is the same
            if (methodUsage.getName().equals(focalMethodName)) {
                // Check if the number of parameters is the same
                if (methodUsage.getParamTypes().size() == focalMethodArgs.size()) {
                    // Add method/constructor to candidates if the previous conditions are satisfied
                    candidatesMethodUsage.add(methodUsage);
                }
            }
        }

        if (candidatesMethodUsage.size() == 0) {
            throw new CandidateCallableMethodUsageNotFoundException("[WARNING] - No candidate method usage found in the original class, analyzing both the name and the number of parameters. Focal method not found. ");
        } else if (candidatesMethodUsage.size() == 1) {
            // If only one candidate method/constructor is found, set the focal method to the candidate method/constructor
            focalMethod = candidatesMethodUsage.get(0);
        } else {
            // If multiple candidate methods are found, try to discern the focal method by analyzing
            // the types of the parameters of the focal method call expression and the parameters of the
            // candidate methods/constructors.
            // Initialize the number of parameters in common to -1 (no parameters in common)
            int bestParamsTypesInCommon = -1;
            // Initialize the boolean flag multipleCandidates to false (only one candidate method/constructor is
            // the most similar to the method call expression)
            boolean multipleCandidates = false;
            // Initialize the most similar candidate method/constructor to null
            MethodUsage mostSimilar = null;
            // Iterate over the list of candidate methods/constructors
            for (MethodUsage candidate : candidatesMethodUsage) {
                // Get the list of parameters of the candidate method/constructor
                List<ResolvedType> paramsTypeList = candidate.getParamTypes();
                // Initialize the number of parameters in common with the current candidate to 0
                int paramsTypesInCommon = 0;
                // Iterate over the list of parameters of the current candidate method/constructor
                for (int i = 0; i < paramsTypeList.size(); i++) {
                    // Get the i-th parameter
                    ResolvedType paramType = paramsTypeList.get(i);
                    // Get the type of the i-th parameter of the focal method call in the test
                    // prefix, as a string
                    String focalMethodTypeName = focalMethodArgs.get(i).calculateResolvedType().describe();
                    // Get the type of the i-th parameter of the current candidate method, as a string
                    String paramTypeName = paramType.describe();
                    // Compare the parameters type names
                    if (focalMethodTypeName.endsWith(paramTypeName) || paramTypeName.endsWith("Object") || paramType.isTypeVariable()) {
                        // If the type names corresponds or the candidate param type is Object,
                        // increment the number of parameters in common (any type inherits from Object)
                        paramsTypesInCommon += 1;
                    }
                }
                // If the number of parameters in common with the current candidate method/constructor is
                // greater or equal to the best number of parameters in common found so far, update the best
                // number of parameters in common and assign the current candidate method/constructor to the
                // most similar candidate method/constructor
                if (paramsTypesInCommon >= bestParamsTypesInCommon) {
                    if (paramsTypesInCommon == bestParamsTypesInCommon) {
                        if (mostSimilar.getDeclaration().isAbstract() && !candidate.getDeclaration().isAbstract()) {
                            mostSimilar = candidate;
                        } else if (mostSimilar.getDeclaration().isAbstract() && candidate.getDeclaration().isAbstract()) {
                            multipleCandidates = true;
                        }
                    } else {
                        bestParamsTypesInCommon = paramsTypesInCommon;
                        multipleCandidates = false;
                        mostSimilar = candidate;
                    }
                }
            }
            // If only one candidate method/constructor is the most similar to the focal method call expression, set
            // the focal method to the most similar candidate method
            if (!(multipleCandidates || mostSimilar == null)) {
                focalMethod = mostSimilar;
                if (bestParamsTypesInCommon < focalMethodArgs.size()) {
                    // If the number of parameters in common is less than the number of parameters of
                    // the method call expression, log a warning
                    throw new CandidateCallableMethodUsageNotFoundException("[WARNING] - No candidate method fully match the signature of the callable expression. No focal method found.");
                }
            } else {
                // Multiple candidates, impossible to discern
                throw new MultipleCandidatesException("[WARNING] - Multiple candidate methods/constructors found. Impossible to discern the focal method.");
            }
        }
        return focalMethod;
    }

    /**
     * Match the method declaration with the most similar signature to the given method in the form of {@link MethodUsage}.
     * @param referenceMethodUsage the method usage to resolve
     * @param methodDeclarationList the list of methods in the reference class where to search the corresponding method declaration
     * @return the corresponding {@link MethodDeclaration} of the method usage, if found.
     * @throws CandidateCallableDeclarationNotFoundException if the method corresponding to the method usage
     *                                                       cannot be found and no candidates are available
     * @throws MultipleCandidatesException if multiple candidates are found for the method usage and the method
     *                                     cannot be resolved uniquely
     */
    public static CallableDeclaration matchMethodUsageWithMethodDeclaration(MethodUsage referenceMethodUsage, List<MethodDeclaration> methodDeclarationList) throws  CandidateCallableDeclarationNotFoundException, MultipleCandidatesException {
        String referenceMethodUsageName = referenceMethodUsage.getName();
        List<ResolvedType> referenceMehodUsageArgs = referenceMethodUsage.getParamTypes();
        // Define the focal method. Initially null.
        CallableDeclaration focalMethod = null;
        // Define a list of candidate methods (methods with the same name and number of parameters).
        // Initially empty.
        List<CallableDeclaration> candidatesMethods = new ArrayList<>();
        // Iterate over the list of methods/constructors in the class file
        for (MethodDeclaration method : methodDeclarationList) {
            // Check if the method/constructor name is the same
            if (method.getNameAsString().equals(referenceMethodUsageName)) {
                // Check if the number of parameters is the same
                if (method.getParameters().size() == referenceMehodUsageArgs.size()) {
                    // Add method/constructor to candidates if the previous conditions are satisfied
                    candidatesMethods.add(method);
                }
            }
        }
        // If no candidate methods are found, throw an exception (focal method not found)
        if (candidatesMethods.size() == 0) {
            return focalMethod;
        } else if (candidatesMethods.size() == 1) {
            // If only one candidate method/constructor is found, set the focal method to the candidate method/constructor
            focalMethod = candidatesMethods.get(0);
        } else {
            focalMethod = matchCallableDeclaration(referenceMethodUsage.getParamTypes(), candidatesMethods);
        }
        return focalMethod;
    }

    private static Pair<CallableDeclaration<?>,HashMap<String, TypeDeclaration>> processInvokedMethod(Path srcDir, TypeDeclaration classUnderTest, Expression oracleExpr, HashMap<String, TypeDeclaration> projectVariables) {
        try {
            if (projectVariables.isEmpty()) {
                for (FieldDeclaration classField : classUnderTest.findAll(FieldDeclaration.class)) {
                    for (VariableDeclarator variable : classField.getVariables()) {
                        Type variableType = variable.getType();
                        while (variableType.isArrayType()) {
                            variableType = variableType.asArrayType().getComponentType();
                        }
                        // TODO: Handle the case when the variable type is an array
                        if (variableType.isReferenceType() && !variableType.resolve().isTypeVariable()) {
                            try {
                                ResolvedReferenceType resolvedReferenceType = variableType.resolve().asReferenceType();
                                Path fqnVarType = FileUtils.getFQNPath(resolvedReferenceType.getQualifiedName());
                                Path classFilePath = srcDir.resolve(fqnVarType);
                                if (Files.exists(classFilePath)) {
                                    try {
                                        CompilationUnit cuVarType = FileUtils.getCompilationUnit(classFilePath);
                                        projectVariables.put(variable.getNameAsString(), cuVarType.getPrimaryType().orElseThrow(() -> new NoPrimaryTypeException("[ERROR] - ClassFile from method usage, with no primary file.")));
                                    } catch (Error e) {
                                        System.err.println("Error reading file: " + classFilePath);
                                    }
                                }
                            } catch (UnsolvedSymbolException e) {
                                System.err.println("Unable to resolve variable type " + variableType.toString() + " in test class " + classUnderTest.getNameAsString());
                            }
                        }
                    }
                }
            }

            Expression expr = null;
            CallableExprType exprType = null;
            // Parse the expression to understand if it refers to a method call or construction invocation
            List<Pair<Expression,CallableExprType>> expressionAnalysisResult = parseExpression(oracleExpr);
            for (Pair<Expression,CallableExprType> exprPair : expressionAnalysisResult) {
                try {
                    // Get the expression and its type
                    expr = exprPair.getValue0();
                    exprType = exprPair.getValue1();
                    // Initialize the invoked class to the reference class. The invoked class is the instance of the class
                    // that contains the invoked method.
                    TypeDeclaration invokedClass = classUnderTest;

                    if (exprType == CallableExprType.METHOD) {
                        if (expr.asMethodCallExpr().getScope().isPresent()) {
                            Expression scope = expr.asMethodCallExpr().getScope().get();
                            while (scope.isArrayAccessExpr()) {
                                scope = scope.asArrayAccessExpr().getName();
                            }
                            if (scope.isNameExpr()) {
                                String scopeName = scope.asNameExpr().getNameAsString();
                                if (projectVariables.containsKey(scopeName)) {
                                    invokedClass = projectVariables.get(scopeName);
                                }
                            }
                        }
                    }

                    // Get the invoked method. The invoked method must be a method declaration or a constructor declaration.
                    // Start searching the method or constructor in the test class
                    CallableDeclaration invokedMethod = resolveCallExpression(invokedClass, expr, exprType);
                    // If the focal method is still not found, search it within the inherited methods of the focal class
                    if (invokedMethod == null) {
                        if (exprType == CallableExprType.CONSTRUCTOR) {
                            String candidateClassPath = expr.asObjectCreationExpr().getType().resolve().asReferenceType().getQualifiedName();
                            Path fullyQualifiedCandidateFilePath = srcDir.resolve(FileUtils.getFQNPath(candidateClassPath));
                            if (Files.exists(fullyQualifiedCandidateFilePath)) {
                                invokedClass = FileUtils.getCompilationUnit(fullyQualifiedCandidateFilePath).getPrimaryType().get();
                                invokedMethod = resolveCallExpression(invokedClass, expr, exprType);
                                if (invokedMethod == null) {
                                    throw new NoFocalMethodMatchingException("The constructor in the expression " + expr.toString() + " cannot be resolved in the given focal class " + invokedClass.getNameAsString());
                                }
                            }
                        } else {
                            MethodUsage invokedMethodUsage = searchCandidateMethodUsage(invokedClass, expr);
                            // If the focal method is still not found, raise an exception
                            if (!(invokedMethodUsage == null)) {
                                Path fullyQualifiedClassNameLastExpressionPath = FileUtils.getFQNPath(invokedMethodUsage.declaringType().getQualifiedName());
                                Path classFileInvokedMethodUsagePath = srcDir.resolve(fullyQualifiedClassNameLastExpressionPath);
                                if (Files.exists(classFileInvokedMethodUsagePath)) {
                                    try {
                                        CompilationUnit cuInvokedMethodUsage = FileUtils.getCompilationUnit(classFileInvokedMethodUsagePath);
                                        invokedClass = cuInvokedMethodUsage.getPrimaryType().orElseThrow(() -> new NoPrimaryTypeException("[ERROR] - ClassFile from method usage, with no primary file."));
                                        List<MethodDeclaration> candidateMethods = invokedClass.getMethods();
                                        invokedMethod = matchMethodUsageWithMethodDeclaration(invokedMethodUsage, candidateMethods);
                                    } catch (Error e) {
                                        System.err.println("Error reading file: " + classFileInvokedMethodUsagePath);
                                    }
                                }
                            }
                        }
                    }
                    if (oracleExpr.isVariableDeclarationExpr() ) {
                        VariableDeclarationExpr varExpr = oracleExpr.asVariableDeclarationExpr();
                        for (VariableDeclarator var : varExpr.getVariables()) {
                            Type varType = var.getType();
                            while (varType.isArrayType()) {
                                varType = varType.asArrayType().getComponentType();
                            }
                            // TODO: Handle the case when the variable type is an array
                            if (varType.isReferenceType()) {
                                try {
                                    Path fqnReturnType = FileUtils.getFQNPath(varType.resolve().asReferenceType().getQualifiedName());
                                    Path classFilePath = srcDir.resolve(fqnReturnType);
                                    if (Files.exists(classFilePath)) {
                                        try {
                                            CompilationUnit cuReturnType = FileUtils.getCompilationUnit(classFilePath);
                                            projectVariables.put(var.getNameAsString(), cuReturnType.getPrimaryType().orElseThrow(() -> new NoPrimaryTypeException("[ERROR] - ClassFile from method usage, with no primary file.")));
                                        } catch (Error e) {
                                            System.err.println("Error reading file: " + classFilePath);
                                        }
                                    }
                                } catch (UnsolvedSymbolException e) {
                                    System.err.println("Unable to resolve variable type " + varType + " in test class " + classUnderTest.getNameAsString());
                                }
                            }
                        }
                    }
                    // If the invoked method is still not found, process the expression as a call from an external library
                    if (invokedMethod != null) {
                        return new Pair<>(invokedMethod,projectVariables);
                    }
                    return new Pair<>(null,projectVariables);
                } catch (
                        NoFocalMethodMatchingException | NoPrimaryTypeException | UnrecognizedExprException |
                        CandidateCallableDeclarationNotFoundException | CandidateCallableMethodUsageNotFoundException |
                        MultipleCandidatesException | UnsolvedSymbolException | IllegalStateException e
                ) {
                    System.err.println(String.format("Error processing expression %s of test case", expr));
                }
            }
        } catch (Exception e) {
            String errMsg = String.format(
                    "[ERROR] - Unexpected error while retrieving callable declaration of class %s - expression %s - exception: %s",
                    classUnderTest.getNameAsString(),
                    oracleExpr.toString(),
                    e.getClass().getName()
            );
            System.err.println(errMsg);
        }
        return new Pair<>(null,projectVariables);
    }






    private static CallableDeclaration<?> resolveDeclaration(
            TypeDeclaration<?> classUnderTest,
            Expression oracleExpr
    ) {
        if (oracleExpr.isMethodCallExpr()) {
            return resolveMethodCall(classUnderTest, oracleExpr.asMethodCallExpr());
        } else if (oracleExpr.isObjectCreationExpr()) {
            return resolveObjectCreation(classUnderTest, oracleExpr.asObjectCreationExpr());
        } else {
            return null;
        }
    }

    /**
     * Adds axiomatic oracles to test prefixes in a given Java file.
     * Axiomatic oracles are not specific to a given test prefix. The oracles
     * are inserted wherever they may be applicable in source code. For
     * example, if a TOG generates an axiomatic post-condition for a method
     * "foo", then the oracle is added after every call to "foo" in the test
     * prefix. The approach for inserting an axiomatic oracle differs based on
     * the {@link OracleType} (PRE, NORMAL_POST, EXCEPT_POST). This method
     * iterates through each line in each test case, and adds all related
     * oracles.
     *
     * @param testFile a Java test file
     * @param classUnderTest the class corresponding to the EvoSuite test file
     * @param oracles a list of test oracles made by an axiomatic tog
     */
    private static void insertAxiomaticOracles(
            Path srcDir,
            CompilationUnit testFile,
            TypeDeclaration<?> classUnderTest,
            List<OracleOutput> oracles
    ) {
        List<MethodDeclaration> tests = testFile.findAll(MethodDeclaration.class);
        for (MethodDeclaration test : tests) {
            NodeList<Statement> oldTestBody = test.getBody().orElseThrow().getStatements();
            NodeList<Statement> newTestBody = new NodeList<>();
            // Define an HashMap saving information about the variable progressively defined (and used) within the test case
            HashMap<String, TypeDeclaration> projectVariables = new HashMap<>();
            for (Statement testStatement : oldTestBody) {
                // get all oracles related to a method call
                Expression oracleExpr = getOracleExpression(testStatement);
                if (oracleExpr == null) {
                    newTestBody.add(testStatement.clone());
                    continue;
                }
                Pair<CallableDeclaration<?>,HashMap<String, TypeDeclaration>> callableDeclarationPair = processInvokedMethod(srcDir, classUnderTest, oracleExpr, projectVariables); //resolveDeclaration(classUnderTest, oracleExpr);
                CallableDeclaration<?> callableDeclaration = callableDeclarationPair.getValue0();
                projectVariables = callableDeclarationPair.getValue1();
                if (callableDeclaration == null) {
                    newTestBody.add(testStatement.clone());
                    continue;
                }
                List<OracleOutput> relatedOracles = getRelatedOracles(oracles, callableDeclaration);
                // insert oracles into test suite
                if (relatedOracles.isEmpty()) {
                    newTestBody.add(testStatement.clone());
                } else {
                    newTestBody.addAll(getOracleStatements(callableDeclaration, oracleExpr, testStatement, relatedOracles));
                }
            }
            test.setBody(new BlockStmt(newTestBody));
        }
    }

    /**
     * Adds an assertion to the end of a given test prefix. This method does
     * nothing if the assertion is an empty string.
     *
     * @param testBody the body of a test case
     * @param assertion the assertion to add
     */
    private static void insertNonAxiomaticAssertion(BlockStmt testBody, String assertion) {
        if (!assertion.isEmpty()) {
            testBody
                    .getStatements()
                    .add(StaticJavaParser.parseStatement(assertion + ";"));
        }
    }

    /**
     * Wraps a test prefix with a try/catch block, where the catch block
     * expects a given exception type.
     *
     * @param testBody the body of a test case
     * @param exception the exception to catch
     */
    private static void insertNonAxiomaticException(BlockStmt testBody, String exception) {
        // Create catch block
        Parameter exceptionType = new Parameter()
                .setName("e")
                .setType(StaticJavaParser.parseType(exception));
        CatchClause catchClause = new CatchClause()
                .setParameter(exceptionType);
        List<TryStmt> tryStmts = testBody.findAll(TryStmt.class);

        if (tryStmts.size() > 1) {
            throw new IllegalStateException("Unexpected multiple try/catch within test prefix.");
        }

        TryStmt tryStmt = tryStmts.isEmpty() ? new TryStmt() : tryStmts.get(0);

        if (tryStmts.isEmpty()) {
            ExpressionStmt failStatement = StaticJavaParser.parseStatement("fail();").asExpressionStmt();
            tryStmt.setTryBlock(testBody.addStatement(failStatement));
        }

        tryStmt
                .getCatchClauses()
                .add(catchClause);
    }

    /**
     * Gets the non-axiomatic oracle corresponding to a given test from a list
     * of oracles.
     *
     * @param testName a test name
     * @param oracles a list of oracle records
     * @return the oracle record with the given test name. Returns null if no
     * such oracle exists.
     */
    private static List<OracleOutput> getOraclesWithTestName(String testName, List<OracleOutput> oracles) {
        return oracles
                .stream()
                .filter(o -> o.testName().equals(testName) && !o.oracle().isEmpty())
                .toList();
    }

    /**
     * Adds non-axiomatic oracles to test prefixes in a given test file. Each
     * oracle is matched to its corresponding test prefix using the
     * {@link OracleOutput#testName()} field.
     *
     * @param testFile a Java file of test prefixes
     * @param oracles a list of test oracles made by a non-axiomatic tog
     * @param insertAtStatementIdx true if the oracles must be inserted after the given statement indices. If false,
     *                             the oracles are added at the end of the test case
     */
    private static void insertNonAxiomaticOracles(CompilationUnit testFile, List<OracleOutput> oracles, boolean insertAtStatementIdx) {
        if (insertAtStatementIdx) {
            testFile.findAll(MethodDeclaration.class)
                    .forEach(testCase -> {
                        String testName = testCase.getNameAsString();
                        List<OracleOutput> testOracles = getOraclesWithTestName(testName, oracles);
                        BlockStmt testBody = new BlockStmt();
                        int stmtIdx = 0;
                        for (Statement testStatement : testCase.getBody().orElseThrow().getStatements()) {
                            final int currentStmtIdx = stmtIdx;
                            testBody.addStatement(testStatement);
                            List<OracleOutput> stmtOracles = testOracles
                                    .stream()
                                    .filter(o -> Integer.parseInt(o.statementIndex()) == currentStmtIdx)
                                    .toList();
                            List<OracleOutput> exceptionOracles = stmtOracles
                                    .stream()
                                    .filter(OracleOutput::isExceptional)
                                    .toList();
                            List<OracleOutput> assertionOracles = stmtOracles
                                    .stream()
                                    .filter(o -> !o.isExceptional())
                                    .toList();
                            for (OracleOutput exceptionOracle : exceptionOracles) {
                                insertNonAxiomaticException(testBody, exceptionOracle.exception());
                            }
                            for (OracleOutput assertionOracle : assertionOracles) {
                                insertNonAxiomaticAssertion(testBody, assertionOracle.oracle());
                            }
                            stmtIdx++;
                        }
                        testCase.setBody(testBody);
                    });
        } else {
            testFile.findAll(MethodDeclaration.class)
                    .forEach(testCase -> {
                        String testName = testCase.getNameAsString();
                        List<OracleOutput> testOracles = getOraclesWithTestName(testName, oracles);
                        BlockStmt testBody = testCase.getBody().orElseThrow();
                        List<OracleOutput> exceptionOracles = testOracles
                                .stream()
                                .filter(OracleOutput::isExceptional)
                                .toList();
                        List<OracleOutput> assertionOracles = testOracles
                                .stream()
                                .filter(o -> !o.isExceptional())
                                .toList();
                        for (OracleOutput exceptionOracle : exceptionOracles) {
                            insertNonAxiomaticException(testBody, exceptionOracle.exception());
                        }
                        for (OracleOutput assertionOracle : assertionOracles) {
                            insertNonAxiomaticAssertion(testBody, assertionOracle.oracle());
                        }
                    });
        }
    }

    /**
     * Recursively gets all OracleOutputs from all JSON files in a given
     * directory and its subdirectories.
     *
     * @param pathToOracles a directory containing OracleOutput JSON files
     * @return the corresponding OracleOutput objects
     */
    private static List<OracleOutput> getOracleOutputs(Path pathToOracles) {
        List<OracleOutput> oracleOutputs = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(pathToOracles)) {
            walk.forEach(p -> {
                if (p.toString().endsWith("_Oracle.json")) {
                    oracleOutputs.addAll(FileUtils.readJSONList(p, OracleOutput.class));
                }
            });
        } catch (IOException e) {
            throw new Error("Unable to traverse directory " + pathToOracles);
        }
        return oracleOutputs;
    }

    /** A pattern used to find the "[tog]-oracles" segment of a path. */
    private static final Pattern oracleTogPattern = Pattern.compile("\\b[a-zA-Z]+-oracles\\b");

    /**
     * Gets the TOG corresponding to an arbitrary path to generated oracles.
     * This method assumes that the path has the format:
     * "path/to/[tog]-oracles/com/example"
     * and returns the TogType corresponding to the "[tog]".
     *
     * @param pathToOracles a path to oracles generated by a TOG
     * @return the corresponding TOG that generated the oracles
     */
    private static TogType getTogFromOraclePath(Path pathToOracles) {
        Matcher matcher = oracleTogPattern.matcher(pathToOracles.toString());
        if (!matcher.find()) {
            throw new Error(
                    "Poorly formatted path " + pathToOracles + ". " +
                    "Path should include [tog]-oracles"
            );
        }
        String togTestSegment = matcher.group(0);
        TogType tog = TogType.valueOf(togTestSegment.substring(0, togTestSegment.indexOf("-oracles")).toUpperCase());
        if (matcher.find()) {
            throw new Error(
                    "Poorly formatted path " + pathToOracles + ". " +
                    "Path should not include more than one instance of [tog]-oracles"
            );
        }
        return tog;
    }

    /**
     * Sets the SymbolSolver used by the StaticJavaParser for a given
     * project. This method should be called before attempting to resolve any
     * types from the project under analysis
     *
     * @param srcDir the project source directory
     * @param classpath a classpath
     */
    public static void setJavaParser(Path srcDir, String classpath) {
        CombinedTypeSolver typeSolver = new CombinedTypeSolver();
        typeSolver.add(new ReflectionTypeSolver());
        typeSolver.add(new JavaParserTypeSolver(srcDir));
        String[] classpathElements = classpath.split(":");
        for (String classPathElement : classpathElements) {
            if (classPathElement.endsWith(".jar")) {
                try {
                    typeSolver.add(new JarTypeSolver(classPathElement));
                } catch (IOException e) {
                    throw new Error("Unable to resolve jar " + classPathElement, e);
                }
            } else {
                typeSolver.add(new JavaParserTypeSolver(classPathElement));
            }
        }
        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
        StaticJavaParser
                .getParserConfiguration()
                .setSymbolResolver(symbolSolver);
    }

    private static TypeDeclaration<?> getClassUnderTest(CompilationUnit testFile, Path pathToSrc) {
        String packageName = testFile.getPackageDeclaration().orElseThrow().getNameAsString();
        String testName = testFile.getPrimaryTypeName().orElseThrow();
        String simpleClassName = testName.substring(0, testName.length() - "_ESTest".length());
        String fullyQualifiedClassName = packageName + "." + simpleClassName;
        String[] pathSegments = fullyQualifiedClassName.split("[.]");
        String pathString = String.join(FileSystems.getDefault().getSeparator(), pathSegments) + ".java";
        Path pathToClass = pathToSrc.resolve(Paths.get(pathString));
        try {
            CompilationUnit classFile = StaticJavaParser.parse(pathToClass);
            return classFile.getPrimaryType().orElseThrow();
        } catch (IOException e) {
            throw new Error("Unable to parse class file " + pathToClass);
        }
    }

    /**
     * Adds a given collection of oracles to a given collection of test
     * prefixes generated by EvoSuite. The approach for adding oracles varies
     * based on whether the oracles are axiomatic or non-axiomatic. Saves the
     * new test suites to "output/[tog]-tests/..." where [tog] is the
     * corresponding test oracle generator. The TOG is extracted from the
     * {@code pathToOracles} parameter. This method will throw an error if the
     * path does not contain a TOG name (or contains multiple tog names). This
     * method does NOT override the original test prefixes.
     *
     * @param pathToPrefixes the path to the test prefixes
     * @param pathToOracles the path to the TOG oracles
     * @param pathToSrc the path to the project source directory
     * @param classpath the classpath of the project under analysis
     * @param insertAtStatementIdx true if the oracles must be inserted after
     *                             the given statement indices. If false, the
     *                             oracles are added at the end of the test
     *                             case
     */
    public static void insertOracles(
            Path pathToPrefixes,
            Path pathToOracles,
            Path pathToSrc,
            String classpath,
            boolean insertAtStatementIdx
    ) {
        setJavaParser(pathToSrc, classpath);
        List<OracleOutput> oracleOutputs = getOracleOutputs(pathToOracles);
        TogType tog = getTogFromOraclePath(pathToOracles);
        togUnderAnalysis = tog;
        String togName = tog.toString().toLowerCase();
        Path pathToTests = FileUtils.swapParentDirectory(pathToOracles, togName + "-oracles", togName + "-tests");
        FileUtils.copy(pathToPrefixes, pathToTests);
        try (Stream<Path> walk = Files.walk(pathToTests)) {
            walk.forEach(p -> {
                if (!Files.isDirectory(p) && !FileUtils.isScaffolding(p)) {
                    CompilationUnit cu = FileUtils.getCompilationUnit(p);
                    TypeDeclaration<?> classUnderTest = getClassUnderTest(cu, pathToSrc);
                    if (tog.isAxiomatic()) {
                        insertAxiomaticOracles(pathToSrc, cu, classUnderTest, oracleOutputs);
                    } else {
                        insertNonAxiomaticOracles(cu, oracleOutputs, insertAtStatementIdx);
                    }
                    FileUtils.writeString(p, cu.toString());
                }
            });
        } catch (IOException e) {
            throw new Error("Unable to traverse directory " + pathToTests);
        }
    }
}
