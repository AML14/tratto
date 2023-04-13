
get_tokensMethodArguments <- function(projectId, sourceFileId, methodParameters){
  # this function generates the "tokensMethodArguments" column of the oracle-dataset which includes the method parameters (arguments) of a method
  # input: the parameters of a method as in collected form via javaparser
  # 1) a numeric value represents id of the project we are processing (could be from 1 to 6) --project info will be used to determine a parameter's type in the case of it is not determined by the javaparser 
  # 2) a numeric value represents id of the source file that method under process belongs to --source file info will be used to determine a parameter's type in the case of it is not determined by the javaparser 
  # 3) the method parameters
  # output: the parameters of a method as in the processed format we use in the dataset
  # 1) processedMethodParameters vector includes the processed method parameters
  
  
  # parameterName, packageName, className
  processedMethodParameters <- list()
  parameterNameVector <- character()
  packageNameVector <- character()
  classNameVector <- character()
  
  for(parameter in methodParameters) {
    parameterName <- parameter$parameterName
    parameterType <- parameter$parameterType
    parameterTypeResolved <- parameter$parameterTypeResolved
    parameterTypeResolvedDescribed <- parameter$parameterTypeResolvedDescribed
    
    finalType <- get_type(projectId, sourceFileId, get_method_parameter_type_as_list(parameter))
    
    # we choose the 1st option explained below:
    
    # 1st option in parameter type representation in the case of it is a collection type: 
    # packageName: "java.util"
    # className: "HashMap"
    
    # 2nd option in parameter type representation in the case of it is a collection type:  
    # packageName: "java.util"
    # className: "HashMap<java.lang.Integer, java.util.HashSet<V>>"
    
    if(length(finalType) > 0){
      if(finalType$finalClassPathOfType %in% javaPrimitiveTypes){
        packageName <- ""
        className <- parameterType #1st option
        # className <- finalType$finalType #2nd option
      }
      else {
        # 1st option
        packageName <- finalType$finalClassPathOfType
        className <- sub(".*\\.", "", packageName)
        packageName <- gsub(paste(".", className, sep = ""), "", finalType$finalClassPathOfType, fixed = T)
        
        # 2nd option
        # packageName <- tools::file_path_sans_ext(finalType$finalClassPathOfType)
        # className <- sub(x = finalType$finalType, pattern = paste(packageName, ".", sep = ""), replacement = "")
      }
      
      parameterNameVector <- c(parameterNameVector, parameterName)
      packageNameVector <- c(packageNameVector, packageName)
      classNameVector <- c(classNameVector, className)
      
      # parameterData <- paste("[", 
      #                        parameterName, ";", 
      #                        packageName, ";", 
      #                        className, 
      #                        "]", sep = "")
      # processedMethodParameters <- c(processedMethodParameters, parameterData)
      
    }
  }
  
  processedMethodParameters <- list(parameterNameVector, packageNameVector, classNameVector)
  
  return(collapseTokenElements(processedMethodParameters))
}

get_tokensProjectClassesNonPrivateStaticNonVoidMethods <- function(projectId){
  # this function collects the "tokensProjectClassesNonPrivateStaticNonVoidMethods" column of the oracle-dataset
  # input: a numeric value represents id of the project we are processing, could be from 1 to 6.
  # output: a character vector includes tokens in format of "methodName1\;packageName1\;className1\;methodDeclaration1\,methodName2\;packageName2\;className2\;methodDeclaration2\, ..."
  
  # an example output (in the final oracle-dataset, double slashes will appear as one  (\\ becomes \)):
  # resettableEmptyIterator\\;org.apache.commons.collections4.iterators\\;EmptyIterator\\;public static ResettableIterator<E> resettableEmptyIterator()\\,emptyIterator\\;org.apache.commons.collections4.iterators\\;EmptyIterator\\;public static Iterator<E> emptyIterator()
  
  return(collapseTokenElements(get_all_nonPrivate_nonVoid_static_methods_in_project_as_list(projectId)))
}

get_all_nonPrivate_nonVoid_static_methods_in_project_as_list <- function(projectId){
  # this function collects all methods in a given project which are non-private, non-void and static
  # this function is used to generate the "tokensProjectClasses" column of the oracle-dataset
  # input: a numeric value represents id of the project we are processing, could be from 1 to 6.
  # output: a list includes four vectors: 
  # 1) methodNameVector includes all collected methods' name 
  # 2) packageNameVector includes all collected methods' package information 
  # 3) classNameVector includes all collected methods' class name
  # 4) methodDeclarationVector includes all collected methods' declarations
  
  methodsList <- list()
  methodNameVector <- character()
  packageNameVector <- character()
  classNameVector <- character()
  methodDeclarationVector <- character()
  
  projectName <- projectNames[projectId]
  javaParserDataJson <- allProjectsJavaParserDataList[[projectName]]
  
  for(sf in 1:length(javaParserDataJson)){
    sourceFileData <- javaParserDataJson[[sf]]
    sourceFileId <- sourceFileData$sourceFileId
    sourceFileName <- sourceFileData$sourceFileName
    packageName <- sourceFileData$packageName
    typeDeclarationsData <-  sourceFileData$typeDeclarations
    
    if(length(typeDeclarationsData) > 0){
      typeDeclarationData <- typeDeclarationsData[[1]]
      className <- typeDeclarationData$className
      
      methodsData <- typeDeclarationData$methods
      if(length(methodsData) > 0){
        for(m in 1:length(methodsData)){
          methodData <- methodsData[[m]]
          
          # method name, declaration
          methodName <- methodData$name
          methodDeclaration <- methodData$declaration
      
          # method return type
          methodReturnType <- methodData$type # this is method type
          methodResolvedReturnType <- methodData$returnTypeResolved  # this is resolved method type
          methodDescribedReturnType <- methodData$returnTypeDescribed  # this is resolved & described method type
          # an example case in method return type from plume.ICalAvailable, processOptions() :
          # methodReturnType: "/*@EnsuresNonNull(\"tz1\")*/\nvoid" 
          # methodResolvedReturnType: "com.github.javaparser.resolution.types.ResolvedVoidType@2145b572"
          # methodDescribedReturnType: "void"
    
          # method access type
          methodAccessSpecifier <- tolower(methodData$accessSpecifier)
          
          # method is static or not
          isStaticMethod <- ifelse(any(grepl(x = methodDeclaration, pattern = "static")), TRUE, FALSE)
          
          if(methodDescribedReturnType != "void" & methodAccessSpecifier != "private" & isStaticMethod){
            methodNameVector <- c(methodNameVector, methodName)
            packageNameVector <- c(packageNameVector, packageName)
            classNameVector <- c(classNameVector, className)
            methodDeclarationVector <- c(methodDeclarationVector, methodDeclaration)
          }
        }
      }
    }
  }
  
  methodsList <- list(methodNameVector, packageNameVector, classNameVector, methodDeclarationVector)
  
  return(methodsList)
}

get_tokensMethodVariablesNonPrivateNonStaticNonVoidMethods <- function(projectId, sourceFileId, methodId, isConstructor){
  # This function collects non-private, non-void and non-static method tokens based on a specific given method
  # Three sources are used to collect methods tokens: 
  # 1. the given method's argument types
  # 2. the given method's return type
  # 3. the class that the given method belongs to
  # input: 
  # 1) a numeric value represents id of the project we are processing (could be from 1 to 6) 
  # 2) a numeric value represents id of the source file that method under process belongs to 
  # 3) a numeric value represents id of the method
  # 4) a boolean value represents whether the method is constructor or not
  # output: a list includes four vectors: 
  # 1) methodNameVector includes all collected methods' name 
  # 2) packageNameVector includes all collected methods' package information 
  # 3) classNameVector includes all collected methods' class name
  # 4) methodDeclarationVector includes all collected methods' declarations
  
  methodNameVector <- character()
  packageNameVector <- character()
  classNameVector <- character()
  methodDeclarationVector <- character()
  methodsList <- list(methodNameVector, packageNameVector, classNameVector, methodDeclarationVector)
  
  projectName <- projectNames[projectId]
  javaParserDataJson <- allProjectsJavaParserDataList[[projectName]]
  
  sourceFileData <- javaParserDataJson[[sourceFileId]]
  sourceFileName <- sourceFileData$sourceFileName
  typeDeclarationsData <- sourceFileData$typeDeclarations
  
  if(length(typeDeclarationsData) > 0){
    typeDeclarationData <- typeDeclarationsData[[1]]
    className <- typeDeclarationData$className
    
    if(isConstructor){
      methodsData <- typeDeclarationData$constructors
    } else {
      methodsData <- typeDeclarationData$methods
    }
    
    if(length(methodsData) > 0){
      methodData <- methodsData[[methodId]]
      
      # 1. collect method tokens using method arguments types
      for(parameter in methodData$parameters){
        detectedParameterType <- get_type(projectId, sourceFileId, get_method_parameter_type_as_list(parameter))
        detectedParameterTypePath <- detectedParameterType$finalClassPathOfType
        methodsList <- mapply(c, methodsList, get_nonPrivate_nonVoid_nonStatic_methods_in_class_as_list(projectId, detectedParameterTypePath), SIMPLIFY = FALSE)
        # methodsInfoVector <- c(methodsInfoVector, get_nonPrivate_nonVoid_nonStatic_methods_in_class_as_list(projectId, detectedParameterTypePath))
      }
      
      # 2. collect method tokens using method return type
      if(!isConstructor){
        detectedMethodReturnType <- get_type(projectId, sourceFileId, get_method_return_type_as_list(methodData))
        detectedMethodReturnTypePath <- detectedMethodReturnType$finalClassPathOfType
        
        if(detectedMethodReturnTypePath != "void"){
          if(endsWith(detectedMethodReturnType$finalType, "[]")){
            currentTypeList <- list("clone", "java.lang", "Object", "protected native Object clone() throws CloneNotSupportedException")
            methodsList <- mapply(c, methodsList, currentTypeList, SIMPLIFY = FALSE)
            # methodsInfoVector <- c(methodsInfoVector, paste("[length;;", escape_square_brackets(detectedMethodReturnType$finalType), ";]", sep = ""))
          } else{
            methodsList <- mapply(c, methodsList, get_nonPrivate_nonVoid_nonStatic_methods_in_class_as_list(projectId, detectedMethodReturnTypePath), SIMPLIFY = FALSE)
            # methodsInfoVector <- c(methodsInfoVector, get_nonPrivate_nonVoid_nonStatic_methods_in_class_as_list(projectId, detectedMethodReturnTypePath))
          }
        }
      }
      
      # 3. collect method tokens in the same class
      # methodsInfoVector <- c(methodsInfoVector, get_nonPrivate_nonVoid_nonStatic_methods_in_class_as_list(projectId, sourceFileName))
      methodsList <- mapply(c, methodsList, get_nonPrivate_nonVoid_nonStatic_methods_in_class_as_list(projectId, sourceFileName), SIMPLIFY = FALSE)
    }
  }
  
  return(collapseTokenElements(methodsList))
}

get_nonPrivate_nonVoid_nonStatic_methods_in_class_as_list <- function(projectId, className){
  # This function collects non-private, non-void and non-static method tokens based on a specific given method
  # Three sources are used to collect methods tokens: 
  # 1. the given method's argument types
  # 2. the given method's return type
  # 3. the class that the given method belongs to
  # input: 
  # 1) a numeric value represents id of the project we are processing (could be from 1 to 6) 
  # 2) a character value represents a class name
  # output: a list includes four vectors: 
  # 1) methodNameVector includes all collected methods' name 
  # 2) packageNameVector includes all collected methods' package information 
  # 3) classNameVector includes all collected methods' class name
  # 4) methodDeclarationVector includes all collected methods' declarations
  
  methodsList <- list()
  methodNameVector <- character()
  packageNameVector <- character()
  classNameVector <- character()
  methodDeclarationVector <- character()
  
  # detect whether the given className belongs to project or jdk
  # check if className is in project's own source
  # TODO:
  # detectedSourceFileName <- get_sourceFileName_of_class(projectId, sourceFileId, className)
  # sourceFileNames <- c(sapply(javaParserDataJson, `[[`, "sourceFileName"))
  # detectedSourceFileId <- which(sourceFileNames == detectedSourceFileName)
  classDetectedInProject <- classDetectedInJDK <- FALSE
  sourceFileNames <- c(sapply(allProjectsJavaParserDataList[[projectNames[projectId]]], `[[`, "sourceFileName"))
  if(grepl(x = className, pattern = ".", fixed = T)){
    sourceFileId <- which(sourceFileNames == className)
  } else {
    sourceFileId <- which(endsWith(sourceFileNames, paste(".", className, sep = "")))
  }
  if(length(sourceFileId) > 0){
    classDetectedInProject <- TRUE
  } else {
    classDetectedInProject <- FALSE
  }
  # if the className is not in the project's own source, check if it is in java libraries
  if(length(sourceFileId) == 0){
    sourceFileNames <- c(sapply(javaParserDataJsonJDK, `[[`, "sourceFileName"))
    sourceFileId <- which(endsWith(sourceFileNames, paste(".", className, sep = "")))
    if(length(sourceFileId) > 0){
      classDetectedInJDK <- TRUE
    } else {
      classDetectedInJDK <- FALSE
    }
    if(length(sourceFileId) > 1){
      log_print(paste("WARNING: multiple match for a source file name in function get_nonPrivate_nonVoid_nonStatic_methods_in_class_as_list"))
      log_print(paste("className:", className, "matched files:", sourceFileNames[sourceFileId]))
    }
  }
  
  if(classDetectedInProject & classDetectedInJDK){
    log_print(paste("WARNING: a class name is found in both project and jdk (get_nonPrivate_nonVoid_nonStatic_methods_in_class_as_list)"))
  }
  
  
  if(length(sourceFileId) == 1){
    if(classDetectedInProject){
      javaParserDataJson <- allProjectsJavaParserDataList[[projectNames[projectId]]]
    } else if(classDetectedInJDK) {
      javaParserDataJson <- javaParserDataJsonJDK
    }
    
    sourceFileData <- javaParserDataJson[[sourceFileId]]
    sourceFileName <- sourceFileData$sourceFileName
    packageName <- sourceFileData$packageName
    typeDeclarationsData <- sourceFileData$typeDeclarations
    
    if(length(typeDeclarationsData) > 0){
      typeDeclarationData <- typeDeclarationsData[[1]]
      className <- typeDeclarationData$className
      
      methodsData <- typeDeclarationData$methods
      if(length(methodsData) > 0){
        for(m in 1:length(methodsData)){
          methodData <- methodsData[[m]]
          
          # method name, signature
          methodName <- methodData$name
          methodQualifiedName <- methodData$qualifiedName
          methodQualifiedSignature <- methodData$qualifiedSignature
          
          # # method declaration escaped "[" and "]" --CANCELLED
          # methodDeclaration <- escape_square_brackets(methodData$declaration)
          methodDeclaration <- methodData$declaration
          
          # method return type
          methodReturnType <- methodData$type # this is method type
          methodResolvedReturnType <- methodData$returnTypeResolved  # this is resolved method type
          methodDescribedReturnType <- methodData$returnTypeDescribed  # this is resolved & described method type
          # an example case in method return type from plume.ICalAvailable, processOptions() :
          # methodReturnType: "/*@EnsuresNonNull(\"tz1\")*/\nvoid" 
          # methodResolvedReturnType: "com.github.javaparser.resolution.types.ResolvedVoidType@2145b572"
          # methodDescribedReturnType: "void"
          
          # method access type
          methodAccessSpecifier <- tolower(methodData$accessSpecifier)
          
          if(methodDescribedReturnType != "void" & methodAccessSpecifier != "private" & !grepl(x = methodDeclaration, pattern = "static")){
            methodNameVector <- c(methodNameVector, methodName)
            packageNameVector <- c(packageNameVector, packageName)
            classNameVector <- c(classNameVector, className)
            methodDeclarationVector <- c(methodDeclarationVector, methodDeclaration)
          }
        }
      }
    }
  }
  
  methodsList <- list(methodNameVector, packageNameVector, classNameVector, methodDeclarationVector)
  
  return(methodsList)
}



