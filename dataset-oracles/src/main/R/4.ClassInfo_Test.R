

# test the correctness of collected classes

test_that(desc = "test_get_all_classes_in_project_return_vector_function_plume", code = {
  projectId <- 6
    
  groundTruthClassList <- c("plume.FileIOException",
                            "plume.MathMDE",
                            "plume.DeterministicObject",
                            "plume.Digest",
                            "plume.StringBuilderDelimited",
                            "plume.OrderedPairIterator",
                            "plume.CountingPrintWriter",
                            "plume.MultiVersionControl",
                            "plume.WeakIdentityHashMap",
                            "plume.IterableIterator",
                            "plume.Lookup",
                            "plume.Pair",
                            "plume.FileCompiler",
                            "plume.MultiRandSelector",
                            "plume.RandoopMappedCalls",
                            "plume.Stopwatch",
                            "plume.TaskManager",
                            "plume.WeakHasherMap",
                            "plume.ClassFileVersion",
                            "plume.WeakIdentityPair",
                            "plume.OptionsDoclet",
                            "plume.DeclarationAnnotations",
                            "plume.Option",
                            "plume.RegexUtil",
                            "plume.JWhich",
                            "plume.TimeLimitProcess",
                            "plume.Intern",
                            "plume.GraphMDE",
                            "plume.HtmlPrettyPrint",
                            "plume.SimpleLog",
                            "plume.OptionGroup",
                            "plume.ICalAvailable",
                            "plume.RandomSelector",
                            "plume.BCELUtil",
                            "plume.UtilMDE",
                            "plume.Hasher",
                            "plume.BibtexClean",
                            "plume.LimitedSizeIntSet",
                            "plume.Partitioner",
                            "plume.Triple",
                            "plume.Options",
                            "plume.ArraysMDE",
                            "plume.StrTok",
                            "plume.Unpublicized",
                            "plume.TestPlume",
                            "plume.LimitedSizeSet",
                            "plume.EntryReader",
                            "plume.Filter",
                            "plume.FuzzyFloat")
  
  groundTruthClassList <- groundTruthClassList[order(groundTruthClassList)]
  
  retrievedClasses <- get_all_classes_in_project_as_list(projectId) #retrievedClasses is a list, first item keeps class names, second item keeps package names
  retrievedClassesWithPackageNames <- paste(retrievedClasses[[2]], ".", retrievedClasses[[1]], sep = "")
  retrievedClassesWithPackageNames <- retrievedClassesWithPackageNames[order(retrievedClassesWithPackageNames)]
  
  expect_equal(retrievedClassesWithPackageNames, groundTruthClassList)
  
})

test_that(desc = "test_get_all_classes_in_project_return_vector_function_common_collections", code = {
  projectId <- 1
  
  # retrieve java all files in the project's source code directory
  # filter out the package-info files
  # replace / with . "org/apache/commons/collections4/Trie.java" --> "org.apache.commons.collections4.Trie.java"    
  # remove file extensions (.java)
  allJavaFilesInProjectSourceDirectory <- list.files(path = projectSourceDirs[projectId], pattern = ".java", recursive = T)
  allJavaFilesInProjectSourceDirectory <- allJavaFilesInProjectSourceDirectory[-grep(x = allJavaFilesInProjectSourceDirectory, pattern = "package-info")]
  allJavaFilesInProjectSourceDirectory <- gsub(x = allJavaFilesInProjectSourceDirectory, pattern = "/", fixed = T, replacement = ".")
  allJavaFilesInProjectSourceDirectory <- gsub(x = allJavaFilesInProjectSourceDirectory, pattern = ".java", fixed = T, replacement = "")
  allJavaFilesInProjectSourceDirectory <- allJavaFilesInProjectSourceDirectory[order(allJavaFilesInProjectSourceDirectory)]
             
  retrievedClasses <- get_all_classes_in_project_as_list(projectId) #retrievedClasses is a list, first item keeps class names, second item keeps package names
  retrievedClassesWithPackageNames <- paste(retrievedClasses[[2]], ".", retrievedClasses[[1]], sep = "")
  retrievedClassesWithPackageNames <- retrievedClassesWithPackageNames[order(retrievedClassesWithPackageNames)]
  
  expect_equal(retrievedClassesWithPackageNames, allJavaFilesInProjectSourceDirectory)
})

test_that(desc = "test_get_all_classes_in_project_return_vector_function_jgrapht", code = {
  projectId <- 5
  
  # retrieve java all files in the project's source code directory
  # filter out the package-info files
  # replace / with . "org/apache/commons/collections4/Trie.java" --> "org.apache.commons.collections4.Trie.java"    
  # remove file extensions (.java)
  allJavaFilesInProjectSourceDirectory <- list.files(path = projectSourceDirs[projectId], pattern = ".java", recursive = T)
  packageInfoFiles <- grep(x = allJavaFilesInProjectSourceDirectory, pattern = "package-info")
  if(length(packageInfoFiles) > 0){
    allJavaFilesInProjectSourceDirectory <- allJavaFilesInProjectSourceDirectory[-packageInfoFiles] 
  }
  
  allJavaFilesInProjectSourceDirectory <- gsub(x = allJavaFilesInProjectSourceDirectory, pattern = "/", fixed = T, replacement = ".")
  allJavaFilesInProjectSourceDirectory <- gsub(x = allJavaFilesInProjectSourceDirectory, pattern = ".java", fixed = T, replacement = "")
  allJavaFilesInProjectSourceDirectory <- allJavaFilesInProjectSourceDirectory[order(allJavaFilesInProjectSourceDirectory)]
  
  retrievedClasses <- get_all_classes_in_project_as_list(projectId) #retrievedClasses is a list, first item keeps class names, second item keeps package names
  retrievedClassesWithPackageNames <- paste(retrievedClasses[[2]], ".", retrievedClasses[[1]], sep = "")
  retrievedClassesWithPackageNames <- retrievedClassesWithPackageNames[order(retrievedClassesWithPackageNames)]
  
  expect_equal(retrievedClassesWithPackageNames, allJavaFilesInProjectSourceDirectory)
})
