import React, {useState} from "react";
import { NavLink, Link } from "react-router-dom";
import NewPayment from "./New Payment";

function AccountBalance(finAcct){

    const [isPopped, setPopover] = useState(false);

    function handlePop(){
            setPopover(!isPopped);
    }

    // console.log("This is the Account Balance", finAcct);

    return(
        <div className="accountBalance">
            <div></div>
            <h2>ACCOUNT BALANCE</h2>
            <p>${finAcct?.account_balance?.toFixed(2)}</p>
            <h3>Due on 12/23/2023</h3>
            <button onClick={handlePop}>Make Payment</button>
            <div className={isPopped ? "newPaymentOpen" :"newPaymentClosed"} >
                <div className="closepopover">
                    <button onClick={handlePop}>X</button>
                </div>
                <div>
                    <NewPayment/>
                </div>
            </div>
        </div>
    );
}

export default AccountBalance;