import React, {useState} from "react";
import NewProperty from "./NewProperty";
import DetailsLedger from "./DetailsLedger";

function Properties(){
    
    const [newPopped, setNewPopover] = useState(false);

    function handleNewPop(){
            setNewPopover(!newPopped);
    }

    const [propPopped, setPropPopover] = useState(false);

    function handlePropPop(){
            setPropPopover(!propPopped);
    }

    const [isLedgPop, setLedgPop] = useState(false);

    function handleLedgPop(){
        setLedgPop(!isLedgPop);
    }

    const [isNewLedgPop, setNewLedgPop] = useState(false);

    function handleNewLedgPop(){
        setNewLedgPop(!isNewLedgPop);
    }
    
    return(
        <div>
        <div className="propertiesHeader">
          <h2>PROPERTIES</h2>
          <button className="openpopover" onClick={handleNewPop}>+</button>
        </div>
        <div className="Properties">
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
            <div className={propPopped ? "propertyDetailsOpen" :"offscreen"} >
                <div className="propertyDetailsBackground">
                    <div className="propertyDetails">
                    <h1>Property Details</h1>
                    <div class="closeRecord">
                        <button onClick={handlePropPop}>X</button>
                    </div>
                        <img src="https://hips.hearstapps.com/hmg-prod/images/over-the-top-apartments-main-1512422328.jpg?crop=1.00xw:0.502xh;0,0.263xh&resize=1200:*"/>
                        <form typeof="submit">
                            <div>
                                <label >Thumbnail URL</label>
                                <input type="url" name="" id="" value="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg" placeholder="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg"/>
                            </div>
                            <div>
                                <label >Property Name</label>
                                <input type="text" name="" id="" value="East Bay Apartments" placeholder="East Bay Apartments"/>
                            </div>
                            <div>
                                <label >Street Address</label>
                                <input type="text" name="" id="" value="123 Main St." placeholder="Street Address"/>
                                <input type="text" name="" id="" value="Apt 2B" placeholder="Apt 2B"/>
                            </div>
                            <div>
                                <label >City</label>
                                <input type="text" name="" id="" value="Bridgewater" placeholder="Bridgewater"/>
                            </div>
                            <div>
                                <label >State</label>
                                <input type="text" name="" id="" value="NJ" placeholder="NJ"/>
                            </div>
                            <div>
                            <label >Postal Code</label>
                            <input type="text" name="" id="" value="08807" placeholder="08807"/>
                            </div>
                            <button>Save Property Details</button>
                        </form>
                    <DetailsLedger/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    );
}

export default Properties;