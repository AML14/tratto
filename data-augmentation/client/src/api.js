const BASE_URL = "http://localhost:3000";

const api = {
    getAllrepositoriesUrl: () => `${BASE_URL}/repositories`,
    getAllreporitoryClassesUrl: (idRepository) => `${BASE_URL}/repositories/${idRepository}/repositoryClasses`,
    getAllJDoctorConditionsUrl: (idRepository, idRepositoryClass) => `${BASE_URL}/repositories/${idRepository}/repositoryClasses/${idRepositoryClass}/jdoctorconditions`,
    getAllConditionsUrl: (idRepository, idRepositoryClass, idJDoctorCondition, conditionType) => `${BASE_URL}/repositories/${idRepository}/repositoryClasses/${idRepositoryClass}/jdoctorconditions/${idJDoctorCondition}/${conditionType}`,
    getRepositoryUrl: (idRepository) => `${BASE_URL}/repositories/${idRepository}`,
    getRepositoryClassUrl: (idRepository, idRepositoryClass) => `${BASE_URL}/repositories/${idRepository}/repositoryClasses/${idRepositoryClass}`,
    deleteCondition: (idRepository, idRepositoryClass, idJDoctorCondition, idCondition, conditionType) => `${BASE_URL}/repositories/${idRepository}/repositoryClasses/${idRepositoryClass}/jdoctorconditions/${idJDoctorCondition}/${conditionType}/${idCondition}`,

}

export default api;