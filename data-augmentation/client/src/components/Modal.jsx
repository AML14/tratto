import React, {useState} from 'react';
import ReactDOM from "react-dom";

export default function Modal({ open, children, onClose, onUpload }) {
    if (!open) {
        return null;
    }

    const [returnState, setReturnState] = useState({
        repository: null,
        repositoryClasses: []
    });

    const processFiles = () => {
        const selectedFiles = returnState.files;
        const repositoryClasses = [];

        const readFile = (file) => {
            return new Promise((resolve, reject) => {
                if (file.type === 'application/json' || file.name.endsWith('.json')) {
                    const reader = new FileReader();

                    reader.onload = (e) => {
                        const fileContent = e.target.result;
                        try {
                            const repositoryClass = JSON.parse(fileContent);
                            resolve(repositoryClass);
                        } catch (error) {
                            reject(error);
                        }
                    };
                    reader.readAsText(file);
                } else {
                    resolve(null); // Skip non-JSON files
                }
            });
        };

        const readAllFiles = () => {
            const promises = selectedFiles.map((file) => readFile(file));
            return Promise.all(promises);
        };

        return readAllFiles()
            .then((results) => {
                results.forEach((repositoryClass) => {
                    if (repositoryClass !== null) {
                        repositoryClasses.push(repositoryClass);
                    }
                });

                return {
                    repository: returnState.repository,
                    repositoryClasses: repositoryClasses,
                };
            })
            .catch((error) => {
                console.error(error);
                return {
                    repository: returnState.repository,
                    repositoryClasses: repositoryClasses,
                };
            });
    };

    const modalUpdateState = (modalReturnState) => {
        setReturnState(modalReturnState);
    }

    return ReactDOM.createPortal(
        <>
            <div className="modal-background"></div>
            <div className="modal-content">
                {React.cloneElement(children, { modalUpdateState: modalUpdateState })}
                <div className="modal-button-container">
                    <button
                        onClick={async () => {
                            processFiles().then((processedObj) => {
                                onClose();
                                onUpload(processedObj);
                            });
                        }} 
                        disabled={returnState.repository == null}
                        className="modal-button confirm-button"
                        style={ returnState.repository == null ? { opacity: 0.5, cursor: "not-allowed" } : {} }
                    >Upload</button>
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