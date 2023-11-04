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
        console.log(returnState)
        const selectedFiles = returnState.files;
        const repositoryClasses = [];

        for (let i = 0; i < selectedFiles.length; i++) {
            const file = selectedFiles[i];
            if (file.type === 'application/json' || file.name.endsWith('.json')) {
                const reader = new FileReader();

                reader.onload = (e) => {
                    // The file content can be accessed as e.target.result
                    const fileContent = e.target.result;
                    // Parse the JSON content into a JavaScript object
                    try {
                        const repositoryClass = JSON.parse(fileContent);
                        repositoryClasses.push(repositoryClass);
                    } catch (error) {
                        console.log(error);
                    }
                };
                reader.readAsText(file);
            } else {
                console.log(`File ${file.name} is not a JSON file and will be skipped.`);
            }
        }
        return {
            repository: returnState.repository,
            repositoryClasses: repositoryClasses
        }
    }

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
                        className="modal-button" 
                        onClick={() => {
                            const processedObj = processFiles();
                            onClose();
                            onUpload(processedObj);
                        }} 
                        disabled={returnState.repository == null}
                    >Upload</button>
                    <button className="modal-button" onClick={onClose} >Close</button>
                </div>
            </div>
        </>,
        document.getElementById("portal")
    )
}