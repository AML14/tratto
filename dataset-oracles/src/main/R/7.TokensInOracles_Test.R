
# tests for tokensOracleVariablesNonPrivateNonStaticNonVoidMethods	& tokensOracleVariablesNonPrivateNonStaticAttributes columns

# oracles for test
# oracle1 <- "((o1==null)==false) && (o1.equals(o2)) ? methodResultID == true : true;"
# oracle2 <- "this.getCoefficients().length==0;"
# oracle3 <- "(com.google.common.net.InternetDomainName.isValid(domain)) == false"
# oracle4 <- "(((file==null)==false) && (file.exists())) == false"

# oracle5 <- "true ? methodResultID.equals(receiverObjectID.EMPTY_COLLECTION) : true"
# commons-collections4-4.1  sf:102	m:1	j: 4


# tests for the detection of function calls in oracles ####
test_that(desc = "test_extract_function_calls_in_oracle_single_call", code = {
 
  oracle <- "(((file==null)==false) && (file.exists())) == false"
  
  expectedOutput <- list(functionName = "exists", 
                         functionCallerName = "file", 
                         functionArguments = list(""))
  
  receivedOutput <- extract_function_info_from_oracle(oracle)
  
  expect_equal(receivedOutput, expectedOutput) 
})

test_that(desc = "test_extract_function_calls_in_oracle_single_call_with_attribute", code = {
  
  oracle <- "this.getCoefficients().length==0;"
  
  expectedOutput <- list(functionName = "getCoefficients", 
                         functionCallerName = "this", 
                         functionArguments = list(""))
  
  receivedOutput <- extract_function_info_from_oracle(oracle)
  
  expect_equal(receivedOutput, expectedOutput) 
})

test_that(desc = "test_extract_function_calls_in_oracle_call_with_argument", code = {
  
  oracle <- "((o1==null)==false) && (o1.equals(o2)) ? methodResultID == true : true;"
  
  expectedOutput <- list(functionName = "equals", 
                         functionCallerName = "o1", 
                         functionArguments = list("o2"))
  
  receivedOutput <- extract_function_info_from_oracle(oracle)
  
  expect_equal(receivedOutput, expectedOutput) 
})

test_that(desc = "test_extract_function_calls_in_oracle_call_with_class_name", code = {
  
  oracle <- "(com.google.common.net.InternetDomainName.isValid(domain)) == false"
  
  expectedOutput <- list(functionName = "isValid", 
                         functionCallerName = "InternetDomainName", 
                         functionArguments = list("domain"))
  
  receivedOutput <- extract_function_info_from_oracle(oracle)
  
  expect_equal(receivedOutput, expectedOutput) 
})
##########################################################

# tests for the detection of field accesses in oracles ####
test_that(desc = "test_extract_field_calls_in_oracle", code = {
  
  oracle <- "true ? methodResultID.equals(receiverObjectID.EMPTY_COLLECTION) : true"
  
  expectedOutput <- list(fieldName = "EMPTY_COLLECTION", 
                         fieldCallerName = "receiverObjectID")
  
  receivedOutput <- extract_field_info_from_oracle(oracle)
  
  expect_equal(receivedOutput, expectedOutput)
})
###########################################################


# tests for the detection of types based on field accesses ####
# test_that(desc = "test_get_type_based_on_field_accesses_in_oracle", code = {
#   
#   oracle <- "true ? methodResultID.equals(receiverObjectID.EMPTY_COLLECTION) : true"
#   
#   methodData <- allProjectsJavaParserDataList[[1]][[102]]$typeDeclarations[[1]]$methods[[1]]
#   
#   expectedOutput <- list(get_type(projectId, sourceFileId, get_method_return_type_as_list()))
#   
#   receivedOutput <- find_oracle_field_return_type(projectId = 1, sourceFileId = 102, methodId = 1, oracle, isConstructor = FALSE)
#   
#   expect_equal(receivedOutput, expectedOutput)
# })
################################################################################

