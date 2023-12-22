import React from "react";

function RecentPayments(){
    return(
        <div className="recentPayments">
            <h2>RECENT PAYMENTS</h2>
            <table>
                <tr>
                    <th>Posted Date</th>
                    <th>Amount</th>
                    <th>Account</th>
                </tr>
                <tr>
                    <td>December 2, 2023</td>
                    <td>$250.00</td>
                    <td>Jane Snow</td>
                </tr>
                <tr>
                    <td>December 5, 2023</td>
                    <td>$550.00</td>
                    <td>Bruce Lee</td>
                </tr>
            </table>
        </div>
    );
}

export default RecentPayments;