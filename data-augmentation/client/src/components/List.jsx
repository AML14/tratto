import {TiDelete} from "react-icons/ti";
import React, {useState} from "react";
import {FiMinusSquare, FiPlusSquare} from "react-icons/fi";

const PERMIT = import.meta.env.VITE_PERMIT;

export default function List({ label, identifier, selected, elements, onClickCallback, deleteButtonCallback, style }) {

    const [expand, setExpand] = useState(true);
    const [searchTerm, setSearchTerm] = useState('');

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredData = (elements) => {
        return elements.filter((item) =>
            item.name.toLowerCase().includes(searchTerm.toLowerCase())
        );
    }

    return (
        <div className="list-container" style={style}>
            {
                label != null &&
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
            }
            {
                expand ?
                    <div className="list-item" style={ elements.length > 15 ? { boxShadow: "inset 0px -5px 10px -10px rgba(0, 0, 0, 0.5)" } : null }>
                        <input
                            type="text"
                            placeholder="Search..."
                            className="search-input"
                            value={searchTerm}
                            onChange={handleSearch}
                        />
                        {
                            elements.length > 0 ?
                                filteredData(elements).map((e) => {
                                    return (
                                        <div key={ e._id } className="list-row">
                                            <span className="list-row-name" style={ selected && selected == e._id ? { color: "rgba(226, 119, 122, 1)" } : null } onClick={() => { onClickCallback(e._id) }}>{e.name}</span>
                                            {
                                                PERMIT != "student" && !e.description &&
                                                <button className="delete-button" onClick={() => { deleteButtonCallback(e._id) } }><TiDelete color="#e2777a" size={25} /></button>
                                            }
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