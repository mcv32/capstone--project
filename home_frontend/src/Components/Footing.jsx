import React from "react";
import { NavLink, useLocation } from "react-router-dom";
import Logo from "../static/CapstoneLogo.png";
import '../App.css';

function Footing() {
        const location = useLocation();
        // M: got rid of footer on admin page
        const isLoginPage = location.pathname === '/login';
        const isDashboardPage = location.pathname === '/dashboard';
        const isAdminPage = location.pathname === '/admin';
      
        // If it's the login page, don't render the footer
        if (isLoginPage || isDashboardPage || isAdminPage) {
          return null;
        }

        const isTransparent = isLoginPage || isDashboardPage || isAdminPage;
        const footerClassName = `footing${isTransparent ? ' transparent' : ''}`

  return (
    <footer className={footerClassName}>
      <div className="footingTopRow"> 
        <div className="footingLogo">
          <h2>You're Home With Us</h2>
          <img src={Logo} alt="" />
        </div>
        <ul>
          <NavLink
            className="Navlink"
            style={({ isActive }) => ({
              color: isActive ? "white" : "white",
              textDecoration: isActive ? "underline" : "none",
            })}
            to="/about"
          >
            ABOUT
          </NavLink>
          <NavLink
            className="Navlink"
            style={({ isActive }) => ({
              color: isActive ? "white" : "white",
              textDecoration: isActive ? "underline" : "none",
            })}
            to="/contact"
          >
            CONTACT US
          </NavLink>
        </ul>
      </div>
      <p>@Copyright {new Date().getUTCFullYear()}</p>
      {/* Add the Contact Us title in the center */}
      <div className="centered-title">
        <h3>Contact Us</h3>
      </div>
    </footer>
  );
}

export default Footing;