import React, {useState} from "react";
import { NavLink, Link } from "react-router-dom";
import NewPayment from "./New Payment";

function AccountBalance(){

    const [isPopped, setPopover] = useState(false);

    function handlePop(){
            setPopover(!isPopped);
    }

    return(
        <div className="accountBalance">
            <div></div>
            <h2>ACCOUNT BALANCE</h2>
            <p>$250.00</p>
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