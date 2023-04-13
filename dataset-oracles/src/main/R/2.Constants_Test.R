# tests for tokensMethodJavadocValues column ####
test_that(desc = "test_string_extraction_between_single_quotations", code = {
  javadocText <-    "
  * Parses the specified string as a signed decimal integer value. The ASCII
  * character {@code '-'} (<code>'&#92;u002D'</code>) is recognized as the
  * minus sign.
  *
    * <p>Unlike {@link Integer#parseInt(String)}, this method returns
      * {@code null} instead of throwing an exception if parsing fails.
      * Additionally, this method only accepts ASCII digits, and returns
      * {@code null} if non-ASCII digits are present in the string.
      *
        * <p>Note that strings prefixed with ASCII {@code '+'} are rejected, even
      * under JDK 7, despite the change to {@link Integer#parseInt(String)} for
        * that version.
        *
          * @param string the string representation of an integer value
        * @return the integer value represented by {@code string}, or {@code null} if
        *     {@code string} has a length of zero or cannot be parsed as an integer
        *     value
        * @since 11.0
        "
  
  expectedDataFrame <- data.frame()
  expectedDataFrame[nrow(expectedDataFrame) + 1, "tokens"] <- "0" #zero token should be converted to 0
  expectedDataFrame[nrow(expectedDataFrame), "type"] <- "int"
  expectedDataFrame[nrow(expectedDataFrame) + 1, "tokens"] <- "7"
  expectedDataFrame[nrow(expectedDataFrame), "type"] <- "int"
  expectedDataFrame[nrow(expectedDataFrame) + 1, "tokens"] <- "+"
  expectedDataFrame[nrow(expectedDataFrame), "type"] <- "String"
  expectedDataFrame[nrow(expectedDataFrame) + 1, "tokens"] <- "&#92;u002D"
  expectedDataFrame[nrow(expectedDataFrame), "type"] <- "String"
  expectedDataFrame[nrow(expectedDataFrame) + 1, "tokens"] <- "-"
  expectedDataFrame[nrow(expectedDataFrame), "type"] <- "String"
  rownames(expectedDataFrame) <- NULL
  
  receivedDataFrame <- extract_literals_from_javadoc_return_dataframe(javadocText)
  rownames(receivedDataFrame) <- NULL #reset row names, since expect_equal also compares row names of dataframes
  
  expect_equal(receivedDataFrame, expectedDataFrame) 
  
})

test_that(desc = "test_string_extraction_between_single_quotations_from_a_javadoc_includes_apostroph", code = {
  javadocText <- allProjectsJavaParserDataList[[4]][[58]]$typeDeclarations[[1]]$methods[[46]]$javadocComment

  # content of javadocText:
  # "
  #  * Returns a view of a map where each value is transformed by a function. All
  #  * other properties of the map, such as iteration order, are left intact. For
  #  * example, the code: <pre>   {@code
  #  *
  #  *   Map<String, Integer> map = ImmutableMap.of(""a"", 4, ""b"", 9);
  #  *   Function<Integer, Double> sqrt =
  #  *       new Function<Integer, Double>() {
  #  *         public Double apply(Integer in) {
  #  *           return Math.sqrt((int) in);
  #  *         }
  #  *       };
  #  *   Map<String, Double> transformed = Maps.transformValues(map, sqrt);
  #  *   System.out.println(transformed);}</pre>
  #  *
  #  * ... prints {@code {a=2.0, b=3.0}}.
  #  *
  #  * <p>Changes in the underlying map are reflected in this view. Conversely,
  #  * this view supports removal operations, and these are reflected in the
  #  * underlying map.
  #  *
  #  * <p>It's acceptable for the underlying map to contain null keys, and even
  #  * null values provided that the function is capable of accepting null input.
  #  * The transformed map might contain null values, if the function sometimes
  #  * gives a null result.
  #  *
  #  * <p>The returned map is not thread-safe or serializable, even if the
  #  * underlying map is.
  #  *
  #  * <p>The function is applied lazily, invoked when needed. This is necessary
  #  * for the returned map to be a view, but it means that the function will be
  #  * applied many times for bulk operations like {@link Map#containsValue} and
  #  * {@code Map.toString()}. For this to perform well, {@code function} should
  #  * be fast. To avoid lazy evaluation when the returned map doesn't need to be
  #  * a view, copy the returned map into a new map of your choosing.
  #  "

  
  expectedDataFrame <- data.frame()
  expectedDataFrame[nrow(expectedDataFrame) + 1, "tokens"] <- "9" 
  expectedDataFrame[nrow(expectedDataFrame), "type"] <- "int"
  expectedDataFrame[nrow(expectedDataFrame) + 1, "tokens"] <- "4"
  expectedDataFrame[nrow(expectedDataFrame), "type"] <- "int"
  expectedDataFrame[nrow(expectedDataFrame) + 1, "tokens"] <- "3.0"
  expectedDataFrame[nrow(expectedDataFrame), "type"] <- "float"
  expectedDataFrame[nrow(expectedDataFrame) + 1, "tokens"] <- "2.0"
  expectedDataFrame[nrow(expectedDataFrame), "type"] <- "float"
  expectedDataFrame[nrow(expectedDataFrame) + 1, "tokens"] <- "b"
  expectedDataFrame[nrow(expectedDataFrame), "type"] <- "String"
  expectedDataFrame[nrow(expectedDataFrame) + 1, "tokens"] <- "a"
  expectedDataFrame[nrow(expectedDataFrame), "type"] <- "String"
  
  expectedDataFrame[order(expectedDataFrame$tokens), ]
  rownames(expectedDataFrame) <- NULL

  receivedDataFrame <- extract_literals_from_javadoc_return_dataframe(javadocText)
  receivedDataFrame[order(receivedDataFrame$tokens), ] #order data
  rownames(receivedDataFrame) <- NULL #reset row names, since expect_equal also compares row names of dataframes
  
  expect_equal(receivedDataFrame, expectedDataFrame) 
  
})

test_that(desc = "test_does_not_extract_literals_with_percent_sign", code = {
  javadocText <- allProjectsJavaParserDataList[[4]][[230]]$typeDeclarations[[1]]$methods[[2]]$javadocComment
  
  # javadoc is from com.google.common.net.UrlEscapers, urlPathSegmentEscaper()
  # content of javadocText:
  # "
  #  * Returns an {@link Escaper} instance that escapes strings so they can be
  #  * safely included in <a href=""http://goo.gl/swjbR"">URL path segments</a>. The
  #  * returned escaper escapes all non-ASCII characters, even though <a
  #  * href=""http://goo.gl/xIJWe"">many of these are accepted in modern URLs</a>.
  #  * (<a href=""http://goo.gl/WMGvZ"">If the escaper were to leave these
  #  * characters unescaped, they would be escaped by the consumer at parse time,
  #  * anyway.</a>) Additionally, the escaper escapes the slash character (""/"").
  #  * While slashes are acceptable in URL paths, they are considered by the
  #  * specification to be separators between ""path segments."" This implies that,
  #  * if you wish for your path to contain slashes, you must escape each segment
  #  * separately and then join them.
  #  *
  #  * <p>When escaping a String, the following rules apply:
  #  * <ul>
  #  * <li>The alphanumeric characters ""a"" through ""z"", ""A"" through ""Z"" and ""0""
  #  *     through ""9"" remain the same.
  #  * <li>The unreserved characters ""."", ""-"", ""~"", and ""_"" remain the same.
  #  * <li>The general delimiters ""@"" and "":"" remain the same.
  #  * <li>The subdelimiters ""!"", ""$"", ""&amp;"", ""'"", ""("", "")"", ""*"", ""+"", "","", "";"",
  #  *     and ""="" remain the same.
  #  * <li>The space character "" "" is converted into %20.
  #  * <li>All other characters are converted into one or more bytes using UTF-8
  #  *     encoding and each byte is then represented by the 3-character string
  #  *     ""%XY"", where ""XY"" is the two-digit, uppercase, hexadecimal
  #  *     representation of the byte value.
  #  * </ul>
  #  *
  #  * <p><b>Note:</b> Unlike other escapers, URL escapers produce uppercase
  #  * hexadecimal sequences. From <a href=""http://www.ietf.org/rfc/rfc3986.txt"">
  #  * RFC 3986</a>:<br>
  #  * <i>""URI producers and normalizers should use uppercase hexadecimal digits
  #  * for all percent-encodings.""</i>
  #  "
  
  receivedDataFrame <- extract_literals_from_javadoc_return_dataframe(javadocText)
  
  expect_true(object = nrow(receivedDataFrame[grepl(x = receivedDataFrame$tokens, pattern = "20"), ]) == 0, info = "20 is not on the literals list")
  
})

test_that(desc = "test_does_not_extract_literals_implies_versions", code = {
  javadocText <- allProjectsJavaParserDataList[[1]][[102]]$typeDeclarations[[1]]$methods[[14]]$javadocComment

  # javadoc is from org.apache.commons.collections4.CollectionUtils, isEqualCollection()
  # content of javadocText:
  # "
  #    * Returns {@code true} iff the given {@link Collection}s contain
  #    * exactly the same elements with exactly the same cardinalities.
  #    * <p>
  #    * That is, iff the cardinality of <i>e</i> in <i>a</i> is
  #    * equal to the cardinality of <i>e</i> in <i>b</i>,
  #    * for each element <i>e</i> in <i>a</i> or <i>b</i>.
  #    * <p>
  #    * <b>Note:</b> from version 4.1 onwards this method requires the input
  #    * collections and equator to be of compatible type (using bounded wildcards).
  #    * Providing incompatible arguments (e.g. by casting to their rawtypes)
  #    * will result in a {@code ClassCastException} thrown at runtime.
  #    *
  #    * @param <E>  the element type
  #    * @param a  the first collection, must not be null
  #    * @param b  the second collection, must not be null
  #    * @param equator  the Equator used for testing equality
  #    * @return <code>true</code> iff the collections contain the same elements with the same cardinalities.
  #    * @throws NullPointerException if the equator is null
  #    * @since 4.0
  #    "
  
  receivedDataFrame <- extract_literals_from_javadoc_return_dataframe(javadocText)
  
  # receivedDataFrame should be empty (= zero row)
  expect_equal(nrow(receivedDataFrame), 0)
  
})


# below function writes out all method javadocs in dataset & literals in those javadocs into an excel file
test_tokensMethodJavadocValues <- function(){
  
  # allJavadocs <- character()
  outputDataFrame <- data.frame()
  
  for(projectId in 1:length(projectNames)){
    jpData <- allProjectsJavaParserDataList[[projectId]]
    
    for(sourceFileId in 1:length(jpData)){
      typeDeclarationsData <- jpData[[sourceFileId]]$typeDeclarations
      if(length(typeDeclarationsData) > 0){
        methodsData <- jpData[[sourceFileId]]$typeDeclarations[[1]]$methods
        if(length(methodsData) > 0){
          for(methodId in 1:length(methodsData)){
            # allJavadocs <- c(allJavadocs, jpData[[sourceFileId]]$typeDeclarations[[1]]$methods[[methodId]]$javadocComment)
            
            literals <- get_tokensMethodJavadocValues(jpData[[sourceFileId]]$typeDeclarations[[1]]$methods[[methodId]]$javadocComment)
            
            outputDataFrame[nrow(outputDataFrame) + 1, "project"] <- projectNames[projectId]
            outputDataFrame[nrow(outputDataFrame), "class"] <- jpData[[sourceFileId]]$sourceFileName
            outputDataFrame[nrow(outputDataFrame), "method"] <- jpData[[sourceFileId]]$typeDeclarations[[1]]$methods[[methodId]]$name
            outputDataFrame[nrow(outputDataFrame), "javadoc"] <- jpData[[sourceFileId]]$typeDeclarations[[1]]$methods[[methodId]]$javadocComment
            outputDataFrame[nrow(outputDataFrame), "literals"] <- ifelse(length(literals) > 0, literals, "")
            outputDataFrame[nrow(outputDataFrame), "sourceFileId"] <- sourceFileId
            outputDataFrame[nrow(outputDataFrame), "methodId"] <- methodId
          } 
        }
        constructorsData <- jpData[[sourceFileId]]$typeDeclarations[[1]]$constructors
        if(length(constructorsData) > 0){
          for(methodId in 1:length(constructorsData)){
            # allJavadocs <- c(allJavadocs, jpData[[sourceFileId]]$typeDeclarations[[1]]$constructors[[methodId]]$javadocComment)
            
            literals <- get_tokensMethodJavadocValues(jpData[[sourceFileId]]$typeDeclarations[[1]]$constructors[[methodId]]$javadocComment)
            
            outputDataFrame[nrow(outputDataFrame) + 1, "project"] <- projectNames[projectId]
            outputDataFrame[nrow(outputDataFrame), "class"] <- jpData[[sourceFileId]]$sourceFileName
            outputDataFrame[nrow(outputDataFrame), "method"] <- jpData[[sourceFileId]]$typeDeclarations[[1]]$constructors[[methodId]]$name
            outputDataFrame[nrow(outputDataFrame), "javadoc"] <- jpData[[sourceFileId]]$typeDeclarations[[1]]$constructors[[methodId]]$javadocComment
            outputDataFrame[nrow(outputDataFrame), "literals"] <- ifelse(length(literals) > 0, literals, "")
            outputDataFrame[nrow(outputDataFrame), "sourceFileId"] <- sourceFileId
            outputDataFrame[nrow(outputDataFrame), "methodId"] <- methodId
          } 
        } 
      }
    }
  }
  
  # length(allJavadocs)
  # nrow(outputDataFrame)
  
  # write.csv(x = outputDataFrame, file = paste(testDataDir, "test_tokensMethodJavadocValues.csv"))
  # write.xlsx(x = outputDataFrame, file = paste(testDataDir, "test_tokensMethodJavadocValues.xlsx"), sheetName = "test_tokensMethodJavadocValues")
  openxlsx2::write_xlsx(x = outputDataFrame, file = paste(testDataDir, "test_tokensMethodJavadocValues.xlsx"))
}
test_tokensMethodJavadocValues()
################################################



