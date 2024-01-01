import React, {useState} from "react";
import useAuth from "../Hooks/useAuth";

function Ledger({ledgers}){

    const { auth, setAuth } = useAuth();
    const [propPopped, setPropPopover] = useState(false);

    function handlePropPop(){
            setPropPopover(!propPopped);
    }

    const [isNewLedgPop, setNewLedgPop] = useState(false);

    function handleNewLedgPop(){
        setNewLedgPop(!isNewLedgPop);
    }

    // console.log("Ledger data import", ledgers);
    // console.log("Ledger amount import", ledgers[0].amount);
    // console.log("Ledge import", ledge[0]);
    // console.log("new ledger log", Object.keys(ledgers));

    return(
        <div className="ledger">
        {auth?.roles === "ADMIN" || auth?.roles === "MANAGER" || auth?.roles === "USER" && 
            <div className="detailsLedgerHeader">
                <h2>LEDGERS</h2>
                <button className="openpopover" onClick={handleNewLedgPop}>+</button>
            </div>}
        <table>
            <thead>
                <tr>
                    <th>Amount</th>
                    <th>Description</th>
                    <th>Associated Property</th>
                </tr>
            </thead>
            <tbody>
                {Object.keys(ledgers).map((i) => (
                    <tr key = {i} onClick={ledgers[i]?.amount > 0 ? handlePropPop:null}>
                        <td>${ledgers[i]?.amount?.toFixed(2)}</td>
                        <td>{ledgers[i]?.description}</td>
                        <td>{ledgers[i]?.property?.name}</td>
                    </tr>
                ))}              
            </tbody>
        </table>
        <div className={isNewLedgPop ? "proppopoverOpen" :"proppopoverClosed"} >
            <div className="closepopover">
                <button onClick={handleNewLedgPop}>X</button>
            </div>
            <div className="newLedger">
                <h2>Enter New Ledger Details</h2>
                <form action="submit" onSubmit="">
                    <div>
                        <select id="ledgerType">
                            <option value="+">Account Charge</option>
                            <option value="-">Account Credit</option>
                        </select>
                    </div>

                    <label>Amount</label>
                    <input id="paymentAmount" type="text"  />
                    
                    <label>Description</label>
                    <textarea id="ledger description" type="text" rows="3"/>
                    
                    <label>Associated Property</label>
                    <select name="" id="" multiple>
                        <option value="User Account 1">Property 1</option>
                        <option value="User Account 2">Property 2</option>
                    </select>

                    <button type="submit">Submit Payment</button>
                </form>
                <div className>
                    <h1>Response Message</h1>
                </div>
            </div>
        </div>
        <div className={propPopped ? "transactionRecordOpen" :"offscreen"} >
            <div className="closeRecord">
                <button onClick={handlePropPop}>X</button>
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
    );
}

export default Ledger;