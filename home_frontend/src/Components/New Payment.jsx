import React from "react";

function NewPayment(){
    return(
        <div className="newPayment">
            <h2>Enter Payment Details</h2>
            <form action="submit">
                <div>
                    {/* <label htmlFor="PaymentMethod">Payment Method</label> */}
                    <select name="PaymentMethod" id="payMethod">
                        <option value="Credit/Debit Card">Test Credit/Debit Card</option>
                        <option value="ACH/eCheck">ACH/eCheck</option>
                        <option value="Fiserv Credit/Debit Card">Fiserv Credit/Debit Card</option>
                        <option value="Clover Credit/Debit Card">Clover Credit/Debit Card</option>
                    </select>
                </div>
                
                <label htmlFor="CardNumber">Card Number</label>
                <input id="CardNumber" type="text" />

                <label htmlFor="Account">Account</label>
                <input id="Account" type="text" />

                <label htmlFor="Expiration Date">Expiration Date</label>
                <input type="text" />
                <input type="text" />

                <label htmlFor="State">CVV Code</label>
                <input type="text" />

                <label htmlFor="Postal Code">Name on Card</label>
                <input type="text" />

                <button>Submit Payment</button>
            </form>
        </div>
    );
}

export default NewPayment;