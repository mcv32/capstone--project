import React from "react";

function Ledger(){
    return(
        <div className="Accounts">
        <h2></h2>
        <table>
            <tr>
                <th>Amount</th>
                <th>Date Posted</th>
                <th>Ledger Item</th>
            </tr>
            <tr>
                <td>$250.00</td>
                <td>January 1, 2024</td>
                <td>January Rent</td>
            </tr>
            <tr>
                <td>$250.00</td>
                <td>December 1, 2023</td>
                <td>December Rent</td>
            </tr>
            <tr>
                <td>$200.00</td>
                <td>November 30, 2023</td>
                <td>Window Repair after Cody's Thanksgiving Eve Rager</td>
            </tr>
            <tr>
                <td>$250.00</td>
                <td>November 1, 2023</td>
                <td>November Rent</td>
            </tr>
            <tr>
                <td>- $50.00</td>
                <td>October 30, 2023</td>
                <td>Loyalty Customer Credit</td>
            </tr>
        </table>
    </div>
    );
}

export default Ledger;