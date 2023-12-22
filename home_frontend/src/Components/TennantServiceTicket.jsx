import React from "react";

function TennantServiceTickets(){
    return(
        <div className="tenantServiceTickets">
            <h2>MY SERVICE TICKETS</h2>
            <table>
                <tr>
                    <th>Request Date</th>
                    <th>Description</th>
                    <th>Status</th>
                </tr>
                <tr>
                    <td>December 10, 2023</td>
                    <td>Broken sink</td>
                    <td>Scheduled Maintenance</td>
                </tr>
                <tr>
                    <td>December 15, 2023</td>
                    <td>New doorbell install</td>
                    <td>Pending</td>
                </tr>
            </table>
        </div>
    );
}

export default TennantServiceTickets;