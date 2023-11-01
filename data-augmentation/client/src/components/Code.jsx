import React, { useEffect } from "react";
import Prism from "prismjs";

export default function Code({ label, identifier, code, language }) {
    useEffect(() => {
        Prism.highlightAll();
    }, []);
    return (
        <>
            <div className="label">
                <label>{label}</label>
            </div>
            <div id={identifier}>
                 <pre className="line-numbers">
                <code className={`language-${language}`}>{code}</code>
            </pre>
            </div>
        </>
    );
}