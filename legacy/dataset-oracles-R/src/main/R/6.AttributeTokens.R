
get_tokensProjectClassesNonPrivateStaticAttributes <- function(projectId){
  # this function collects the "tokensProjectClassesNonPrivateStaticAttributes" column of the oracle-dataset
  # input: a numeric value represents id of the project we are processing, could be from 1 to 6.
  # output: a character vector includes tokens in format of "fieldSymbolicName1\;packageName1\;className1\;fieldDeclaration1\,fieldSymbolicName2\;packageName2\;className2\;fieldDeclaration2\, ..."
  
  # an example output (in the final oracle-dataset, double slashes will appear as one  (\\ becomes \)):
  # INSTANCE\\;org.apache.commons.collections4.iterators\\;EmptyOrderedMapIterator\\;@SuppressWarnings(\"rawtypes\")\n    public static final OrderedMapIterator INSTANCE = new EmptyOrderedMapIterator<Object, Object>()\\,RESETTABLE_INSTANCE\\;org.apache.commons.collections4.iterators\\;EmptyIterator\\;@SuppressWarnings(\"rawtypes\")\n    public static final ResettableIterator RESETTABLE_INSTANCE = new EmptyIterator<Object>()
  
  return(collapseTokenElements(get_all_nonPrivate_static_attributes_in_project_as_list(projectId)))
  
}

get_all_nonPrivate_static_attributes_in_project_as_list <- function(projectId){
  # this function collects all attributes in a given project which are non-private, and static 
  # this function is used to generate the "tokensProjectClassesNonPrivateStaticAttributes" column of the oracle-dataset
  # input: a numeric value represents id of the project we are processing (could be from 1 to 6) and a boolean variable implies the inclusion of static attributes 
  # output: a list includes four vectors: 
  # 1) symbolicNameVector includes all collected attributes' name 
  # 2) packageNameVector includes all collected attributes' package information 
  # 3) classNameVector includes all collected attributes' class name
  # 4) fieldDeclarationVector includes source codes used to declare the collected attributes
  
  # we include static attributes in the column tokensProjectClassesNonPrivateStaticAttributes
  staticsIncluded <- TRUE
  
  # create an empty list, this is filled  below
  symbolicNameVector <- character()
  packageNameVector <- character()
  classNameVector <- character()
  fieldDeclarationVector <- character()
  attributesList <- list(symbolicNameVector, packageNameVector, classNameVector, fieldDeclarationVector)
  
  # gather info of the project using given projectId
  projectName <- projectNames[projectId]
  javaParserDataJson <- allProjectsJavaParserDataList[[projectName]]
  
  # loop in source code files of the project to collect attributes of each
  for(sf in 1:length(javaParserDataJson)){
    attributesList <- mapply(c, attributesList, get_all_nonPrivate_attributes_in_class_as_list(projectId, sourceFileId = sf, staticsIncluded), SIMPLIFY = FALSE)
  }
  
  return(attributesList)
}

get_all_nonPrivate_attributes_in_class_as_list <- function(projectId, sourceFileId, staticsIncluded){
  # this function collects all attributes in a source file of a given project which are non-private, and static? (depends on staticsIncluded)
  # this function is used to generate the "tokensProjectClassesNonPrivateStaticAttributes" and "tokensMethodVariablesNonPrivateNonStaticAttributes" columns of the oracle-dataset
  # input: 
  # 1) a numeric value represents id of the project we are processing (could be from 1 to 6), 
  # 2) a numeric value represents id of the source file, and 
  # 3) a boolean variable implies the inclusion (or exclusion) of static attributes 
  # output: a list includes four vectors: 
  # 1) symbolicNameVector includes all collected attributes' name 
  # 2) packageNameVector includes all collected attributes' package information 
  # 3) classNameVector includes all collected attributes' class name
  # 4) fieldDeclarationVector includes source codes which are used to declare the collected attributes
  
  # create an empty list and vectors which are going to be filled
  attributesList<- list()
  symbolicNameVector <- character()
  packageNameVector <- character()
  classNameVector <- character()
  fieldDeclarationVector <- character()
  
  # gather info of the project using projectId
  if(projectId == "jdk"){
    javaParserDataJson <- javaParserDataJsonJDK
  } else {
    projectName <- projectNames[projectId]
    javaParserDataJson <- allProjectsJavaParserDataList[[projectName]]
  }
  
  # gather info of the source code file using sourceFileId
  sourceFileData <- javaParserDataJson[[sourceFileId]]
  sourceFileId <- sourceFileData$sourceFileId
  sourceFileName <- sourceFileData$sourceFileName
  packageName <- sourceFileData$packageName
  
  typeDeclarationsData <-  sourceFileData$typeDeclarations
  
  if(length(typeDeclarationsData) > 0){
    typeDeclarationData <- typeDeclarationsData[[1]]
    className <- typeDeclarationData$className
    fieldsDataOfClass <- typeDeclarationData$fields
    
    if(length(fieldsDataOfClass) > 0){
      # gather field related information
      for(fc in 1:length(fieldsDataOfClass)){
        fieldName <- fieldsDataOfClass[[fc]]$fieldName
        fieldTokenRange <- fieldsDataOfClass[[fc]]$fieldTokenRange
        # remove ";" at the end of field declaration 
        if(endsWith(fieldTokenRange, ';')){
          fieldTokenRange <- substring(fieldTokenRange, 1, nchar(fieldTokenRange) - 1)
        }
        
        fieldAccessSpecifier <- tolower(fieldsDataOfClass[[fc]]$fieldAccessSpecifier)
        isStatic <- ifelse(any(grepl(x = fieldTokenRange, pattern = "static")), TRUE, FALSE)
        isNonPrivate <- ifelse(fieldAccessSpecifier != "private", TRUE, FALSE)
        
        if(staticsIncluded){
          if(isNonPrivate & isStatic){
            symbolicNameVector <- c(symbolicNameVector, fieldName)
            packageNameVector <- c(packageNameVector, packageName)
            classNameVector <- c(classNameVector, className)
            fieldDeclarationVector <- c(fieldDeclarationVector, fieldTokenRange)
          }
        } else {
          if(isNonPrivate & !isStatic){
            symbolicNameVector <- c(symbolicNameVector, fieldName)
            packageNameVector <- c(packageNameVector, packageName)
            classNameVector <- c(classNameVector, className)
            fieldDeclarationVector <- c(fieldDeclarationVector, fieldTokenRange)
          }
        }
      }
    }
  }
  
  attributesList <- list(symbolicNameVector, packageNameVector, classNameVector, fieldDeclarationVector)
  
  return(attributesList)
}

get_tokensMethodVariablesNonPrivateNonStaticAttributes <- function(projectId, sourceFileId, methodId, isConstructor){

  staticsIncluded <- FALSE
  
  # create an empty list, this is filled  below
  symbolicNameVector <- character()
  packageNameVector <- character()
  classNameVector <- character()
  fieldDeclarationVector <- character()
  attributesList <- list(symbolicNameVector, packageNameVector, classNameVector, fieldDeclarationVector)
  
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
    } else{
      methodsData <- typeDeclarationData$methods
    }
    
    if(length(methodsData) > 0){
      methodData <- methodsData[[methodId]]
      
      # 1. collect attributes using method arguments types
      for(parameter in methodData$parameters){
        detectedParameterType <- get_type(projectId, sourceFileId, get_method_parameter_type_as_list(parameter))
        if(endsWith(detectedParameterType$finalType, "[]")){
          
          packageName <- detectedParameterType$finalClassPathOfType
          className <- sub(".*\\.", "", packageName)
          packageName <- gsub(paste(".", className, sep = ""), "", detectedParameterType$finalClassPathOfType, fixed = T)
          currentTypeList <- list("length", packageName, paste(className, "[]", sep = ""), "")
          if(className %in% javaPrimitiveTypes){
            packageName <- ""
            currentTypeList <- list("length", "", "", paste(className, "[]", sep = ""))
          }
          
          # currentTypeList <- list("length", packageName, className, "[]")
          attributesList <- mapply(c, attributesList, currentTypeList, SIMPLIFY = FALSE)
          # fieldsInfoVector <- c(fieldsInfoVector, paste("[length;", packageName, ";", className, escape_square_brackets("[]"), ";]", sep = ""))
        } else{
          detectedParameterTypePath <- detectedParameterType$finalClassPathOfType
          attributesList <- mapply(c, attributesList, 
                                   get_class_level_field_declarations_by_source_name_as_list(projectId, sourceFileId, className = detectedParameterTypePath, staticsIncluded), SIMPLIFY = FALSE)
          # fieldsInfoVector <- c(fieldsInfoVector, 
          #                       get_class_level_field_declarations_by_source_name_as_list(projectId, sourceFileId, className = detectedParameterTypePath, staticsIncluded))
        }
      }
      
      if(!isConstructor){
        # 2. collect attributes using method return type
        detectedMethodReturnType <- get_type(projectId, sourceFileId, get_method_return_type_as_list(methodData))
        detectedMethodReturnTypePath <- detectedMethodReturnType$finalClassPathOfType
        
        if(detectedMethodReturnTypePath != "void"){
          if(endsWith(detectedMethodReturnType$finalType, "[]")){
            
            packageName <- detectedMethodReturnType$finalClassPathOfType
            className <- sub(".*\\.", "", packageName)
            packageName <- gsub(paste(".", className, sep = ""), "", detectedMethodReturnType$finalClassPathOfType, fixed = T)
            if(className %in% javaPrimitiveTypes){
              packageName <- ""
            }
            
            currentTypeList <- list("length", packageName, className, "[]")
            attributesList <- mapply(c, attributesList, currentTypeList, SIMPLIFY = FALSE)
            # fieldsInfoVector <- c(fieldsInfoVector, paste("[length;", packageName, ";", className, escape_square_brackets("[]"), ";]", sep = ""))
            
          } else{
            attributesList <- mapply(c, attributesList, 
                                     get_class_level_field_declarations_by_source_name_as_list(projectId, sourceFileId, className = detectedMethodReturnTypePath, staticsIncluded), SIMPLIFY = FALSE)
            # fieldsInfoVector <- c(fieldsInfoVector, 
            #                       get_class_level_field_declarations_by_source_name_as_list(projectId, sourceFileId, className = detectedMethodReturnTypePath, staticsIncluded))
          }
        }
      }
       
      # 3. collect attributes in the same class
      # fieldsInfoVector <- c(fieldsInfoVector, get_class_level_field_declarations_by_source_name_as_list(projectId, sourceFileId, className = sourceFileName, staticsIncluded))
      attributesList <- mapply(c, attributesList, 
                               get_class_level_field_declarations_by_source_name_as_list(projectId, sourceFileId, className = sourceFileName, staticsIncluded), SIMPLIFY = FALSE)
      
    }
  }
  
  return(collapseTokenElements(attributesList))
}

get_class_level_field_declarations_by_source_name_as_list<- function(projectId, sourceFileId, className, staticsIncluded){
  attributesList <- list()
  
  # detect whether the given className belongs to project or jdk
  # check if className is in project's own source
  classDetectedInProject <- classDetectedInJDK <- FALSE

  detectedSourceFileName <- get_sourceFileName_of_class(projectId, sourceFileId, className)
  sourceFileNames <- c(sapply(allProjectsJavaParserDataList[[projectNames[projectId]]], `[[`, "sourceFileName"))
  detectedSourceFileId <- which(sourceFileNames == detectedSourceFileName)
  # sourceFileNames <- c(sapply(allProjectsJavaParserDataList[[projectNames[projectId]]], `[[`, "sourceFileName"))
  # if(grepl(x = className, pattern = ".", fixed = T)){
  #   sourceFileId <- which(sourceFileNames == className)
  # } else {
  #   sourceFileId <- which(endsWith(sourceFileNames, paste(".", className, sep = "")))
  # }
  if(length(detectedSourceFileId) > 0){
    classDetectedInProject <- TRUE
  } else {
    classDetectedInProject <- FALSE
  }
  # if the className is not in the project's own source, check if it is in java libraries
  if(length(detectedSourceFileId) == 0){
    sourceFileNames <- c(sapply(javaParserDataJsonJDK, `[[`, "sourceFileName"))
    detectedSourceFileId <- which(endsWith(sourceFileNames, paste(".", className, sep = "")))
    if(length(detectedSourceFileId) > 0){
      classDetectedInJDK <- TRUE
    } else {
      classDetectedInJDK <- FALSE
    }
    if(length(detectedSourceFileId) > 1){
      log_print(paste("WARNING: multiple match for a source file name in function get_class_level_field_declarations_by_source_name_as_list"))
      log_print(paste("className:", className, "matched files:", sourceFileNames[sourceFileId]))
    }
  }
  
  if(classDetectedInProject & classDetectedInJDK){
    log_print(paste("WARNING: a class name is found in both project and jdk (get_class_level_field_declarations_by_source_name_as_list)"))
  }
  
  if(length(detectedSourceFileId) == 1){
    if(classDetectedInProject){
      attributesList <- get_all_nonPrivate_attributes_in_class_as_list(projectId, detectedSourceFileId, staticsIncluded) 
    } else if(classDetectedInJDK) {
      attributesList <- get_all_nonPrivate_attributes_in_class_as_list(projectId = "jdk", detectedSourceFileId, staticsIncluded) 
    }
  }
  
  return(attributesList)
}






