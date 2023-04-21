package star.tratto.dataset.oracles;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;
import star.tratto.util.javaparser.JavaParserUtils;
import star.tratto.dataset.oracles.JDoctorCondition.PreCondition;
import star.tratto.dataset.oracles.JDoctorCondition.PostCondition;
import star.tratto.dataset.oracles.JDoctorCondition.ThrowsCondition;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;

/**
 * ProjectOracleGenerator generates all oracle data points of a project using
 * a list of JDoctor conditions associated with its classes and methods.
 */
public class ProjectOracleGenerator {
    private int idCounter;
    private int checkpoint;
    private Project project;
    private JDoctorCondition[] jDoctorConditions;
    private JavaParser javaParser;

    /**
     * Create a new instance of ProjectOracleGenerator.
     * The constructor sets up:
     * <ul>
     *     <li> A counter ({@link #idCounter}) used to provide unique
     *     identifiers of the generated oracle data points.</li>
     *     <li>A checkpoint that memorizes the last identifier used to
     *     generate a data point within a given project. The checkpoint is
     *     used to calculate the total number of oracles generated between
     *     projects.</li>
     * </ul>
     *
     */
    public ProjectOracleGenerator() {
        this.idCounter = 0;
        this.checkpoint = 0;
    }

    /**
     * This method sets up a Java project for analysis. It creates a reference
     * to the Project and associated JDoctor conditions, and creates an
     * instance of JavaParserUtils.
     * @param project The Java project under analysis
     * @param jDocConditions A list of conditions generated by JDoctor from the project.
     */
    public void loadProject(
            Project project,
            JDoctorCondition[] jDocConditions
    ) {
        this.project = project;
        this.javaParser = JavaParserUtils.getJavaParser();
        this.jDoctorConditions = jDocConditions;
    }

    /**
     * This method generates all oracle data points for the loaded Java
     * project from a list of JDoctor conditions.
     * @return The list of oracle data points {@link OracleDatapoint} produced
     * for the loader project, from the JDoctor conditions.
     */
    public List<OracleDatapoint> generate() {
        List<OracleDatapoint> oracleDPs = new ArrayList<>();

        // Generate an OracleDatapoint for each JDoctor condition.
        for (JDoctorCondition jDoctorCondition : this.jDoctorConditions) {
            List<ThrowsCondition> throwsConditions = jDoctorCondition.getThrowsConditions();
            List<PreCondition> preConditions = jDoctorCondition.getPreCondition();
            List<PostCondition> postConditions = jDoctorCondition.getPostConditions();
        }

        return oracleDPs;
    }

    /**
     * The method generates all the oracle data-points for the Java project loaded, from the list of JDoctor conditions
     * associated.
     * @return The list of oracle data-points {@link OracleDP} produced for the loaded project, from the JDoctor
     * conditions associated.
     */
    public List<OracleDP> generateS() {
        List<OracleDP> oracleDPs = new ArrayList<>();
        List<HashMap<OracleDPAttrKey,OracleDPAttrValue>> oraclesDPMap = new ArrayList<>();
        HashMap<OracleDPAttrKey, OracleDPAttrValue> defaultTokensGrammarMap = this.defaultTokensGrammar();
        HashMap<OracleDPAttrKey, OracleDPAttrValue> defaultTokensGeneralValuesMap = this.defaultTokensGeneralValues();
        HashMap<OracleDPAttrKey, OracleDPAttrValue> projectInfoMap = this.extractProjectInfo();
        HashMap<OracleDPAttrKey, OracleDPAttrValue> projectClassAndMethodTokensMap = this.extractProjectClassAndMethodTokens();


        for (JDocCondition jDocCondition : jDocConditions) {
            List<HashMap<OracleDPAttrKey,OracleDPAttrValue>> conditionMapList = new ArrayList<>();
            List<ThrowsCondition> throwsConditions = jDocCondition.throwsConditions();
            List<PreCondition> preConditions = jDocCondition.preConditions();
            List<PostCondition> postConditions = jDocCondition.postConditions();

            Operation operation = jDocCondition.operation();
            Identifiers identifiers = jDocCondition.identifiers();

            conditionMapList.addAll(
                    throwsConditions
                            .stream()
                            .map(this::extractThrowConditionInfo)
                            .toList()
            );
            conditionMapList.addAll(
                    preConditions
                            .stream()
                            .map(this::extractPreConditionInfo)
                            .toList()
            );

            if (postConditions.size() > 0 ) {
                conditionMapList.add(extractPostConditionInfo(postConditions));
            }

            conditionMapList
                    .stream()
                    .peek(condition -> condition.putAll(this.assignID()))
                    .peek(condition -> condition.putAll(defaultTokensGrammarMap))
                    .peek(condition -> condition.putAll(defaultTokensGeneralValuesMap))
                    .peek(condition -> condition.putAll(projectInfoMap))
                    .peek(condition -> condition.putAll(projectClassAndMethodTokensMap))
                    .peek(condition -> condition.putAll(this.extractClassInfo(operation)))
                    .peek(condition -> condition.putAll(this.extractMethodInfo(operation)))
                    .peek(condition -> condition.putAll(this.extractOracleInfo(
                            operation,
                            (List<Triplet<String, String, String>>) condition.get(OracleDPAttrKey.TOKENS_METHOD_ARGUMENTS).value(),
                            (String) condition.get(OracleDPAttrKey.ORACLE).value()
                    )))
                    .toList();
            oraclesDPMap.addAll(conditionMapList);
        }
        // Notify
        System.out.println(String.format("Processed %s conditions.", this.idCounter - this.checkpoint));
        this.checkpoint = this.idCounter;
        // Generate oracle datapoints from the list of hashMap
        oracleDPs.addAll(oraclesDPMap.stream().map(o -> OracleDP.generateOracleDPFromHashMap(o)).toList());
        // Return the oracle datapoints
        return oracleDPs;
    }

    /**
     * The method generates a unique identifier for an oracle data-point.
     * @return A hash map {@link HashMap<OracleDPAttrKey, OracleDPAttrValue>} composed of a single key
     * {@code OracleDPAttrKey.ID} whose value contains an integer representing the identifier of the oracle data-point.
     */
    private HashMap<OracleDPAttrKey, OracleDPAttrValue> assignID() {
        HashMap<OracleDPAttrKey,OracleDPAttrValue> idMap = new HashMap<>();
        idMap.put(OracleDPAttrKey.ID, new OracleDPAttrValue<>(this.idCounter));
        this.idCounter++;
        return idMap;
    }

    /**
     * The method generates the default grammar tokens for a given data-point.
     * @return A hash map {@link HashMap<OracleDPAttrKey, OracleDPAttrValue>} composed of a single key
     * {@code OracleDPAttrKey.TOKENS_GENERAL_GRAMMAR} whose value contains a list of the default grammar tokens
     */
    private HashMap<OracleDPAttrKey, OracleDPAttrValue> defaultTokensGrammar() {
        HashMap<OracleDPAttrKey, OracleDPAttrValue> defaultTokensGrammarMap = new HashMap<>();
        String tokensGrammarPath = FileUtils.getAbsolutePathToFile(Path.REPOS.getValue(), FileName.TOKENS_GRAMMAR, FileFormat.JSON);
        List<String> tokenGrammarList = (List<String>) FileUtils.readJSONList(tokensGrammarPath);
        defaultTokensGrammarMap.put(OracleDPAttrKey.TOKENS_GENERAL_GRAMMAR, new OracleDPAttrValue<>(tokenGrammarList));
        return defaultTokensGrammarMap;
    }

    /**
     * The method generates the default general values tokens for a given data-point, reading them from a json file where
     * they are listed.
     * @return A hash map {@link HashMap<OracleDPAttrKey, OracleDPAttrValue>} composed of a single key
     * {@code OracleDPAttrKey.TOKENS_GENERAL_VALUES_GLOBAL_DICTIONARY} whose value contains a list of the default
     * values tokens
     */
    private HashMap<OracleDPAttrKey, OracleDPAttrValue> defaultTokensGeneralValues() {
        HashMap<OracleDPAttrKey,OracleDPAttrValue> defaultTokensGrammarMap = new HashMap<>();
        String tokenGeneralValuesPath = FileUtils.getAbsolutePathToFile(Path.REPOS.getValue(), FileName.TOKENS_GENERAL_VALUES, FileFormat.JSON);
        List<Pair<String,String>> tokenGeneralValuesList = ((List<List<String>>) FileUtils.readJSONList(tokenGeneralValuesPath)).stream().map(tokenList -> new Pair<>(tokenList.get(0),tokenList.get(1))).toList();
        defaultTokensGrammarMap.put(OracleDPAttrKey.TOKENS_GENERAL_VALUES_GLOBAL_DICTIONARY, new OracleDPAttrValue<>(tokenGeneralValuesList));
        return defaultTokensGrammarMap;
    }

    /**
     * The method extracts all the general information of the class where the method/constructor to which a JDoctor
     * condition refers is defined.
     * @param operation A JDoctor operation object of a JDoctor condition.
     * @return A hash map {@link HashMap<OracleDPAttrKey, OracleDPAttrValue>} composed of four keys:
     * <ol>
     *     <li>{@code OracleDPAttrKey.CLASS_NAME} - the corresponding value contains the name of the class where the
     *     method/constructor to which a JDoctor condition refers is defined.</li>
     *     <li>{@code OracleDPAttrKey.PACKAGE_NAME} - the corresponding value contains the package name of the class
     *     where the  method/constructor to which a JDoctor condition refers is defined.</li>
     *     <li>{@code OracleAttrKey.CLASS_JAVADOC} - the corresponding value contains the Javadoc comment of the class
     *     where the method/constructor to which a JDoctor condition refers is defined.</li>
     *     <li>{@code OracleAttrKey.CLASS_SOURCE_CODE} - the corresponding value contains the source code of the class
     *     where the method/constructor to which a JDoctor condition refers is defined.</li>
     * </ol>
     */
    private HashMap<OracleDPAttrKey, OracleDPAttrValue> extractClassInfo(
            Operation operation
    ) {
        HashMap<OracleDPAttrKey, OracleDPAttrValue> classMap = new HashMap<>();
        String classJavadoc = "";
        String classSourceCode = "";
        List<String> pathList = TypeUtils.getPathList(operation.classname());
        List<String> packageList = TypeUtils.getPackageList(pathList);
        String className = TypeUtils.getClassNameFromPathList(pathList);
        String packageName = TypeUtils.getPackageNameFromPackageList(packageList);
        Optional<CompilationUnit> cu = this.getClassCompilationUnit(operation);
        if (cu.isPresent()) {
            try {
                jpUtils.checkCompilationUnitConsistency(cu.get(), className, packageName);
            } catch (PrimaryTypeNotFoundException e) {
                e.printStackTrace();
                return classMap;
            } catch (PackageDeclarationNotFoundException e) {
                e.printStackTrace();
                return classMap;
            }
            try {
                classJavadoc = jpUtils.getJavadocCommentFromPrimaryClass(cu.get());
                classSourceCode = jpUtils.getSourceCodeFromPrimaryClass(cu.get());
            } catch (PrimaryTypeNotFoundException e) {
                e.printStackTrace();
                return classMap;
            }
        }
        classMap.put(OracleDPAttrKey.CLASS_NAME, new OracleDPAttrValue<>(className));
        classMap.put(OracleDPAttrKey.PACKAGE_NAME, new OracleDPAttrValue<>(packageName));
        classMap.put(OracleDPAttrKey.CLASS_JAVADOC, new OracleDPAttrValue<>(classJavadoc));
        classMap.put(OracleDPAttrKey.CLASS_SOURCE_CODE, new OracleDPAttrValue<>(classSourceCode));
        return classMap;
    }

    /**
     * The method extracts all the general information of the method/constructor to which a JDoctor condition refers.
     * @param operation A JDoctor operation object of a JDoctor condition.
     * @return A hash map {@link HashMap<OracleDPAttrKey, OracleDPAttrValue>} composed of four keys:
     * <ol>
     *     <li>{@code OracleDPAttrKey.METHOD_JAVADOC} - the corresponding value contains the Javadoc comment of the
     *     method/constructor to which a JDoctor condition refers.</li>
     *     <li>{@code OracleDPAttrKey.METHOD_SOURCE_CODE} - the corresponding value contains the source code of the
     *     method/constructor to which a JDoctor condition refers.</li>
     *     <li>{@code OracleAttrKey.TOKENS_METHOD_JAVADOC_VALUES} - the corresponding value contains the integer, real,
     *     and string values defined within the Javadoc comment of the method/constructor to which a JDoctor condition refers,
     *     in the form of a list of pairs of strings {@link Pair<String,String>}, where the first element of each pair is the
     *     numeric or string value, while the second element is the type of the value {@link JavadocValueType} (integer,
     *     real, or string).</li>
     *     <li>{@code OracleAttrKey.TOKENS_METHOD_ARGUMENTS} - the corresponding value contains the list of arguments of
     *     the method/constructor to which a JDoctor condition refers, in the form of a list of triplets of strings
     *     {@link Triplet<String,String,String>}. Each triplet contains the argument name, package name, and type name of
     *     each argument.</li>
     * </ol>
     */
    private HashMap<OracleDPAttrKey, OracleDPAttrValue> extractMethodInfo(
            Operation operation
    ) {
        HashMap<OracleDPAttrKey, OracleDPAttrValue> methodMap = new HashMap<>();
        String className = operation.classname();
        String methodName = operation.name();
        List<String> jDoctorConditionParamTypeNames = operation.parameterTypes();
        List<Pair<String, String>> methodJavadocValuesTokenList = new ArrayList<>();
        List<Triplet<String, String, String>> methodArgumentsTokenList = new ArrayList<>();
        String methodJavadoc = "";
        String methodSourceCode = "";
        Optional<CompilationUnit> cu = this.getClassCompilationUnit(operation);

        if (cu.isPresent()) {
            // if constructor else method
            if (className.equals(methodName)) {
                try {
                    methodJavadoc += this.jpUtils.getJavadocCommentFromConstructorDeclaration(cu.get(), jDoctorConditionParamTypeNames);
                    methodSourceCode += jpUtils.getSourceCodeFromConstructorDeclaration(cu.get(), jDoctorConditionParamTypeNames);
                    methodJavadocValuesTokenList.addAll(jpUtils.getJavadocValuesFromConstructorDeclaration(cu.get(), jDoctorConditionParamTypeNames));
                    methodArgumentsTokenList.addAll(jpUtils.getArgumentsNameAndPackageAndTypeFromConstructorDeclaration(cu.get(), jDoctorConditionParamTypeNames));
                } catch (PrimaryTypeNotFoundException e) {
                    e.printStackTrace();
                } catch (JPConstructorNotFound e) {
                    e.printStackTrace();
                } catch (PackageDeclarationNotFoundException e) {
                    e.printStackTrace();
                } catch (JPClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    methodJavadoc += this.jpUtils.getJavadocCommentFromMethodDeclaration(cu.get(), methodName, jDoctorConditionParamTypeNames);
                    methodSourceCode += jpUtils.getSourceCodeFromMethodDeclaration(cu.get(), methodName, jDoctorConditionParamTypeNames);
                    methodJavadocValuesTokenList.addAll(jpUtils.getJavadocValuesFromMethodDeclaration(cu.get(), methodName, jDoctorConditionParamTypeNames));
                    methodArgumentsTokenList.addAll(jpUtils.getArgumentsNameAndPackageAndTypeFromMethodDeclaration(cu.get(), methodName, className, jDoctorConditionParamTypeNames));
                } catch (PrimaryTypeNotFoundException e) {
                    e.printStackTrace();
                } catch (JPMethodNotFound e) {
                    e.printStackTrace();
                } catch (JPConstructorNotFound e) {
                    e.printStackTrace();
                } catch (PackageDeclarationNotFoundException e) {
                    e.printStackTrace();
                } catch (JPClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        if (methodJavadoc.equals("")) {
            String errMsg = String.format("Method %s of class %s not found.", methodName, className);
            System.out.println(errMsg);
        }
        if (methodSourceCode.equals("")) {
            String errMsg = String.format("Source code %s of class %s not found.", methodName, className);
            System.out.println(errMsg);
        }

        methodMap.put(OracleDPAttrKey.METHOD_JAVADOC, new OracleDPAttrValue<>(methodJavadoc));
        methodMap.put(OracleDPAttrKey.METHOD_SOURCE_CODE, new OracleDPAttrValue<>(methodSourceCode));
        methodMap.put(OracleDPAttrKey.TOKENS_METHOD_JAVADOC_VALUES, new OracleDPAttrValue<>(methodJavadocValuesTokenList));
        methodMap.put(OracleDPAttrKey.TOKENS_METHOD_ARGUMENTS, new OracleDPAttrValue<>(methodArgumentsTokenList));
        return methodMap;
    }

    /**
     * The method extracts all the information of the oracle defined in a JDoctor condition.
     * @param operation A JDoctor operation object of a JDoctor condition.
     * @param methodArgumentsTokenList The list of arguments of the method/constructor to which a JDoctor condition refers.
     *                                 The list is in the form of triplets of strings {@link Triplet<String,String,String>}
     *                                 representing the argument name, package name, and type name of each argument.
     * @param oracle The oracle defined within a JDoctor condition.
     * @return A hash map {@link HashMap<OracleDPAttrKey, OracleDPAttrValue>} composed of four keys:
     * <ol>
     *     <li>{@code OracleDPAttrKey.TOKENS_METHOD_VARIABLES_NON_PRIVATE_NON_STATIC_NON_VOID_METHODS} - the corresponding
     *     value contains a list of quartets of strings {@link Quartet <String,String,String,String>}, representing
     *     (i) the non-private, non-static, non-void methods declared within the class and superclasses where the method/
     *     constructor to which a JDoctor condition refers is defined, (ii) the non-private, non-static, non-void methods
     *     declared within the class and superclasses of the parameter types of the method/constructor to which a JDoctor
     *     condition refers is defined, and (iii) the non-private, non-static, non-void methods declared within the class
     *     and superclasses of the return type of the method/constructor to which a JDoctor condition refers is defined.</li>
     *     <li>{@code OracleDPAttrKey.TOKENS_METHOD_VARIABLES_NON_PRIVATE_NON_STATIC_ATTRIBUTES} - the corresponding value
     *     contains a list of quartets of strings {@link Quartet<String,String,String,String>}, representing (i) the
     *     non-private, non-static attributes declared within the class and superclasses where the method/constructor
     *     to which a JDoctor condition refers is defined, (ii) the non-private, non-static attributes declared within
     *     the class and superclasses of the parameter types of the method/constructor to which a JDoctor condition refers
     *     is defined, and (iii) the non-private, non-static attributes declared within the class and superclasses of the
     *     return type of the method/constructor to which a JDoctor condition refers is defined (no return type if the
     *     method considered is a constructor).
     *     <li>{@code OracleAttrKey.TOKENS_ORACLE_VARIABLES_NON_PRIVATE_NON_STATIC_NON_VOID_METHODS} - the corresponding
     *     value contains the list of quartets of strings {@link Quartet<String,String,String,String>}, representing
     *     the non-private, non-static, non-void methods declared within the classes that represent the return types of
     *     each subexpression of the oracle defined within the JDoctor condition.</li>
     *     <li>{@code OracleAttrKey.TOKENS_ORACLE_VARIABLES_NON_PRIVATE_NON_STATIC_ATTRIBUTES} - the corresponding
     *     value contains a list of quartets of strings {@link Quartet<String,String,String,String>}, representing the
     *     non-private, non-static attributes declared within the classes that represent the return types of each
     *     subexpression of the oracle defined within the JDoctor condition.
     * </ol>
     */
    private HashMap<OracleDPAttrKey, OracleDPAttrValue> extractOracleInfo(
            Operation operation,
            List<Triplet<String, String, String>> methodArgumentsTokenList,
            String oracle
    ) {
        HashMap<OracleDPAttrKey, OracleDPAttrValue> oracleMap = new HashMap<>();
        String className = operation.classname();
        String methodName = operation.name();
        List<String> jDoctorConditionParamTypeNames = operation.parameterTypes();
        List<Quartet<String, String, String, String>> tokensMethodMethods = new ArrayList<>();
        List<Quartet<String, String, String, String>> tokensMethodAttributes = new ArrayList<>();
        List<Quartet<String, String, String, String>> tokensOracleMethods = new ArrayList<>();
        List<Quartet<String, String, String, String>> tokensOracleAttributes = new ArrayList<>();
        Optional<CompilationUnit> cu = this.getClassCompilationUnit(operation);
        if (cu.isPresent()) {
            try {
                tokensMethodMethods = this.jpUtils.getTokensMethodVariablesNonPrivateNonStaticNonVoidMethods(
                        cu.get(),
                        methodName,
                        className,
                        jDoctorConditionParamTypeNames,
                        project
                );
                tokensMethodAttributes = this.jpUtils.getTokensMethodVariablesNonPrivateNonStaticAttributes(
                        cu.get(),
                        methodName,
                        className,
                        jDoctorConditionParamTypeNames,
                        project
                );
                tokensOracleMethods = this.jpUtils.getTokensOracleVariablesNonPrivateNonStaticNonVoidMethods(
                        cu.get(),
                        methodName,
                        className,
                        jDoctorConditionParamTypeNames,
                        methodArgumentsTokenList,
                        project,
                        oracle
                );
                tokensOracleAttributes = this.jpUtils.getTokensOracleVariablesNonPrivateNonStaticAttributes(
                        cu.get(),
                        methodName,
                        className,
                        jDoctorConditionParamTypeNames,
                        methodArgumentsTokenList,
                        project,
                        oracle
                );
            } catch (PrimaryTypeNotFoundException e) {
                e.printStackTrace();
            } catch (JPMethodNotFound e) {
                e.printStackTrace();
            } catch (JPConstructorNotFound e) {
                e.printStackTrace();
            } catch (PackageDeclarationNotFoundException e) {
                e.printStackTrace();
            } catch (JPClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        oracleMap.put(OracleDPAttrKey.TOKENS_METHOD_VARIABLES_NON_PRIVATE_NON_STATIC_NON_VOID_METHODS, new OracleDPAttrValue(tokensMethodMethods));
        oracleMap.put(OracleDPAttrKey.TOKENS_METHOD_VARIABLES_NON_PRIVATE_NON_STATIC_ATTRIBUTES, new OracleDPAttrValue(tokensMethodAttributes));
        oracleMap.put(OracleDPAttrKey.TOKENS_ORACLE_VARIABLES_NON_PRIVATE_NON_STATIC_NON_VOID_METHODS, new OracleDPAttrValue(tokensOracleMethods));
        oracleMap.put(OracleDPAttrKey.TOKENS_ORACLE_VARIABLES_NON_PRIVATE_NON_STATIC_ATTRIBUTES, new OracleDPAttrValue(tokensOracleAttributes));
        return oracleMap;
    }

    /**
     * The method extracts all the information related to a JDoctor pre-condition {@link PreCondition}, passed to the
     * function.
     * @param preCondition A JDoctor pre-condition {@link PreCondition}.
     * @return A hash map {@link HashMap<OracleDPAttrKey, OracleDPAttrValue>} composed of three keys:
     * <ol>
     *     <li>{@code OracleDPAttrKey.ORACLE_TYPE} - the corresponding value contains the identifier of the type of
     *     condition (always {@code OracleType.PRE})</li>
     *     <li>{@code OracleDPAttrKey.JAVADOC_TAG} - the corresponding value contains the string representation
     *     of the Javadoc tag from which the JDoctor pre-condition has been generated.</li>
     *     <li>{@code OracleDPAttrKey.ORACLE} - the corresponding value contains the oracle associated to the JDoctor
     *     pre-condition.</li>
     * </ol>
     */
    private HashMap<OracleDPAttrKey, OracleDPAttrValue> extractPreConditionInfo(
            PreCondition preCondition
    ) {
        HashMap<OracleDPAttrKey, OracleDPAttrValue> preConditionMap = new HashMap<>();
        OracleType oracleType = OracleType.PRE;
        String javadocTag = preCondition.description();
        Guard guard = preCondition.guard();
        String oracle = guard.condition();
        preConditionMap.put(OracleDPAttrKey.ORACLE_TYPE, new OracleDPAttrValue<>(oracleType));
        preConditionMap.put(OracleDPAttrKey.JAVADOC_TAG, new OracleDPAttrValue<>(javadocTag));
        preConditionMap.put(OracleDPAttrKey.ORACLE, new OracleDPAttrValue<>(oracle));
        return preConditionMap;
    }

    /**
     * The method extracts all the information related to a list of JDoctor post-conditions {@link PreCondition}, passed
     * to the function. The list can be composed of one or two elements. If the list is composed of two elements, the two
     * post conditions are complementary, and refers to a condition of a ternary operator (if the condition is true, the
     * first post-condition must be verified, otherwise the second post-condition must be verified.
     * @param postConditions A list of JDoctor post-conditions {@link PostCondition}.
     * @return A hash map {@link HashMap<OracleDPAttrKey,OracleDPAttrValue>} composed of three keys:
     * <ol>
     *     <li>{@code OracleDPAttrKey.ORACLE_TYPE} - the corresponding value contains the identifier of the type of
     *     condition (always {@code OracleType.POST})</li>
     *     <li>{@code OracleDPAttrKey.JAVADOC_TAG} - the corresponding value contains the string representation
     *     of the Javadoc tag from which the JDoctor post-conditions has been generated.</li>
     *     <li>{@code OracleDPAttrKey.ORACLE} - the corresponding value contains the ternary oracle associated to the JDoctor
     *     post-condition(s). If the list is composed of two elements, the oracle generated is in the form of a ternary
     *     operator.</li>
     * </ol>
     */
    private HashMap<OracleDPAttrKey, OracleDPAttrValue> extractPostConditionInfo(
            List<PostCondition> postConditions
    ) {
        assert postConditions.size() <= 2;
        HashMap<OracleDPAttrKey, OracleDPAttrValue> postConditionMap = new HashMap<>();
        OracleType oracleType = OracleType.NORMAL_POST;

        PostCondition firstPostCondition = postConditions.get(0);
        String firstPostJavadocTag = firstPostCondition.description();
        Guard firstPostGuard = firstPostCondition.guard();
        Property firstPostProperty = firstPostCondition.property();
        String oracle = String.format("%s ? %s : ", firstPostGuard.condition(), firstPostProperty.condition());
        if(postConditions.size() == 2) {
            PostCondition secondPostCondition = postConditions.get(0);
            String secondPostJavadocTag = secondPostCondition.description();
            assert firstPostJavadocTag == secondPostJavadocTag;
            Property secondProperty = secondPostCondition.property();
            oracle += secondProperty.condition();
        } else {
            oracle += "true";
        }
        postConditionMap.put(OracleDPAttrKey.ORACLE_TYPE, new OracleDPAttrValue<>(oracleType));
        postConditionMap.put(OracleDPAttrKey.JAVADOC_TAG, new OracleDPAttrValue<>(firstPostJavadocTag));
        postConditionMap.put(OracleDPAttrKey.ORACLE, new OracleDPAttrValue<>(oracle));
        return postConditionMap;
    }

    /**
     * The method extracts all the information about all the classes and the methods of the project under analysis.
     * @return A hash map {@link HashMap<OracleDPAttrKey, OracleDPAttrValue>} composed of three keys:
     * <ol>
     *     <li>{@code OracleDPAttrKey.TOKENS_PROJECT_CLASSES} - the corresponding value contains a list of pairs of strings
     *     {@link Pair<String,String>}, each one representing the class name and the package name of a class of the
     *     Java project under analysis.
     *     <li>{@code OracleDPAttrKey.TOKENS_PROJECT_CLASSES_NON_PRIVATE_STATIC_NON_VOID_METHODS} - the corresponding value
     *     contains the quartets of strings {@link Quartet<String,String,String,String>}, representing the methods declared
     *     within each class of the Java project under analysis.</li>
     *     <li>{@code OracleDPAttrKey.TOKENS_PROJECT_CLASSES_NON_PRIVATE_STATIC_ATTRIBUTES} - the corresponding value contains
     *     the quartets of strings {@link Quartet<String,String,String,String>}, representing the attributes declared
     *     within each class of the Java project under analysis. The attributes collected are static and non-private.</li>
     * </ol>
     */
    private HashMap<OracleDPAttrKey, OracleDPAttrValue> extractProjectClassAndMethodTokens() {
        HashMap<OracleDPAttrKey,OracleDPAttrValue> projectMapList = new HashMap<>();
        String srcPath = this.project.srcPath();
        File srcDir = new File(srcPath);
        List<File> javaFiles = FileUtils.extractJavaFilesFromDirectory(srcDir);
        List<Pair<String, String>> projectClassesTokenList = new ArrayList<>();
        List<Quartet<String, String, String, String>> projectClassesNonPrivateStaticNonVoidMethodsTokenList = new ArrayList<>();
        List<Quartet<String, String, String, String>> projectClassesNonPrivateStaticAttributes = new ArrayList<>();

        String ignoreFilePath = Paths.get(Path.REPOS.getValue(), FileName.IGNORE_FILE.getValue() + FileFormat.JSON.getValue()).toString();
        List<String> ignoreFileList = (List<String>) FileUtils.readJSONList(ignoreFilePath);

        for (File javaFile : javaFiles) {
            // Get the name of the file without extension
            String javaFileName = javaFile.getName().replace(FileFormat.JAVA.getValue(),"");
            // Skip the file if it is in the black list
            if (ignoreFileList.contains(javaFileName)) {
                continue;
            }
            Optional<CompilationUnit> cu = jpUtils.getCompilationUnitFromFile(javaFile);
            if (cu.isEmpty()) {
                // Add Warning
                continue;
            }
            try {
                projectClassesTokenList.addAll(jpUtils.getClassesNamesAndPackagesFromCompilationUnit(cu.get()));
                projectClassesNonPrivateStaticNonVoidMethodsTokenList.addAll(jpUtils.getClassesMethodsNonPrivateNonStaticNonVoidFromCompilationUnit(cu.get()));
                projectClassesNonPrivateStaticAttributes.addAll(jpUtils.getClassesNonPrivateStaticAttributesFromCompilationUnit(cu.get()));
            } catch (PackageDeclarationNotFoundException | PrimaryTypeNotFoundException e) {
                e.printStackTrace();
            }
        }
        projectMapList.put(
                OracleDPAttrKey.TOKENS_PROJECT_CLASSES,
                new OracleDPAttrValue<>(projectClassesTokenList)
        );
        projectMapList.put(
                OracleDPAttrKey.TOKENS_PROJECT_CLASSES_NON_PRIVATE_STATIC_NON_VOID_METHODS,
                new OracleDPAttrValue<>(projectClassesNonPrivateStaticNonVoidMethodsTokenList)
        );
        projectMapList.put(
                OracleDPAttrKey.TOKENS_PROJECT_CLASSES_NON_PRIVATE_STATIC_ATTRIBUTES,
                new OracleDPAttrValue<>(projectClassesNonPrivateStaticAttributes)
        );
        return projectMapList;
    }

    /**
     * The method extracts the name of the project under analysis.
     * @return A hash map {@link HashMap<OracleDPAttrKey, OracleDPAttrValue>} composed of a single key
     * {@code OracleDPAttrKey.PROJECT_NAME} whose value contains a string representing the name of the project under
     * analysis.
     */
    private HashMap<OracleDPAttrKey, OracleDPAttrValue> extractProjectInfo() {
        HashMap<OracleDPAttrKey,OracleDPAttrValue> projectMapList = new HashMap<>();
        String projectName = this.project.projectName();
        projectMapList.put(OracleDPAttrKey.PROJECT_NAME, new OracleDPAttrValue<>(projectName));
        return projectMapList;
    }

    /**
     * The method extracts all the information related to a JDoctor throws-condition {@link ThrowsCondition}, passed to the
     * function.
     * @param throwsCondition A JDoctor throws-condition {@link ThrowsCondition}.
     * @return A hash map {@link HashMap<OracleDPAttrKey, OracleDPAttrValue>} composed of three keys:
     * <ol>
     *     <li>{@code OracleDPAttrKey.ORACLE_TYPE} - the corresponding value contains the identifier of the type of
     *     condition (always {@code OracleType.EXCEPT_POST})</li>
     *     <li>{@code OracleDPAttrKey.JAVADOC_TAG} - the corresponding value contains the string representation
     *     of the Javadoc tag from which the JDoctor throws-condition has been generated.</li>
     *     <li>{@code OracleDPAttrKey.ORACLE} - the corresponding value contains the oracle associated to the JDoctor
     *     throws-condition.</li>
     * </ol>
     */
    private HashMap<OracleDPAttrKey, OracleDPAttrValue> extractThrowConditionInfo(
            ThrowsCondition throwsCondition
    ) {
        HashMap<OracleDPAttrKey, OracleDPAttrValue> throwsConditionMap = new HashMap<>();
        OracleType oracleType = OracleType.EXCEPT_POST;
        String javadocTag = throwsCondition.description();
        Guard guard = throwsCondition.guard();
        String oracle = guard.condition();
        throwsConditionMap.put(OracleDPAttrKey.ORACLE_TYPE, new OracleDPAttrValue<>(oracleType));
        throwsConditionMap.put(OracleDPAttrKey.JAVADOC_TAG, new OracleDPAttrValue<>(javadocTag));
        throwsConditionMap.put(OracleDPAttrKey.ORACLE, new OracleDPAttrValue<>(oracle));
        return throwsConditionMap;
    }

    /**
     * The method gets the JavaParser compilation unit {@link CompilationUnit} corresponding to the class to which the
     * operation object of a JDoctor condition refers.
     * @param operation A JDoctor operation object of a JDoctor condition.
     * @return An optional JavaParaser compilation unit {@link CompilationUnit}. The optional contains the compilation
     * unit corresponding to the class to which the operation object of a JDoctor condition refers, if it is found.
     * Otherwise, the method returns an empty optional.
     */
    public Optional<CompilationUnit> getClassCompilationUnit(Operation operation) {
        List<String> wholePathList = Arrays.asList(operation.classname().split("\\."));
        String srcPath = this.project.srcPath();
        String classPath = Paths.get(srcPath, wholePathList.toArray(String[]::new)) + FileFormat.JAVA.getValue();
        Optional<CompilationUnit> cu = jpUtils.getCompilationUnitFromFilePath(classPath, true);
        return cu;
    }
}
