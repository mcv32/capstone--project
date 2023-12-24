import React, {useState} from "react";
import AccountID from "../Components/AccountID";
import OverdueAccounts from "../Components/OverdueAccounts";
import RecentPayments from "../Components/RecentPayments";
import OpenServiceTickets from "../Components/OpenServiceTickets";
import Properties from "../Components/Properties";
import Accounts from "../Components/Accounts";
import TennantServiceTickets from "../Components/TennantServiceTicket";
import HomeIDcard from "../Components/HomeIDcard";
import AccountBalance from "../Components/AccountBalance";
import Ledger from "../Components/Ledger";
import useAuth from "../Hooks/useAuth";

function Dashboard(){

    const { auth, setAuth } = useAuth();

    
    return(
        auth?.roles === "MANAGER" ?
        <section className="dashboard">
            <div className="dashHead">
                <h1>Welcome Manager</h1>
            </div>
            <div className="dashBody">
                <div className="dashColumn">
                    <AccountID/>
                    <OverdueAccounts/>
                    <RecentPayments/>
                    <OpenServiceTickets/>
                </div>
                <div className= "dashCore">
                    <Properties/>
                    <Accounts/>
                </div>
            </div>
        </section>
        :
        <section className="dashboard">
        <div className="dashHead">
            <h1>Welcome Home</h1>
        </div>
            <div className="dashBody">
                <div className="dashColumn">
                <AccountID/>
                <HomeIDcard/>
                <TennantServiceTickets/>

                </div>
                <div className="dashCore">
                    <AccountBalance/>
                    <Ledger/>

                </div>
            </div>
            <div className="userDash">
                <div className="userDashItem4">
                </div>
                <div className="userDashItem5">
                </div>
        </div>
    </section>
    );
}

export default Dashboard;