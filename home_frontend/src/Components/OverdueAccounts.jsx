import React, {useState, useEffect} from "react";
import AccountID from "./AccountID";
import useAuth from "../Hooks/useAuth";
import axios from "axios";
import Ledger from "./Ledger";

function OverdueAccounts(){
    const { auth, setAuth } = useAuth();
    const [accountData, setAccountResponse] = useState([]);
    const [viewAccount, setViewAccount] = useState([]);

    useEffect(() => {
        console.log("view account state", viewAccount.financial_account_id);
    }, [viewAccount]);

    let config = {
        method: 'get',
        maxBodyLength: Infinity,
        url: 'http://localhost:8080/financialAccounts/overdue',
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

    function handleNewAcctPop(){
        setNewAcctPop(!isNewAcctPop);
    }

    const [isAcctPop, setAcctPop] = useState(false);

    function handleAcctPop(acct){
        setAcctPop(!isAcctPop);
        setViewAccount(acct);
    }

    const [isLedgPop, setLedgPop] = useState(false);

    function handleLedgPop(){
        setLedgPop(!isLedgPop);
    }

    const [isNewLedgPop, setNewLedgPop] = useState(false);

    function handleNewLedgPop(){
        setNewLedgPop(!isNewLedgPop);
    }

    return(
        <div>
        <div className="columnHeader">
          <h2>OVERDUE ACCOUNTS</h2>
        </div>
        <div className="overdueAccounts">
            <table>
                <thead>
                    <tr>
                        <th>Past Due Date</th>
                        <th>Amount Owed</th>
                        <th>Account</th>
                    </tr>
                </thead>
                <tbody>
                {Object.keys(accountData).map((i) => (
                    <tr key = {i} onClick={() => handleAcctPop(accountData[i])}>
                        <td>{accountData[i]?.status}</td>
                        {/* 
                        <td>{accountData[i]?.appUsers[0]?.l_name}</td> */}
                        <td>${accountData[i]?.account_balance.toFixed(2)}</td>
                        <td>{accountData[i]?.appUsers[0]?.f_name} {accountData[i]?.appUsers[0]?.l_name} {accountData[i].email}</td>
                    
                        
                    </tr>
                ))} 
                {/* <tr onClick={() => handleAcctPop({})}>
                        <td>Status TBD</td>
                        <td>Ryan</td>
                        <td>Rad</td>
                        <td>ryan.rad@gmail.com</td>
                        <td>$20.00</td>
                    </tr> */}
                </tbody>
            </table> 
        </div>
        <div className={isAcctPop ? "billingAccountRecordOpen" :"offscreen"} >
            <h1 style={{ color: "black" }}>Billing Account Details</h1>
                <div className="closeRecord">
                    <button onClick={() => handleAcctPop({})}>X</button>
                </div>
                {Object.keys(viewAccount).length > 0 && (Object.keys(viewAccount.appUsers).map((i) => (
                    <AccountID {...viewAccount.appUsers[i]}/>)
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
                        (<Ledger account_id={viewAccount.financial_account_id} ledgers={viewAccount.ledgers}/>)
                    }
            </div>
        </div>
    </div>
    );
}

export default OverdueAccounts;