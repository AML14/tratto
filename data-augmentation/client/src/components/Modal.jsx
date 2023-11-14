import React, {useState} from 'react';
import ReactDOM from "react-dom";

export default function Modal({ open, children, onClose, onConfirm, modalState, disableProperties, confirmButtonLabel }) {
    if (!open) {
        return null;
    }

    const [returnState, setReturnState] = useState(modalState);

    const modalUpdateState = (modalReturnState) => {
        setReturnState(modalReturnState);
    }

    const disableConfirmButton = () => {
        if (disableProperties != null) {
            for (const [property, check] of Object.entries(disableProperties)) {
                if (check(returnState[property])) {
                    return true;
                }
            }
        }
        return false;
    }

    return ReactDOM.createPortal(
        <>
            <div className="modal-background"></div>
            <div className="modal-content">
                {React.cloneElement(children, { modalUpdateState: modalUpdateState })}
                <div className="modal-button-container">
                    <button
                        onClick={() => {
                            onConfirm(returnState);
                            onClose();
                        }}
                        disabled={disableConfirmButton()}
                        className="modal-button confirm-button"
                        style={ disableConfirmButton() ? { opacity: 0.5, cursor: "not-allowed" } : {} }
                    >{confirmButtonLabel}</button>
                    <button
                        className="modal-button close-button"
                        onClick={onClose}
                    >Close</button>
                </div>
            </div>
        </>,
        document.getElementById("portal")
    )
}