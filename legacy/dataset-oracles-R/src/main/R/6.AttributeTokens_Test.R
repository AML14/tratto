
# test the correctness of collected tokens for tokensProjectClassesNonPrivateStaticAttributes column ####
# test subjects are commons-collections4-4.1 and plume-lib-1.1.0 projects (project ids are 1 and 6)

create_output_for_manual_test_of_column_tokensProjectClassesNonPrivateStaticAttributes_project_common_collections <- function(){
  projectId <- 1
  
  # collected method (non-private, static, non-void) tokens for common collections project 
  collectedData <- get_tokensProjectClassesNonPrivateStaticAttributes(projectId)
  collectedAttributeTokens <- unlist(strsplit(collectedData, "\\,", fixed = T))
  
  # transfer the collected tokens into a dataframe to observe and match them manually with the actual methods in project source code 
  collectedAttributeTokensDataFrame <- data.frame(do.call(rbind, strsplit(collectedAttributeTokens, "\\;", fixed=TRUE)))
  colnames(collectedAttributeTokensDataFrame) <- c("symbolicName", "packageName", "className", "fieldDeclaration")
  
  openxlsx2::write_xlsx(x = collectedAttributeTokensDataFrame, 
                        file = paste(testDataDir, "tokensProjectClassesNonPrivateStaticAttributes_project_", projectNames[projectId], ".xlsx", sep = ""), sheetName = "tokens")
}

create_output_for_manual_test_of_column_tokensProjectClassesNonPrivateStaticAttributes_project_plume <- function(){
  projectId <- 6
  
  # collected method (non-private, static, non-void) tokens for plume project 
  collectedData <- get_tokensProjectClassesNonPrivateStaticAttributes(projectId)
  collectedAttributeTokens <- unlist(strsplit(collectedData, "\\,", fixed = T))
  
  # transfer the collected tokens into a dataframe to observe and match them manually with the actual methods in project source code 
  collectedAttributeTokensDataFrame <- data.frame(do.call(rbind, strsplit(collectedAttributeTokens, "\\;", fixed=TRUE)))
  colnames(collectedAttributeTokensDataFrame) <- c("symbolicName", "packageName", "className", "fieldDeclaration")
  
  openxlsx2::write_xlsx(x = collectedAttributeTokensDataFrame, 
                        file = paste(testDataDir, "tokensProjectClassesNonPrivateStaticAttributes_project_", projectNames[projectId], ".xlsx", sep = ""), sheetName = "tokens")
}


create_output_for_manual_test_of_column_tokensProjectClassesNonPrivateStaticAttributes_project_common_collections()
create_output_for_manual_test_of_column_tokensProjectClassesNonPrivateStaticAttributes_project_plume()
#############################################################################################################


# test the correctness of collected tokens for tokensMethodVariablesNonPrivateNonStaticAttributes column ####
create_output_for_manual_test_of_column_tokensMethodVariablesNonPrivateNonStaticAttributes_org.apache.commons.collections4.BidiMap.put <- function(){
  projectId <- 1
  sourceFileId <- 1
  methodId <- 1
  isConstructor <- FALSE
  methodParameters <- allProjectsJavaParserDataList[[projectId]][[sourceFileId]]$typeDeclarations[[1]]$methods[[methodId]]$parameters
  methodQualifiedSignature <- allProjectsJavaParserDataList[[projectId]][[sourceFileId]]$typeDeclarations[[1]]$methods[[methodId]]$qualifiedSignature
  methodDeclaration <- allProjectsJavaParserDataList[[projectId]][[sourceFileId]]$typeDeclarations[[1]]$methods[[methodId]]$declaration
  # declaration: " V put(K key, V value)"
  
  collectedData <- get_tokensMethodVariablesNonPrivateNonStaticAttributes(projectId, sourceFileId, methodId, isConstructor)
  
  if(collectedData != ""){
    collectedAttributeTokens <- unlist(strsplit(collectedData, "\\,", fixed = T))
    
    # transfer the collected tokens into a dataframe to observe and match them manually with the actual methods in project source code
    collectedAttributeTokensDataFrame <- data.frame(do.call(rbind, strsplit(collectedAttributeTokens, "\\;", fixed=TRUE)))
    colnames(collectedAttributeTokensDataFrame) <- c("symbolicName", "packageName", "className", "fieldDeclaration")
    
    openxlsx2::write_xlsx(x = collectedAttributeTokensDataFrame,
                          file = paste(testDataDir, "tokensMethodVariablesNonPrivateNonStaticAttributes_project_org.apache.commons.collections4.BidiMap.put.xlsx", sep = ""), sheetName = "tokens")
  }
}

create_output_for_manual_test_of_column_tokensMethodVariablesNonPrivateNonStaticAttributes_org.jgrapht.graph.WeightedPseudograph.builder_UndirectedWeightedGraphBuilderBase <- function(){
  projectId <- 5
  sourceFileId <- 30
  methodId <- 1
  isConstructor <- FALSE
  methodParameters <- allProjectsJavaParserDataList[[projectId]][[sourceFileId]]$typeDeclarations[[1]]$methods[[methodId]]$parameters
  methodQualifiedSignature <- allProjectsJavaParserDataList[[projectId]][[sourceFileId]]$typeDeclarations[[1]]$methods[[methodId]]$qualifiedSignature
  methodDeclaration <- allProjectsJavaParserDataList[[projectId]][[sourceFileId]]$typeDeclarations[[1]]$methods[[methodId]]$declaration
  # declaration: "public static UndirectedWeightedGraphBuilderBase<V, E, ? extends WeightedPseudograph<V, E>, ?> builder(Class<? extends E> edgeClass)"
  
  collectedData <- get_tokensMethodVariablesNonPrivateNonStaticAttributes(projectId, sourceFileId, methodId, isConstructor)
  collectedAttributeTokens <- unlist(strsplit(collectedData, "\\,", fixed = T))
  
  # transfer the collected tokens into a dataframe to observe and match them manually with the actual methods in project source code
  collectedAttributeTokensDataFrame <- data.frame(do.call(rbind, strsplit(collectedAttributeTokens, "\\;", fixed=TRUE)))
  colnames(collectedAttributeTokensDataFrame) <- c("symbolicName", "packageName", "className", "fieldDeclaration")
  
  openxlsx2::write_xlsx(x = collectedAttributeTokensDataFrame,
                        file = paste(testDataDir, "tokensMethodVariablesNonPrivateNonStaticAttributes_project_org.jgrapht.graph.WeightedPseudograph.builder_UndirectedWeightedGraphBuilderBase.xlsx", sep = ""), sheetName = "tokens")
}


create_output_for_manual_test_of_column_tokensMethodVariablesNonPrivateNonStaticAttributes_org.apache.commons.collections4.BidiMap.put()
create_output_for_manual_test_of_column_tokensMethodVariablesNonPrivateNonStaticAttributes_org.jgrapht.graph.WeightedPseudograph.builder_UndirectedWeightedGraphBuilderBase()
#############################################################################################################

