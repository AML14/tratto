
add_data_point_to_list <- function(javadocBlockExists, listOfDataPoints, dataPointToAdd){
  
  listOfDataPoints[[length(listOfDataPoints) + 1]] <- list(oracle = dataPointToAdd$oracle,
                                                           oracleType = ifelse(javadocBlockExists, dataPointToAdd$javadocTagType, ""),
                                                           projectName = dataPointToAdd$projectName,
                                                           packageName = dataPointToAdd$packageName,
                                                           className = dataPointToAdd$className,
                                                           javadocTag = ifelse(javadocBlockExists, dataPointToAdd$javadocTag, ""),
                                                           methodJavadoc = dataPointToAdd$methodJavadocComment,
                                                           methodSourceCode = dataPointToAdd$methodTokenRange,
                                                           classJavadoc = dataPointToAdd$classJavadoc,
                                                           # classSourceCode = dataPointToAdd$classTokenRange,
                                                           tokensGeneralGrammar = str_split(unlist(str_split(dataPointToAdd$tokensGeneralGrammar, "\\\\,")), "\\\\;"),
                                                           
                                                           tokensGeneralValuesGlobalDictionary = lapply(str_split(unlist(str_split(dataPointToAdd$globalDict, "\\\\,")), "\\\\;"),
                                                                                                        function(x) {setNames(as.list(x), c("value", "type"))}),
                                                           
                                                           tokensProjectClasses = lapply(str_split(unlist(str_split(dataPointToAdd$allClassesInProject, "\\\\,")), "\\\\;"),
                                                                                         function(x) {setNames(as.list(x), c("className", "packageName"))}),
                                                           
                                                           tokensProjectClassesNonPrivateStaticNonVoidMethods = lapply(str_split(unlist(str_split(dataPointToAdd$allMethodsInProject, "\\\\,")), "\\\\;"),
                                                                                                                       function(x) {setNames(as.list(x), c("methodName", "packageName", "className", "methodDeclaration"))}),
                                                           
                                                           tokensProjectClassesNonPrivateStaticAttributes = lapply(str_split(unlist(str_split(dataPointToAdd$allAttributesInProject, "\\\\,")), "\\\\;"),
                                                                                                                   function(x) {setNames(as.list(x), c("attributeName", "packageName", "className", "attributeDeclaration"))}),
                                                           
                                                           tokensMethodJavadocValues = if(dataPointToAdd$literalExprsMethodLevelJavadoc == ""){
                                                                                         list()
                                                                                       } else {
                                                                                         lapply(str_split(unlist(str_split(dataPointToAdd$literalExprsMethodLevelJavadoc, "\\\\,")), "\\\\;"),
                                                                                                function(x) { setNames(as.list(x), c("value", "type")) })
                                                                                       },
                                                           
                                                           tokensMethodArguments = if(dataPointToAdd$methodParameters == ""){
                                                                                     list()
                                                                                   } else {
                                                                                     lapply(str_split(unlist(str_split(dataPointToAdd$methodParameters, "\\\\,")), "\\\\;"),
                                                                                            function(x) { setNames(as.list(x), c("parameterName", "packageName", "className")) })
                                                                                   },
                                                           
                                                           tokensMethodVariablesNonPrivateNonStaticNonVoidMethods = if(dataPointToAdd$nonPrivateNonVoidNonStaticMethods == ""){
                                                                                                                       list()
                                                                                                                     } else {
                                                                                                                       lapply(str_split(unlist(str_split(dataPointToAdd$nonPrivateNonVoidNonStaticMethods, "\\\\,")), "\\\\;"),
                                                                                                                              function(x) { setNames(as.list(x), c("methodName", "packageName", "className", "methodDeclaration")) })
                                                                                                                     },
                                                           
                                                           tokensMethodVariablesNonPrivateNonStaticAttributes = if(dataPointToAdd$nonPrivateNonStaticAttributes == ""){
                                                                                                                   list()
                                                                                                                 } else {
                                                                                                                   lapply(str_split(unlist(str_split(dataPointToAdd$nonPrivateNonStaticAttributes, "\\\\,")), "\\\\;"),
                                                                                                                          function(x) { setNames(as.list(x), c("attributeName", "packageName", "className", "attributeDeclaration")) })
                                                                                                                 },
                                                           
                                                           tokensOracleVariablesNonPrivateNonStaticNonVoidMethods = if(dataPointToAdd$oracleMethodTokens == ""){
                                                                                                                       list()
                                                                                                                     } else {
                                                                                                                       lapply(str_split(unlist(str_split(dataPointToAdd$oracleMethodTokens, "\\\\,")), "\\\\;"),
                                                                                                                              function(x) { setNames(as.list(x), c("methodName", "packageName", "className", "methodDeclaration")) })
                                                                                                                     },
                                                                                                                     
                                                           tokensOracleVariablesNonPrivateNonStaticAttributes = if(dataPointToAdd$oracleAttributeTokens == ""){
                                                                                                                   list()
                                                                                                                 } else {
                                                                                                                   lapply(str_split(unlist(str_split(dataPointToAdd$oracleAttributeTokens, "\\\\,")), "\\\\;"),
                                                                                                                          function(x) { setNames(as.list(x), c("attributeName", "packageName", "className", "attributeDeclaration")) })
                                                                                                                 }
                                                           )
           
  log_print("data point added to list")
  
  return(listOfDataPoints)
}

add_data_point_to_dataframe <- function(javadocBlockExists, dataframe, dataPointToAdd){
  dataframe[nrow(dataframe) + 1, "oracle"] <- dataPointToAdd$oracle
  dataframe[nrow(dataframe), "oracleType"] <- ifelse(javadocBlockExists, dataPointToAdd$javadocTagType, "")
  dataframe[nrow(dataframe), "projectName"] <- dataPointToAdd$projectName
  dataframe[nrow(dataframe), "packageName"] <- dataPointToAdd$packageName
  dataframe[nrow(dataframe), "className"] <-  dataPointToAdd$className
  dataframe[nrow(dataframe), "javadocTag"] <- ifelse(javadocBlockExists, dataPointToAdd$javadocTag, "")
  dataframe[nrow(dataframe), "methodJavadoc"] <- dataPointToAdd$methodJavadocComment
  dataframe[nrow(dataframe), "methodSourceCode"] <- dataPointToAdd$methodTokenRange
  dataframe[nrow(dataframe), "classJavadoc"] <- dataPointToAdd$classJavadoc
  # dataframe[nrow(dataframe), "classSourceCode"] <- dataPointToAdd$classTokenRange
  dataframe[nrow(dataframe), "tokensGeneralGrammar"] <- dataPointToAdd$tokensGeneralGrammar
  dataframe[nrow(dataframe), "tokensGeneralValuesGlobalDictionary"] <- dataPointToAdd$globalDict
  dataframe[nrow(dataframe), "tokensProjectClasses"] <- dataPointToAdd$allClassesInProject
  dataframe[nrow(dataframe), "tokensProjectClassesNonPrivateStaticNonVoidMethods"] <- dataPointToAdd$allMethodsInProject
  dataframe[nrow(dataframe), "tokensProjectClassesNonPrivateStaticAttributes"] <- dataPointToAdd$allAttributesInProject
  dataframe[nrow(dataframe), "tokensMethodJavadocValues"] <- ifelse(length(dataPointToAdd$literalExprsMethodLevelJavadoc) == 0, "", dataPointToAdd$literalExprsMethodLevelJavadoc)
  dataframe[nrow(dataframe), "tokensMethodArguments"] <- dataPointToAdd$methodParameters
  dataframe[nrow(dataframe), "tokensMethodVariablesNonPrivateNonStaticNonVoidMethods"] <- ifelse(length(dataPointToAdd$nonPrivateNonVoidNonStaticMethods) == 0, "", dataPointToAdd$nonPrivateNonVoidNonStaticMethods)
  dataframe[nrow(dataframe), "tokensMethodVariablesNonPrivateNonStaticAttributes"] <- ifelse(length(dataPointToAdd$nonPrivateNonStaticAttributes) == 0, "", dataPointToAdd$nonPrivateNonStaticAttributes)
  dataframe[nrow(dataframe), "tokensOracleVariablesNonPrivateNonStaticNonVoidMethods"] <- ifelse(javadocBlockExists, ifelse(length(dataPointToAdd$oracleMethodTokens) == 0, "", dataPointToAdd$oracleMethodTokens), "")
  dataframe[nrow(dataframe), "tokensOracleVariablesNonPrivateNonStaticAttributes"] <- ifelse(javadocBlockExists, ifelse(length(dataPointToAdd$oracleAttributeTokens) == 0, "", dataPointToAdd$oracleAttributeTokens), "")

  dataframe[nrow(dataframe), "sourceFileId"] <- dataPointToAdd$sourceFileId
  dataframe[nrow(dataframe), "methodId"] <- dataPointToAdd$methodId
  dataframe[nrow(dataframe), "javadocTagId"] <- dataPointToAdd$javadocTagId
  
  log_print("data point added to dataframe")
  
  return(dataframe)
}

collect_method_or_constructor_level_tokens <- function(projectId, sourceFileId, methodOrConstructorId, isConstructor, dataPointToAdd){
  projectName <- projectNames[projectId]
  javaParserDataJson <- allProjectsJavaParserDataList[[projectName]]
  
  # get constructor/method data
  if(isConstructor){
    mcData <- javaParserDataJson[[sourceFileId]]$typeDeclarations[[1]]$constructors[[methodOrConstructorId]]
  } else {
    mcData <- javaParserDataJson[[sourceFileId]]$typeDeclarations[[1]]$methods[[methodOrConstructorId]]
  }
  
  # get method, name, signature, source code, javadoc
  dataPointToAdd$methodId <- methodOrConstructorId
  dataPointToAdd$isConstructor <- isConstructor
  dataPointToAdd$methodName <- mcData$name
  dataPointToAdd$methodQualifiedName <- mcData$qualifiedName
  dataPointToAdd$methodQualifiedSignature <- mcData$qualifiedSignature
  dataPointToAdd$methodTokenRange <- mcData$tokenRange
  dataPointToAdd$methodJavadocComment <- mcData$javadocComment
  
  # collect parameters that method takes
  dataPointToAdd$methodParameters <- 
    get_tokensMethodArguments(projectId, sourceFileId, mcData$parameters)
  
  # collect literal expressions in the method javadoc (if javadoc is empty, it will return empty)
  dataPointToAdd$literalExprsMethodLevelJavadoc <- 
    get_tokensMethodJavadocValues(mcData$javadocComment)
  
  # collect non-private, non-void, non-static methods
  dataPointToAdd$nonPrivateNonVoidNonStaticMethods <-
    get_tokensMethodVariablesNonPrivateNonStaticNonVoidMethods(projectId, sourceFileId, methodId = methodOrConstructorId, isConstructor)
  
  # collect non-private, non-void, non-static attributes
  dataPointToAdd$nonPrivateNonStaticAttributes <-
    get_tokensMethodVariablesNonPrivateNonStaticAttributes(projectId, sourceFileId, methodId = methodOrConstructorId, isConstructor)
  
  
  return(dataPointToAdd)
}

collect_javadocTag_level_tokens <- function(projectId, sourceFileId, methodOrConstructorId, isConstructor, javadocTagId, dataPointToAdd){
  projectName <- projectNames[projectId]
  javaParserDataJson <- allProjectsJavaParserDataList[[projectName]]
  
  # get javadoc tag data
  if(isConstructor){
    javadocBlockTag <- javaParserDataJson[[sourceFileId]]$typeDeclarations[[1]]$constructors[[methodOrConstructorId]]$javadocBlockTags[[javadocTagId]]
  } else {
    javadocBlockTag <- javaParserDataJson[[sourceFileId]]$typeDeclarations[[1]]$methods[[methodOrConstructorId]]$javadocBlockTags[[javadocTagId]]
  }
  
  javadocTagTypeInJavaParserFormat <- javadocBlockTag$javadocBlockType
  javadocTagTypeInOracleDatasetFormat <- convert_javaparser_generated_javadocTagType_to_oracle_dataset_format(javadocTagTypeInJavaParserFormat)
  javadocTagTypeInNormalJavadocFormat <- convert_javaparser_generated_javadocTagType_to_javadoc_format(javadocTagTypeInJavaParserFormat)
  javadocTagName <- javadocBlockTag$javadocBlockTagName
  javadocTagContent <- javadocBlockTag$javadocBlockTagContent
  javadocTag <- str_squish(paste(javadocTagTypeInNormalJavadocFormat, javadocTagName, javadocTagContent, sep = " "))
  
  dataPointToAdd$javadocTagId <- javadocTagId
  dataPointToAdd$javadocTag <- javadocTag
  dataPointToAdd$javadocTagType <- javadocTagTypeInOracleDatasetFormat
  
  # check if an oracle is matched for the javadocTag
  matchedJdoctorOracle <- get_oracle_for_javadoc_tag(projectId, sourceFileId, methodId = methodOrConstructorId, isConstructor, javadocTag, javadocTagType = javadocTagTypeInNormalJavadocFormat)
  
  # if javadoc tag matched with an oracle, collect oracle related tokens, if not does not collect
  oracleIsMatched <- FALSE 
  
  if(length(matchedJdoctorOracle) > 0){
    if(any(matchedJdoctorOracle != "")){
      oracleIsMatched <- TRUE
      log_print(paste("oracle is detected for javadoc: ", javadocTag, "- javadocId:", javadocTagId, "- oracle:", matchedJdoctorOracle))
    }
  }
  
  if(oracleIsMatched){
    # add oracle to the data point
    dataPointToAdd$oracle <- matchedJdoctorOracle
    
    # collect method tokens 
    dataPointToAdd$oracleMethodTokens <-
      get_tokensOracleVariablesNonPrivateNonStaticNonVoidMethods(projectId, sourceFileId, methodId = methodOrConstructorId, oracle = matchedJdoctorOracle, isConstructor)
    
    # collect attribute tokens 
    dataPointToAdd$oracleAttributeTokens <-
      get_tokensOracleVariablesNonPrivateNonStaticAttributes(projectId, sourceFileId, methodId = methodOrConstructorId, oracle = matchedJdoctorOracle, isConstructor)
  } else {
    # if javadoc tag does not matched with an oracle
    dataPointToAdd$oracle <- ";"
    
    # collect method tokens 
    dataPointToAdd$oracleMethodTokens <- ""
    
    # collect attribute tokens 
    dataPointToAdd$oracleAttributeTokens <- ""
  }
  
  return(dataPointToAdd)
}

generate_final_dataset <- function(projectId){
  finalDataFrame <- data.frame()
  finalListOfDataPoints <- list()
  finalListOfDataPoints_v2 <-list()
  dataPointToAdd <- list()
  
  projectName <- projectNames[projectId]
  javaParserDataJson <- allProjectsJavaParserDataList[[projectName]]
  
  logPath <- file.path(myWorkingDir, paste(projectName, "_final_data_set_generation.log", sep = ""))
  lf <- log_open(logPath)
  log_print(paste("Collecting final dataset for", projectName))

  dataPointToAdd$projectName <- projectName
  dataPointToAdd$allClassesInProject <- get_tokensProjectClasses(projectId)
  dataPointToAdd$allMethodsInProject <- get_tokensProjectClassesNonPrivateStaticNonVoidMethods(projectId)
  dataPointToAdd$allAttributesInProject <- get_tokensProjectClassesNonPrivateStaticAttributes(projectId)
  dataPointToAdd$tokensGeneralGrammar <- tokensGeneralGrammar
  dataPointToAdd$globalDict <- get_tokensGeneralValuesGlobalDictionary()
  
  # json v2
  # finalListOfDataPoints_v2[[length(finalListOfDataPoints_v2) + 1]] <- list(projectName = projectName)
  # 
  # finalListOfDataPoints_v2[[length(finalListOfDataPoints_v2)]][["projectName"]] <- list(tokensGeneralGrammar = tokensGeneralGrammar,
  #                                                                                     tokensGeneralValuesGlobalDictionary = dataPointToAdd$globalDict,
  #                                                                                     tokensProjectClasses = dataPointToAdd$allClassesInProject,
  #                                                                                     tokensProjectClassesNonPrivateStaticNonVoidMethods = dataPointToAdd$allMethodsInProject)

  for(sf in 1:length(javaParserDataJson)){
    sourceFileData <- javaParserDataJson[[sf]]
    sourceFileId <- sourceFileData$sourceFileId
    sourceFileName <- sourceFileData$sourceFileName
    packageName <- sourceFileData$packageName
    
    dataPointToAdd$sourceFileId <- sourceFileId
    dataPointToAdd$sourceFileName <- sourceFileName
    dataPointToAdd$packageName <- packageName
    
    typeDeclarationsData <-  sourceFileData$typeDeclarations
    
    if(length(typeDeclarationsData) > 0){
      # for(td in 1:length(typeDeclarationsData)){
        td <- 1
        typeDeclarationData <- typeDeclarationsData[[td]]
        
        # get class-level info: class name, javadoc, source code
        dataPointToAdd$className <- typeDeclarationData$className
        dataPointToAdd$classJavadoc <- typeDeclarationData$classJavadoc
        dataPointToAdd$classTokenRange <- get_class_source_code(projectId, sourceFileId)
        
        
        # # json v2
        # finalListOfDataPoints_v2[[length(finalListOfDataPoints_v2) + 1]] <- list(className = dataPointToAdd$className,
        #                                                                          classJavadoc = dataPointToAdd$classJavadoc)
        
        # currentList <- finalListOfDataPoints_v2[[length(finalListOfDataPoints_v2)]][["projectName"]]
        # finalListOfDataPoints_v2[[length(finalListOfDataPoints_v2)]][["projectName"]][[length(currentList) + 1]] <- list(classes = list(className = dataPointToAdd$className,
        #                                                                                                                  classJavadoc = dataPointToAdd$classJavadoc))
        # 
        # 
        
       
        # get constructors
        constructorsData <- typeDeclarationData$constructors
        if(length(constructorsData) > 0){
          for(cs in 1:length(constructorsData)){
            log_print(paste("sourceFileId: ", sourceFileId, "- sourceFileName:", sourceFileName, "- constructorId:", cs))
            
            isConstructor <- TRUE
            
            # get constructor, name, signature, source code, javadoc
            constructorData <- constructorsData[[cs]]
            
            dataPointToAdd <- collect_method_or_constructor_level_tokens(projectId, sourceFileId, methodOrConstructorId = cs, isConstructor, dataPointToAdd)
            
              
            # there are three options for javadoc tags:
            # 1- does not exist
            # 2- exists but does not have oracle generated by jdoctor
            # 3- exists and have an oracle generated by jdoctor
            
            javadocBlockTags <- constructorData$javadocBlockTags
            if(length(javadocBlockTags)){
              for(j in 1:length(javadocBlockTags)){
                if(javadocBlockTags[[j]]$javadocBlockType %in% acceptedJavadocTagTypes){
                  dataPointToAdd <- collect_javadocTag_level_tokens(projectId, sourceFileId, methodOrConstructorId = cs, isConstructor, javadocTagId = j, dataPointToAdd)
              
                  # add data point to the dataframe
                  finalDataFrame <- add_data_point_to_dataframe(javadocBlockExists = TRUE, finalDataFrame, dataPointToAdd)
                  finalListOfDataPoints <- add_data_point_to_list(javadocBlockExists = TRUE, finalListOfDataPoints, dataPointToAdd)
                }
              }
            } 
          }
        }

        # get methods
        methodsData <- typeDeclarationData$methods
        if(length(methodsData) > 0){
          for(m in 1:length(methodsData)){
            log_print(paste("sourceFileId: ", sourceFileId, "- sourceFileName:", sourceFileName, "- methodId:", m))
            
            isConstructor <- FALSE

            # get method, name, signature, source code, javadoc
            methodData <- methodsData[[m]]
            
            dataPointToAdd <- collect_method_or_constructor_level_tokens(projectId, sourceFileId, methodOrConstructorId = m, isConstructor, dataPointToAdd)
            
            # # json v2
            # finalListOfDataPoints_v2[[length(finalListOfDataPoints_v2)]]["methods"] <- list(className = dataPointToAdd$className,
            #                                                                                 classJavadoc = dataPointToAdd$classJavadoc)
            # 
            
            # there are three options for javadoc tags:
            # 1- does not exist
            # 2- exists but does not have oracle generated by jdoctor
            # 3- exists and have an oracle generated by jdoctor
            
            javadocBlockTags <- methodData$javadocBlockTags
            if(length(javadocBlockTags) > 0){
              for(j in 1:length(javadocBlockTags)){
                if(javadocBlockTags[[j]]$javadocBlockType %in% acceptedJavadocTagTypes){
                  
                  dataPointToAdd <- collect_javadocTag_level_tokens(projectId, sourceFileId, methodOrConstructorId = m, isConstructor, javadocTagId = j, dataPointToAdd)
                  
                  # add data point to the dataframe
                  finalDataFrame <- add_data_point_to_dataframe(javadocBlockExists = TRUE, finalDataFrame, dataPointToAdd)
                  finalListOfDataPoints <- add_data_point_to_list(javadocBlockExists = TRUE, finalListOfDataPoints, dataPointToAdd)
                }
              }
            } 
          }
        }
      # }
    }
  }
  
  log_close()
  
  return(list(finalDataFrame = finalDataFrame, finalListOfDataPoints = finalListOfDataPoints))
}

