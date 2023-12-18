import React, {useState} from "react";
import Axios from "axios";


function Login(props){
     
    const urlLogin = "http://localhost:8080/login";
    const [loginPayload, setLoginPayload] = useState({
        username: "",
        password: "",
        confirmpass: null
    })

    
    const urlRegister = "";
    const [registerPayload, setRegisterPayload] = useState({
        username: "",
        password: "",
        confirmpass: ""
    })

    function submit(cred){
        if (props.isRegistered === true){
            cred.preventDefault();
            Axios.post(urlLogin, {
                username: loginPayload.username,
                password: loginPayload.password            
            })
            .then (res => {
                console.log(res.loginPayload);
            })
            .then (res => {
                window.location.href="http://localhost:8080/";
            })

        } else {
            cred.preventDefault();
            Axios.post(urlRegister, {
                username: registerPayload.username,
                password: registerPayload.password,    
                confirmpass: registerPayload.confirmpass        
            })
            .then (res => {
                console.log(res.registerPayload);
            })
            .then (res => {
                window.location.href="http://localhost:8080/";
            })

        }
    }

    function handle(cred){
        if (props.isRegistered === true){
            const newLoginPayload = {... loginPayload}
            newLoginPayload[cred.target.id] = cred.target.value
            setLoginPayload(newLoginPayload)
            console.log(newLoginPayload)
        } else {
            const newRegisterPayload = {... registerPayload}
            newRegisterPayload[cred.target.id] = cred.target.value
            setRegisterPayload(newRegisterPayload)
            console.log(newRegisterPayload)
        }
    }

    return(
        <div>
            <h3>{props.isRegistered ? "Login" : "Register"}</h3>
                <form className="form" onSubmit={(cred) => submit(cred)}>
                    <input onChange={(cred) => handle(cred)} value={props.isRegistered ? loginPayload.username : registerPayload.username} id ="username" type="text" placeholder="Email" />
                    <input onChange={(cred) => handle(cred)} value={props.isRegistered ? loginPayload.password : registerPayload.password} id ="password" type="password" placeholder="Password" />
                    {!props.isRegistered && (
                    <input onChange={(cred) => handle(cred)} value={props.isRegistered ? loginPayload.confirmpass : registerPayload.confirmpass} id ="confirmpass" type="password" placeholder="Confirm Password" />
                )}
        
                <button type="submit">{props.isRegistered ? "Login" : "Register"}</button>
            </form>
        </div>
    );
}

export default Login;