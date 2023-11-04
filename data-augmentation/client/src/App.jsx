import React, { useState, useEffect } from 'react';
import axios from "axios";
import api from "./api.js";
import List from "./components/List.jsx";
import Main from "./components/Main.jsx";
import Modal from "./components/Modal.jsx";
import {FaFolderPlus} from "react-icons/fa6";
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
        }

    }, [currentRepository]);

    useEffect(() => {
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
        setCurrentRepository(repositoryClasses[newIdx]);
    }

    const changeCurrentJDoctorCondition = (newIdx) => {
        setCurrentJDoctorCondition(jDoctorConditions[newIdx]);
    }

    const uploadJDoctorConditions = (modalObj) => {
        console.log(modalObj);
    }

    return (
        <>
            <h1 id="main-title">Data Augmentation</h1>
            <div id="page">
                <div id="menu">
                    <List label="Repositories" identifier="repository" elements={repositories} onClickCallback={changeCurrentRepository}></List>
                    <List label="Classes" identifier="repository-classes" elements={repositoryClasses} onClickCallback={changeCurrentRepositoryClass} ></List>
                    <List label="JDoctor Conditions" identifier="jdc" elements={jDoctorConditions.map(j => { return { name: j.operation.name } })} onClickCallback={changeCurrentJDoctorCondition} ></List>
                </div>
                <div id="main">
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
