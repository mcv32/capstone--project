import React from "react";
import { NavLink, Link } from "react-router-dom";


function Heading(){
   

    return(
        <nav className="heading">
            <NavLink className="logo" to="/">
                <img src="https://4m4you.com/wp-content/uploads/2020/06/logo-placeholder.png" alt="your logo"/>
            </NavLink>
            <ul >
                <NavLink classname="Navlink" style={({ isActive }) => ({
                                                                        color: isActive ? 'white' : 'white',
                                                                        textDecoration: isActive ? 'wavy underline' : 'none',
                                                                    })} to="/about">ABOUT</NavLink>
                <NavLink classname="Navlink" style={({ isActive }) => ({
                                                                        color: isActive ? 'white' : 'white',
                                                                        textDecoration: isActive ? 'wavy underline' : 'none',
                                                                    })} to="/contact">CONTACT US</NavLink>
            <NavLink classname="Navbutton" to="/login">
                <button>Login</button>
            </NavLink>
            </ul>
            
        </nav>
    );
}

export default Heading;
