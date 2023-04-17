
# functions used to generate tokensOracleVariablesNonPrivateNonStaticNonVoidMethods	& tokensOracleVariablesNonPrivateNonStaticAttributes columns ####

get_tokensOracleVariablesNonPrivateNonStaticNonVoidMethods <- function(projectId, sourceFileId, methodId, oracle, isConstructor){
  methodNameVector <- character()
  packageNameVector <- character()
  classNameVector <- character()
  methodDeclarationVector <- character()
  methodsList <- list(methodNameVector, packageNameVector, classNameVector, methodDeclarationVector)
  
  if(length(oracle) > 0){
    if(oracle != ""){
      returnTypesOfFunctionCalls <- find_oracle_function_return_type(projectId, sourceFileId, methodId, oracle, isConstructor)
      typesOfFields <- find_oracle_field_return_type(projectId, sourceFileId, methodId, oracle, isConstructor)
      types <- c(returnTypesOfFunctionCalls, typesOfFields)
      
      # returnTypeOfMethod
      # methodParameterTypes
      
      if(length(types) > 0){
        for(r in 1:length(types)){
          
          if(types[[r]][[1]]$finalType != "void"){
            
            # if it is an array 
            if(endsWith(types[[r]][[1]]$finalType, "[]")){
              
              currentTypeList <- list("clone", "java.lang", "Object", "protected native Object clone() throws CloneNotSupportedException")
              methodsList <- mapply(c, methodsList, currentTypeList, SIMPLIFY = FALSE)
      
              # finalList <- c(finalList, "[clone;java.lang;Object; protected native Object clone() throws CloneNotSupportedException]")
            } else {
              # finalList <- c(finalList, 
                             # get_nonPrivate_nonVoid_nonStatic_methods_in_class_return_vector(projectId, types[[r]][[1]]$finalClassPathOfType))
              methodsList <- mapply(c, methodsList, get_nonPrivate_nonVoid_nonStatic_methods_in_class_as_list(projectId, types[[r]][[1]]$finalClassPathOfType), SIMPLIFY = FALSE)
            }
          }
        }
      }
    }
  }
  
  return(collapseTokenElements(methodsList))
}

get_tokensOracleVariablesNonPrivateNonStaticAttributes <- function(projectId, sourceFileId, methodId, oracle, isConstructor){
  staticsIncluded <- FALSE
  
  # create an empty list, this is filled  below
  symbolicNameVector <- character()
  packageNameVector <- character()
  classNameVector <- character()
  fieldDeclarationVector <- character()
  attributesList <- list(symbolicNameVector, packageNameVector, classNameVector, fieldDeclarationVector)
  
  if(length(oracle) > 0){
    if(oracle != ""){
      returnTypesOfFunctionCalls <- find_oracle_function_return_type(projectId, sourceFileId, methodId, oracle, isConstructor)
      typesOfFields <- find_oracle_field_return_type(projectId, sourceFileId, methodId, oracle, isConstructor)
      types <- c(returnTypesOfFunctionCalls, typesOfFields)
      
      if(length(types) > 0){
        for(r in 1:length(types)){
          if(types[[r]][[1]]$finalType != "void"){
            
            # if it is an array
            if(endsWith(types[[r]][[1]]$finalType, "[]")){
              currentTypeList <- list("length", "", "", types[[r]][[1]]$finalType)
              attributesList <- mapply(c, attributesList, currentTypeList, SIMPLIFY = FALSE)
              # finalList <- c(finalList, paste("[length;;", escape_square_brackets(types[[r]][[1]]$finalType), ";]", sep = ""))
            } else {
              attributesList <- mapply(c, attributesList, 
                                       get_class_level_field_declarations_by_source_name_as_list(projectId, sourceFileId, className = types[[r]][[1]]$finalClassPathOfType, staticsIncluded), SIMPLIFY = FALSE)
              
              # finalList <- c(finalList, 
              #                get_class_level_field_declarations_by_source_name_return_vector(projectId, sourceFileId, types[[r]][[1]]$finalClassPathOfType, staticsIncluded))
            }
          }
        }
      }
    }
  }
  
  return(collapseTokenElements(attributesList))
}


find_oracle_function_return_type <- function(projectId, sourceFileId, methodId, oracle, isConstructor){
  returnTypes <- list()
  
  functionInfo <- extract_function_info_from_oracle(oracle)
  functionName <- functionInfo$functionName
  functionCallerName <- functionInfo$functionCallerName
  functionArguments <- unlist(functionInfo$functionArguments)
 
  if(length(functionName) > 0 & length(functionCallerName)){
    methodParameters <- get_method_parameters_by_method_id_return_list(projectId, sourceFileId, methodId, isConstructor)
    methodParameterNames <- c(sapply(methodParameters, `[[`, "parameterName"))
    methodParameterTypes <- c(sapply(methodParameters, `[[`, "parameterType"))
    methodParameterTypesResolvedDescribed <- c(sapply(methodParameters, `[[`, "parameterTypeResolvedDescribed"))
    
    classAndSourceNamesInProject <- get_all_class_names_and_source_file_names_in_project_return_vector(projectId)
    
    for(fn in 1:length(functionName)){
      if(functionCallerName[fn] == "this" | functionCallerName[fn] == "receiverObjectID"){
        
        allMethodsData <- get_all_methods_in_class_return_list(projectId, sourceFileId)
        methodNames <- c(sapply(allMethodsData, `[[`, "name"))
        detectedMethodId <- which(methodNames %in% functionName[fn])
        if(length(detectedMethodId) > 0){
          if(length(detectedMethodId) > 1){
            for(mId in detectedMethodId){
              if(length(allMethodsData[[mId]]$parameters) == length(functionArguments)){
                detectedMethodId <- mId
                break;
              }
            }
            returnTypes[[length(returnTypes) + 1]] <- list(get_type(projectId, sourceFileId, get_method_return_type_as_list(allMethodsData[[detectedMethodId]])))
            
            if(length(detectedMethodId) > 1){
              log_print(paste("WARNING: NUMBER OF DETECTED METHOD > 1, projectId:", projectId, "sourceFileId:", sourceFileId, "methodId:", methodId, "oracle:", oracle, "isConstructor:", isConstructor))
            }
            if(length(detectedMethodId) == 0){
              log_print(paste("WARNING: NUMBER OF DETECTED METHOD == 0, projectId:", projectId, "sourceFileId:", sourceFileId, "methodId:", methodId, "oracle:", oracle, "isConstructor:", isConstructor))
            }
          } else{
            returnTypes[[length(returnTypes) + 1]] <- list(get_type(projectId, sourceFileId, get_method_return_type_as_list(allMethodsData[[detectedMethodId]])))
          }
        }
        
      } else if(functionCallerName[fn] %in% methodParameterNames){
        
        detectedMethodParameterId <- which(functionCallerName[fn] == methodParameterNames)
        if(length(detectedMethodParameterId) > 0){
          detectedMethodParameterType <- 
            get_class_names_from_resolved_parameter_type(methodParameters[[detectedMethodParameterId]]$parameterTypeResolvedDescribed)
          detectedMethodId <- numeric()
          
          # 1st check project source 
          allClassesInProject <- get_all_class_names_and_source_file_names_in_project_return_vector(projectId)
          detectedSourceFileId <- which(detectedMethodParameterType == allClassesInProject$sourceFileNames)
          if(length(detectedSourceFileId) > 0){
            allMethodsData <- get_all_methods_in_class_return_list(projectId, detectedSourceFileId)
            methodNames <- c(sapply(allMethodsData, `[[`, "name"))
            detectedMethodId <- which(methodNames %in% functionName[fn])
          }
              
          # 2nd check java source 
          if(length(detectedSourceFileId) == 0){
            allMethodsData <- get_all_methods_in_jdk_class_return_list(detectedMethodParameterType)
            methodNames <- c(sapply(allMethodsData, `[[`, "name"))
            detectedMethodId <- which(methodNames %in% functionName[fn])
          }
          
          if(length(detectedMethodId) > 0){
            if(length(detectedMethodId) > 1){
              for(mId in detectedMethodId){
                if(length(allMethodsData[[mId]]$parameters) == length(functionArguments)){
                  detectedMethodId <- mId
                  break;
                }
              }
              returnTypes[[length(returnTypes) + 1]] <- list(get_type(projectId, sourceFileId, get_method_return_type_as_list(allMethodsData[[detectedMethodId]])))
              
              if(length(detectedMethodId) > 1){
                cat("\n DETECTED METHOD > 1, projectId:", projectId, "sourceFileId:", sourceFileId, "methodId:", methodId, "oracle:", oracle, "isConstructor:", isConstructor, "\n")
              }
              if(length(detectedMethodId) == 0){
                cat("\n DETECTED METHOD == 0, projectId:", projectId, "sourceFileId:", sourceFileId, "methodId:", methodId, "oracle:", oracle, "isConstructor:", isConstructor, "\n")
              }
            } else{
              returnTypes[[length(returnTypes) + 1]] <- list(get_type(projectId, sourceFileId, get_method_return_type_as_list(allMethodsData[[detectedMethodId]])))
            }
          }
        }
        
      } else if(functionCallerName[fn] %in% classAndSourceNamesInProject$classNames){
        
        detectedClassNameId <- which(functionCallerName[fn] == classAndSourceNamesInProject$classNames)
        allMethodsData <- get_all_methods_in_class_return_list(projectId, detectedClassNameId)
        methodNames <- c(sapply(allMethodsData, `[[`, "name"))
        detectedMethodId <- which(methodNames %in% functionName[fn])
        if(length(detectedClassNameId) > 0){
          if(length(detectedMethodId) > 1){
            for(mId in detectedMethodId){
              if(length(allMethodsData[[mId]]$parameters) == length(functionArguments)){
                detectedMethodId <- mId
                break;
              }
            }
            returnTypes[[length(returnTypes) + 1]] <- list(get_type(projectId, sourceFileId, get_method_return_type_as_list(allMethodsData[[detectedMethodId]])))
            
            if(length(detectedMethodId) > 1){
              cat("\n DETECTED METHOD > 1, projectId:", projectId, "sourceFileId:", sourceFileId, "methodId:", methodId, "oracle:", oracle, "isConstructor:", isConstructor, "\n")
            }
            if(length(detectedMethodId) == 0){
              cat("\n DETECTED METHOD == 0, projectId:", projectId, "sourceFileId:", sourceFileId, "methodId:", methodId, "oracle:", oracle, "isConstructor:", isConstructor, "\n")
            }
          } else{
            returnTypes[[length(returnTypes) + 1]] <- list(get_type(projectId, sourceFileId, get_method_return_type_as_list(allMethodsData[[detectedMethodId]])))
          }
        }
      }
      else if (functionCallerName[fn] == "methodResultID"){
        methodData <- allProjectsJavaParserDataList[[projectNames[projectId]]][[sourceFileId]]$typeDeclarations[[1]]$methods[[methodId]]
        
        detectedMethodReturnType <- get_type(projectId, sourceFileId, get_method_return_type_as_list(methodData))
        detectedMethodReturnType <- detectedMethodReturnType$finalClassPathOfType
        detectedMethodId <- numeric()
        
        # 1st check project source 
        allClassesInProject <- get_all_class_names_and_source_file_names_in_project_return_vector(projectId)
        detectedSourceFileId <- which(detectedMethodReturnType == allClassesInProject$sourceFileNames)
        if(length(detectedSourceFileId) > 0){
          allMethodsData <- get_all_methods_in_class_return_list(projectId, detectedSourceFileId)
          methodNames <- c(sapply(allMethodsData, `[[`, "name"))
          detectedMethodId <- which(methodNames %in% functionName[fn])
          
          if(length(detectedMethodId) > 0){
            if(length(detectedMethodId) > 1){
              for(mId in detectedMethodId){
                if(length(allMethodsData[[mId]]$parameters) == length(functionArguments)){
                  detectedMethodId <- mId
                  break;
                }
              }
              returnTypes[[length(returnTypes) + 1]] <- list(get_type(projectId, sourceFileId, get_method_return_type_as_list(allMethodsData[[detectedMethodId]])))
              
              if(length(detectedMethodId) > 1){
                cat("\n DETECTED METHOD > 1, projectId:", projectId, "sourceFileId:", sourceFileId, "methodId:", methodId, "oracle:", oracle, "isConstructor:", isConstructor, "\n")
              }
              if(length(detectedMethodId) == 0){
                cat("\n DETECTED METHOD == 0, projectId:", projectId, "sourceFileId:", sourceFileId, "methodId:", methodId, "oracle:", oracle, "isConstructor:", isConstructor, "\n")
              }
            } else{
              returnTypes[[length(returnTypes) + 1]] <- list(get_type(projectId, sourceFileId, get_method_return_type_as_list(allMethodsData[[detectedMethodId]])))
            }
          }
        }
        
        # 2nd check java source 
        if(length(detectedSourceFileId) == 0 | (length(detectedSourceFileId) > 0 & length(detectedMethodId) == 0)){
          allMethodsData <- get_all_methods_in_jdk_class_return_list(detectedMethodReturnType)
          methodNames <- c(sapply(allMethodsData, `[[`, "name"))
          detectedMethodId <- which(methodNames %in% functionName[fn])
          
          if(length(detectedMethodId) > 0){
            if(length(detectedMethodId) > 1){
              for(mId in detectedMethodId){
                if(length(allMethodsData[[mId]]$parameters) == length(functionArguments)){
                  detectedMethodId <- mId
                  break;
                }
              }
              returnTypes[[length(returnTypes) + 1]] <- list(get_type(projectId, sourceFileId, get_method_return_type_as_list(allMethodsData[[detectedMethodId]])))
              
              if(length(detectedMethodId) > 1){
                cat("\n DETECTED METHOD > 1, projectId:", projectId, "sourceFileId:", sourceFileId, "methodId:", methodId, "oracle:", oracle, "isConstructor:", isConstructor, "\n")
              }
              if(length(detectedMethodId) == 0){
                cat("\n DETECTED METHOD == 0, projectId:", projectId, "sourceFileId:", sourceFileId, "methodId:", methodId, "oracle:", oracle, "isConstructor:", isConstructor, "\n")
              }
            } else{
              returnTypes[[length(returnTypes) + 1]] <- list(get_type(projectId, sourceFileId, get_method_return_type_as_list(allMethodsData[[detectedMethodId]])))
            }
          }
        }
      }
    }
  }

  return(returnTypes)
}

extract_function_info_from_oracle <- function(oracle){
  functionName <- ""
  functionCallerName <- "" 
  
  processedOracle <- put_space_for_string_calc(oracle)
  tokensOfOracle <- unlist(strsplit(processedOracle, " "))
  tokensOfOracle <- tokensOfOracle[!tokensOfOracle %in% ""]
  
  dotIds <- which(tokensOfOracle == ".")
  openParenthesesIds <- which(tokensOfOracle == "(")
  closeParenthesesIds <- which(tokensOfOracle == ")")
  
  dotBeforeTheFunctionNameIds <- dotIds[(dotIds + 2) %in% openParenthesesIds]
  openParenthesesAfterTheFunctionNameIds <- dotBeforeTheFunctionNameIds + 2
  functionNameIds <- dotBeforeTheFunctionNameIds + 1
  functionCallerIds <- dotBeforeTheFunctionNameIds - 1
  
  functionName <- tokensOfOracle[functionNameIds]
  functionCallerName <- tokensOfOracle[functionCallerIds] 
  
  closingParanthesisAfterTheFunctionNameIds <- unlist(lapply(openParenthesesAfterTheFunctionNameIds, 
                                                             function(i) {closeParenthesesIds[which.min(abs(closeParenthesesIds - i))]}))
  
  # methodParamtersWithParanthesis <- mapply(function(x, y) {tokensOfOracle[x:y]}, openParenthesesAfterTheFunctionNameIds, closingParanthesisAfterTheFunctionNameIds)
  # sapply(methodParamtersWithParanthesis, "[[", function(x) {x[!x %in% c(",", ")", "(")]})
  # methodParamtersWithParanthesis <- tokensOfOracle[openParenthesesAfterTheFunctionNameIds:closingParanthesisAfterTheFunctionNameIds]
  # methodParameters <- methodParamtersWithParanthesis[!methodParamtersWithParanthesis %in% c(",", ")", "(")]
  
  functionArguments <- list()
  if(length(openParenthesesAfterTheFunctionNameIds) > 0){
    for(i in 1:length(openParenthesesAfterTheFunctionNameIds)){
      methodParamtersWithParanthesis <- tokensOfOracle[openParenthesesAfterTheFunctionNameIds[i]:closingParanthesisAfterTheFunctionNameIds[i]]
      methodParametersCleaned <- methodParamtersWithParanthesis[!methodParamtersWithParanthesis %in% c(",", ")", "(")]
      if(length(methodParametersCleaned) == 0){
        methodParametersCleaned <- ""
      }
      
      # some methodParametersCleaned variables may include dot, e.g., methodResultID.equals(receiverObjectID.getG2())
      if("." %in% methodParametersCleaned){
        dotIndex <- which(methodParametersCleaned == ".")
        methodParametersCleaned <- paste(methodParametersCleaned[(dotIndex - 1):(dotIndex + 1)], collapse = "")
      }
      functionArguments[[i]] <- methodParametersCleaned
    } 
  }
  
  return(list(functionName = functionName, functionCallerName = functionCallerName, functionArguments = functionArguments))
}


find_oracle_field_return_type <- function(projectId, sourceFileId, methodId, oracle, isConstructor){
  returnTypes <- list()
  
  fieldInfo <- extract_field_info_from_oracle(oracle)
  fieldName <- fieldInfo$fieldName
  fieldCallerName <- fieldInfo$fieldCallerName
  
  if(length(fieldName) > 0 & length(fieldCallerName)){
    methodParameters <- get_method_parameters_by_method_id_return_list(projectId, sourceFileId, methodId, isConstructor)
    methodParameterNames <- c(sapply(methodParameters, `[[`, "parameterName"))
    methodParameterTypes <- c(sapply(methodParameters, `[[`, "parameterType"))
    methodParameterTypesResolvedDescribed <- c(sapply(methodParameters, `[[`, "parameterTypeResolvedDescribed"))
    
    classAndSourceNamesInProject <- get_all_class_names_and_source_file_names_in_project_return_vector(projectId)
    
    for(fn in 1:length(fieldName)){
      if(fieldCallerName[fn] == "this" | fieldCallerName[fn] == "receiverObjectID"){
        
        # get fields in the class
        # detect the field we are looking for
        # get its type & add to list
        
        fieldsInClass <- allProjectsJavaParserDataList[[projectId]][[sourceFileId]]$typeDeclarations[[1]]$fields
        allFieldsNamesInClass <- sapply(fieldsInClass, "[[", "fieldName")
        detectedFieldId <- which(allFieldsNamesInClass == fieldName[fn])
        if(length(detectedFieldId) > 0){
          detectedField <- fieldsInClass[[detectedFieldId]]
          returnTypes[[length(returnTypes) + 1]] <- list(get_type(projectId, sourceFileId, get_field_return_type_as_list(detectedField)))
        }
        
      } else if(fieldCallerName[fn] %in% methodParameterNames){
        
          # detect method parameter which calls the field
          # get type of method parameter
          # get the class
          # get its fields
          # detect the field we are looking for
          # get its type & add to list
          
          detectedMethodParameterId <- which(fieldCallerName[fn] == methodParameterNames)
          if(length(detectedMethodParameterId) > 0){
            detectedMethodParameterType <- 
              get_class_names_from_resolved_parameter_type(methodParameters[[detectedMethodParameterId]]$parameterTypeResolvedDescribed)
            
            detectedFieldId <- numeric()
            
            # 1st check project source to find the class of method parameter
            allClassesInProject <- get_all_class_names_and_source_file_names_in_project_return_vector(projectId)
            detectedSourceFileId <- which(detectedMethodParameterType == allClassesInProject$sourceFileNames)
            if(length(detectedSourceFileId) > 0){
              fieldsInClass <- allProjectsJavaParserDataList[[projectId]][[detectedSourceFileId]]$typeDeclarations[[1]]$fields
              allFieldsNamesInClass <- sapply(fieldsInClass, "[[", "fieldName")
              detectedFieldId <- which(allFieldsNamesInClass == fieldName[fn])
              
              if(length(detectedFieldId) > 0){
                detectedField <- fieldsInClass[[detectedFieldId]]
                returnTypes[[length(returnTypes) + 1]] <- list(get_type(projectId, sourceFileId, get_field_return_type_as_list(detectedField)))
              }
            }
            
            # 2nd check jdk source to find the class of method parameter 
            if(length(detectedSourceFileId) == 0){
              fieldsInClass <- get_all_fields_in_jdk_class_return_list(detectedMethodParameterType)
              allFieldsNamesInClass <- sapply(fieldsInClass, "[[", "fieldName")
              detectedFieldId <- which(allFieldsNamesInClass == fieldName[fn])
              
              if(length(detectedFieldId) > 0){
                detectedField <- fieldsInClass[[detectedFieldId]]
                returnTypes[[length(returnTypes) + 1]] <- list(get_type(projectId, sourceFileId, get_field_return_type_as_list(detectedField)))
              }
            }
          }
      
        } else if(fieldCallerName[fn] %in% classAndSourceNamesInProject$classNames){
        
          detectedSourceFileId <- which(functionCallerName[fn] == classAndSourceNamesInProject$classNames)
          if(length(detectedSourceFileId) > 0){
            fieldsInClass <- allProjectsJavaParserDataList[[projectId]][[detectedSourceFileId]]$typeDeclarations[[1]]$fields
            allFieldsNamesInClass <- sapply(fieldsInClass, "[[", "fieldName")
            detectedFieldId <- which(allFieldsNamesInClass == fieldName[fn])
            
            if(length(detectedFieldId) > 0){
              detectedField <- fieldsInClass[[detectedFieldId]]
              returnTypes[[length(returnTypes) + 1]] <- list(get_type(projectId, sourceFileId, get_field_return_type_as_list(detectedField)))
            }
          }
        } else if (fieldCallerName[fn] == "methodResultID"){
        
          methodData <- allProjectsJavaParserDataList[[projectNames[projectId]]][[sourceFileId]]$typeDeclarations[[1]]$methods[[methodId]]
          detectedMethodReturnType <- get_type(projectId, sourceFileId, get_method_return_type_as_list(methodData))
          detectedMethodReturnType <- detectedMethodReturnType$finalClassPathOfType
          
          detectedFieldId <- numeric()
          
          # 1st check project source 
          allClassesInProject <- get_all_class_names_and_source_file_names_in_project_return_vector(projectId)
          detectedSourceFileId <- which(detectedMethodReturnType == allClassesInProject$sourceFileNames)
          if(length(detectedSourceFileId) > 0){
            fieldsInClass <- allProjectsJavaParserDataList[[projectId]][[detectedSourceFileId]]$typeDeclarations[[1]]$fields
            allFieldsNamesInClass <- sapply(fieldsInClass, "[[", "fieldName")
            detectedFieldId <- which(allFieldsNamesInClass == fieldName[fn])
            
            if(length(detectedFieldId) > 0){
              detectedField <- fieldsInClass[[detectedFieldId]]
              returnTypes[[length(returnTypes) + 1]] <- list(get_type(projectId, sourceFileId, get_field_return_type_as_list(detectedField)))
            }
          }
          
          # 2nd check java source 
          if(length(detectedSourceFileId) == 0 | (length(detectedSourceFileId) > 0 & length(detectedMethodId) == 0)){
            fieldsInClass <- get_all_fields_in_jdk_class_return_list(detectedMethodReturnType)
            allFieldsNamesInClass <- sapply(fieldsInClass, "[[", "fieldName")
            detectedFieldId <- which(allFieldsNamesInClass == fieldName[fn])
            
            if(length(detectedFieldId) > 0){
              detectedField <- fieldsInClass[[detectedFieldId]]
              returnTypes[[length(returnTypes) + 1]] <- list(get_type(projectId, sourceFileId, get_field_return_type_as_list(detectedField)))
            }
          }
      }
    }
  }
  
  return(returnTypes)
}

extract_field_info_from_oracle <- function(oracle){
  # input: an oracle, e.g., true ? methodResultID.equals(receiverObjectID.EMPTY_COLLECTION) : true
  # output: a list includes two items which are fieldName and fieldCallerName, e.g., receiverObjectID and EMPTY_COLLECTION
  
  fieldName <- ""
  fieldCallerName <- "" 
  
  processedOracle <- put_space_for_string_calc(oracle)
  tokensOfOracle <- unlist(strsplit(processedOracle, " "))
  tokensOfOracle <- tokensOfOracle[!tokensOfOracle %in% ""]
  
  dotIds <- which(tokensOfOracle == ".")
  openParenthesesIds <- which(tokensOfOracle == "(")
  closeParenthesesIds <- which(tokensOfOracle == ")")
  
  dotBeforeTheFieldNameIds <- dotIds[!(dotIds + 2) %in% openParenthesesIds]
  fieldNameIds <- dotBeforeTheFieldNameIds + 1
  fieldCallerIds <- dotBeforeTheFieldNameIds - 1
  
  fieldName <- tokensOfOracle[fieldNameIds]
  fieldCallerName <- tokensOfOracle[fieldCallerIds] 
  
  return(list(fieldName = fieldName, fieldCallerName = fieldCallerName))
}



get_method_parameters_by_method_id_return_list <- function(projectId, sourceFileId, methodId, isConstructor){
  methodParameters <- list()
  
  projectName <- projectNames[projectId]
  javaParserDataJson <- allProjectsJavaParserDataList[[projectName]]
  
  sourceFileData <- javaParserDataJson[[sourceFileId]]
  sourceFileName <- sourceFileData$sourceFileName
  typeDeclarationsData <- sourceFileData$typeDeclarations
  
  if(length(typeDeclarationsData) > 0){
    for(td in 1:length(typeDeclarationsData)){
      typeDeclarationData <- typeDeclarationsData[[td]]
      className <- typeDeclarationData$className
      
      if(isConstructor){
        constructorsData <- typeDeclarationData$constructors
        
        if(length(constructorsData) > 0){
          constructorData <- constructorsData[[methodId]]
          methodParameters <- constructorData$parameters
        }
      } else{
        methodsData <- typeDeclarationData$methods
        
        if(length(methodsData) > 0){
          methodData <- methodsData[[methodId]]
          methodParameters <- methodData$parameters
        }
      }
    }
  }
  
  return(methodParameters)
}

get_all_methods_in_class_return_list <- function(projectId, sourceFileId){
  methodsData <- list()
  
  projectName <- projectNames[projectId]
  javaParserDataJson <- allProjectsJavaParserDataList[[projectName]]
  
  sourceFileData <- javaParserDataJson[[sourceFileId]]
  sourceFileName <- sourceFileData$sourceFileName
  typeDeclarationsData <- sourceFileData$typeDeclarations
  
  if(length(typeDeclarationsData) > 0){
    for(td in 1:length(typeDeclarationsData)){
      typeDeclarationData <- typeDeclarationsData[[td]]
      className <- typeDeclarationData$className
      methodsData <- typeDeclarationData$methods
    }
  }
  
  return(methodsData)
}

get_all_methods_in_jdk_class_return_list <- function(sourceFileName){
  methodsData <- list()
  
  # javaParserDataJsonJDK
  
  if(length(sourceFileName) > 0){
    if(sourceFileName != ""){
   
      # search in java libraries
      sourceFileNames <- c(sapply(javaParserDataJsonJDK, `[[`, "sourceFileName"))
      sourceFileId <- which(endsWith(sourceFileNames, paste(".", sourceFileName, sep = "")))
      if(length(sourceFileId) > 1){
        log_print(paste("WARNING: multiple match for a source file name in function get_all_methods_in_jdk_class_return_list"))
        log_print(paste("sourceFileName:", sourceFileName, "matched files:", sourceFileNames[sourceFileId]))
      }
      if(length(sourceFileId) == 1){
        detectedSourceFileData <- javaParserDataJsonJDK[[sourceFileId]]
        detectedSourceFileName <- detectedSourceFileData$sourceFileName
        typeDeclarationsData <- detectedSourceFileData$typeDeclarations
        
        if(length(typeDeclarationsData) > 0){
          td <- 1
          # for(td in 1:length(typeDeclarationsData)){
            typeDeclarationData <- typeDeclarationsData[[td]]
            methodsData <- typeDeclarationData$methods
          # }
        }
      } 
    }
  }
  
  return(methodsData)
}

get_all_fields_in_jdk_class_return_list <- function(sourceFileName){
  fieldsData <- list()
  
  # javaParserDataJsonJDK
  
  if(length(sourceFileName) > 0){
    if(sourceFileName != ""){
      
      # search in java libraries
      sourceFileNames <- c(sapply(javaParserDataJsonJDK, `[[`, "sourceFileName"))
      sourceFileId <- which(endsWith(sourceFileNames, paste(".", sourceFileName, sep = "")))
      if(length(sourceFileId) > 1){
        log_print(paste("WARNING: multiple match for a source file name in function get_all_fields_in_jdk_class_return_list"))
        log_print(paste("sourceFileName:", sourceFileName, "matched files:", sourceFileNames[sourceFileId]))
      }
      if(length(sourceFileId) == 1){
        detectedSourceFileData <- javaParserDataJsonJDK[[sourceFileId]]
        detectedSourceFileName <- detectedSourceFileData$sourceFileName
        typeDeclarationsData <- detectedSourceFileData$typeDeclarations
        
        if(length(typeDeclarationsData) > 0){
          td <- 1
          # for(td in 1:length(typeDeclarationsData)){
          typeDeclarationData <- typeDeclarationsData[[td]]
          fieldsData <- typeDeclarationData$fields
          # }
        }
      } 
    }
  }
  
  return(fieldsData)
}

get_all_class_names_and_source_file_names_in_project_return_vector <- function(projectId){
  classNames <- character()
  sourceFileNames <- character()
  
  projectName <- projectNames[projectId]
 
  javaParserDataJson <- allProjectsJavaParserDataList[[projectName]]
  
  sourceFileNames <- c(sapply(javaParserDataJson, `[[`, "sourceFileName"))
  classNames <- sub(".*\\.", "", sourceFileNames)
  
  return(list(classNames = classNames, sourceFileNames = sourceFileNames))
}

####################################################################################################################################################


