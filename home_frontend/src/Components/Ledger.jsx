import React, {useState} from "react";

function Ledger({...userLedgers}){

    const [propPopped, setPropPopover] = useState(false);

    function handlePropPop(){
            setPropPopover(!propPopped);
    }

    function createLedger(){

    }

    return(
        <div className="ledger">
        <h2></h2>
        <table>
            <thead>
                <tr>
                    <th>Amount</th>
                    <th>Date Posted</th>
                    <th>Ledger Item</th>
                </tr>
            </thead>
            <tbody>
                {
                userLedgers.map((userLedgers,key) =>
                <tr key={key} onClick={handlePropPop}>
                    <td>{userLedgers.amount}</td>
                    <td>{userLedgers.datePosted}</td>
                    <td>{userLedgers.Item}</td>
                </tr>
                )
                }
            </tbody>
        </table>
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