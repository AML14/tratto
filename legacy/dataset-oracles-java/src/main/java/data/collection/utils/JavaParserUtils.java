package data.collection.utils;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.body.CallableDeclaration.Signature;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.VoidType;
import com.github.javaparser.resolution.MethodUsage;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedParameterDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.logic.ConfilictingGenericTypesException;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.utils.SymbolSolverCollectionStrategy;
import data.collection.enums.*;
import data.collection.exceptions.*;
import data.collection.models.Project;
import org.eclipse.emf.ecore.EObject;
import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * JavaParserUtils is a class that supports the generation of oracle datapoints, starting from a JDoctor condition,
 * exploiting the functionalities of JavaParser and SymbolSolver to extract information about class, methods,
 * parameters, and attributes.
 *
 * @author Davide Molinelli and Elliott Zackrone
 * @version 1.0
 * @since 2023-04-07
 */
public class JavaParserUtils {
    private JavaParser javaParser;
    private OracleUtils oracleUtils;
    private HashMap<String,List<Quartet<String,String,String,String>>> fieldsQuartet;
    private HashMap<String,List<Quartet<String,String,String,String>>> methodsQuartet;

    /**
     * Generate a new instance of JavaParserUtils.
     * The constructor sets up:
     * <ul>
     *     <li>An instance of JavaParser ({@link #javaParser}) used to generate and manipulate the Abstract Syntax Tree
     *     (AST) of the classes of the project passed to JPUtil (in the format of .jar file).</li>
     *     <li>A SymbolSolver strategy to resolve the type of methods, attributes, and expressions.</li>
     *     <li>An instance of {@link OracleUtils}, a support class to manipulate oracle strings.</li>
     * </ul>
     * 
     * @param projectJarPath The absolute path to the .jar file of a Java project.
     */
    public JavaParserUtils(String projectJarPath) {
        SymbolSolverCollectionStrategy strategy = new SymbolSolverCollectionStrategy();
        strategy.collect(Paths.get(projectJarPath));
        this.javaParser = new JavaParser();
        this.javaParser.getParserConfiguration().setSymbolResolver(strategy.getParserConfiguration().getSymbolResolver().get());
        this.oracleUtils = OracleUtils.getInstance();
    }

    /**
     * The method checks that the attribute ({@code jpField}) satisfies the {@code predicates} passed to the function.
     * For example, a predicate can check if the attribute is static, or final, or private/protected/public.
     * The predicates are combined in {@code &&}, therefore the features of the attribute must satisfy all the predicates
     * in order to have a positive verdict.
     * 
     * @param jpField A JavaParser field {@link FieldDeclaration} representing a statement of a class where one or more 
     *                variable has been defined.
     * @param predicates The list of predicates that the attribute must satisfy.
     * @return The verdict of the checking: {@code true} if the attribute satisfy all the conditions. {@code false}
     * otherwise.
     */
    public static boolean checkFieldDeclarationFeaturesCondition(
            FieldDeclaration jpField,
            List<Predicate<FieldDeclaration>> predicates
    ) {
        Predicate<FieldDeclaration> combinedPredicate = p -> true;
        for (Predicate<FieldDeclaration> predicate : predicates) {
            combinedPredicate = combinedPredicate.and(predicate);
        }
        return combinedPredicate.test(jpField);
    }

    /**
     * The method checks that the attribute ({@code jpResolvedField}) satisfies the {@code predicates} passed to the function.
     * For example, a predicate can check if the attribute is static, or final, or private/protected/public.
     * The predicates are combined in {@code &&}, therefore the features of the attribute must satisfy all the predicates
     * in order to have a positive verdict.
     * 
     * @param jpResolvedField The attribute to analyze, an instance of {@link ResolvedFieldDeclaration} from SymbolSolver.
     * @param predicates The list of predicates that the attribute must satisfy.
     * @return The verdict of the checking: {@code true} if the attribute satisfy all the conditions. {@code false}
     * otherwise.
     */
    public static boolean checkFieldDeclarationFeaturesCondition(
            ResolvedFieldDeclaration jpResolvedField,
            List<Predicate<ResolvedFieldDeclaration>> predicates
    ) {
        Predicate<ResolvedFieldDeclaration> combinedPredicate = p -> true;
        for (Predicate<ResolvedFieldDeclaration> predicate : predicates) {
            combinedPredicate = combinedPredicate.and(predicate);
        }
        return combinedPredicate.test(jpResolvedField);
    }

    /**
     * The method checks that the method passed to the function ({@code jpMethod}) satisfies the {@code predicates}.
     * For example, a predicate can check if the method is static, or final, or private/protected/public.
     * The predicates are combined in {@code &&}, therefore the features of the method must satisfy all the predicates
     * in order to have a positive verdict.
     * 
     * @param jpMethod The method to analyze, an instance of {@link MethodDeclaration} from JavaParser AST.
     * @param predicates The list of predicates that the method must satisfy.
     * @return The verdict of the checking: {@code true} if the method satisfy all the conditions. {@code false}
     * otherwise.
     */
    public static boolean checkMethodDeclarationFeaturesCondition(
            MethodDeclaration jpMethod,
            List<Predicate<MethodDeclaration>> predicates
    ) {
        Predicate<MethodDeclaration> combinedPredicate = p -> true;
        for (Predicate<MethodDeclaration> predicate : predicates) {
            combinedPredicate = combinedPredicate.and(predicate);
        }
        return combinedPredicate.test(jpMethod);
    }

    /**
     * The method checks that the method passed to the function ({@code jpMethod}) satisfies the {@code predicates}.
     * For example, a predicate can check if the method is static, or final, or private/protected/public.
     * The predicates are combined in {@code &&}, therefore the features of the method must satisfy all the predicates
     * in order to have a positive verdict.
     * 
     * @param jpMethod The method to analyze, an instance of {@link MethodUsage} from SymbolSolver.
     * @param predicates The list of predicates that the method must satisfy.
     * @return The verdict of the checking: {@code true} if the method satisfy all the conditions. {@code false}
     * otherwise.
     */
    public static boolean checkMethodDeclarationFeaturesCondition(
            MethodUsage jpMethod,
            List<Predicate<MethodUsage>> predicates
    ) {
        Predicate<MethodUsage> combinedPredicate = p -> true;
        for (Predicate<MethodUsage> predicate : predicates) {
            combinedPredicate = combinedPredicate.and(predicate);
        }
        return combinedPredicate.test(jpMethod);
    }

    /**
     * The method checks that the method passed to the function ({@code jpMethod}) satisfies the {@code predicates}.
     * For example, a predicate can check if the method is static, or final, or private/protected/public.
     * The predicates are combined in {@code &&}, therefore the features of the method must satisfy all the predicates
     * in order to have a positive verdict.
     * 
     * @param jpMethod The method to analyze, an instance of {@link ResolvedMethodDeclaration} from SymbolSolver.
     * @param predicates The list of predicates that the method must satisfy.
     * @return The verdict of the checking: {@code true} if the method satisfy all the conditions. {@code false}
     * otherwise.
     */
    public static boolean checkMethodDeclarationFeaturesCondition(
            ResolvedMethodDeclaration jpMethod,
            List<Predicate<ResolvedMethodDeclaration>> predicates
    ) {
        Predicate<ResolvedMethodDeclaration> combinedPredicate = p -> true;
        for (Predicate<ResolvedMethodDeclaration> predicate : predicates) {
            combinedPredicate = combinedPredicate.and(predicate);
        }
        return combinedPredicate.test(jpMethod);
    }

    /**
     * The method checks that, given a JavaParser compilation unit {@code cu}, a package name {@code packageName},
     * and a class name {@code className}, the compilation unit contains the correct {@link PackageDeclaration} and
     * the correct class {@link TypeDeclaration}
     * 
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @param className The name of the class that must be defined within the compilation unit
     * @param packageName The package name of the class, that must correspond to the one of the compilation unit
     * @throws PrimaryTypeNotFoundException If the compilation does not have a primary class {@link TypeDeclaration} (in
     * other words, the compilation unit does not have a class defined within it).
     * @throws PackageDeclarationNotFoundException If the compilation does not have a package declaration
     * {@link PackageDeclaration}
     */
    public void checkCompilationUnitConsistency(
            CompilationUnit cu,
            String className,
            String packageName
    ) throws PrimaryTypeNotFoundException, PackageDeclarationNotFoundException {
        // Get the primary type from the compilation unit: it must represent the primary class of the Java file analyzed
        Optional<TypeDeclaration<?>> primaryType = cu.getPrimaryType();
        // Check that the primary type is not empty
        if (primaryType.isEmpty()) {
            // Raise an exception if the primary type is null
            String errMsg = String.format(
                    "The compilation unit does not have a primary type for the class %s - package %s",
                    className,
                    packageName
            );
            throw new PrimaryTypeNotFoundException(errMsg);
        }
        // Get the package declaration
        Optional<PackageDeclaration> jpPackage = cu.getPackageDeclaration();
        // Check that the package declaration is not empty
        if (jpPackage.isEmpty()) {
            // Raise an exception if the package declaration is empty
            String errMsg = String.format(
                    "The JavaParser package declaration of the class %s - package %s, is empty",
                    className,
                    packageName
            );
            throw new PrimaryTypeNotFoundException(errMsg);
        }
        // Check that the classname matches the primary type: the primary type must match the class of the condition analyzed.
        if (!className.equals(primaryType.get().getNameAsString())){
            // Raise an exception if the package declaration is empty
            String errMsg = String.format(
                    "The JavaParser package declaration does not match the class of JDoctor condition, for the class %s - package %s",
                    className,
                    packageName
            );
            throw new PackageDeclarationNotFoundException(errMsg);
        }
        // Check that the package name of the condition matches the package declaration of the
        if (!packageName.equals(jpPackage.get().getNameAsString())){
            // Raise an exception if the package declaration is empty
            String errMsg = String.format(
                    "The JavaParser package declaration of the class %s - package %s, is empty",
                    className,
                    packageName
            );
            throw new PackageDeclarationNotFoundException(errMsg);
        }
    }

    /**
     * The method converts the list of JDoctor type names {@code jDoctorTypeNames} into a list of JavaParser type names.
     * For example, the JDoctor type name <span>[D</span> represents a list of doubles, and the corresponding type name in
     * JavaParser is <span>double[]</span>. The method apply these conversions from JDoctor type names to JavaParser type
     * names.
     * 
     * @param jDoctorTypeNames The list of JDoctor type names to convert
     * @return The list of the corresponding JavaParser type names
     */
    public static List<String> convertJDoctorConditionTypeNames2JavaParserTypeNames(
            List<String> jDoctorTypeNames
    ) {
        // Convert JDcotor type names to JavaParser type names
        List<String> jpTypeNames = jDoctorTypeNames
                .stream()
                .map(p -> TypeUtils.convertConditionParameterType(p))
                .toList();
        // Return the list of corresponding JavaParser type names
        return jpTypeNames;
    }

    /**
     * The method tries to extract the JavadocComment of a Java method or class, represented by a JavaParser {@link Node},
     * through heuristics. This method can be helpful, whenever JavaParser is not able to automatically extract the 
     * Javadoc comment with its built-in methods.
     * 
     * @param jpNode a JavaParser node representing a Java method or class
     * @return a {@link String} representing the Javadoc comment associated to the JavaParser {@link Node} passed to the 
     * function. The string is empty if the Javadoc comment is not present or is not found.
     */
    public static String extractJavadocCommentByHeuristics(BodyDeclaration jpNode) {
        // Try to extract manually the Javadoc, if present
        String input = jpNode.toString();
        // Search a content within /** and */ symbol
        Pattern pattern = Pattern.compile("/\\*\\*(.*?)\\*/", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(input);
        // Check if there is a matching
        if (matcher.find()) {
            String content = matcher.group(1);
            // Return the content
            return Javadoc.METHOD_PREFIX.getValue() + content + Javadoc.METHOD_SUFFIX.getValue();
        }
        // Otherwise, return an empty Javadoc comment
        return "";
    }

    /**
     * The method returns the list of all the classes defined within a compilation unit {@code cu}. It means both the 
     * primary class and the inner/outer classes defined within the compilation unit. Each class can be an instance of:
     * <ul>
     *     <li>A standard class or interface {@link ClassOrInterfaceDeclaration}</li>
     *     <li>An enum class {@link EnumDeclaration}</li>
     *     <li>An annotation class {@link AnnotationDeclaration}</li>
     *     <li>A record class {@link RecordDeclaration}</li>
     * </ul>
     * All the classes extend from the same base class, therefore the list returned by the method is of type {@link TypeDeclaration}.
     * 
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @return The list of classes of type {@link TypeDeclaration} defined within the compilation unit
     * @throws PrimaryTypeNotFoundException If the compilation does not have a primary class {@link TypeDeclaration} (in
     * other words, the compilation unit does not have a class defined within it).
     */
    public static List<TypeDeclaration<?>> findAllTypeDeclarationsFromCompilationUnit(
            CompilationUnit cu
    ) throws PrimaryTypeNotFoundException {
        // Get the primary class of the JavaParser compilation unit: it must correspond to the class
        // of the condition analyzed
        TypeDeclaration<?> jpPrimaryClass = getPrimaryClassFromCompilationUnit(cu);
        // Get the other types defined within the compilation unit
        List<TypeDeclaration<?>> jpClasses = cu.getTypes();
        // Add the primary type as the first element of the list. The algorithm will search the method within the
        // primary type first. Then it will search within the other types, in case the method is not defined in
        // the primary type.
        jpClasses.add(0, jpPrimaryClass);
        // Return the collection of class declarations found within the compilation unit
        return jpClasses;
    }

    /**
     * The method converts a JavaParser field {@link FieldDeclaration} into a list of quartets of strings
     * {@link Quartet<String,String,String,String>}. Indeed, each JavaParser field corresponds to a statement where one
     * or more attributes (variables) are defined. For example: {@code public static int x = 1,y = 2;} is a field where
     * {@code x} and {@code y} are the attributes defined and initialized. The method generates a quartet of strings,
     * for each attribute. Each quartet has the same structure (each string represents an information about the field,
     * and the order of the strings is important):
     * <ol>
     *     <li>attribute name</li>
     *     <li>package name</li>
     *     <li>class name</li>
     *     <li>attribute signature</li>
     * </ol>
     * Considering the previous example, given the JavaParser field declaration corresponding to the statement
     * {@code public static int x = 1,y = 2;}, the method will produce the list of quartets:
     * {@code [
     *      ["x", "package_name_of_field", "class_name_of_field", "public static int x = 1;"],
     *      ["y", "package_name_of_field", "class_name_of_field", "public static int y = 2;"]
     * ]}
     * where
     * <ul>
     *     <li>*package_name_of_field* is the package name of the class where the field is declared</li>
     *     <li>*class_name_of_field* is the class name of the class where the field is declared</li>
     * </ul>
     * The method returns the list of collected quartets.
     * 
     * @param jpField A JavaParser field {@link FieldDeclaration} representing a statement of a class where one or more 
     *                variable has been defined.
     * @return The list of quartet strings {@link Quartet<String,String,String,String>}, describing each variable
     * declared (and optionally initialized) within the JavaParser field {@link FieldDeclaration}
     */
    public static List<Quartet<String, String, String, String>> convertFieldDeclaration2AttributeQuartets(
            FieldDeclaration jpField
    ) {
        // Define the list of quartets of type [attributeName, packageName, className, attributeSignature]
        List<Quartet<String, String, String, String>> attributeQuartetList = new ArrayList<>();
        try {
            // Get the compilation unit, containing the class where the field is defined
            Optional<CompilationUnit> jpFieldCU = getCompilationUnitFromBodyDeclaration(jpField);
            // Check that the compilation unit is present
            if (jpFieldCU.isPresent()) {
                // Get the class where the field has been defined (it could be the same of the current
                // JavaParser class currently analyzed, or a super class or an inner class
                TypeDeclaration<?> jpFieldClass = getTypeDeclarationFromFieldDeclaration(jpField);
                // Get the package of the class where the field has been defined (it could be different
                // from the one of the JavaParser class currently analyzed,because it could be
                // the one of a superclass (in case of the inner class the package would be the same)
                PackageDeclaration jpFieldPackage = getPackageDeclarationFromCompilationUnit(jpFieldCU.get());
                // Get all the variables defined in a field (es. public static int x = 1,y = 2; --> variables are x and y)
                List<Pair<VariableDeclarator, String>> variablePairList = getAllVariablesAndSignaturesFromFieldDeclaration(jpField);
                // Add the variables to the quarter list
                for (Pair<VariableDeclarator, String> variablePair: variablePairList) {
                    // Get variable
                    VariableDeclarator variable = variablePair.getValue0();
                    // Get variable signature
                    String variableSignature = variablePair.getValue1();
                    // Get the name of the class where the field has been generated
                    String fieldClassName = jpFieldClass.getNameAsString();
                    // Get the package name of the class where the field has been generated
                    String fieldPackageName = jpFieldPackage.getNameAsString();
                    // Generate the quartet from the variable information
                    attributeQuartetList.add(new Quartet<>(
                            variable.getNameAsString(),
                            fieldPackageName,
                            fieldClassName,
                            variableSignature
                    ));
                }
            } else {
                String errMsg = String.format(
                        "Compilation unit not found for field %s. Unable to generate quartet list from field declaration.",
                        jpField.toString()
                );
                System.err.println(errMsg);
            }
        } catch (JPClassNotFoundException e) {
            e.printStackTrace();
        } catch (PackageDeclarationNotFoundException e) {
            e.printStackTrace();
        }
        return attributeQuartetList;
    }

    /**
     * The method converts a SymbolSolver resolved field {@link ResolvedFieldDeclaration} into a list of quartets of strings
     * {@link Quartet<String,String,String,String>}. Indeed, each SymbolSolver resolved field corresponds to a statement where one
     * or more attributes (variables) are defined. For example: {@code public static int x = 1,y = 2;} is a field where {@code x} and
     * {@code y} are the attributes defined and initialized. The method generates a quartet of strings, for each attribute.
     * Each quartet has the same structure (each string represents an information about the field, and the order of the
     * strings is important):
     * <ol>
     *     <li>attribute name</li>
     *     <li>package name</li>
     *     <li>class name</li>
     *     <li>attribute signature</li>
     * </ol>
     * Considering the previous example, given the SymbolSolver resolved field declaration corresponding to the statement
     * {@code public static int x = 1,y = 2;}, the method will produce the list of quartets:
     * {@code [
     *      ["x", "package_name_of_field", "class_name_of_field", "public static int x = 1;"],
     *      ["y", "package_name_of_field", "class_name_of_field", "public static int y = 2;"]
     * ]}
     * where
     * <ul>
     *     <li>*package_name_of_field* is the package name of the class where the field is declared</li>
     *     <li>*class_name_of_field* is the class name of the class where the field is declared</li>
     * </ul>
     *
     * @param jpResolvedFieldDeclaration The SymbolSolver resolved field {@link ResolvedFieldDeclaration}
     * @return The list of quartet strings {@link Quartet<String,String,String,String>}, describing each variable
     * declared (and optionally initialized) within the SymbolSovler resolved field {@link ResolvedFieldDeclaration}
     */
    public static Quartet<String, String, String, String> convertResolvedFieldDeclaration2AttribtueQuartet(
            ResolvedFieldDeclaration jpResolvedFieldDeclaration
    ) {
        // Get the class where the field has been defined
        String className = jpResolvedFieldDeclaration.declaringType().getClassName();
        // Get the package of the class where the field has been defined
        String packageName = jpResolvedFieldDeclaration.declaringType().getPackageName();
        // Get field name
        String fieldName = jpResolvedFieldDeclaration.getName();
        // Get signature
        String signature = getSignatureFromResolvedFieldDeclaration(jpResolvedFieldDeclaration);
        // Generate quartet
        Quartet<String, String, String, String> quartet = new Quartet<>(
            fieldName,
            packageName,
            className,
            signature
        );
        // Return quartet
        return quartet;
    }

    /**
     * The method converts a JavaParser method {@link MethodDeclaration} into a quartet of strings
     * {@link Quartet<String,String,String,String>}. Indeed, each JavaParser method corresponds to a block of code where
     * a method is defined. For example: {@code public int methodName(int param1, int param2) { [method code] };} 
     * is a block of code that defines the method *methodName*. The method generates a quartet of strings, for *methodName*.
     * The quartet has the following structure (each string represents an information about the method, and the order of the
     * strings is important):
     * <ol>
     *     <li>method name</li>
     *     <li>package name</li>
     *     <li>class name</li>
     *     <li>method signature</li>
     * </ol>
     * Considering the previous example, given the JavaParser method declaration corresponding to the block of code
     * {@code public int methodName(int param1, int param2) { [method code] };}, the method will produce the quartet:
     * {@code ["methodName", "package_name_of_method", "class_name_of_method", "public int methodName(int param1, int param2);"]}
     * where
     * <ul>
     *     <li>*package_name_of_method* is the package name of the class where the method is declared</li>
     *     <li>*class_name_of_method* is the class name of the class where the method is declared</li>
     * </ul>
     * The method returns the quartet of string produced.
     *
     * @param jpMethod The JavaParser method {@link MethodDeclaration}
     * @return The quartet of strings {@link Quartet<String,String,String,String>}, describing the non-private, non-static,
     * non-void method declared
     * within the JavaParser method declaration {@link MethodDeclaration}
     */
    public static Optional<Quartet<String, String, String, String>> convertMethodDeclaration2MethodQuartet(
            MethodDeclaration jpMethod
    ) {
        try {
            // Get the compilation unit, containing the class where the method is defined
            Optional<CompilationUnit> jpMethodCU = getCompilationUnitFromBodyDeclaration(jpMethod);
            if (jpMethodCU.isPresent()) {
                // Get the package of the class where the method has been defined (it could be different
                // from the one of the JavaParser class currently analyzed,because it could be
                // the one of a superclass (in case of the inner class the package would be the same)
                PackageDeclaration jpMethodPackage = getPackageDeclarationFromCompilationUnit(jpMethodCU.get());
                // Get the class where the method is defined
                TypeDeclaration<?> jpClass = getJavaTypeDeclarationFromClassDeclaration(jpMethodCU.get(), jpMethod);
                // Get the class name where the method is defined
                String className = jpClass.getNameAsString();
                // Generate the quartet and return
                Quartet<String, String, String, String> quartet = new Quartet<>(
                        jpMethod.getNameAsString(),
                        jpMethodPackage.getNameAsString(),
                        className,
                        getSignatureFromMethodDeclaration(jpMethod)
                );
                return Optional.of(quartet);
            } else {
                String errMsg = String.format(
                        "Compilation unit not found for method %s. Unable to generate quartet.",
                        jpMethod.toString()
                );
                System.err.println(errMsg);
            }
        } catch(PackageDeclarationNotFoundException | JPClassNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * The method converts a SymbolSolver method usage {@link MethodUsage} into a quartet of string 
     * {@link Quartet<String,String,String,String>}. Indeed, each SymbolSolver method usage corresponds to a block of code 
     * where a method is defined. For example: {@code public int methodName(int param1, int param2) { [method code] };}
     * is a block of code that defines the method *methodName*. The method generates a quartet of strings, for *methodName*.
     * The quartet has the following structure (each string represents an information about the method, and the order of the
     * strings is important):
     * <ol>
     *     <li>method name</li>
     *     <li>package name</li>
     *     <li>class name</li>
     *     <li>method signature</li>
     * </ol>
     * Considering the previous example, given the SymbolSolver method usage corresponding to the block of code
     * {@code public int methodName(int param1, int param2) { [method code] };}, the method will produce the quartet:
     * {@code ["methodName", "package_name_of_method", "class_name_of_method", "public int methodName(int param1, int param2);"]}
     * where
     * <ul>
     *     <li>*package_name_of_method* is the package name of the class where the method is declared</li>
     *     <li>*class_name_of_method* is the class name of the class where the method is declared</li>
     * </ul>
     *
     * @param jpMethodUsage The SymbolSolver method usage {@link MethodUsage}
     * @return The quartet of strings {@link Quartet<String,String,String,String>}, describing the method declared
     * within the SymbolSolver method usage {@link MethodDeclaration}
     */
    public static Quartet<String, String, String, String> convertMethodUsage2MethodQuartet(
            MethodUsage jpMethodUsage
    ) {
        String methodName = jpMethodUsage.getName();
        String className = jpMethodUsage.declaringType().getClassName();
        String packageName = jpMethodUsage.declaringType().getPackageName();
        String methodSignature = getSignatureFromMethodUsage(jpMethodUsage);
        // Generate the quartet and return
        Quartet<String, String, String, String> quartet = new Quartet<>(
                methodName,
                packageName,
                className,
                methodSignature
        );
        return quartet;
    }

    /**
     * The method extracts the type name {@link String} from a whole qualified name {@link String}. A qualified name is
     * a path (like the one of a package), which terminates with the name of the class. For example:
     * {@code "org.com.java.path.Class.InnerClass"}. The method extract the class name (the type name) from the whole
     * qualified name. In the given example, the method will return the string {@code "Class.InnerClass"}. Generally
     * speaking, the method, given a string in input, will analyze it from the end of the string towards the beginning,
     * and it will return the maximum substring where each "." is followed by an upper case.
     * Another example: given the string {@code "name1.Name2.name3.Name4.name5.name6.Name7.Name8.name9.Name10.Name11.Name12.Name13"}
     * will return {Name10.Name11.Name12.Name13"} because {@code "name9"} is the first term (from the end of the string)
     * that starts with a lower case after a ".". This is a heuristics used to extract the type name from a qualified name.
     *
     * @param qualifiedPath A path (in the form of a package name), which terminates with the name of the class
     * @return The maximum substring where each "." is followed by an upper case character (it corresponds to the type
     * name from a qualified name)
     */
    public static String extractTypeNameFromQualifiedName(String qualifiedPath) {
        String typeName = qualifiedPath;
        int firstDotOccurrence = qualifiedPath.indexOf('.');
        if (firstDotOccurrence != -1) {
            for (int i = qualifiedPath.length()-1; i >= 0; i--) {
                if (qualifiedPath.charAt(i) == '.') {
                    if (i+1 < qualifiedPath.length() && Character.isUpperCase(qualifiedPath.charAt(i+1))) {
                        typeName = qualifiedPath.substring(i+1);
                    } else {
                        break;
                    }
                }
                if (i == firstDotOccurrence) {
                    if (Character.isUpperCase(qualifiedPath.charAt(0))) {
                        return qualifiedPath;
                    }
                    return typeName;
                }
            }
        }
        return typeName;
    }

    /**
     * The method finds all the numeric values present within a Javadoc comment, and it returns them as a list of pairs
     * of strings {@link Pair<String,String>}. The first element of the pair is the numeric value, while the second
     * element of the string identifies the type of the numeric value {@link JavadocValueType} (integer or real).
     *
     * @param javadocComment The javadoc comment, in the form of a string {@link String}
     * @return A list of pairs of strings {@link Pair<String,String>}, where the first element of each pair is the
     * numeric value, while the second element is the type of the numeric value {@link JavadocValueType} (integer or
     * real).
     */
    public static List<Pair<String, String>> findAllNumericValuesInJavadocComment(
            String javadocComment
    ) {
        // Define regex pattern that matches integer and real values within a string
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?"); // matches integers and real numbers
        // Instantiate matcher
        Matcher matcher = pattern.matcher(javadocComment);
        // Define collection of pairs of numeric values [numericType, numericValue]
        List<Pair<String, String>> numericValues = new ArrayList<>();
        // Collect the matches
        while (matcher.find()) {
            // Extract the value
            String match = matcher.group();
            // Check if the value is integer or real
            if (match.contains(".")) {
                // The number is real
                try {
                    Float realValue = Float.parseFloat(match);
                    numericValues.add(new Pair<>(realValue.toString(), JavadocValueType.REAL.getValue()));
                } catch (Exception e) {
                    String errMsg = String.format("Number exceed maximum real value: %s", match);
                    System.err.println(errMsg);
                }
            } else {
                // The number is integer
                try {
                    Long longIntValue = Long.parseLong(match);
                    numericValues.add(new Pair<>(longIntValue.toString(), JavadocValueType.INTEGER.getValue()));
                } catch (NumberFormatException e) {
                    String errMsg = String.format("Number exceed maximum integer value: %s", match);
                    System.err.println(errMsg);
                }
            }
        }
        // Return the list of the collected integer values
        return numericValues;
    }

    /**
     * The method finds all the string values present within a Javadoc comment, and it returns them as a list of pairs
     * of strings {@link Pair<String,String>}. The first element of the pair is the string value, while the second
     * element of the pair identifies the type of the pair {@link JavadocValueType} (always a string).
     *
     * @param javadocComment The javadoc comment, in the form of a string {@link String}
     * @return A list of pairs of strings {@link Pair<String,String>}, where the first element of each pair is the
     * string value found within the Javadoc comment, while the second element is the type of the value
     * {@link JavadocValueType} (always "string").
     */
    public static List<Pair<String, String>> findAllStringValuesInJavadocComment(String javadocComment) {
        // Define regex pattern that matches a string value within a string
        Pattern pattern = Pattern.compile("\"([^\"]*)\"");
        // Instantiate matcher
        Matcher matcher = pattern.matcher(javadocComment);
        // Define collection of pairs of string values [stringValue, "String"]
        List<Pair<String, String>> stringValues = new ArrayList<>();
        // Collect the matches
        while (matcher.find()) {
            // Extract the value
            String match = matcher.group(1);
            stringValues.add(new Pair<>(match, "String"));
        }
        // Return the list of the collected string values
        return stringValues;
    }

    /**
     * The method finds the class name and the package name of all the arguments of a method, and it returns the as a
     * list of pairs of strings {@link Pair<String,String>}. The first element of the pair is the class name, while
     * the second element of the pair represents the package name of the class.
     *
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @param methodName The name of the method from which to extract the class name and package name of its arguments
     * @param jDoctorConditionParamTypeNames The list of strings, representing the type names of the arguments of the method
     * @return The list of pairs of strings {@link Pair<String,String>}, representing the class name and package name
     * of the arguments of the method analyzed.
     * @throws PrimaryTypeNotFoundException If the compilation does not have a primary class {@link TypeDeclaration} (in
     * other words, the compilation unit does not have a class defined within it).
     * @throws JPMethodNotFound If the method analyzed is not found within the compilation unit passed to the function
     */
    public static List<Pair<String, String>> findClassNameAndPackageNameFromMethodArguments(
            CompilationUnit cu,
            String methodName,
            List<String> jDoctorConditionParamTypeNames
    ) throws PrimaryTypeNotFoundException, JPMethodNotFound {
        // Convert condition parameters types strings in the format of the JavaParser types strings
        jDoctorConditionParamTypeNames = convertJDoctorConditionTypeNames2JavaParserTypeNames(jDoctorConditionParamTypeNames);
        // Get the primary class of the JavaParser compilation unit: it must correspond to the class
        // of the condition analyzed
        TypeDeclaration<?> jpClass = getPrimaryClassFromCompilationUnit(cu);
        // Define the list of pairs [argumentClassName, argumentClassPackage]
        List<Pair<String, String>> argumentsPackagesList = new ArrayList<>();
        // Get the JavaParser method declaration corresponding to the condition method
        MethodDeclaration jpMethod = getMethodDeclaration(cu, methodName, jDoctorConditionParamTypeNames);
        // Extract the list of JavaParser parameters from the JavaParser method
        List<Parameter> jpMethodParams = jpMethod.getParameters();
        // Iterate over each parameter to extract the type of the parameter
        for(Parameter jpParam : jpMethodParams) {
            // Get the JavaParser parameter type
            Type jpParamType = jpParam.getType();
            // Get the name of JavaParser parameter type
            String jpTypeName = TypeUtils.getJPTypeName(jpClass, jpMethod, jpParam);
            // Get the package name of the JavaParser parameter type
            String jpParamPackageName = jpParamType.toString();
            // Add the type name and the package name of the JavaParser parameter to the collection
            argumentsPackagesList.add(new Pair<>(jpTypeName, jpParamPackageName));
        }
        // Return the list of the processed method arguments
        return argumentsPackagesList;
    }

    /**
     * The method converts a JavaParser field {@link FieldDeclaration} into a list of pairs of strings
     * {@link Pair<String,String>}. Indeed, each JavaParser field corresponds to a statement where one
     * or more attributes (variables) are defined. For example: {@code public static int x = 1,y = 2;} is a field where
     * {@code x} and {@code y} are the attributes defined and initialized. The method generates a pair of strings, for
     * each attribute. Each pair has the same structure (each string represents an information about the field, and the
     * order of the strings is important):
     * <ol>
     *     <li>attribute name</li>
     *     <li>attribute signature</li>
     * </ol>
     * Considering the previous example, given the JavaParser field declaration corresponding to the statement
     * {@code public static int x = 1,y = 2;}, the method will produce the list of quartets:
     * {@code [
     *      ["x", "public static int x = 1;"],
     *      ["y", "public static int y = 2;"]
     * ]}
     * The method returns the list of pairs collected.
     *
     * @param jpField A JavaParser field {@link FieldDeclaration} representing a statement of a class where one or more
     *                variable has been defined.
     * @return The list of pair strings {@link Pair<String,String>}, describing each variable declared (and optionally
     * initialized) within the JavaParser field {@link FieldDeclaration}.
     */
    public static List<Pair<VariableDeclarator,String>> getAllVariablesAndSignaturesFromFieldDeclaration(
            FieldDeclaration jpField
    ) {
        List<Pair<VariableDeclarator,String>> variablePairList = new ArrayList<>();
        // Iterate over each variable of the field (es. static int x = 1,y = 2; --> variables are x and y)
        for (VariableDeclarator jpVariable : jpField.getVariables()) {
            // Build the signature for each variable ([int x = 1;], [int y = 2;])
            // alternatively, argumentSignature = field.toString(); will give the same signature
            // for each variable of the field (static int x = 1,y = 2;);
            variablePairList.add(new Pair<>(jpVariable, getSignatureFromVariableDeclarator(jpField, jpVariable)));
        }
        return variablePairList;
    }

    /**
     * The method tries to find all the fields declared by a class or inherited from its superclasses, given a SymbolSolver
     * type {@link Type}. The method returns the fields in the form of a pair of lists of JavaParser field declarations
     * {@link FieldDeclaration} and resolved field declarations {@link ResolvedFieldDeclaration}. Given the type, the
     * method searches the corresponding JavaParser AST declaration node. If found, the method returns all the fields in
     * the form of field declarations {@link FieldDeclaration} (in this case the second element of the pair is an empty list).
     * Otherwise, if the class declaration is not found, the method get all the SymbolSolver resolved field declarations
     * {@link ResolvedFieldDeclaration} from the SymbolSolver type {@link ResolvedType}, and it tries to find the
     * corresponding JavaParser field declaration {@link FieldDeclaration}, from each SymbolSolver resolved field
     * declaration {@link ResolvedFieldDeclaration}. The fields successfully converted are returned in the form of
     * JavaParser field declarations {@link FieldDeclaration}, while the others are returned in the form of SymbolSolver
     * resolved field declarations {@link ResolvedFieldDeclaration}.
     *
     * @param project The project under analysis.
     * @param jpType The SymbolSolver type {@link Type} from which to extract the fields.
     * @return A pair of lists of field declarations {@link FieldDeclaration} and resolved field declarations {@link
     * ResolvedFieldDeclaration}, representing the fields declared by the class or inherited from its superclasses. The
     * method returns a pair of empty lists if the type cannot be resolved.
     */
    public Pair<List<FieldDeclaration>,List<ResolvedFieldDeclaration>> getFieldsFromType(
            Project project,
            Type jpType
    ) {
        // Get the SymbolSolver ResolvedType
        ResolvedType jpResolvedType = jpType.resolve();
        return getFieldsFromType(project, jpResolvedType);
    }

    /**
     * The method tries to find all the fields declared by a class or inherited from its superclasses, given a SymbolSolver
     * resolved type {@link ResolvedType}. The method returns the fields in the form of a pair of lists of JavaParser
     * field declarations {@link FieldDeclaration} and resolved field declarations {@link ResolvedFieldDeclaration}.
     * Given the resolved type, the method searches the corresponding JavaParser AST declaration node. If found, the
     * method returns all the fields in the form of field declarations {@link FieldDeclaration} (in this case the second
     * element of the pair is an empty list). Otherwise, if the class declaration is not found, the method get all the
     * SymbolSolver resolved field declarations {@link ResolvedFieldDeclaration} from the SymbolSolver resolved type
     * {@link ResolvedType}, and it tries to find the corresponding JavaParser field declaration {@link FieldDeclaration},
     * from each SymbolSolver resolved field declaration {@link ResolvedFieldDeclaration}. The fields successfully
     * converted are returned in the form of JavaParser field declarations {@link FieldDeclaration}, while the others
     * are returned in the form of SymbolSolver resolved field declarations {@link ResolvedFieldDeclaration}.
     *
     * @param project The project under analysis.
     * @param jpResolvedType The SymbolSolver resolved type {@link ResolvedType} from which to extract the fields.
     * @return A pair of lists of field declarations {@link FieldDeclaration} and resolved field declarations {@link
     * ResolvedFieldDeclaration}, representing the fields declared by the class or inherited from its superclasses. The
     * method returns a pair of empty lists if the resolved type cannot be analyzed.
     */
    public Pair<List<FieldDeclaration>,List<ResolvedFieldDeclaration>> getFieldsFromType(
            Project project,
            ResolvedType jpResolvedType
    ) {
        // Check that the type is not primitive
        if (jpResolvedType.isReferenceType()) {
            // Get the SymbolSolver reference declaration
            Optional<ResolvedReferenceTypeDeclaration> jpResolvedDeclaration = jpResolvedType.asReferenceType().getTypeDeclaration();
            // Define the list of method usage not converted
            List<ResolvedFieldDeclaration> jpResolvedFieldNotFound = new ArrayList<>();
            // Check that the reference declaration exists
            if (jpResolvedDeclaration.isPresent()) {
                // Try to get the corresponding JavaParser node declaration
                Optional<Node> jpNode = jpResolvedDeclaration.get().toAst();
                // Check if the corresponding JavaParser node has been found, and it is a class
                if (jpNode.isPresent() && jpNode.get() instanceof TypeDeclaration<?>) {
                    // Get all the fields declared within the class and inherited from its superclass
                    // in the form of JavaParser field declarations
                    List<FieldDeclaration> jpFields = ((TypeDeclaration<?>) jpNode.get()).getFields();
                    // Return the pair (all the fields are in the form of field declarations)
                    return new Pair<>(jpFields, new ArrayList<>());
                }
                // Get all the fields declared within the class and inherited from its superclass, in the form of
                // SymbolSolver resolved field declarations
                List<ResolvedFieldDeclaration> jpResolvedFieldDeclarationList = jpResolvedDeclaration.get().getAllFields();
                // Try to convert has many as possible SymbolSolver resolved field declarations into JavaParser field
                // declarations
                List<FieldDeclaration> jpFieldDeclarationList = jpResolvedFieldDeclarationList
                        .stream()
                        .map(m -> getFieldDeclarationFromResolvedFieldDeclaration(project, m))
                        .flatMap(Optional::stream)
                        .toList();
                // Check if all the resolved field declarations have been converted
                if (!(jpFieldDeclarationList.size() == jpResolvedFieldDeclarationList.size())) {
                    // Notify the impossibility to convert {n} SymbolSolver resolved field declarations into
                    // JavaParser field declarations
                    String errMsg = String.format(
                            "Unable to convert %s ResolvedFieldDeclaration instances to FieldDeclaration instances.",
                            Integer.valueOf(jpResolvedFieldDeclarationList.size() - jpFieldDeclarationList.size()).toString()
                    );
                    //System.err.println(errMsg);
                    String fileName = String.format(
                            "field_declaration_not_found_%s_%s",
                            project.projectName(),
                            "getFieldsFromType"
                    );
                    FileUtils.appendToFileExclusive(
                            Path.OUTPUT.getValue(),
                            fileName,
                            FileFormat.TXT,
                            project.projectName(),
                            jpResolvedDeclaration.get().getQualifiedName()
                    );
                    List<String> jpFieldDeclarationNameList = jpFieldDeclarationList
                            .stream()
                            .map(jpField -> jpField.getVariables())
                            .flatMap(List::stream)
                            .map(jpVariable -> jpVariable.getNameAsString())
                            .toList();
                    // Collect all the SymbolSolver resolved field declarations not converted
                    jpResolvedFieldNotFound.addAll(
                        jpResolvedFieldDeclarationList
                            .stream()
                            .filter(m -> !jpFieldDeclarationNameList.contains(m.getName()))
                            .toList()
                    );
                }
                // Return the two list of field declarations (successfully converted) and the resolved field declaration
                // not converted
                return new Pair<>(jpFieldDeclarationList, jpResolvedFieldNotFound);
            } else {
                // Notify the impossibility to find the resolved type declaration
                String errMsg = String.format(
                        "Unable to analyze the resolved type %s: resolved type declaration not found.",
                        jpResolvedType.toString()
                );
                System.err.println(errMsg);
            }
        } else if (!(jpResolvedType.isPrimitive() || jpResolvedType.isVoid() || jpResolvedType.isTypeVariable() || jpResolvedType.isArray())) {
            // Unknown resolved type
            String errMsg = String.format(
                    "Return type %s different from ReferenceType, PrimitiveType, ArrayType, TypeVariable, and VoidType not yet supported",
                    jpResolvedType.toString()
            );
            System.err.println(errMsg);
        }
        // Return empty lists if the resolved type cannot be analyzed
        return new Pair<>(new ArrayList<>(), new ArrayList<>());
    }

    /**
     * The method tries to find all the methods declared by a class or inherited from its superclasses, given a SymbolSolver
     * type {@link Type}. The method returns the methods in the form of a pair of lists of JavaParser method declarations
     * {@link MethodDeclaration} and method usage {@link MethodUsage}. Given the type, the method searches the
     * corresponding JavaParser AST declaration node. If found, the method returns all the methods in the form of method
     * declarations {@link MethodDeclaration} (in this case the second element of the pair is an empty list).
     * Otherwise, if the class declaration is not found, the method get all the SymbolSolver method usages {@link MethodUsage}
     * from the SymbolSolver resolved type {@link ResolvedType}, and it tries to find the corresponding JavaParser method
     * declaration {@link MethodDeclaration}, from each SymbolSolver method usage {@link MethodUsage}. The fields successfully
     * converted are returned in the form of JavaParser method declarations {@link MethodDeclaration}, while the others
     * are returned in the form of SymbolSolver method usages {@link MethodUsage}.
     *
     * @param project The project under analysis.
     * @param jpType The SymbolSolver type {@link Type} from which to extract the methods.
     * @return A pair of lists of method declarations {@link FieldDeclaration} and method usages {@link MethodUsage},
     * representing the methods declared by the class or inherited from its superclasses. The method returns a pair of
     * empty lists if the type cannot be resolved.
     */
    public Pair<List<MethodDeclaration>,List<MethodUsage>> getMethodsFromType(
            Project project,
            Type jpType
    ) {
        // Get the SymbolSolver ResolvedType
        ResolvedType jpResolvedType = jpType.resolve();
        return getMethodsFromType(project, jpResolvedType);
    }

    /**
     * The method tries to find all the methods declared by a class or inherited from its superclasses, given a SymbolSolver
     * resolved type {@link ResolvedType}. The method returns the methods in the form of a pair of lists of JavaParser
     * method declarations {@link MethodDeclaration} and method usage {@link MethodUsage}. Given the resolved type, the method
     * searches the corresponding JavaParser AST declaration node. If found, the method returns all the methods in the
     * form of method declarations {@link MethodDeclaration} (in this case the second element of the pair is an empty list).
     * Otherwise, if the class declaration is not found, the method get all the SymbolSolver method usages {@link MethodUsage}
     * from the SymbolSolver resolved type {@link ResolvedType}, and it tries to find the corresponding JavaParser method
     * declaration {@link MethodDeclaration}, from each SymbolSolver method usage {@link MethodUsage}. The fields successfully
     * converted are returned in the form of JavaParser method declarations {@link MethodDeclaration}, while the others
     * are returned in the form of SymbolSolver method usages {@link MethodUsage}.
     *
     * @param project The project under analysis.
     * @param jpResolvedType The SymbolSolver resolved type {@link ResolvedType} from which to extract the methods.
     * @return A pair of lists of method declarations {@link FieldDeclaration} and method usages {@link MethodUsage},
     * representing the methods declared by the class or inherited from its superclasses. The method returns a pair of
     * empty lists if the type cannot be resolved.
     */
    public Pair<List<MethodDeclaration>,List<MethodUsage>> getMethodsFromType(
            Project project,
            ResolvedType jpResolvedType
    ) {
        // Check that the type is not primitive
        if (jpResolvedType.isReferenceType()) {
            // Get the SymbolSolver reference declaration
            Optional<ResolvedReferenceTypeDeclaration> jpResolvedDeclaration = jpResolvedType.asReferenceType().getTypeDeclaration();
            // Define the list of method usage not converted
            List<MethodUsage> jpMethodUsageNotFound = new ArrayList<>();
            // Check that the reference declaration exists
            if (jpResolvedDeclaration.isPresent()) {
                // Try to get the corresponding JavaParser node declaration
                Optional<Node> jpNode = jpResolvedDeclaration.get().toAst();
                // Check if the corresponding JavaParser node has been found, and it is a class
                if (jpNode.isPresent() && jpNode.get() instanceof TypeDeclaration<?>) {
                    // Get all the methods declared within the class and inherited from its superclass
                    // in the form of JavaParser method declarations
                    List<MethodDeclaration> jpMethods = ((TypeDeclaration<?>) jpNode.get()).getMethods();
                    // Return the pair (all the methods are in the form of methods declarations)
                    return new Pair<>(jpMethods, new ArrayList<>());
                }
                try {
                    // Get all the methods declared within the class and inherited from its superclass, in the form of
                    // SymbolSolver method usaged
                    Set<MethodUsage> jpMethodUsageList = jpResolvedDeclaration.get().getAllMethods();
                    // Try to convert has many as possible SymbolSolver method usages into JavaParser method
                    // declarations
                    List<MethodDeclaration> jpMethodDeclarationList = jpMethodUsageList
                            .stream()
                            .map(m -> getMethodDeclarationFromMethodUsage(project, m))
                            .flatMap(Optional::stream)
                            .toList();
                    // Check if all the method usages have been converted
                    if (!(jpMethodDeclarationList.size() == jpMethodUsageList.size())) {
                        // Notify the impossibility to convert {n} SymbolSolver method usages into
                        // JavaParser method declarations
                        String errMsg = String.format(
                                "Unable to convert %s MethodUsage instances to MethodDeclaration instances.",
                                Integer.valueOf(jpMethodUsageList.size() - jpMethodDeclarationList.size()).toString()
                        );
                        //System.err.println(errMsg);
                        String fileName = String.format(
                                "method_declaration_not_found_%s_%s",
                                project.projectName(),
                                "getMethodsFromType"
                        );
                        FileUtils.appendToFileExclusive(
                                Path.OUTPUT.getValue(),
                                fileName,
                                FileFormat.TXT,
                                project.projectName(),
                                jpResolvedDeclaration.get().getQualifiedName()
                        );
                        List<String> jpMethodDeclarationNameList = jpMethodDeclarationList.stream().map(m -> m.getNameAsString()).toList();
                        // Collect all the SymbolSolver method usages not converted
                        jpMethodUsageNotFound.addAll(jpMethodUsageList.stream().filter(m -> !jpMethodDeclarationNameList.contains(m.getName())).toList());
                    }
                    // Return the two list of method declarations (successfully converted) and the method usages
                    // not converted
                    return new Pair<>(jpMethodDeclarationList, jpMethodUsageNotFound);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (UnsolvedSymbolException e) {
                    e.printStackTrace();
                }
            } else {
                // Notify the impossibility to find the resolved type declaration
                String errMsg = String.format(
                        "Unable to analyze the resolved type %s: resolved type declaration not found.",
                        jpResolvedType.toString()
                );
                System.err.println(errMsg);
            }
        } else if (!(jpResolvedType.isPrimitive() || jpResolvedType.isVoid() || jpResolvedType.isTypeVariable() || jpResolvedType.isArray())) {
            // Unknown resolved type
            String errMsg = String.format(
                    "Return type %s different from ReferenceType, PrimitiveType, ArrayType, TypeVariable, and VoidType not yet supported",
                    jpResolvedType.toString()
            );
            System.err.println(errMsg);
        }
        // Return empty lists if the resolved type cannot be analyzed
        return new Pair<>(new ArrayList<>(), new ArrayList<>());
    }

    /**
     * The method searches a class within the compilation unit {@link CompilationUnit} passed to the function, that
     * defines a constructor {@link ConstructorDeclaration} with the same number and type name of parameters defined by
     * the list of JDoctor condition parameter type names passed to the function {@code jDoctorConditionParamTypeNames}.
     * If the class is found, the method returns it in the form of a type declaration {@link TypeDeclaration}. Otherwise,
     * the method throws an exception {@link JPClassNotFoundException}.
     *
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @param jDoctorConditionParamTypeNames A list of JDoctor condition parameter type names
     * @return The method returns the class where the constructor corresponding to the list of JDoctor condition parameter 
     * type names has been declared (if the class is found). Otherwise, the method throws an exception 
     * {@link JPClassNotFoundException}.
     *
     * @throws PrimaryTypeNotFoundException If the compilation does not have a primary class {@link TypeDeclaration} (in
     * other words, the compilation unit does not have a class defined within it). Therefore, is impossible to find a
     * constructor within it.
     * @throws JPClassNotFoundException If the method does not find a class with a constructor corresponding to the list
     * of JDoctor condition parameter type names passed to the function.
     */
    public static TypeDeclaration<?> getTypeDeclarationFromConstructorJDoctorConditionParamTypeNames(
        CompilationUnit cu,
        List<String> jDoctorConditionParamTypeNames
    ) throws PrimaryTypeNotFoundException, JPClassNotFoundException {
        // Get the list of all the classes defined within the compulation unit: the first one is the primary type
        List<TypeDeclaration<?>> jpClasses = findAllTypeDeclarationsFromCompilationUnit(cu);
        // Convert condition parameters types strings in the format of the JavaParser types strings
        jDoctorConditionParamTypeNames = convertJDoctorConditionTypeNames2JavaParserTypeNames(jDoctorConditionParamTypeNames);
        // Iterate over the classes until the class that defines the constructor is found
        for(TypeDeclaration<?> jpClass: jpClasses) {
            try {
                ConstructorDeclaration jpConstructor = getConstructorDeclaration(jpClass, jDoctorConditionParamTypeNames);
                return jpClass;
            } catch (JPConstructorNotFound e) {
                continue;
            }
        }
        // Raise an exception if the constructor has not been found
        String errMsg = String.format("The compilation unit passed to the function does not have a class with the given constructor.");
        throw new JPClassNotFoundException(errMsg);
    }

    /**
     * The method searches a class within the compilation unit {@link CompilationUnit}, that defines a method 
     * {@link MethodDeclaration} with the same number and type name of parameters defined by the list of JDoctor 
     * condition parameter type names passed to the function {@code jDoctorConditionParamTypeNames}. If the class is 
     * found, the method returns it in the form of a type declaration {@link TypeDeclaration}. Otherwise, the method 
     * throws an exception {@link JPClassNotFoundException}.
     *
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @param jDoctorConditionParamTypeNames A list of JDoctor condition parameter type names
     * @return The method returns the class where the method corresponding to the list of JDoctor condition parameter 
     * type names has been declared (if the class is found). Otherwise, the method throws an exception 
     * {@link JPClassNotFoundException}.
     *
     * @throws PrimaryTypeNotFoundException If the compilation does not have a primary class {@link TypeDeclaration} (in
     * other words, the compilation unit does not have a class defined within it). Therefore, is impossible to find a
     * constructor within it.
     * @throws JPClassNotFoundException If the method does not find a class with a method corresponding to the list
     * of JDoctor condition parameter type names passed to the function.
     */
    public static TypeDeclaration<?> getTypeDeclarationFromMethodJDoctorConditionParamTypeNames(
            CompilationUnit cu,
            String methodName,
            String className,
            List<String> jDoctorConditionParamTypeNames
    ) throws PrimaryTypeNotFoundException, JPClassNotFoundException {
        // Get the list of all the classes defined within the compulation unit: the first one is the primary type
        List<TypeDeclaration<?>> jpClasses = findAllTypeDeclarationsFromCompilationUnit(cu);
        // Convert condition parameters types strings in the format of the JavaParser types strings
        jDoctorConditionParamTypeNames = convertJDoctorConditionTypeNames2JavaParserTypeNames(jDoctorConditionParamTypeNames);
        // Iterate over the classes until the class that defines the constructor is found
        for(TypeDeclaration<?> jpClass: jpClasses) {
            try {
                CallableDeclaration<?> jpCallable = methodName.equals(className) ? getConstructorDeclaration(jpClass, jDoctorConditionParamTypeNames) : getMethodDeclaration(jpClass, methodName, jDoctorConditionParamTypeNames);
                TypeDeclaration<?> jpCandidateClass = getJavaTypeDeclarationFromClassDeclaration(cu, jpCallable);
                if (jpCandidateClass.equals(jpCandidateClass)) {
                    return jpClass;
                }
            } catch (JPMethodNotFound | JPConstructorNotFound e) {
                e.printStackTrace();
                continue;
            }
        }
        // Raise an exception if the constructor has not been found
        String errMsg = String.format("The compilation unit passed to the function does not have a class with the given method.");
        throw new JPClassNotFoundException(errMsg);
    }

    /**
     * The method navigates the hierarchy of JavaParser AST Nodes (from the body declaration {@link BodyDeclaration} 
     * passed to the function) until the first type declaration {@link TypeDeclaration} is found. The body declaration 
     * {@code jpBody} can be any instance of an object that extends the {@link BodyDeclaration} type, like for example,
     * a field declaration {@link FieldDeclaration} or a method declaration {@link MethodDeclaration}. The first type 
     * declaration {@link TypeDeclaration} found navigating the hierarchy corresponds to the class where the body 
     * declaration has been defined. The method returns an optional type declaration {@link Optional<TypeDeclaration>}: 
     * the type declaration is present within the optional, if the class is found, otherwise the optional is empty.
     * 
     * @param jpBody An instance of an object that extends the {@link BodyDeclaration} type, like for example,
     *               A field declaration {@link FieldDeclaration} or a method declaration {@link MethodDeclaration}
     * @return An optional type declaration {@link Optional<TypeDeclaration>}. The optional contains a type declaration
     * representing the class where the body declaration has been defined. Instead, if the type declaration is not found, the 
     * optional is empty.
     */
    public static Optional<TypeDeclaration<?>> getTypeDeclarationFromBodyDeclaration(
            BodyDeclaration<?> jpBody
    ) {
        // Navigate the hierarchy of parent nodes while a node of type TypeDeclaration (that refers to a JavaParser
        // class declaration) is found
        Node parentNode = jpBody.getParentNode().orElse(null);
        while (parentNode != null) {
            // Check if the node corresponds to an instance of a class declaration
            if (parentNode instanceof TypeDeclaration) {
                // Cast the node to a TypeDeclaration
                TypeDeclaration<?> jpClass = (TypeDeclaration<?>) parentNode;
                // Return the class declaration
                return Optional.of(jpClass);
            }
            // Get the parent node of the current node
            parentNode = parentNode.getParentNode().orElse(null);
        }
        // The class declaration has not been found
        return Optional.empty();
    }

    /**
     * The method navigates the hierarchy of JavaParser AST Nodes (from the field declaration {@link FieldDeclaration} 
     * passed to the function) until the first type declaration {@link TypeDeclaration} is found. The first type declaration 
     * {@link TypeDeclaration} found navigating the hierarchy corresponds to the class where the field declaration 
     * has been defined. The method returns an optional type declaration {@link Optional<TypeDeclaration>}: the type 
     * declaration is present within the optional, if the class is found, otherwise the optional is empty.
     * 
     * @param jpField A JavaParser field {@link FieldDeclaration} representing a statement of a class where one or more 
     *                variable has been defined.
     * @return An optional type declaration {@link Optional<TypeDeclaration>}. The optional contains a type declaration
     * representing the class where the field declaration has been defined. Instead, if the type declaration is not found, 
     * the optional is empty.
     * @throws JPClassNotFoundException If the method does not find a class with a method corresponding to the list
     * of JDoctor condition parameter type names passed to the function.
     */
    public static TypeDeclaration<?> getTypeDeclarationFromFieldDeclaration(
            FieldDeclaration jpField
    ) throws JPClassNotFoundException {
        // Get the JavaParser class where the field has been defined, if exists
        Optional<TypeDeclaration<?>> jpClass = getTypeDeclarationFromBodyDeclaration(jpField);
        // Check if the class has not been found
        if (jpClass.isEmpty()) {
            // Raise an exception if the class where the field should have been defined has not been found instead
            String errMsg = String.format(
                "The class where the field %s should have been defined has not be found.",
                jpField.toString()
            );
            throw new JPClassNotFoundException();
        }
        // Return the JavaParser class
        return jpClass.get();
    }

    /**
     * The method navigates the hierarchy of JavaParser AST Nodes (from the callable declaration {@link CallableDeclaration}
     * passed to the function) until the first type declaration {@link TypeDeclaration} is found. The callable declaration 
     * {@code jpCallable} can be any instance of an object that extends the {@link CallableDeclaration} type, like 
     * for example, a method declaration {@link MethodDeclaration} or a constructor declaration {@link ConstructorDeclaration}. 
     * The first type declaration {@link TypeDeclaration} found navigating the hierarchy corresponds to the class where 
     * the body declaration has been defined. The method returns an optional type declaration {@link Optional<TypeDeclaration>}: 
     * the type declaration is present within the optional, if the class is found, otherwise the optional is empty.
     *
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @param jpCallable An instance of an object that extends the {@link CallableDeclaration} type, like for example,
     *                   A method declaration {@link MethodDeclaration} or a constructor declaration 
     *                   {@link ConstructorDeclaration}
     * @return An optional type declaration {@link Optional<TypeDeclaration>}. The optional contains a type declaration
     * representing the class where the callable declaration has been defined. Instead, if the type declaration is not 
     * found, the optional is empty.
     * @throws JPClassNotFoundException If the method does not find a class with a constructor corresponding to the list
     * of JDoctor condition parameter type names passed to the function.
     */
    public static TypeDeclaration<?> getJavaTypeDeclarationFromClassDeclaration(
            CompilationUnit cu,
            CallableDeclaration<?> jpCallable
    ) throws JPClassNotFoundException {
        // Get the JavaParser class where the method has been defined, if exists
        Optional<TypeDeclaration<?>> jpClass = getTypeDeclarationFromBodyDeclaration(jpCallable);
        if (jpClass.isEmpty()) {
            // Raise an exception if the class where the method should have been defined has not been found instead
            String errMsg = String.format(
                "The class where the field %s should have been defined has not be found.",
                jpCallable.toString()
            );
            throw new JPClassNotFoundException();
        }
        // Return the JavaParser class
        return jpClass.get();
    }

    /**
     * The method try to find the corresponding JavaParser AST type declaration {@link TypeDeclaration}, given a SymbolSolver
     * type {@liink Type} passed to the function. The type declaration corresponds to the class that the SymbolSolver type
     * represents. The method returns an optional type declaration {@link Optional<TypeDeclaration>}: the type declaration
     * is present within the optional, if the class is found, otherwise the optional is empty.
     *
     * @param project The project under analysis.
     * @param jpType The SymbolSolver type {@link Type} from which to extract the type declaration {@link TypeDeclaration}
     * @return An optional type declaration {@link Optional<TypeDeclaration>}. The optional contains a type declaration
     * representing the class where the callable declaration has been defined. Instead, if the type declaration is not
     * found, the optional is empty.
     */
    public Optional<TypeDeclaration<?>> getTypeDeclarationFromType(
            Project project,
            Type jpType
    ) {
        // Get the SymbolSolver ResolvedType
        ResolvedType jpResolvedType = jpType.resolve();
        // Check that the type is not primitive
        if (jpResolvedType.isReferenceType()) {
            // Get the SymbolSolver reference declaration
            Optional<ResolvedReferenceTypeDeclaration> jpResolvedDeclaration = jpResolvedType.asReferenceType().getTypeDeclaration();
            // Check that the reference declaration exists
            if (jpResolvedDeclaration.isPresent()) {
                // Map the SymbolSolver reference declaration to a node of the JavaParser AST
                Optional<Node> node = jpResolvedDeclaration.get().toAst();
                // Check if the node exists and it represents a class declaration
                if (node.isPresent() && node.get() instanceof TypeDeclaration<?>) {
                    // Return the class declaration
                    return Optional.of((TypeDeclaration<?>) node.get());
                } else {
                    // Try to find the TypeDeclaration from the qualified name
                    // Get the qualified name
                    String qualifiedName= jpResolvedDeclaration.get().getQualifiedName();
                    // Search the compilation unit from the qualified name
                    Optional<CompilationUnit> cu = getCompilationUnitFromQualifiedName(qualifiedName, project);
                    // Check if the compilation unit has been found
                    if (cu.isPresent()) {
                        // Return the optional primary type or empty
                        return cu.get().getPrimaryType();
                    }
                }
            }
        }
        // The class declaration has not been found
        return Optional.empty();
    }

    /**
     * The method collects a list of pairs of strings {@link Pair<String,String>}, representing the class names of the
     * classes defined within the given compilation unit {@link CompilationUnit}, and the corresponding Javadoc comments,
     * if found.
     *
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @return A list of pairs of strings {@link Pair<String,String>}. The first element of each pair is the class name
     * of a class defined within the compilation unit {@link CompilationUnit} passed to the function. The second element
     * represents the corresponding Javadoc comment (the string is empty if the Javadoc comment is not present or found).
     * @throws PrimaryTypeNotFoundException If the compilation does not have a primary class {@link TypeDeclaration} (in
     * other words, the compilation unit does not have a class defined within it).
     */
    public static List<Pair<String, String>> getJavadocCommentForAllTypeDeclarationsInCompilationUnit(
            CompilationUnit cu
    ) throws PrimaryTypeNotFoundException {
        // Define the list of pairs of type [className, classJavadoc]
        List<Pair<String, String>> pairList = new ArrayList<>();
        // Get the primary class of the JavaParser compilation unit: it must correspond to the class
        // of the condition analyzed
        List<TypeDeclaration<?>> jpClasses = findAllTypeDeclarationsFromCompilationUnit(cu);
        // Iterate over all the classes defined within the compilation unit
        for(TypeDeclaration<?> jpClass: jpClasses) {
            // Get the class name
            String jpClassName = jpClass.getNameAsString();
            // Define the javadoc string and initialize it as an empty string
            String javadocComment = "";
            // Extract Javadoc comment (if present) of the class
            Optional<JavadocComment> jpJavadocComment = jpClass.getJavadocComment();
            // Check if the Javadoc comment is present
            if (jpJavadocComment.isEmpty()) {
                // If empty, try to extract it with a manual approach
                javadocComment = extractJavadocCommentByHeuristics(jpClass);
                if (!javadocComment.isEmpty()) {
                    // Add Prefix and suffix to the JavaParser Javadoc comment
                    javadocComment = Javadoc.CLASS_PREFIX.getValue() + javadocComment + Javadoc.CLASS_SUFFIX.getValue();
                }
                pairList.add(new Pair<>(jpClassName, javadocComment));
            } else {
                // Add Prefix and suffix to the JavaParser Javadoc comment
                javadocComment = Javadoc.CLASS_PREFIX.getValue() + jpJavadocComment.get().getContent() + Javadoc.CLASS_SUFFIX.getValue();
                // Add Javadoc comment of the class to the pair list
                pairList.add(new Pair<>(jpClassName, javadocComment));
            }
        }
        // Return the pair list
        return pairList;
    }

    /**
     * The method gets the Javadoc comment of the primary class {@link TypeDeclaration} defined within the given 
     * compilation unit {@link CompilationUnit}. The string returned by the function is empty if the Javadoc comment is
     * not present or found. 
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @return A string {@link String} representing the Javadoc comment of the primary class {@link TypeDeclaration} of 
     * the compilation unit {@link CompilationUnit} passed to the function. The string is empty if the Javadoc comment
     * is not present or not found.
     * @throws PrimaryTypeNotFoundException If the compilation does not have a primary class {@link TypeDeclaration} (in
     * other words, the compilation unit does not have a class defined within it).
     */
    public static String getJavadocCommentFromPrimaryClass(
            CompilationUnit cu
    ) throws PrimaryTypeNotFoundException {
        // Get the primary class of the JavaParser compilation unit: it must correspond to the class
        // of the condition analyzed
        TypeDeclaration<?> jpClass = getPrimaryClassFromCompilationUnit(cu);
        // Extract Javadoc comment (if present) of the class
        Optional<JavadocComment> jpJavadocComment = jpClass.getJavadocComment();
        // Check if the Javadoc comment is present
        if (jpJavadocComment.isEmpty()) {
            // If empty, try to extract it with a manual approach
            return extractJavadocCommentByHeuristics(jpClass);
        }
        // Return Javadoc comment of the class
        return Javadoc.CLASS_PREFIX.getValue() + jpJavadocComment.get().getContent() + Javadoc.CLASS_SUFFIX.getValue();
    }

    /**
     * The method gets the string representation of the Java source code of the primary class {@link TypeDeclaration}
     * defined within the given compilation unit {@link CompilationUnit}.
     *
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @return A string {@link String} representation of the Java source code of the primary class {@link TypeDeclaration}
     * of the compilation unit {@link CompilationUnit} passed to the function.
     * @throws PrimaryTypeNotFoundException If the compilation does not have a primary class {@link TypeDeclaration} (in
     * other words, the compilation unit does not have a class defined within it).
     */
    public static String getSourceCodeFromPrimaryClass(
            CompilationUnit cu
    ) throws PrimaryTypeNotFoundException {
        // Get the primary class of the JavaParser compilation unit: it must correspond to the class
        // of the condition analyzed
        TypeDeclaration<?> jpClass = getPrimaryClassFromCompilationUnit(cu);
        // Extract source code of the class
        String sourceCode = jpClass.toString();
        return sourceCode;
    }

    /**
     * The method searches the corresponding JavaParser compilation unit {@link CompilationUnit} where the body declaration
     * {@link BodyDeclaration} passed to the function has been defined.
     * 
     * @param jpBody An instance of an object that extends the {@link BodyDeclaration} type, like for example,
     *               A field declaration {@link FieldDeclaration} or a method declaration {@link MethodDeclaration}
     * @return An optional JavaParser compilation unit {@link Optional<CompilationUnit>}. The optional contains the
     * compilation unit, if found. Otherwise, the method returns an empty optional.
     */
    public static Optional<CompilationUnit> getCompilationUnitFromBodyDeclaration(
            BodyDeclaration<?> jpBody
    ) {
        // Find the compilation unit to which the body declaration refers
        return jpBody.findCompilationUnit();
    }

    /**
     * The method reads the corresponding JavaParser compilation unit {@link CompilationUnit}, from a given file {@link File}.
     * 
     * @param file the file to read (it must represent a Java class)
     * @return an optional JavaParser compilation unit {@link CompilationUnit}. The optional contains the compilation unit,
     * if found. Otherwise, the method returns an empty optional.
     */
    public Optional<CompilationUnit> getCompilationUnitFromFile(File file) {
        // Get absolute path of the file
        String filePath = file.getAbsolutePath();
        // Instantiate a JavaParser compilation unit from the absolute path of the file
        return getCompilationUnitFromFile(filePath);
    }

    /**
     * The method gets the corresponding JavaParser compilation unit {@link CompilationUnit}, from a file lying in the
     * given path passed to the function {@link String}.
     *
     * @param filePath a string {@link String} representing the absolute path to the file (it must represent a Java class)
     * @return an optional JavaParser compilation unit {@link CompilationUnit}. The optional contains the compilation unit,
     * if found. Otherwise, the method returns an empty optional.
     */
    public Optional<CompilationUnit> getCompilationUnitFromFile(String filePath) {
        return getCompilationUnitFromFile(filePath, false);
    }

    /**
     * The method gets the corresponding JavaParser compilation unit {@link CompilationUnit}, from a file lying in the
     * given path passed to the function {@link String}. The method is silent, therefore it does not notify any error
     * if the file is not found.
     *
     * @param filePath a string {@link String} representing the absolute path to the file (it must represent a Java class)
     * @return an optional JavaParser compilation unit {@link CompilationUnit}. The optional contains the compilation unit,
     * if found. Otherwise, the method returns an empty optional.
     */
    public Optional<CompilationUnit> getCompilationUnitFromFileSilent(String filePath) {
        return getCompilationUnitFromFile(filePath, true);
    }

    /**
     * The method gets the corresponding JavaParser compilation unit {@link CompilationUnit}, from a file lying in the
     * given path passed to the function {@link String}. The method has a second parameter {@code silent} that is a boolean
     * {@link Boolean}: if {@code true} the method is silent, therefore it does not notify any error if the file is not
     * found.
     *
     * @param filePath the absolute path to a Java file
     * @param silent if {@code true} the method is silent, therefore this method does not notify any error
     * if the file is not found.
     * @return the compilation unit, if found
     */
    public Optional<CompilationUnit> getCompilationUnitFromFile(String filePath, boolean silent) {
        // Instantiate a file from a given path
        File file = new File(filePath);
        try {
            // Instantiate a JavaParser compilation unit from the file (if found)
            Optional<CompilationUnit> cu = this.javaParser.parse(file).getResult();
            return cu;
        } catch (FileNotFoundException e) {
            if (!silent) {
                // Manage exception if JavaParser is not able to instantiate the compilation unit
                String errMsg = String.format("File not found: %s", filePath);
                System.err.println(errMsg);
                //e.printStackTrace();
            }
            return Optional.empty();
        }
    }

    /**
     * The method retrieves the respective JavaParser compilation unit {@link CompilationUnit} of a given type {@link Type}.
     *
     * @param jpType The SymbolSolver type {@link Type} from which to extract the compilation unit {@link CompilationUnit}.
     * @return An optional JavaParser compilation unit {@link CompilationUnit}. The optional contains the compilation unit,
     * if found. Otherwise, the method returns an empty optional.
     */
    private Optional<CompilationUnit> getCompilationUnitFromType(
            Type jpType
    ) {
        // Get the parent node of the SymbolSolver type
        Optional<Node> node = jpType.getParentNode();
        // Check if the node is present
        if (node.isPresent()) {
            // Get the node as a parent node of the SymbolSolver type, in the hierarchy of nodes
            Node parentNode = node.get();
            // Find the corresponding JavaParser compilation unit of the SymbolSolver type
            Optional<CompilationUnit> cu = parentNode.findCompilationUnit();
            // Return the optional JavaParser compilation unit
            return cu;
        }
        // Return empty if the SymbolSolver type does not have a parent node and the corresponding JavaParser
        // compilation unit cannot be found
        return Optional.empty();
    }

    /**
     * The method searches a JavaParser constructor declaration {@link ConstructorDeclaration} within the given class
     * {@code jpClass}, with the same number and type name of parameters defined by the list of JDoctor condition parameter
     * type names passed to the function {@code jDoctorConditionParamTypeNames}. If the constructor is found, the method
     * returns the JavaParser constructor declaration {@link ConstructorDeclaration}. Otherwise, the method throws an
     * exception {@link JPConstructorNotFound}.
     *
     * @param jpClass An instance of a JavaParser type declaration {@link TypeDeclaration} representing the class where
     *                to find the constructor corresponding to the one to which the JDoctor condition refers.
     * @param jDoctorConditionParamTypeNames The list of strings, representing the type names of the arguments of the
     *                                       constructor to find within the given class {@link TypeDeclaration}.
     * @return The JavaParser constructor declaration {@link ConstructorDeclaration} corresponding to the one to which
     * the JDoctor condition refers. The method returns an exception if the constructor is not found.
     * @throws JPConstructorNotFound If the constructor is not found within the given class {@link TypeDeclaration}.
     */
    public static ConstructorDeclaration getConstructorDeclaration(
            TypeDeclaration<?> jpClass,
            List<String> jDoctorConditionParamTypeNames
    ) throws JPConstructorNotFound {
        // Get the list of constructors of the JavaParser primary class
        List<? extends CallableDeclaration<?>> jpCallableList = jpClass.getConstructors();
        // Convert condition parameters types strings in the format of the JavaParser types strings
        jDoctorConditionParamTypeNames = convertJDoctorConditionTypeNames2JavaParserTypeNames(jDoctorConditionParamTypeNames);
        // Search the JavaParser constructor that matches the constructor of the JDoctor condition analyzed
        Optional<CallableDeclaration<?>> jpConstructor = getCallableDeclarationFromTypeDeclaration(
                jpClass,
                jDoctorConditionParamTypeNames,
                jpCallableList
        );
        // Check if the constructor has been found
        if (jpConstructor.isPresent()) {
            // Return the constructor declaration
            return (ConstructorDeclaration) jpConstructor.get();
        }
        // Raise an exception if the constructor has not been found
        String errMsg = String.format("The Java class passed to the function does not have a constructor that matches the constructor of JDoctor condition.");
        throw new JPConstructorNotFound(errMsg);
    }

    /**
     * The method searches a class within the compilation unit {@link CompilationUnit} passed to the function, that
     * defines a constructor {@link ConstructorDeclaration} with the same number and type name of parameters defined by
     * the list of JDoctor condition parameter type names passed to the function {@code jDoctorConditionParamTypeNames}.
     * If the class is found and the corresponding constructor is found, the method returns the JavaParser constructor
     * declaration {@link ConstructorDeclaration}. Otherwise, the method throws an exception {@link JPConstructorNotFound}.
     *
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @param jDoctorConditionParamTypeNames The list of strings, representing the type names of the arguments of the
     *                                       constructor to find within the given compilation unit {@link CompilationUnit}.
     * @return The JavaParser constructor declaration {@link ConstructorDeclaration} corresponding to the one to which
     * the JDoctor condition refers within the compilation unit.
     * @throws JPConstructorNotFound If the constructor is not found within the given JavaParser compilation unit
     * {@link CompilationUnit}.
     */
    public static ConstructorDeclaration getConstructorDeclaration(
            CompilationUnit cu,
            List<String> jDoctorConditionParamTypeNames
    ) throws PrimaryTypeNotFoundException, JPConstructorNotFound {
        // Get the list of all the classes defined within the compulation unit: the first one is the primary type
        List<TypeDeclaration<?>> jpClasses = findAllTypeDeclarationsFromCompilationUnit(cu);
        // Convert condition parameters types strings in the format of the JavaParser types strings
        jDoctorConditionParamTypeNames = convertJDoctorConditionTypeNames2JavaParserTypeNames(jDoctorConditionParamTypeNames);
        // Iterate over the classes until the class that defines the constructor is found
        for(TypeDeclaration<?> jpClass: jpClasses) {
            try {
                return getConstructorDeclaration(jpClass, jDoctorConditionParamTypeNames);
            } catch (JPConstructorNotFound e) {
                continue;
            }
        }
        // Raise an exception if the constructor has not been found
        String errMsg = String.format("The compilation unit passed to the function does not have a constructor that matches the constructor of JDoctor condition.");
        throw new JPConstructorNotFound(errMsg);
    }

    /**
     * The method collects all the arguments names, packages, and types of a constructor, in the form of a list of 
     * triplets of strings {@link Triplet<String,String,String>}. The method searches a class within the compilation 
     * unit {@link CompilationUnit} passed to the function, that defines a constructor {@link ConstructorDeclaration} 
     * with the same number and type name of parameters defined by the list of JDoctor condition parameter type names 
     * passed to the function {@code jDoctorConditionParamTypeNames}. If the class is found and the corresponding 
     * constructor is found, the method analyzes all the arguments of the constructor and generates the corresponding triplets.
     * Each triplet has the same structure (each string represents an information about the argument, and the order of the
     * strings is important):
     * <ol>
     *     <li>argument name</li>
     *     <li>package name</li>
     *     <li>type name</li>
     * </ol>
     * As an example, consider the constructor: {@code public Constructor(Type1 arg1, Type2 arg2){ [constructor code] }}.
     * The method will produce the following list of triplets:
     * {@code [
     *      ["arg1", "package_name_of_arg1", "type_name_of_arg1"],
     *      ["arg2", "package_name_of_arg2", "type_name_of_arg2"]
     * ]}
     * where
     * <ul>
     *     <li>*package_name_of_arg1* is the package name of the class of arg1</li>
     *     <li>*type_name_of_arg1* is the type name (i.e. the class name) of arg1</li>
     * </ul>
     * The method returns the list of triplets collected.
     *
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @param jDoctorConditionParamTypeNames The list of strings, representing the type names of the arguments of the
     *                                       constructor to find within the given class {@link TypeDeclaration}.
     * @return A list of triplets of strings {@link Triplet<String,String,String>}, where each triplet contains the
     * argument name, package name, and type name of the constructor to which the JDoctor condition refers within the
     * compilation unit {@link CompilationUnit} passed to the function.
     * @throws PrimaryTypeNotFoundException If the compilation does not have a primary class {@link TypeDeclaration} (in
     * other words, the compilation unit does not have a class defined within it).
     * @throws JPConstructorNotFound If the constructor is not found within the given JavaParser compilation unit
     * {@link CompilationUnit}.
     * @throws PackageDeclarationNotFoundException If the compilation does not have a package declaration
     * {@link PackageDeclaration}
     * @throws JPClassNotFoundException If the method does not find a class with a constructor corresponding to the list
     * of JDoctor condition parameter type names passed to the function.
     */
    public static List<Triplet<String, String, String>> getArgumentsNameAndPackageAndTypeFromConstructorDeclaration(
            CompilationUnit cu,
            List<String> jDoctorConditionParamTypeNames
    ) throws PrimaryTypeNotFoundException, JPConstructorNotFound, PackageDeclarationNotFoundException, JPClassNotFoundException {
        // Get the class of the compilation unit where the constructor has been defined
        TypeDeclaration<?> jpClass = getTypeDeclarationFromConstructorJDoctorConditionParamTypeNames(cu, jDoctorConditionParamTypeNames);
        // Get the package declaration of the compilation unit
        PackageDeclaration jpPackage = getPackageDeclarationFromCompilationUnit(cu);
        // Search for a match between all the JavaParser constructors defined within the primary class, and the
        // JDoctor condition constructor analyzed
        ConstructorDeclaration jpConstructor = getConstructorDeclaration(jpClass, jDoctorConditionParamTypeNames);
        // Return the string representing the javadoc of the constructor
        return getArgumentsNameAndPackageAndTypeFromCallableDeclaration(jpClass, jpPackage, jpConstructor);
    }

    /**
     * The method gets the Javadoc comment associated to a constructor declaration {@link ConstructorDeclaration}, if
     * present. The method searches a class within the compilation unit {@link CompilationUnit} passed to the function,
     * that defines a constructor {@link ConstructorDeclaration} with the same number and type name of parameters defined
     * by the list of JDoctor condition parameter type names passed to the function {@code jDoctorConditionParamTypeNames}.
     * If the class is found and the corresponding constructor is found, the methods searches if there is a Javadoc Comment
     * associated to the constructor. The method returns a string representing the Javadoc comment of the constructor, if
     * found, otherwise it returns an empty string.
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @param jDoctorConditionParamTypeNames A list of strings, representing the type names of the arguments of the
     *                                       constructor to find within the given JavaParser compilation unit
     *                                       {@link CompilationUnit}.
     * @return A string representing the Javadoc comment of the constructor {@link ConstructorDeclaration}, if present.
     * The string returned by the function is empty if the Javadoc comment is not present or found.
     * @throws PrimaryTypeNotFoundException If the compilation does not have a primary class {@link TypeDeclaration} (in
     * other words, the compilation unit does not have a class defined within it).
     * @throws JPConstructorNotFound If the constructor is not found within the given class {@link TypeDeclaration}.
     */
    public static String getJavadocCommentFromConstructorDeclaration(
            CompilationUnit cu,
            List<String> jDoctorConditionParamTypeNames
    ) throws PrimaryTypeNotFoundException, JPConstructorNotFound {
        // Search for a match between all the JavaParser constructors defined within the compilation unit, and the
        // JDoctor condition constructor analyzed
        ConstructorDeclaration jpConstructor = getConstructorDeclaration(cu, jDoctorConditionParamTypeNames);
        // Return the string representing the javadoc of the constructor
        return getJavadocCommentFromConstructorOrMethodDeclaration(jpConstructor);
    }

    /**
     * The method finds all the string values present within a Javadoc comment, and it returns them as a list of pairs
     * of strings {@link Pair<String,String>}.
     * The method searches a class within the compilation unit {@link CompilationUnit} passed to the function,
     * that defines a constructor {@link ConstructorDeclaration} with the same number and type name of parameters defined
     * by the list of JDoctor condition parameter type names passed to the function {@code jDoctorConditionParamTypeNames}.
     * If the class is found and the corresponding constructor is found, the method searches if there is a Javadoc Comment
     * associated to the constructor and collects all the pairs of numeric and string values defined within it. For each
     * pair:
     * <ul>
     *     <li>If the pair refers to numeric value, the first element of each pair is the string representation of the
     *     numeric value, while the second element is the type of the numeric value {@link JavadocValueType}
     *     (integer or real).</li>
     *     <li>If the pair refers to a string value, the first element of each pair is the string value found within
     *     the Javadoc comment, while the second element is the type of the value {@link JavadocValueType} (always
     *     "string").</li>
     * </ul>
     * The method returns the list of pairs collected. The list is empty if the Javadoc comment is not present, or it
     * does not contain numeric or string values.
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @param jDoctorConditionParamTypeNames A list of strings, representing the type names of the arguments of the
     *                                       constructor to find within the given compilation unit {@link CompilationUnit}.
     * @return A list of pairs of strings {@link Pair<String,String>}, representing the numeric and string values present
     * in the Javadoc comment of the constructor to which the JDoctor condition parameters type names refer to. For each
     * pair in the list, the first element is the string representation of the value (numeric or string), while
     * the second element represents the type of the value collected ({@link JavadocValueType} - integer, real, or string).
     * @throws PrimaryTypeNotFoundException If the compilation does not have a primary class {@link TypeDeclaration} (in
     * other words, the compilation unit does not have a class defined within it).
     * @throws JPConstructorNotFound If the constructor is not found within the given class {@link TypeDeclaration}.
     */
    public static List<Pair<String, String>> getJavadocValuesFromConstructorDeclaration(
            CompilationUnit cu,
            List<String> jDoctorConditionParamTypeNames
    ) throws PrimaryTypeNotFoundException, JPConstructorNotFound {
        // Get the string representing the javadoc of the method
        String methodJavadoc = getJavadocCommentFromConstructorDeclaration(cu, jDoctorConditionParamTypeNames);
        // Return the list of pairs of type [valueName, valueType]
        return getJavadocValuesFromCallableJavadocString(methodJavadoc);
    }

    /**
     * Given a list of JavaParser candidates methods or constructors {@link List<CallableDeclaration>} and the class
     * {@link TypeDeclaration} where the candidates are defined, the method searches a JavaParser callable declaration
     * {@link CallableDeclaration} (i.e. a constructor or a method) with the same number and type name of parameters
     * defined by the list of JDoctor condition parameter type names passed to the function
     * {@code jDoctorConditionParamTypeNames}. Indeed, in JavaParser, both constructors {@link ConstructorDeclaration}
     * and methods {@link MethodDeclaration} extends from {@link CallableDeclaration}.
     * The method returns an optional that contains the instance of {@link CallableDeclaration} (if found), otherwise an
     * empty optional.
     * @param jpClass An instance of a JavaParser type declaration {@link TypeDeclaration} representing the class where
     *                to find the constructor or method corresponding to the one to which the JDoctor condition refers.
     * @param jDoctorConditionParamTypeNames A list of strings, representing the type names of the arguments of the
     *                                       constructor or method to find within the given JavaParser type declaration
     *                                       {@link TypeDeclaration}.
     * @param jpCallableList A list of JavaParser callable declarations {@link List<CallableDeclaration>}, representing
     *                       the candidates constructors or methods of the given class, where to find the one to which
     *                       the JDoctor condition parameter type names refers.
     * @return An optional JavaParser callable declaration {@link Optional<CallableDeclaration>} i.e. the constructor or
     * the method that matches the list of Jdoctor condition parameter type names passed to the function
     * {@code jDoctorConditionParamTypeNames}. If the method or constructor is not found, the method returns an
     * empty optional.
     */
    private static Optional<CallableDeclaration<?>> getCallableDeclarationFromTypeDeclaration(
            TypeDeclaration<?> jpClass,
            List<String> jDoctorConditionParamTypeNames,
            List<? extends CallableDeclaration<?>> jpCallableList
    ) {
        // Search for a match between all the JavaParser constructors defined within the primary class, and the
        // JDoctor condition constructor analyzed
        Optional<CallableDeclaration<?>> jpCallable = getCallableDeclarationFromJDoctorConditionParamTypeNames(
                jpCallableList,
                jDoctorConditionParamTypeNames,
                jpClass
        );
        // Return the result (the constructor or method, or null, embraced within an Optional)
        return jpCallable;
    }

    /**
     * The method collects all the arguments names, packages, and types of a JavaParser callable declaration 
     * {@link CallableDeclaration} (i.e. a constructor or method), in the form of a list of triplets of strings 
     * {@link Triplet<String,String,String>}. Indeed, in JavaParser, both constructors {@link ConstructorDeclaration}
     * and methods {@link MethodDeclaration} extends from {@link CallableDeclaration}. 
     * The method analyzes all the arguments of the constructor and generates the corresponding triplets.
     * Each triplet has the same structure (each string represents an information about the argument, and the order of the
     * strings is important):
     * <ol>
     *     <li>argument name</li>
     *     <li>package name</li>
     *     <li>type name</li>
     * </ol>
     * As an example, consider the method: {@code public callable(Type1 arg1, Type2 arg2){ [callable code] }}.
     * The method will produce the following list of triplets:
     * {@code [
     *      ["arg1", "package_name_of_arg1", "type_name_of_arg1"],
     *      ["arg2", "package_name_of_arg2", "type_name_of_arg2"]
     * ]}
     * where
     * <ul>
     *     <li>*package_name_of_arg1* is the package name of the class of arg1</li>
     *     <li>*type_name_of_arg1* is the type name (i.e. the class name) of arg1</li>
     * </ul>
     * The method returns the list of triplets collected.
     * @param jpClass A JavaParser type declaration {@link TypeDeclaration}, representing the class where the JavaParser
     *                callable declaration has been defined.
     * @param jpPackage A JavaParser package declaration {@link PackageDeclaration}, representing the package where the
     *                  {@code jpClass} passed to the function is defined.
     * @param jpCallable A JavaParser callable declaration {@link CallableDeclaration}, representing the candidate
     *                   constructor or method of the given class, from which to extract the arguments name, package,
     *                   and type.
     * @return A list of triplets of strings {@link Triplet<String,String,String>}, containing the name, the package, and
     * the type of each argument of the JavaParser callable declaration {@code jpCallable} passed to the function. 
     */
    public static List<Triplet<String, String, String>> getArgumentsNameAndPackageAndTypeFromCallableDeclaration(
            TypeDeclaration<?> jpClass,
            PackageDeclaration jpPackage,
            CallableDeclaration<?> jpCallable
    ) {
        // Define the list of triplets [argumentName, packageName, typeName]
        List<Triplet<String, String, String>> tripletList = new ArrayList<>();
        // Get the package name (the path of the package)
        String packageName = jpPackage.getNameAsString();
        // Add the triplet [parameterName, parameterType, packageName]
        tripletList.addAll(
            jpCallable
                .getParameters()
                .stream()
                .map(p -> new Triplet<>(p.getNameAsString(), packageName, TypeUtils.getJPTypeName(jpClass, jpCallable, p)))
                .toList()
        );
        // Return the list of triplets collected
        return tripletList;
    }

    /**
     * The method gets the Javadoc comment associated to a JavaParser callable declaration {@link ConstructorDeclaration}
     * (i.e. a method or a constructor), if present. The method returns a string representing the Javadoc comment of the 
     * constructor, if found, otherwise it returns an empty string.
     *
     * @param jpCallable A JavaParser callable declaration {@link CallableDeclaration}, representing the candidate
     *                   constructor or method of the given class, from which to extract the arguments name, package,
     *                   and type.
     * @return A string representation of the Javadoc comment associated to the JavaParser callable declaration
     * {@link CallableDeclaration}. The string is empty if the Javadoc comment is not present or found.
     */
    public static String getJavadocCommentFromConstructorOrMethodDeclaration(
            CallableDeclaration<?> jpCallable
    ) {
        // Extract Javadoc comment from the constructor with JavaParser (if present)
        Optional<JavadocComment> jpJavadocComment = jpCallable.getJavadocComment();
        // Check if the Javadoc comment is present
        if (jpJavadocComment.isEmpty()) {
            // If empty, try to extract it with a manual approach
            return extractJavadocCommentByHeuristics(jpCallable);
        }
        // Return the
        return Javadoc.METHOD_PREFIX.getValue() + jpJavadocComment.get().getContent() + Javadoc.METHOD_SUFFIX.getValue();
    }

    /**
     * The method finds all the numeric and string values defined within a string representation of a Javadoc comment, 
     * and it returns them as a list of pairs of strings {@link Pair<String,String>}.
     * For each pair:
     * <ul>
     *     <li>If the pair refers to a numeric value, the first element of each pair is the string representation of the
     *     numeric value, while the second element is the type of the numeric value {@link JavadocValueType}
     *     (integer or real).</li>
     *     <li>If the pair refers to a string value, the first element of each pair is the string value found within
     *     the Javadoc comment, while the second element is the type of the value {@link JavadocValueType} (always
     *     "string").</li>
     * </ul>
     * The method returns the list of pairs collected. The list is empty if it does not contain numeric or string values.
     * @param jpCallableJavadoc A string {@link String} representing the Javadoc comment of a JavaParser callable 
     *                          declaration {@link CallableDeclaration} (i.e. a method or a constructor).
     * @return A list of pairs of strings {@link Pair<String,String>}, representing the numeric and string values present
     * in the Javadoc comment of the JavaParser callable declaration {@link CallableDeclaration} (i.e. a method or
     * constructor) to which the string representation passed to the function {@code jpCallableJavadoc} refers. For each
     * pair in the list, the first element is the string representation of the value (numeric or string), while
     * the second element represents the type of the value collected ({@link JavadocValueType} - integer, real, or string).
     */
    public static List<Pair<String, String>> getJavadocValuesFromCallableJavadocString(
            String jpCallableJavadoc
    ) {
        // Define the list of pair values of type [valueName, valueType]
        List<Pair<String, String>> pairList = new ArrayList<>();
        // Collect the numeric values present in the javadoc comment
        pairList.addAll(findAllNumericValuesInJavadocComment(jpCallableJavadoc));
        // Collect the string values present in the javadoc comment
        pairList.addAll(findAllStringValuesInJavadocComment(jpCallableJavadoc));
        return pairList;
    }

    /**
     * The method gets the string {@link String} representation of the source code of the JavaParser callable declaration
     * {@link CallableDeclaration} (i.e. a method or a constructor) passed to the function. Indeed, in JavaParser, both
     * constructors {@link ConstructorDeclaration} and methods {@link MethodDeclaration} extends from
     * {@link CallableDeclaration}.
     * @param jpCallable An instance of an object that extends the {@link CallableDeclaration} type, like for example,
     *                   A method declaration {@link MethodDeclaration} or a constructor declaration
     *                   {@link ConstructorDeclaration}
     * @param jpCallableType An instance of {@link JPCallableType}, that identify the type of the JavaParser callable
     *                       declaration passed to the function {@code jpCallable}. Its value can be 'METHOD' or
     *                       'CONSTRUCTOR'.
     * @return The string representation of the source code of the JavaParser callable declaration
     * {@link CallableDeclaration} (i.e. a method or a constructor) passed to the function.
     */
    public static String getSourceCodeFromCallableDeclaration(
            CallableDeclaration<?> jpCallable,
            JPCallableType jpCallableType
    ) {
        // Get the signature of the constructor or method
        Signature jpSignature = jpCallable.getSignature();
        // Define the string that represents the source code of the constructor or method, and add
        // the signature to the constructor or method
        String jpCallableSourceCode = jpSignature.toString();
        // Get the body of the constructor or method, if present
        Optional<BlockStmt> jpBody = jpCallableType == JPCallableType.CONSTRUCTOR ? Optional.ofNullable(((ConstructorDeclaration) jpCallable).getBody()) : ((MethodDeclaration) jpCallable).getBody();
        // Add the body to the string that represents the source code of the constructor or method.
        // If the class is an interface, the constructor or method is just a declaration, therefore
        // it ends with ";" without a body, while if it is a concrete class it has an implementation,
        // i.e. a body
        jpCallableSourceCode += jpBody.isEmpty() ? ";" : jpBody.toString();
        // Return the string representing the source code of the constructor or method
        return jpCallableSourceCode;
    }

    /**
     * The method gets the string {@link String} representation of the source code of a JavaParser constructor declaration 
     * {@link ConstructorDeclaration}. The method searches a class within the compilation unit {@link CompilationUnit} 
     * passed to the function, that defines a constructor {@link ConstructorDeclaration} with the same number and type 
     * name of parameters defined by the list of JDoctor condition parameter type names passed to the function 
     * {@code jDoctorConditionParamTypeNames}. If the class is found and the corresponding constructor is found, the 
     * method returns a string representation of its source code.
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @param jDoctorConditionParamTypeNames A list of strings, representing the type names of the arguments of the
     *                                       constructor to find within the given compilation unit {@link CompilationUnit}
     * @return A string representation of the source code of the constructor, if found. The method throws an exception if
     * the constructor is not found.
     * @throws PrimaryTypeNotFoundException If the compilation does not have a primary class {@link TypeDeclaration} (in
     * other words, the compilation unit does not have a class defined within it).
     * @throws JPConstructorNotFound If the constructor is not found within the given class {@link TypeDeclaration}.
     */
    public static String getSourceCodeFromConstructorDeclaration(
            CompilationUnit cu,
            List<String> jDoctorConditionParamTypeNames
    ) throws PrimaryTypeNotFoundException, JPConstructorNotFound {
        // Search for a match between all the JavaParser constructors defined within the primary class, and the
        // JDoctor condition constructor analyzed
        ConstructorDeclaration jpConstructor = getConstructorDeclaration(cu, jDoctorConditionParamTypeNames);
        // Return the source code string of the constructor
        return getSourceCodeFromCallableDeclaration(jpConstructor, JPCallableType.CONSTRUCTOR);
    }

    /**
     * The method searches a JavaParser method declaration {@link MethodDeclaration} within the given class {@code jpClass},
     * with the same number and type name of parameters defined by the list of JDoctor condition parameter type names passed
     * to the function {@code jDoctorConditionParamTypeNames}. If the method declaration is found, the method returns the
     * JavaParser method declaration {@link MethodDeclaration}. Otherwise, the method throws an exception
     * {@link JPMethodNotFound}.
     *
     * @param jpClass An instance of a JavaParser type declaration {@link TypeDeclaration} representing the class where
     *                to find the constructor corresponding to the one to which the JDoctor condition refers.
     * @param methodName A string representing the name of the method to find within the given class {@code jpClass}
     * @param jDoctorConditionParamTypeNames The list of strings, representing the type names of the arguments of the
     *                                       constructor to find within the given class {@link TypeDeclaration}.
     * @return The JavaParser method declaration {@link MethodDeclaration} corresponding to the one to which the JDoctor
     * condition refers. The method throws an exception if the method is not found.
     * @throws JPMethodNotFound If the method analyzed is not found within the JavaParser type declaration
     * {@link TypeDeclaration} passed to the function.
     */
    public static MethodDeclaration getMethodDeclaration(
            TypeDeclaration<?> jpClass,
            String methodName,
            List<String> jDoctorConditionParamTypeNames
    ) throws JPMethodNotFound {
        // Get the list of methods of the JavaParser class
        List<? extends CallableDeclaration<?>> jpCallableList = jpClass.getMethodsByName(methodName);
        // Convert condition parameters types strings in the format of the JavaParser types strings
        jDoctorConditionParamTypeNames = convertJDoctorConditionTypeNames2JavaParserTypeNames(jDoctorConditionParamTypeNames);
        // Search the JavaParser method that matches the method of the JDoctor condition analyzed
        Optional<CallableDeclaration<?>> jpMethod = getCallableDeclarationFromTypeDeclaration(
                jpClass,
                jDoctorConditionParamTypeNames,
                jpCallableList
        );
        if (jpMethod.isPresent()) {
            return (MethodDeclaration) jpMethod.get();
        }
        // Raise an exception if the method has not been found
        String errMsg = String.format("The class passed to the function does not have a method that matches the condition method.");
        throw new JPMethodNotFound(errMsg);
    }

    /**
     * The method tries to find the corresponding JavaParser field declaration AST node {@link FieldDeclaration} from a
     * SymbolSolver resolved field declaration {@link ResolvedFieldDeclaration}, passed to the function. The method
     * returns an optional JavaParser field declaration {@link FieldDeclaration}. The optional contains an instance of
     * JavaParser field declaration if the method find a match between SymbolSolver and JavaParser nodes, otherwise,
     * it returns an empty optional.
     * @param project The project under analysis.
     * @param jpResolvedFieldDeclaration The SymbolSolver resolved field declaration {@link ResolvedFieldDeclaration} to
     *                                   be converted into a JavaParser field declaration {@link FieldDeclaration}.
     * @return An optional JavaParser field declaration {@link FieldDeclaration}. The optional contains an instance of
     * JavaParser field declaration if the method find a match between the SymbolSolver and JavaParser nodes, otherwise,
     * it returns an empty optional.
     */
    public Optional<FieldDeclaration> getFieldDeclarationFromResolvedFieldDeclaration(
        Project project,
        ResolvedFieldDeclaration jpResolvedFieldDeclaration
    ) {
        Optional<Node> node = jpResolvedFieldDeclaration.toAst();

        if (node.isPresent() && node.get() instanceof FieldDeclaration) {
            return Optional.of((FieldDeclaration) node.get());
        } else {
            // Try to find the TypeDeclaration from the qualified name
            // Get the qualified name
            String qualifiedName= jpResolvedFieldDeclaration.declaringType().getQualifiedName();
            // Search the compilation unit from the qualified name
            Optional<CompilationUnit> cu = getCompilationUnitFromQualifiedName(qualifiedName, project);
            // Check if the compilation unit has been found
            if (cu.isPresent()) {
                // The primary type, if present, must correspond to the class where the field has been declared
                Optional<TypeDeclaration<?>> jpClass = cu.get().getPrimaryType();
                // Check if the primary type has been found
                if (jpClass.isPresent()) {
                    // Search a field with the same name
                    Optional<FieldDeclaration> jpField = jpClass.get().getFieldByName(jpResolvedFieldDeclaration.getName());
                    // Return the optional field declaration or empty
                    return jpField;
                }
            }
        }
        // Return an optional empty if the field has not been found
        return Optional.empty();
    }

    /**
     * The method tries to find the corresponding JavaParser method declaration AST node {@link MethodDeclaration} from a
     * SymbolSolver method usage {@link MethodUsage}, passed to the function. The method returns an optional JavaParser
     * method declaration {@link MethodDeclaration}. The optional contains an instance of JavaParser method declaration
     * if the method find a match between SymbolSolver and JavaParser nodes, otherwise, it returns an empty optional.
     * @param project The project under analysis.
     * @param jpMethodUsage The SymbolSolver method usage {@link MethodUsage} to be converted into a JavaParser
     *                      method declaration {@link MethodDeclaration}.
     * @return An optional JavaParser method declaration {@link MethodDeclaration}. The optional contains an instance of
     * JavaParser method declaration if the method find a match between the SymbolSolver and JavaParser nodes, otherwise,
     * it returns an empty optional.
     */
    public Optional<MethodDeclaration> getMethodDeclarationFromMethodUsage(
            Project project,
            MethodUsage jpMethodUsage
    ) {
        ResolvedMethodDeclaration jpResolvedMethodDeclaration = jpMethodUsage.getDeclaration();
        return getMethodDeclarationFromResolvedMethodDeclaration(project, jpResolvedMethodDeclaration);
    }

    /**
     * The method tries to find the corresponding JavaParser method declaration AST node {@link MethodDeclaration} from a
     * SymbolSolver resolved method declaration {@link ResolvedMethodDeclaration}, passed to the function. The method
     * returns an optional JavaParser method declaration {@link MethodDeclaration}. The optional contains an instance of
     * JavaParser method declaration if the method find a match between SymbolSolver and JavaParser nodes, otherwise,
     * it returns an empty optional.
     * @param project The project under analysis.
     * @param jpResolvedMethodDeclaration The SymbolSolver resolved method declaration {@link ResolvedMethodDeclaration}
     *                                    to be converted into a JavaParser method declaration {@link MethodDeclaration}.
     * @return An optional JavaParser method declaration {@link MethodDeclaration}. The optional contains an instance of
     * JavaParser method declaration if the method find a match between the SymbolSolver and JavaParser nodes, otherwise,
     * it returns an empty optional.
     */
    public Optional<MethodDeclaration> getMethodDeclarationFromResolvedMethodDeclaration(
            Project project,
            ResolvedMethodDeclaration jpResolvedMethodDeclaration
    ) {
        Optional<Node> node = jpResolvedMethodDeclaration.toAst();

        if (node.isPresent() && node.get() instanceof MethodDeclaration) {
            return Optional.of((MethodDeclaration) node.get());
        } else {
            // Try to find the MethodDeclaration from the qualified name
            // Get the qualified name
            String qualifiedName= jpResolvedMethodDeclaration.declaringType().getQualifiedName();
            // Search the compilation unit from the qualified name
            Optional<CompilationUnit> cu = getCompilationUnitFromQualifiedName(qualifiedName, project);
            // Check if the compilation unit has been found
            if (cu.isPresent()) {
                // The primary type, if present, must correspond to the class where the field has been declared
                Optional<TypeDeclaration<?>> jpClass = cu.get().getPrimaryType();
                // Check if the primary type has been found
                if (jpClass.isPresent()) {
                    // Search a method with the same name
                    List<MethodDeclaration> jpCandidateMethods = jpClass.get().getMethodsByName(jpResolvedMethodDeclaration.getName());
                    for(MethodDeclaration jpCandidateMethod: jpCandidateMethods) {
                        List<Parameter> jpParams = jpCandidateMethod.getParameters();
                        boolean found = true;
                        if (jpParams.size() == jpResolvedMethodDeclaration.getNumberOfParams()) {
                            for(int i=0; i < jpParams.size(); i++) {
                                Parameter jpParam = jpParams.get(i);
                                String resolvedMethodParamDescriptor = jpResolvedMethodDeclaration.getParam(i).getType().describe();
                                String resolvedMethodParamType = extractTypeNameFromQualifiedName(resolvedMethodParamDescriptor);
                                if (!jpParam.getType().toString().equals(resolvedMethodParamType)) {
                                    found = false;
                                    break;
                                }
                            }
                            if (found) {
                                return Optional.of(jpCandidateMethod);
                            }
                        }
                    }
                    return Optional.empty();
                }
            }
        }
        return Optional.empty();
    }

    /**
     * The method searches a class within the JavaParser compilation unit {@link CompilationUnit} passed to the function,
     * that defines a method {@link MethodDeclaration} with the same number and type name of parameters defined by
     * the list of JDoctor condition parameter type names passed to the function {@code jDoctorConditionParamTypeNames}.
     * If the class is found and the corresponding method is found, the method returns the JavaParser method declaration
     * {@link MethodDeclaration}. Otherwise, the method throws an exception {@link JPMethodNotFound}.
     *
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @param methodName A string {@link String} representing the name of the method to find.
     * @param jDoctorConditionParamTypeNames The list of strings, representing the type names of the arguments of the
     *                                       method to find within the given compilation unit {@link CompilationUnit}.
     * @return The JavaParser method declaration {@link MethodDeclaration} corresponding to the one to which
     * the JDoctor condition refers within the compilation unit.
     * @throws PrimaryTypeNotFoundException If the compilation does not have a primary class {@link TypeDeclaration} (in
     * other words, the compilation unit does not have a class defined within it).
     * @throws JPMethodNotFound If the method is not found within the given JavaParser compilation unit
     * {@link CompilationUnit}.
     */
    public static MethodDeclaration getMethodDeclaration(
            CompilationUnit cu,
            String methodName,
            List<String> jDoctorConditionParamTypeNames
    ) throws PrimaryTypeNotFoundException, JPMethodNotFound {
        // Get the list of all the classes defined within the compulation unit: the first one is the primary type
        List<TypeDeclaration<?>> jpClasses = findAllTypeDeclarationsFromCompilationUnit(cu);
        // Convert condition parameters types strings in the format of the JavaParser types strings
        jDoctorConditionParamTypeNames = convertJDoctorConditionTypeNames2JavaParserTypeNames(jDoctorConditionParamTypeNames);
        // Iterate over the classes until the class that defines the method is found
        for (TypeDeclaration<?> jpClass: jpClasses) {
            try {
                return getMethodDeclaration(jpClass, methodName, jDoctorConditionParamTypeNames);
            } catch (JPMethodNotFound e) {
                continue;
            }
        }
        // Raise an exception if the method has not been found
        String errMsg = String.format("The compilation unit passed to the function does not have a method that matches the condition method.");
        throw new JPMethodNotFound(errMsg);
    }

    /**
     * The method collects all the arguments names, packages, and types of a method, in the form of a list of 
     * triplets of strings {@link Triplet<String,String,String>}. The method searches a class within the compilation 
     * unit {@link CompilationUnit} passed to the function, that defines a method {@link MethodDeclaration}
     * with the same number and type name of parameters defined by the list of JDoctor condition parameter type names 
     * passed to the function {@code jDoctorConditionParamTypeNames}. If the class is found and the corresponding 
     * method is found, the function analyzes all the arguments of the method and generates the corresponding triplets.
     * Each triplet has the same structure (each string represents an information about the argument, and the order of the
     * strings is important):
     * <ol>
     *     <li>argument name</li>
     *     <li>package name</li>
     *     <li>type name</li>
     * </ol>
     * As an example, consider the method: {@code public Method(Type1 arg1, Type2 arg2){ [method code] }}.
     * The method will produce the following list of triplets:
     * {@code [
     *      ["arg1", "package_name_of_arg1", "type_name_of_arg1"],
     *      ["arg2", "package_name_of_arg2", "type_name_of_arg2"]
     * ]}
     * where
     * <ul>
     *     <li>*package_name_of_arg1* is the package name of the class of arg1</li>
     *     <li>*type_name_of_arg1* is the type name (i.e. the class name) of arg1</li>
     * </ul>
     * The method returns the list of triplets collected.
     *
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @param methodName The name of the method to find within the JavaParser compilation unit {@link CompilationUnit}
     * @param className The name of the class where the method has been defined.
     * @param jDoctorConditionParamTypeNames The list of strings, representing the type names of the arguments of the
     *                                       method to find within the given JavaParser compilation unit
     *                                       {@link CompilationUnit}.
     * @return A list of triplets of strings {@link Triplet<String,String,String>}, where each triplet contains the
     * argument name, package name, and type name of the constructor to which the JDoctor condition refers within the
     * compilation unit {@link CompilationUnit} passed to the function.
     * @throws PrimaryTypeNotFoundException If the compilation does not have a primary class {@link TypeDeclaration} (in
     * other words, the compilation unit does not have a class defined within it).
     * @throws JPMethodNotFound If the method is not found within the given JavaParser compilation unit {@link CompilationUnit}.
     * @throws PackageDeclarationNotFoundException If the compilation does not have a package declaration
     * {@link PackageDeclaration}.
     * @throws JPClassNotFoundException If the method does not find a class with a constructor corresponding to the list
     * of JDoctor condition parameter type names passed to the function.
     */
    public static List<Triplet<String, String, String>> getArgumentsNameAndPackageAndTypeFromMethodDeclaration(
            CompilationUnit cu,
            String methodName,
            String className,
            List<String> jDoctorConditionParamTypeNames
    ) throws PrimaryTypeNotFoundException, JPMethodNotFound, JPConstructorNotFound, PackageDeclarationNotFoundException, JPClassNotFoundException {
        // Get the class of the compilation unit where the method has been defined
        TypeDeclaration<?> jpClass = getTypeDeclarationFromMethodJDoctorConditionParamTypeNames(cu, methodName, className, jDoctorConditionParamTypeNames);
        // Get the package declaration of the compilation unit
        PackageDeclaration jpPackage = getPackageDeclarationFromCompilationUnit(cu);
        // Search for a match between all the JavaParser methods defined within the class, and the
        // JDoctor condition method analyzed
        MethodDeclaration jpMethod = getMethodDeclaration(jpClass, methodName, jDoctorConditionParamTypeNames);
        // Return the string representing the javadoc of the constructor
        return getArgumentsNameAndPackageAndTypeFromCallableDeclaration(jpClass, jpPackage, jpMethod);
    }

    /**
     * The method gets the Javadoc comment associated to a method declaration {@link MethodDeclaration}, if
     * present. The method searches a class within the compilation unit {@link CompilationUnit} passed to the function,
     * that defines a method {@link MethodDeclaration} with the same number and type name of parameters defined
     * by the list of JDoctor condition parameter type names passed to the function {@code jDoctorConditionParamTypeNames}.
     * If the class is found and the corresponding method is found, the function searches if there is a Javadoc Comment
     * associated to the method. The method returns a string representing the Javadoc comment of the method, if
     * found, otherwise it returns an empty string.
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @param methodName A string {@link String} representing the name of the method to find within the JavaParser
     *                   compilation unit {@link CompilationUnit} passed to the function.
     * @param jDoctorConditionParamTypeNames A list of strings, representing the type names of the arguments of the
     *                                       method to find within the given JavaParser compilation unit
     *                                       {@link CompilationUnit}.
     * @return A string representing the Javadoc Comment of the method {@link MethodDeclaration}, if present.
     * The string returned by the function is empty if the Javadoc comment is not present or found.
     * @throws PrimaryTypeNotFoundException If the compilation does not have a primary class {@link TypeDeclaration} (in
     * other words, the compilation unit does not have a class defined within it).
     * @throws JPMethodNotFound If the method is not found within the given JavaParser compilation unit
     * {@link CompilationUnit}.
     */
    public static String getJavadocCommentFromMethodDeclaration(
            CompilationUnit cu,
            String methodName,
            List<String> jDoctorConditionParamTypeNames
    ) throws PrimaryTypeNotFoundException, JPMethodNotFound {
        // Search for a match between all the JavaParser methods defined within the primary class, and the
        // JDoctor condition method analyzed
        MethodDeclaration jpMethod = getMethodDeclaration(cu, methodName, jDoctorConditionParamTypeNames);
        // Return the string representing the javadoc of the method
        return getJavadocCommentFromConstructorOrMethodDeclaration(jpMethod);
    }

    /**
     * The method finds all the string values present within a Javadoc comment, and it returns them as a list of pairs
     * of strings {@link Pair<String,String>}.
     * The method searches a class within the compilation unit {@link CompilationUnit} passed to the function,
     * that defines a method {@link MethodDeclaration} with the same number and type name of parameters defined
     * by the list of JDoctor condition parameter type names passed to the function {@code jDoctorConditionParamTypeNames}.
     * If the class is found and the corresponding method is found, the method searches if there is a Javadoc Comment
     * associated to the method and collects all the pairs of numeric and string values defined within it. For each
     * pair:
     * <ul>
     *     <li>If the pair refers to numeric value, the first element of each pair is the string representation of the
     *     numeric value, while the second element is the type of the numeric value {@link JavadocValueType}
     *     (integer or real).</li>
     *     <li>If the pair refers to a string value, the first element of each pair is the string value found within
     *     the Javadoc comment, while the second element is the type of the value {@link JavadocValueType} (always
     *     "string").</li>
     * </ul>
     * The method returns the list of pairs collected. The list is empty if the Javadoc comment is not present, or it
     * does not contain numeric or string values.
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @param methodName A string {@link String} representing the name of the method to find within the JavaParser
     *                   compilation unit {@link CompilationUnit} passed to the function.
     * @param jDoctorConditionParamTypeNames A list of strings, representing the type names of the arguments of the
     *                                       constructor to find within the given compilation unit {@link CompilationUnit}.
     * @return A list of pairs of strings {@link Pair<String,String>}, representing the numeric and string values present
     * in the Javadoc comment of the method to which the JDoctor condition parameters type names refer to. For each
     * pair in the list, the first element is the string representation of the value (numeric or string), while
     * the second element represents the type of the value collected ({@link JavadocValueType} - integer, real, or string).
     * @throws PrimaryTypeNotFoundException If the compilation does not have a primary class {@link TypeDeclaration} (in
     * other words, the compilation unit does not have a class defined within it).
     * @throws JPMethodNotFound If the method is not found within the given JavaParser compilation unit
     * {@link CompilationUnit}.
     */
    public static List<Pair<String, String>> getJavadocValuesFromMethodDeclaration(
            CompilationUnit cu,
            String methodName,
            List<String> jDoctorConditionParamTypeNames
    ) throws PrimaryTypeNotFoundException, JPMethodNotFound {
        // Get the string representing the javadoc of the method
        String methodJavadoc = getJavadocCommentFromMethodDeclaration(cu, methodName, jDoctorConditionParamTypeNames);
        // Return the list of pairs of type [valueName, valueType]
        return getJavadocValuesFromCallableJavadocString(methodJavadoc);
    }

    /**
     * The method gets the string {@link String} representation of the source code of a JavaParser method declaration 
     * {@link MethodDeclaration}. The method searches a class within the compilation unit {@link CompilationUnit}
     * passed to the function, that defines a method {@link MethodDeclaration} with the same number and type 
     * name of parameters defined by the list of JDoctor condition parameter type names passed to the function 
     * {@code jDoctorConditionParamTypeNames}. If the class is found and the corresponding method is found, the 
     * method returns a string representation of its source code.
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @param jDoctorConditionParamTypeNames A list of strings, representing the type names of the arguments of the
     *                                       method to find within the given compilation unit {@link CompilationUnit}
     * @return A string representation of the source code of the method, if found. The method throws an exception if
     * the method is not found {@link JPMethodNotFound}.
     * @throws PrimaryTypeNotFoundException If the compilation does not have a primary class {@link TypeDeclaration} (in
     * other words, the compilation unit does not have a class defined within it).
     * @throws JPMethodNotFound If the method is not found within the given class {@link TypeDeclaration}.
     */
    public static String getSourceCodeFromMethodDeclaration(
            CompilationUnit cu,
            String methodName,
            List<String> jDoctorConditionParamTypeNames
    ) throws PrimaryTypeNotFoundException, JPMethodNotFound, JPConstructorNotFound {
        // Search for a match between all the JavaParser methods defined within the primary class, and the
        // JDoctor condition method analyzed
        MethodDeclaration jpMethod = getMethodDeclaration(cu, methodName, jDoctorConditionParamTypeNames);
        // Return the source code string of the method
        return getSourceCodeFromCallableDeclaration(jpMethod, JPCallableType.METHOD);
    }

    /**
     * The method gets the JavaParser package declaration {@link PackageDeclaration} of the compilation unit passed
     * to the function.
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @return The JavaParser package declaration associated to the compilation unit {@link CompilationUnit} passed to 
     * the function. The method throws an exception if the package is not found.
     * @throws PackageDeclarationNotFoundException If the package is not found.
     */
    public static PackageDeclaration getPackageDeclarationFromCompilationUnit(
            CompilationUnit cu
    ) throws PackageDeclarationNotFoundException {
        // Get JavaParser package of the compilation unit: it must be the package of the primary class of the file
        Optional<PackageDeclaration> jpPackage = cu.getPackageDeclaration();
        // Check if the package has been found
        if (jpPackage.isEmpty()) {
            // Raise an exception if the package has not been found
            String errMsg = String.format("The JavaParser package declaration of the compilation unit is empty");
            throw new PackageDeclarationNotFoundException(errMsg);
        }
        // Return the JavaParser package
        return jpPackage.get();
    }

    /**
     * The method gets the JavaParser type declaration {@link TypeDeclaration} corresponding to the primary class of the 
     * compilation unit passed to the function. The primary class is the class that has the same name of the file used to
     * instantiate the JavaParser compilation unit {@link CompilationUnit}.
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @return The JavaParser package declaration associated to the compilation unit {@link CompilationUnit} passed to 
     * the function. The method throws an exception if the package is not found.
     * @throws PrimaryTypeNotFoundException If the compilation does not have a primary class {@link TypeDeclaration} (in
     * other words, the compilation unit does not have a class defined within it).
     */
    public static TypeDeclaration<?> getPrimaryClassFromCompilationUnit(
            CompilationUnit cu
    ) throws PrimaryTypeNotFoundException {
        Optional<TypeDeclaration<?>> primaryType = cu.getPrimaryType();
        // Check that the primary type of the compilation unit is not empty
        if (primaryType.isEmpty()) {
            // Raise an exception if the primary type is null
            String errMsg = String.format("The compilation unit passed to the function does not have a primary type");
            throw new PrimaryTypeNotFoundException(errMsg);
        }
        // Get the primary class of the JavaParser compilation unit: it must correspond
        // to the class of the condition analyzed
        TypeDeclaration<?> jpClass = primaryType.get();
        // Return the JavaParser primary class
        return jpClass;
    }

    /**
     * The method collects the names and the package names of the classes defined within the JavaParser CompilationUnit 
     * {@link CompilationUnit} passed to the function, as a list of pairs of strings {@link Pair<String,String>}.
     * The method generates a pair of strings, for each attribute.
     * Each pair has the same structure (each string represents an information about the class, and the order of the
     * strings is important):
     * <ol>
     *     <li>class name</li>
     *     <li>package name</li>
     * </ol>
     * The method returns the list of collected pairs.
     * 
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @return A list of pairs of strings {@link Pair<String,String>}, each one representing the class name and the package
     * name of a class defined within the JavaParser compilation unit {@link CompilationUnit} passed to the function.
     * @throws PackageDeclarationNotFoundException If the package is not found.
     */
    public static List<Pair<String, String>> getClassesNamesAndPackagesFromCompilationUnit(
            CompilationUnit cu
    ) throws PackageDeclarationNotFoundException {
        List<Pair<String, String>> pairList = new ArrayList<>();
        // Extract and print the name and package of each class
        List<TypeDeclaration<?>> jpClasses = cu.getTypes();
        // Get the JavaParser package: the package name is shared among the classes defined within the compilation unit
        PackageDeclaration jpPackage = getPackageDeclarationFromCompilationUnit(cu);
        // Get the package name
        String packageName = jpPackage.getNameAsString();
        // Iterate over all the classes defined within the compilation unit
        for (TypeDeclaration<?> jpClass : jpClasses) {
            // Get the class name
            String className = jpClass.getNameAsString();
            // Add the pair [className, packageName]
            pairList.add(new Pair<>(className, packageName));
        }
        // Return the list of pairs of type [className, packageName]
        return pairList;
    }

    /**
     * The method collects all the JavaParser method declarations {@link MethodDeclaration} declared within the classes
     * {@link TypeDeclaration} of the JavaParser compilation unit {@link CompilationUnit}, as quartets of strings
     * {@link Quartet<String,String,String,String>}. A method is collected if:
     * <ol>
     *     <li>The method is not private (public or protected)</li>
     *     <li>The method is non-static</li>
     *     <li>The return type is non-void</li>
     * </ol>
     * For example, consider the following methods: 
     *      {@code public int methodName1(int param1, int param2) { [method code] };}
     *      {@code public int methodName2(int param3, int param4) { [method code] };}
     * defined within a class of the compilation unit passed to the function.
     * The corresponding quartet will have the following structure (each string represents an information about the method, 
     * and the order of the strings is important):
     * <ol>
     *     <li>method name</li>
     *     <li>package name</li>
     *     <li>class name</li>
     *     <li>method signature</li>
     * </ol>
     * Considering the previous example, the method will produce the following list of quartets:
     * {@code [
     *      ["methodName1", "package_name_of_method", "class_name_of_method", "public int methodName1(int param1, int param2);"],
     *      ["methodName2", "package_name_of_method", "class_name_of_method", "public int methodName2(int param3, int param4);"]
     * ]}
     * where
     * <ul>
     *     <li>*package_name_of_method* is the package name of the class where the method is declared</li>
     *     <li>*class_name_of_method* is the class name of the class where the method is declared</li>
     * </ul>
     * The method returns the list of collected pairs.
     * 
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @return The quartets of strings {@link Quartet<String,String,String,String>}, representing the methods declared
     * within the classes defined within the JavaParser compilation unit {@link CompilationUnit} passed to the function.
     * The methods collected are non-private, non-static, non-void.
     * @throws PackageDeclarationNotFoundException If the package is not found.
     */
    public static List<Quartet<String, String, String, String>> getClassesMethodsNonPrivateNonStaticNonVoidFromCompilationUnit(
            CompilationUnit cu
    ) throws PackageDeclarationNotFoundException {
        // Define the list of quartets of type [methodName, packageName, className, methodSignature]
        List<Quartet<String, String, String, String>> quartetList = new ArrayList<>();
        // Traverse all the ClassOrInterfaceDeclaration nodes in the CompilationUnit
        List<TypeDeclaration<?>> classes = cu.getTypes();
        // Get the JavaParser package: the package name is shared among the classes defined within the compilation unit
        PackageDeclaration jpPackage = getPackageDeclarationFromCompilationUnit(cu);
        // Get the package name
        String packageName = jpPackage.getNameAsString();
        // Iterate over all the classes defined within the compilation unit
        for (TypeDeclaration<?> jpClass : classes) {
            // Get the class name
            String className = jpClass.getNameAsString();
            // Traverse all the MethodDeclaration nodes in the ClassOrInterfaceDeclaration
            List<MethodDeclaration> methods = jpClass.findAll(MethodDeclaration.class);
            // Iterate over each method of the class
            for (MethodDeclaration method : methods) {
                // Get the return type of the method
                Type returnType = method.getType();
                // Check if the method is not private, is static, and the return value is not void
                if (!method.isPrivate() && method.isStatic() && !returnType.isVoidType()) {
                    // Get the name and signature of the MethodDeclaration
                    String methodName = method.getNameAsString();
                    // Get the signature of the method
                    String methodSignature = getSignatureFromMethodDeclaration(method);
                    // Add the quartet [methodName, packageName, className, methodSignature]
                    quartetList.add(new Quartet<>(methodName, packageName, className, methodSignature));
                }
            }
        }
        // Return the list of pairs of type [methodName, packageName, methodSignature]
        return quartetList;
    }

    /**
     * The method collects all the attributes (i.e. variables defined in the JavaParser field declarations 
     * {@link FieldDeclaration}) declared within the classes {@link TypeDeclaration} of the JavaParser compilation unit 
     * {@link CompilationUnit}, as quartets of strings {@link Quartet<String,String,String,String>}.
     * The method collects an attribute only if:
     * <ol>
     *     <li>The attirbute is non-private (public or protected)</li>
     *     <li>The attribute is static</li>
     * </ol>
     * Indeed, each JavaParser field declaration corresponds to a statement where one or more attributes (variables) are 
     * defined. For example: {@code public static int x = 1,y = 2;} is a field where {@code x} and {@code y} are the 
     * attributes defined and initialized. The method generates a quartet of strings, for each attribute.
     * Each quartet has the same structure (each string represents an information about the field, and the order of the
     * strings is important):
     * <ol>
     *     <li>attribute name</li>
     *     <li>package name</li>
     *     <li>class name</li>
     *     <li>attribute signature</li>
     * </ol>
     * Considering the previous example, given the JavaParser field declaration corresponding to the statement
     * {@code public static int x = 1,y = 2;}, the method will produce the list of quartets:
     * {@code [
     *      ["x", "package_name_of_field", "class_name_of_field", "public static int x = 1;"],
     *      ["y", "package_name_of_field", "class_name_of_field", "public static int y = 2;"]
     * ]}
     * where
     * <ul>
     *     <li>*package_name_of_field* is the package name of the class where the field is declared</li>
     *     <li>*class_name_of_field* is the class name of the class where the field is declared</li>
     * </ul>
     * The method returns the list of collected quartets.
     *
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @return The quartets of strings {@link Quartet<String,String,String,String>}, representing the attributes declared
     * within the classes defined within the JavaParser compilation unit {@link CompilationUnit} passed to the function.
     * The attributes collected are static and non-private.
     * @throws PackageDeclarationNotFoundException If the package is not found.
     */
    public List<Quartet<String, String, String, String>> getClassesNonPrivateStaticAttributesFromCompilationUnit(
            CompilationUnit cu
    ) throws PackageDeclarationNotFoundException, PrimaryTypeNotFoundException {
        // Define the list of quartets of type [attributeName, packageName, className, attributeSignature]
        List<Quartet<String, String, String, String>> attributeQuartetList = new ArrayList<>();
        // Generate list of attributes features
        List<Pair<FieldFeature, Boolean>> fieldFeatures = new ArrayList<>();
        // The attribute must be non-private
        fieldFeatures.add(new Pair<>(FieldFeature.PRIVATE, Boolean.valueOf(false)));
        // The attribute must be non-static
        fieldFeatures.add(new Pair<>(FieldFeature.STATIC, Boolean.valueOf(true)));
        // Generate predicates to find the attributes that satisfy the given conditions
        List<Predicate<FieldDeclaration>> predicates = FieldFeature.generateFieldDeclarationPredicates(fieldFeatures);
        // Traverse all the ClassOrInterfaceDeclaration nodes in the CompilationUnit
        List<TypeDeclaration<?>> rootCUClasses = findAllTypeDeclarationsFromCompilationUnit(cu);
        // Get the JavaParser package: the package name is shared among the classes defined within the compilation unit
        PackageDeclaration rootCUPackage = getPackageDeclarationFromCompilationUnit(cu);
        // Iterate over all the classes defined within the compilation unit
        for (TypeDeclaration<?> jpClass: rootCUClasses) {
            // Get the class name
            String jpClassName = jpClass.getNameAsString();
            // Iterate over each field of the class
            jpClass.findAll(FieldDeclaration.class)
                    .stream()
                    .forEach(jpField -> {
                        // Check if the field is non-private and static
                        if (checkFieldDeclarationFeaturesCondition(jpField, predicates)) {
                            // Iterate over each variable of the field (es. static int x = 1,y = 2; --> variables are x and y)
                            for (VariableDeclarator jpVariable : jpField.getVariables()) {
                                // Build the signature for each variable ([int x = 1;], [int y = 2;])
                                // alternatively, argumentSignature = field.toString(); will give the same signature
                                // for each variable of the field (static int x = 1,y = 2;);
                                String argumentSignature = getSignatureFromVariableDeclarator(jpField, jpVariable);
                                // Add to the list the quartet [attributeName, packageName, className, attributeSignature]
                                attributeQuartetList.add(new Quartet<>(jpVariable.getNameAsString(), rootCUPackage.getNameAsString(), jpClassName, argumentSignature));
                            }
                        }
                    });
        }
        return attributeQuartetList;
    }

    /**
     * The method gets the JavaParser type declaration {@link TypeDeclaration} corresponding to the class defined within
     * the JavaParser compilation unit {@link CompilationUnit} and with the same name of the {@code classname}, both
     * passed to the funtion.
     * The method returns an optional JavaParser type declaration {@link Optional<TypeDeclaration>}. The optional contains
     * an instance of JavaParser type declaration {@link TypeDeclaration}, if the match is found. Otherwise, the method
     * returns an empty optional.
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @param className The name of the class to match within the JavaParser compilation unit {@link CompilationUnit},
     *                  passed to the function.
     * @return An optional JavaParser type declaration {@link Optional<TypeDeclaration>}. The optional contains
     * an instance of JavaParser type declaration {@link TypeDeclaration}, if the match is found. Otherwise, the method
     * returns an empty optional.
     */
    public static Optional<TypeDeclaration> getTypeDeclarationFromClassName(
            CompilationUnit cu,
            String className
    ) {
        // Get all the classes defined within the compilation unit
        List<TypeDeclaration> allTypes = cu.findAll(TypeDeclaration.class);
        // Filter the classes that have the same name of the class we are looking for
        List<TypeDeclaration> filteredTypes = allTypes
            .stream()
            .filter(jpClass -> className.endsWith(jpClass.getNameAsString()))
            .toList();
        // Assert there is not more than one classes with the same name
        assert filteredTypes.size() > 1 : String.format(
                "Unexpected number of type declarations with the same class name (expected: 1, actual: %s",
                filteredTypes.size()
        );
        // Check if the class has been found
        if (filteredTypes.size() == 1) {
            // Get the class
            TypeDeclaration jpClass = filteredTypes.get(0);
            // Return the class found
            return Optional.of(jpClass);
        }
        return Optional.empty();
    }

    /**
     * Given a syntactically valid Java expression, the maethod evaluates its return type, including package and class.
     * If the expression is not syntactically valid, the method will throw an exception.
     * @param expression The expression to evaluate, e.g., "methodResultID.negate().value(null).getField()"
     * @return The ResolvedType of the whole expression
     */
    public static ResolvedType getReturnTypeOfExpression(
            CompilationUnit cu,
            String methodName,
            String className,
            List<String> jDoctorConditionParamTypeNames,
            List<Triplet<String, String, String>> methodArguments,
            String expression
    ) throws PrimaryTypeNotFoundException, JPMethodNotFound, JPClassNotFoundException, ResolvedTypeNotFound {
        String SYNTHETIC_METHOD_NAME = "__tratto__auxiliaryMethod";
        Optional<TypeDeclaration> jpClass = getTypeDeclarationFromClassName(cu, className);
        if (jpClass.isPresent() && jpClass.get() instanceof ClassOrInterfaceDeclaration) {
            BlockStmt syntheticMethodBody = jpClass.get().addMethod(SYNTHETIC_METHOD_NAME).getBody().get();
            // Add one statement per method argument
            for (Triplet<String, String, String> methodArgument : methodArguments) {
                syntheticMethodBody.addStatement(methodArgument.getValue2() + " " + methodArgument.getValue0() + ";");
            }
            if (!methodName.equals(className)) {
                // Get the JavaParser method declaration
                MethodDeclaration jpMethod = getMethodDeclaration(cu, methodName, jDoctorConditionParamTypeNames);
                String methodReturnType = jpMethod.getType().asString();
                // If the method is not void, add statement to save methodResultID
                if (!"void".equals(methodReturnType)) {
                    syntheticMethodBody.addStatement(methodReturnType + " methodResultID = " + methodName +
                            "(" + methodArguments.stream().map(Triplet::getValue0).collect(Collectors.joining(", ")) + ");");
                }
            }
            // Add last statement where the expression will be evaluated
            syntheticMethodBody.addStatement("var returnType = " + expression + ";");
            // Get return type of expression
            ResolvedType returnType = ((ClassOrInterfaceDeclaration) jpClass.get())
                    .getMethodsByName(SYNTHETIC_METHOD_NAME).get(0)
                    .getBody().get()
                    .getStatements().getLast().get()
                    .asExpressionStmt().getExpression()
                    .asVariableDeclarationExpr().getVariables().get(0)
                    .getInitializer().get()
                    .calculateResolvedType();
            return returnType;
        }
        String errMsg = String.format(
                "ResolvedType of expression %s of class %s and method %s not found",
                expression,
                className,
                methodName
        );
        throw new ResolvedTypeNotFound(errMsg);
    }

    /**
     * The method generates the signature of a JavaParser resolved type declaration {@link ResolvedFieldDeclaration},
     * and returns its string representation.
     * @param resolvedFieldDeclaration The JavaParser resolved field declaration {@link ResolvedFieldDeclaration} from
     *                                 which to generate the corresponding signature.
     * @return A string representation of the signature of the JavaParser resolved field declaration {@link ResolvedFieldDeclaration}
     * passed to the function.
     */
    public static String getSignatureFromResolvedFieldDeclaration(
            ResolvedFieldDeclaration resolvedFieldDeclaration
    ) {
        // TODO: from a ResolvedFieldDeclaration is not possible to get the final attribute and the value of a field
        String signature = "";
        signature += resolvedFieldDeclaration.accessSpecifier().asString();
        signature += resolvedFieldDeclaration.isStatic() ? " static " : "";
        signature += resolvedFieldDeclaration.getName();
        return signature;
    }
    
    /**
     * The method generates the signature of a JavaParser method declaration {@link MethodDeclaration}, and returns its 
     * string representation. 
     * @param jpMethodDeclaration The JavaParser method declaration {@link MethodDeclaration} from which to generate the
     *                            corresponding signature.
     * @return A string representation of the signature of the JavaParser method declaration {@link MethodDeclaration}
     * passed to the function. 
     */
    public static String getSignatureFromMethodDeclaration(
            MethodDeclaration jpMethodDeclaration
    ) {
        String signature = "";
        // Add access specifier (public, private or protected)
        signature += String.format("%s ", jpMethodDeclaration.getAccessSpecifier().asString());
        // Add static label if static method
        signature += jpMethodDeclaration.isStatic() ? "static " : "";
        // Add static label if static method
        signature += jpMethodDeclaration.isFinal() ? "final " : "";
        // Get return type
        String returnType = jpMethodDeclaration.getType().isVoidType() ? "void " : String.format("%s ", jpMethodDeclaration.getType().asString());
        // Manage return type qualified name (es. java.lang.String -> String)
        returnType = extractTypeNameFromQualifiedName(returnType);
        // Add return type to signature
        signature += String.format("%s ", returnType);
        // Get method name and parameters
        signature += jpMethodDeclaration.getSignature();
        signature += ";";
        // Return signature
        return signature;
    }

    /**
     * The method generates the signature of a JavaParser method usage {@link MethodUsage}, and returns its 
     * string representation. 
     * @param jpMethodUsage The JavaParser method usage {@link MethodUsage} from which to generate the
     *                      corresponding signature.
     * @return A string representation of the signature of the JavaParser method usage {@link MethodUsage}
     * passed to the function. 
     */
    public static String getSignatureFromMethodUsage(
            MethodUsage jpMethodUsage
    ) {
        ResolvedMethodDeclaration jpResolvedMethodUsage = jpMethodUsage.getDeclaration();
        String signature = "";
        // Add access specifier (public, private or protected)
        signature += String.format("%s ", jpResolvedMethodUsage.accessSpecifier().asString());
        // Add static label if static method
        signature += jpResolvedMethodUsage.isStatic() ? "static " : "";
        // Get return type
        String returnType = jpResolvedMethodUsage.getReturnType().isVoid() ? "void " : String.format("%s ", jpResolvedMethodUsage.getReturnType().describe());
        // Manage return type qualified name (es. java.lang.String -> String)
        returnType = extractTypeNameFromQualifiedName(returnType);
        // Add return type to signature
        signature += String.format("%s ", returnType);
        // Get method name
        signature += jpMethodUsage.getName();
        // Iterate over the parameters, if any
        int parametersNumber = jpResolvedMethodUsage.getNumberOfParams();
        signature += "(";
        if (parametersNumber > 0) {
            for(int i = 0; i < parametersNumber; i++) {
                ResolvedParameterDeclaration jpResolvedParam = jpResolvedMethodUsage.getParam(i);
                String paramType = jpResolvedParam.describeType();
                paramType = extractTypeNameFromQualifiedName(paramType);
                signature += paramType;
                if (i < (parametersNumber - 1)) {
                    signature += ", ";
                }
            }
        }
        signature += ");";
        return signature;
    }

    /**
     * The method generates the signature of a JavaParser variable declarator {@link VariableDeclarator}, and returns its
     * string representation.
     * @param jpField The JavaParser field declaration {@link FieldDeclaration} where the variable is defined.
     * @param jpVariable The JavaParser variable declarator {@link VariableDeclarator} from which to generate the signature.
     * @return A string representation of the signature of the JavaParser variable declarator {@link VariableDeclarator}
     * passed to the function.
     */
    public static String getSignatureFromVariableDeclarator(
            FieldDeclaration jpField,
            VariableDeclarator jpVariable
    ) {
        String signature = "";
        signature += jpField.getAccessSpecifier().asString();
        signature += jpField.isStatic() ? " static " : " ";
        signature += jpField.isFinal() ? " final " : "";
        signature += String.format("%s ", jpVariable.getTypeAsString());
        signature += String.format("%s", jpVariable.getNameAsString());
        signature += jpVariable.getInitializer().isPresent() ? String.format(" = %s;", jpVariable.getInitializer().get()) : ";";
        return signature;
    }

    /**
     * The method searches a JavaParser compilation unit {@link CompilationUnit} among all the files of the project under
     * analysis, given the qualified name of the primary class {@code qualifiedName}. Indeed, the primary class of a 
     * compilation unit is the class that has the same name of the Java file name where it is defined.
     * The method returns an optional JavaParser compilation unit {@link CompilationUnit}. The optional contains an instance
     * of JavaParser compilation unit, if the method finds a match between a file with the same path extracted from the
     * qualified name passed to the function. Otherwise, the method returns an empty optional.
     * @param qualifiedName The qualified name of the primary class to find.
     * @param project The project under analysis.
     * @return An optional JavaParser compilation unit {@link CompilationUnit}. The optional contains an instance
     * of JavaParser compilation unit, if the method finds a match between a file with the same path extracted from the
     * qualified name passed to the function. Otherwise, the method returns an empty optional.
     */
    public  Optional<CompilationUnit> getCompilationUnitFromQualifiedName(String qualifiedName, Project project) {
        // Get the path of the superclass, from its package: a.b.c --> [a,b,c]
        String[] pathToClass = Arrays.stream(qualifiedName.split("\\.")).toArray(String[]::new);
        // Generate the absolute path to the class
        String filePath = Paths.get(project.srcPath(), pathToClass).toString().concat(".java");
        // Search compilation unit, given the entire file path
        Optional<CompilationUnit> cu = getCompilationUnitFromFileSilent(filePath);
        if (cu.isEmpty()) {
            filePath = filePath.replace(FileFormat.JAVA.getValue(), "");
            // Get the file path of the source file for the outer class
            String outerClassFilePath = filePath.substring(0, filePath.lastIndexOf(File.separator)) + FileFormat.JAVA.getValue();
            // Parse the source file to get the CompilationUnit of the outer class
            Optional<CompilationUnit> outerClassCompilationUnit = getCompilationUnitFromFileSilent(outerClassFilePath);
            if (outerClassCompilationUnit.isPresent()) {
                // Get the name of the inner class
                String innerClassName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
                // Get a list of all members of the outer class, including inner classes
                List<ClassOrInterfaceDeclaration> members = outerClassCompilationUnit.get().findAll(ClassOrInterfaceDeclaration.class);
                // Search for the inner class by name
                ClassOrInterfaceDeclaration innerClass = members.stream()
                        .filter(member -> member.isInnerClass() && member.getNameAsString().equals(innerClassName))
                        .findFirst()
                        .orElse(null);
                if (innerClass != null) {
                    // Get the CompilationUnit of the inner class
                    CompilationUnit innerClassCompilationUnit = innerClass.findCompilationUnit().orElse(null);

                    if (innerClassCompilationUnit != null) {
                        return Optional.of(innerClassCompilationUnit);
                    }
                }
            }
            return Optional.empty();
        }
        return cu;
    }

    /**
     * Given a list of JavaParser candidates methods or constructors {@link List<CallableDeclaration>} and the class
     * {@link TypeDeclaration} where the candidates are defined, the method searches a JavaParser callable
     * declaration {@link CallableDeclaration} (i.e. a constructor or a method) with the same number and type name of
     * parameters defined by the list of JDoctor condition parameter type names passed to the function
     * {@code jDoctorConditionParamTypeNames}. Indeed, in JavaParser, both constructors {@link ConstructorDeclaration}
     * and methods {@link MethodDeclaration} extends from {@link CallableDeclaration}.
     * The method returns an optional that contains the instance of {@link CallableDeclaration} (if found), otherwise an
     * empty optional.
     * @param jpClass An instance of a JavaParser type declaration {@link TypeDeclaration} representing the class where
     *                to find the constructor or method corresponding to the one to which the JDoctor condition refers.
     * @param jDoctorConditionParamTypeNames A list of strings, representing the type names of the arguments of the
     *                                       constructor or method to find within the given JavaParser type declaration
     *                                       {@link TypeDeclaration}.
     * @param jpCallableList A list of JavaParser callable declarations {@link List<CallableDeclaration>}, representing
     *                       the candidates constructors or methods of the given class, where to find the one to which
     *                       the JDoctor condition parameter type names refers.
     * @return An optional JavaParser callable declaration {@link Optional<CallableDeclaration>} i.e. the constructor or
     * the method that matches the list of Jdoctor condition parameter type names passed to the function
     * {@code jDoctorConditionParamTypeNames}. If the method or constructor is not found, the method returns an
     * empty optional.
     */
    public static Optional<CallableDeclaration<?>> getCallableDeclarationFromJDoctorConditionParamTypeNames(
            List<? extends CallableDeclaration<?>> jpCallableList,
            List<String> jDoctorConditionParamTypeNames,
            TypeDeclaration<?> jpClass
    ) {
        // Iterate over the constructors or the methods and try to find a match with the one of the JDoctor condition
        // considering the type of the parameters
        for (CallableDeclaration<?> jpCallable : jpCallableList) {
            // Get the parameters of the JavaParser constructor or method considered
            List<Parameter> jpCallableParams = jpCallable.getParameters();
            // The JavaParser constructor or method must have the same number of parameters of the on the JDoctor condition,
            // otherwise get the next candidate in the list
            if (jpCallableParams.size() == jDoctorConditionParamTypeNames.size()) {
                // Set the constructor or method as a potential candidate
                boolean found = true;
                // Iterate over the parameters of the
                for (int i = 0; i < jpCallableParams.size(); i++) {
                    // Get the i-th parameter of the JavaParser constructor or method candidate
                    Parameter jpParameter = jpCallableParams.get(i);
                    // Get the i-th parameter of the JDoctor constructor or method of the condition
                    String conditionParamTypeName = jDoctorConditionParamTypeNames.get(i);
                    // Get the class name (type name) of the JavaParser parameter
                    String jpTypeName = TypeUtils.getJPTypeName(jpClass, jpCallable, jpParameter);
                    // Check if the JavaParser type name of the parameter and the type name of the parameter of the
                    // JDoctor condition matches
                    if (!(jpTypeName.equals(conditionParamTypeName))) {
                        // Check if the JavaParser parameter and the parameter of the JDoctor condition represents
                        // generics (in this case the two can have different names)
                        boolean conditionParamTypeIsGeneric = TypeUtils.isGenericCondition(conditionParamTypeName);
                        boolean conditionParamTypeIsGenericArray = TypeUtils.isGenericConditionArray(conditionParamTypeName);
                        boolean jpParamTypeIsArray = jpTypeName.endsWith("[]");
                        if (!(conditionParamTypeIsGeneric || (conditionParamTypeIsGenericArray && jpParamTypeIsArray))) {
                            // Discard the constructor or method candidate
                            found = false;
                            break;
                        }
                    }
                }
                // Check if all the parameters of the JavaParser constructor or method matches the ones of the
                // corresponding constructor or method of the JDoctor condition
                if (found) {
                    // Return the constructor or the method found
                    return Optional.of(jpCallable);
                }
            }
        }
        // Return an empty optional because the method has not been found
        return Optional.empty();
    }

    /**
     * The method removes all the duplicates from a list.
     * @param list The list from which remove the duplicates.
     * @return A new list that does not contain the duplicates of the list passed to the function.
     * @param <T> The generic type of the list.
     */
    private <T> List<T> removeDuplicates(List<T> list) {
        Set<T> set = new LinkedHashSet<>(list);
        return new ArrayList<>(set);
    }

    /**
     * Given all the information of a method or a constructor (the method/constructor name, the list of JDoctor condition
     * parameter type names of the method/constructor, and the class name where the method/constructor is defined), the
     * function collects all the non-private, non-static, non-void methods of:
     * <ol>
     *     <li>The class (and superclasses) where the method/constructor has been defined.</li>
     *     <li>The classes (and superclasses) corresponding to the types of the parameters of the method/constructor analyzed</li>
     *     <li>The class (and superclasses) corresponding to the return type of the method analyzed (no return type, if the
     *     method name refers to a constructor).</li>
     * </ol>
     * The method searches a class within the compilation unit {@link CompilationUnit}, that defines a method
     * {@link MethodDeclaration} (or constructor {@link ConstructorDeclaration}) with the same name of {@code methodName}
     * and the same number and type name of the parameters defined by the list of JDoctor condition parameter type names
     * passed to the function {@code jDoctorConditionParamTypeNames}. If the class is found, the method generates the
     * list of non-private, non-static, non-void methods, in the form of a list of quartets of strings
     * {@link Quartet<String,String,String,String>}.
     * For example, consider the following non-private, non-static, non-void methods:
     *      {@code public int methodName1(int param1, int param2) { [method code] };}
     *      {@code public int methodName2(int param3, int param4) { [method code] };}
     * defined within one of the listed class (or superclass) under inspection.
     * The corresponding quartets will have the following structure (each string represents an information about the method,
     * and the order of the strings is important):
     * <ol>
     *     <li>method name</li>
     *     <li>package name</li>
     *     <li>class name</li>
     *     <li>method signature</li>
     * </ol>
     * Considering the previous example, the method will produce the following list of quartets:
     * {@code [
     *      ["methodName1", "package_name_of_method", "class_name_of_method", "public int methodName1(int param1, int param2);"],
     *      ["methodName2", "package_name_of_method", "class_name_of_method", "public int methodName2(int param3, int param4);"]
     * ]}
     * where
     * <ul>
     *     <li>*package_name_of_method* is the package name of the class (or superclass) where the method is declared</li>
     *     <li>*class_name_of_method* is the class name of the class (or superclass) where the method is declared</li>
     * </ul>
     * The method returns the list of quartets strings collected after the analysis of the method/constructor.
     *
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @param methodName The name of the method or constructor under analysis
     * @param className The name of the class where the method has been defined
     * @param jDoctorConditionParamTypeNames A list of strings, representing the type names of the arguments of the
     *                                       constructor or method to find within the given JavaParser compilation unit
     *                                       {@link CompilationUnit}.
     * @param project The project under analysis.
     * @return A list of quartets of strings {@link Quartet<String,String,String,String>}, representing (i) the non-private,
     * non-static, non-void methods declared within the class and superclasses where the method/constructor under analysis
     * is defined, (ii) the non-private, non-static, non-void methods declared within the class and superclasses of the
     * parameter types of the method/constructor under analysis, and (iii) the non-private, non-static, non-void methods
     * declared within the class and superclasses of the return type of the method under analysis (no return type if the
     * method under analysis is a constructor).
     *
     * @throws PrimaryTypeNotFoundException If the compilation unit passed to the function does not have a primary class.
     * {@link TypeDeclaration} (in other words, the compilation unit does not have a class defined within it).
     * @throws JPMethodNotFound If the method analyzed is not found within the compilation unit passed to the function.
     * @throws PackageDeclarationNotFoundException If the compilation unit passed to the function does not have a package
     * declaration.
     * @throws JPClassNotFoundException If the method under analysis does not find a class with a constructor corresponding
     * to the list of JDoctor condition parameter type names passed to the function.
     * @throws JPConstructorNotFound If the constructor under analysis is not found within a class {@link TypeDeclaration}
     * declared within the compilation unit {@link CompilationUnit} passed to the function.
     */
    public List<Quartet<String, String, String, String>> getTokensMethodVariablesNonPrivateNonStaticNonVoidMethods(
            CompilationUnit cu,
            String methodName,
            String className,
            List<String> jDoctorConditionParamTypeNames,
            Project project
    ) throws PrimaryTypeNotFoundException, JPMethodNotFound, PackageDeclarationNotFoundException, JPClassNotFoundException, JPConstructorNotFound {
        // Define the list of quartets of type [methodName, packageName, className, methodSignature]
        List<Quartet<String, String, String, String>> quartetList = new ArrayList<>();
        // Generate list of conditions for method declarations and usages
        List<Pair<MethodFeature, Boolean>> methodConditions = new ArrayList<>();
        // The method must be non-private
        methodConditions.add(new Pair<>(MethodFeature.PRIVATE, Boolean.valueOf(false)));
        // The method must be non-static
        methodConditions.add(new Pair<>(MethodFeature.STATIC, Boolean.valueOf(false)));
        // The method must be non-void
        methodConditions.add(new Pair<>(MethodFeature.VOID, Boolean.valueOf(false)));
        // Get method declaration
        CallableDeclaration<?> jpMethod = methodName.equals(className) ? getConstructorDeclaration(cu, jDoctorConditionParamTypeNames) : getMethodDeclaration(cu, methodName, jDoctorConditionParamTypeNames);
        // Get the class declaration of the method under test
        TypeDeclaration<?> jpClass = getJavaTypeDeclarationFromClassDeclaration(cu, jpMethod);
        // Get all the candidate methods of the class
        List<MethodDeclaration> jpCandidateMethods = jpClass.getMethods();
        // Add to the collections all the methods of the class (receiverObjectID -> this) that satisfy the method conditions
        quartetList.addAll(generateMethodQuartetListFromMethodDeclarationList(jpCandidateMethods, methodConditions));
        // Get method parameters
        List<Parameter> jpParameters = jpMethod.getParameters();
        // Iterate over the list of parameters
        for (Parameter jpParam: jpParameters) {
            // Get the parameter type
            Type jpParamType = jpParam.getType();
            // Add to the collections all the methods of the parameter that satisfy the method conditions
            quartetList.addAll(generateMethodQuartetListFromType(project, jpParamType, methodConditions));
        }

        if (jpMethod instanceof MethodDeclaration) {
            // Get the return type of the method under test (methodResultID)
            Type jpMethodReturnType = ((MethodDeclaration) jpMethod).getType();
            // Add to the collections all the methods of the return type (methodResultID) that satisfy the method conditions
            quartetList.addAll(generateMethodQuartetListFromType(project, jpMethodReturnType, methodConditions));
        }
        // remove duplicates and return.
        return removeDuplicates(quartetList);
    }

    /**
     * The method converts a list of JavaParser fields {@link FieldDeclaration} into a list of quartets of strings
     * {@link Quartet<String,String,String,String>}. Indeed, each JavaParser field corresponds to a statement where one
     * or more attributes (variables) are defined. For example: {@code public static int x = 1,y = 2;} is a field where
     * {@code x} and {@code y} are the attributes defined and initialized. The method generates a quartet of strings,
     * for each attribute of each JavaParser field in the list {@code jpFieldList} passed to the function.
     * The method generate quartets only for the fields in the list that satisfies the conditions defined in the list
     * {@code featureConditions} passed to the function.
     * Each feature condition is a pair where the first element is a field feature {@link FieldFeature}, and the second
     * element is a boolean {@link Boolean}. A field feature refers to a field specifier (*public*, *private*, *protected*,
     * *static*, or *final*). Instead, the boolean value evaluates if the given condition must be positive or negative.
     * For example, the pair {@code [FieldFeature.STATIC, false]} means that the fields to consider must be non-static
     * (negative condition), while the pair {@code [FieldFeature.PUBLIC, true]} means that the fields to consider must
     * be public. If the condition is not present the state of the corresponding feature don't care: for example, if there
     * is not a condition about the *final* specifier, the fields can be both *final* or not.
     * Each quartet has the same structure (each string represents an information about the field, and the order of the
     * strings is important):
     * <ol>
     *     <li>attribute name</li>
     *     <li>package name</li>
     *     <li>class name</li>
     *     <li>attribute signature</li>
     * </ol>
     * Considering the previous example, given the JavaParser field declaration corresponding to the statement
     * {@code public static int x = 1,y = 2;}, the method will produce the list of quartets:
     * {@code [
     *      ["x", "package_name_of_field", "class_name_of_field", "public static int x = 1;"],
     *      ["y", "package_name_of_field", "class_name_of_field", "public static int y = 2;"]
     * ]}
     * where
     * <ul>
     *     <li>*package_name_of_field* is the package name of the class where the field is declared</li>
     *     <li>*class_name_of_field* is the class name of the class where the field is declared</li>
     * </ul>
     * The method returns the list of collected quartets.
     *
     * @param jpFieldList A list of JavaParser fields {@link FieldDeclaration} representing statements of a class where
     *                    one or more variable has been defined.
     * @param featureConditions A list of conditions that each field to convert must respect.
     * @return A list of quartets of strings {@link Quartet<String,String,String,String>}, representing the information
     * of the attributes of the fields of the list {@code @jpFieldList} passed to the function, that respect the list of
     * given feature conditions {@code featureConditions}.
     */
    public List<Quartet<String, String, String, String>> generateFieldQuartetListFromFieldDeclarationList(
            List<FieldDeclaration> jpFieldList,
            List<Pair<FieldFeature, Boolean>> featureConditions
    ) {
        // Generate predicates for field declarations, to find the fields that satisfy the given conditions
        List<Predicate<FieldDeclaration>> predicates = FieldFeature.generateFieldDeclarationPredicates(featureConditions);
        // Generate quartet list, filtering according to the conditions
        List<Quartet<String, String, String, String>> quartetList = jpFieldList
                .stream()
                .filter(jpField -> checkFieldDeclarationFeaturesCondition(jpField, predicates))
                .map(jpField -> convertFieldDeclaration2AttributeQuartets(jpField))
                .flatMap(List::stream)
                .toList();
        // Return the quartet list
        return quartetList;
    }

    /**
     * The method converts a list of SymbolSovler resolved fields {@link ResolvedFieldDeclaration} into a list of quartets
     * of strings {@link Quartet<String,String,String,String>}. Indeed, each SymbolSolver field corresponds to a statement
     * where one or more attributes (variables) are defined. For example: {@code public static int x = 1,y = 2;} is a field
     * where {@code x} and {@code y} are the attributes defined and initialized. The method generates a quartet of strings,
     * for each attribute. The method generate quartets only for the fields in the list that satisfies the conditions
     * defined in the list {@code featureConditions} passed to the function.
     * Each feature condition is a pair where the first element is a field feature {@link FieldFeature}, and the second
     * element is a boolean {@link Boolean}. A field feature refer to a field specifier (*public*, *private*, *protected*,
     * *static*, or *final*). Instead, the boolean value evaluates if the given condition must be positive or negative.
     * For example, the pair {@code [FieldFeature.STATIC, false]} means that the fields to consider must be non-static
     * (negative condition), while the pair {@code [FieldFeature.PUBLIC, true]} means that the fields to consider must
     * be public. If the condition is not present the state of the corresponding feature don't care: for example, if there
     * is not a condition about the *final* specifier, the fields can be both *final* or not.
     * Each quartet has the same structure (each string represents an information about the field, and the order of the
     * strings is important):
     * <ol>
     *     <li>attribute name</li>
     *     <li>package name</li>
     *     <li>class name</li>
     *     <li>attribute signature</li>
     * </ol>
     * Considering the previous example, given the SymbolSolver resolved field declaration corresponding to the statement
     * {@code public static int x = 1,y = 2;}, the method will produce the list of quartets:
     * {@code [
     *      ["x", "package_name_of_field", "class_name_of_field", "public static int x = 1;"],
     *      ["y", "package_name_of_field", "class_name_of_field", "public static int y = 2;"]
     * ]}
     * where
     * <ul>
     *     <li>*package_name_of_field* is the package name of the class where the field is declared</li>
     *     <li>*class_name_of_field* is the class name of the class where the field is declared</li>
     * </ul>
     * The method returns the list of collected quartets.
     *
     * @param jpFieldList A list of SymbolSolver resolved fields {@link ResolvedFieldDeclaration} representing statements
     *                    of a class where one or more variable has been defined.
     * @param featureConditions A list of conditions that each field to convert must respect.
     * @return A list of quartets of strings {@link Quartet<String,String,String,String>}, representing the information
     * of the attributes of the fields of the list {@code @jpFieldList} passed to the function, that respect the list of
     * given feature conditions {@code featureConditions}.
     */
    public List<Quartet<String, String, String, String>> generateFieldQuartetListFromResolvedFieldDeclarationList(
            List<ResolvedFieldDeclaration> jpFieldList,
            List<Pair<FieldFeature, Boolean>> featureConditions
    ) {
        // Generate predicates for field declarations, to find the fields that satisfy the given conditions
        List<Predicate<ResolvedFieldDeclaration>> predicates = FieldFeature.generateResolvedFieldDeclarationPredicates(featureConditions);
        // Generate quartet list, filtering according to the conditions
        List<Quartet<String, String, String, String>> quartetList = jpFieldList
                .stream()
                .filter(jpField -> checkFieldDeclarationFeaturesCondition(jpField, predicates))
                .map(jpField -> convertResolvedFieldDeclaration2AttribtueQuartet(jpField))
                .toList();
        // Return the quartet list
        return quartetList;
    }

    /**
     * Given a SymbolSolver type {@link Type}, the method gets the list of all the SymbolSolver resolved fields defined
     * within the type and its superclasses and generates a list of quartets of strings
     * {@link Quartet<String,String,String,String>}, from the fields that respect a set of feature conditions. Indeed,
     * each SymbolSolver field corresponds to a statement where one or more attributes (variables) are defined.
     * For example: {@code public static int x = 1,y = 2;} is a field where {@code x} and {@code y} are the attributes
     * defined and initialized. The method generates a quartet of strings, for each attribute. The method generate quartets
     * only for the fields in the list that satisfies the conditions defined in the list {@code featureConditions} passed
     * to the function.
     * Each feature condition is a pair where the first element is a field feature {@link FieldFeature}, and the second
     * element is a boolean {@link Boolean}. A field feature refers to a field specifier (*public*, *private*, *protected*,
     * *static*, or *final*). Instead, the boolean value evaluates if the given condition must be positive or negative.
     * For example, the pair {@code [FieldFeature.STATIC, false]} means that the fields to consider must be non-static
     * (negative condition), while the pair {@code [FieldFeature.PUBLIC, true]} means that the fields to consider must
     * be public. If the condition is not present the state of the corresponding feature don't care: for example, if there
     * is not a condition about the *final* specifier, the fields can be both *final* or not.
     * Each quartet has the same structure (each string represents an information about the field, and the order of the
     * strings is important):
     * <ol>
     *     <li>attribute name</li>
     *     <li>package name</li>
     *     <li>class name</li>
     *     <li>attribute signature</li>
     * </ol>
     * Considering the previous example, given the SymbolSolver resolved field declaration corresponding to the statement
     * {@code public static int x = 1,y = 2;}, the method will produce the list of quartets:
     * {@code [
     *      ["x", "package_name_of_field", "class_name_of_field", "public static int x = 1;"],
     *      ["y", "package_name_of_field", "class_name_of_field", "public static int y = 2;"]
     * ]}
     * where
     * <ul>
     *     <li>*package_name_of_field* is the package name of the class where the field is declared</li>
     *     <li>*class_name_of_field* is the class name of the class where the field is declared</li>
     * </ul>
     * The method returns the list of collected quartets.
     *
     * @param project The project under analysis.
     * @param jpType The SymbolSolver type {@link Type} from which extract the list of resolved fields
     *               {@link ResolvedFieldDeclaration} and generate the list of quartets of strings.
     * @param featureConditions A list of conditions that each field to convert must respect.
     * @return A list of quartets of strings {@link Quartet<String,String,String,String>}, representing the information
     * of the attributes defined in the resolved fields extracted from the SymbolSolver type {@link Type} passed to the
     * function, and that respect the list of given feature conditions {@code featureConditions}.
     */
    public List<Quartet<String, String, String, String>> generateFieldQuartetListFromType(
            Project project,
            Type jpType,
            List<Pair<FieldFeature, Boolean>> featureConditions
    ) {
        // Get resolved type
        ResolvedType jpResolvedType = jpType.resolve();
        return generateFieldQuartetListFromType(project, jpResolvedType, featureConditions);
    }

    /**
     * Given a SymbolSolver resolved type {@link ResolvedType}, the method gets the list of all the SymbolSolver resolved
     * fields defined within the type and its superclasses and generates a list of quartets of strings
     * {@link Quartet<String,String,String,String>}, from the fields that respect a set of feature conditions. Indeed,
     * each SymbolSolver field corresponds to a statement where one or more attributes (variables) are defined.
     * For example: {@code public static int x = 1,y = 2;} is a field where {@code x} and {@code y} are the attributes
     * defined and initialized. The method generates a quartet of strings, for each attribute. The method generate quartets
     * only for the fields in the list that satisfies the conditions defined in the list {@code featureConditions} passed
     * to the function.
     * Each feature condition is a pair where the first element is a field feature {@link FieldFeature}, and the second
     * element is a boolean {@link Boolean}. A field feature refers to a field specifier (*public*, *private*, *protected*,
     * *static*, or *final*). Instead, the boolean value evaluates if the given condition must be positive or negative.
     * For example, the pair {@code [FieldFeature.STATIC, false]} means that the fields to consider must be non-static
     * (negative condition), while the pair {@code [FieldFeature.PUBLIC, true]} means that the fields to consider must
     * be public. If the condition is not present the state of the corresponding feature don't care: for example, if there
     * is not a condition about the *final* specifier, the fields can be both *final* or not.
     * Each quartet has the same structure (each string represents an information about the field, and the order of the
     * strings is important):
     * <ol>
     *     <li>attribute name</li>
     *     <li>package name</li>
     *     <li>class name</li>
     *     <li>attribute signature</li>
     * </ol>
     * Considering the previous example, given the SymbolSolver resolved field declaration corresponding to the statement
     * {@code public static int x = 1,y = 2;}, the method will produce the list of quartets:
     * {@code [
     *      ["x", "package_name_of_field", "class_name_of_field", "public static int x = 1;"],
     *      ["y", "package_name_of_field", "class_name_of_field", "public static int y = 2;"]
     * ]}
     * where
     * <ul>
     *     <li>*package_name_of_field* is the package name of the class where the field is declared</li>
     *     <li>*class_name_of_field* is the class name of the class where the field is declared</li>
     * </ul>
     * The method returns the list of collected quartets.
     *
     * @param project The project under analysis.
     * @param jpResolvedType The SymbolSolver resolved type {@link ResolvedType} from which extract the list of resolved fields
     *                       {@link ResolvedFieldDeclaration} and generate the list of quartets of strings.
     * @param featureConditions A list of conditions that each field to convert must respect.
     * @return A list of quartets of strings {@link Quartet<String,String,String,String>}, representing the information
     * of the attributes defined in the resolved fields extracted from the SymbolSolver type {@link Type} passed to the
     * function, and that respect the list of given feature conditions {@code featureConditions}.
     */
    public List<Quartet<String, String, String, String>> generateFieldQuartetListFromType(
            Project project,
            ResolvedType jpResolvedType,
            List<Pair<FieldFeature, Boolean>> featureConditions
    ) {
        // Define the list of quartets of type [attributeName, packageName, className, attributeSignature]
        List<Quartet<String, String, String, String>> quartetList = new ArrayList<>();
        // Get all the candidate fields of the class
        Pair<List<FieldDeclaration>,List<ResolvedFieldDeclaration>> jpFieldTypePairList = getFieldsFromType(project, jpResolvedType);
        // Get the list of successfully converted field declarations
        List<FieldDeclaration> jpCandidateFieldDeclarations = jpFieldTypePairList.getValue0();
        // Add to the collections all the fields of the class (receiverObjectID -> this) that satisfy the predicates
        quartetList.addAll(generateFieldQuartetListFromFieldDeclarationList(jpCandidateFieldDeclarations, featureConditions));
        // Get the list of resolved fields declarations not converted in method declarations
        List<ResolvedFieldDeclaration> jpCandidateResolvedFieldDeclarationsNotFound = jpFieldTypePairList.getValue1();
        // Add to the collections all the methods of the class (receiverObjectID -> this) that satisfy the predicates
        quartetList.addAll(generateFieldQuartetListFromResolvedFieldDeclarationList(jpCandidateResolvedFieldDeclarationsNotFound, featureConditions));
        // Return the quartet list
        return quartetList;
    }

    /**
     * The method converts a list of JavaParser methods {@link MethodDeclaration} into a list of quartets of strings
     * {@link Quartet<String,String,String,String>}, considering only the methods in the list that respect a set of
     * method conditions.
     * Each JavaParser method corresponds to a block of code where a method is defined. For example:
     * {@code public int methodName(int param1, int param2) { [method code] };} is a block of code that defines the
     * method *methodName*.
     * Each method condition is a pair where the first element is a method feature {@link MethodFeature}, and the second
     * element is a boolean {@link Boolean}. A method feature refers to a method specifier (*public*, *private*, *protected*,
     * *static*, *final*, or *void*). Instead, the boolean value evaluates if the given condition must be positive or negative.
     * For example, the pair {@code [MethodFeature.STATIC, false]} means that the methods to consider must be non-static
     * (negative condition), while the pair {@code [MethodFeature.PUBLIC, true]} means that the methods to consider must
     * be public. If the condition is not present the state of the corresponding feature don't care: for example, if there
     * is not a condition about the *final* specifier, the methods can be both *final* or not.
     * The quartet has the following structure (each string represents an information about the method, and the order of the
     * strings is important):
     * <ol>
     *     <li>method name</li>
     *     <li>package name</li>
     *     <li>class name</li>
     *     <li>method signature</li>
     * </ol>
     * Considering the previous example, given the JavaParser method declaration corresponding to the block of code
     * {@code public int methodName(int param1, int param2) { [method code] };}, the method will produce the quartet:
     * {@code [
     *      "methodName",
     *      "package_name_of_method",
     *      "class_name_of_method",
     *      "public int methodName(int param1, int param2);"
     * ]}
     * where
     * <ul>
     *     <li>*package_name_of_method* is the package name of the class where the method is declared</li>
     *     <li>*class_name_of_method* is the class name of the class where the method is declared</li>
     * </ul>
     * The method returns the list of collected quartets.
     *
     * @param jpMethodList A list of JavaParser methods {@link MethodDeclaration}.
     * @param methodConditions A list of conditions that each method to convert must respect.
     * @return A list of quartets of strings {@link Quartet<String,String,String,String>}, representing the information
     * of the methods of the list {@code @jpMethodList} passed to the function, that respect the list of
     * given method conditions {@code methodConditions}.
     */
    public List<Quartet<String, String, String, String>> generateMethodQuartetListFromMethodDeclarationList(
            List<MethodDeclaration> jpMethodList,
            List<Pair<MethodFeature, Boolean>> methodConditions
    ) {
        // Generate predicates for method declarations, to find the methods that satisfy the given conditions
        List<Predicate<MethodDeclaration>> predicates = MethodFeature.generateMethodDeclarationPredicates(methodConditions);
        // Generate quartet list, filtering according to the conditions
        List<Quartet<String, String, String, String>> quartetList = jpMethodList
            .stream()
            .filter(jpMethod -> checkMethodDeclarationFeaturesCondition(jpMethod, predicates))
            .map(jpCandidateMethod -> convertMethodDeclaration2MethodQuartet(jpCandidateMethod))
            .flatMap(Optional::stream)
            .toList();
        // Return the quartet list
        return quartetList;
    }

    /**
     * The method converts a list of SymbolSolver method usages {@link MethodUsage} into a list of quartets of strings
     * {@link Quartet<String,String,String,String>}, considering only the method usages in the list that respect a set of
     * method conditions.
     * Each SymbolSolver method usage corresponds to a block of code where a method is defined. For example:
     * {@code public int methodName(int param1, int param2) { [method code] };} is a block of code that defines the
     * method *methodName*.
     * Each method condition is a pair where the first element is a method feature {@link MethodFeature}, and the second
     * element is a boolean {@link Boolean}. A method feature refers to a method specifier (*public*, *private*, *protected*,
     * *static*, *final*, or *void*). Instead, the boolean value evaluates if the given condition must be positive or negative.
     * For example, the pair {@code [MethodFeature.STATIC, false]} means that the methods to consider must be non-static
     * (negative condition), while the pair {@code [MethodFeature.PUBLIC, true]} means that the methods to consider must
     * be public. If the condition is not present the state of the corresponding feature don't care: for example, if there
     * is not a condition about the *final* specifier, the methods can be both *final* or not.
     * The quartet has the following structure (each string represents an information about the method, and the order of the
     * strings is important):
     * <ol>
     *     <li>method name</li>
     *     <li>package name</li>
     *     <li>class name</li>
     *     <li>method signature</li>
     * </ol>
     * Considering the previous example, given the JavaParser method declaration corresponding to the block of code
     * {@code public int methodName(int param1, int param2) { [method code] };}, the method will produce the quartet:
     * {@code [
     *      "methodName",
     *      "package_name_of_method",
     *      "class_name_of_method",
     *      "public int methodName(int param1, int param2);"
     * ]}
     * where
     * <ul>
     *     <li>*package_name_of_method* is the package name of the class where the method is declared</li>
     *     <li>*class_name_of_method* is the class name of the class where the method is declared</li>
     * </ul>
     * The method returns the list of collected quartets.
     *
     * @param jpMethodList A list of JavaParser methods {@link MethodDeclaration}.
     * @param methodConditions A list of conditions that each method to convert must respect.
     * @return A list of quartets of strings {@link Quartet<String,String,String,String>}, representing the information
     * of the SymbolSolver method usages of the list {@code @jpMethodList} passed to the function, that respect the list of
     * given method conditions {@code methodConditions}.
     */
    public List<Quartet<String, String, String, String>> generateMethodQuartetListFromMethodUsageList(
            List<MethodUsage> jpMethodList,
            List<Pair<MethodFeature, Boolean>> methodConditions
    ) {
        // Generate predicates for method declarations, to find the methods that satisfy the given conditions
        List<Predicate<MethodUsage>> predicates = MethodFeature.generateMethodUsagePredicates(methodConditions);
        // Generate quartet list, filtering according to the conditions
        List<Quartet<String, String, String, String>> quartetList = jpMethodList
                .stream()
                .filter(jpMethod -> checkMethodDeclarationFeaturesCondition(jpMethod, predicates))
                .map(jpCandidateMethod -> convertMethodUsage2MethodQuartet(jpCandidateMethod))
                .toList();
        // Return the quartet list
        return quartetList;
    }

    /**
     * Given a SymbolSolver type {@link Type}, the method gets the list of all the SymbolSolver method usages defined
     * within the type and its superclasses and generates a list of quartets of strings
     * {@link Quartet<String,String,String,String>}, from the methods that respect a set of method conditions.
     * Each SymbolSolver method usage corresponds to a block of code where a method is defined. For example:
     * {@code public int methodName(int param1, int param2) { [method code] };} is a block of code that defines the
     * method *methodName*.
     * Each method condition is a pair where the first element is a method feature {@link MethodFeature}, and the second
     * element is a boolean {@link Boolean}. A method feature refers to a method specifier (*public*, *private*, *protected*,
     * *static*, *final*, or *void*). Instead, the boolean value evaluates if the given condition must be positive or negative.
     * For example, the pair {@code [MethodFeature.STATIC, false]} means that the methods to consider must be non-static
     * (negative condition), while the pair {@code [MethodFeature.PUBLIC, true]} means that the methods to consider must
     * be public. If the condition is not present the state of the corresponding feature don't care: for example, if there
     * is not a condition about the *final* specifier, the methods can be both *final* or not.
     * The quartet has the following structure (each string represents an information about the method, and the order of the
     * strings is important):
     * <ol>
     *     <li>method name</li>
     *     <li>package name</li>
     *     <li>class name</li>
     *     <li>method signature</li>
     * </ol>
     * Considering the previous example, given the JavaParser method declaration corresponding to the block of code
     * {@code public int methodName(int param1, int param2) { [method code] };}, the method will produce the quartet:
     * {@code [
     *      "methodName",
     *      "package_name_of_method",
     *      "class_name_of_method",
     *      "public int methodName(int param1, int param2);"
     * ]}
     * where
     * <ul>
     *     <li>*package_name_of_method* is the package name of the class where the method is declared</li>
     *     <li>*class_name_of_method* is the class name of the class where the method is declared</li>
     * </ul>
     * The method returns the list of collected quartets.
     *
     * @param project The project under analysis.
     * @param jpType The SymbolSolver type {@link Type} from which extract the list of method usages
     *               {@link ResolvedFieldDeclaration} and generate the list of quartets of strings.
     * @param methodConditions A list of conditions that each method to convert must respect.
     * @return A list of quartets of strings {@link Quartet<String,String,String,String>}, representing the information
     * of the methods extracted from the SymbolSolver type {@link Type} passed to the function, and that respect the list
     * of given method conditions {@code methodConditions}.
     */
    public List<Quartet<String, String, String, String>> generateMethodQuartetListFromType(
            Project project,
            Type jpType,
            List<Pair<MethodFeature, Boolean>> methodConditions
    ) {
        // Get resolved type
        ResolvedType jpResolvedType = jpType.resolve();
        return generateMethodQuartetListFromType(project, jpResolvedType, methodConditions);
    }

    /**
     * Given a SymbolSolver resolved type {@link ResolvedType}, the method gets the list of all the SymbolSolver
     * method usages defined within the type and its superclasses and generates a list of quartets of strings
     * {@link Quartet<String,String,String,String>}, from the methods that respect a set of method conditions.
     * Each SymbolSolver method usage corresponds to a block of code where a method is defined. For example:
     * {@code public int methodName(int param1, int param2) { [method code] };} is a block of code that defines the
     * method *methodName*.
     * Each method condition is a pair where the first element is a method feature {@link MethodFeature}, and the second
     * element is a boolean {@link Boolean}. A method feature refers to a method specifier (*public*, *private*, *protected*,
     * *static*, *final*, or *void*). Instead, the boolean value evaluates if the given condition must be positive or negative.
     * For example, the pair {@code [MethodFeature.STATIC, false]} means that the methods to consider must be non-static
     * (negative condition), while the pair {@code [MethodFeature.PUBLIC, true]} means that the methods to consider must
     * be public. If the condition is not present the state of the corresponding feature don't care: for example, if there
     * is not a condition about the *final* specifier, the methods can be both *final* or not.
     * The quartet has the following structure (each string represents an information about the method, and the order of the
     * strings is important):
     * <ol>
     *     <li>method name</li>
     *     <li>package name</li>
     *     <li>class name</li>
     *     <li>method signature</li>
     * </ol>
     * Considering the previous example, given the JavaParser method declaration corresponding to the block of code
     * {@code public int methodName(int param1, int param2) { [method code] };}, the method will produce the quartet:
     * {@code [
     *      "methodName",
     *      "package_name_of_method",
     *      "class_name_of_method",
     *      "public int methodName(int param1, int param2);"
     * ]}
     * where
     * <ul>
     *     <li>*package_name_of_method* is the package name of the class where the method is declared</li>
     *     <li>*class_name_of_method* is the class name of the class where the method is declared</li>
     * </ul>
     * The method returns the list of collected quartets.
     *
     * @param project The project under analysis.
     * @param jpResolvedType The SymbolSolver resolved type {@link ResolvedType} from which extract the list of method usages
     *                       {@link ResolvedFieldDeclaration} and generate the list of quartets of strings.
     * @param methodConditions A list of conditions that each method to convert must respect.
     * @return A list of quartets of strings {@link Quartet<String,String,String,String>}, representing the information
     * of the methods extracted from the SymbolSolver resolved type {@link ResolvedType} passed to the function, and that
     * respect the list of given method conditions {@code methodConditions}.
     */
    public List<Quartet<String, String, String, String>> generateMethodQuartetListFromType(
            Project project,
            ResolvedType jpResolvedType,
            List<Pair<MethodFeature, Boolean>> methodConditions
    ) {
        // Define the list of quartets of type [methodName, packageName, className, methodSignature]
        List<Quartet<String, String, String, String>> quartetList = new ArrayList<>();
        // Get all the candidate methods of the class
        Pair<List<MethodDeclaration>,List<MethodUsage>> jpMethodTypePairList = getMethodsFromType(project, jpResolvedType);
        // Get the list of successfully converted method declarations
        List<MethodDeclaration> jpCandidateMethodDeclarations = jpMethodTypePairList.getValue0();
        // Add to the collections all the methods of the class (receiverObjectID -> this) that satisfy the predicates
        quartetList.addAll(generateMethodQuartetListFromMethodDeclarationList(jpCandidateMethodDeclarations, methodConditions));
        // Get the list of method usages not converted in method declarations
        List<MethodUsage> jpCandidateMethodDeclarationsNotFound = jpMethodTypePairList.getValue1();
        // Add to the collections all the methods of the class (receiverObjectID -> this) that satisfy the predicates
        quartetList.addAll(generateMethodQuartetListFromMethodUsageList(jpCandidateMethodDeclarationsNotFound, methodConditions));
        // Manage array type
        if (jpResolvedType.isArray()) {
            // Get path to json file
            String arrayMethodsJsonPath = Paths.get(Path.REPOS.getValue(), FileName.ARRAY_METHODS.getValue() + FileFormat.JSON.getValue()).toString();
            List<List<String>> arrayMethods = (List<List<String>>) FileUtils.readJSONList(arrayMethodsJsonPath);
            quartetList.addAll(arrayMethods.stream().map(m -> new Quartet<>(m.get(0), m.get(1), m.get(2), m.get(3))).toList());
        }
        // Return the quartet list
        return quartetList;
    }

    /**
     * Given all the information of a method or a constructor (the method/constructor name, the list of JDoctor condition
     * parameter type names of the method/constructor, and the class name where the method/constructor is defined), the
     * function collects all the non-private, non-static, attributes of:
     * <ol>
     *     <li>The class (and superclasses) where the method/constructor has been defined.</li>
     *     <li>The classes (and superclasses) corresponding to the types of the parameters of the method/constructor analyzed</li>
     *     <li>The class (and superclasses) corresponding to the return type of the method analyzed (no return type, if the
     *     method name refers to a constructor).</li>
     * </ol>
     * The method searches a class within the compilation unit {@link CompilationUnit}, that defines a method
     * {@link MethodDeclaration} (or constructor {@link ConstructorDeclaration}) with the same name of {@code methodName}
     * and the same number and type name of the parameters defined by the list of JDoctor condition parameter type names
     * passed to the function {@code jDoctorConditionParamTypeNames}. If the class is found, the method generates the
     * list of non-private, non-static, attributes, in the form of a list of quartets of strings
     * {@link Quartet<String,String,String,String>}.
     * For example, consider the following non-private, non-static, field {@code public static int x = 1,y = 2;}
     * extracted from one of the three type of classes listed before.
     * Each quartet has the same structure (each string represents an information about the field, and the order of the
     * strings is important):
     * <ol>
     *     <li>attribute name</li>
     *     <li>package name</li>
     *     <li>class name</li>
     *     <li>attribute signature</li>
     * </ol>
     * Considering the previous example, field declaration corresponding to the statement {@code public static int x = 1,y = 2;},
     * the method will produce the list of quartets:
     * {@code [
     *      ["x", "package_name_of_field", "class_name_of_field", "public static int x = 1;"],
     *      ["y", "package_name_of_field", "class_name_of_field", "public static int y = 2;"]
     * ]}
     * where
     * <ul>
     *     <li>*package_name_of_field* is the package name of the class where the field is declared</li>
     *     <li>*class_name_of_field* is the class name of the class where the field is declared</li>
     * </ul>
     * The method returns the list of quartets strings of the fields collected after the analysis of the method/constructor.
     *
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @param methodName The name of the method or constructor under analysis
     * @param className The name of the class where the method has been defined
     * @param jDoctorConditionParamTypeNames A list of strings, representing the type names of the arguments of the
     *                                       constructor or method to find within the given JavaParser compilation unit
     *                                       {@link CompilationUnit}.
     * @param project The project under analysis.
     * @return A list of quartets of strings {@link Quartet<String,String,String,String>}, representing (i) the non-private,
     * non-static attributes declared within the class and superclasses where the method/constructor under analysis
     * is defined, (ii) the non-private, non-static attributes declared within the class and superclasses of the
     * parameter types of the method/constructor under analysis, and (iii) the non-private, non-static attributes
     * declared within the class and superclasses of the return type of the method under analysis (no return type if the
     * method under analysis is a constructor).
     *
     * @throws PrimaryTypeNotFoundException If the compilation unit passed to the function does not have a primary class.
     * {@link TypeDeclaration} (in other words, the compilation unit does not have a class defined within it).
     * @throws JPMethodNotFound If the method analyzed is not found within the compilation unit passed to the function.
     * @throws JPConstructorNotFound If the constructor under analysis is not found within a class {@link TypeDeclaration}
     * declared within the compilation unit {@link CompilationUnit} passed to the function.
     * @throws JPClassNotFoundException If the method does not find a class with a constructor corresponding
     * to the list of JDoctor condition parameter type names passed to the function.
     */
    public List<Quartet<String, String, String, String>> getTokensMethodVariablesNonPrivateNonStaticAttributes(
            CompilationUnit cu,
            String methodName,
            String className,
            List<String> jDoctorConditionParamTypeNames,
            Project project
    ) throws PrimaryTypeNotFoundException, JPMethodNotFound, JPConstructorNotFound, JPClassNotFoundException {
        // Define the list of quartets of type [attributeName, packageName, className, attributeSignature]
        List<Quartet<String, String, String, String>> quartetList = new ArrayList<>();
        // Generate list of attributes features
        List<Pair<FieldFeature, Boolean>> fieldFeatures = new ArrayList<>();
        // The attribute must be non-private
        fieldFeatures.add(new Pair<>(FieldFeature.PRIVATE, Boolean.valueOf(false)));
        // The attribute must be non-static
        fieldFeatures.add(new Pair<>(FieldFeature.STATIC, Boolean.valueOf(false)));
        // Get method declaration
        CallableDeclaration<?> jpMethod = methodName.equals(className) ? getConstructorDeclaration(cu, jDoctorConditionParamTypeNames) : getMethodDeclaration(cu, methodName, jDoctorConditionParamTypeNames);
        // Get the class declaration of the method under test
        TypeDeclaration<?> jpClass = getJavaTypeDeclarationFromClassDeclaration(cu, jpMethod);
        // Get all candidate fields of the class
        List<FieldDeclaration> jpCandidateFields = jpClass.getFields();
        // Add to the collections all the fields of the class (receiverObjectID -> this) that satisfy the predicates
        quartetList.addAll(generateFieldQuartetListFromFieldDeclarationList(jpCandidateFields, fieldFeatures));
        // Get method parameters
        List<Parameter> jpParameters = jpMethod.getParameters();
        // Iterate over the list of parameters
        for (Parameter jpParam: jpParameters) {
            // Get the parameter type
            Type jpParamType = jpParam.getType();
            // Add to the collections all the attributes of the class of the given parameter type that satisfy the predicates
            quartetList.addAll(generateFieldQuartetListFromType(project, jpParamType, fieldFeatures));
        }

        if (jpMethod instanceof MethodDeclaration) {
            // Get the return type of the method under test (methodResultID)
            Type jpMethodReturnType = ((MethodDeclaration) jpMethod).getType();
            // Add to the collections all the attributes of the return type that satisfy the predicates
            quartetList.addAll(generateFieldQuartetListFromType(project, jpMethodReturnType, fieldFeatures));
        }

        // remove duplicates and return.
        return removeDuplicates(quartetList);
    }

    /**
     * Given all the information of a method or a constructor (the method/constructor name, the list of JDoctor condition
     * parameter type names of the method/constructor, and the class name where the method/constructor is defined) and an
     * oracle on the method/constructor considered, the function collects all the non-private, non-static, non-void
     * methods of the classes (and superclasses) of all the return types of each subexpression.
     * The method searches a class within the compilation unit {@link CompilationUnit}, that defines a method
     * {@link MethodDeclaration} (or constructor {@link ConstructorDeclaration}) with the same name of {@code methodName}
     * and the same number and type name of the parameters defined by the list of JDoctor condition parameter type names
     * passed to the function {@code jDoctorConditionParamTypeNames}. If the class is found, the method generates the
     * list of non-private, non-static, non-void methods, in the form of a list of quartets of strings
     * {@link Quartet<String,String,String,String>}.
     * For example, consider the following non-private, non-static, non-void methods:
     *      {@code public int methodName1(int param1, int param2) { [method code] };}
     *      {@code public int methodName2(int param3, int param4) { [method code] };}
     * defined within one of the listed class (or superclass) under inspection.
     * The corresponding quartets will have the following structure (each string represents an information about the method,
     * and the order of the strings is important):
     * <ol>
     *     <li>method name</li>
     *     <li>package name</li>
     *     <li>class name</li>
     *     <li>method signature</li>
     * </ol>
     * Considering the previous example, the method will produce the following list of quartets:
     * {@code [
     *      ["methodName1", "package_name_of_method", "class_name_of_method", "public int methodName1(int param1, int param2);"],
     *      ["methodName2", "package_name_of_method", "class_name_of_method", "public int methodName2(int param3, int param4);"]
     * ]}
     * where
     * <ul>
     *     <li>*package_name_of_method* is the package name of the class (or superclass) where the method is declared</li>
     *     <li>*class_name_of_method* is the class name of the class (or superclass) where the method is declared</li>
     * </ul>
     * The method returns the list of quartets strings of the non-private, non-static, non-void methods collected after
     * the analysis of each subexpresstion of the oracle passed to the function, on the method/constructor under analysis.
     *
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @param methodName The name of the method or constructor under analysis
     * @param className The name of the class where the method has been defined
     * @param jDoctorConditionParamTypeNames A list of strings, representing the type names of the arguments of the
     *                                       constructor or method to find within the given JavaParser compilation unit
     *                                       {@link CompilationUnit}.
     * @param methodArguments The list of method arguments of the method/constructor under analysis, in the form of
     *                        triplets of strings {@link Triplet<String,String,String>}
     * @param project The project under analysis.
     * @param oracle The oracle defined on the method/constructor under analysis.
     * @return A list of quartets of strings {@link Quartet<String,String,String,String>}, representing the non-private,
     * non-static, non-void methods declared within the classes that represent the return types of each subexpression of the
     * oracle passed to the function, on the method/constructor under analysis.
     *
     * @throws PrimaryTypeNotFoundException If the compilation unit passed to the function does not have a primary class.
     * {@link TypeDeclaration} (in other words, the compilation unit does not have a class defined within it).
     * @throws JPMethodNotFound If the method analyzed is not found within the compilation unit passed to the function.
     * @throws JPConstructorNotFound If the constructor under analysis is not found within a class {@link TypeDeclaration}
     * declared within the compilation unit {@link CompilationUnit} passed to the function.
     */
    public List<Quartet<String, String, String, String>> getTokensOracleVariablesNonPrivateNonStaticNonVoidMethods(
            CompilationUnit cu,
            String methodName,
            String className,
            List<String> jDoctorConditionParamTypeNames,
            List<Triplet<String, String, String>> methodArguments,
            Project project,
            String oracle
    ) throws PrimaryTypeNotFoundException, JPMethodNotFound, JPConstructorNotFound {
        // Define the list of quartets of type [methodName, packageName, className, methodSignature]
        List<Quartet<String, String, String, String>> quartetList = new ArrayList<>();
        // Generate list of method features
        List<Pair<MethodFeature, Boolean>> methodFeatures = new ArrayList<>();
        // The method must be non-private
        methodFeatures.add(new Pair<>(MethodFeature.PRIVATE, Boolean.valueOf(false)));
        // The method must be non-static
        methodFeatures.add(new Pair<>(MethodFeature.STATIC, Boolean.valueOf(false)));
        // The method must be non-void
        methodFeatures.add(new Pair<>(MethodFeature.VOID, Boolean.valueOf(false)));
        // Define a list of oracle subexpression. Each oracle subexpression is represented as a linked list of tokens
        List<LinkedList<String>> oracleSubExpressions = new ArrayList<>();
        // Get all the oracle subexpressions from the original oracle, as a list of EObjects
        List<EObject> oracleSubexpressionsEObjects = this.oracleUtils.getAllMethodsAndAttributes(oracle);
        // Map each EObjects subexpressions into linked list of tokens, and add all the tranformed subexpressions to
        // the list especially created
        oracleSubExpressions.addAll(oracleSubexpressionsEObjects.stream().map(e -> {
            LinkedList<String> subExpressionTokens = new LinkedList<>();
            subExpressionTokens.addAll(SplitterUtils.split(e));
            return subExpressionTokens;
        }).toList());
        // Iterate over all the linked lists of oracle tokens (each one representing a subexpression of the original oracle)
        for (LinkedList<String> oracleSubExpression: oracleSubExpressions) {
            // Generate the oracle subexpression in string format, from the linked list of tokens
            // The receiverObjectID corresponds to the "this" identifier
            String subExpressionStr = String.join("", oracleSubExpression).replaceAll("receiverObjectID", "this");
            try {
                // Get the JavaParser return type of the subexpression
                ResolvedType jpReturnType = getReturnTypeOfExpression(
                        cu,
                        methodName,
                        className,
                        jDoctorConditionParamTypeNames,
                        methodArguments,
                        subExpressionStr
                );
                // Add to the collections all the methods of the class of the return type that satisfy the predicates
                quartetList.addAll(generateMethodQuartetListFromType(project, jpReturnType, methodFeatures));
            } catch (UnsolvedSymbolException | ConfilictingGenericTypesException | JPClassNotFoundException | ResolvedTypeNotFound e) {
                System.err.println(e.getMessage());
                // TODO: manage cases where the expression starts with java.util.Array... or com.google.org... etc.
                // Notify the impossibility to find the return type within the project, given the file path
                String fileName = String.format(
                        "return_type_not_found_%s_%s",
                        project.projectName(),
                        "getTokensOracleVariablesNonPrivateNonStaticNonVoidMethods"
                );
                FileUtils.appendToFileExclusive(
                        Path.OUTPUT.getValue(),
                        fileName,
                        FileFormat.TXT,
                        project.projectName(),
                        subExpressionStr
                );
                String errMsg = String.format(
                        "Impossible to continue to analyse the subexpression %s. Return type not found.",
                        subExpressionStr
                );
                System.err.println(errMsg);
            }
        }
        // Remove possible duplicates and return the collection
        return removeDuplicates(quartetList);
    }

    /**
     * Given all the information of a method or a constructor (the method/constructor name, the list of JDoctor condition
     * parameter type names of the method/constructor, and the class name where the method/constructor is defined) and an
     * oracle on the method/constructor considered, the function collects all the non-private, non-static attributes
     * of the classes (and superclasses) of all the return types of each subexpression.
     * The method searches a class within the compilation unit {@link CompilationUnit}, that defines a method
     * {@link MethodDeclaration} (or constructor {@link ConstructorDeclaration}) with the same name of {@code methodName}
     * and the same number and type name of the parameters defined by the list of JDoctor condition parameter type names
     * passed to the function {@code jDoctorConditionParamTypeNames}. If the class is found, the method generates the
     * list of non-private, non-static, attributes, in the form of a list of quartets of strings
     * {@link Quartet<String,String,String,String>}.
     * For example, consider the following non-private, non-static field {@code public int x = 1,y = 2;}
     * extracted from one of the three type of classes listed before.
     * Each quartet has the same structure (each string represents an information about the field, and the order of the
     * strings is important):
     * <ol>
     *     <li>attribute name</li>
     *     <li>package name</li>
     *     <li>class name</li>
     *     <li>attribute signature</li>
     * </ol>
     * Considering the previous example, given the field declaration corresponding to the statement
     * {@code public int x = 1,y = 2;}, the method will produce the list of quartets:
     * {@code [
     *      ["x", "package_name_of_field", "class_name_of_field", "public static int x = 1;"],
     *      ["y", "package_name_of_field", "class_name_of_field", "public static int y = 2;"]
     * ]}
     * where
     * <ul>
     *     <li>*package_name_of_field* is the package name of the class where the field is declared</li>
     *     <li>*class_name_of_field* is the class name of the class where the field is declared</li>
     * </ul>
     * The method returns the list of quartets strings of the non-private, non-static attributes collected after
     * the analysis of each subexpresstion of the oracle passed to the function, on the method/constructor under analysis.
     *
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @param methodName The name of the method or constructor under analysis
     * @param className The name of the class where the method has been defined
     * @param jDoctorConditionParamTypeNames A list of strings, representing the type names of the arguments of the
     *                                       constructor or method to find within the given JavaParser compilation unit
     *                                       {@link CompilationUnit}.
     * @param methodArguments The list of method arguments of the method/constructor under analysis, in the form of
     *                        triplets of strings {@link Triplet<String,String,String>}
     * @param project The project under analysis.
     * @param oracle The oracle defined on the method/constructor under analysis.
     * @return A list of quartets of strings {@link Quartet<String,String,String,String>}, representing the non-private,
     * non-static attributes declared within the classes that represent the return types of each subexpression of the
     * oracle passed to the function, on the method/constructor under analysis.
     *
     * @throws PrimaryTypeNotFoundException If the compilation unit passed to the function does not have a primary class.
     * {@link TypeDeclaration} (in other words, the compilation unit does not have a class defined within it).
     * @throws JPMethodNotFound If the method analyzed is not found within the compilation unit passed to the function.
     * @throws JPConstructorNotFound If the constructor under analysis is not found within a class {@link TypeDeclaration}
     * declared within the compilation unit {@link CompilationUnit} passed to the function.
     */
    public List<Quartet<String, String, String, String>> getTokensOracleVariablesNonPrivateNonStaticAttributes(
            CompilationUnit cu,
            String methodName,
            String className,
            List<String> jDoctorConditionParamTypeNames,
            List<Triplet<String, String, String>> methodArguments,
            Project project,
            String oracle
    ) throws PrimaryTypeNotFoundException, JPMethodNotFound, JPConstructorNotFound {
        // Define the list of quartets of type [attributeName, packageName, className, attributeSignature]
        List<Quartet<String, String, String, String>> quartetList = new ArrayList<>();
        // Generate list of attributes features
        List<Pair<FieldFeature, Boolean>> fieldFeatures = new ArrayList<>();
        // The method must be non-private
        fieldFeatures.add(new Pair<>(FieldFeature.PRIVATE, Boolean.valueOf(false)));
        // The method must be non-static
        fieldFeatures.add(new Pair<>(FieldFeature.STATIC, Boolean.valueOf(false)));
        // Define the list of oracle subexpressions
        List<LinkedList<String>> oracleSubExpressions = new ArrayList<>();
        // Get all the subexpressions of the oracle, as a list of EObjects
        List<EObject> oracleSubexpressionsEObjects = this.oracleUtils.getAllMethodsAndAttributes(oracle);
        // Map the list of EObjects into a list of linked lists of tokens.
        // Each linked list represents a subexpression of the oracle, decomposed in tokens
        oracleSubExpressions.addAll(oracleSubexpressionsEObjects.stream().map(e -> {
            LinkedList<String> subExpressionTokens = new LinkedList<>();
            subExpressionTokens.addAll(SplitterUtils.split(e));
            return subExpressionTokens;
        }).toList());
        // Iterate over each subexpression in the form of linked list of tokens
        for (LinkedList<String> oracleSubExpression: oracleSubExpressions) {
            // Convert the current subexpression into a string, joining the tokens of the linked list that compose it
            String subExpressionStr = String.join("", oracleSubExpression).replaceAll("receiverObjectID", "this");
            try {
                // Get the return type of the subexpression
                ResolvedType jpReturnType = getReturnTypeOfExpression(
                    cu,
                    methodName,
                    className,
                    jDoctorConditionParamTypeNames,
                    methodArguments,
                    subExpressionStr
                );
                // Add to the collections all the attributes of the class of the given parameter type that satisfy the predicates
                quartetList.addAll(generateFieldQuartetListFromType(project, jpReturnType, fieldFeatures));
            } catch (UnsolvedSymbolException | ConfilictingGenericTypesException | JPClassNotFoundException | ResolvedTypeNotFound e) {
                System.err.println(e.getMessage());
                // Notify the impossibility to find the return type within the project, given the file path
                String fileName = String.format(
                        "return_type_not_found_%s_%s",
                        project.projectName(),
                        "getTokensOracleVariablesNonPrivateNonStaticNonVoidAttributes"
                );
                FileUtils.appendToFileExclusive(
                        Path.OUTPUT.getValue(),
                        fileName,
                        FileFormat.TXT,
                        project.projectName(),
                        subExpressionStr
                );
                String errMsg = String.format(
                        "Impossible to continue to analyse the subexpression %s. Return type not found.",
                        subExpressionStr
                );
                System.err.println(errMsg);
                break;
            }
        }
        return removeDuplicates(quartetList);
    }

    /**
     * The method collects the list of all the JavaParser type declarations {@link TypeDeclaration} corresponding to all
     * the superclasses of a JavaParser class {@link TypeDeclaration} passed to the function.
     *
     * @param cu A JavaParser compilation unit {@link CompilationUnit}
     * @param jpClass An instance of a JavaParser type declaration {@link TypeDeclaration} representing the class from
     *                which collect all the superclasses.
     * @param project The project under analysis.
     * @param callingMethod The name of the method under analysis
     * @param visited The list of classes already visited
     * @return The list of all the JavaParser type declarations {@link TypeDeclaration} representing the superclasses
     * of the class passed to the function.
     * @throws PackageDeclarationNotFoundException If the compilation unit passed to the function does not have a package
     * declaration.
     */
    public List<Pair<TypeDeclaration<?>, PackageDeclaration>> getAllSuperClasses(
            CompilationUnit cu,
            TypeDeclaration<?> jpClass,
            Project project,
            String callingMethod,
            ArrayList<String> visited
    ) throws PackageDeclarationNotFoundException {
        ArrayList<Pair<TypeDeclaration<?>, PackageDeclaration>> pairList = new ArrayList<>();
        PackageDeclaration jpPackage = getPackageDeclarationFromCompilationUnit(cu);
        // Iterate over the superclasses of the considered class
        if (jpClass.isClassOrInterfaceDeclaration()) {
            jpClass.asClassOrInterfaceDeclaration().getExtendedTypes()
                .stream()
                .filter(type -> !visited.contains(type.resolve().describe()))
                .forEach(type -> {
                    // Get fully qualified name of the class
                    String qualifiedName = type.resolve().describe();
                    // Add fully superclass qualified name to visited list
                    visited.add(qualifiedName); // return the package   java.org.A.B.F --> java/org/A/B/F
                    // Get the compilation unit of the superclass
                    Optional<CompilationUnit> cuSuperClass = getCompilationUnitFromQualifiedName(qualifiedName, project);
                    // Check if the compilation unit is empty
                    if (cuSuperClass.isEmpty()) {
                        // Notify the impossibility to find the superclass within the project, given the file path
                        String fileName = String.format("superclass_not_found_%s_%s", project.projectName(), callingMethod);
                        FileUtils.appendToFileExclusive(
                                Path.OUTPUT.getValue(),
                                fileName,
                                FileFormat.TXT,
                                project.projectName(),
                                qualifiedName
                        );
                    } else {
                        try {
                            TypeDeclaration<?> jpSuperClass = getPrimaryClassFromCompilationUnit(cuSuperClass.get());
                            pairList.addAll(getAllSuperClasses(
                                    cuSuperClass.get(),
                                    jpSuperClass,
                                    project,
                                    callingMethod,
                                    visited
                            ));
                        } catch (PrimaryTypeNotFoundException | PackageDeclarationNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
        }
        pairList.add(new Pair<>(jpClass, jpPackage));
        return pairList;
    }
}
