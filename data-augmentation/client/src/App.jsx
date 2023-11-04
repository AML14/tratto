import React, { useState, useEffect } from 'react';
import axios from "axios";
import api from "./api.js";
import List from "./components/List.jsx";
import Main from "./components/Main.jsx";
import Modal from "./components/Modal.jsx";
import {FaFileUpload} from "react-icons/fa";
import CircleMenuButton from "./components/CircleMenuButton.jsx";
import UploadJDCModalContent from "./components/UploadJDCModalContent.jsx";

export const AppContext = React.createContext();

function App() {
    const [repositories, setRepositories] = useState([]);
    const [repositoryClasses, setRepositoryClasses] = useState([]);
    const [jDoctorConditions, setJDoctorConditions] = useState([]);
    const [currentRepository, setCurrentRepository] = useState(repositories.length > 0 ? repositories[0] : null);
    const [currentRepositoryClass, setCurrentRepositoryClass] = useState(repositoryClasses.length > 0 ? repositoryClasses[0] : null);
    const [currentJDoctorCondition, setCurrentJDoctorCondition] = useState(jDoctorConditions.length > 0 ? jDoctorConditions[0] : null);
    const [isModalOpen, setIsModalOpen] = useState(false);

    useEffect( () => {
        console.log("useEffect []");
         axios
            .get(api.getAllrepositoriesUrl())
            .then((response) => {
                setRepositories(response.data);
                if (response.data.length > 0) {
                    setCurrentRepository(response.data[0]);
                } else {
                    setCurrentRepository(null);
                }
            })
            .catch((error) => {
                console.log(error);
                setRepositories([]);
                setCurrentRepository(null);
            })
    }, []);

    useEffect(() => {
        console.log("useEffect [currentRepository]");
        if (currentRepository !== null) {
            const idRepository = currentRepository._id;
            axios
                .get(api.getAllreporitoryClassesUrl(idRepository))
                .then((response) => {
                    setRepositoryClasses(response.data);
                    if (response.data.length > 0) {
                        setCurrentRepositoryClass(response.data[0]);
                    }
                })
                .catch((error) => {
                    console.log(error);
                    setRepositoryClasses([]);
                    setCurrentRepository(null);
                })
        } else {
            setCurrentRepositoryClass(null);
            setRepositoryClasses([]);
        }

    }, [currentRepository]);

    useEffect(() => {
        console.log("useEffect [currentRepositoryClass]");
        if (currentRepository !== null && currentRepositoryClass !== null) {
            const idCurrentRepository = currentRepository._id;
            const idRepositoryClass = currentRepositoryClass._id;
            axios
                .get(api.getAllJDoctorConditionsUrl(idCurrentRepository, idRepositoryClass))
                .then((response) => {
                    setJDoctorConditions(response.data);
                    if (response.data.length > 0 ) {
                        setCurrentJDoctorCondition(response.data[0]);
                    }
                })
                .catch((error) => {
                    console.log(error);
                    setJDoctorConditions([]);
                    setCurrentJDoctorCondition(null);
                })
        } else {
            setCurrentJDoctorCondition(null);
        }

    }, [currentRepositoryClass]);

    const changeCurrentRepository = (newIdx) => {
        setCurrentRepository(repositories[newIdx]);
    }

    const changeCurrentRepositoryClass = (newIdx) => {
        setCurrentRepositoryClass(repositoryClasses[newIdx]);
    }

    const changeCurrentJDoctorCondition = (newIdx) => {
        setCurrentJDoctorCondition(jDoctorConditions[newIdx]);
    }

    const uploadJDoctorConditions = async (modalObj) => {
        const repository = modalObj.repository;
        const uploadRepositoryClasses = modalObj.repositoryClasses;
        let idRepository = repository._id;
        const filteredUploadedRepositoryClasses = [];

        if (idRepository == null) {
            await axios
                .post(api.createRepositoryUrl(), { "repository": repository })
                .then((response) => {
                    console.log(1)
                    idRepository = response.data._id;
                    repository._id = response.data._id;
                    setRepositories([...repositories, response.data]);
                })
                .catch((error) => {
                    console.log(error);
                })
        }
        for(let repositoryClass of uploadRepositoryClasses) {
            const filteredRepositoryClasses = repositoryClasses.filter(r => r.name == repositoryClass.name);
            if (filteredRepositoryClasses.length == 0) {
                await axios
                    .post(api.createRepositoryClassUrl(repository._id), { "repositoryClass": repositoryClass })
                    .then((response) => {
                        filteredUploadedRepositoryClasses.push(response.data);
                    })
                    .catch((error) => {
                        console.log(error);
                    });

            } else {
                console.log("Repository class already exists. Not uploaded.");
            }
            setRepositoryClasses([...repositoryClasses, ...filteredUploadedRepositoryClasses]);
        }

        if (currentRepository == null) {
            setCurrentRepository(repository);
        }
    }

    const deleteRepository = async (idx) => {
        const repository = repositories[idx];
        const idRepository = repository._id;
        axios
            .delete(api.deleteRepositoryUrl(idRepository))
            .then((response) => {
                const filteredRepositories = repositories.filter(r => r._id !== idRepository);
                setRepositories(filteredRepositories);
                if (filteredRepositories.length > 0 ) {
                    setCurrentRepository(filteredRepositories[0]);
                } else {
                    setCurrentRepository(null);
                }
            })
            .catch((error) => {
                console.log(error);
            })
    }

    const deleteRepositoryClass = async (idx) => {
        const idCurrentRepository = currentRepository._id;
        const repositoryClass = repositoryClasses[idx];
        const idRepositoryClass = repositoryClass._id;
        axios
            .delete(api.deleteRepositoryClassUrl(idCurrentRepository, idRepositoryClass))
            .then((response) => {
                const filteredRepositoryClasses = repositoryClasses.filter(r => r._id !== idRepositoryClass);
                setRepositoryClasses(filteredRepositoryClasses);
                if (filteredRepositoryClasses.length > 0 ) {
                    setCurrentRepositoryClass(filteredRepositoryClasses[0]);
                } else {
                    setCurrentRepositoryClass(null);
                }
            })
            .catch((error) => {
                console.log(error);
            })
    };

    const deleteJDoctorCondition = async (idx) => {
        console.log(idx);
        console.log(jDoctorConditions);
        console.log(jDoctorConditions[idx]);
        const idRepository = currentRepository._id;
        const idRepositoryClass = currentRepositoryClass._id;
        const jDoctorCondition = jDoctorConditions[idx];
        const idJDoctorCondition = jDoctorCondition._id;
        axios
            .delete(api.deleteJDoctorConditionUrl(idRepository, idRepositoryClass, idJDoctorCondition))
            .then((response) => {
                const filteredJDoctorConditions = jDoctorConditions.filter(r => r._id !== idJDoctorCondition);
                setJDoctorConditions(filteredJDoctorConditions);
                if (filteredJDoctorConditions.length > 0 ) {
                    setCurrentJDoctorCondition(filteredJDoctorConditions[0]);
                } else {
                    setCurrentJDoctorCondition(null);
                }
            })
            .catch((error) => {
                console.log(error);
            })
    };

    return (
        <>
            <h1 id="main-title">Data Augmentation</h1>
            <div id="page">
                <div id="menu">
                    <List
                        label="Repositories"
                        identifier="repository"
                        selected={currentRepository ? currentRepository._id : null}
                        elements={ repositories.map(r => { return {
                            _id: r._id,
                            name: r.projectName
                        }}) }
                        onClickCallback={changeCurrentRepository}
                        deleteButtonCallback={deleteRepository}
                    />
                    <List
                        label="Classes"
                        identifier="repository-classes"
                        selected={currentRepositoryClass ? currentRepositoryClass._id : null}
                        elements={ repositoryClasses.map(r => {
                            let name = r.name;
                            let namePackagePrefix = name.substring(0, name.lastIndexOf("."));
                            name = name.substring(name.lastIndexOf(".") + 1);
                            return {
                                _id: r._id,
                                name: name,
                                namePackagePrefix: namePackagePrefix
                            }
                        }) }
                        onClickCallback={changeCurrentRepositoryClass}
                        deleteButtonCallback={deleteRepositoryClass}
                    />
                    <List
                        label="JDoctor Conditions"
                        identifier="jdc"
                        selected={currentJDoctorCondition ? currentJDoctorCondition._id : null}
                        elements={ currentRepositoryClass !== null ?
                            jDoctorConditions.map(j => {
                                let name = j.operation.name;
                                if (name == currentRepositoryClass.name) {
                                    name = name.substring(name.lastIndexOf(".") + 1)
                                }
                                name += `(${j.identifiers.parameters.join(", ")})`;
                                return {
                                    _id: j._id,
                                    name: name
                                }
                            })
                        :
                            []
                        }
                        onClickCallback={ changeCurrentJDoctorCondition }
                        deleteButtonCallback={ deleteJDoctorCondition }
                    />
                </div>
                <div id="main" className={ currentJDoctorCondition != null ? "main" : "main-not-found" }>
                    <Main
                        repository={currentRepository}
                        repositoryClass={currentRepositoryClass}
                        jdc={currentJDoctorCondition}
                        onClickCallback={() => {}}
                    />
                </div>
                <div className="add-button-set">
                    <CircleMenuButton
                        label="Upload JDoctor Conditions"
                        onClick={() => { setIsModalOpen(true); }}
                    ><FaFileUpload color="white" size={30} /></CircleMenuButton>
                </div>
            </div>
            <Modal
                open={isModalOpen}
                onClose={()=>{ setIsModalOpen(false); }}
                onUpload={(modalObj) => { uploadJDoctorConditions(modalObj) }}
            >
                <UploadJDCModalContent
                    repositories={repositories}
                    repositoryClasses={repositoryClasses}
                />
            </Modal>
        </>
    )
}

export default App;
