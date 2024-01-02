import React, {useState, useEffect} from "react";
import axios from "axios";
import useAuth from "../Hooks/useAuth";

function NewProperty(){
    const { auth, setAuth } = useAuth();
    const [newPropertyPayload, setNewPropertyPayload] = useState({
        address_street: "",
        address_line_2: "",
        balance: 0,
        city: "",
        state: "",
        name: "",
        property_balance: 0,
        status: "",
        zip: ""
    })


    const [resMsg, setResMsg] = useState();


    let config = {
        method: 'post',
        maxBodyLength: Infinity,
        url: 'http://localhost:8080/properties/create',
        headers: { 
          'Authorization': 'Bearer ' + auth?.accessToken
        },
        data : {
            address_street: newPropertyPayload.address_street,
            address_line_2: newPropertyPayload.address_line_2,
            balance: newPropertyPayload.balance,
            city: newPropertyPayload.city,
            state: newPropertyPayload.state,
            name: newPropertyPayload.name,
            property_balance: newPropertyPayload.property_balance,
            status: newPropertyPayload.status,
            zip: newPropertyPayload.zip
        }
      };

    const submit = async (e) => {
        try {
            e.preventDefault();
            const response = await axios.request(config)

                console.log(response);         
                setResMsg("Property Succesfully Added");
                setNewPropertyPayload({
                    address_street: "",
                    address_line_2: "",
                    balance: 0,
                    city: "",
                    state: "",
                    name: "",
                    property_balance: 0,
                    status: "",
                    zip: ""
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
        const newNewPropertyPayload = {... newPropertyPayload};
        newNewPropertyPayload [e.target.id] = e.target.value;
        setNewPropertyPayload(newNewPropertyPayload);
        console.log(newPropertyPayload);
    }

    const [isPopped, setPopover] = useState(false);

    function resetPopover(){
            setPopover(false);
    }
    
    return(
        <div className="newProperty">
            <h2>Enter New Property Details</h2>
            <form action="submit">
                <label htmlFor="PropertyName">Property Name</label>
                <input type="text" />
                
                <label htmlFor="StreetAddress">Street Address</label>
                <input type="text" />
                <input type="text" />

    <div class="labelInputPair">
      <label>State</label>
      <input onChange={(e) => handle(e)} value={newPropertyPayload.state} id="state" type="text"/>
    </div>

    <div class="labelInputPair">
      <label>Postal Code</label>
      <input onChange={(e) => handle(e)} value={newPropertyPayload.zip} id="zip" type="text"/>
    </div>

                <label>Status</label>
                <select onChange={(e) => handle(e)} value={newPropertyPayload.status} id="status" type="text"  >
                    <option value="OCCUPIED">OCCUPIED</option>
                    <option value="VACANT">VACANT</option>
                </select>

    <button>Add New Property</button>
  </form>
</div>


    );
}

export default NewProperty;