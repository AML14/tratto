
import Code from "./Code.jsx";
import List from "./List.jsx";
import React, {useEffect, useState} from "react";
import axios from "axios";
import api from "../api.js";
import ConditionContainer from "./ConditionContainer.jsx";
import Modal from "./Modal.jsx";
import AddConditionModalContent from "./AddConditionModalContent.jsx";

export default function Main({ repository, repositoryClass, jdc, updateCurrentJDC }) {

    const [preConditions, setPreConditions] = useState([]);
    const [postConditions, setPostConditions] = useState([]);
    const [throwsConditions, setThrowsConditions] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [modalIdentifier, setModalIdentifier] = useState(null);

    useEffect(() => {
        if (repository !== null && repositoryClass !== null && jdc !== null) {
            const idCurrentRepository = repository._id;
            const idRepositoryClass = repositoryClass._id;
            const idJDoctorCondition = jdc._id;
            axios
                .get(api.getAllConditionsUrl(idCurrentRepository, idRepositoryClass, idJDoctorCondition, "pre"))
                .then((response) => {
                    setPreConditions(response.data);
                })
                .catch((error) => {
                    console.log(error);
                    setPreConditions([]);
                })
            axios
                .get(api.getAllConditionsUrl(idCurrentRepository, idRepositoryClass, idJDoctorCondition, "post"))
                .then((response) => {
                    setPostConditions(response.data);
                })
                .catch((error) => {
                    console.log(error);
                    setPostConditions([]);
                })
            axios
                .get(api.getAllConditionsUrl(idCurrentRepository, idRepositoryClass, idJDoctorCondition, "throws"))
                .then((response) => {
                    setThrowsConditions(response.data);
                })
                .catch((error) => {
                    console.log(error);
                    setThrowsConditions([]);
                })
        }

    }, [jdc]);

    const deleteCondition = (conditionType, idCondition) => {
        const idRepository = repository._id;
        const idRepositoryClass = repositoryClass._id;
        const idJDoctorCondition = jdc._id;

        console.log(api.deleteConditionUrl(idRepository, idRepositoryClass, idJDoctorCondition, idCondition, conditionType));

        axios
            .delete(api.deleteConditionUrl(idRepository, idRepositoryClass, idJDoctorCondition, idCondition, conditionType))
            .then((response) => {
                if (conditionType == "pre") {
                    const filteredPreConditions = preConditions.filter(p => p._id != idCondition);
                    setPreConditions(filteredPreConditions);
                } else if (conditionType == "post") {
                    const filteredPostConditions = postConditions.filter(p => p._id != idCondition);
                    setPostConditions(filteredPostConditions);
                } else if (conditionType == "throws") {
                    const filteredThrowsConditions = throwsConditions.filter(t => t._id != idCondition);
                    setThrowsConditions(filteredThrowsConditions);
                }
            })
            .catch((error) => {
                console.log(error);
            })
    }

    const addCondition = (conditionType, modalObj) => {
        const idRepository = repository._id;
        const idRepositoryClass = repositoryClass._id;
        const idJDoctorCondition = jdc._id;

        const condition = {
            description: modalObj.description,
            guard: {
                condition: modalObj.condition,
                description: modalObj.description
            }
        };

        if (conditionType == "post") {
            condition["description"] = modalObj.description;
            condition["property"] = {
                condition: modalObj.oracle,
                description: modalObj.description
            }
        }
        if (conditionType == "throws") {
            condition["exception"] = modalObj.exception;
        }

        console.log({ condition: condition });

        console.log(api.createConditionUrl(idRepository, idRepositoryClass, idJDoctorCondition, conditionType));

        axios
            .post(api.createConditionUrl(idRepository, idRepositoryClass, idJDoctorCondition, conditionType), { condition: condition } )
            .then((response) => {
                if (conditionType == "pre") {
                    setPreConditions((prevState) => { return [...prevState, response.data] });
                } else if (conditionType == "post") {
                    setPostConditions((prevState) => { return [...prevState, response.data] });
                } else if (conditionType == "throws") {
                    setThrowsConditions((prevState) => { return [...prevState, response.data] });
                }
            })
            .catch((error) => {
                console.log(error);
            })
    }

    return (
        <>
            {
                jdc !== null ?
                (
                    <>
                        <div id="jdoctor-source">
                            <Code
                                label="Class Source Code"
                                identifier="class-source-code"
                                code={jdc.source.classSourceCode}
                                language="java"
                            />
                            <Code
                                label="Method Source Code"
                                identifier="method-source-code"
                                code={jdc.source.methodSourceCode}
                                language="java"
                            />
                            <Code
                                label="Javadoc"
                                identifier="javadoc"
                                code={jdc.source.methodJavadoc}
                                language="java"
                            />
                        </div>
                        <div id="jdoctor-conditions">
                            <ConditionContainer
                                classname="pre-condition-container"
                                title="Pre-conditions"
                                identifier="pre"
                                conditions={ preConditions.map(p => { return { ...p, name: p.guard.condition } }) }
                                addCondition={() => {
                                    setModalIdentifier("pre");
                                    setIsModalOpen(true);
                                }}
                                deleteCondition={ deleteCondition.bind(null, "pre") }
                                onClickAdd={ () => {
                                    setModalIdentifier("pre");
                                    setIsModalOpen(true);
                                }}
                            />
                            <ConditionContainer
                                classname="post-condition-container"
                                title="Post-conditions"
                                identifier="post"
                                conditions={ postConditions.map(p => { return { ...p, name: p.property.condition } }) }
                                addCondition={() => {
                                    setModalIdentifier("post");
                                    setIsModalOpen(true);
                                }}
                                deleteCondition={ deleteCondition.bind(null, "post") }
                                onClickAdd={ () => {
                                    setModalIdentifier("post");
                                    setIsModalOpen(true);
                                } }
                            />
                            <ConditionContainer
                                classname="throws-condition-container"
                                title="Throws-conditions"
                                identifier="throws"
                                conditions={ throwsConditions.map(t => { return { ...t, name: t.exception } }) }
                                addCondition={() => {
                                    setModalIdentifier("throws");
                                    setIsModalOpen(true);
                                }}
                                deleteCondition={ deleteCondition.bind(null, "throws") }
                                onClickAdd={ () => {
                                    setModalIdentifier("throws");
                                    setIsModalOpen(true);
                                }}
                            />
                        </div>
                        <Modal
                            open={isModalOpen}
                            onClose={()=>{ setIsModalOpen(false); }}
                            onConfirm={(modalObj) => {
                                addCondition(modalIdentifier, modalObj);
                            }}
                            disableProperties={{
                                description: (value) => { return value == "" },
                                condition: (value) => { return value == "" },
                                exception: modalIdentifier == "throws" ? (value) => { return value == "" } : (value) => { return false; } ,
                                oracle: modalIdentifier == "post" ? (value) => { return value == "" } : (value) => { return false; }
                            }}
                            modalState={{
                                description: "",
                                condition: "",
                                exception: modalIdentifier == "throws" ? "" : null,
                                oracle: modalIdentifier == "post" ? "" : null
                            }}
                            confirmButtonLabel="Add"
                        >
                            <AddConditionModalContent
                                identifier={modalIdentifier}
                                formData={{
                                    description: "",
                                    condition: "",
                                    exception: modalIdentifier == "throws" ? "" : null,
                                    oracle: modalIdentifier == "post" ? "" : null
                                }}
                            />
                        </Modal>
                    </>
                )
                :
                    <span>JDoctor Condition Not Found</span>
            }
        </>
    )
}