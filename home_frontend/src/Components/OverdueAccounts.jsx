import React, {useState} from "react";
import AccountID from "./AccountID";
import DetailsLedger from "./DetailsLedger";

function OverdueAccounts(){

    const [isNewAcctPop, setNewAcctPop] = useState(false);

    function handleNewAcctPop(){
        setNewAcctPop(!isNewAcctPop);
    }

    const [isAcctPop, setAcctPop] = useState(false);

    function handleAcctPop(){
        setAcctPop(!isAcctPop);
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
        <div className="columnHeader">
          <h2>OVERDUE ACCOUNTS</h2>
        </div>
        <div className="overdueAccounts">
            <table>
                <tr>
                    <th>Past Due Date</th>
                    <th>Amount Owed</th>
                    <th>Account</th>
                </tr>
                <tr onClick={handleAcctPop}>
                    <td>Aug 1, 2023</td>
                    <td>$75.50</td>
                    <td>John Smith</td>
                </tr>
                <tr onClick={handleAcctPop}>
                    <td>September 23, 2023</td>
                    <td>$110.13</td>
                    <td>Maria Wong</td>
                </tr>
            </table>
            <div className={isAcctPop ? "billingAccountRecordOpen" :"offscreen"} >
            <div className="closeRecord">
                <button onClick={handleAcctPop}>X</button>
            </div>
            <div className={isAcctPop ? "billingAccountRecordOpen" :"offscreen"} >
            <h1 style={{color:"black"}}>Billing Account Details</h1>
            <div className="closeRecord">
                <button onClick={handleAcctPop}>X</button>
            </div>
 
            <AccountID/>

            <form typeof="submit">
                <select name="" id="" multiple>
                    <option value="User Account 1">User Account 1</option>
                    <option value="User Account 2">User Account 2</option>
                </select>
                <button className="formButton">Save Account Details</button>
            </form>
            <DetailsLedger/>                 
            </div> 
            </div> 
        </div>
        </div>
    );
}

export default OverdueAccounts;