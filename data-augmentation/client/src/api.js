const BASE_URL = "https://tratto-jdc.onrender.com/api";

const api = {
    getAllrepositoriesUrl: () => `${BASE_URL}/repositories`,
    getAllreporitoryClassesUrl: (idRepository) => `${BASE_URL}/repositories/${idRepository}/repositoryClasses`,
    getAllJDoctorConditionsUrl: (idRepository, idRepositoryClass) => `${BASE_URL}/repositories/${idRepository}/repositoryClasses/${idRepositoryClass}/jdoctorconditions`,
    getAllConditionsUrl: (idRepository, idRepositoryClass, idJDoctorCondition, conditionType) => `${BASE_URL}/repositories/${idRepository}/repositoryClasses/${idRepositoryClass}/jdoctorconditions/${idJDoctorCondition}/${conditionType}`,
    createRepositoryUrl: () => `${BASE_URL}/repositories/`,
    createRepositoryClassUrl: (idRepository) => `${BASE_URL}/repositories/${idRepository}/repositoryClasses`,
    createConditionUrl: (idRepository, idRepositoryClass, idJDoctorCondition, conditionType) => `${BASE_URL}/repositories/${idRepository}/repositoryClasses/${idRepositoryClass}/jdoctorconditions/${idJDoctorCondition}/${conditionType}`,
    deleteRepositoryUrl: (idRepository) => `${BASE_URL}/repositories/${idRepository}`,
    deleteRepositoryClassUrl: (idRepository, idRepositoryClass) => `${BASE_URL}/repositories/${idRepository}/repositoryClasses/${idRepositoryClass}`,
    deleteJDoctorConditionUrl: (idRepository, idRepositoryClass, idJDoctorCondition) => `${BASE_URL}/repositories/${idRepository}/repositoryClasses/${idRepositoryClass}/jdoctorconditions/${idJDoctorCondition}`,
    deleteConditionUrl: (idRepository, idRepositoryClass, idJDoctorCondition, idCondition, conditionType) => `${BASE_URL}/repositories/${idRepository}/repositoryClasses/${idRepositoryClass}/jdoctorconditions/${idJDoctorCondition}/${conditionType}/${idCondition}`
}

export default api;