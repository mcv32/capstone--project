import React from "react";

function OpenServiceTickets(){
    return(
        <div className="openServiceTickets">
            <h2>OPEN SERVICE TICKETS</h2>   
            <table>
                <tr>
                    <th>Request Date</th>
                    <th>Description</th>
                    <th>Account</th>
                </tr>
                <tr>
                    <td>December 10, 2023</td>
                    <td>Broken sink</td>
                    <td>Henry March</td>
                </tr>
                <tr>
                    <td>December 15, 2023</td>
                    <td>New doorbell install</td>
                    <td>George Washington</td>
                </tr>
            </table>
        </div>
    );
}

export default OpenServiceTickets;