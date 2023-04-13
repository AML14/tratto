# test oracle-dataset: correctness of collected tokens in terms of access type, return type (void), being static

# test column tokensProjectClassesNonPrivateStaticNonVoidMethods ####
# initialize columnData and numberOfTotalDataPoints variables which are used in below test cases
columnData <- allProjectsOracleDatasetCleaned$tokensProjectClassesNonPrivateStaticNonVoidMethods
numberOfTotalDataPoints <- nrow(allProjectsOracleDatasetCleaned)

test_that(desc = "test_column_tokensProjectClassesNonPrivateStaticNonVoidMethods_does_not_include_private_methods", code = {
  columnsIncludesPrivateKeyword <- columnData[str_detect(string = columnData, pattern = "private")]
  expect_length(columnsIncludesPrivateKeyword, 0)
})

test_that(desc = "test_column_tokensProjectClassesNonPrivateStaticNonVoidMethods_include_at_least_one_public_method", code = {
  columnsIncludesPublicKeyword <- columnData[str_detect(string = columnData, pattern = "public")]
  expect_gt(length(columnsIncludesPublicKeyword), 0) # greater than 0
})

test_that(desc = "test_column_tokensProjectClassesNonPrivateStaticNonVoidMethods_all_rows_include_static_method", code = {
  columnsIncludesPublicKeyword <- columnData[str_detect(string = columnData, pattern = "static")]
  expect_equal(numberOfTotalDataPoints, length(columnsIncludesPublicKeyword)) 
})

test_that(desc = "test_column_tokensProjectClassesNonPrivateStaticNonVoidMethods_does_not_include_void_methods", code = {
  columnsIncludesVoidKeyword <- columnData[grep(x = columnData, pattern = "void", ignore.case = T)]
  expect_length(columnsIncludesVoidKeyword, 0)
})
####################################################################

# test column tokensProjectClassesNonPrivateStaticAttributes ####
columnData <- allProjectsOracleDatasetCleaned$tokensProjectClassesNonPrivateStaticAttributes
numberOfTotalDataPoints <- nrow(allProjectsOracleDatasetCleaned)

test_that(desc = "test_column_tokensProjectClassesNonPrivateStaticAttributes_does_not_include_private_attributes", code = {
  columnsIncludesPrivateKeyword <- columnData[str_detect(string = columnData, pattern = "private")]
  expect_length(columnsIncludesPrivateKeyword, 0)
})

test_that(desc = "test_column_tokensProjectClassesNonPrivateStaticAttributes_include_at_least_one_public_attributes", code = {
  columnsIncludesPublicKeyword <- columnData[str_detect(string = columnData, pattern = "public")]
  expect_gt(length(columnsIncludesPublicKeyword), 0) # greater than 0
})

test_that(desc = "test_column_tokensProjectClassesNonPrivateStaticAttributes_all_rows_include_static_attributes", code = {
  columnsIncludesStaticKeyword <- grep(x = columnData, pattern = "static", ignore.case = T)
  expect_equal(numberOfTotalDataPoints, length(columnsIncludesStaticKeyword)) 
})
################################################################

# test column tokensMethodVariablesNonPrivateNonStaticNonVoidMethods ####
columnData <- allProjectsOracleDatasetCleaned$tokensMethodVariablesNonPrivateNonStaticNonVoidMethods
numberOfTotalDataPoints <- nrow(allProjectsOracleDatasetCleaned)

test_that(desc = "test_column_tokensMethodVariablesNonPrivateNonStaticNonVoidMethods_does_not_include_private_methods", code = {
  columnsIncludesPrivateKeyword <- columnData[str_detect(string = columnData, pattern = "private")]
  expect_length(columnsIncludesPrivateKeyword, 0)
})

test_that(desc = "test_column_tokensMethodVariablesNonPrivateNonStaticNonVoidMethods_include_at_least_one_public_methods", code = {
  columnsIncludesPublicKeyword <- columnData[str_detect(string = columnData, pattern = "public")]
  expect_gt(length(columnsIncludesPublicKeyword), 0) # greater than 0
})

test_that(desc = "test_column_tokensMethodVariablesNonPrivateNonStaticNonVoidMethods_does_not_include_static_methods", code = {
  columnsIncludesStaticKeyword <- columnData[str_detect(string = columnData, pattern = "static")]
  expect_length(columnsIncludesStaticKeyword, 0)
})

test_that(desc = "test_column_tokensMethodVariablesNonPrivateNonStaticNonVoidMethods_does_not_include_void_methods", code = {
  columnsIncludesVoidKeyword <- columnData[grep(x = columnData, pattern = "void", ignore.case = T)]
  expect_length(columnsIncludesVoidKeyword, 0)
})
########################################################################

# test column tokensMethodVariablesNonPrivateNonStaticAttributes ####
columnData <- allProjectsOracleDatasetCleaned$tokensMethodVariablesNonPrivateNonStaticAttributes
numberOfTotalDataPoints <- nrow(allProjectsOracleDatasetCleaned)

test_that(desc = "test_column_tokensMethodVariablesNonPrivateNonStaticAttributes_does_not_include_private_attributes", code = {
  columnsIncludesPrivateKeyword <- columnData[str_detect(string = columnData, pattern = "private")]
  expect_length(columnsIncludesPrivateKeyword, 0)
})

test_that(desc = "test_column_tokensMethodVariablesNonPrivateNonStaticAttributes_include_at_least_one_public_attributes", code = {
  columnsIncludesPublicKeyword <- columnData[str_detect(string = columnData, pattern = "public")]
  expect_gt(length(columnsIncludesPublicKeyword), 0) # greater than 0
})

test_that(desc = "test_column_tokensMethodVariablesNonPrivateNonStaticAttributes_does_not_include_static_attributes", code = {
  columnsIncludesStaticKeyword <- columnData[str_detect(columnData, "static")]
  expect_equal(length(columnsIncludesStaticKeyword), 0) 
})
#####################################################################

# test column tokensOracleVariablesNonPrivateNonStaticNonVoidMethods ####
columnData <- allProjectsOracleDatasetCleaned$tokensOracleVariablesNonPrivateNonStaticNonVoidMethods
numberOfTotalDataPoints <- nrow(allProjectsOracleDatasetCleaned)

test_that(desc = "test_column_tokensOracleVariablesNonPrivateNonStaticNonVoidMethods_does_not_include_private_methods", code = {
  columnsIncludesPrivateKeyword <- columnData[str_detect(string = columnData, pattern = "private")]
  expect_length(columnsIncludesPrivateKeyword, 0)
})

test_that(desc = "test_column_tokensOracleVariablesNonPrivateNonStaticNonVoidMethods_include_at_least_one_public_methods", code = {
  columnsIncludesPublicKeyword <- columnData[str_detect(string = columnData, pattern = "public")]
  expect_gt(length(columnsIncludesPublicKeyword), 0) # greater than 0
})

test_that(desc = "test_column_tokensOracleVariablesNonPrivateNonStaticNonVoidMethods_does_not_include_static_methods", code = {
  columnsIncludesStaticKeyword <- columnData[str_detect(string = columnData, pattern = "static")]
  expect_length(columnsIncludesStaticKeyword, 0)
})

test_that(desc = "test_column_tokensOracleVariablesNonPrivateNonStaticNonVoidMethods_does_not_include_void_methods", code = {
  columnsIncludesVoidKeyword <- columnData[grep(x = columnData, pattern = "void", ignore.case = T)]
  expect_length(columnsIncludesVoidKeyword, 0)
})
########################################################################

# test column tokensOracleVariablesNonPrivateNonStaticAttributes ####
columnData <- allProjectsOracleDatasetCleaned$tokensOracleVariablesNonPrivateNonStaticAttributes
numberOfTotalDataPoints <- nrow(allProjectsOracleDatasetCleaned)

test_that(desc = "test_column_tokensOracleVariablesNonPrivateNonStaticAttributes_does_not_include_private_attributes", code = {
  columnsIncludesPrivateKeyword <- columnData[str_detect(string = columnData, pattern = "private")]
  expect_length(columnsIncludesPrivateKeyword, 0)
})

test_that(desc = "test_column_tokensOracleVariablesNonPrivateNonStaticAttributes_does_not_include_static_attributes", code = {
  columnsIncludesStaticKeyword <- columnData[str_detect(string = columnData, pattern = "static")]
  expect_length(columnsIncludesStaticKeyword, 0)
})
#####################################################################			



# test oracle-dataset: correctness of collected tokens in terms of javadoc tag type ####
test_that(desc = "test_dataset_includes_only_accepted_javadoc_tag_types", code = {
  oracleTypesDetectedInDataset <- unique(allProjectsOracleDatasetCleaned$oracleType)
  oracleTypesAllowedInDataset <- c("PRE", "NORMAL_POST", "EXCEPT_POST")
  
  expect_equal(oracleTypesDetectedInDataset, oracleTypesAllowedInDataset)
})
#######################################################################################

# test oracle-dataset: consistency of oracle tags types among oracleType and javadocTag columns ####
test_that(desc = "test_consistency_of_oracle_tags_types_among_oracleType_and_javadocTag_columns", code = {
  
  oracleTypeCol <- allProjectsOracleDatasetCleaned$oracleType
  javadocTagCol <- allProjectsOracleDatasetCleaned$javadocTag
  
  conditionAppliedDataPoints <- 
    (oracleTypeCol == "PRE" & grepl(x = javadocTagCol, pattern = "@param", fixed = T)) |
    (oracleTypeCol == "NORMAL_POST" & grepl(x = javadocTagCol, pattern = "@return", fixed = T)) |
    (oracleTypeCol == "EXCEPT_POST" & grepl(x = javadocTagCol, pattern = "@throws", fixed = T)) |
    (oracleTypeCol == "EXCEPT_POST" & grepl(x = javadocTagCol, pattern = "@exception", fixed = T))
    
  # dataPointsReturnFalseToAppliedCondition <- allProjectsOracleDatasetCleaned[!conditions, c("oracle", "oracleType", "projectName", "javadocTag")]
   
  expect_true(all(conditionAppliedDataPoints)) 
})
###################################################################################################



# test oracle-dataset: correctness of column names ####
test_that(desc = "test_dataset_includes_correct_column_names_in_correct_order", code = {
  columnNamesInTheGeneratedDataset <- colnames(allProjectsOracleDatasetCleaned)
  
  groundTruthColumnNames <- c("id",
                              "oracle", "oracleType", "projectName", "packageName", "className", "javadocTag", 
                              "methodJavadoc", "methodSourceCode", "classJavadoc", "classSourceCode", 
                              "tokensGeneralGrammar", "tokensGeneralValuesGlobalDictionary", 
                              "tokensProjectClasses", 
                              "tokensProjectClassesNonPrivateStaticNonVoidMethods",
                              "tokensProjectClassesNonPrivateStaticAttributes",
                              "tokensMethodJavadocValues",
                              "tokensMethodArguments",
                              "tokensMethodVariablesNonPrivateNonStaticNonVoidMethods",
                              "tokensMethodVariablesNonPrivateNonStaticAttributes", 
                              "tokensOracleVariablesNonPrivateNonStaticNonVoidMethods",
                              "tokensOracleVariablesNonPrivateNonStaticAttributes")
  
  expect_equal(columnNamesInTheGeneratedDataset, groundTruthColumnNames)
})
######################################################



# test oracle-dataset: correctness of format

# test separator between elements & sub-elements ####
# tokensProjectClasses	
# tokensProjectClassesNonPrivateStaticNonVoidMethods	
# tokensProjectClassesNonPrivateStaticAttributes	
# tokensMethodJavadocValues	
# tokensMethodArguments	
# tokensMethodVariablesNonPrivateNonStaticNonVoidMethods	
# tokensMethodVariablesNonPrivateNonStaticAttrributes	
# tokensOracleVariablesNonPrivateNonStaticNonVoidMethods	
# tokensOracleVariablesNonPrivateNonStaticAttributes

# test_that(desc = "test_square_brackets_and_separator_in_tokensProjectClasses_column", code = {
#   # test for each datapoint
#   for(d in 1:nrow(allFinalDataFrame)){
#     tokensProjectClassesOfDataPoint <- allFinalDataFrame[d, "tokensProjectClasses"]
#     
#     dataSplittedByComma <- strsplit(x = tokensProjectClassesOfDataPoint, split = ",")
#     dataSplittedByBracketsAndComma <- strsplit(x = tokensProjectClassesOfDataPoint, split = "],[", fixed = T)
#     
#     expect_equal(length(dataSplittedByComma), length(dataSplittedByBracketsAndComma))
#   }
# })

#####################################################












