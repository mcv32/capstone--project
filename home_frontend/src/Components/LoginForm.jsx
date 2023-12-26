import React, {useState, useRef, useEffect} from "react";
import useAuth from "../Hooks/useAuth";
import { useNavigate, useLocation } from "react-router-dom";
import { RiUserLine, RiMailLine, RiLockPasswordLine } from 'react-icons/ri';

import Axios from "axios";

function LoginForm(props){
    const [userIsRegistered, setUserIsRegistered] = useState(false);
    const { setAuth } = useAuth();

    const navigate = useNavigate();
    const location = useLocation();
    const from = location.state?.from?.pathname || "/dashboard";
    
    function handleClick(event){
        const checkValue = event.target.checked;
        if (checkValue != false){
            setUserIsRegistered(checkValue); 
        } else{
            setUserIsRegistered(checkValue);
        }
        console.log(userIsRegistered);
    }
     
    const urlLogin = "http://localhost:8080/authenticate";
    const [loginPayload, setLoginPayload] = useState({
        email: "",
        password: ""
    })

    
    const urlRegister = "http://localhost:8080/api/v1/registration";
    const [registerPayload, setRegisterPayload] = useState({
        firstName: "",
        lastName:"",
        email: "",
        password: "",
        confirmpass:""
    })

    function resetLoginPayload(){
        setLoginPayload({
            email: "",
            password: ""
        })
    }

    function resetRegisterPayload(){
        setRegisterPayload({
            firstName: "",
            lastName:"",
            email: "",
            password: "",
            confirmpass:""
        })
    }
    
    const [resMsg, setResMsg] = useState('');
    const [success, setSuccess] = useState(false);

    function resetResMsg(){
        setResMsg('');
    }

    const submit = async (cred) => {
        if (userIsRegistered === true){
            try {
            console.log(loginPayload);
            cred.preventDefault();
            const response = await Axios.post(urlLogin, {
                email: loginPayload.email,
                password: loginPayload.password
            })
                console.log(response);

                const accessToken = response?.data?.token;
                const roles = response?.data?.role;
                const email = loginPayload.email;
                const password = loginPayload.password;
                
                console.log(email);
                console.log(password);
                console.log(roles);
                console.log(accessToken);
                setAuth({email, password, roles, accessToken });
                resetLoginPayload();
                navigate(from, {replace:true});
            
            }catch (err){
                if (err.response.status === 400){
                    // console.log(err.response);
                    // console.log(err.response.data.errorDesc);
                    setResMsg(err.response.data.errorDesc);
                    setTimeout(resetResMsg, 5000);
                } else {
                    setResMsg("Unknown Error Occured. Account Not Created. Please Try Again");
                    setTimeout(resetResMsg, 5000);
                }
        }      

        } else {
            try {
                cred.preventDefault();
                const response = await Axios.post(urlRegister, {
                    firstName: registerPayload.firstName,
                    lastName: registerPayload.lastName,
                    email: registerPayload.email,
                    password: registerPayload.password
                })
                    setSuccess(true);
                    setResMsg("Successful Registration! Proceed to inbox to confirm email and enable login.");
                    resetRegisterPayload();
                    setTimeout(resetResMsg, 5000);
            } catch (err) {
                if (err.response?.status === 400){
                    // console.log(err.response);
                    // console.log(err.response.data.errorDesc);
                    setResMsg(err.response.data.errorDesc);
                    setTimeout(resetResMsg, 5000);
                } else {
                    setResMsg("Unknown Error Occured. Account Not Created. Please Try Again");
                    setTimeout(resetResMsg, 5000);
                }
        }      
    }
    }

    function handle(cred){
        if (userIsRegistered === true){
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

    // return(
    //     <div className="loginForm">
    //         <p className={resMsg ? "resmsg" : "offscreen"} aria-live="assertive">{resMsg}</p>
    //         <h3>{userIsRegistered ? "Login" : "Register"}</h3>
    //             <form className="form" onSubmit={(cred) => submit(cred)}>
    //             {!userIsRegistered && (
    //                 <input onChange={(cred) => handle(cred)} value={registerPayload.firstName} id ="firstName" type="text" placeholder="First Name" />
    //             )}
    //             {!userIsRegistered && (
    //                 <input onChange={(cred) => handle(cred)} value={registerPayload.lastName} id ="lastName" type="text" placeholder="Last Name" />
    //             )}
    //                 <input onChange={(cred) => handle(cred)} value={userIsRegistered ? loginPayload.email : registerPayload.email} id ="email" type="text" placeholder="Email" />
    //                 <input onChange={(cred) => handle(cred)} value={userIsRegistered ? loginPayload.password : registerPayload.password} id ="password" type="password" placeholder="Password" />
    //                 {!userIsRegistered && (
    //                 <input onChange={(cred) => handle(cred)} value={userIsRegistered ? loginPayload.confirmpass : registerPayload.confirmpass} id ="confirmpass" type="password" placeholder="Confirm Password" />
    //             )}
        
    //             <button type="submit">{userIsRegistered ? "Login" : "Register"}</button>
    //         </form>
    //         <div className="toggle">
    //             <label className="switch">
    //             <input id="toggleRegister" type="checkbox" onClick={handleClick} ></input>
    //             <span className="slider round"></span>
    //             </label>
    //             <span className = "label">{userIsRegistered? "New user?" : "Already have an account?"}</span>
    //         </div>
    //     </div>
    // );
    return (
        <div className="loginForm">
          <p className={resMsg ? "resmsg" : "offscreen"} aria-live="assertive">{resMsg}</p>
          <h3>{userIsRegistered ? "Login" : "Register"}</h3>
          <form className="form" onSubmit={(cred) => submit(cred)}>
            {!userIsRegistered && (
              <div className="input-group">
                <RiUserLine className="icon" />
                <input
                  onChange={(cred) => handle(cred)}
                  value={registerPayload.firstName}
                  id="firstName"
                  type="text"
                  placeholder="First Name"
                />
              </div>
            )}
            {!userIsRegistered && (
              <div className="input-group">
                <RiUserLine className="icon" />
                <input
                  onChange={(cred) => handle(cred)}
                  value={registerPayload.lastName}
                  id="lastName"
                  type="text"
                  placeholder="Last Name"
                />
              </div>
            )}
            <div className="input-group">
              <RiMailLine className="icon" />
              <input
                onChange={(cred) => handle(cred)}
                value={userIsRegistered ? loginPayload.email : registerPayload.email}
                id="email"
                type="text"
                placeholder="Email"
              />
            </div>
            <div className="input-group">
              <RiLockPasswordLine className="icon" />
              <input
                onChange={(cred) => handle(cred)}
                value={userIsRegistered ? loginPayload.password : registerPayload.password}
                id="password"
                type="password"
                placeholder="Password"
              />
            </div>
            {!userIsRegistered && (
              <div className="input-group">
                <RiLockPasswordLine className="icon" />
                <input
                  onChange={(cred) => handle(cred)}
                  value={userIsRegistered ? loginPayload.confirmpass : registerPayload.confirmpass}
                  id="confirmpass"
                  type="password"
                  placeholder="Confirm Password"
                />
              </div>
            )}
      
            <button type="submit">{userIsRegistered ? "Login" : "Register"}</button>
          </form>
          <div className="toggle">
            <label className="switch">
              <input id="toggleRegister" type="checkbox" onClick={handleClick}></input>
              <span className="slider round"></span>
            </label>
            <span className="label">{userIsRegistered ? "New user?" : "Already have an account?"}</span>
          </div>
        </div>
      );
}

export default LoginForm;