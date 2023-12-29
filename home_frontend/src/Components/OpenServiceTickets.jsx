import React from "react";

function OpenServiceTickets(){
    return(
        <div>
        <div className="columnHeader">
          <h2>OPEN SERVICE TICKETS</h2>
        </div>
        <div className="openServiceTickets">
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
        </div>
    );
}

export default OpenServiceTickets;