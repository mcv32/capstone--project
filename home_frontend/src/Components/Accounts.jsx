import React, {useState} from "react";
import NewAccount from "./NewAccount";

function Accounts(){
    
    const [isPopped, setPopover] = useState(false);

    function handlePop(){
            setPopover(!isPopped);
    }

    const [propPopped, setPropPopover] = useState(false);

    function handlePropPop(){
            setPropPopover(!propPopped);
    }
    
    return(
        <div className="Accounts">
            <div className="propertiesHeader">
                <h2>ACCOUNTS</h2>
                <button className="openpopover" onClick={handlePop}>+</button>
            </div>
        <table>
            <tr>
                <th>Status</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Next Payment Due</th>
            </tr>
            <tr onClick={handlePropPop}>
                <td>Good Standing</td>
                <td>Jane</td>
                <td>Snow</td>
                <td>jane.snow@gmail.com</td>
                <td>January 1, 2024</td>
            </tr>
            <tr onClick={handlePropPop}>
                <td>Overdue</td>
                <td>Maria</td>
                <td>Wong</td>
                <td>m.wong@gmail.com</td>
                <td>January 1, 2024</td>
            </tr>
            <tr onClick={handlePropPop}>
                <td>Good Standing</td>
                <td>Bruce</td>
                <td>Lee</td>
                <td>bl.smoothie@gmail.com</td>
                <td>January 1, 2024</td>
            </tr>
        </table>
        <div className={isPopped ? "proppopoverOpen" :"proppopoverClosed"} >
                <div className="closepopover">
                    <button onClick={handlePop}>X</button>
                </div>
                <div>
                    <NewAccount/>
                </div>
        </div>
        <div className={propPopped ? "recordOpen" :"offscreen"} >
            <div className="closeRecord">
                <button onClick={handlePropPop}>X</button>
            </div>
            <div>
                <h1 style={{color:"black"}}>Account Details View</h1>
            </div>
        </div> 
    </div>
    );
}

export default Accounts;