package star.tratto;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.data.DataAgumentationPath;
import star.tratto.data.ProjectInitializer;
import star.tratto.records.JDoctorConditionDocument;
import star.tratto.records.Project;
import star.tratto.records.Repository;
import star.tratto.records.RepositoryClass;
import star.tratto.utils.FileUtils;
import star.tratto.utils.javaparser.DatasetUtils;
import star.tratto.utils.javaparser.JavaParserUtils;
import star.tratto.utils.javaparser.TypeUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * The DataAugmentation class
 */
public class DataAugmentation {
    private static final Logger logger = LoggerFactory.getLogger(DataAugmentation.class);

    /**
     * Gets a list of relative path, from the root directory of the project under analysis.
     * @param path The relative path from the root of the DataAugmentation project.
     * @return a relative path list from the root of the project under analysis
     */
    private static List<String> getProjectPathList(Path path, Path basePath, String projectName) {
        String pathStr = path.toString();
        String basePathToProjectStr = basePath.resolve(projectName).toString();
        String pathfromProjectRootStr = pathStr.replace(basePathToProjectStr, "");
        pathfromProjectRootStr = pathfromProjectRootStr.startsWith("/") ? pathfromProjectRootStr.substring(1) : pathfromProjectRootStr;
        if (pathfromProjectRootStr.length() == 0) {
            return new ArrayList<>();
        }
        return Arrays.stream(pathfromProjectRootStr.split("/")).toList();
    }

    public static void main( String[] args ) {
        boolean emptyJavadoc = false;
        if (args.length > 0) {
            emptyJavadoc = Boolean.parseBoolean(args[0]);
        }

        List<Project> projects = ProjectInitializer.initialize(DataAgumentationPath.INPUT_PROJECTS.getPath());

        for (Project project: projects) {
            System.out.println(String.format("Processing project %s", project.projectName()));
            List<Path> classPathList = DatasetUtils.getSelectionOfValidJavaFiles(project.srcPath(), project.fullyQualifiedClassNameList());
            Path projectRootPath = DataAgumentationPath.DATA_ENTRIES.getPath().resolve(project.projectName());
            for (Path classPath: classPathList) {
                List<JDoctorConditionDocument> jDoctorConditions = new ArrayList<>();
                Optional<CompilationUnit> cuOptional = JavaParserUtils.getCompilationUnit(classPath);
                if (cuOptional.isEmpty()) {
                    continue;
                }
                CompilationUnit cu = cuOptional.get();
                TypeDeclaration jpClass = cu.getPrimaryType().get();
                String fullyQualifiedClassName = (String) jpClass.getFullyQualifiedName().get();
                String classSourceCode = cu.toString();
                List<CallableDeclaration> jpCallableList = cu.findAll(CallableDeclaration.class);
                for (CallableDeclaration<?> jpCallable: jpCallableList) {
                    String methodJavadoc = DatasetUtils.getCallableJavadoc(jpCallable);

                    if (methodJavadoc.length() > 0 || !emptyJavadoc) {

                        String focalMethodSourceCode = DatasetUtils.getCallableSourceCode(jpCallable);
                        List<String> parameterTypeList = jpCallable.getParameters()
                                .stream()
                                .map(p -> TypeUtils.getJDoctorSimpleNameFromSourceCode(jpClass, jpCallable, p))
                                .toList();
                        List<String> parameterNameList = jpCallable.getParameters()
                                .stream()
                                .map(p -> p.getNameAsString())
                                .toList();
                        JDoctorConditionDocument.Source source = new JDoctorConditionDocument.Source(
                                methodJavadoc,
                                focalMethodSourceCode,
                                classSourceCode
                        );
                        JDoctorConditionDocument.Operation operation = new JDoctorConditionDocument.Operation(
                                fullyQualifiedClassName,
                                jpCallable instanceof ConstructorDeclaration ? fullyQualifiedClassName : jpCallable.getNameAsString(),
                                parameterTypeList
                        );
                        JDoctorConditionDocument.Identifiers identifiers = new JDoctorConditionDocument.Identifiers(
                                parameterNameList,
                                "receiverObjectID",
                                "methodResultID"
                        );
                        jDoctorConditions.add(new JDoctorConditionDocument(
                                source,
                                operation,
                                identifiers,
                                new ArrayList<>(),
                                new ArrayList<>(),
                                new ArrayList<>()
                        ));
                    }
                }
                RepositoryClass repositoryClass = new RepositoryClass(fullyQualifiedClassName, jDoctorConditions);
                Path classFilePath = projectRootPath.resolve(Paths.get(
                    "classes",
                    fullyQualifiedClassName.replace(".", "-").concat(".json")
                ));
                FileUtils.writeJSON(classFilePath, repositoryClass);
            }
            Repository repository = new Repository(
                project.projectName(),
                project.githubLink(),
                project.commit(),
                getProjectPathList(project.srcPath(), DataAgumentationPath.PROJECTS_SOURCE.getPath(), project.projectName()),
                getProjectPathList(project.rootPath(), DataAgumentationPath.PROJECTS_SOURCE.getPath(), "")
            );
            Path repositoryFilePath = projectRootPath.resolve("repository.json");
            FileUtils.writeJSON(repositoryFilePath, repository);
        }
    }
}
