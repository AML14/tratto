import React, { useState } from 'react';

export default function CircleMenuButton({ label, children, onClick }) {
    const [showLabel, setShowLabel] = useState(false);

    return (
        <div className="add-button-container">
            <button
                className="add-button"
                onClick={() => {
                    onClick();
                }}
                onMouseOver={() => {
                    setShowLabel(true);
                }}
                onMouseOut={() => {
                    setShowLabel(false);
                }}
            >
                {children}
            </button>
            <span className="button-label" style={ showLabel ? { "opacity": 1 } : { "opacity": 0 } }>{label}</span>
        </div>
    );
}