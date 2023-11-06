import React, {useEffect, useState} from "react";
import List from "./List.jsx";

export default function ConditionContainer({ classname, title, identifier, conditions, deleteCondition, onClickAdd }) {
    const [currentCondition, setCurrentCondition] = useState(conditions[0] || null);

    useEffect(() => {
        if (currentCondition == null && conditions.length > 0){
            setCurrentCondition(conditions[0]);
        } else if (currentCondition != null && conditions.length == 0) {
            setCurrentCondition(null);
        }
    }, [conditions]);

    const changeCurrentCondition = (newIdx) => {
        setCurrentCondition(conditions[newIdx]);
    }

    return (
        <div className={classname}>
            <h2 className="condition-title">{title}</h2>
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
                        deleteButtonCallback={ (idx) => {
                            deleteCondition(conditions[idx]._id) } }
                        style={ { "height": "150px", "overflow": "scroll" } }
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
                <button
                    className="add-condition-button"
                    onClick={ onClickAdd }
                >Add</button>
            </div>
        </div>
    );
}