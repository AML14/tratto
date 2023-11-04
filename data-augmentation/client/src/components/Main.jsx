
import Code from "./Code.jsx";
import List from "./List.jsx";
import {useEffect, useState} from "react";
import axios from "axios";
import api from "../api.js";

export default function Main({ repository, repositoryClass, jdc, onClickCallback }) {

    const [preConditions, setPreConditions] = useState([]);
    const [postConditions, setPostConditions] = useState([]);
    const [throwsConditions, setThrowsConditions] = useState([]);
    const [currentPreCondition, setCurrentPreCondition] = useState(null);
    const [currentPostCondition, setCurrentPostCondition] = useState(null);
    const [currentThrowsCondition, setCurrentThrowsCondition] = useState(null);

    useEffect(() => {
        if (repository !== null && repositoryClass !== null && jdc !== null) {
            const idCurrentRepository = repository._id;
            const idRepositoryClass = repositoryClass._id;
            const idJDoctorCondition = jdc._id;
            axios
                .get(api.getAllConditionsUrl(idCurrentRepository, idRepositoryClass, idJDoctorCondition, "pre"))
                .then((response) => {
                    setPreConditions(response.data);
                    if (response.data.length > 0 ) {
                        setCurrentPreCondition(response.data[0]);
                    }
                })
                .catch((error) => {
                    console.log(error);
                    setPreConditions([]);
                    setCurrentPreCondition(null);
                })
            axios
                .get(api.getAllConditionsUrl(idCurrentRepository, idRepositoryClass, idJDoctorCondition, "post"))
                .then((response) => {
                    setPostConditions(response.data);
                    if (response.data.length > 0 ) {
                        setCurrentPostCondition(response.data[0]);
                    }
                })
                .catch((error) => {
                    console.log(error);
                    setPostConditions([]);
                    setCurrentPostCondition(null);
                })
            axios
                .get(api.getAllConditionsUrl(idCurrentRepository, idRepositoryClass, idJDoctorCondition, "throws"))
                .then((response) => {
                    setThrowsConditions(response.data);
                    if (response.data.length > 0 ) {
                        setCurrentThrowsCondition(response.data[0]);
                    }
                })
                .catch((error) => {
                    console.log(error);
                    setThrowsConditions([]);
                    setCurrentThrowsCondition(null);
                })
        }

    }, [jdc]);

    const deleteCondition = (conditionType, idCondition) => {
        const idRepository = repository._id;
        const idRepositoryClass = repositoryClass._id;
        const idJDoctorCondition = jdc._id;

        axios
            .delete(api.deleteConditionUrl(idRepository, idRepositoryClass, idJDoctorCondition, idCondition, conditionType))
            .then((response) => {
                if (conditionType == "pre") {
                    const filteredPreConditions = preConditions.filter(p => p._id != idCondition);
                    setPreConditions(filteredPreConditions);
                    if (filteredPreConditions.length > 0) {
                        setCurrentPreCondition(filteredPreConditions[0]);
                    } else {
                        setCurrentPreCondition(null);
                    }
                } else if (conditionType == "post") {
                    const filteredPostConditions = postConditions.filter(p => p._id != idCondition);
                    setPostConditions(filteredPostConditions);
                    if (filteredPostConditions.length > 0) {
                        setCurrentPostCondition(filteredPostConditions[0]);
                    } else {
                        setCurrentPostCondition(null);
                    }
                } else if (conditionType == "throws") {
                    const filteredThrowsConditions = throwsConditions.filter(t => t._id != idCondition);
                    setThrowsConditions(filteredThrowsConditions);
                    if (filteredThrowsConditions.length > 0) {
                        setCurrentThrowsCondition(filteredThrowsConditions[0]);
                    } else {
                        setCurrentThrowsCondition(null);
                    }
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
                            <h2 id="pre-condition-title">Pre-conditions</h2>
                            <List
                                identifier="pre"
                                elements={ preConditions.map( p => { return {
                                    _id: p._id,
                                    name : p.guard.condition
                                } } ) }
                                onClickCallback={ onClickCallback }
                                deleteButtonCallback={ deleteCondition.bind(null, "pre") }
                            />
                            <h2 id="post-condition-title">Post-conditions</h2>
                            <List
                                identifier="post"
                                elements={ postConditions.map( p => { return {
                                    _id: p._id,
                                    name : p.property.condition
                                } } ) }
                                onClickCallback={ onClickCallback }
                                deleteButtonCallback={ deleteCondition.bind(null, "post") }
                            />
                            <h2 id="throws-condition-title">Throws-conditions</h2>
                            <List
                                identifier="throws"
                                elements={ throwsConditions.map( t => { return {
                                    _id: t._id,
                                    name : t.guard.condition
                                } } ) }
                                onClickCallback={ onClickCallback }
                                deleteButtonCallback={ deleteCondition.bind(null, "throws") }
                            />
                            <div id="pre-container" className="condition-container">
                                {
                                    currentPreCondition != null ?
                                        <>
                                            <div id="pre-description-container" className="property-container">
                                                <label>Javadoc Tag</label>
                                                <span>{currentPreCondition.description}</span>
                                            </div>
                                            <div id="pre-oracle-container" className="property-container">
                                                <label>Oracle</label>
                                                <span>{currentPreCondition.guard.condition}</span>
                                            </div>
                                        </>
                                    :
                                        <span>No pre-conditions defined</span>
                                }
                            </div>
                            <div id="post-container" className="condition-container">
                                {
                                    currentPostCondition != null ?
                                        <>
                                            <div id="post-description-container" className="property-container">
                                                <label>Javadoc Tag</label>
                                                <span>{currentPostCondition.description}</span>
                                            </div>
                                            <div id="post-condition-container" className="property-container">
                                                <label>Condition</label>
                                                <span>{currentPostCondition.guard.condition}</span>
                                            </div>
                                            <div id="post-oracle-container" className="property-container">
                                                <label>Oracle</label>
                                                <span>{currentPostCondition.property.condition}</span>
                                            </div>
                                        </>
                                        :
                                        <span>No pre-conditions defined</span>
                                }
                            </div>
                            <div id="throws-container" className="condition-container">
                                {
                                    currentThrowsCondition != null ?
                                        <>
                                            <div id="throws-description-container" className="property-container">
                                                <label>Javadoc Tag</label>
                                                <span>{currentThrowsCondition.description}</span>
                                            </div>
                                            <div id="throws-condition-container" className="property-container">
                                                <label>Condition</label>
                                                <span>{currentThrowsCondition.guard.condition}</span>
                                            </div>
                                            <div id="throws-oracle-container" className="property-container">
                                                <label>Exception</label>
                                                <span>{currentThrowsCondition.exception}</span>
                                            </div>
                                        </>
                                        :
                                        <span>No pre-conditions defined</span>
                                }
                            </div>
                        </div>
                    </>
                )
                :
                    <span>JDoctor Condition Not Found</span>
            }
        </>
    )
}