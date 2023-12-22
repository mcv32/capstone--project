import React from "react";

function NewPayment(){
    return(
        <div className="newPayment">
            <h2>Enter Payment Details</h2>
            <form action="submit">
                <label htmlFor="PaymentMethod">Payment Method</label>
                <input type="text" />
                
                <label htmlFor="CardNumber">Card Number</label>
                <input type="text" />

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