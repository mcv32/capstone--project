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


function Dashboard(){

    const { auth, setAuth } = useAuth();

    const [dash, setDash] = useState(null);

    const [responseData, setResponse] = useState();
    const [userData, setUserData] = useState();
    const [userFinAcct, setUserFinAcct] = useState();
    const [userLedgers, setUserLedgers] = useState([]);
    const [userProperties, setUserProperties] = useState([]);
    const [userTransactions, setUserTransactions] = useState([]);

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

                console.log(response);
                setResponse({...response.data});
                console.log("Response Data Load", {...responseData});
                setUserData({...responseData.appUser});
                console.log("Set User Data: ", {userData});
                setUserFinAcct({...responseData.financialAccount});
                console.log("Set User Financial Account", {userFinAcct});
                setUserLedgers({...responseData.ledgers});
                console.log("Set User Ledgers", {...userLedgers});
                setUserLedgers({...response.data.properties});
                console.log("Set User Properties", {...userProperties});
                setUserTransactions({...response.data.transactions});
                console.log("Set User Transactions", {...userTransactions});
                

            } catch (err) {
                console.log(err.response);

            }
        }

        fetchDash();
      }, []);

    
    return(
        auth?.roles === "MANAGER" ?
        <section className="dashboard">
            <div className="dashHead">
                <h1>Welcome Manager</h1>
            </div>
            <div className="dashBody">
                <div className="dashColumn">
                    <AccountID {...userData}/>
                    <OverdueAccounts/>
                    <RecentPayments/>
                    <OpenServiceTickets/>
                </div>
                <div className= "dashCore">
                    <Properties {...userProperties}/>
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
                <h1>Welcome Home</h1>
                <AccountID {...userData}/>
                <HomeIDcard {...userProperties}/>
                <TennantServiceTickets/>

                </div>
                <div className="dashCore">
                    <AccountBalance {...userFinAcct}/>
                    {/* <Ledger {...userLedgers}/> */}

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