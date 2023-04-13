
get_all_classes_in_project_as_list <- function(projectId){
  # this function collects all classes' class name and package name in a given project 
  # this function is used to generate the "tokensProjectClasses" column of the oracle-dataset
  # input: a numeric value represents id of the project we are processing, could be from 1 to 6.
  # output: a list includes two vectors: 1) className vector includes all classes' name 2) packageName vector includes all classes' package information 
  
  # Flow of code:
  # 1. get project directory using given projectId
  # 2. read the list of all classes from the classList.txt file in the project directory
  # 3. extract all classes' class name and package name
  # 4. ignore package-info files
  
  # this function uses functions from strex library to extract class name and package name
  # str_after_last: to retrieve class name (e.g., BidiMap)
  # str_before_last_dot: to retrieve package name (e.g., org.apache.commons.collections4)

  # Example of how class info is kept in the classList.txt file:
  # org.apache.commons.collections4.BidiMap
  # org.apache.commons.collections4.Equator
  # org.apache.commons.collections4.MultiSet
  # ....

  projectDir <- projectDirs[projectId]

  classList <- read.table(file = paste(projectDir, "classList.txt", sep = ""))
  classList <- classList[, 1]

  # detect the package-info items in the classList, and filter out them from the classList
  # package-info items are detected by checking if the class name (the substring after last dot) equals to package-info
  packageInfoFiles <- grep(x = str_after_last(classList, "\\."), pattern = "package-info", ignore.case = T)
  if(length(packageInfoFiles) > 0){
    classList <- classList[-packageInfoFiles] 
  }

  # retrieve both class (substring after last dot) & package name (substring before last dot)
  # classNamesVector <- paste(paste("[", str_after_last(classList, "\\."), ";", str_before_last_dot(classList), "]", sep = ""), collapse = ",")
  # classNamesVector <- paste(paste(str_after_last(classList, "\\."), subElementSeparator, str_before_last_dot(classList), sep = ""), collapse = elementSeparator)

  classPackageNamesList <- list(str_after_last(classList, "\\."), str_before_last_dot(classList))

  return(classPackageNamesList)
}

get_tokensProjectClasses <- function(projectId){
  # this function collects the "tokensProjectClasses" column of the oracle-dataset
  # input: a numeric value represents id of the project we are processing, could be from 1 to 6.
  # output: a character vector includes tokens in format of "className1\;packageName1\,className2\;packageName2\, ..."
  
  # an example of the generated output: 
  # AbstractOrderedMapIteratorDecorator\\;org.apache.commons.collections4.iterators\\,FilterListIterator\\;org.apache.commons.collections4.iterators\\,SplitMapUtils\\;org.apache.commons.collections4\\,BoundedMap\\;org.apache.commons.collections4
  
  # above example will be seen in the oracle-dataset as following:
  # AbstractOrderedMapIteratorDecorator\;org.apache.commons.collections4.iterators\,FilterListIterator\;org.apache.commons.collections4.iterators\,SplitMapUtils\;org.apache.commons.collections4\,BoundedMap\;org.apache.commons.collections4
  
  return(collapseTokenElements(get_all_classes_in_project_as_list(projectId)))
}

get_class_source_code <- function(projectId, sourceFileId){
  
  sourceFileName <- allProjectsJavaParserDataList[[projectId]][[sourceFileId]]$sourceFileName
  
  sourceFilePath <- paste(projectSourceDirs[projectId], 
                          gsub(x = sourceFileName, pattern = ".", replacement = "/", fixed = TRUE), 
                          ".java", sep = "")
 
  classSourceCode <- paste(readLines(sourceFilePath), collapse = "\n")
  
  return(classSourceCode)
}
