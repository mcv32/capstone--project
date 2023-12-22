import React from "react";
import { NavLink } from "react-router-dom";
import Logo from "../static/CapstoneLogo.png"

function Footing(){
    return(
        <footer className= "footing">
            <div className="footingTopRow">
                <div className="footingLogo">
                    <h2>You're Home With Us</h2>
                    <img src={Logo} alt="" />
                </div>
                <ul >
                    <NavLink classname="Navlink" style={({ isActive }) => ({
                                                                            color: isActive ? 'white' : 'white',
                                                                            textDecoration: isActive ? 'underline' : 'none',
                                                                        })} to="/about">ABOUT</NavLink>
                    <NavLink classname="Navlink" style={({ isActive }) => ({
                                                                            color: isActive ? 'white' : 'white',
                                                                            textDecoration: isActive ? 'underline' : 'none',
                                                                        })} to="/contact">CONTACT US</NavLink>
                </ul>
            </div>
            <p>Copyright {new Date().getUTCFullYear()}</p>
        </footer>
    );
}

export default Footing;