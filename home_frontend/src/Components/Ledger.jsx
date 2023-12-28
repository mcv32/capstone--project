import React, {useState} from "react";

function Ledger(){

    const [propPopped, setPropPopover] = useState(false);

    function handlePropPop(){
            setPropPopover(!propPopped);
    }
    return(
        <div className="ledger">
        <h2></h2>
        <table>
            <tr>
                <th>Amount</th>
                <th>Date Posted</th>
                <th>Ledger Item</th>
            </tr>
            <tr onClick={handlePropPop}>
                <td>$250.00</td>
                <td>January 1, 2024</td>
                <td>January Rent</td>
            </tr>
            <tr onClick={handlePropPop}>
                <td>$250.00</td>
                <td>December 1, 2023</td>
                <td>December Rent</td>
            </tr>
            <tr onClick={handlePropPop}>
                <td>$200.00</td>
                <td>November 30, 2023</td>
                <td>Window Repair after Cody's Thanksgiving Eve Rager</td>
            </tr>
            <tr onClick={handlePropPop}>
                <td>$250.00</td>
                <td>November 1, 2023</td>
                <td>November Rent</td>
            </tr>
            <tr onClick={handlePropPop}>
                <td>- $50.00</td>
                <td>October 30, 2023</td>
                <td>Loyalty Customer Credit</td>
            </tr>
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