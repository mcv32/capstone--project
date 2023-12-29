import React, {useState} from "react";
import NewAccount from "./NewAccount";
import AccountID from "./AccountID";
import DetailsLedger from "./DetailsLedger";
import { Link } from "react-router-dom";

function Accounts(){
    
    const [isNewAcctPop, setNewAcctPop] = useState(false);

    function handleNewAcctPop(){
        setNewAcctPop(!isNewAcctPop);
    }

    const [isAcctPop, setAcctPop] = useState(false);

    function handleAcctPop(){
        setAcctPop(!isAcctPop);
    }

    const [isDetailsPop, setDetailsPop] = useState(false);

    function handleDetailsPop(){
        setDetailsPop(!isDetailsPop);
    }

    return (
        <div style={{ marginTop: '5px' }}>
          <div className="propertiesHeader">
            <h2>ACCOUNTS</h2>
            <button className="openpopover" onClick={handleNewAcctPop}>+</button>
          </div>
          <div className="Accounts">
            <table>
                <tr>
                    <th>Status</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Next Payment Due</th>
                </tr>
                <tr onClick={handleAcctPop}>
                    <td>Good Standing</td>
                    <td>Jane</td>
                    <td>Snow</td>
                    <td>jane.snow@gmail.com</td>
                    <td>January 1, 2024</td>
                </tr>
                <tr onClick={handleAcctPop}>
                    <td>Overdue</td>
                    <td>Maria</td>
                    <td>Wong</td>
                    <td>m.wong@gmail.com</td>
                    <td>January 1, 2024</td>
                </tr>
                <tr onClick={handleAcctPop}>
                    <td>Good Standing</td>
                    <td>Bruce</td>
                    <td>Lee</td>
                    <td>bl.smoothie@gmail.com</td>
                    <td>January 1, 2024</td>
                </tr>
            </table>
            <div className={isNewAcctPop ? "proppopoverOpen" : "proppopoverClosed"}>
                <div className="closepopover">
                    <button onClick={handleNewAcctPop}>X</button>
                </div>
                <div>
                    <NewAccount />
                </div>
            </div>
            <div className={isAcctPop ? "billingAccountRecordOpen" : "offscreen"}>
                <h1 style={{ color: "black" }}>Billing Account Details</h1>
                <div className="closeRecord">
                    <button onClick={handleAcctPop}>X</button>
                </div>

                <AccountID />

                <form typeof="submit">
                    <select name="" id="" multiple>
                        <option value="User Account 1">User Account 1</option>
                        <option value="User Account 2">User Account 2</option>
                    </select>
                    <button className="formButton">Save Account Details</button>
                </form>
                <DetailsLedger />
                </div>
            </div>
        </div>
    );
}

export default Accounts;