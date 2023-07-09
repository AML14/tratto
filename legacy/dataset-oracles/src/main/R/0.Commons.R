
tokensGeneralGrammar_old <- "[null],[true],[false],[,],[.],[:],[;],[?],[jdVar],[stream],[Arrays],[allMatch],[noneMatch],[anyMatch],[~],[>>],[<<],[>>>],[|],[&],[^],[+],[-],[/],[*],[%],[&&],[||],[>=],[<=],[>],[<],[==],[!=],[instanceof],[class],[methodResultID],[this]"
tokensGeneralGrammar <- "null\\,true\\,false\\,,\\,.\\,:\\,;\\,?\\,jdVar\\,stream\\,Arrays\\,allMatch\\,noneMatch\\,anyMatch\\,~\\,>>\\,<<\\,>>>\\,|\\,&\\,^\\,+\\,-\\,/\\,*\\,%\\,&&\\,||\\,>=\\,<=\\,>\\,<\\,==\\,!=\\,instanceof\\,class\\,methodResultID\\,this"

acceptedJavadocTagTypes <- c("PARAM", "RETURN", "THROWS")

accessTypes <- c("public", "private", "protected", "none")

javaPrimitiveTypes <- c("byte", "char", "short", "int", "long", "float", "double", "boolean")

generics <- c("E", "K", "N", "T", "V", "I", "A", "B", "S", "U", "C", "R", "M", "F", "G", "D", "ELEMENT", "FUNC", "T1", "T2")

literalTypesInJavaParser <- c(
  "class com.github.javaparser.ast.expr.IntegerLiteralExpr",
  "class com.github.javaparser.ast.expr.LongLiteralExpr",
  "class com.github.javaparser.ast.expr.DoubleLiteralExpr", 
  "class com.github.javaparser.ast.expr.StringLiteralExpr",
  "class com.github.javaparser.ast.expr.CharLiteralExpr",
  "class com.github.javaparser.ast.expr.BooleanLiteralExpr",
  "class com.github.javaparser.ast.expr.NullLiteralExpr"
)
translationsOfLiteralTypesInJavaParser <- c(
  "int",
  "long",
  "double",
  "string",
  "char",
  "boolean",
  "null"
)
literalTypesInJavaParser <- setNames(as.list(translationsOfLiteralTypesInJavaParser), literalTypesInJavaParser)


randoopTypes <- c("I", "J", "D", "B")
javaparserTypes <- c("int", "long", "double", "byte")
randoopToJavaparserTypes <- setNames(as.list(javaparserTypes), randoopTypes)

elementSeparator <- "\\,"
subElementSeparator <- "\\;"
collapseTokenElements <- function(elementsAsListOfVectors){
  paste(unique(do.call("paste", c(elementsAsListOfVectors, sep = subElementSeparator))), collapse = elementSeparator)
}


put_space <- function(sentence){
  sentence <- gsub(x = sentence, pattern = "==", replacement = " == ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "!=", replacement = " != ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "<=", replacement = " <= ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = ">=", replacement = " >= ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "<", replacement = " < ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = ">", replacement = " > ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "(", replacement = " ( ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = ")", replacement = " ) ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "[", replacement = " [ ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "]", replacement = " ] ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "&&", replacement = " && ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "||", replacement = " || ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "+", replacement = " + ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = ",", replacement = " , ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = ";", replacement = " ; ", fixed = TRUE)
}
put_space_for_numeric_calc <- function(sentence){
  sentence <- gsub(x = sentence, pattern = "==", replacement = " == ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "=", replacement = " = ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "!=", replacement = " != ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "<=", replacement = " <= ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = ">=", replacement = " >= ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "<", replacement = " < ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = ">", replacement = " > ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "(", replacement = " ( ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = ")", replacement = " ) ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "{", replacement = " { ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "}", replacement = " } ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "[", replacement = " [ ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "]", replacement = " ] ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "&&", replacement = " && ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "||", replacement = " || ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "+", replacement = " + ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = ",", replacement = " , ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = ";", replacement = " ; ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = ":", replacement = " : ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "!", replacement = " ! ", fixed = TRUE) #check later: if it is not "!="
  sentence <- gsub(x = sentence, pattern = "\t", replacement = " \t ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "\n", replacement = " \n ", fixed = TRUE)
  # sentence <- gsub(x = sentence, pattern = "%", replacement = " % ", fixed = TRUE)
}
put_space_for_string_calc <- function(sentence){
  sentence <- gsub(x = sentence, pattern = "==", replacement = " == ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "!=", replacement = " != ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "<=", replacement = " <= ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = ">=", replacement = " >= ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "<", replacement = " < ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = ">", replacement = " > ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "(", replacement = " ( ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = ")", replacement = " ) ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "[", replacement = " [ ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "]", replacement = " ] ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "&&", replacement = " && ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "||", replacement = " || ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = "+", replacement = " + ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = ",", replacement = " , ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = ";", replacement = " ; ", fixed = TRUE)
  sentence <- gsub(x = sentence, pattern = ".", replacement = " . ", fixed = TRUE)
}

escape_square_brackets <- function(statement){
  escapedStatement <- character()
  
  escapedStatement <- gsub(x = statement, pattern = "[", replacement = "\\[", fixed = T)
  escapedStatement <- gsub(x = escapedStatement, pattern = "]", replacement = "\\]", fixed = T)
  
  return(escapedStatement)
}
get_after_last_dot_with_sub <- function(text){
  return(sub(".*\\.", "", text))
}

get_main_type <- function(type){
  finalMainType <- character()
  
  if(length(type) > 0){
    if(type != ""){
      processedType <- type
      
      # if the type includes a collection (such as "org.jgrapht.Graph<V, E>", "java.util.Collection<java.util.Iterator<? extends E>>")
      # extract the class name (org.jgrapht.Graph<V, E> will be converted to org.jgrapht.Graph)
      if(grepl(x = type, pattern = "<") & grepl(x = type, pattern = ">")){
        firstIndexOfExcludedPart <- min(unlist(gregexpr("<", type)))
        lastIndexfExcludedPart <- max(unlist(gregexpr(">", type)))
        processedType <- substr(type, 1, firstIndexOfExcludedPart - 1)
      }
      
      # remove the "[]"
      if(grepl(x = processedType, pattern = "\\[\\]")){
        processedType <- gsub(x = type, pattern = "\\[\\]", replacement = "") 
      }
      
      # remove ...
      if(endsWith(processedType, "...")){
        processedType <- substr(processedType, 1, nchar(processedType) - 3)
      }
      
      finalMainType <- processedType
    }   
  }
  
  return(finalMainType)
}

get_type <- function(projectId, sourceFileId, typeAsList){
  # There are three data pieces collected via javaparser for types (method argument types or could be method return types)
  # typeRaw: raw javaparser output without the application of symbol solver
  # typeResolved: output of JavaSymbolSolver, might be empty if the type is not solved
  # typeResolvedDescribed: also another output of JavaSymbolSolver, might be empty if the type is not solved
  
  # Sample type objects with its three variables:
  # typeRaw: "V"
  # typeResolved: "ResolvedArrayType{TypeVariable {JPTypeParameter(V, bounds=[])}}"
  # typeResolvedDescribed: "V..."
  # typeRaw: "double[]"
  # typeResolved: "ResolvedArrayType{PrimitiveTypeUsage{name='double'}}"
  # typeResolvedDescribed: "double[]"
  # typeRaw: "HashMap<Integer, HashSet<V>>"
  # typeResolved: "ReferenceType{java.util.HashMap, typeParametersMap=TypeParametersMap{nameToValue={java.util.HashMap.V=ReferenceType{java.util.HashSet, typeParametersMap=TypeParametersMap{nameToValue={java.util.HashSet.E=TypeVariable {JPTypeParameter(V, bounds=[])}}}}, java.util.HashMap.K=ReferenceType{java.lang.Integer, typeParametersMap=TypeParametersMap{nameToValue={}}}}}}"
  # typeResolvedDescribed: "java.util.HashMap<java.lang.Integer, java.util.HashSet<V>>"
  
  
  # Function returns
  finalClassPathOfType <- character() #only the class path of main type e.g., org.jgrapth.Graph
  finalType <- character() #full type e.g., org.jgrapth.Graph<E, V>
  
  # Function does
  # 1st checks the typeResolvedDescribed 
  # if it is not empty continue the process with typeResolvedDescribed  
  # if it is empty take the typeRaw 
  typeDetected <- typeAsList$typeResolvedDescribed
  if(typeDetected == ""){
    typeDetected <- typeAsList$typeRaw
  }
  
  # 2nd get the main type
  # e.g., double from double[], V from V...
  mainType <- get_main_type(typeDetected)
  
  if(mainType %in% javaPrimitiveTypes){
    finalClassPathOfType <- mainType
    finalType <- typeDetected
  }
  
  # 3rd check if is generic
  if(mainType %in% generics){
    if(!typeAsList$typeResolved == ""){
      boundClass <- get_bound_of_generic_type(projectId, sourceFileId, typeAsList$typeResolved)
      if(length(boundClass) > 0){
        # get the source file includes boundClass
        # check project source
        finalClassPathOfType <- get_sourceFileName_of_class(projectId, sourceFileId, boundClass)
        
        # if we cannot find the class name in project's own source
        # check jdk source
        if(length(finalClassPathOfType) == 0){
          finalClassPathOfType <- get_sourceFileName_of_class_JDK(boundClass)
        }
        
        if(length(finalClassPathOfType) == 0){
          finalClassPathOfType <- boundClass
        }
      } else {
        finalClassPathOfType <- "java.lang.Object"
      }
      # finalType <- gsub(x = typeDetected, pattern = mainType, replacement = finalClassPathOfType)
    }
    else {
      finalClassPathOfType <- "java.lang.Object"
    }
    finalType <- gsub(x = typeDetected, pattern = mainType, replacement = finalClassPathOfType)
  }
  
  # 4th be sure we get the full class path for non generic and primitives
  if(!(mainType %in% generics) & !(mainType %in% javaPrimitiveTypes)){
    # 5th check if the type is void
    if(mainType == "void"){
      finalClassPathOfType <- finalType <- "void"
    } else {
      # check project source
      finalClassPathOfType <- get_sourceFileName_of_class(projectId, sourceFileId, mainType)
      
      # if we cannot find the class name in project's own source
      # check jdk source
      if(length(finalClassPathOfType) == 0){
        finalClassPathOfType <- get_sourceFileName_of_class_JDK(mainType)
      }
      
      if(length(finalClassPathOfType) == 0){
        finalClassPathOfType <- mainType
      }
      
      finalType <- gsub(x = typeDetected, pattern = mainType, replacement = finalClassPathOfType) 
    }
  }
  
  finalReturnType <- list("finalClassPathOfType" = finalClassPathOfType, "finalType" = finalType)
  
  return(finalReturnType)
}

get_bound_of_generic_type <- function(projectId, sourceFileId, typeResolved){
  # a sample
  # "TypeVariable {JPTypeParameter(G, bounds=[UndirectedGraph<V, E>, WeightedGraph<V, E>])}"
  
  boundClass <- character()
  
  if(grepl(x = typeResolved, pattern = "JPTypeParameter")){
    boundsToken <- gsub(x = typeResolved, pattern = "(.*bounds=\\[)(.*)(\\].*)", replacement = "\\2")
    if(!boundsToken == ""){
      boundClass <- str_extract(boundsToken, "[^<]+")
      
      # boundClass <- gsub(x = boundsToken, pattern = "(.*)(<.*>)", "\\1")
      # if(grepl(x = boundClass, pattern = "<") & grepl(x = boundClass, pattern = ">")){
      #   boundClass <- gsub(x = boundClass, pattern = "(.*)(<.*>)", "\\1")
      # }
    } 
  }
  
  return(boundClass)
}

get_sourceFileName_of_class <- function(projectId, sourceFileId, className){
  matchedSourceFileName <- character()
  
  classList <- read.table(classListDirs[projectId])[, 1]
  projectName <- projectNames[[projectId]]
  javaParserDataJson <- allProjectsJavaParserDataList[[projectName]]
  
  # 0. check if the given className is full path or includes only class name 
  # full path: "org.apache.commons.math3.optim.ConvergenceChecker"
  # only class name: "ConvergenceChecker"
  # This control is done with the check of dots in the given className
  if(grepl(x = className, pattern = ".", fixed = T)){
    matchedSourceFileName <- className
  }
  
  # 1. try matching with imported classes
  if(length(matchedSourceFileName) == 0){
    sourceFileData <- javaParserDataJson[[sourceFileId]]
    imports <- sapply(sourceFileData$imports, "[[", "import")
    imports <- gsub(x = imports, pattern = ";\n", "")
    imports <- gsub(x = imports, pattern = "import ", "")
    importedClassNames <- gsub(".*\\.", "", imports)
    
    matchedSourceFileName <- imports[grep(x = importedClassNames, pattern = paste("\\b", className, "\\b", sep = ""))]
  }
  
  # 2. try matching with other classes in the project
  # In the case of more than one match, take the one belongs to the same package
  if(length(matchedSourceFileName) == 0){
    allClassNames <- gsub(".*\\.", "", classList)
    matchedSourceFileName <- classList[grep(x = allClassNames, pattern = paste("\\b", className, "\\b", sep = ""))]
    
    # if more than one match, take the one in the same package
    if(length(matchedSourceFileName) > 0){
      matchedSourceFileName <- matchedSourceFileName[grepl(x = matchedSourceFileName, pattern = paste(sourceFileData$packageName, ".", sep = ""), fixed = T)]
    }
  }
  
  if(length(matchedSourceFileName) > 1){
    cat("\n ERROR: MORE THAN ONE MATCH (detecting the correct class path of an unresolved type by javaparser)\n")
  }
  
  return(matchedSourceFileName)
}

get_sourceFileName_of_class_JDK <- function(className){
  matchedSourceFileName <- character()
  
  sourceFileNamesJDK <- c(sapply(javaParserDataJsonJDK, `[[`, "sourceFileName"))
  sourceFileId <- which(endsWith(sourceFileNamesJDK, paste(".", className, sep = "")))
  
  if(length(sourceFileId) > 1){
    log_print(paste("WARNING: multiple match for a source file name in function get_sourceFileName_of_class_JDK"))
    log_print(paste("className:", className, "matched files:", sourceFileNamesJDK[sourceFileId]))
  }
  if(length(sourceFileId) == 1){
    detectedSourceFileData <- javaParserDataJsonJDK[[sourceFileId]]
    detectedSourceFileName <- detectedSourceFileData$sourceFileName
    matchedSourceFileName <- substr(detectedSourceFileName, gregexpr(pattern = detectedSourceFileData$packageName, detectedSourceFileName), nchar(detectedSourceFileName))
  }
  
  return(matchedSourceFileName)
}

get_method_return_type_as_list <- function(methodData){
  type <- list(typeRaw = methodData$type,
               typeResolved = methodData$returnTypeResolved,
               typeResolvedDescribed = methodData$returnTypeDescribed)
  
  return(type)
}

get_method_parameter_type_as_list <- function(parameter){
  type <- list(typeRaw = parameter$parameterType,
               typeResolved = parameter$parameterTypeResolved,
               typeResolvedDescribed = parameter$parameterTypeResolvedDescribed)
  
  return(type)
  
}

get_field_return_type_as_list <- function(fieldData){
  type <- list(typeRaw = fieldData$fieldType,
               typeResolved = fieldData$fieldTypeResolved,
               typeResolvedDescribed = fieldData$fieldTypeResolvedDescribed)
  
  return(type)
}


convert_javaparser_generated_javadocTagType_to_javadoc_format <- function(tagType){
  switch(tagType, PARAM = {"@param"}, RETURN = {"@return"}, THROWS = {"@throws"}, EXCEPTION = {"@exception"}, {tagType})
}

convert_javaparser_generated_javadocTagType_to_oracle_dataset_format <- function(tagType){
  switch(tagType, PARAM = {"PRE"}, RETURN = {"NORMAL_POST"}, THROWS = {"EXCEPT_POST"}, EXCEPTION = {"EXCEPT_POST"}, {tagType})
}



# TODO: will be removed
get_class_names_from_resolved_parameter_type <- function(parameterTypeResolved){
  finalClassList <- list()
  
  if(length(parameterTypeResolved) > 0){
    if(parameterTypeResolved != ""){
      processedInput <- parameterTypeResolved
      
      # if the type includes a collection (such as "org.jgrapht.Graph<V, E>", "java.util.Collection<java.util.Iterator<? extends E>>")
      # extract the class name (org.jgrapht.Graph<V, E> will be converted to org.jgrapht.Graph)
      if(grepl(x = parameterTypeResolved, pattern = "<") & grepl(x = parameterTypeResolved, pattern = ">")){
        firstIndexOfExcludedPart <- min(unlist(gregexpr("<", parameterTypeResolved)))
        lastIndexfExcludedPart <- max(unlist(gregexpr(">", parameterTypeResolved)))
        processedInput <- substr(parameterTypeResolved, 1, firstIndexOfExcludedPart - 1)
      }
      
      # split into tokens and remove the "[", "]"
      processedInput <- put_space(processedInput)
      itemsOfProcessedInput <- unlist(strsplit(processedInput, " "))
      itemsOfProcessedInput <- itemsOfProcessedInput[!itemsOfProcessedInput %in% c("",  "[", "]")]
      
      # if ends with "..." remove "..."
      # if(endsWith(itemsOfProcessedInput, "...")){
      #   itemsOfProcessedInput <- substr(itemsOfProcessedInput, 1, nchar(itemsOfProcessedInput) - 3)
      # }
      itemsOfProcessedInput <- itemsOfProcessedInput[!itemsOfProcessedInput %in% c("...")]
      
      # if one of generic types
      itemsOfProcessedInput[itemsOfProcessedInput %in% c("E", "K", "N", "T", "V", "I")] <- "java.lang.Object"
      
      finalClassList <- unique(itemsOfProcessedInput)
    }   
  }
  
  return(finalClassList)
}
# TODO: will be removed
get_class_names_from_resolved_parameter_type_2 <- function(parameterTypeResolved){
  finalClassList <- list()
  
  if(length(parameterTypeResolved) > 0){
    if(parameterTypeResolved != ""){
      processedInput <- parameterTypeResolved
      
      # if ends with "..." change with "[]"
      if(endsWith(processedInput, "...")){
        processedInput <- substr(processedInput, 1, nchar(processedInput) - 3)
        processedInput <- paste(processedInput, "[]", sep = "")
      }
      
      # if the type includes a collection (such as "org.jgrapht.Graph<V, E>", "java.util.Collection<java.util.Iterator<? extends E>>")
      # extract the class name (org.jgrapht.Graph<V, E> will be converted to org.jgrapht.Graph)
      if(grepl(x = processedInput, pattern = "<") & grepl(x = processedInput, pattern = ">")){
        firstIndexOfExcludedPart <- min(unlist(gregexpr("<", processedInput)))
        lastIndexfExcludedPart <- max(unlist(gregexpr(">", processedInput)))
        extractionPart <- substr(processedInput, firstIndexOfExcludedPart, lastIndexfExcludedPart)
        processedInput <- gsub(x = processedInput, pattern = extractionPart, replacement = "", fixed = T)
        # processedInput <- paste(processedInput, "[]", sep = "")
      }
      
      # # split into tokens
      processedInput <- put_space(processedInput)
      itemsOfProcessedInput <- unlist(strsplit(processedInput, " "))
      
      # if one of generic types
      itemsOfProcessedInput[itemsOfProcessedInput %in% c("E", "K", "N", "T", "V", "I")] <- "java.lang.Object"
      
      
      finalClassList <- paste(itemsOfProcessedInput, collapse = "")
    }   
  }
  
  return(finalClassList)
}

