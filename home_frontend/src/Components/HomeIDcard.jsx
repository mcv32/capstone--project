import React from "react";
import { NavLink, Link } from "react-router-dom";

function HomeIDcard(){
    return(
        <div className="homeIDBlock">
            <div className="homeIDLeft">
                <img src="https://hips.hearstapps.com/hmg-prod/images/over-the-top-apartments-main-1512422328.jpg?crop=1.00xw:0.502xh;0,0.263xh&resize=1200:*"/>
            </div>
            <div className="homeIDRight">
                <h2>YOUR HOME</h2>
                <h3>123 Main St.</h3>
                <p>Apt 2B</p>
                <div className="homeIDaddress">
                    <p>Bridgewater</p>
                    <p>NJ</p>
                    <p>08807</p>
                </div>
            </div>
        </div>
    );
}

export default HomeIDcard;