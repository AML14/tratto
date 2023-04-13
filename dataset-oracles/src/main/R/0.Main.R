myWorkingDir <- "/Users/beyzaeken/Desktop/dataset-oracles/"
setwd(myWorkingDir)

install.packages("rjson", repos = "http://cran.us.r-project.org")
# install.packages("xlsx", repos = "http://cran.us.r-project.org")
install.packages("openxlsx2", repos = "http://cran.us.r-project.org")
install.packages("stringr", repos = "http://cran.us.r-project.org")
install.packages("qdapRegex", repos = "http://cran.us.r-project.org")
install.packages("stringdist", repos = "http://cran.us.r-project.org")
install.packages("strex", repos = "http://cran.us.r-project.org")
install.packages("plyr", repos = "http://cran.us.r-project.org")
install.packages("logr", repos = "http://cran.us.r-project.org")
install.packages("testthat", repos = "http://cran.us.r-project.org")

library("rjson")
# library("xlsx")
library("openxlsx2")
library("stringr")
library("qdapRegex")
library("stringdist")
library("strex")
library("plyr")
library("logr")
library("testthat")

source("src/main/R/0.WorkingEnvironment.R")
source("src/main/R/0.Commons.R")
source("src/main/R/1.Oracles.R")
source("src/main/R/2.Constants.R")
source("src/main/R/3.GlobalDictionary.R")
source("src/main/R/4.ClassInfo.R")
source("src/main/R/5.MethodTokens.R")
source("src/main/R/6.AttributeTokens.R")
source("src/main/R/7.TokensInOracles.R")
source("src/main/R/8.FinalDataset.R")

# read all projects' and jdk json files (javaparser output) into a list ####
allProjectsJavaParserDataList <- list()
for(i in 1:length(projectNames)){
  projectName <- projectNames[i]
  projectJavaParserOutputFilePath <- javaParserOutputPaths[i]
  projectJavaParserData <- fromJSON(paste(readLines(projectJavaParserOutputFilePath), collapse = ""))
  allProjectsJavaParserDataList[[projectName]] <- projectJavaParserData
}
javaParserDataJsonJDK <- fromJSON(paste(readLines(javaParserOutputPathJDK), collapse = ""))
###########################################################################

# create oracle matching dataframe (this dataframe is used to create the final dataset) ####
# get all conditions (jdoctor generated conditions for oracles) of all six projects
matchedOracleConditionsDataFrames <- lapply(c(1:6), detect_matched_conditions)
matchedOracleConditionsDataFrame <- ldply(matchedOracleConditionsDataFrames, data.frame)
# write.xlsx(x = matchedOracleConditionsDataFrame, file = paste(outputDataDir, "/matchedOracleConditions.xlsx", sep = ""), sheetName = "matchedOracleConditions", row.names = F)
###########################################################################################

# create final oracle-dataset ####
# generate dataset for each project in both list (json) and dataframe (excel/csv) formats
dataset1 <- generate_final_dataset(projectId = 1)
dataset2 <- generate_final_dataset(projectId = 2)
dataset3 <- generate_final_dataset(projectId = 3)
dataset4 <- generate_final_dataset(projectId = 4)
dataset5 <- generate_final_dataset(projectId = 5)
dataset6 <- generate_final_dataset(projectId = 6)

# write the dataset to a json file
allProjectsDataPointsAsList <- c(dataset1$finalListOfDataPoints, dataset2$finalListOfDataPoints, dataset3$finalListOfDataPoints, dataset4$finalListOfDataPoints, dataset5$finalListOfDataPoints, dataset6$finalListOfDataPoints)
write(jsonify::to_json(allProjectsDataPointsAsList, unbox = T), paste(outputDataDir, "oracle-dataset.json", sep = ""))

# write the dataset to excel and csv files
allProjectsDataPointsAsDataFrame <- rbind(dataset1$finalDataFrame, dataset2$finalDataFrame, dataset3$finalDataFrame, dataset4$finalDataFrame, dataset5$finalDataFrame, dataset6$finalDataFrame)
allProjectsDataPointsAsDataFrame <- cbind(id = seq.int(nrow(allProjectsDataPointsAsDataFrame)), allProjectsDataPointsAsDataFrame)
allProjectsDataPointsAsDataFrame <- allProjectsDataPointsAsDataFrame[, -which(names(allProjectsDataPointsAsDataFrame) %in% c("sourceFileId","methodId", "javadocTagId"))]
# openxlsx2::write_xlsx(x = allFinalDataFrame, file = paste("/Users/beyzaeken/Desktop/oracle-dataset.xlsx", sep = ""), sheetName = "data")
# write.csv(x = allFinalDataFrame, file = paste("/Users/beyzaeken/Desktop/oracle-dataset.csv", sep = ""))


# select some samples from all data points
selectedSamplesIds <- c(24646, 24938, 23025, 22461, 15593, 1934, 24598, 24599, 24600, 24601)
selectedSamplesAsList <- allProjectsDataPointsAsList[selectedSamplesIds]
write(jsonify::to_json(selectedSamplesAsList, unbox = T), paste(outputDataDir, "selected-samples.json", sep = ""))

selectedSamplesAsDataFrame <- allProjectsDataPointsAsDataFrame[selectedSamplesIds, ]
openxlsx2::write_xlsx(x = selectedSamplesAsDataFrame, file = paste(outputDataDir, "selectedSamples.xlsx", sep = ""), sheetName = "data", row.names = F)

##########################







