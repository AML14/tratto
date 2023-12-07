import React, {useEffect, useState} from "react";
import List from "./List.jsx";
import {FiMinusSquare, FiPlusSquare} from "react-icons/fi";
import AddConditionForm from "./AddConditionForm.jsx";

export default function ConditionContainer({ classname, title, identifier, conditions, deleteCondition, addCondition }) {
    const [currentCondition, setCurrentCondition] = useState(conditions[0] || null);
    const [expand, setExpand] = useState(true);

    useEffect(() => {
        if (currentCondition == null && conditions.length > 0){
            setCurrentCondition(conditions[0]);
        } else if (currentCondition != null && conditions.length == 0) {
            setCurrentCondition(null);
        } else if (currentCondition != null && !conditions.map(c => c._id).includes(currentCondition._id)) {
            if (conditions.length > 0) {
                setCurrentCondition(conditions[0]);
            } else {
                setCurrentCondition(null);
            }
        }
    }, [conditions]);

    const changeCurrentCondition = (id) => {
        setCurrentCondition(conditions.find(c => c._id == id));
    }

    const changeCurrentConditionOnDelete = (id) => {
        if (currentCondition._id == id) {
            if (conditions.length > 1) {
                if (conditions[0]._id !== id) {
                    setCurrentCondition(conditions[0]);
                } else {
                    setCurrentCondition(conditions[1]);
                }
            } else {
                setCurrentCondition(null);
            }
        }
    }

    return (
        <div className={classname}>
            <div className="condition-title-container">
                <button
                    className="expand-button"
                    onClick={() => { setExpand((prevState) => { return !prevState; }) }}
                >
                    {
                        expand ?
                            <FiMinusSquare size={20}/>
                            :
                            <FiPlusSquare size={20}/>
                    }
                </button>
                <h2 className="condition-title">{title}</h2>
            </div>
            {
                expand ?
                    <div className="condition-item">
                        <div className="condition-list">
                            <List
                                identifier={ identifier }
                                elements={ conditions.map( c => { return {
                                    _id: c._id,
                                    name : c.name
                                } } ) }
                                selected={ currentCondition != null ? currentCondition._id : null }
                                onClickCallback={ changeCurrentCondition }
                                deleteButtonCallback={ (id) => {
                                    deleteCondition(id) } }
                                style={ { "height": "300px", "overflow": "auto" } }
                            />
                        </div>
                        <div className="current-condition-container">
                            {
                                currentCondition != null ?
                                    <>
                                        {
                                            currentCondition.description != null &&
                                            <div className="property-container">
                                                <label className="label-property">Javadoc Tag</label>
                                                <span>{currentCondition.description}</span>
                                            </div>
                                        }
                                        {
                                            currentCondition.guard.condition != null &&
                                            <div className="property-container ">
                                                <label className="label-property">Condition</label>
                                                <span>{currentCondition.guard.condition}</span>
                                            </div>
                                        }
                                        {
                                            currentCondition.property != null &&
                                            <div className="property-container">
                                                <label className="label-property">Oracle</label>
                                                <span>{currentCondition.property.condition}</span>
                                            </div>
                                        }
                                        {
                                            currentCondition.exception != null &&
                                            <div className="property-container">
                                                <label className="label-property">Exception</label>
                                                <span>{currentCondition.exception}</span>
                                            </div>
                                        }
                                    </>
                                    :
                                    <span>{ `No ${identifier}-conditions defined` }</span>
                            }
                        </div>
                        <AddConditionForm
                            identifier={ identifier }
                            addCondition={ (formData) => { addCondition(formData) } }
                            disableProperties={{
                                condition: (value) => { return value == "" },
                                exception: identifier == "throws" ? (value) => { return value == "" } : (value) => { return false; } ,
                                oracle: identifier == "post" ? (value) => { return value == "" } : (value) => { return false; }
                            }}
                        />
                    </div>
                :
                    null
            }
        </div>
    );
}