import React from "react";
import { NavLink, Link } from "react-router-dom";
import Logo from "../static/CapstoneLogo.png"


function Heading(){
   

    return(
        <nav className="heading">
            <NavLink className="logo" to="/">
                <img src={Logo} alt="your logo"/>
            </NavLink>
            <ul >
                <NavLink classname="Navlink" style={({ isActive }) => ({
                                                                        color: isActive ? 'white' : 'white',
                                                                        textDecoration: isActive ? 'underline' : 'none',
                                                                    })} to="/about">ABOUT</NavLink>
                <NavLink classname="Navlink" style={({ isActive }) => ({
                                                                        color: isActive ? 'white' : 'white',
                                                                        textDecoration: isActive ? 'underline' : 'none',
                                                                    })} to="/contact">CONTACT US</NavLink>
            <NavLink classname="Navbutton" to="/login">
                <button>Login</button>
            </NavLink>
            </ul>
            
        </nav>
    );
}

export default Heading;
