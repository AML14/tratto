
# functions used to generate tokensMethodJavadocValues column ####

get_tokensMethodJavadocValues <- function(javadocText){
  # input: a javadoc (javadocText)
  # output: a vector includes the literals in the given javadoc
  # output format: "literalValue1\;literalType1\,literalValue2\;literalType2\, ..."

  # an example output (in the final oracle-dataset, double slashes will appear as one  (\\ becomes \) and \" will appear as "):
  # "7\\;int\\,\"+\"\\;String\\,\"&#92;u002D\"\\;String\\,\"-\"\\;String"
  
  literalsDf <- extract_literals_from_javadoc_return_dataframe(javadocText)
  
  literalValuesVector <- character()
  literalTypesVector <- character()

  if(nrow(literalsDf) > 0){
    for(i in 1:nrow(literalsDf)){
      token <- literalsDf$tokens[i]
      type <- literalsDf$type[i]
      
      # if the literal is a string
      # 1) put escape character before square brackets --CANCELLED
      # 2) put quotation marks around the string value
      
      if(type == "String"){
      #   token <- escape_square_brackets(token) --CANCELLED
        token <- paste("\"", token, "\"", sep = "")
      }
      
      # if the literal is numeric, and the token equals to 0 or 1
      # do not include them to the final tokens list since they also appear in the global dictionary
      if(!(type == "float" | type == "int" & token == "1" | token == "0" | token == "1.0" | token == "0.0")){
        
          literalValuesVector <- c(literalValuesVector, token)
          literalTypesVector <- c(literalTypesVector, type)
        
      }
    }
  }
  
  finalTokens <- collapseTokenElements(list(literalValuesVector, literalTypesVector))
  
  return(finalTokens)
}

extract_literals_from_javadoc_return_dataframe <- function(javadocText){
  # input: a javadoc (javadocText)
  # output: a dataframe includes the literals in the given javadoc (finalList)
  # output format: there are two columns in the output
  # first column represents the literal tokens 
  # second column represents the type of the literal
  # a sample output:
  # tokens type
  # 1      int
  # 10.1   float
  # A      String
  
  # this function extracts literals in the given javadoc
  # Flow of code:
  # 1. Take a javadoc text
  # 2. Extract string literals 
  # 3. Clean the string literals in the javadoc: before processing with the extraction of numeric literals, 
  # clean the strings, because we don't want to process them further. 
  # For example, a token such as "20" is a string literal, we don't want to count token 20 again under the integer literals category.
  # 4. Extract int and float type of literals
  # 5. Finally, add all detected literals into the finalList dataframe
  
  
  finalList <- data.frame()
  
  if(javadocText != ""){
    javadocText <- str_squish(javadocText) # trim spaces
    javadocText_woSinceTag <- gsub("@since.*", "", trimws(javadocText)) # remove since tags (e.g., @since 2.1 )
    javadocText_woVersionTokens <- gsub("version ([0-9]|[0-9].[0-9]|[0-9].[0-9].[0-9])*", "", javadocText_woSinceTag, ignore.case = T) # remove since tags (e.g., @since 2.1 )
    javadocText_cleaned <- javadocText_woVersionTokens
    
    if(javadocText_cleaned != ""){
      stringValues_double_quotation <- unlist(rm_between(javadocText_cleaned, '"', '"', extract = TRUE, trim = FALSE)) # get text between the double quotation marks
      stringValues_single_quotation <- unlist(rm_between(javadocText_cleaned, '\'', '\'', extract = TRUE, trim = FALSE)) # get text between the single quotation marks
      stringValues <- c(stringValues_double_quotation, stringValues_single_quotation) #combine two vectors
      
      # this is a workaround to eliminate captured strings between wrongly detected quotation marks
      # this workaround assumes if a captured string is too long, that means it is a string between a two apostrophes of different blocks/sentences. 
      # limit is set as 20 character
      # an example case from guava, com.google.common.collect.Maps, transformValues():
      # "s acceptable for the underlying map to contain null keys, and even * null values provided that the function is capable of accepting null input. * The transformed map might contain null values, if the function sometimes * gives a null result. * * <p>The returned map is not thread-safe or serializable, even if the * underlying map is. * * <p>The function is applied lazily, invoked when needed. This is necessary * for the returned map to be a view, but it means that the function will be * applied many times for bulk operations like {@link Map#containsValue} and * {@code Map.toString()}. For this to perform well, {@code function} should * be fast. To avoid lazy evaluation when the returned map doesn"
      stringValues <- stringValues[nchar(stringValues) < 20]
      
      stringValues <- stringValues[!grepl(x = stringValues, pattern = paste(c("href", "www", "http"), collapse = "|"))] # filter out the text between the quotation marks if they point out a link
      stringValues <- stringValues[!is.na(stringValues)] #remove NAs
      
      # javadocText_woStrings <- gsub('(\").*?(\")', "", javadocText_cleaned)
      # javadocText_woStrings <- gsub('(\').*?(\')', "", javadocText_woStrings)
      
      javadocText_woStrings <- javadocText_cleaned
      for(stringValue in stringValues){
        javadocText_woStrings <- gsub(x = javadocText_woStrings, pattern = paste('"', stringValue, '"', sep = ""), replacement = "", fixed = T)
      }
      
      dataToBeUsed <- javadocText_woStrings
      
      tokens <- unlist(strsplit(put_space_for_numeric_calc(dataToBeUsed), " "))
      tokens <- tokens[!tokens %in% ""]
      length(tokens)
      
      tokens_unique <- unique(tokens)
      length(tokens_unique)
      
      freqTable <- table(tokens)
      freqTable <- as.data.frame(freqTable)
      freqTable$tokens <- as.character(freqTable$tokens)
      freqTable <- freqTable[rev(order(freqTable$Freq)), ]
      freqTable[which(tolower(freqTable$tokens) == "zero"), "tokens"] <- 0
      freqTable[which(tolower(freqTable$tokens) == "one"), "tokens"] <- 1
      
      freqTable[grepl(x = freqTable$tokens, pattern = "^[0-9]+$|^-[0-9]+$"), "type"] <- "int"
      freqTable[grepl(x = freqTable$tokens, pattern = "^[0-9]+\\.[0-9]+$|^-[0-9]+\\.[0-9]+$"), "type"] <- "float"
      # freqTable[freqTable$tokens == "null", "type"] <- "null"
      # freqTable[freqTable$tokens == "true", "type"] <- "boolean"
      # freqTable[freqTable$tokens == "false", "type"] <- "boolean"
      # NOTE: we do not include null and boolean since they are already exist in global dictionary
      
      finalList <- freqTable[!is.na(freqTable$type), c("tokens", "type")]
      finalList <- unique(finalList)
      
      if(length(stringValues) > 0){
        freqTableStringValues <- table(stringValues)
        freqTableStringValues <- freqTableStringValues[rev(order(freqTableStringValues))]
        freqTableStringValues <- as.data.frame(freqTableStringValues)
        if(nrow(freqTableStringValues) == 1)
          freqTableStringValues <- tibble::rownames_to_column(freqTableStringValues, "tokens")
        freqTableStringValues$type = "String"
        colnames(freqTableStringValues) = c("tokens", "freq", "type")
        finalList <- rbind(finalList, freqTableStringValues[, c("tokens", "type")])
      }
      
      # paste(paste("[", finalList$tokens, ";", finalList$type, "]", sep = ""), collapse = ",")
      
    }
  }
  
  return(finalList)
}

#################################################################


# NOTE: 
# java 7, jdk 7, IEEE 854
# float 0.0, 1.0
# hex values 0x12