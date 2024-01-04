import React, {useState, useEffect} from "react";
import useAuth from "../Hooks/useAuth";
import axios from "axios";


function RecentPayments(){
    const { auth, setAuth } = useAuth();
    const [recentPaymentsData, setRecentPaymentsResponse] = useState([]);
    const [viewTransaction, setViewTransaction] = useState([]);


    // useEffect(() => {
    //     console.log("view recentpayment", viewAccount.financial_account_id);
    // }, [viewAccount]);

    let config = {
        method: 'get',
        maxBodyLength: Infinity,
        url: 'http://localhost:8080/ledgers/recentPayments',
        headers: { 
          'Authorization': 'Bearer ' + auth?.accessToken
        }
      };
    
    useEffect(() => {
        const fetchRecentPayments = async () => {
            try {
                const response = await axios.request(config)

                console.log("Recent Payments Load", {...response?.data});
                setRecentPaymentsResponse({...response?.data})
                

                

            } catch (err) {
                console.log(err?.response);

            }
        }

        fetchRecentPayments();
      }, []);
    
    
    const [transactionPop, setTransactionPop] = useState(false);

    function handleTransactionPop(trans){
        setTransactionPop(!transactionPop);
        setViewTransaction(trans);
        console.log("This is the view transaction",viewTransaction);
    }

return (
    <div>
        <div className="columnHeader">
          <h2>RECENT PAYMENTS</h2>
        </div>
        <div className="recentPayments">
            <table>
                <thead>
                    <tr>
                        <th>Posted Date</th>
                        <th>Amount</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                    { Object.keys(recentPaymentsData).map((i) => (
                        <tr key = {i} onClick={() => handleTransactionPop(recentPaymentsData[i])}>
                            <td>{recentPaymentsData[i]?.time[1]}/{recentPaymentsData[i]?.time[2]}/{recentPaymentsData[i]?.time[0]} {recentPaymentsData[i]?.time[3]}:{recentPaymentsData[i]?.time[4]}</td>
                            <td>${recentPaymentsData[i]?.amount.toFixed(2)*-1}</td>
                            <td>{recentPaymentsData[i]?.description}</td>
                        
                            
                        </tr>
                    ))} 
                </tbody>
            </table>
            <div className={transactionPop ? "transactionRecordOpen" :"offscreen"} >
            <div className="closeRecord">
                <button onClick={handleTransactionPop}>X</button>
            </div>
            <div>
                <p>Transaction Type: {viewTransaction?.transactionTests?.paymentType}</p>
                <p>Transaction ID#: {viewTransaction?.transactionTests?.transaction_id}</p>
                <p>Transaction Amount: ${viewTransaction?.transactionTests?.amount.toFixed(2)}</p>
                {viewTransaction?.transactionTests?.paymentType === "CREDIT_DEBIT" ?
                <p>Card number ending in **** {viewTransaction?.transactionTests?.last_four_digits}</p>
                :
                <p>Account number ending in **** {viewTransaction?.transactionTests?.last_four_digits}</p>
                }
                <p>Date Time Posted: {viewTransaction?.transactionTests?.time[1]}/{viewTransaction?.transactionTests?.time[2]}/{viewTransaction?.transactionTests?.time[0]} {viewTransaction?.transactionTests?.time[3]}:{viewTransaction?.transactionTests?.time[4]} EST</p>
                <p>Status: {viewTransaction?.transactionTests?.status === true ? "APPROVED" : "FAILED"}</p>
            </div>
            </div> 
        </div>
    </div> 
    );
}

export default RecentPayments;