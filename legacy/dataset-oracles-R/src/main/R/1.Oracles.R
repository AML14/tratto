
convert_parameter_format_randoop_spec_to_javaparser <- function(randoopSpecParameter){
  # some sample inputs - expected outputs (converted version):
  # [I - int[]
  # [J - long[] 
  # [D - double[]
  # [B - byte[]
  # [Ljava.lang.Integer; - java.lang.Integer[]
  # [Ljava.lang.Long; - java.lang.Long[]
  # [[D - double[][] 
  # [I int - int[] int
  # java.lang.Object - java.lang.Object
  
  finalConvertedType <- character()
  
  randoopSpecParameterIsAnArray <- grepl(x = randoopSpecParameter, pattern = "\\[")
  
  if(randoopSpecParameterIsAnArray){
    arraySignPart <- gsub(x = randoopSpecParameter, pattern = "(\\[+)([^\\[].*)", "\\1")
    arrayTypePart <- gsub(x = randoopSpecParameter, pattern = "(\\[+)([^\\[].*)", "\\2")
    
    # "[" means one dimensional array
    # "[[" means two dimensional array
    numberOfDimension <- nchar(arraySignPart)
    
    # if the array type is not included in randoopTypes (I, J, D), that means arrayTypePart directly represents the type of the parameter
    convertedType <- randoopToJavaparserTypes[[arrayTypePart]]
    if(is.null(convertedType)){
      if(startsWith(arrayTypePart, "L")){
        arrayTypePart <- substr(arrayTypePart, 2, nchar(arrayTypePart))
      }
      if( endsWith(arrayTypePart, ";")){
        arrayTypePart <- substr(arrayTypePart, 1, nchar(arrayTypePart) - 1)
      }
      convertedType <- arrayTypePart
    }
    
    finalConvertedType <- c(finalConvertedType, paste(convertedType, paste(rep("[]", numberOfDimension), collapse = ""), sep = ""))
  } else {
    # if it is not an array, that means input directly represents the type of the parameter
    finalConvertedType <- c(finalConvertedType, randoopSpecParameter)
  }
  
  return(finalConvertedType)
}

clear_javadocTagContent_for_oracle_matching <- function(javadocTagContent){
  # squish
  # make lower all letters
  # remove commas (because jdoctor generated output does not keep commas)
  
  javadocTagContentModified <- str_squish(javadocTagContent)
  javadocTagContentModified <- tolower(str_squish(javadocTagContent))
  javadocTagContentModified <- gsub(x = javadocTagContentModified, pattern = ",", fixed = TRUE, replacement = "")
  
  return(javadocTagContentModified)
}

get_candidates_based_on_text_distance_of_tags_for_oracle_matching <- function(actualJavadocTag, jdoctorProcessedTags){
  distances <- stringdist(actualJavadocTag, jdoctorProcessedTags)
  minDistance <- min(distances)
  canditateItemsIds <- which(distances == minDistance)
  
  return(canditateItemsIds)
}


get_all_conditions_for_project_return_df <- function(projectId){
  conditionsDataFrame <- as.data.frame(matrix(nrow = 0, ncol = 20))
  colnames(conditionsDataFrame) <- c("className", 
                                     "methodName", 
                                     "numberOfMethodParameters",
                                     "methodParameterNames",
                                     "methodParameterTypes", 
                                     "convertedMethodParameterTypes",
                                     "throws_exception", 
                                     "throws_exception_name",
                                     "throws_description",
                                     "throws_guard_description",
                                     "throws_guard_condition",
                                     "post_property_condition",
                                     "post_property_description",
                                     "post_description",
                                     "post_guard_description",
                                     "post_guard_condition",
                                     "pre_description", 
                                     "pre_description_param_name", 
                                     "pre_guard_description",
                                     "pre_guard_condition")
  
  projectName <- projectNames[projectId]
  projectDir <- projectDirs[projectId]
  conditionsDir <- conditionDirs[projectId]
  
  classList <- read.table(classListDirs[projectId])
  
  for(cl in 1:nrow(classList)){
    sourceFileName <- classList[cl, 1]
    sourceFileNameWithHypen <- sourceFileName
    
    # modify source file name to be able to read the corresponding conditions file which includes jdoctor generated outputs
    # file naming format is not consistent, format may change depending on the project
    if(grepl(x = projectDir, pattern = "commons") || grepl(x = projectDir, pattern = "guava") || grepl(x = projectDir, pattern = "math")){
      sourceFileNameWithHypen <- gsub(x = sourceFileName, pattern = ".", replacement = "-", fixed = T)
    }
    if (grepl(x = projectDir, pattern = "gs") || grepl(x = projectDir, pattern = "plume") || grepl(x = projectDir, pattern = "jgrapht")){
      sourceFileNameWithHypen <- gsub(x = sourceFileName, pattern = ".", replacement = "_", fixed = T)
    }
    
    # get the jdoctor generated randoop specifications belongs to the given source file 
    randoopSpecFile <- paste(conditionsDir, sourceFileNameWithHypen, ".json", sep = "")
    if(file.exists(randoopSpecFile)){
      # put randoop spec file content (conditions) into a dataframe ####
      conditions <- rjson::fromJSON(file = paste(conditionsDir, sourceFileNameWithHypen, ".json", sep = ""))
      
      
      if(length(conditions) > 0){
        for(j in 1:length(conditions)){
          item <- conditions[[j]]
          
          operation <- item$operation
          identifiers <- item$identifiers
          identifierParameters <- paste(item$identifiers$parameters, collapse = " ")
          conditionClassName <- operation$classname
          conditionMethodName <- operation$name
          operationParameterTypes <- paste(operation$parameterTypes, collapse = " ")
          numberOfOperationParameters <- length(operation$parameterTypes)
          
          throws <- item$throws
          pre <- item$pre
          post <- item$post
          
          if(length(throws) > 0){
            for(k in 1:length(throws)){
              throwsException <- throws[[k]]$exception
              throwsDescription <- throws[[k]]$description
              throwsGuardCondition <- throws[[k]]$guard$condition
              throwsGuardDescription <- throws[[k]]$guard$description 
              
              conditionsDataFrame[nrow(conditionsDataFrame) + 1, "className"] <- conditionClassName
              conditionsDataFrame[nrow(conditionsDataFrame), "methodName"] <- conditionMethodName
              conditionsDataFrame[nrow(conditionsDataFrame), "numberOfMethodParameters"] <- numberOfOperationParameters
              conditionsDataFrame[nrow(conditionsDataFrame), "methodParameterNames"] <- identifierParameters
              conditionsDataFrame[nrow(conditionsDataFrame), "methodParameterTypes"] <- operationParameterTypes
              conditionsDataFrame[nrow(conditionsDataFrame), "convertedMethodParameterTypes"] <- 
                paste(unlist(lapply(unlist(strsplit(operationParameterTypes, " ")), convert_parameter_format_randoop_spec_to_javaparser)), collapse = " ")
              
              conditionsDataFrame[nrow(conditionsDataFrame), "throws_exception"] <- throwsException
              conditionsDataFrame[nrow(conditionsDataFrame), "throws_exception_name"] <- sub('.*\\.', '', throwsException)
              conditionsDataFrame[nrow(conditionsDataFrame), "throws_description"] <- throwsDescription
              conditionsDataFrame[nrow(conditionsDataFrame), "throws_guard_description"] <- throwsGuardDescription
              conditionsDataFrame[nrow(conditionsDataFrame), "throws_guard_condition"] <- throwsGuardCondition
            }
          }
          
          if(length(post) > 0){
            for(k in 1:length(post)){
              postPropertyCondition <- post[[k]]$property$condition
              postPropertyDescription <- post[[k]]$property$description
              
              postDescription <- post[[k]]$description 
              
              postGuardCondition <- post[[k]]$guard$condition
              postGuardDescription <- post[[k]]$guard$description
              
              conditionsDataFrame[nrow(conditionsDataFrame) + 1, "className"] <- conditionClassName
              conditionsDataFrame[nrow(conditionsDataFrame), "methodName"] <- conditionMethodName
              conditionsDataFrame[nrow(conditionsDataFrame), "numberOfMethodParameters"] <- numberOfOperationParameters
              conditionsDataFrame[nrow(conditionsDataFrame), "methodParameterNames"] <- identifierParameters
              conditionsDataFrame[nrow(conditionsDataFrame), "methodParameterTypes"] <- operationParameterTypes
              conditionsDataFrame[nrow(conditionsDataFrame), "convertedMethodParameterTypes"] <- 
                paste(unlist(lapply(unlist(strsplit(operationParameterTypes, " ")), convert_parameter_format_randoop_spec_to_javaparser)), collapse = " ")
              
              conditionsDataFrame[nrow(conditionsDataFrame), "post_property_condition"] <- postPropertyCondition
              conditionsDataFrame[nrow(conditionsDataFrame), "post_property_description"] <- postPropertyDescription
              
              conditionsDataFrame[nrow(conditionsDataFrame), "post_description"] <- postDescription
              
              conditionsDataFrame[nrow(conditionsDataFrame), "post_guard_description"] <- postGuardDescription
              conditionsDataFrame[nrow(conditionsDataFrame), "post_guard_condition"] <- postGuardCondition
            }
          }
          
          if(length(pre) > 0){
            for(k in 1:length(pre)){
              preDescription <- pre[[k]]$description
              preGuardCondition <- pre[[k]]$guard$condition
              preGuardDescription <- pre[[k]]$guard$description
              
              conditionsDataFrame[nrow(conditionsDataFrame) + 1, "className"] <- conditionClassName
              conditionsDataFrame[nrow(conditionsDataFrame), "methodName"] <- conditionMethodName
              conditionsDataFrame[nrow(conditionsDataFrame), "numberOfMethodParameters"] <- numberOfOperationParameters
              conditionsDataFrame[nrow(conditionsDataFrame), "methodParameterNames"] <- identifierParameters
              conditionsDataFrame[nrow(conditionsDataFrame), "methodParameterTypes"] <- operationParameterTypes
              conditionsDataFrame[nrow(conditionsDataFrame), "convertedMethodParameterTypes"] <- 
                paste(unlist(lapply(unlist(strsplit(operationParameterTypes, " ")), convert_parameter_format_randoop_spec_to_javaparser)), collapse = " ")
              
              conditionsDataFrame[nrow(conditionsDataFrame), "pre_description"] <- preDescription
              conditionsDataFrame[nrow(conditionsDataFrame), "pre_description_param_name"] <- unlist(strsplit(preDescription, " "))[2]
              conditionsDataFrame[nrow(conditionsDataFrame), "pre_guard_description"] <- preGuardDescription
              conditionsDataFrame[nrow(conditionsDataFrame), "pre_guard_condition"] <- preGuardCondition
            }
          }
        }
      }
      #################################################################
    }
  }
  
  return(conditionsDataFrame)
}

detect_matched_conditions <- function(projectId){
  conditionsOfProject <- get_all_conditions_for_project_return_df(projectId)
  matchedConditionsDf <- conditionsOfProject
  
  projectName <- projectNames[projectId]
  javaParserDataJson <- allProjectsJavaParserDataList[[projectName]]
  
  for(cd in 1:nrow(conditionsOfProject)){
    cat("project id:", projectId, "condition id:", cd, "\n")
    
    # get the oracle information: class, method, method parameters
    classNameOfOracle <- conditionsOfProject[cd, "className"]
    methodNameOfOracle <- conditionsOfProject[cd, "methodName"]
    numberOfMethodParametersOfOracle <- conditionsOfProject[cd, "numberOfMethodParameters"]
    methodParameterNamesOfOracle <- unlist(strsplit(conditionsOfProject[cd, "methodParameterNames"], " "))
    convertedMethodParameterTypesOfOracle <- unlist(strsplit(conditionsOfProject[cd, "convertedMethodParameterTypes"], " "))
    methodParameterTypesOfOracle <- unlist(strsplit(conditionsOfProject[cd, "methodParameterTypes"], " "))
    
    if(classNameOfOracle == methodNameOfOracle){
      isConstructor <- TRUE
    } else {
      isConstructor <- FALSE
    }
    
    # get the javaparser output based on the oracle's class, method, method parameters
    allSourceFileNames <- c(sapply(javaParserDataJson, `[[`, "sourceFileName"))
    sf <- which(allSourceFileNames == classNameOfOracle)
    sourceFileData <- javaParserDataJson[[sf]] 
    sourceFileName <- sourceFileData$sourceFileName
    typeDeclarationsData <-  sourceFileData$typeDeclarations
    
    if(length(typeDeclarationsData) > 0){
      typeDeclaration <- typeDeclarationsData[[1]]
      
      if(isConstructor){
        methodsData <- typeDeclaration$constructors
      } else {
        methodsData <- typeDeclaration$methods
      }
      
      if(length(methodsData) > 0){
        allMethodNames <- c(sapply(methodsData, `[[`, "name"))
        ifelse(isConstructor, mIds <- which(allMethodNames == sub(".*\\.", "", classNameOfOracle)), mIds <- which(allMethodNames == methodNameOfOracle))
        detectedMethodId <- numeric()
        
        if(length(mIds) > 0){
          for(m in mIds){
            methodData <- methodsData[[m]]
            methodParametersNames <- c(sapply(methodData$parameters, `[[`, "parameterName"))
            methodParametersTypes <- c(sapply(methodData$parameters, `[[`, "parameterType"))
            methodParametersConverted <- unlist(lapply(methodParametersTypes, get_class_names_from_resolved_parameter_type_2))
            methodParametersTypesResolvedDescribed <- c(sapply(methodData$parameters, `[[`, "parameterTypeResolvedDescribed"))
            methodParametersTypesResolvedDescribedConverted <- unlist(lapply(methodParametersTypesResolvedDescribed, get_class_names_from_resolved_parameter_type_2))
            numberOfMethodParameters <- length(methodData$parameters)
            
            if(numberOfMethodParametersOfOracle == numberOfMethodParameters){
              if(identical(convertedMethodParameterTypesOfOracle, methodParametersTypesResolvedDescribedConverted)){
                detectedMethodId <- m
                match_explanation <- "parameters_directly_matched"
                break;
              }
            }
          }
          if(length(detectedMethodId) == 0){
            for(m in mIds){
              methodData <- methodsData[[m]] 
              methodParametersNames <- c(sapply(methodData$parameters, `[[`, "parameterName"))
              methodParametersTypes <- c(sapply(methodData$parameters, `[[`, "parameterType"))
              methodParametersConverted <- unlist(lapply(methodParametersTypes, get_class_names_from_resolved_parameter_type_2))
              methodParametersTypesResolvedDescribed <- c(sapply(methodData$parameters, `[[`, "parameterTypeResolvedDescribed"))
              methodParametersTypesResolvedDescribedConverted <- unlist(lapply(methodParametersTypesResolvedDescribed, get_class_names_from_resolved_parameter_type_2))
              numberOfMethodParameters <- length(methodData$parameters)
              
              if(numberOfMethodParametersOfOracle == numberOfMethodParameters){
                # if(identical(sub("\\[\\]", "", sub(".*\\.", "", convertedMethodParameterTypesOfOracle)), methodParametersConverted)){
                if(identical(sub(".*\\.", "", convertedMethodParameterTypesOfOracle), sub(".*\\.", "", methodParametersConverted))){
                  detectedMethodId <- m
                  match_explanation <- "parameters_matched_with_modification"
                  break;
                }
              }
            }
          }
          if(length(detectedMethodId) == 0){
            if(length(mIds) == 1){
              detectedMethodId <- mIds
              match_explanation <- "parameters_does_not_matched_but_only_one_method_detected"
            }
          }
        }
        
        if(length(detectedMethodId) > 0){
          methodData <- methodsData[[detectedMethodId]] 
          methodJavadocBlockTags <- methodData$javadocBlockTags
          
          if(length(methodJavadocBlockTags) > 0){
            tagTypes <- c(sapply(methodJavadocBlockTags, `[[`, "javadocBlockType"))
            tagTypes <- unlist(lapply(tagTypes, function(i) switch(i, PARAM = {"@param"}, RETURN = {"@return"}, THROWS = {"@throws"}, EXCEPTION = {"@exception"}, {i})))
            tagNames <- c(sapply(methodJavadocBlockTags, `[[`, "javadocBlockTagName"))
            tagContents <- c(sapply(methodJavadocBlockTags, `[[`, "javadocBlockTagContent"))
            javadocTags <- str_squish(paste(tagTypes, tagNames, tagContents, sep = " "))
            
            tagThrow <- conditionsOfProject[cd, c("throws_exception", "throws_exception_name", "throws_description", "throws_guard_description", "throws_guard_condition")]
            tagPost <- conditionsOfProject[cd, c("post_property_condition", "post_property_description", "post_description", "post_guard_description", "post_guard_condition")]
            tagPre <- conditionsOfProject[cd, c("pre_description", "pre_description_param_name", "pre_guard_description", "pre_guard_condition")]
            
            isThrowTag <- any(!is.na(tagThrow))
            isPostTag <- any(!is.na(tagPost))
            isPreTag <- any(!is.na(tagPre))
            
            if(isThrowTag){
              javadocTagId <- grep(x = javadocTags, pattern = "@throws", fixed = T)
              javadocTagId <- c(javadocTagId, grep(x = javadocTags, pattern = "@exception", fixed = T))
              if(length(javadocTagId) > 0){
                matchedCondition <- javadocTags[javadocTagId] 
                if(length(matchedCondition) > 1){
                  matchedCondition <- matchedCondition[grep(x = matchedCondition, pattern = tagThrow$throws_exception_name)]
                  if(length(matchedCondition) > 1){
                    matchedCondition <- matchedCondition[get_candidates_based_on_text_distance_of_tags_for_oracle_matching(tagThrow$throws_description, matchedCondition)]
                    
                    if(length(matchedCondition) > 1)
                      cat("throws - cd:", cd, "- projectId:", projectId, "\n")
                  }
                } 
                matchedConditionsDf[cd, ] <- conditionsOfProject[cd, ]
                matchedConditionsDf[cd, "match_explanation"] <- paste("matched:", match_explanation)
                matchedConditionsDf[cd, "match_condition"] <- matchedCondition
                matchedConditionsDf[cd, "projectId"] <- projectId
                matchedConditionsDf[cd, "sf"] <- sf
                matchedConditionsDf[cd, "m/cs"] <- detectedMethodId
                matchedConditionsDf[cd, "cd"] <- cd
                matchedConditionsDf[cd, "isConstructor"] <- isConstructor
              } else {
                matchedConditionsDf[cd, ] <- conditionsOfProject[cd, ]
                matchedConditionsDf[cd, "match_explanation"] <- "no_javadoc_tag_somehow"
                matchedConditionsDf[cd, "match_condition"] <- matchedCondition
                matchedConditionsDf[cd, "projectId"] <- projectId
                matchedConditionsDf[cd, "sf"] <- sf
                matchedConditionsDf[cd, "m/cs"] <- detectedMethodId
                matchedConditionsDf[cd, "cd"] <- cd
                matchedConditionsDf[cd, "isConstructor"] <- isConstructor
              }
            }
            if(isPostTag){
              javadocTagId <- grep(x = javadocTags, pattern = "@return", fixed = T)
              if(length(javadocTagId) > 0){
                matchedCondition <- javadocTags[javadocTagId]
                if(length(javadocTagId) > 1){
                  cat("return - cd:", cd, "- projectId:", projectId, "\n")
                } 
                matchedConditionsDf[cd, ] <- conditionsOfProject[cd, ]
                matchedConditionsDf[cd, "match_explanation"] <- paste("matched:", match_explanation)
                matchedConditionsDf[cd, "match_condition"] <- matchedCondition
                matchedConditionsDf[cd, "projectId"] <- projectId
                matchedConditionsDf[cd, "sf"] <- sf
                matchedConditionsDf[cd, "m/cs"] <- detectedMethodId
                matchedConditionsDf[cd, "cd"] <- cd
                matchedConditionsDf[cd, "isConstructor"] <- isConstructor
              } else {
                matchedConditionsDf[cd, ] <- conditionsOfProject[cd, ]
                matchedConditionsDf[cd, "match_explanation"] <- "no_javadoc_tag_somehow"
                matchedConditionsDf[cd, "match_condition"] <- "-"
                matchedConditionsDf[cd, "projectId"] <- projectId
                matchedConditionsDf[cd, "sf"] <- sf
                matchedConditionsDf[cd, "m/cs"] <- detectedMethodId
                matchedConditionsDf[cd, "cd"] <- cd
                matchedConditionsDf[cd, "isConstructor"] <- isConstructor
              }
            }
            if(isPreTag){
              javadocTagId <- grep(x = javadocTags, pattern = "@param", fixed = T)
              if(length(javadocTagId) > 0){
                matchedCondition <- javadocTags[javadocTagId]
                if(length(javadocTagId) > 1){
                  matchedCondition <- javadocTags[which(tagPre$pre_description_param_name == tagNames)]
                  # if(length(matchedCondition) == 0){
                  #   matchedCondition <- javadocTags[get_candidates_based_on_text_distance_of_tags_for_oracle_matching(tagPre$pre_description, javadocTags)]
                  # }
                  if(length(matchedCondition) > 1){
                    cat("param - cd:", cd, "- projectId:", projectId, "\n")
                  }
                } 
                if(length(matchedCondition) > 0) {
                  matchedConditionsDf[cd, ] <- conditionsOfProject[cd, ]
                  matchedConditionsDf[cd, "match_explanation"] <- paste("matched:", match_explanation)
                  matchedConditionsDf[cd, "match_condition"] <- matchedCondition
                  matchedConditionsDf[cd, "projectId"] <- projectId
                  matchedConditionsDf[cd, "sf"] <- sf
                  matchedConditionsDf[cd, "m/cs"] <- detectedMethodId
                  matchedConditionsDf[cd, "cd"] <- cd
                  matchedConditionsDf[cd, "isConstructor"] <- isConstructor 
                }
              } else {
                matchedConditionsDf[cd, ] <- conditionsOfProject[cd, ]
                matchedConditionsDf[cd, "match_explanation"] <- "no_javadoc_tag_somehow"
                matchedConditionsDf[cd, "match_condition"] <- "-"
                matchedConditionsDf[cd, "projectId"] <- projectId
                matchedConditionsDf[cd, "sf"] <- sf
                matchedConditionsDf[cd, "m/cs"] <- detectedMethodId
                matchedConditionsDf[cd, "cd"] <- cd
                matchedConditionsDf[cd, "isConstructor"] <- isConstructor
              }
            }
            
            if(!isThrowTag & !isPostTag & !isPreTag){
              cat("no tag")
            }
          } else{
            matchedConditionsDf[cd, ] <- conditionsOfProject[cd, ]
            matchedConditionsDf[cd, "match_explanation"] <- "no_javadoc_somehow"
            matchedConditionsDf[cd, "match_condition"] <- "-"
            matchedConditionsDf[cd, "projectId"] <- projectId
            matchedConditionsDf[cd, "sf"] <- sf
            matchedConditionsDf[cd, "m/cs"] <- detectedMethodId
            matchedConditionsDf[cd, "cd"] <- cd
            matchedConditionsDf[cd, "isConstructor"] <- isConstructor
          }
        } else{
          matchedConditionsDf[cd, ] <- conditionsOfProject[cd, ]
          matchedConditionsDf[cd, "match_explanation"] <- "no_detected_method"
          matchedConditionsDf[cd, "match_condition"] <- "-"
          matchedConditionsDf[cd, "projectId"] <- projectId
          matchedConditionsDf[cd, "sf"] <- sf
          matchedConditionsDf[cd, "m/cs"] <- "-"
          matchedConditionsDf[cd, "cd"] <- cd
          matchedConditionsDf[cd, "isConstructor"] <- isConstructor
        }
      } else {
        matchedConditionsDf[cd + 1, ] <- conditionsOfProject[cd, ]
        matchedConditionsDf[cd, "match_explanation"] <- "no_method_data_somehow"
        matchedConditionsDf[cd, "match_condition"] <- "-"
        matchedConditionsDf[cd, "projectId"] <- projectId
        matchedConditionsDf[cd, "sf"] <- sf
        matchedConditionsDf[cd, "m/cs"] <-  "-"
        matchedConditionsDf[cd, "cd"] <- cd
        matchedConditionsDf[cd, "isConstructor"] <- isConstructor
      }
    } else {
      matchedConditionsDf[cd, ] <- conditionsOfProject[cd, ]
      matchedConditionsDf[cd, "match_explanation"] <- "no_class_data_somehow"
      matchedConditionsDf[cd, "match_condition"] <- "-"
      matchedConditionsDf[cd, "projectId"] <- projectId
      matchedConditionsDf[cd, "sf"] <- sf
      matchedConditionsDf[cd, "m/cs"] <- "-"
      matchedConditionsDf[cd, "cd"] <- cd
      matchedConditionsDf[cd, "isConstructor"] <- isConstructor
    }
  }
  
  return(matchedConditionsDf)
}

get_oracle_for_javadoc_tag <- function(projectId, sourceFileId, methodId, isConstructor, javadocTag, javadocTagType){
  
  matchId <- which(matchedOracleConditionsDataFrame$projectId == projectId &
                     # tolower(matchedOracleConditionsDataFrame$methodName) == tolower(sourceFileName) &
                     # matchedOracleConditionsDataFrame$match_explanation == "matched" &
                     matchedOracleConditionsDataFrame$match_condition == javadocTag &
                     matchedOracleConditionsDataFrame$isConstructor == isConstructor &
                     matchedOracleConditionsDataFrame$m.cs == methodId &
                     matchedOracleConditionsDataFrame$sf == sourceFileId)
  
  # tolower(matchedOracleConditionsDataFrame$methodName) == tolower(dataPointToAddDataFrame$methodName.final) &
  
  if(length(matchId) > 0){
    matchedJdoctorOracle <- switch(javadocTagType, 
                                   "@param" = { 
                                     unique(matchedOracleConditionsDataFrame[matchId, "pre_guard_condition"])
                                   },
                                   "@throws" = {
                                     unique(matchedOracleConditionsDataFrame[matchId, "throws_guard_condition"])
                                   },
                                   "@exception" = {
                                     unique(matchedOracleConditionsDataFrame[matchId, "throws_guard_condition"])
                                   },
                                   "@return" = {
                                     if(length(matchId) == 2){
                                       paste(matchedOracleConditionsDataFrame[matchId[1], "post_guard_condition"], "?", matchedOracleConditionsDataFrame[matchId[1], "post_property_condition"], ":", matchedOracleConditionsDataFrame[matchId[2], "post_property_condition"])
                                     } else {
                                       paste(unique(matchedOracleConditionsDataFrame[matchId, "post_guard_condition"]), "?", unique(matchedOracleConditionsDataFrame[matchId, "post_property_condition"]), ":", "true")
                                     }
                                   }
    )
  } else {
    matchedJdoctorOracle <- "" 
  }
  
  return(matchedJdoctorOracle)
}
