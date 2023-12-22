import React from "react";

function Toggle(props){
   
    return(
        <div className="toggle">
            <label className="switch">
                <input id="toggleRegister" type="checkbox" value="true" onClick={props.onClick}></input>
                <span className="slider round"></span>
            </label>
        </div>
    );
}

export default Toggle;