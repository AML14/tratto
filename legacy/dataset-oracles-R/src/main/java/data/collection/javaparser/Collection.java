package data.collection.javaparser;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.LiteralExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.javadoc.JavadocBlockTag;
import com.github.javaparser.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Collection {

    public static void main(String[] args) throws IOException {

        InitializeProjects initializeProjects = new InitializeProjects();
        initializeProjects.InitializeProjects();

        for (InputProject inputProject : initializeProjects.inputProjects) {
            collectDataFromProject(inputProject);
        }

    }

    /**
     * This method takes an input project and collects its elements (classses and members of it) using java parser and java symbol solver
     * It produces a json file for each project, which includes the collected data
     * @param inputProject object keeps the source path of the project and information for optional parts of data collection process
     */
    public static void collectDataFromProject(InputProject inputProject) throws IOException {
        System.out.println("Collecting data from: " + inputProject.getProjectName());

        File sourceFilesDir = inputProject.getSourceFilesDir();
        File outputDataDir = inputProject.getOutputDataDir();
        String projectName = inputProject.getProjectName();

        List<File> allSourceFiles = listSourceFiles(sourceFilesDir);

        //initialize java symbol solver
        //we use static solver which does the analysis by considering the project's source directory
        TypeSolver typeSolver = new ReflectionTypeSolver();
        TypeSolver javaParserTypeSolver = new JavaParserTypeSolver(sourceFilesDir);
        CombinedTypeSolver combinedSolver = new CombinedTypeSolver();
        combinedSolver.add(typeSolver);
        combinedSolver.add(javaParserTypeSolver);

        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
        StaticJavaParser.getParserConfiguration().setSymbolResolver(symbolSolver);

        //create json array to keep collected data for each class in the project
        JSONArray classArray = new JSONArray();

        int sourceFileId = 0;
        for (File classFile : allSourceFiles) {
            sourceFileId++;
            String className = fullPath2ClassName(classFile.getAbsolutePath());

            //create a json object to keep the data collected for a class
            JSONObject classObject = new JSONObject();
            classObject.put("sourceFileName", className);
            classObject.put("sourceFileId", sourceFileId);

            //try parsing a class (compilation unit) using static java parser
            CompilationUnit cu = null;
            try {
                cu = StaticJavaParser.parse(classFile);
            } catch (Exception e) {
                System.out.println("Source file parse error in source " + sourceFileId + " - " + e.getMessage());
            }

            if(cu != null) {
                classObject.put("packageName", cu.getPackageDeclaration().isPresent() ? cu.getPackageDeclaration().get().getName().toString() : "");
                classObject.put("imports", collectImportsOfSourceFile(cu));

                //get type declarations in a class (class declarations)
                NodeList<TypeDeclaration<?>> typeDeclarations = cu.getTypes();
                if (!typeDeclarations.isEmpty()) {
                    TypeDeclaration typeDec = typeDeclarations.get(0);
                    //typeDec.resolve().getAllAncestors()
                    //typeDec.resolve().getAllFields();
                    //typeDec.resolve().getAllMethods();
//                    try {
//                        System.out.println(typeDec.resolve().getQualifiedName());
//                        System.out.println(typeDec.resolve().isReferenceType());
//                        System.out.println(typeDec.resolve().getAllFields());
//                        System.out.println(typeDec.resolve().getAllMethods());
//                    } catch(Exception e){
//                        System.out.println("cannot solve type dec");
//                    }

//                    StaticJavaParser.getParserConfiguration().getSymbolResolver().get().toResolvedType

                    JSONArray typeDecArray = new JSONArray();
                    JSONObject typeDecObject = new JSONObject();
                    typeDecObject.put("className", typeDec.getName().toString());
                    typeDecObject.put("classJavadoc", typeDec.getJavadocComment().isPresent() ? typeDec.getJavadocComment().get().toString() : "");

                    JSONArray constructorDeclarationsInClass = collectConstructorDeclarationsInClass(inputProject, classFile, cu);
                    typeDecObject.put("constructors", constructorDeclarationsInClass);
                    JSONArray methodDeclarationsInClass = collectMethodDeclarationsInClass(inputProject, classFile, cu);
                    typeDecObject.put("methods", methodDeclarationsInClass);
                    JSONArray fieldDeclarationsInClass = collectFieldDeclarationsInClass(inputProject, classFile, cu);
                    typeDecObject.put("fields", fieldDeclarationsInClass);

                    typeDecArray.add(typeDecObject);


                    classObject.put("typeDeclarations", typeDecArray);
                }
            }


            classArray.add(classObject);
        }

        //write the collected class data into the json file
        //all class related data is kept in classArray
        try (FileWriter file = new FileWriter(outputDataDir.toString() + "/" + projectName + "_dataCollectedWithJavaParser.json")) {
            file.write(classArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONArray collectMethodDeclarationsInClass(InputProject inputProject, File classFile, CompilationUnit cu){
        NodeList<TypeDeclaration<?>> typeDeclarations = cu.getTypes();
        TypeDeclaration typeDec = typeDeclarations.get(0);

        JSONArray methodDeclarationsJsonArray = new JSONArray();
        List<MethodDeclaration> methodDeclarations = typeDec.findAll(MethodDeclaration.class);
        //System.out.println(methodDeclarations.size());

        //for(MethodDeclaration methodDeclaration : methodDeclarations){
        for (int i = 0; i < methodDeclarations.size(); i++) {
            MethodDeclaration methodDeclaration = methodDeclarations.get(i);
            //System.out.println("1: " + methodDeclaration.getName());
            //System.out.println("2: " + methodDeclaration.getJavadocComment());
            //System.out.println("3: " + methodDeclaration.getDeclarationAsString());

            JSONObject methodDeclarationJsonObject = new JSONObject();

            if(methodDeclaration.getParentNode().get().equals(typeDec)){
                methodDeclarationJsonObject.put("name", methodDeclaration.getName().toString());
//            methodDeclarationJsonObject.put("declaration", methodDeclaration.getDeclarationAsString());
                methodDeclarationJsonObject.put("type", methodDeclaration.getType().toString());
                methodDeclarationJsonObject.put("accessSpecifier", methodDeclaration.getAccessSpecifier().toString());
                methodDeclarationJsonObject.put("body", (methodDeclaration.getBody().isPresent()) ? methodDeclaration.getBody().get().toString() : (""));
                methodDeclarationJsonObject.put("range", (methodDeclaration.getRange().isPresent()) ? methodDeclaration.getRange().get().toString() : (""));
                methodDeclarationJsonObject.put("tokenRange", methodDeclaration.getTokenRange().get().toString());
                if (!methodDeclaration.getJavadoc().isEmpty()) {
                    JSONArray javadocBlockTagsArr = new JSONArray();

                    Javadoc javadoc = methodDeclaration.getJavadoc().get();
                    List<JavadocBlockTag> javadocBlockTags = javadoc.getBlockTags();

                    for(JavadocBlockTag javadocBlockTag : javadocBlockTags){
                        JSONObject javadocBlockTagObj = new JSONObject();
                        javadocBlockTagObj.put("javadocBlockTag", javadocBlockTag.toString());
                        javadocBlockTagObj.put("javadocBlockType", javadocBlockTag.getType().toString());
                        javadocBlockTagObj.put("javadocBlockTagContent", javadocBlockTag.getContent().toText());
                        javadocBlockTagObj.put("javadocBlockTagName", !javadocBlockTag.getName().isEmpty() ? javadocBlockTag.getName().get() : "");
                        javadocBlockTagsArr.add(javadocBlockTagObj);
                    }
                    methodDeclarationJsonObject.put("javadoc", javadoc.toString());
                    methodDeclarationJsonObject.put("javadocBlockTags", javadocBlockTagsArr);
                } else {
                    methodDeclarationJsonObject.put("javadoc", "");
                }
                methodDeclarationJsonObject.put("comment",
                        methodDeclaration.getComment().isPresent() ? methodDeclaration.getComment().get().getContent().toString() : "");
                methodDeclarationJsonObject.put("javadocComment",
                        methodDeclaration.getJavadocComment().isPresent() ? methodDeclaration.getJavadocComment().get().toString() : "");

                String methodQualifiedSignature = "";
                String methodQualifiedName = "";
                String methodReturnTypeResolved = "";
                String methodReturnTypeDescribed = "";
                try {
                    methodQualifiedName = methodDeclaration.resolve().getQualifiedName();
                } catch (StackOverflowError | RuntimeException e) {
                    System.out.println("Cannot resolve the method qualified name - " + e.getMessage());
                }
                try {
                    methodQualifiedSignature = methodDeclaration.resolve().getQualifiedSignature();
                }
                catch (StackOverflowError | RuntimeException e) {
                    System.out.println("Cannot resolve the method qualified signature - " + e.getMessage());
                }
                try {
                    methodReturnTypeResolved = methodDeclaration.resolve().getReturnType().toString();
                    methodReturnTypeDescribed = methodDeclaration.resolve().getReturnType().describe().toString();
                } catch (StackOverflowError | RuntimeException e) {
                    System.out.println("Cannot resolve the method return type - " + e.getMessage());
                }
                methodDeclarationJsonObject.put("qualifiedName", methodQualifiedSignature);
                methodDeclarationJsonObject.put("qualifiedSignature", methodQualifiedName);
                methodDeclarationJsonObject.put("returnTypeResolved", methodReturnTypeResolved);
                methodDeclarationJsonObject.put("returnTypeDescribed", methodReturnTypeDescribed);

                methodDeclarationJsonObject.put("parameters", collectParametersOfMethod(methodDeclaration.getParameters()));

                if(inputProject.getCollectFieldAccesses()){
                    methodDeclarationJsonObject.put("fieldAccesses", collectFieldAccessesOfMember(methodDeclaration));
                }

                if(inputProject.getCollectMethodCalls()) {
                    methodDeclarationJsonObject.put("methodCalls", collectMethodCallsOfMember(methodDeclaration));
                }

                if(inputProject.getCollectLiteralExprs()) {
                    methodDeclarationJsonObject.put("literalExprs", collectLiteralExprsOfMember(methodDeclaration));
                }
                if(inputProject.getCollectVariableDeclarationExprs()) {
                    methodDeclarationJsonObject.put("variableDeclarationExprs", collectVariableDeclarationExprsOfMember(methodDeclaration));
                }

                try {
                    CompilationUnit cu2 =  StaticJavaParser.parse(classFile);
                    methodDeclarationJsonObject.put("declaration", cu2.getTypes().get(0).findAll(MethodDeclaration.class).get(i).getDeclarationAsString());
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                methodDeclarationsJsonArray.add(methodDeclarationJsonObject);
            }
        }

        return methodDeclarationsJsonArray;
    }
    public static JSONArray collectConstructorDeclarationsInClass(InputProject inputProject, File classFile, CompilationUnit cu) {
        NodeList<TypeDeclaration<?>> typeDeclarations = cu.getTypes();
        TypeDeclaration typeDec = typeDeclarations.get(0);

        JSONArray constructorDeclarationsJsonArray = new JSONArray();
        List<ConstructorDeclaration> constructorDeclarations = typeDec.findAll(ConstructorDeclaration.class);
        //System.out.println(constructorDeclarations.size());

        //for (ConstructorDeclaration constructorDeclaration : constructorDeclarations) {
        for (int i = 0; i < constructorDeclarations.size(); i++) {
            ConstructorDeclaration constructorDeclaration = constructorDeclarations.get(i);
//            System.out.println("1: " + constructorDeclaration.getName());
//            System.out.println("2: " + constructorDeclaration.getJavadocComment());
//            System.out.println("3: " + constructorDeclaration.getDeclarationAsString());

            JSONObject constructorDeclarationJsonObject = new JSONObject();

            constructorDeclarationJsonObject.put("name", constructorDeclaration.getName().toString());
//            constructorDeclarationJsonObject.put("declaration", constructorDeclaration.getDeclarationAsString());
            constructorDeclarationJsonObject.put("body", constructorDeclaration.getBody().toString());
            constructorDeclarationJsonObject.put("range", constructorDeclaration.getRange().get().toString());
            constructorDeclarationJsonObject.put("tokenRange", constructorDeclaration.getTokenRange().get().toString());
            if (!constructorDeclaration.getJavadoc().isEmpty()) {
                JSONArray javadocBlockTagsArr = new JSONArray();

                Javadoc javadoc = constructorDeclaration.getJavadoc().get();
                List<JavadocBlockTag> javadocBlockTags = javadoc.getBlockTags();

                for (JavadocBlockTag javadocBlockTag : javadocBlockTags) {
                    JSONObject javadocBlockTagObj = new JSONObject();
                    javadocBlockTagObj.put("javadocBlockTag", javadocBlockTag.toString());
                    javadocBlockTagObj.put("javadocBlockType", javadocBlockTag.getType().toString());
                    javadocBlockTagObj.put("javadocBlockTagContent", javadocBlockTag.getContent().toText());
                    javadocBlockTagObj.put("javadocBlockTagName", !javadocBlockTag.getName().isEmpty() ? javadocBlockTag.getName().get() : "");
                    javadocBlockTagsArr.add(javadocBlockTagObj);
                }
                constructorDeclarationJsonObject.put("javadoc", javadoc.toString());
                constructorDeclarationJsonObject.put("javadocBlockTags", javadocBlockTagsArr);
            } else {
                constructorDeclarationJsonObject.put("javadoc", "");
            }
            constructorDeclarationJsonObject.put("comment",
                    constructorDeclaration.getComment().isPresent() ? constructorDeclaration.getComment().get().getContent().toString() : "");
            constructorDeclarationJsonObject.put("javadocComment",
                    constructorDeclaration.getJavadocComment().isPresent() ? constructorDeclaration.getJavadocComment().get().toString() : "");

            String constructorQualifiedSignature = "";
            String constructorQualifiedName = "";
            try {
                constructorQualifiedName = constructorDeclaration.resolve().getQualifiedName();
            } catch (StackOverflowError | RuntimeException e) {
                System.out.println("Cannot resolve the method's qualified name - " + e.getMessage());
            }
            try {
                constructorQualifiedSignature = constructorDeclaration.resolve().getQualifiedSignature();
            } catch (StackOverflowError | RuntimeException e) {
                System.out.println("Cannot resolve the method's qualified signature - " + e.getMessage());
            }
            constructorDeclarationJsonObject.put("qualifiedName", constructorQualifiedName);
            constructorDeclarationJsonObject.put("qualifiedSignature", constructorQualifiedSignature);

            constructorDeclarationJsonObject.put("parameters", collectParametersOfMethod(constructorDeclaration.getParameters()));

            if (inputProject.getCollectFieldAccesses()) {
                constructorDeclarationJsonObject.put("fieldAccesses", collectFieldAccessesOfMember(constructorDeclaration));
            }

            if (inputProject.getCollectMethodCalls()) {
                constructorDeclarationJsonObject.put("methodCalls", collectMethodCallsOfMember(constructorDeclaration));
            }

            if (inputProject.getCollectLiteralExprs()) {
                constructorDeclarationJsonObject.put("literalExprs", collectLiteralExprsOfMember(constructorDeclaration));
            }
            if (inputProject.getCollectVariableDeclarationExprs()) {
                constructorDeclarationJsonObject.put("variableDeclarationExprs", collectVariableDeclarationExprsOfMember(constructorDeclaration));
            }

            try {
                CompilationUnit cu2 =  StaticJavaParser.parse(classFile);
                constructorDeclarationJsonObject.put("declaration", cu2.getTypes().get(0).findAll(ConstructorDeclaration.class).get(i).getDeclarationAsString());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            constructorDeclarationsJsonArray.add(constructorDeclarationJsonObject);
        }

        return constructorDeclarationsJsonArray;
    }
    public static JSONArray collectFieldDeclarationsInClass(InputProject inputProject, File classFile, CompilationUnit cu) {
        NodeList<TypeDeclaration<?>> typeDeclarations = cu.getTypes();
        TypeDeclaration typeDec = typeDeclarations.get(0);

        JSONArray fieldDeclarationsJsonArray = new JSONArray();
        List<FieldDeclaration> fieldDeclarations = typeDec.findAll(FieldDeclaration.class);

        for (FieldDeclaration fieldDeclaration : fieldDeclarations) {
            JSONObject fieldDeclarationJsonObject = new JSONObject();

            if (fieldDeclaration.getParentNode().get().equals(typeDec)) {
                fieldDeclarationJsonObject.put("fieldRange", fieldDeclaration.getRange().get().toString());
                fieldDeclarationJsonObject.put("fieldTokenRange", fieldDeclaration.getTokenRange().get().toString());
                fieldDeclarationJsonObject.put("fieldJavadoc", fieldDeclaration.getJavadoc().isPresent() ? fieldDeclaration.getJavadoc().get().toText() : "");
                fieldDeclarationJsonObject.put("fieldJavadocComment", fieldDeclaration.getComment().isPresent() ? fieldDeclaration.getComment().get().getContent().toString() : "");
                fieldDeclarationJsonObject.put("fieldType", fieldDeclaration.getVariables().get(0).getType().toString());
                fieldDeclarationJsonObject.put("fieldName", fieldDeclaration.getVariables().get(0).getName().toString());
                fieldDeclarationJsonObject.put("fieldInitializer", fieldDeclaration.getVariables().get(0).getInitializer().isPresent() ?
                        fieldDeclaration.getVariables().get(0).getInitializer().toString() : "");
                fieldDeclarationJsonObject.put("fieldAccessSpecifier", fieldDeclaration.getAccessSpecifier().toString());

                String fieldTypeResolved = "";
                String fieldTypeResolvedDescribed = "";
                try {
                    fieldTypeResolved = String.valueOf(fieldDeclaration.resolve().getType());
                } catch (Exception e) {
                    System.out.println("Cannot resolve field type");
                }
                try {
                    fieldTypeResolvedDescribed = String.valueOf(fieldDeclaration.resolve().getType().describe());
                } catch (Exception e) {
                    System.out.println("Cannot resolve field type");
                }
                fieldDeclarationJsonObject.put("fieldTypeResolved", fieldTypeResolved);
                fieldDeclarationJsonObject.put("fieldTypeResolvedDescribed", fieldTypeResolvedDescribed);

                if (inputProject.getCollectFieldAccesses()) {
                    fieldDeclarationJsonObject.put("fieldAccesses", collectFieldAccessesOfMember(fieldDeclaration));
                }

                if (inputProject.getCollectMethodCalls()) {
                    fieldDeclarationJsonObject.put("methodCalls", collectMethodCallsOfMember(fieldDeclaration));
                }

                if (inputProject.getCollectLiteralExprs()) {
                    fieldDeclarationJsonObject.put("literalExprs", collectLiteralExprsOfMember(fieldDeclaration));
                }

                fieldDeclarationsJsonArray.add(fieldDeclarationJsonObject);
            }
        }
        return fieldDeclarationsJsonArray;
    }

    public static JSONArray collectFieldAccessesOfMember(BodyDeclaration<?> member){
        List<FieldAccessExpr> fieldAccessesOfMember = member.findAll(FieldAccessExpr.class);
        JSONArray fieldAccesses = new JSONArray();
        int fieldAccessesId = 0;
        for (FieldAccessExpr fa : fieldAccessesOfMember) {
            fieldAccessesId++;

            JSONObject fieldAccess = new JSONObject();

            fieldAccess.put("fieldAccessId", fieldAccessesId);
            fieldAccess.put("fieldAccess", fa.toString());
            fieldAccess.put("fieldAccessName", fa.getNameAsString());
            fieldAccess.put("fieldAccessRange", fa.getRange().get().toString());
            fieldAccess.put("fieldAccessScope", fa.getScope().toString());

            fieldAccesses.add(fieldAccess);
        }
        return fieldAccesses;
    }
    public static JSONArray collectMethodCallsOfMember(BodyDeclaration<?> member){
        List<MethodCallExpr> methodCallsOfMember = member.findAll(MethodCallExpr.class);
        JSONArray methodCalls = new JSONArray();
        int methodCallId = 0;
        for (MethodCallExpr mc : methodCallsOfMember) {
            methodCallId++;

            JSONObject methodCall = new JSONObject();

            String name = mc.getNameAsString();
            String qualifiedSignature = "";
            String qualifiedName = "";
            String returnType = "";
            String returnTypeDescribed = "";

            try {
                qualifiedName = mc.resolve().getQualifiedName();
            } catch (StackOverflowError | RuntimeException e) {
                System.out.println("Cannot resolve the method qualified name - " + e.getMessage());
            }
            try {
                qualifiedSignature = mc.resolve().getQualifiedSignature();
            }
            catch (StackOverflowError | RuntimeException e) {
                System.out.println("Cannot resolve the method qualified signature - " + e.getMessage());
            }
            try {
                returnType = String.valueOf(mc.resolve().getReturnType());
                returnTypeDescribed = mc.resolve().getReturnType().describe().toString();
            } catch (StackOverflowError | RuntimeException e) {
                System.out.println("Cannot resolve the method return type - " + e.getMessage());
            }

            methodCall.put("methodCallId", methodCallId);
            methodCall.put("methodCall", mc.toString());
            methodCall.put("methodCallName", name);
            methodCall.put("methodCallRange", mc.getRange().get().toString());
            methodCall.put("methodQualifiedSignature", qualifiedSignature);
            methodCall.put("methodQualifiedName", qualifiedName);
            methodCall.put("methodReturnType", returnType);
            methodCall.put("methodReturnTypeDescribed", returnTypeDescribed);

            if(!mc.getScope().isEmpty()){
                methodCall.put("methodCallScope", mc.getScope().get().toString());
            } else {
                methodCall.put("methodCallScope", "");
            }

            methodCalls.add(methodCall);
        }

        return methodCalls;
    }
    public static JSONArray collectLiteralExprsOfMember(BodyDeclaration<?> member){
        List<LiteralExpr> literalExprsOfMember = member.findAll(LiteralExpr.class);
        JSONArray literalExprs = new JSONArray();
        int literalExprId = 0;
        for (LiteralExpr l : literalExprsOfMember) {
            literalExprId++;

            JSONObject literalExpr = new JSONObject();

            literalExpr.put("literalExprId", literalExprId);
            literalExpr.put("literalExpr", l.toString());
            literalExpr.put("literalExprClass", l.getClass().toString());
            literalExpr.put("literalExprRange", l.getRange().get().toString());

            literalExprs.add(literalExpr);
        }

        return literalExprs;
    }
    public static JSONArray collectParametersOfMethod(NodeList<Parameter> parametersOfMember){
        JSONArray parameters = new JSONArray();
        int parameterId = 0;
        for (Parameter p : parametersOfMember) {
            parameterId++;
            JSONObject parameter = new JSONObject();
            String parameterTypeResolved = "";
            String parameterTypeResolvedDescribed = "";

            parameter.put("parameterId", parameterId);
            parameter.put("parameter", p.toString());
            parameter.put("parameterName", p.getNameAsString());
            parameter.put("parameterType", p.getType().toString());
            try{
                parameterTypeResolved = p.resolve().getType().toString();
                parameterTypeResolvedDescribed = p.resolve().describeType();
            } catch (StackOverflowError | RuntimeException e) {
                System.out.println("Cannot resolve the method call - " + e.getMessage());
            }
            parameter.put("parameterTypeResolved", parameterTypeResolved);
            parameter.put("parameterTypeResolvedDescribed", parameterTypeResolvedDescribed);

            parameters.add(parameter);
        }

        return parameters;
    }
    public static JSONArray collectVariableDeclarationExprsOfMember(BodyDeclaration<?> member){
        List<VariableDeclarationExpr> variableDeclarationExprsOfMember = member.findAll(VariableDeclarationExpr.class);
        JSONArray variableDeclarationExprs = new JSONArray();
        int variableDeclarationExpId = 0;
        for (VariableDeclarationExpr vd : variableDeclarationExprsOfMember) {
            variableDeclarationExpId++;

            JSONObject variableDeclarationExp = new JSONObject();

            variableDeclarationExp.put("variableDeclarationExprId", variableDeclarationExpId);
            variableDeclarationExp.put("variableDeclarationExpr", vd.toString());
            variableDeclarationExp.put("variableDeclarationExprRange", vd.getRange().get().toString());

            NodeList<VariableDeclarator> declaredVariables = vd.getVariables();
            for(VariableDeclarator variable : declaredVariables){
                String variableTypeResolved = "";
                try{
                    variableTypeResolved = variable.getType().resolve().toString();
                } catch (StackOverflowError | RuntimeException e) {
                    System.out.println("Cannot resolve the field access - " + e.getMessage());
                }

                variableDeclarationExp.put("variableName", variable.getNameAsString());
                variableDeclarationExp.put("variableType", variable.getType().toString());
                variableDeclarationExp.put("variableTypeResolved", variableTypeResolved);
                variableDeclarationExp.put("variableRange", variable.getRange().get().toString());
            }

            variableDeclarationExprs.add(variableDeclarationExp);
        }

        return variableDeclarationExprs;
    }
    public static JSONArray collectImportsOfSourceFile(CompilationUnit cu){
        JSONArray importsArr = new JSONArray();
        NodeList<ImportDeclaration> imports = cu.getImports();
        int importId = 0;
        for(ImportDeclaration im : imports){
            importId++;
            JSONObject importObj = new JSONObject();
            importObj.put("importId", importId);
            importObj.put("import", im.toString());
            importsArr.add(importObj);
        }
        return importsArr;
    }

    /**
     * This method extracts the class name from the full path of a source code file
     * @param classFullPath is the full path of a source code file
     * @return a string of class name
     */
    public static String fullPath2ClassName(String classFullPath){
        String className = "";
        if(classFullPath.contains("plume")) {
            className = classFullPath.substring(classFullPath.indexOf("plume/")); //remove till
            className = className.substring(0, className.indexOf(".java")); //remove .java extension
            className = className.replace("/", "."); //replace / with .
        }
        else if(classFullPath.contains("guava")){
            className = classFullPath.substring(classFullPath.indexOf("com")); //remove till com
            className = className.substring(0, className.indexOf(".java")); //remove .java extension
            className = className.replace("/", "."); //replace / with .
        }
        else if(classFullPath.contains("jdk")){
            className = classFullPath.substring(classFullPath.indexOf("src")); //remove till src
            className = className.substring(0, className.indexOf(".java")); //remove .java extension
            className = className.replace("/", "."); //replace / with .
        }
        else {
            className = classFullPath.substring(classFullPath.indexOf("org")); //remove till org
            className = className.substring(0, className.indexOf(".java")); //remove .java extension
            className = className.replace("/", "."); //replace / with .
        }
        return className;
    }

    /**
     * This method runs recursively in subdirectories to retrieve all source code files
     * @param directory
     * @return a list of java source code files in the given directory
     */
    public static List<File> listSourceFiles(File directory) {
        List<File> allSourceFiles = new ArrayList<>();

        //skip the hidden files with .DS_Store extension (for mac os)
        File[] currentFiles = directory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return !name.equals(".DS_Store");
            }
        });

        for (int i = 0; i < currentFiles.length; i++) {
            if (currentFiles[i].isFile()) {
                if (currentFiles[i].getName().endsWith(".java")) {
                    allSourceFiles.add(currentFiles[i]);
                }
                //System.out.println("File " + currentFiles[i].getName());
            } else if (currentFiles[i].isDirectory()) {
                //System.out.println(currentFiles[i].getAbsoluteFile());
                allSourceFiles.addAll(listSourceFiles(currentFiles[i].getAbsoluteFile()));
                //System.out.println("Directory " + currentFiles[i].getName());
            }
        }
        return allSourceFiles;
    }

}
