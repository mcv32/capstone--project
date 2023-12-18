import React, {useState} from "react";
import Login from "./Login";
import Toggle from "./Toggle";
import Dashboard from "./Dashboard";

function Body(){
    
    const [userIsRegistered, setUserIsRegistered] = useState(false);
    const [bodyState, setBodyState] = useState(false);
    
    function changeLoginView(event){
        const viewState = event.target.checked;
        if (viewState != false){
            setBodyState(viewState); 
        } else{
            setBodyState(viewState);
        }
    }
    
    function handleClick(event){
        const checkValue = event.target.checked;
        if (checkValue != false){
            setUserIsRegistered(checkValue); 
        } else{
            setUserIsRegistered(checkValue);
        }
    }
    
    function setBody(){
        if (bodyState === true){
            return (
                <div>
                    <Dashboard />
                </div>
            );
            
        } else {
            return (
                <div>
                    <Login isRegistered={userIsRegistered} />
                    <div className="toggle">
                        <label className="switch">
                        <input id="toggleRegister" type="checkbox" onClick={handleClick}></input>
                        <span className="slider round"></span>
                        </label>
                        <span className = "label">Existing User?</span>
                    </div>
                </div>
            );
        }
    }

    return(
        <div className="body">
            <div className="developer">
                <label className="switch">
                <input id="toggleRegister" type="checkbox" onClick={changeLoginView}></input>
                <span className="slider round"></span>
                </label>
                <span className="label">Change Developer View</span>
            </div>
            {setBody()}
        </div>
    );
}

export default Body;