import React from "react";
import Toggle from "../Components/Toggle";

function Dashboard(){
    return(
        <section className="dashboard">
            <div className="dashHead">
                <h1>Welcome</h1>
                <Toggle />
            </div>
            <div className="dashBody">
                <div className="dashColumn">
                    <span className="testblock">AccountID Card</span>
                    <span className="testblock">OVERDUE ACCOUNTS</span>
                    <span className="testblock">RECENT PAYMENTS</span>
                    <span className="testblock">OPEN SERVICE TICKETS</span>
                </div>
                <div className= "dashCore">
                    <span className="testblock">MANAGED PROPERTIES</span>
                    <span className="testblock">TENANTS</span>
                </div>
            </div>
        </section>
    );
}

export default Dashboard;