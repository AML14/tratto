import {TiDelete} from "react-icons/ti";
import React, {useState} from "react";
import {FiMinusSquare, FiPlusSquare} from "react-icons/fi";

export default function List({ label, identifier, elements, onClickCallback, deleteButtonCallback }) {

    const [expand, setExpand] = useState(true);

    return (
        <div className="list-container">
            {
                label != null ?
                    <div className="list-label">
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
                        <h3 className="title-list">{label}</h3>
                    </div>
                :
                    null
            }
            {
                expand ?
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
                :
                    null
            }

        </div>
    )
}