import React, {useState} from "react";
import NewProperty from "./NewProperty";

function Properties(){
    
    const [newPopped, setNewPopover] = useState(false);

    function handleNewPop(){
            setNewPopover(!newPopped);
    }

    const [propPopped, setPropPopover] = useState(false);

    function handlePropPop(){
            setPropPopover(!propPopped);
    }
    
    return(
        <div className="Properties">
            <div className="propertiesHeader">
                <h2>PROPERTIES</h2>
                <button className="openpopover" onClick={handlePropPop}>+</button>
            </div>
            <table>
                <tr>
                    <th >Status</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Account</th>
                </tr>
                <tr onClick={handlePropPop}>
                    <td>Occupied</td>
                    <td>123 Main St. Unit 2B</td>
                    <td>Bridgewater</td>
                    <td>NJ</td>
                    <td>John Smith</td>
                </tr>
                <tr onClick={handlePropPop}>
                    <td>Unoccupied</td>
                    <td>500 E. West Street</td>
                    <td>Plainfield</td>
                    <td>NJ</td>
                    <td>Henry March</td>
                </tr>
                <tr onClick={handlePropPop}>
                    <td>Unoccupied</td>
                    <td>525 Johnsen Ave Apt 2A</td>
                    <td>New York</td>
                    <td>NY</td>
                    <td>George Washington</td>
                </tr>
            </table>
            <div className={newPopped ? "proppopoverOpen" :"proppopoverClosed"} >
                <div className="closepopover">
                    <button onClick={handleNewPop}>X</button>
                </div>
                <div>
                    <NewProperty/>
                </div>
            </div>
            <div className={propPopped ? "recordOpen" :"offscreen"} >
                <div className="closeRecord">
                    <button onClick={handlePropPop}>X</button>
                </div>
                <div>
                    <h1 style={{color:"black"}}>Property Details View</h1>
                </div>
            </div>
    </div>
    );
}

export default Properties;