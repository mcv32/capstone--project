import React from "react";

function Accounts(){
    return(
        <div className="Accounts">
        <h2>ACCOUNTS</h2>
        <table>
            <tr>
                <th>Status</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Next Payment Due</th>
            </tr>
            <tr>
                <td>Good Standing</td>
                <td>Jane</td>
                <td>Snow</td>
                <td>jane.snow@gmail.com</td>
                <td>January 1, 2024</td>
            </tr>
            <tr>
                <td>Overdue</td>
                <td>Maria</td>
                <td>Wong</td>
                <td>m.wong@gmail.com</td>
                <td>January 1, 2024</td>
            </tr>
            <tr>
                <td>Good Standing</td>
                <td>Bruce</td>
                <td>Lee</td>
                <td>bl.smoothie@gmail.com</td>
                <td>January 1, 2024</td>
            </tr>
        </table>
    </div>
    );
}

export default Accounts;