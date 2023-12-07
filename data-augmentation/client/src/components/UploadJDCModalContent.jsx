import React, {useEffect, useRef, useState} from 'react';

export default function UploadJDCModalContent({ repositories, modalUpdateState }) {
    const [newRepository, setNewRepository] = useState(null);
    const [selectValue, setSelectValue] = useState(repositories.length > 0 ? 0 : null);
    const [files, setFiles] = useState([]);
    const [radioChoice, setRadioChoice] = useState("new");
    const fileInputRef = useRef(null);

    useEffect(() => {
        if (repositories.length > 0) {
            setSelectValue(0);

            if (radioChoice == "existing") {
                modalUpdateState({
                repository: repositories[0],
                files: files
                })
            }
        } else {
            setSelectValue(null);
            if (radioChoice == "existing") {
                modalUpdateState({
                    repository: null,
                    files: files
                })
            }
        }
    }, [repositories]);

    const repositoryFileUpload = (event) => {
        const file = event.target.files.length > 0 ? event.target.files[0] : null;
        if (file !== null && (file.type === 'application/json' || file.name && file.name.endsWith('.json'))) {
            const reader = new FileReader();
            reader.onload = (e) => {
                const fileContent = e.target.result;
                // Parse the JSON content into a JavaScript object
                try {
                    const repository = JSON.parse(fileContent);
                    // Check if the object has the required attributes
                    if (
                        'projectName' in repository &&
                        'githubLink' in repository &&
                        'commit' in repository &&
                        'rootPathList' in repository &&
                        'srcPathList' in repository
                    ) {
                        setNewRepository(repository);
                        modalUpdateState({
                            repository: repository,
                            files: files
                        });
                    } else {
                        console.log("The uploaded file does not have all the required attributes.")
                    }
                } catch (error) {
                    console.log("File could not be parsed as JSON.");
                }
            };
            reader.readAsText(file);
        } else {
            setNewRepository(null);
            modalUpdateState({
                repository: null,
                files: files
            });
            console.log(`File to upload is not a JSON file.`);
        }
    }

    const filesUpload = (event) => {
        const selectedFiles = Object.values(event.target.files);
        setFiles(selectedFiles);
        modalUpdateState({
            repository: newRepository,
            files: selectedFiles
        });
    }

    return (
        <div id="modal-jdc" className="modal-jdc-container">
            <h2 className="modal-title">Upload JDoctor Conditions</h2>
            <div id="repository-option-container">
                <div className="radio-option">
                    <h3>Repository</h3>
                    <div className="radio-option-input-label-container">
                        <input
                            type="radio"
                            className="radio-input"
                            name="repository-radio"
                            value="new"
                            defaultChecked={radioChoice == "new"}
                            onClick={() => {
                                fileInputRef.current.value = "";
                                setNewRepository(null);
                                setRadioChoice("new");
                                modalUpdateState({
                                    repository: null,
                                    files: files
                                })
                            }}
                        />
                        <label
                            className="radio-label"
                            style={ radioChoice == "new" ? { "opacity": 1 } : { "opacity": 0.5 }}
                        >New Repository</label>
                    </div>
                    <input
                        type="file"
                        name="repository-file"
                        id="repository-file"
                        className="input-file"
                        accept=".json"
                        ref={fileInputRef}
                        onChange={(event) => { repositoryFileUpload(event); }}
                        disabled={radioChoice == "existing"}
                        style={ radioChoice == "new" ? { "opacity": 1 } : { "opacity": 0.5 }}
                    />
                </div>
                <div className="radio-option">
                    <div className="radio-option-input-label-container">
                        <input
                            type="radio"
                            className="radio-input"
                            name="repository-radio"
                            value="existing"
                            defaultChecked={radioChoice == "existing"}
                            onClick={() => {
                                fileInputRef.current.value="";
                                setNewRepository(repositories[selectValue]);
                                modalUpdateState({
                                    repository: repositories[selectValue],
                                    files: files
                                })
                                setRadioChoice("existing");
                            }}
                        />
                        <label
                            className="radio-label"
                            style={ radioChoice == "existing" ? { "opacity": 1 } : { "opacity": 0.5 }}
                        >Existing Repository</label>
                    </div>
                    <select
                        name="repositories"
                        id="repositories-select"
                        className="select-repository"
                        disabled={radioChoice == "new"}
                        value={selectValue || ""}
                        onChange={(event) => {
                            const idx = event.target.value[0];
                            setNewRepository(repositories[idx]);
                            setSelectValue(idx);
                            modalUpdateState({
                                repository: repositories[idx],
                                files: files
                            });
                        }}
                    >
                        {
                            repositories.map( (r, idx) => {
                                return <option
                                    key={`option-${r._id}`}
                                    value={idx}
                                >{r.projectName}</option>
                            })
                        }
                    </select>
                </div>
            </div>
            <div id="jdc-options-container">
                <h3>JDoctor Conditions</h3>
                <p className="jdc-p">Upload files</p>
                <input
                    type="file"
                    className="input-file"
                    name="jdc-files"
                    id="jdc-files"
                    accept=".json"
                    onChange={(event) => {
                        filesUpload(event);
                    }}
                    multiple={true}
                />
            </div>
        </div>
    )
}