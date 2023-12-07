import React, {useEffect, useState} from "react";
import Prism from "prismjs";
import {FiMinusSquare, FiPlusSquare} from "react-icons/fi";

export default function Code({ label, identifier, code, language, defaultExpand = false }) {

    const [expand, setExpand] = useState(defaultExpand);

    useEffect(() => {
        Prism.highlightAll();
    }, [expand, code]);
    return (
        <>
            {
                label != null ?
                    <div className="code-label">
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
                        <h2>{label}</h2>
                    </div>
                :
                    null
            }
            {
                expand ?
                    <div id={identifier}>
                        <pre className="line-numbers">
                            <code className={`language-${language}`}>{code}</code>
                        </pre>
                    </div>
                :
                    null
            }
        </>
    );
}