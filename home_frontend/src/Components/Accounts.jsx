import React, {useState, useEffect} from "react";
import NewAccount from "./NewAccount";
import AccountID from "./AccountID";
import DetailsLedger from "./DetailsLedger";
import Ledger from "./Ledger";
import { Link } from "react-router-dom";
import axios from "axios";
import useAuth from "../Hooks/useAuth";

function Accounts(props){
    const { auth, setAuth } = useAuth();
    const [accountData, setAccountResponse] = useState([]);
    const [viewAccount, setViewAccount] = useState([]);
    const [refreshAccounts, setRefreshAccounts] = useState(false);

    function handleAccountRefresh(){
        this.forceUpdate();
    }

    useEffect(() => {
        console.log("view account state", viewAccount.financial_account_id);
    }, [viewAccount]);

    let config = {
        method: 'get',
        maxBodyLength: Infinity,
        url: 'http://localhost:8080/financialAccounts',
        headers: { 
          'Authorization': 'Bearer ' + auth?.accessToken
        }
      };
    
    useEffect(() => {
        const fetchDash = async () => {
            try {
                const response = await axios.request(config)

                console.log("Account Data Load", {...response?.data});
                setAccountResponse({...response?.data})
                // console.log("Account Data State", Object.keys(accountData));

                

            } catch (err) {
                console.log(err?.response);

            }
        }

        fetchDash();
      }, []);

    const [isNewAcctPop, setNewAcctPop] = useState(false);
    const [isAcctPop, setAcctPop] = useState(false);
    const [isDetailsPop, setDetailsPop] = useState(false);

    function handleNewAcctPop() {
        setNewAcctPop(!isNewAcctPop);
    }

    function handleAcctPop(acct){
        setAcctPop(!isAcctPop);
        setViewAccount(acct);
    }

    function closeAcctModal(){
        setAccountResponse(!isAcctPop);
    }

    function handleDetailsPop(){
        setDetailsPop(!isDetailsPop);
    }

    return (
        <div>
          <div className="propertiesHeader">
            <h2>ACCOUNTS</h2>
            <button className="openpopover" onClick={handleNewAcctPop}>+</button>
          </div>
          <div className="Accounts">
            <table>
                <thead>
                    <tr>
                        <th>Status</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Balance</th>
                    </tr>
                </thead>
                <tbody>

                {Object.keys(accountData).map((i) => (
                    <tr key = {i} onClick={() => handleAcctPop(accountData[i])}>
                        <td>{accountData[i]?.status}</td>
                        <td>{accountData[i]?.appUsers[0]?.f_name}</td>
                        <td>{accountData[i]?.appUsers[0]?.l_name}</td>
                        <td>{accountData[i]?.email}</td>
                        <td>${accountData[i]?.account_balance.toFixed(2)}</td>
                    </tr>
                ))} 
                </tbody>
            </table>
            <div className={isNewAcctPop ? "rightPopoverOpen" : "rightPopoverClosed"}>
                <div className="closepopover">
                    <button onClick={handleNewAcctPop}>X</button>
                </div>
                    <div>
                        <NewAccount />
                    </div>
                </div>
            <div className={isAcctPop ? "billingAccountRecordOpen" : "offscreen"}>
                <h1 style={{ color: "black" }}>Billing Account Details</h1>
                <div className="closeRecord">
                    <button onClick={() => handleAcctPop({})}>X</button>
                </div>
                {Object.keys(viewAccount).length > 0 && (Object.keys(viewAccount.appUsers).map((i) => (
                    <AccountID userData={viewAccount.appUsers[i]}/>)
                ))}

                <form typeof="submit">
                    <select name="" id="" multiple>
                        <option value="User Account 1">User Account 1</option>
                        <option value="User Account 2">User Account 2</option>
                    </select>
                    <button className="formButton">Save Account Details</button>
                </form>
                <div className="billingAccountLedger">
                    {Object.keys(viewAccount).length > 0 &&
                        (<Ledger 
                            close_account ={() => handleAcctPop({})}
                            refresh_accounts = {handleAccountRefresh}
                            parent_component = "accounts" 
                            account_id={viewAccount.financial_account_id} 
                            ledgers={viewAccount.ledgers}/>)
                    }
                </div>
                </div>
            </div>
        </div>
    );
}

export default Accounts;