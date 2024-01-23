package star.tratto.input;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.data.TrattoPath;
import star.tratto.data.OracleDatapoint;
import star.tratto.data.OracleType;
import star.tratto.data.records.AttributeTokens;
import star.tratto.data.records.ClassTokens;
import star.tratto.data.records.MethodTokens;
import star.tratto.data.records.ValueTokens;
import star.tratto.util.FileUtils;
import star.tratto.util.javaparser.JavaParserUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static star.tratto.util.FileUtils.readString;
import static star.tratto.util.StringUtils.getClassNameFromPath;
import static star.tratto.util.javaparser.DatasetUtils.getJavaFiles;
import static star.tratto.util.javaparser.JavaParserUtils.getClassOrInterface;

public class ClassAnalyzerTest {

    private static final Logger logger = LoggerFactory.getLogger(ClassAnalyzerTest.class);

    private static final ClassAnalyzer classAnalyzer = ClassAnalyzer.getInstance();
    private static final JavaParser javaParser = JavaParserUtils.getJavaParser();
    private static final String ROOT = "src/test/resources/";
    private static final String JAR = ROOT + "projects-packaged/commons-collections4-4.1-jar-with-dependencies.jar";
    private static final String SOURCE = ROOT + "projects-source/commons-collections4-4.1/src/main/java/";
    private static final String CLASS = SOURCE + "org/apache/commons/collections4/BagUtils.java";
    private static final String CLASS_NAME = getClassNameFromPath(CLASS);
    private static final String CLASS_SOURCE = readString(Paths.get(CLASS));
    static { JavaParserUtils.updateSymbolSolver(JAR); }
    private static final CompilationUnit CLASS_CU = javaParser.parse(CLASS_SOURCE).getResult().get();
    private static final TypeDeclaration<?> CLASS_TD = getClassOrInterface(CLASS_CU, CLASS_NAME);
    private static final int MAX_METHODS = 500; // to avoid test hanging due to huge classes

    @BeforeEach
    public void reset() {
        classAnalyzer.reset();
    }

    @AfterAll
    public static void resetJavaParserSymbolSolver() {
        JavaParserUtils.resetSymbolSolver();
    }

    @Test
    public void setProjectPathTest() {
        OracleDatapoint oracleDatapoint;
        oracleDatapoint = classAnalyzer.getOracleDPBuilder().copy();
        assertEquals("", oracleDatapoint.getOracle());
        assertEquals("", oracleDatapoint.getProjectName());
        assertEquals(0, oracleDatapoint.getId());
        assertNull(oracleDatapoint.getTokensProjectClasses());
        assertNull(oracleDatapoint.getTokensProjectClassesNonPrivateStaticNonVoidMethods());
        assertNull(oracleDatapoint.getTokensProjectClassesNonPrivateStaticAttributes());

        classAnalyzer.setProjectPath(SOURCE);
        oracleDatapoint = classAnalyzer.getOracleDPBuilder().copy();
        assertEquals(2, oracleDatapoint.getTokensProjectClasses().size());
        assertTrue(oracleDatapoint.getTokensProjectClasses().contains(new ClassTokens("Bag", "org.apache.commons.collections4")));
        assertTrue(oracleDatapoint.getTokensProjectClasses().contains(new ClassTokens("BagUtils", "org.apache.commons.collections4")));
        assertEquals(13, oracleDatapoint.getTokensProjectClassesNonPrivateStaticNonVoidMethods().size());
        assertEquals(3, oracleDatapoint.getTokensProjectClassesNonPrivateStaticAttributes().size());
    }

    @Test
    public void setClassFeaturesTest() {
        OracleDatapoint oracleDatapoint;
        classAnalyzer.setProjectPath(SOURCE);
        oracleDatapoint = classAnalyzer.getOracleDPBuilder().copy();
        assertNull(oracleDatapoint.getClassName());
        assertNull(oracleDatapoint.getClassSourceCode());
        assertNull(oracleDatapoint.getClassJavadoc());
        assertNull(oracleDatapoint.getPackageName());

        classAnalyzer.setClassFeatures(CLASS_NAME, CLASS_SOURCE, CLASS_CU, CLASS_TD);
        oracleDatapoint = classAnalyzer.getOracleDPBuilder().copy();
        assertEquals("BagUtils", oracleDatapoint.getClassName());
        assertTrue(oracleDatapoint.getClassSourceCode().contains("public class BagUtils {"));
        assertTrue(oracleDatapoint.getClassJavadoc().startsWith("/**\n"));
        assertEquals("org.apache.commons.collections4", oracleDatapoint.getPackageName());
    }

    @Test
    public void setMethodFeaturesTest() {
        OracleDatapoint oracleDatapoint;
        CallableDeclaration<?> method = CLASS_TD.getMethodsByName("methodWithoutJavadoc").get(0);
        classAnalyzer.setProjectPath(SOURCE);
        classAnalyzer.setClassFeatures(CLASS_NAME, CLASS_SOURCE, CLASS_CU, CLASS_TD);
        oracleDatapoint = classAnalyzer.getOracleDPBuilder().copy();
        assertNull(oracleDatapoint.getMethodSourceCode());

        classAnalyzer.setMethodFeatures(method);
        oracleDatapoint = classAnalyzer.getOracleDPBuilder().copy();
        List<String> attributeTokens = oracleDatapoint.getTokensMethodVariablesNonPrivateNonStaticAttributes().stream().map(AttributeTokens::attributeName).toList();
        List<String> methodTokens = oracleDatapoint.getTokensMethodVariablesNonPrivateNonStaticNonVoidMethods().stream().map(MethodTokens::methodName).toList();
        assertTrue(oracleDatapoint.getMethodSourceCode().startsWith("public static Bag methodWithoutJavadoc(Integer param1, double... param2)"));
        assertTrue(attributeTokens.contains("length")); // from double... param2
        assertTrue(methodTokens.contains("uniqueSet")); // from Bag
        assertTrue(methodTokens.contains("byteValue")); // from Integer
    }

    @Test
    public void getOracleDatapointsFromMethod_NoJavadocTest() {
        classAnalyzer.setProjectPath(SOURCE);
        classAnalyzer.setClassFeatures(CLASS_NAME, CLASS_SOURCE, CLASS_CU, CLASS_TD);
        MethodDeclaration method = CLASS_TD.getMethodsByName("methodWithoutJavadoc").get(0);
        classAnalyzer.setMethodFeatures(method);
        Optional<Javadoc> optionalJavadoc = method.getJavadoc();

        // Precondition
        assertTrue(optionalJavadoc.isEmpty());

        // Test
        List<OracleDatapoint> oracleDatapoints = classAnalyzer.getOracleDatapointsFromMethod(method);
        List<String> javadocTags = oracleDatapoints.stream().map(OracleDatapoint::getJavadocTag).toList();
        List<OracleType> oracleTypes = oracleDatapoints.stream().map(OracleDatapoint::getOracleType).toList();
        assertEquals(3, oracleDatapoints.size());
        javadocTags.forEach(jt -> assertEquals("", jt));
        assertTrue(oracleTypes.contains(OracleType.PRE));
        assertTrue(oracleTypes.contains(OracleType.NORMAL_POST));
        assertTrue(oracleTypes.contains(OracleType.EXCEPT_POST));
    }

    @Test
    public void getOracleDatapointsFromJavadoc_NoTagsTest() {
        classAnalyzer.setProjectPath(SOURCE);
        classAnalyzer.setClassFeatures(CLASS_NAME, CLASS_SOURCE, CLASS_CU, CLASS_TD);
        ConstructorDeclaration method = CLASS_TD.getConstructors().get(0);
        classAnalyzer.setMethodFeatures(method);
        Javadoc javadoc = method.getJavadoc().get();

        // Precondition
        assertEquals("Instantiation of BagUtils is not intended or required.", javadoc.getDescription().toText());

        // Test
        List<OracleDatapoint> oracleDatapoints = classAnalyzer.getOracleDatapointsFromJavadoc(javadoc);
        List<String> javadocTags = oracleDatapoints.stream().map(OracleDatapoint::getJavadocTag).toList();
        List<OracleType> oracleTypes = oracleDatapoints.stream().map(OracleDatapoint::getOracleType).toList();
        assertEquals(3, oracleDatapoints.size());
        javadocTags.forEach(jt -> assertEquals("", jt));
        assertTrue(oracleTypes.contains(OracleType.PRE));
        assertTrue(oracleTypes.contains(OracleType.NORMAL_POST));
        assertTrue(oracleTypes.contains(OracleType.EXCEPT_POST));
    }

    @Test
    public void getOracleDatapointsFromJavadoc_NoThrowsTagTest() {
        classAnalyzer.setProjectPath(SOURCE);
        classAnalyzer.setClassFeatures(CLASS_NAME, CLASS_SOURCE, CLASS_CU, CLASS_TD);
        MethodDeclaration method = CLASS_TD.getMethodsByName("emptySortedBag").get(0);
        classAnalyzer.setMethodFeatures(method);
        Javadoc javadoc = method.getJavadoc().get();

        // Precondition
        assertEquals("Get an empty <code>SortedBag</code>.", javadoc.getDescription().toText());

        // Test
        List<OracleDatapoint> oracleDatapoints = classAnalyzer.getOracleDatapointsFromJavadoc(javadoc);
        List<String> javadocTags = oracleDatapoints.stream().map(OracleDatapoint::getJavadocTag).toList();
        List<OracleType> oracleTypes = oracleDatapoints.stream().map(OracleDatapoint::getOracleType).toList();
        assertEquals(3, oracleDatapoints.size());
        assertTrue(javadocTags.contains("@param <E> the element type"));
        assertTrue(javadocTags.contains("@return an empty sorted Bag"));
        assertTrue(javadocTags.contains(""));
        assertTrue(oracleTypes.contains(OracleType.PRE));
        assertTrue(oracleTypes.contains(OracleType.NORMAL_POST));
        assertTrue(oracleTypes.contains(OracleType.EXCEPT_POST));
    }

    @Test
    public void getOracleDatapointsFromMethod_MultipleParamTagsTest() {
        classAnalyzer.setProjectPath(SOURCE);
        classAnalyzer.setClassFeatures(CLASS_NAME, CLASS_SOURCE, CLASS_CU, CLASS_TD);
        MethodDeclaration method = CLASS_TD.getMethodsByName("predicatedSortedBag").get(0);
        classAnalyzer.setMethodFeatures(method);

        // Precondition
        assertTrue(method.getJavadoc().get().getDescription().toText().startsWith("Returns a predicated (validating) sorted bag backed by the given sorted\nbag."));

        // Test
        List<OracleDatapoint> oracleDatapoints = classAnalyzer.getOracleDatapointsFromMethod(method);
        List<Pair<String, OracleType>> javadocTagsAndOracleTypes = oracleDatapoints.stream().map(odp -> Pair.with(odp.getJavadocTag(), odp.getOracleType())).toList();
        assertEquals(5, oracleDatapoints.size());
        assertTrue(javadocTagsAndOracleTypes.contains(Pair.with("@param <E> the element type", OracleType.PRE)));
        assertTrue(javadocTagsAndOracleTypes.contains(Pair.with("@param bag the sorted bag to predicate, must not be null", OracleType.PRE)));
        assertTrue(javadocTagsAndOracleTypes.contains(Pair.with("@param predicate the predicate for the bag, must not be null", OracleType.PRE)));
        assertTrue(javadocTagsAndOracleTypes.contains(Pair.with("@return a predicated bag backed by the given bag", OracleType.NORMAL_POST)));
        assertTrue(javadocTagsAndOracleTypes.contains(Pair.with("@throws NullPointerException if the SortedBag or Predicate is null", OracleType.EXCEPT_POST)));
    }

    @Test
    public void getOracleDatapointsFromClassTest() {
        classAnalyzer.setProjectPath(SOURCE);
        classAnalyzer.setClassFeatures(CLASS_NAME, CLASS_SOURCE, CLASS_CU, CLASS_TD);
        List<OracleDatapoint> oracleDatapoints = classAnalyzer.getOracleDatapointsFromClass();

        assertEquals(52, oracleDatapoints.size());

        assertEquals(26, oracleDatapoints.stream().filter(odp -> odp.getOracleType().equals(OracleType.PRE)).toList().size());
        assertEquals(13, oracleDatapoints.stream().filter(odp -> odp.getOracleType().equals(OracleType.NORMAL_POST)).toList().size());
        assertEquals(13, oracleDatapoints.stream().filter(odp -> odp.getOracleType().equals(OracleType.EXCEPT_POST)).toList().size());

        assertEquals(8, oracleDatapoints.stream().filter(odp -> odp.getJavadocTag().equals("")).toList().size());

        String allMethodsSourceCodes = oracleDatapoints.stream().map(OracleDatapoint::getMethodSourceCode).toList().toString();
        assertTrue(allMethodsSourceCodes.contains("private BagUtils()"));
        assertTrue(allMethodsSourceCodes.contains("public static <E> Bag<E> synchronizedBag(final Bag<E> bag)"));
        assertTrue(allMethodsSourceCodes.contains("public static <E> Bag<E> unmodifiableBag(final Bag<? extends E> bag)"));
        assertTrue(allMethodsSourceCodes.contains("public static <E> Bag<E> predicatedBag(final Bag<E> bag, final Predicate<? super E> predicate)"));
        assertTrue(allMethodsSourceCodes.contains("public static <E> Bag<E> transformingBag(final Bag<E> bag, final Transformer<? super E, ? extends E> transformer)"));
        assertTrue(allMethodsSourceCodes.contains("public static <E> Bag<E> collectionBag(final Bag<E> bag)"));
        assertTrue(allMethodsSourceCodes.contains("public static <E> SortedBag<E> synchronizedSortedBag(final SortedBag<E> bag)"));
        assertTrue(allMethodsSourceCodes.contains("public static <E> SortedBag<E> unmodifiableSortedBag(final SortedBag<E> bag)"));
        assertTrue(allMethodsSourceCodes.contains("public static <E> SortedBag<E> predicatedSortedBag(final SortedBag<E> bag, final Predicate<? super E> predicate)"));
        assertTrue(allMethodsSourceCodes.contains("public static <E> SortedBag<E> transformingSortedBag(final SortedBag<E> bag, final Transformer<? super E, ? extends E> transformer)"));
        assertTrue(allMethodsSourceCodes.contains("public static <E> Bag<E> emptyBag()"));
        assertTrue(allMethodsSourceCodes.contains("public static <E> SortedBag<E> emptySortedBag()"));
        assertTrue(allMethodsSourceCodes.contains("public static Bag methodWithoutJavadoc(Integer param1, double... param2)"));
    }

    /**
     * E2E test, to be called from {@link star.tratto.E2ETests}
     */
    public static void classAnalyzerE2ETest() {
        classAnalyzer.reset();
        List<OracleDatapoint> oracleDatapoints = new ArrayList<>();

        // General variables for later assertions
        List<String> tokensGeneralGrammar = FileUtils.readJSONList(TrattoPath.TOKENS_GRAMMAR.getPath())
                .stream()
                .map(e -> (String) e)
                .toList();
        List<ValueTokens> tokensGeneralValues = FileUtils.readJSONList(TrattoPath.TOKENS_GENERAL_VALUES.getPath())
                .stream()
                .map(e -> ((List<?>) e)
                        .stream()
                        .map(o -> (String) o)
                        .collect(Collectors.toList()))
                .toList()
                .stream()
                .map(tokenList -> new ValueTokens(tokenList.get(0), tokenList.get(1)))
                .collect(Collectors.toList());

        // Projects for which to generate oracle datapoints
        String rsrcsPath = TrattoPath.RESOURCES.getPath().toString() + "/";
        List<Triplet<String, String, String>> projectPathsAndJars = FileUtils.readJSONList(TrattoPath.INPUT_PROJECTS.getPath(), Map.class)
                .stream()
                .map(projectObject -> {
                    String p = (String) projectObject.get("projectName");
                    return Triplet.with(
                            p,
                            rsrcsPath + p + "/" + String.join("/", (List)projectObject.get("srcPathList")),
                            rsrcsPath + p + "/" + String.join("/", (List)projectObject.get("jarPathList")));
                })
                .toList();

        for (Triplet<String, String, String> projectPathAndJar : projectPathsAndJars) {
            logger.info("------------------------------------------------------------");
            logger.info("Processing project: {}", projectPathAndJar.getValue0());
            logger.info("------------------------------------------------------------");

            JavaParserUtils.updateSymbolSolver(projectPathAndJar.getValue2());
            List<Path> javaFiles = getJavaFiles(Path.of(projectPathAndJar.getValue1())).stream().filter(jf -> {
                    String jfPath = jf.toString();
                    return
                            !jfPath.contains("/test/") &&
                            !jfPath.contains("/test-super/") &&
                            !jfPath.contains("/guava-test-gwt-sources/") &&
                            !jfPath.contains("/guava-test-sources/") &&
                            !jfPath.contains("/src-test/") &&
                            !jfPath.contains("/target/");

            }).toList();
            for (Path javaFile : javaFiles) {
                logger.info("Processing file: {}", javaFile);

                String classPath = javaFile.toString();
                String className = getClassNameFromPath(classPath);
                String classSource = readString(javaFile);

                CompilationUnit classCu;
                try {
                    classCu = javaParser.parse(classSource).getResult().get();
                } catch (NoSuchElementException e) {
                    logger.warn("The source code of the class {} could not be parsed", className);
                    continue;
                }
                TypeDeclaration<?> classTd = getClassOrInterface(classCu, className);

                classAnalyzer.setProjectPath(projectPathAndJar.getValue1());
                classAnalyzer.setClassFeatures(className, classSource, classCu, classTd);
                try {
                    if (classTd.getMethods().size() + classTd.getConstructors().size() <= MAX_METHODS) {
                        oracleDatapoints.addAll(classAnalyzer.getOracleDatapointsFromClass());
                    } else {
                        logger.warn("The class {} has more than {} methods and constructors. Skipping...", className, MAX_METHODS);
                    }
                } catch (UnsolvedSymbolException e) {
                    logger.warn("The class {} could not be analyzed because it contains some symbol that cannot be solved. Stack trace:", className);
                    e.printStackTrace();
                }
            }
        }

        oracleDatapoints.forEach(odp -> {
            assertEquals(0, odp.getId());
            assertEquals("", odp.getOracle());
            assertTrue(List.of(OracleType.PRE, OracleType.NORMAL_POST, OracleType.EXCEPT_POST).contains(odp.getOracleType()));
            assertEquals("", odp.getProjectName());
            assertNotNull(odp.getPackageName());
            assertNotNull(odp.getClassName());
            assertNotNull(odp.getJavadocTag());
            assertNotNull(odp.getMethodSourceCode());
            assertNotNull(odp.getClassSourceCode());
            assertEquals(tokensGeneralGrammar, odp.getTokensGeneralGrammar());
            assertEquals(tokensGeneralValues, odp.getTokensGeneralValuesGlobalDictionary());
            assertTrue(odp.getTokensProjectClasses().size() > 0);
            assertTrue(odp.getTokensProjectClassesNonPrivateStaticNonVoidMethods().size() > 0);
            assertTrue(odp.getTokensProjectClassesNonPrivateStaticAttributes().size() > 0);
            assertNotNull(odp.getTokensMethodVariablesNonPrivateNonStaticNonVoidMethods());
            assertNotNull(odp.getTokensMethodVariablesNonPrivateNonStaticAttributes());
            assertNotNull(odp.getTokensOracleVariablesNonPrivateNonStaticNonVoidMethods());
            assertNotNull(odp.getTokensOracleVariablesNonPrivateNonStaticAttributes());
        });
    }
}
