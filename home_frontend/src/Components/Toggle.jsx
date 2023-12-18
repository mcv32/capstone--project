import React from "react";

function Toggle(){
    
    function handleClick(){
        console.log("Clicked");
    }
    
    return(
        <div className="toggle">
            <label className="switch">
                <input id="toggleRegister" type="checkbox" value="true" onClick={handleClick}></input>
                <span className="slider round"></span>
            </label>
        </div>
    );
}

export default Toggle;