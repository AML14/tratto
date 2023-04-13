
# currently we only use this function
get_tokensGeneralValuesGlobalDictionary <- function(){
  globalDict_old <- "[0;int],[1;int]"
  globalDict <- "0\\;int\\,1\\;int"
  
  return(globalDict)
}


create_vector_includes_all_oracles_return_vector <- function(jsonFilePath, projectId){
  # gather and put all oracles into a vector
  projectName <- projectNames[projectId]
  projectDir <- projectDirs[projectId]
  conditionsDir <- conditionDirs[projectId]
  
  allOracles <- character()
  
  javaParserDataJson <- fromJSON(paste(readLines(jsonFilePath), collapse = ""))
    
  for(sf in 1:length(javaParserDataJson)){
      sourceFileData <- javaParserDataJson[[sf]]
      sourceFileName <- sourceFileData$sourceFileName
      typeDeclarationsData <-  sourceFileData$typeDeclarations
      
      if(length(typeDeclarationsData) > 0){
        for(td in 1:length(typeDeclarationsData)){
          typeDeclarationData <- typeDeclarationsData[[td]]
          
          className <- typeDeclarationData$className
          constructorsData <- typeDeclarationData$constructors
          methodsData <- typeDeclarationData$methods
          
          if(length(constructorsData) > 0){
            for(cs in 1:length(constructorsData)){
              isConstructor <- TRUE
              
              constructorData <- constructorsData[[cs]]
              
              constructorName <- constructorData$constructorName
              constructorParameters <- constructorData$constructorParameters
              constructorParametersTypes <- c(sapply(constructorParameters, `[[`, "parameterType"))
              constructorParametersTypesResolvedDescribed <- c(sapply(constructorParameters, `[[`, "parameterTypeResolvedDescribed"))
              constructorJavadocBlockTags <- constructorData$javadocBlockTags
              
              if(length(constructorJavadocBlockTags)){
                for(j in 1:length(constructorJavadocBlockTags)){
                  
                  oracle <- javaParserDataJson[[sf]]$typeDeclarations[[td]]$constructors[[cs]]$javadocBlockTags[[j]]$oracle
                  
                  if(oracle != ""){
                    allOracles <- c(allOracles, oracle)
                  }
                }
              }
            }
          }
          
          if(length(methodsData) > 0){
            for(m in 1:length(methodsData)){
              isConstructor <- FALSE
              
              methodData <- methodsData[[m]]
              
              methodName <- methodData$methodName
              methodParameters <- methodData$methodParameters
              methodParametersTypes <- c(sapply(methodParameters, `[[`, "parameterType"))
              methodParametersTypesResolvedDescribed <- c(sapply(methodParameters, `[[`, "parameterTypeResolvedDescribed"))
              methodJavadocBlockTags <- methodData$javadocBlockTags
              
              if(length(methodJavadocBlockTags) > 0){
                for(j in 1:length(methodJavadocBlockTags)){
                  oracle <- javaParserDataJson[[sf]]$typeDeclarations[[td]]$methods[[m]]$javadocBlockTags[[j]]$oracle
                  
                  if(oracle != ""){
                    allOracles <- c(allOracles, oracle)
                  }
                }
              }
            }
          }
        }
      }
    }
   
  return(allOracles)
}

oracle_vector_to_global_dictionary_return_dataframe <- function(allOracles){
  tokens <- unlist(strsplit(put_space(gsub(x = allOracles, pattern = "->", replacement = "")), " "))
  tokens <- tokens[!tokens %in% ""]
  length(tokens)
  
  tokens_unique <- unique(tokens)
  length(tokens_unique)
  
  freqTable <- table(tokens)
  freqTable <- freqTable[rev(order(freqTable))]
  
  freqTable <- as.data.frame(freqTable)
  freqTable[grepl(x = freqTable$tokens, pattern = "^[0-9]|^-[0-9]"), "type"] <- "int"
  freqTable[freqTable$tokens == "null", "type"] <- "null"
  freqTable[freqTable$tokens == "true", "type"] <- "boolean"
  freqTable[freqTable$tokens == "false", "type"] <- "boolean"
  freqTable[intersect(which(freqTable$type == "int"), grep(x = freqTable$tokens, pattern = ".", fixed = T)), "type"] <- "float"
  
  finalList <- freqTable[!is.na(freqTable$type), c("tokens", "type", "Freq")]
  paste(paste("[", finalList$tokens, ",", finalList$type, "]", sep = ""), collapse = ";")
  
  return(finalList)
}

read_global_dict_file_return_single_vector <- function(){
  globalDictDf <- read.csv(file = paste(processingDataDir, "globalDict.csv", sep = ""))
  
  return(paste(paste("[", globalDictDf$tokens, ";", globalDictDf$type, "]", sep = ""), collapse = ";"))
}

