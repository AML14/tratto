const fs = require('fs');
const axios = require('axios');
const dotenv = require('dotenv');
// Load configuration file and environment variables
dotenv.config({ path: ".env" });
const port = process.env.PORT || 3000;

const arg1 = process.argv[2];
const arg2 = process.argv[3];

async function getRepositoryClass(pathToFile) {
    try {
        const repositoryClassObj = JSON.parse(fs.readFileSync(`${pathToFile}`));
        return repositoryClassObj;
    } catch (error) {
        console.error(error);
        return []
    }
}
async function createRepositoryClass(repositoryId, repositoryClass) {
    try {
        const repoResult = await axios.get(`http://localhost:${port}/repositories/${repositoryId}`);
        await new Promise(resolve => setTimeout(resolve, 100));

        if (repoResult.data !== null) {
            await new Promise(resolve => setTimeout(resolve, 500));
            const repoClassResult = await axios.post(`http://localhost:${port}/repositories/${repositoryId}/repositoryClasses`, { repositoryClass: { name: repositoryClass.name, jDoctorConditions: [] } });
            await new Promise(resolve => setTimeout(resolve, 500));
            const repositoryClassData = repoClassResult.data;
            const repositoryClassId = repositoryClassData._id;
            console.log(`Assigned repository class: ${repositoryClassData.name}`);
            for (const jDoctorCondition of repositoryClass.jDoctorConditions) {
                const result = await axios.post(`http://localhost:${port}/repositories/${repositoryId}/repositoryClasses/${repositoryClassId}/jdoctorconditions`, { jDoctorCondition: jDoctorCondition });
                await new Promise(resolve => setTimeout(resolve, 100));
                const jDoctorConditionData = result.data;
                const jDoctorConditionId = jDoctorConditionData._id;
                console.log(`Uploaded JDoctorCondition: ${jDoctorConditionData.operation.name}`);
            }
            console.log("Completed!")
        } else {
            console.log(`Repository with id ${repositoryId} does not exist!`);
        }
    } catch (error) {
        console.error(error);
    }
}

(async function upload() {
    try{
        const repositoryClass = await getRepositoryClass(arg2);
        await new Promise(resolve => setTimeout(resolve, 500));
        createRepositoryClass(arg1, repositoryClass);
    } catch (error) {
        console.error(error);
    }
})();
