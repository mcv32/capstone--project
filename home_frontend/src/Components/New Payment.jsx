import React, {useState} from "react";
import Axios from "axios";

function NewPayment(){
    const urlPay = "http://localhost:8080/payments";
    const [paymentPayload, setPaymentPayload] = useState({
        paymentAmount:"",
        paymentType: "CREDIT_DEBIT",
        name:"",
        number: "",
        accountingNumber: "",
        routingNumber: "",
        exMo: "",
        exYr: "",
        CVV: ""
    })

    const [resMsg, setResMsg] = useState();

    const submit = async (e) => {
        try {
            console.log(paymentPayload);
            e.preventDefault();
            const response = await Axios.post(urlPay, {
                paymentType: paymentPayload.paymentType,
                number: paymentPayload.number,
                accountingNumber: paymentPayload.accountingNumber
            })

                console.log(response);
                setResMsg(response?.data);

                if (response?.data === "successful"){
                    setPaymentPayload({
                        paymentAmount:"",
                        paymentType: "CREDIT_DEBIT",
                        name:"",
                        number: "",
                        accountingNumber: "",
                        routingNumber: "",
                        exMo: "",
                        exYr: "",
                        CVV: ""
                    })
    
                } 
                
                setPopover(true);
                setTimeout(resetPopover, 5000);
            
            }catch (err){
                console.log(err.response);
                console.log(err.response.data.errorDesc);
                if (!err.response){
                    console.log ("No Server Response");
                    setResMsg("No Server Response")
                }
                // if (err.response.status === 400){
                //     setResMsg(err.response.data.errorDesc);
                //     setTimeout(resetResMsg, 5000);
                // } else {
                //     setResMsg("Unknown Error Occured. Account Not Created. Please Try Again");
                //     setTimeout(resetResMsg, 5000);
                // }
            }
        }

    function handle(e){
        const newPaymentPayload = {... paymentPayload}
        newPaymentPayload [e.target.id] = e.target.value
        setPaymentPayload(newPaymentPayload)
        console.log(newPaymentPayload)
    }

    const [isPopped, setPopover] = useState(false);

    function resetPopover(){
            setPopover(false);
    }

    
    return(
        paymentPayload.paymentType === "ACH_ECHECK" ?
            <div className="newPayment">
            <h2>Enter Payment Details</h2>
            <form action="submit" onSubmit={(e) => submit(e)}>
                <div>
                    {/* <label htmlFor="PaymentMethod">Payment Method</label> */}
                    <select onChange={(e) => handle(e)} value={paymentPayload.paymentType} id="paymentType">
                        <option value="CREDIT_DEBIT">Credit/Debit Card</option>
                        <option value="ACH_ECHECK">ACH/eCheck</option>
                        <option value="Fiserv Credit/Debit Card">Fiserv Credit/Debit Card</option>
                        <option value="Clover Credit/Debit Card">Clover Credit/Debit Card</option>
                    </select>
                </div>
                
                    <label htmlFor="Postal Code">Payment Amount</label>
                    <input onChange={(e) => handle(e)} value={paymentPayload.paymentAmount} id="paymentAmount" type="text"  />
                
                    <label htmlFor="Account">Account Number</label>
                    <input onChange={(e) => handle(e)} value={paymentPayload.accountingNumber} id="accountingNumber" type="text" />

                    <label htmlFor="Routing">Routing Number</label>
                    <input onChange={(e) => handle(e)} value={paymentPayload.routingNumber} id="routingNumber" type="text" />


                <button>Submit Payment</button>
            </form>
            <div className={isPopped ? "payPopOpen" : "offscreen"}>
                    <h1>Payment Successful! Thank you.</h1>
            </div>
            </div>
            :
            <div className="newPayment">
                <h2>Enter Payment Details</h2>
                <form action="submit" onSubmit={(e) => submit(e)}>
                    <div>
                        {/* <label htmlFor="PaymentMethod">Payment Method</label> */}
                        <select onChange={(e) => handle(e)} value={paymentPayload.paymentType} id="paymentType">
                            <option value="CREDIT_DEBIT">Credit/Debit Card</option>
                            <option value="ACH_ECHECK">ACH/eCheck</option>
                            <option value="Fiserv Credit/Debit Card">Fiserv Credit/Debit Card</option>
                            <option value="Clover Credit/Debit Card">Clover Credit/Debit Card</option>
                        </select>
                    </div>

                    <label>Payment Amount</label>
                    <input onChange={(e) => handle(e)} value={paymentPayload.paymentAmount} id="paymentAmount" type="text"  />
                    
                    <label>Name on Card</label>
                    <input onChange={(e) => handle(e)} value={paymentPayload.name} id="name" type="text" />

                    <label>Card Number</label>
                    <input onChange={(e) => handle(e)} value={paymentPayload.number} id="number" type="text" />

                    <label>Expiration Month</label>
                    <input onChange={(e) => handle(e)} value={paymentPayload.exMo} id="exMo" type="text" />
                    <label>Year</label>
                    <input onChange={(e) => handle(e)} value={paymentPayload.exYr} id="exYr" type="text" />

                    <label>CVV Code</label>
                    <input onChange={(e) => handle(e)} value={paymentPayload.CVV} id="CVV" type="text" />


                    <button type="submit">Submit Payment</button>
                </form>
                <div className={isPopped ? "payPopOpen" : "offscreen"}>
                    <h1>{resMsg}</h1>
                </div>
            </div>
    );
}

export default NewPayment;