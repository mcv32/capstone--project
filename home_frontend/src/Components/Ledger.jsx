import React, {useState, useEffect} from "react";
import useAuth from "../Hooks/useAuth";
import axios from "axios";

function Ledger(props){
    const { auth, setAuth } = useAuth();
    const [ledgePopped, setLedgePopover] = useState(false);
    const [ledgeClickView, setLedgeClickView] = useState();

    function handleLedgeClick(ledge){
        setLedgePopover(!ledgePopped);
        setLedgeClickView(ledge);
        // console.log("Ledger Click View", ledgeClickView)
    }

    console.log("Ledger props import", props);
    // console.log("Ledger amount import", ledgers[0].amount);
    // console.log("Ledge import", ledge[0]);
    // console.log("new ledger log", Object.keys(ledgers));

    const [isNewLedgPop, setNewLedgPop] = useState(false);

    function handleNewLedgPop(){
        setNewLedgPop(!isNewLedgPop);
    }

    const [newLedgerPayload, setNewLedgerPayload] = useState({
        ledgerType:"CHARGE",
        amount:0,
        description:"",
        time:"",
        status:true,
        financial_account_id: props.account_id !== null ? props.account_id : null,
        property_id: props.ledgers[0]?.property?.property_id !== null ? props.ledgers[0]?.property?.property_id : 
        propertyData[0]?.property_id !== null ? propertyData[0]?.property_id: props?.property_id
    })
    
    const [resMsg, setResMsg] = useState();
    const config = {
        method: 'post',
        maxBodyLength: Infinity,
        url: 'http://localhost:8080/ledgers/create',
        headers: { 
          'Authorization': 'Bearer ' + auth?.accessToken
        },
        data : {
            ledgerType: newLedgerPayload.ledgerType,
            amount: newLedgerPayload.amount,
            description: newLedgerPayload.description,
            time: newLedgerPayload.time,
            status: newLedgerPayload.status,
            financial_account_id: newLedgerPayload.financial_account_id,
            property_id: newLedgerPayload.property_id
        }
      };

    const submit = async (e) => {
        // console.log(config)
        try {
            e.preventDefault();
            const response = await axios.request(config)
                console.log("New Ledger Config", config);    
                console.log("New Ledger Response", response);         
                setResMsg("Ledger Succesfully Added");
                setNewLedgerPayload({
                    ledgerType:"CHARGE",
                    amount:0,
                    description:"",
                    time:"",
                    status:true,
                    financial_account_id: props.account_id,
                    property_id:0
                });
                props.close_modal();
                props.refresh_accounts();
                setPopover(true);
                setTimeout(resetPopover, 5000);
            
            }catch (err){
                console.log(err?.response);
                console.log(err?.response?.data?.errorDesc);
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
        const newNewLedgerPayload = {... newLedgerPayload};
        newNewLedgerPayload [e.target.id] = e.target.value;
        setNewLedgerPayload(newNewLedgerPayload);
        console.log(newLedgerPayload);
    }

    const [isPopped, setPopover] = useState(false);

    function resetPopover(){
            setPopover(false);
    }

    const uniquePropertyIds = new Set();
    const [allProps, setAllProps] = useState(false);
    function handleToggle(){
        setAllProps(!allProps);
    }

    
    useEffect(() => {
            console.log("set view ledger click", ledgeClickView);
        }, [ledgeClickView]);
        
    const [propertyData, setPropertyResponse] = useState([]);
    let getPropconfig = {
        method: 'get',
        maxBodyLength: Infinity,
        url: 'http://localhost:8080/properties',
        headers: { 
          'Authorization': 'Bearer ' + auth?.accessToken
        }
      };
    
    useEffect(() => {
        const fetchProperties = async () => {
            try {
                const response = await axios.request(getPropconfig)

                // console.log("Property Data Load", {...response?.data});
                setPropertyResponse({...response?.data})

                

            } catch (err) {
                console.log(err?.response);

            }
        }

        fetchProperties();
        console.log("Property Data State", propertyData);
      }, [allProps]);

      return (
        <div>
        <div className="propertiesHeader">
          <h2>LEDGERS</h2>
          {auth?.roles === "ADMIN" || auth?.roles === "MANAGER" && <button className="openpopover" onClick={handleNewLedgPop}>+</button>}
        </div>
        <div className="ledger">
        <div className="detailsLedgerHeader">
        <table>
            <thead>
                <tr>
                    <th>Amount</th>
                    <th>Description</th>
                    <th>Associated Property</th>
                </tr>
            </thead>
            <tbody>
                {Object.keys(props.ledgers).map((i) => (
                    <tr key = {i} onClick={props.ledgers[i]?.ledgerType === "PAYMENT" ? () => handleLedgeClick(props.ledgers[i]):null}>
                        <td>${props.ledgers[i]?.amount?.toFixed(2)}</td>
                        <td>{props.ledgers[i]?.description}</td>
                        <td>{props.ledgers[i]?.property?.name}</td>
                    </tr>
                ))}              
            </tbody>
        </table>
        <div className={isNewLedgPop ? "rightPopoverOpen" :"rightPopoverClosed"} >
            <div className="closepopover">
                <button onClick={handleNewLedgPop}>X</button>
            </div>
            <div className="newLedger">
                <h2>Enter New Ledger Details</h2>
                <form action="submit" onSubmit={(e) => submit(e)}>
                    <div>
                        {props.parent_component === "properties" ?
                        <select onChange={(e) => handle(e)} value={newLedgerPayload.ledgerType} id="ledgerType" type="text">
                            <option value="EXPENSE">EXPENSE</option>
                        </select>
                        :
                        <select onChange={(e) => handle(e)} value={newLedgerPayload.ledgerType} id="ledgerType" type="text">
                            <option value="CHARGE">CHARGE</option>
                            <option value="CREDIT">CREDIT</option>
                    </select>
                        }
                    </div>

                    <label>Amount</label>
                    <input onChange={(e) => handle(e)} value={newLedgerPayload.amount} id="amount" type="text" />
                    
                    <label>Description</label>
                    <textarea onChange={(e) => handle(e)} value={newLedgerPayload.description} id="description" type="text" rows="3"/>
                    
                    <label>Associated Property</label>
                    <div className="toggle">
                        <label className="switch">
                        <input id="toggleRegister" type="checkbox" onClick={handleToggle}></input>
                        <span className="slider round"></span>
                        </label>
                        <span className="label">{allProps ? "All Properties" : "Known Properties"}</span>
                    </div>
                    <select onChange={(e) => handle(e)} value={newLedgerPayload.property_id} id="property_id">
                        <option value="">Select a Property</option>
                        {allProps === true ?
                        Object.keys(propertyData).map((i) => (
                            <option key={i} value={propertyData[i]?.property_id}>
                                {propertyData[i]?.name}
                            </option>
                        ))
                        :
                        Object.keys(props.ledgers).map((i) => {
                            const propertyId = props.ledgers[i]?.property?.property_id;

                            // Check if propertyId is not in the set to avoid duplicates
                            if (!uniquePropertyIds.has(propertyId)) {
                                uniquePropertyIds.add(propertyId);

                                return (
                                    <option key={propertyId} value={propertyId}>
                                        {props.ledgers[i]?.property?.name}
                                    </option>
                                );
                            }

                            return null; // Skip rendering for duplicates
                        }  
                        )}
                        
                    </select>
                    <button type="submit">Submit Ledger</button>
                </form>
                <div className>
                    <h1>Response Message</h1>
                </div>
            </div>
        </div>
        <div className={ledgePopped ? "transactionRecordOpen" :"offscreen"} >
            <div className="closeRecord">
                <button onClick={handleLedgeClick}>X</button>
            </div>
            <div>
                <p>Transaction Type: {ledgeClickView?.transactionTests?.paymentType}</p>
                <p>Transaction ID#: {ledgeClickView?.transactionTests?.transaction_id}</p>
                <p>Transaction Amount: ${ledgeClickView?.transactionTests?.amount.toFixed(2)}</p>
                {ledgeClickView?.transactionTests?.paymentType === "CREDIT_DEBIT" ?
                <p>Card number ending in **** {ledgeClickView?.transactionTests?.last_four_digits}</p>
                :
                <p>Account number ending in **** {ledgeClickView?.transactionTests?.last_four_digits}</p>
                }
                <p>Date Time Posted: {ledgeClickView?.transactionTests?.time[1]}/{ledgeClickView?.transactionTests?.time[2]}/{ledgeClickView?.transactionTests?.time[0]} {ledgeClickView?.transactionTests?.time[3]}:{ledgeClickView?.transactionTests?.time[4]} EST</p>
                <p>Status: {ledgeClickView?.transactionTests?.status === true ? "APPROVED" : "FAILED"}</p>
            </div>
        </div> 
    </div>
    </div>
    </div>
    );
}

export default Ledger;