import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.Expression;
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
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserMethodDeclaration;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JarTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import data.OracleOutput;
import data.OracleType;
import data.TogType;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
        List<MethodCallExpr> methodCallExprs = statement.findAll(MethodCallExpr.class);
        List<ObjectCreationExpr> objectCreationExprs = statement.findAll(ObjectCreationExpr.class);
        List<VariableDeclarationExpr> variableDeclarationExprs = statement.findAll(VariableDeclarationExpr.class);
        if (!methodCallExprs.isEmpty()) {
            return methodCallExprs.get(0);
        } else if (!objectCreationExprs.isEmpty()) {
            return objectCreationExprs.get(0);
        } else if (!variableDeclarationExprs.isEmpty()) {
            return variableDeclarationExprs.get(0);
        } else {
            return new MarkerAnnotationExpr("UnableToIdentifyOracleExpression");
        }
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

    private static List<String> getParameterTypesFromMethodSignature(String methodSignature) {
        String parameters = methodSignature.substring(methodSignature.indexOf('(') + 1, methodSignature.indexOf(')'));
        if (parameters.length() == 0) {
            return new ArrayList<>();
        }
        return Stream.of(parameters.split(","))
                .map(p -> {
                    String[] paramParts = p.trim().split(" ");
                    StringBuilder paramTypeFqnBuilder = new StringBuilder();
                    for (int i = 0; i < paramParts.length - 1; i++) {
                        paramTypeFqnBuilder
                                .append(paramParts[i].trim())
                                .append(" ");
                    }
                    return paramTypeFqnBuilder.toString().trim();
                })
                .collect(Collectors.toList());
    }

    /**
     * Gets the variable names of all parameters from the method signature.
     *
     * @param methodSignature a method signature
     * @return all variable parameter names in the method signature
     */
    private static List<String> getParameterNames(String methodSignature) {
        String parameters = methodSignature.substring(methodSignature.indexOf('(') + 1, methodSignature.indexOf(')'));
        if (parameters.length() == 0) {
            return new ArrayList<>();
        }
        return Stream.of(parameters.split(","))
                .map(p -> p.trim().split(" ")[1].trim())
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
        List<String> originalNames = getParameterNames(oracleOutput.methodSignature());
        List<String> contextNames = ((NodeWithArguments<?>) oracleExpr).getArguments()
                .stream()
                .map(expr -> {
                    if (expr.isLiteralExpr() || expr.isCastExpr()) {
                        return "(" + expr + ")";
                    } else {
                        return expr.toString();
                    }
                }).toList();
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
        catchBody.addStatement(new ReturnStmt());
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

    private static List<OracleOutput> getRelatedOracles(List<OracleOutput> oracles, CallableDeclaration<?> targetMethod) {
        String targetMethodName = targetMethod.getNameAsString();
        List<String> targetMethodParameters = targetMethod
                .getParameters()
                .stream()
                .map(Parameter::getTypeAsString)
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
                    List<String> oracleMethodParameters = getParameterTypesFromMethodSignature(o.methodSignature())
                            .stream()
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
            CompilationUnit testFile,
            TypeDeclaration<?> classUnderTest,
            List<OracleOutput> oracles
    ) {
        List<MethodDeclaration> tests = testFile.findAll(MethodDeclaration.class);
        for (MethodDeclaration test : tests) {
            NodeList<Statement> oldTestBody = test.getBody().orElseThrow().getStatements();
            NodeList<Statement> newTestBody = new NodeList<>();
            for (Statement testStatement : oldTestBody) {
                // get all oracles related to a method call
                Expression oracleExpr = getOracleExpression(testStatement);
                CallableDeclaration<?> callableDeclaration = resolveDeclaration(classUnderTest, oracleExpr);
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
     */
    private static void insertNonAxiomaticOracles(CompilationUnit testFile, List<OracleOutput> oracles) {
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
     */
    public static void insertOracles(
            Path pathToPrefixes,
            Path pathToOracles,
            Path pathToSrc,
            String classpath
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
                        insertAxiomaticOracles(cu, classUnderTest, oracleOutputs);
                    } else {
                        insertNonAxiomaticOracles(cu, oracleOutputs);
                    }
                    FileUtils.writeString(p, cu.toString());
                }
            });
        } catch (IOException e) {
            throw new Error("Unable to traverse directory " + pathToTests);
        }
    }
}
