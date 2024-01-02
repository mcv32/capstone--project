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
            <form action="submit" onSubmit={(e) => submit(e)}>
                <label>Property Name</label>
                <input onChange={(e) => handle(e)} value={newPropertyPayload.name} id="name" type="text"  />
                
                <label>Street Address</label>
                <input onChange={(e) => handle(e)} value={newPropertyPayload.address_street} id="address_street" type="text"   />
                <input onChange={(e) => handle(e)} value={newPropertyPayload.address_line_2} id="address_line_2" type="text"   />

                <label>City</label>
                <input onChange={(e) => handle(e)} value={newPropertyPayload.city} id="city" type="text"/>

                <label>State</label>
                <input onChange={(e) => handle(e)} value={newPropertyPayload.state} id="state" type="text"/>

                <label>Postal Code</label>
                <input onChange={(e) => handle(e)} value={newPropertyPayload.zip} id="zip" type="text"/>

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