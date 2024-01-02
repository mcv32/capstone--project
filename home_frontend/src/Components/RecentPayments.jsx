import React, {useState} from "react";


function RecentPayments(){

    const [transactionPop, setTransactionPop] = useState(false);

    function handleTransactionPop(){
        setTransactionPop(!transactionPop);
    }

    return(
        <div>
        <div className="columnHeader">
          <h2>RECENT PAYMENTS</h2>
        </div>
        <div className="recentPayments">
            <table>
                <tr>
                    <th>Posted Date</th>
                    <th>Amount</th>
                    <th>Account</th>
                </tr>
                <tr onClick={handleTransactionPop}>
                    <td>December 2, 2023</td>
                    <td>$250.00</td>
                    <td>Jane Snow</td>
                </tr>
                <tr onClick={handleTransactionPop}>
                    <td>December 5, 2023</td>
                    <td>$550.00</td>
                    <td>Bruce Lee</td>
                </tr>
            </table>
            <div className={transactionPop ? "transactionRecordOpen" :"offscreen"} >
            <div className="closeRecord">
                <button onClick={handleTransactionPop}>X</button>
            </div>
            <div>
            <p><strong>Type:</strong> Credit Card</p>
            <p><strong>Transaction ID:</strong> ###-####</p>
            <p><strong>Card:</strong> **** **** **** **** 1234</p>
            <p><strong>Date Time Posted:</strong> 1/2/2024 13:32 EST</p>
            <p><strong>Status:</strong> Approved</p>
            </div>
            </div> 
        </div>
    </div> 
    );
}

export default RecentPayments;