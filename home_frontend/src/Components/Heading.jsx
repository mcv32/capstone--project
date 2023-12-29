import React from "react";
import { NavLink, useNavigate } from "react-router-dom";
import Logo from "../static/CapstoneLogo.png"
import useAuth from "../Hooks/useAuth";


function Heading(){
    const { auth, setAuth } = useAuth();
    const navigate = useNavigate();
   
    const logout = async () => {
        setAuth({});
        navigate('/');
    }

    return(
        auth?.roles === "USER" || auth?.roles === "MANAGER" || auth?.roles === "ADMIN" ?
        <nav className="heading">
            <NavLink className="logo" to="/">
                <img src={Logo} alt="your logo"/>
            </NavLink>
            <ul >
                <NavLink className="Navlink" style={({ isActive }) => ({
                                                                        color: isActive ? 'white' : 'white',
                                                                        textDecoration: isActive ? 'underline' : 'none',
                                                                    })} to="/about">ABOUT</NavLink>
                <NavLink className="Navlink" style={({ isActive }) => ({
                                                                        color: isActive ? 'white' : 'white',
                                                                        textDecoration: isActive ? 'underline' : 'none',
                                                                    })} to="/contact">CONTACT US</NavLink>
                <NavLink className="Navlink" style={({ isActive }) => ({
                                                                        color: isActive ? 'white' : 'white',
                                                                        textDecoration: isActive ? 'underline' : 'none',
                                                                    })} to="/dashboard">MY DASHBOARD</NavLink>
                <button className="Navbutton" onClick={logout}>Logout</button>
            </ul> 
        </nav>
        :
        <nav className="heading">
        <NavLink className="logo" to="/">
            <img src={Logo} alt="your logo"/>
        </NavLink>
        <ul >
            <NavLink className="Navlink" style={({ isActive }) => ({
                                                                    color: isActive ? 'white' : 'white',
                                                                    textDecoration: isActive ? 'underline' : 'none',
                                                                })} to="#homeERD">ERD</NavLink>
            <NavLink className="Navlink" style={({ isActive }) => ({
                                                                    color: isActive ? 'white' : 'white',
                                                                    textDecoration: isActive ? 'underline' : 'none',
                                                                })} to="/contact">CONTACT US</NavLink>
        <NavLink className="Navbutton" to="/login">
            <button>Login</button>
        </NavLink>
        </ul> 
    </nav>
    );
}

export default Heading;
