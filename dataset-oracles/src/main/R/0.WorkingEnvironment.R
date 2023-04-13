
dataDir <- paste(myWorkingDir, "data/", sep = "")
testDataDir <- paste(myWorkingDir, "data/Test/", sep = "")
outputDataDir <- paste(myWorkingDir, "data/rOutput/", sep = "")
projectsMainDir <- paste(myWorkingDir, "src/main/resources/ISSTA18_Projects/", sep = "")
jdkDir <- paste(myWorkingDir, "src/main/resources/openjdk-jdk11/", sep = "")

dir.create(file.path(outputDataDir), recursive = T, showWarnings = FALSE)

projectNames <- c(
  "commons-collections4-4.1",
  "commons-math3-3.6.1",
  "gs-core-1.3",
  "guava-19.0",
  "jgrapht-core-0.9.2",
  "plume-lib-1.1.0"
)

projectDirs <- paste(projectsMainDir, projectNames, "/", sep = "")
projectSourceDirs <- paste(projectDirs, c("src/main/java/", "src/main/java/", "src/", "guava/src/", "", "java/src/"), sep = "")
conditionDirs <- paste(projectsMainDir, projectNames, "/conditions/", sep = "")
classListDirs <- paste(projectDirs, "classList.txt", sep = "")
javaParserOutputPaths <- paste(dataDir, "javaparserOutput/", projectNames, "_dataCollectedWithJavaParser.json", sep = "")
javaParserOutputPathJDK <- paste(dataDir, "javaparserOutput/", "openjdk-jdk11_dataCollectedWithJavaParser.json", sep = "")

