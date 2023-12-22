import React, {useState} from "react";
import NewProperty from "./NewProperty";

function Properties(){
    
    const [isPopped, setPopover] = useState(false);

    function handlePop(){
            setPopover(!isPopped);
    }
    
    return(
        <div className="Properties">
            <div className="propertiesHeader">
                <h2>PROPERTIES</h2>
                <button className="openpopover" onClick={handlePop}>+</button>
            </div>
            <table>
                <tr>
                    <th>Status</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Account</th>
                </tr>
                <tr>
                    <td>Occupied</td>
                    <td>123 Main St. Unit 2B</td>
                    <td>Bridgewater</td>
                    <td>NJ</td>
                    <td>John Smith</td>
                </tr>
                <tr>
                    <td>Unoccupied</td>
                    <td>500 E. West Street</td>
                    <td>Plainfield</td>
                    <td>NJ</td>
                    <td>Henry March</td>
                </tr>
                <tr>
                    <td>Unoccupied</td>
                    <td>525 Johnsen Ave Apt 2A</td>
                    <td>New York</td>
                    <td>NY</td>
                    <td>George Washington</td>
                </tr>
            </table>
            <div className={isPopped ? "proppopoverOpen" :"proppopoverClosed"} >
                <div className="closepopover">
                    <button onClick={handlePop}>X</button>
                </div>
                <div>
                    <NewProperty/>
                </div>
            </div>
    </div>
    );
}

export default Properties;