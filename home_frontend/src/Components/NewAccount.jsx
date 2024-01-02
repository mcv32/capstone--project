import React, {useState, useEffect} from "react";
import axios from "axios";
import useAuth from "../Hooks/useAuth";

function NewAccount(){
    const { auth, setAuth } = useAuth();
    const [newAccountPayload, setNewAccountPayload] = useState({
        account_balance: 0,
        email: ""
    })


    const [resMsg, setResMsg] = useState();


    let config = {
        method: 'post',
        maxBodyLength: Infinity,
        url: 'http://localhost:8080/financialAccounts/create',
        headers: { 
          'Authorization': 'Bearer ' + auth?.accessToken
        },
        data : {
            account_balance: newAccountPayload.account_balance,
            email: newAccountPayload.email
        }
      };

    const submit = async (e) => {
        try {
            e.preventDefault();
            const response = await axios.request(config)

                console.log(response);         
                setResMsg("Account Succesfully Added");
                setNewAccountPayload({
                    account_balance: 0,
                    email: ""
                });
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
        const newNewAccountPayload = {... newAccountPayload};
        newNewAccountPayload [e.target.id] = e.target.value;
        setNewAccountPayload(newNewAccountPayload);
        console.log(newAccountPayload);
    }

    const [isPopped, setPopover] = useState(false);

    function resetPopover(){
            setPopover(false);
    }

    
    return(
        <div className="newAccount">
            <h2>Enter New Account Details</h2>
            <form action="submit" onSubmit={(e) => submit(e)}>
                <label >Primary Account Holder Email</label>
                <input onChange={(e) => handle(e)} value={newAccountPayload.email} id="email" type="email" />
                
                <label >Initial Balance</label>
                <input onChange={(e) => handle(e)} value={newAccountPayload.account_balance} id="account_balance" type="text"/>

                <button>Create New Account</button>
            </form>
            <div className={isPopped ? "payPopOpen" : "offscreen"}>
                    <h1>{resMsg}</h1>
            </div>
        </div>
    );
}

export default NewAccount;