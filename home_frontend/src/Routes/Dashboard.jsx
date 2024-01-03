import React, {useState, useEffect} from "react";
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
import axios from "axios";
import { useLocation } from 'react-router-dom';


function Dashboard(){
    const location = useLocation();
    const { auth, setAuth } = useAuth();

    const [dash, setDash] = useState(null);

    const [responseData, setResponse] = useState();
    const [userData, setUserData] = useState();
    const [userFinAcct, setUserFinAcct] = useState();
    const [userLedgers, setUserLedgers] = useState([]);
    const [userProperties, setUserProperties] = useState([]);
    const [userTransactions, setUserTransactions] = useState([]);

    const [refresh, setRefresh] = useState(true);

    function refreshData(){
        setRefresh(!refresh);
        console.log(refresh);
    }

    useEffect(() => {
        const fetchDash = async () => {
            try {
                const response = await axios.post("http://localhost:8080/users/details",
                {
                    email: auth?.email
                },
                { headers: { 
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + auth?.accessToken
                    } 
                }
                );

                // console.log("Response Data Load", {...response.data});
                setResponse({...response.data});
                console.log("Set Reponse Data", responseData);
                
                // console.log("Response User Data: ", {...response.data.appUser});
                setUserData({...response.data.appUser});
                console.log("Set User Data", userData);

                
                // console.log("Response Financial Account", {...response.data.financialAccount});
                setUserFinAcct({...response.data.financialAccount});
                console.log("Set Financial Account", userFinAcct);
                
                // console.log("Response Ledgers", [...response.data.ledgers]);
                setUserLedgers({...response.data.ledgers});
                // console.log("Set User Ledgers", userLedgers);
                // console.log("Set User Ledgers", userLedgers[0]);
                
                // console.log("Response Properties", {...response.data.properties});
                setUserProperties({...response.data.properties});
                // console.log("Set User Properties", userProperties);
                
                // console.log("Response Transactions", {...response.data.transactions});
                setUserTransactions({...response.data.transactions});
                // console.log("Set User Transactions", userTransactions);
                

            } catch (err) {
                console.log(err.response);

            }
        }

        fetchDash();
      }, [refresh]);

    
    return(
        //comment line below
        auth?.roles === "MANAGER" ?
        <section className="dashboard">
            <div className="dashHead">
                <h1>Welcome, {userData?.f_name}</h1>
                <button onClick={refreshData}>refresh</button>
            </div>
            <div className="dashBody">
                <div className="dashColumn">
                    <AccountID {...userData}/>
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
        {/* <div className="dashHead">
        </div> */}
            <div className="dashBody">
                <div className="dashColumn">
                <h1>Welcome, {userData?.f_name}</h1>
                <button onClick={refreshData}>refresh</button>
                <AccountID {...userData}/>
                <HomeIDcard properties = {userProperties}/>
                <TennantServiceTickets/>

                </div>
                <div className="dashCore">
                    <AccountBalance {...userFinAcct}/>
                    <Ledger account_id={userFinAcct} ledgers={userLedgers}/> 

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