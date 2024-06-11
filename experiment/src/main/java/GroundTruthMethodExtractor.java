import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class GroundTruthMethodExtractor {

    public static void generateProjectMethodsJSON(String projectName, Path classFilePath, Path outputFilePath) {
        try {
            // Create an instance of JavaParser
            JavaParser parser = new JavaParser();
            ParseResult<CompilationUnit> parseResult = parser.parse(classFilePath);
            CompilationUnit cu = parseResult.getResult().orElse(null);

            if (cu == null) {
                System.out.println("Failed to parse the file.");
                return;
            }

            // Create ObjectMapper for JSON serialization
            ObjectMapper mapper = new ObjectMapper();
            ArrayNode methodsArray = mapper.createArrayNode();

            // Process the top-level classes and interfaces
            cu.findAll(ClassOrInterfaceDeclaration.class).forEach(classDecl -> {
                String className = classDecl.getFullyQualifiedName().get().toString();
                extractMembers(projectName, className, classDecl, methodsArray, mapper);
            });

            // Convert the JSON array to a string
            String jsonOutput = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(methodsArray);
            // Write JSON to a file
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFilePath.toString()), methodsArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void extractMembers(String projectName, String className,
                                       ClassOrInterfaceDeclaration classDecl,
                                       ArrayNode methodsArray, ObjectMapper mapper) {
        // Extract methods
        for (MethodDeclaration method : classDecl.getMethods()) {
            ObjectNode methodJson = mapper.createObjectNode();
            methodJson.put("projectName", projectName);
            methodJson.put("className", className);
            methodJson.put("methodSourceCode", method.toString());
            methodsArray.add(methodJson);
        }

        // Extract constructors
        for (ConstructorDeclaration constructor : classDecl.getConstructors()) {
            ObjectNode constructorJson = mapper.createObjectNode();
            constructorJson.put("projectName", projectName);
            constructorJson.put("className", className);
            constructorJson.put("methodSourceCode", constructor.toString());
            methodsArray.add(constructorJson);
        }

        // Recursively process inner classes
        for (ClassOrInterfaceDeclaration innerClass : classDecl.getChildNodesByType(ClassOrInterfaceDeclaration.class)) {
            String innerClassName = className + "." + innerClass.getNameAsString();
            extractMembers(projectName, innerClassName, innerClass, methodsArray, mapper);
        }
    }
}