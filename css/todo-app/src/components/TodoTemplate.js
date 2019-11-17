import React from "react";
import "./TodoTemplate.scss";

export const TodoTemplate = ({ children }) => {
    return (
        <div>
            <div className="TodoTemplate">
                <div className="app-title">일정관리</div>
                <div className="content">{children}</div>
            </div>
        </div>
    );
};
