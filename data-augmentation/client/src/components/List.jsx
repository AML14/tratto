import {TiDelete} from "react-icons/ti";
import React from "react";

export default function List({ label, identifier, elements, onClickCallback, deleteButtonCallback }) {
    return (
        <div className="list-container">
            <div className="label">
                <label>{label}</label>
            </div>
            <div className="list-item">
                {
                    elements.length > 0 ?
                        elements.map((e, idx) => {
                            return (
                                <div key={`${identifier}-${e.name}`} className="list-row">
                                    <span className="list-row-name" onClick={() => { onClickCallback(idx) }}>{e.name}</span>
                                    <button className="delete-button" onClick={() => deleteButtonCallback(idx)}><TiDelete color="darkred" size={25} /></button>
                                </div>
                            );
                        })
                        :
                        <span>Not found</span>
                }
            </div>
        </div>
    )
}