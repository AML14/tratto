import React, {useState} from 'react';

export default function AddConditionModalContent({ identifier, modalUpdateState }) {

    const [formData, setFormData] = useState({
        description: "",
        condition: "",
        oracle: "",
        exception: ""
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
        modalUpdateState({
            ...formData,
            [name]: value,
        });
    };

    return (
        <div id="modal-jdc" className="modal-jdc-container">
            <h2 className="modal-title">{`Add ${identifier.charAt(0).toUpperCase() + identifier.slice(1)}`}-condition</h2>
            <div className="form-container">
                <form className="form-container">
                    <div className="add-condition-input-container">
                        <label
                            htmlFor="description"
                            className="add-condition-label"
                        >Description:</label>
                        <input
                            type="text"
                            id="description"
                            name="description"
                            value={formData.description}
                            onChange={handleInputChange}
                            className="add-condition-input"
                        />
                    </div>
                    <div className="add-condition-input-container">
                        <label
                            htmlFor="condition"
                            className="add-condition-label"
                        >Condition:</label>
                        <input
                            type="text"
                            id="condition"
                            name="condition"
                            value={formData.condition}
                            onChange={handleInputChange}
                            className="add-condition-input"
                        />
                    </div>
                    {
                        identifier == "post" &&
                        <div className="add-condition-input-container">
                            <label
                                htmlFor="oracle"
                                className="add-condition-label"
                            >Oracle:</label>
                            <input
                                type="text"
                                id="oracle"
                                name="oracle"
                                value={formData.oracle}
                                onChange={handleInputChange}
                                className="add-condition-input"
                            />
                        </div>
                    }
                    {
                        identifier == "throws" &&
                        <div className="add-condition-input-container">
                            <label
                                htmlFor="exception"
                                className="add-condition-label"
                            >Exception:</label>
                            <input
                                type="text"
                                id="exception"
                                name="exception"
                                value={formData.exception}
                                onChange={handleInputChange}
                                className="add-condition-input"
                            />
                        </div>
                    }
                </form>
            </div>
        </div>
    )
}