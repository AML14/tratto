import React, {useState} from 'react';

export default function AddConditionForm({ identifier, addCondition, disableProperties }) {

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
    };

    const disableConfirmButton = () => {
        if (disableProperties != null) {
            for (const [property, check] of Object.entries(disableProperties)) {
                if (check(formData[property])) {
                    return true;
                }
            }
        }
        return false;
    }

    return (
        <div id="add-condition-jdc" className="add-condition-jdc-container">
            <div className="external-form-container">
                <form className="form-container">
                    <div className="add-condition-input-container">
                        <label
                            htmlFor="description"
                            className="add-condition-label"
                        >Javadoc Tag:</label>
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
                <button
                    disabled={ disableConfirmButton() }
                    className="add-condition-button"
                    style={ disableConfirmButton() ? { opacity: 0.5, cursor: "not-allowed" } : {} }
                    onClick={ () => {
                        addCondition(formData);
                        setFormData({
                            description: "",
                            condition: "",
                            oracle: "",
                            exception: ""
                        })
                    } }
                >Add</button>
            </div>
        </div>
    )
}