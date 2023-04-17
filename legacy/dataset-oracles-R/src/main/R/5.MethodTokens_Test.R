
# test the correctness of collected tokens for tokensMethodArguments column ####

test_that(desc = "test_column_tokensMethodArguments_org.apache.commons.collections4.BoundedCollection.maxSize()_returns_empty", code = {
  projectId <- 1
  sourceFileId <- 20
  methodId <- 2
  methodParameters <- allProjectsJavaParserDataList[[projectId]][[sourceFileId]]$typeDeclarations[[1]]$methods[[methodId]]$parameters
  
  tokensMethodArguments <- get_tokensMethodArguments(projectId, sourceFileId, methodParameters)
  expect_equal(tokensMethodArguments, "")
})

test_that(desc = "test_column_tokensMethodArguments_org.apache.commons.collections4.collection.CompositeCollection.CompositeCollection(java.util.Collection<E>)", code = {
  projectId <- 1
  sourceFileId <- 22
  methodId <- 2
  methodParameters <- allProjectsJavaParserDataList[[projectId]][[sourceFileId]]$typeDeclarations[[1]]$constructors[[methodId]]$parameters
  methodQualifiedSignature <- allProjectsJavaParserDataList[[projectId]][[sourceFileId]]$typeDeclarations[[1]]$constructors[[methodId]]$qualifiedSignature
  
  tokensMethodArguments <- get_tokensMethodArguments(projectId, sourceFileId, methodParameters)
  expect_equal(tokensMethodArguments, "compositeCollection\\;java.util\\;Collection")
})

test_that(desc = "test_column_tokensMethodArguments_org.graphstream.ui.layout.LayoutRunner.LayoutRunner(org.graphstream.stream.Source, org.graphstream.ui.layout.Layout, boolean)", code = {
  projectId <- 3
  sourceFileId <- 22
  methodId <- 2
  methodParameters <- allProjectsJavaParserDataList[[projectId]][[sourceFileId]]$typeDeclarations[[1]]$constructors[[methodId]]$parameters
  methodQualifiedSignature <- allProjectsJavaParserDataList[[projectId]][[sourceFileId]]$typeDeclarations[[1]]$constructors[[methodId]]$qualifiedSignature
  
  tokensMethodArguments <- get_tokensMethodArguments(projectId, sourceFileId, methodParameters)
  expect_equal(tokensMethodArguments, "source\\;org.graphstream.stream\\;Source\\,layout\\;org.graphstream.ui.layout\\;Layout\\,start\\;\\;boolean")
})

test_that(desc = "test_column_tokensMethodArguments_org.jgrapht.graph.WeightedPseudograph.builder.UndirectedWeightedGraphBuilderBase<V, E, ? extends WeightedPseudograph<V, E>, ?> builder(Class<? extends E> edgeClass)", code = {
  projectId <- 5
  sourceFileId <- 30
  methodId <- 1
  methodParameters <- allProjectsJavaParserDataList[[projectId]][[sourceFileId]]$typeDeclarations[[1]]$methods[[methodId]]$parameters
  methodQualifiedSignature <- allProjectsJavaParserDataList[[projectId]][[sourceFileId]]$typeDeclarations[[1]]$methods[[methodId]]$qualifiedSignature
  methodDeclaration <- allProjectsJavaParserDataList[[projectId]][[sourceFileId]]$typeDeclarations[[1]]$methods[[methodId]]$declaration
  # declaration: "public static UndirectedWeightedGraphBuilderBase<V, E, ? extends WeightedPseudograph<V, E>, ?> builder(Class<? extends E> edgeClass)"
  
  tokensMethodArguments <- get_tokensMethodArguments(projectId, sourceFileId, methodParameters)
  expect_equal(tokensMethodArguments, "edgeClass\\;java.lang\\;Class")
})

################################################################################

# test the correctness of collected tokens for tokensProjectClassesNonPrivateStaticNonVoidMethods column ####
# test subjects are commons-collections4-4.1 and plume-lib-1.1.0 projects (project ids are 1 and 6)

create_output_for_manual_test_of_column_tokensProjectClassesNonPrivateStaticNonVoidMethods_project_common_collections <- function(){
  projectId <- 1
  
  # collected method (non-private, static, non-void) tokens for common collections project 
  collectedData <- get_tokensProjectClassesNonPrivateStaticNonVoidMethods(projectId)
  collectedMethodTokens <- unlist(strsplit(collectedData, "\\,", fixed = T))
  
  # transfer the collected tokens into a dataframe to observe and match them manually with the actual methods in project source code 
  collectedMethodTokensDataFrame <- data.frame(do.call(rbind, strsplit(collectedMethodTokens, "\\;", fixed=TRUE)))
  colnames(collectedMethodTokensDataFrame) <- c("methodName", "packageName", "className", "methodDeclaration")
  
  openxlsx2::write_xlsx(x = collectedMethodTokensDataFrame, 
                        file = paste(testDataDir, "tokensProjectClassesNonPrivateStaticNonVoidMethods_project_", projectNames[projectId], ".xlsx", sep = ""), sheetName = "tokens")
}

create_output_for_manual_test_of_column_tokensProjectClassesNonPrivateStaticNonVoidMethods_project_plume <- function(){
  projectId <- 6
  
  # collected method (non-private, static, non-void) tokens for plume project 
  collectedData <- get_tokensProjectClassesNonPrivateStaticNonVoidMethods(projectId)
  collectedMethodTokens <- unlist(strsplit(collectedData, "\\,", fixed = T))
  
  # transfer the collected tokens into a dataframe to observe and match them manually with the actual methods in project source code 
  collectedMethodTokensDataFrame <- data.frame(do.call(rbind, strsplit(collectedMethodTokens, "\\;", fixed=TRUE)))
  colnames(collectedMethodTokensDataFrame) <- c("methodName", "packageName", "className", "methodDeclaration")
  
  openxlsx2::write_xlsx(x = collectedMethodTokensDataFrame, 
                        file = paste(testDataDir, "tokensProjectClassesNonPrivateStaticNonVoidMethods_project_", projectNames[projectId], ".xlsx", sep = ""), sheetName = "tokens")
}


create_output_for_manual_test_of_column_tokensProjectClassesNonPrivateStaticNonVoidMethods_project_common_collections()
create_output_for_manual_test_of_column_tokensProjectClassesNonPrivateStaticNonVoidMethods_project_plume()
############################################################################################################


# test the correctness of collected tokens for tokensMethodVariablesNonPrivateNonStaticNonVoidMethods column ####
create_output_for_manual_test_of_column_tokensMethodVariablesNonPrivateNonStaticNonVoidMethods_org.apache.commons.collections4.BidiMap.put <- function(){
  projectId <- 1
  sourceFileId <- 1
  methodId <- 1
  isConstructor <- FALSE
  methodParameters <- allProjectsJavaParserDataList[[projectId]][[sourceFileId]]$typeDeclarations[[1]]$methods[[methodId]]$parameters
  methodQualifiedSignature <- allProjectsJavaParserDataList[[projectId]][[sourceFileId]]$typeDeclarations[[1]]$methods[[methodId]]$qualifiedSignature
  methodDeclaration <- allProjectsJavaParserDataList[[projectId]][[sourceFileId]]$typeDeclarations[[1]]$methods[[methodId]]$declaration
  # declaration: " V put(K key, V value)"
  
  collectedData <- get_tokensMethodVariablesNonPrivateNonStaticNonVoidMethods(projectId, sourceFileId, methodId, isConstructor)
  collectedMethodTokens <- unlist(strsplit(collectedData, "\\,", fixed = T))

  # transfer the collected tokens into a dataframe to observe and match them manually with the actual methods in project source code
  collectedMethodTokensDataFrame <- data.frame(do.call(rbind, strsplit(collectedMethodTokens, "\\;", fixed=TRUE)))
  colnames(collectedMethodTokensDataFrame) <- c("methodName", "packageName", "className", "methodDeclaration")

  openxlsx2::write_xlsx(x = collectedMethodTokensDataFrame,
                        file = paste(testDataDir, "tokensMethodVariablesNonPrivateNonStaticNonVoidMethods_project_org.apache.commons.collections4.BidiMap.put.xlsx", sep = ""), sheetName = "tokens")
}

create_output_for_manual_test_of_column_tokensMethodVariablesNonPrivateNonStaticNonVoidMethods_org.jgrapht.graph.WeightedPseudograph.builder_UndirectedWeightedGraphBuilderBase <- function(){
  projectId <- 5
  sourceFileId <- 30
  methodId <- 1
  isConstructor <- FALSE
  methodParameters <- allProjectsJavaParserDataList[[projectId]][[sourceFileId]]$typeDeclarations[[1]]$methods[[methodId]]$parameters
  methodQualifiedSignature <- allProjectsJavaParserDataList[[projectId]][[sourceFileId]]$typeDeclarations[[1]]$methods[[methodId]]$qualifiedSignature
  methodDeclaration <- allProjectsJavaParserDataList[[projectId]][[sourceFileId]]$typeDeclarations[[1]]$methods[[methodId]]$declaration
  # declaration: "public static UndirectedWeightedGraphBuilderBase<V, E, ? extends WeightedPseudograph<V, E>, ?> builder(Class<? extends E> edgeClass)"
  
  # collected method (non-private, static, non-void) tokens for common collections project 
  collectedData <- get_tokensMethodVariablesNonPrivateNonStaticNonVoidMethods(projectId, sourceFileId, methodId, isConstructor)
  collectedMethodTokens <- unlist(strsplit(collectedData, "\\,", fixed = T))

  # transfer the collected tokens into a dataframe to observe and match them manually with the actual methods in project source code
  collectedMethodTokensDataFrame <- data.frame(do.call(rbind, strsplit(collectedMethodTokens, "\\;", fixed=TRUE)))
  colnames(collectedMethodTokensDataFrame) <- c("methodName", "packageName", "className", "methodDeclaration")

  openxlsx2::write_xlsx(x = collectedMethodTokensDataFrame,
                        file = paste(testDataDir, "tokensMethodVariablesNonPrivateNonStaticNonVoidMethods_project_org.jgrapht.graph.WeightedPseudograph.builder_UndirectedWeightedGraphBuilderBase.xlsx", sep = ""), sheetName = "tokens")
}

create_output_for_manual_test_of_column_tokensMethodVariablesNonPrivateNonStaticNonVoidMethods_org.apache.commons.collections4.IteratorUtils.toArray <- function(){
  projectId <- 1 
  sourceFileId <- 4
  methodId <- 51
  isConstructor <- FALSE
  methodParameters <- allProjectsJavaParserDataList[[projectId]][[sourceFileId]]$typeDeclarations[[1]]$methods[[methodId]]$parameters
  methodQualifiedSignature <- allProjectsJavaParserDataList[[projectId]][[sourceFileId]]$typeDeclarations[[1]]$methods[[methodId]]$qualifiedSignature
  methodDeclaration <- allProjectsJavaParserDataList[[projectId]][[sourceFileId]]$typeDeclarations[[1]]$methods[[methodId]]$declaration
  # public static Object[] toArray(final Iterator<?> iterator)
  
  # collected method (non-private, static, non-void) tokens for common collections project 
  collectedData <- get_tokensMethodVariablesNonPrivateNonStaticNonVoidMethods(projectId, sourceFileId, methodId, isConstructor)
  collectedMethodTokens <- unlist(strsplit(collectedData, "\\,", fixed = T))
  
  # transfer the collected tokens into a dataframe to observe and match them manually with the actual methods in project source code
  collectedMethodTokensDataFrame <- data.frame(do.call(rbind, strsplit(collectedMethodTokens, "\\;", fixed=TRUE)))
  colnames(collectedMethodTokensDataFrame) <- c("methodName", "packageName", "className", "methodDeclaration")
  
  openxlsx2::write_xlsx(x = collectedMethodTokensDataFrame,
                        file = paste(testDataDir, "tokensMethodVariablesNonPrivateNonStaticNonVoidMethods_org.apache.commons.collections4.IteratorUtils.toArray.xlsx", sep = ""), sheetName = "tokens")
}




create_output_for_manual_test_of_column_tokensMethodVariablesNonPrivateNonStaticNonVoidMethods_org.apache.commons.collections4.BidiMap.put()
create_output_for_manual_test_of_column_tokensMethodVariablesNonPrivateNonStaticNonVoidMethods_org.jgrapht.graph.WeightedPseudograph.builder_UndirectedWeightedGraphBuilderBase()
################################################################################################################


collectedData <- allProjectsOracleDatasetCleaned[22461, "tokensOracleVariablesNonPrivateNonStaticNonVoidMethods"]
collectedMethodTokens <- unlist(strsplit(collectedData, "\\,", fixed = T))
collectedMethodTokensDataFrame <- data.frame(do.call(rbind, strsplit(collectedMethodTokens, "\\;", fixed=TRUE)))
colnames(collectedMethodTokensDataFrame) <- c("methodName", "packageName", "className", "methodDeclaration")


