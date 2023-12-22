import React from "react";
import { NavLink, Link } from "react-router-dom";

function AccountID(){
    return(
        <div className="accountBlock">
            <div className="accountLeft">
                <img src="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg"/>
                <Link>Edit Account</Link>
            </div>
            <div className="accountRight">
                <h2>MANAGER</h2>
                <h3>Cody Phelan</h3>
                <p>Contact Information</p>
                <p>908-685-1182</p>
                <p>cody@fiserv.com</p>
            </div>
        </div>
    );
}

export default AccountID;