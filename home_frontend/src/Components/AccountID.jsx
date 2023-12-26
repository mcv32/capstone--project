import React, {useState} from "react";
import { NavLink, Link } from "react-router-dom";
import useAuth from "../Hooks/useAuth";

function AccountID(){

    const { auth, setAuth } = useAuth();
    const [propPopped, setPropPopover] = useState(false);

    function handlePropPop(){
            setPropPopover(!propPopped);
    }
    
    return(
        auth?.roles === "MANAGER" ?
        <div className="accountBlock">
            <div className="accountLeft">
                <img src="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg"/>
                <Link onClick={handlePropPop}>Edit Account</Link>
            </div>
            <div className="accountRight">
                <h2>MANAGER</h2>
                <h3>Cody Phelan</h3>
                <p>Contact Information</p>
                <p>908-685-1182</p>
                <p>cody@fiserv.com</p>
            </div>
            <div className={propPopped ? "accountDetailsOpen" :"offscreen"} >
                <div className="closeRecord">
                    <button onClick={handlePropPop}>X</button>
                </div>
                <div className="accountDetails">
                    <h1 style={{color:"black"}}>Personal Account Details</h1>
                    <form typeof="submit">
                        <img src="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg"/>
                        <label >Avatar URL</label>
                        <input type="url" name="" id="" value="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg" placeholder="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg"/>
                        <label >First Name</label>
                        <input type="text" name="" id="" value="Cody" placeholder="Cody"/>
                        <label >Last Name</label>
                        <input type="text" name="" id="" value="Phelan" placeholder="Phelan"/>
                        <label >Phone Number</label>
                        <input type="tel" name="" id="" value="908-685-1182" placeholder="908-685-1182"/>
                        <label >Email</label>
                        <input type="email" name="" id="" value="cody@fiserv.com" placeholder="cody@fiserv.com"/>
                        <button>Save Personal Details</button>
                    </form>
                </div>
            </div>
        </div>
        :
        <div className="accountBlock">
            <div className="accountLeft">
                <img src="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg"/>
                <Link onClick={handlePropPop}>Edit Account</Link>
            </div>
            <div className="accountRight">
                <h2>Cody Phelan</h2>
                <p>908-685-1182</p>
                <p>cody@fiserv.com</p>
            </div>
            <div className={propPopped ? "accountDetailsOpen" :"offscreen"} >
                <div className="closeRecord">
                    <button onClick={handlePropPop}>X</button>
                </div>
                <div className="accountDetails">
                    <h1 style={{color:"black"}}>Edit Account Details</h1>
                    <form typeof="submit">
                        <img src="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg"/>
                        <label >Avatar URL</label>
                        <input type="url" name="" id="" value="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg" placeholder="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg"/>
                        <label >First Name</label>
                        <input type="text" name="" id="" value="Cody" placeholder="Cody"/>
                        <label >Last Name</label>
                        <input type="text" name="" id="" value="Phelan" placeholder="Phelan"/>
                        <label >Phone Number</label>
                        <input type="tel" name="" id="" value="908-685-1182" placeholder="908-685-1182"/>
                        <label >Email</label>
                        <input type="email" name="" id="" value="cody@fiserv.com" placeholder="cody@fiserv.com"/>
                        <button>Save Personal Details</button>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default AccountID;