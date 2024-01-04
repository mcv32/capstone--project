import React from "react";
import { NavLink, Link } from "react-router-dom";

function HomeIDcard({properties}){

    console.log("ID Card Properties", properties);

    return(
        <div>
            {Object.keys(properties).map((i) => (
                <div className="homeIDBlock">
                    <div className="homeIDLeft">
                        <img src="https://hips.hearstapps.com/hmg-prod/images/over-the-top-apartments-main-1512422328.jpg?crop=1.00xw:0.502xh;0,0.263xh&resize=1200:*"/>
                    </div>
                    
                    {/* M: moved address and apartment / state and zip on the same line */}
                    <div className="homeIDRight">
                        <h2>{properties[i]?.name}</h2>
                        <div className="homeIDaddress">
                            <p>{properties[i]?.address_street}, {properties[i]?.address_line_2}</p>
                            <p>{properties[i]?.city}</p>
                            <p>{properties[i]?.state}, {properties[i]?.zip}</p>
                        </div>
                    </div>
                </div>
            ))}   
        </div>
    );
}

export default HomeIDcard;