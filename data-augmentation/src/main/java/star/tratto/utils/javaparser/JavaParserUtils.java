package star.tratto.utils.javaparser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.resolution.MethodUsage;
import com.github.javaparser.resolution.TypeSolver;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ClassLoaderTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import com.github.javaparser.symbolsolver.utils.SymbolSolverCollectionStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class JavaParserUtils {
    private static final Logger logger = LoggerFactory.getLogger(JavaParserUtils.class);
    private static final String ROOT = "src/main/resources/projects-packaged";
    private static JavaParser javaParser = getJavaParser();
    // artificial source code used to parse arbitrary source code expressions using JavaParser
    /** Artificial class name */
    private static final String SYNTHETIC_CLASS_NAME = "DataAugmentation__AuxiliaryClass";
    /** Artificial class source code */
    private static final String SYNTHETIC_CLASS_SOURCE = "public class " + SYNTHETIC_CLASS_NAME + " {}";
    /** Artificial method name */
    private static final String SYNTHETIC_METHOD_NAME = "__dataAugmentation__auxiliaryMethod";
    /** Cache ResolvedType of Object to make subsequent accesses free. */
    private static ResolvedType objectType;
    /** Cache Set&lt;MethodUsage&gt; of Object methods to make subsequent accesses free. */
    private static Set<MethodUsage> objectMethods;

    /** Private constructor to avoid creating an instance of this class. */
    private JavaParserUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    public static JavaParser getJavaParser() {
        if (javaParser == null) {
            ParserConfiguration parserConfiguration = new ParserConfiguration();
            parserConfiguration.setLanguageLevel(ParserConfiguration.LanguageLevel.JAVA_17);
            CombinedTypeSolver typeSolver = createCombinedTypeSolver(ROOT);
            parserConfiguration.setSymbolResolver(new JavaSymbolSolver(typeSolver));
            javaParser = new JavaParser(parserConfiguration);
        }
        return javaParser;
    }

    /**
     * Sets up the SymbolSolver for JavaParser. Given a path which may contain both source code and JAR files,
     * we need the SymbolSolver to consider all those. This can be done using the
     * {@link SymbolSolverCollectionStrategy}, which creates a {@link CombinedTypeSolver} composed of
     * multiple {@link TypeSolver}s plus one {@link ReflectionTypeSolver}. The latter is needed to
     * resolve the types of the JDK classes.
     */
    private static CombinedTypeSolver createCombinedTypeSolver(String root) {
        CombinedTypeSolver combinedTypeSolver = new CombinedTypeSolver();
        combinedTypeSolver.add(new ReflectionTypeSolver(true));
        combinedTypeSolver.add(new ClassLoaderTypeSolver(ClassLoader.getPlatformClassLoader()));
        SymbolSolverCollectionStrategy strategy = new SymbolSolverCollectionStrategy();
        strategy.collect(Paths.get(root));
        try {
            Field typeSolverField = strategy.getClass().getDeclaredField("typeSolver");
            typeSolverField.setAccessible(true);
            CombinedTypeSolver strategyTypeSolver = (CombinedTypeSolver) typeSolverField.get(strategy);
            Field elementsField = strategyTypeSolver.getClass().getDeclaredField("elements");
            elementsField.setAccessible(true);
            List<TypeSolver> strategyTypeSolvers = (List<TypeSolver>) elementsField.get(strategyTypeSolver);
            strategyTypeSolvers.stream().filter(ts -> !(ts instanceof ReflectionTypeSolver)).forEach(ts -> {
                try {
                    Field parentField = ts.getClass().getDeclaredField("parent");
                    parentField.setAccessible(true);
                    parentField.set(ts, null);
                    parentField.setAccessible(false);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                combinedTypeSolver.add(ts);
            });
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return combinedTypeSolver;
    }

    /**
     * Returns the signature of a JavaParser callable declaration. Uses the
     * method source code and removes method body, contained comments, the
     * Javadoc comment, and other special characters (e.g. "\n").
     *
     * @param jpCallable a JavaParser callable declaration
     * @return a string representation of the signature. A signature follows
     * the format:
     *     "[modifiers] [typeParameters] [type] [methodName]([parameters]) throws [exceptions]"
     */
    public static String getCallableSignature(
            CallableDeclaration<?> jpCallable
    ) {
        StringBuilder sb = new StringBuilder();
        // add modifiers
        List<String> modifiers = jpCallable.getModifiers()
                .stream()
                .map(Modifier::toString)
                .toList();
        sb.append(String.join("", modifiers));
        // add type parameters
        List<String> typeParameters = jpCallable.getTypeParameters()
                .stream()
                .map(TypeParameter::toString)
                .toList();
        if (!typeParameters.isEmpty()) {
            sb.append("<")
                    .append(String.join(", ", typeParameters))
                    .append(">")
                    .append(" ");
        }
        // add return type (if not a constructor)
        if (jpCallable.isMethodDeclaration()) {
            sb.append(jpCallable.asMethodDeclaration().getType())
                    .append(" ");
        }
        // add method name
        sb.append(jpCallable.getNameAsString());
        // add formal parameters
        List<String> parameters = jpCallable.getParameters()
                .stream()
                .map(Parameter::toString)
                .toList();
        sb.append("(")
                .append(String.join(", ", parameters))
                .append(")");
        // add exceptions
        List<String> exceptions = jpCallable.getThrownExceptions()
                .stream()
                .map(ReferenceType::asString)
                .toList();
        if (!exceptions.isEmpty()) {
            sb.append(" throws ")
                    .append(String.join(", ", exceptions));
        }
        return sb.toString().replaceAll(" +", " ").trim();
    }

    /**
     * Gets a compilation unit from a Java file path.
     *
     * @param path a Java file
     * @return the corresponding JavaParser compilation unit. Returns
     * {@code Optional.empty()} if an error occurs while attempting to parse
     * the file.
     */
    public static Optional<CompilationUnit> getCompilationUnit(Path path) {
        try {
            return javaParser.parse(path).getResult();
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
