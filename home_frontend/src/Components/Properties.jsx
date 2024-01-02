import React, {useState, useEffect} from "react";
import NewProperty from "./NewProperty";
import Ledger from "./Ledger";
import useAuth from "../Hooks/useAuth";
import axios from "axios";

function Properties(){
    
    const { auth, setAuth } = useAuth();
    const [propertyData, setPropertyResponse] = useState([]);
    const [viewProperty, setViewProperty] = useState([]);

    useEffect(() => {
        console.log("view property state", viewProperty);
    }, [viewProperty]);

    let config = {
        method: 'get',
        maxBodyLength: Infinity,
        url: 'http://localhost:8080/properties',
        headers: { 
          'Authorization': 'Bearer ' + auth?.accessToken
        }
      };
    
    useEffect(() => {
        const fetchDash = async () => {
            try {
                const response = await axios.request(config)

                console.log("Property Data Load", {...response?.data});
                setPropertyResponse({...response?.data})
                console.log("Property Data State", Object.keys(propertyData));

                

            } catch (err) {
                console.log(err?.response);

            }
        }

        fetchDash();
      }, []);

    // Methods for handling creation of new property record
    const [newPopped, setNewPopover] = useState(false);

    function handleNewPop(){
            setNewPopover(!newPopped);
    }

    // Methods for handling property update form inputs
    const [propPopped, setPropPopover] = useState(false);

    function handlePropPop(prop){
            setPropPopover(!propPopped);
            setViewProperty(prop);
    }

    function handlePropDetailsInput(e){
        const newPropDetailsPayload = {...viewProperty};
        newPropDetailsPayload[e.target.id] = e.target.value;
        setViewProperty(newPropDetailsPayload);
        // console.log(newPropDetailsPayload);
    }

    const savePropDetails = async (form) => {
        try {
            // console.log(loginPayload);
            form.preventDefault();
            const response = await axios.post("post URL", {
                name: viewProperty.name,
                address_street: viewProperty.address_street,
                address_line_2: viewProperty.address_line_2,
                city: viewProperty.city,
                state: viewProperty.state,
                zip: viewProperty.zip,
                status: viewProperty.status
            })
                // console.log(response);

                //display success message
                //refresh dashboard data
                //toggle popover off
                
                // navigate(from, {replace:true});
            
            }catch (err){
                if (err.response.status === 400){
                    // console.log(err.response);
                    // console.log(err.response.data.errorDesc);
                    // setResMsg(err.response.data.errorDesc);
                    // setTimeout(resetResMsg, 5000);
                } else {
                    // setResMsg("Unknown Error Occured. Details Not Saved. Please Try Again");
                    // setTimeout(resetResMsg, 5000);
                }
        }      

    }
    
    // Methods for handling new ledgers
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
                <thead>
                    <tr>
                        <th>Status</th>
                        <th>Name</th>
                        <th>Balance</th>
                        <th>Address</th>
                        <th>City, State</th>
                        <th>Financial Account</th>
                    </tr>
                </thead>
                <tbody>
                {Object.keys(propertyData).map((i) => (
                    <tr key = {i} onClick={() => handlePropPop(propertyData[i])}>
                        <td>{propertyData[i]?.status.toUpperCase()}</td>
                        <td>{propertyData[i]?.name}</td>
                        <td>${propertyData[i]?.property_balance.toFixed(2)}</td>
                        <td>{propertyData[i]?.address_street} <br /> {propertyData[i]?.address_line_2}</td>
                        <td>{propertyData[i]?.city + ", " + propertyData[i]?.state}</td>
                        <td>Get request needs to fetch linked FinAccts</td>
                    </tr>
                ))} 
                </tbody>
            </table>
            <div className={newPopped ? "rightPopoverOpen" :"rightPopoverClosed"} >
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
                        <button onClick={() => handlePropPop({})}>X</button>
                    </div>
                        <img src="https://hips.hearstapps.com/hmg-prod/images/over-the-top-apartments-main-1512422328.jpg?crop=1.00xw:0.502xh;0,0.263xh&resize=1200:*"/>
                        <form typeof="submit">
                            <div>
                                <label >Thumbnail URL</label>
                                <input type="url" name="" id="" value="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg" placeholder="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg"/>
                            </div>
                            <div>
                                <label >Property Name</label>
                                <input id="name" type="text" 
                                    onChange={(e) => handlePropDetailsInput(e)}
                                    value={viewProperty.name}/>
                            </div>
                            <div>
                                <label >Street Address</label>
                                <input id="address_street" type="text" 
                                    onChange={(e) => handlePropDetailsInput(e)}
                                    value={viewProperty.address_street}/>
                                <input id="address_line_2" type="text" 
                                    onChange={(e) => handlePropDetailsInput(e)}
                                    value={viewProperty.address_line_2}/>
                            </div>
                            <div>
                                <label >City</label>
                                <input id="city" type="text" 
                                    onChange={(e) => handlePropDetailsInput(e)}
                                    value={viewProperty.city}/>
                            </div>
                            <div>
                                <label >State</label>
                                <input id="state" type="text" 
                                    onChange={(e) => handlePropDetailsInput(e)}
                                    value={viewProperty.state}/>
                            </div>
                            <div>
                            <label >Postal Code</label>
                            <input id="zip" type="text" 
                                onChange={(e) => handlePropDetailsInput(e)}
                                value={viewProperty.zip}/>
                            </div>
                            <button >Save Property Details</button>
                        </form>
                    <h1>Missing ledger returns on /properties get request</h1>
                    {/* <Ledger/> */}
                    </div>
                </div>
            </div>
        </div>
    </div>
    );
}

export default Properties;