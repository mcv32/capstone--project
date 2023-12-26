import React, {useState} from "react";
import NewAccount from "./NewAccount";
import AccountID from "./AccountID";

function Accounts(){
    
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
        <div className="Accounts">
            <div className="propertiesHeader">
                <h2>ACCOUNTS</h2>
                <button className="openpopover" onClick={handleNewAcctPop}>+</button>
            </div>
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
        <div className={isNewAcctPop ? "proppopoverOpen" :"proppopoverClosed"} >
                <div className="closepopover">
                    <button onClick={handleNewAcctPop}>X</button>
                </div>
                <div>
                    <NewAccount/>
                </div>
        </div>
        <div className={isAcctPop ? "billingAccountRecordOpen" :"offscreen"} >
            <div className="closeRecord">
                <button onClick={handleAcctPop}>X</button>
            </div>
            <div className="billingAccountRecord">
                <h1 style={{color:"black"}}>Billing Account Details</h1>
                <AccountID/>
                <form typeof="submit">
                    <select name="" id="" multiple>
                        <option value="User Account 1">User Account 1</option>
                        <option value="User Account 2">User Account 2</option>
                    </select>
                    <button className="formButtom">Save Account Details</button>
                </form>                    
                <div className="ledger detailsLedger">
                    <div className="detailsLedgerHeader">
                        <h2>LEDGERS</h2>
                        <button className="openpopover" onClick={handleNewLedgPop}>+</button>
                    </div>
                    <table>
                        <tr>
                            <th>Amount</th>
                            <th>Date Posted</th>
                            <th>Ledger Item</th>
                        </tr>
                        <tr onClick={handleLedgPop}>
                            <td>$250.00</td>
                            <td>January 1, 2024</td>
                            <td>January Rent</td>
                        </tr>
                        <tr onClick={handleLedgPop}>
                            <td>$250.00</td>
                            <td>December 1, 2023</td>
                            <td>December Rent</td>
                        </tr>
                        <tr onClick={handleLedgPop}>
                            <td>$200.00</td>
                            <td>November 30, 2023</td>
                            <td>Window Repair after Cody's Thanksgiving Eve Rager</td>
                        </tr>
                        <tr onClick={handleLedgPop}>
                            <td>$250.00</td>
                            <td>November 1, 2023</td>
                            <td>November Rent</td>
                        </tr>
                        <tr onClick={handleLedgPop}>
                            <td>- $50.00</td>
                            <td>October 30, 2023</td>
                            <td>Loyalty Customer Credit</td>
                        </tr>
                    </table>
                    <div className={isNewLedgPop ? "proppopoverOpen" :"proppopoverClosed"} >
                        <div className="closepopover">
                            <button onClick={handleNewLedgPop}>X</button>
                        </div>
                        <div>
                            <h1>New Ledger Form</h1>
                        </div>
                    </div>
                    <div className={isLedgPop ? "recordOpen" :"offscreen"} >
                        <div className="closeRecord">
                            <button onClick={handleLedgPop}>X</button>
                        </div>
                        <div>
                            <p>Type: Credit Card</p>
                            <p>Transaction ID: ###-####</p>
                            <p>Card: **** **** **** **** 1234</p>
                            <p>Date Time Posted: 1/2/2024 13:32 EST</p>
                            <p>Status: Approved</p>
                        </div>
                    </div> 
                </div>
            </div>
        </div> 
    </div>
    );
}

export default Accounts;