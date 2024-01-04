import React, {useState, useEffect} from "react";
import NewPayment from "./New Payment";

function AccountBalance(props){

    const [isPopped, setPopover] = useState(false);

    function handlePop(){
            setPopover(!isPopped);
    }

    console.log("Account Balance Props", props);
    
    return(
        
        <div className="accountBalance">
            <div></div>
            <h2>ACCOUNT BALANCE</h2>
            <p>${props.userFinAcct?.account_balance?.toFixed(2)}</p>
            {props.userFinAcct?.due_date[0] !== null && typeof props.userFinAcct?.due_date[0] !== undefined ? <h3>Due on {props.userFinAcct?.due_date[1]}/{props.userFinAcct?.due_date[2]}/{props.userFinAcct?.due_date[0]}</h3>:null}
            <button onClick={handlePop}>Make Payment</button>
            <div className={isPopped ? "newPaymentOpen" :"newPaymentClosed"} >
                <div className="closepopover">
                    <button onClick={handlePop}>X</button>
                </div>
                <div>
                    <NewPayment close={handlePop} refresh={props.refresh} finAcct_id = {props.userFinAcct.financial_account_id} ledgers = {props.userFinAcct.ledgers}/>
                </div>
            </div>
        </div>
    );
}

export default AccountBalance;