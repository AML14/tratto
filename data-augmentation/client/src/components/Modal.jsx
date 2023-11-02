import React from 'react';
import ReactDOM from "react-dom";

export default function Modal({ open, children, onClose }) {
    if (!open) {
        return null;
    }
    return ReactDOM.createPortal(
        <>
            <div className="modal-background"></div>
            <div className="modal-content">
                {children}
                <button onClick={onClose}>Close</button>
            </div>
        </>,
        document.getElementById("portal")
    )
}