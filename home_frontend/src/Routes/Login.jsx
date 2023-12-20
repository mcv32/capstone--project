import React, {useState} from "react";
import LoginForm from "../Components/LoginForm";
import { Link } from "react-router-dom";

function Login(){
    const [userIsRegistered, setUserIsRegistered] = useState(false);
    
    function handleClick(event){
        const checkValue = event.target.checked;
        if (checkValue != false){
            setUserIsRegistered(checkValue); 
        } else{
            setUserIsRegistered(checkValue);
        }
    }

    return(
        <section className="bodySection">
            <LoginForm isRegistered={userIsRegistered} />
            <div className="toggle">
                <label className="switch">
                <input id="toggleRegister" type="checkbox" onClick={handleClick}></input>
                <span className="slider round"></span>
                </label>
                <span className = "label">Existing User?</span>
            </div>
            <Link to="/dashboard">Dev Link to Dashboard</Link>
        </section>
    );
}

export default Login;