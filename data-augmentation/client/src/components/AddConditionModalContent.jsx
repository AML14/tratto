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
                <form>
                    <div>
                        <label htmlFor="description">Description:</label>
                        <input
                            type="text"
                            id="description"
                            name="description"
                            value={formData.description}
                            onChange={handleInputChange}
                        />
                    </div>
                    <div>
                        <label htmlFor="condition">Condition:</label>
                        <input
                            type="text"
                            id="condition"
                            name="condition"
                            value={formData.condition}
                            onChange={handleInputChange}
                        />
                    </div>
                    {
                        identifier == "post" &&
                        <div>
                            <label htmlFor="oracle">Oracle:</label>
                            <input
                                type="text"
                                id="oracle"
                                name="oracle"
                                value={formData.oracle}
                                onChange={handleInputChange}
                            />
                        </div>
                    }
                    {
                        identifier == "throws" &&
                        <div>
                            <label htmlFor="exception">Exception:</label>
                            <input
                                type="text"
                                id="exception"
                                name="exception"
                                value={formData.exception}
                                onChange={handleInputChange}
                            />
                        </div>
                    }
                </form>
            </div>
        </div>
    )
}