import React from "react";

function OverdueAccounts(){
    return(
        <div className="overdueAccounts">
            <h2>OVERDUE ACCOUNTS</h2>
            <table>
                <tr>
                    <th>Past Due Date</th>
                    <th>Amount Owed</th>
                    <th>Account</th>
                </tr>
                <tr>
                    <td>Aug 1, 2023</td>
                    <td>$75.50</td>
                    <td>John Smith</td>
                </tr>
                <tr>
                    <td>September 23, 2023</td>
                    <td>$110.13</td>
                    <td>Maria Wong</td>
                </tr>
            </table>
        </div>
    );
}

export default OverdueAccounts;