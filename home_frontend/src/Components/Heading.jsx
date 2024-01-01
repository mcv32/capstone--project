import React from "react";
import { NavLink, useNavigate } from "react-router-dom";
import Logo from "../static/CapstoneLogo.png"
import useAuth from "../Hooks/useAuth";
import ScrollToAnchor from "./ScrollToAnchor";


function Heading(){
    const { auth, setAuth } = useAuth();
    const navigate = useNavigate();
   
    const logout = async () => {
        setAuth({});
        navigate('/');
    }

    return(
        <nav className="heading">
            <ScrollToAnchor/>
            <NavLink className="logo" to="/">
                <img src={Logo} alt="your logo"/>
            </NavLink>
            <ul >
                <NavLink className="Navlink" style={({ isActive }) => ({
                                                                        color: isActive ? 'white' : 'white',
                                                                        textDecoration: isActive ? 'underline' : 'none',
                                                                    })} to="/#homeDemo1">DEMO 1</NavLink>
                <NavLink className="Navlink" style={({ isActive }) => ({
                                                                        color: isActive ? 'white' : 'white',
                                                                        textDecoration: isActive ? 'underline' : 'none',
                                                                    })} to="/#homeFeatures">FEATURES</NavLink>
                <NavLink className="Navlink" style={({ isActive }) => ({
                                                                        color: isActive ? 'white' : 'white',
                                                                        textDecoration: isActive ? 'underline' : 'none',
                                                                    })} to="/#homeTeam">OUR TEAM</NavLink>
                <NavLink className="Navlink" style={({ isActive }) => ({
                                                                        color: isActive ? 'white' : 'white',
                                                                        textDecoration: isActive ? 'underline' : 'none',
                                                                    })} to="/#homeTechStack">TECH STACK</NavLink>
                <NavLink className="Navlink" style={({ isActive }) => ({
                                                                        color: isActive ? 'white' : 'white',
                                                                        textDecoration: isActive ? 'underline' : 'none',
                                                                    })} to="/#homeDemo2">DEMO 2</NavLink>
                <NavLink className="Navlink" style={({ isActive }) => ({
                                                                        color: isActive ? 'white' : 'white',
                                                                        textDecoration: isActive ? 'underline' : 'none',
                                                                    })} to="/#homeERD">ERD</NavLink>
                <NavLink className="Navlink" style={({ isActive }) => ({
                                                                        color: isActive ? 'white' : 'white',
                                                                        textDecoration: isActive ? 'underline' : 'none',
                                                                    })} to="/#homeDemo3">DEMO 3</NavLink>
                {auth?.roles === "USER" || auth?.roles === "MANAGER" || auth?.roles === "ADMIN" ? 
                <NavLink className="Navlink" style={({ isActive }) => ({
                                                                        color: isActive ? 'white' : 'white',
                                                                        textDecoration: isActive ? 'underline' : 'none',
                                                                    })} to="/dashboard">MY DASHBOARD</NavLink>
                    : null}
                {auth?.roles === "USER" || auth?.roles === "MANAGER" || auth?.roles === "ADMIN" ?
                    <button className="Navbutton" onClick={logout}>Logout</button>
                    :<NavLink className="Navbutton" to="/login">
                        <button className="Navbutton">Login</button>
                    </NavLink>}
            </ul> 
        </nav>
    );
}

export default Heading;
