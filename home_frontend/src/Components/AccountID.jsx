import React, {useEffect, useState} from "react";
import { NavLink, Link } from "react-router-dom";
import useAuth from "../Hooks/useAuth";
import Axios from "axios";

function AccountID({...userData}){

    const { auth, setAuth } = useAuth();
    const fullName = userData?.f_name + " " + userData?.l_name;
    const [isPopped, setIsPopped] = useState(false); 
    const [userDetailsPayload, setUserDetailsPayload] = useState({
        firstName: "",
        lastName: "",
        email: "",
        telephone: ""

    });

    function handlePop(){
        setIsPopped(!isPopped);
        setUserDetailsPayload({
            firstName: userData?.f_name,
            lastName: userData?.l_name,
            email: userData?.email,
            telephone: userData?.phone_number
        });
    }

    function handleInput(e){
        const newUserDetailsPayload = {... userDetailsPayload};
        newUserDetailsPayload[e.target.id] = e.target.value;
        setUserDetailsPayload(newUserDetailsPayload);
        console.log(newUserDetailsPayload);
    }

    let userConfig = {
        method: 'post',
        maxBodyLength: Infinity,
        url: 'http://localhost:8080/users/update',
        headers: { 
          'Authorization': 'Bearer ' + auth?.accessToken
        },
        data : {
            firstName: userDetailsPayload.firstName,
            lastName: userDetailsPayload.lastName,
            old_email: userData?.email,
            new_email: userDetailsPayload.email,
            phoneNumber: userDetailsPayload.telephone
        }
      };

    const saveDetails = async (form) => {
            try {
                console.log(userConfig);
                form.preventDefault();
                const response = await Axios.request(userConfig)
                
                console.log({...userConfig})
                console.log("User Details Response", response);
    
                    //display success message
                    //refresh dashboard data
                    //toggle popover off
                    
                    // navigate(from, {replace:true});
                
                }catch (err){
                    if (err.response.status === 400){
                        // console.log(err.response);
                        // console.log(err.response.data.errorDesc);
                        // setResMsg(err.response.data.errorDesc);
                        // setTimeout(resetResMsg, 5000);
                    } else {
                        // setResMsg("Unknown Error Occured. Details Not Saved. Please Try Again");
                        // setTimeout(resetResMsg, 5000);
                    }
            }      
    
    }

    
    return(
        <div className="accountBlock">
            <div className="accountLeft">
                <img src="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg"/>
                {auth?.email === userData?.email && <Link onClick={handlePop}>Edit Account</Link>}
            </div>
            <div className="accountRight">
                {auth?.roles === "MANAGER" ? <h2>MANAGER</h2> : null}
                <h3>{fullName}</h3>
               
               {/* M: tweaked title */}
                <p>Contact Information: </p>
                <p>{userData?.phoneNumber}</p>
                <p>{userData?.email}</p>
            </div>
            <div className={isPopped ? "accountDetailsOpen" :"offscreen"} >
                <div className="closeRecord">
                    <button onClick={handlePop}>X</button>
                </div>
                <div className="accountDetails">
                    <h1 style={{color:"black"}}>Personal Account Details</h1>
                    <form typeof="submit">
                    <img src="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg" alt="avatar" />
                    
                    {/* M: gave it a different class name */}
                    <label className="accountIdPair">Avatar URL</label>
                    <input type="url" name="" id="" value="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg" placeholder="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg" />

                        <label >First Name</label>
                        <input 
                            id="firstName" type="text" 
                            onChange={(e) => handleInput(e)}
                            value={userDetailsPayload.firstName}/>
                        <label >Last Name</label>
                        <input 
                            id="lastName" type="text" 
                            onChange={(e) => handleInput(e)}
                            value={userDetailsPayload.lastName}/>
                        <label >Email</label>

                        <input 
                            id="email" type="email" 
                            onChange={(e) => handleInput(e)}
                            value={userDetailsPayload.email}/>
                        <label >Phone Number</label>
                        <input 
                            id="telephone" type="tel" 
                            onChange={(e) => handleInput(e)}
                            value={userDetailsPayload.telephone}/>
                        <button onClick={saveDetails}>Save</button>
                    </form>
                </div>
            </div>
        </div>
        // ryan's commented code
        // :
        // <div className="accountBlock">
        //     <div className="accountLeft">
        //         <img src="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg"/>
        //         <Link onClick={handlePropPop}>Edit Account</Link>
        //     </div>
        //     <div className="accountRight">
        //         <h2>{fullName}</h2>
        //         <p>{userData?.phoneNumber}</p>
        //         <p>{userData?.email}</p>
        //     </div>
        //     <div className={propPopped ? "accountDetailsOpen" :"offscreen"} >
        //         <div className="closeRecord">
        //             <button onClick={handlePropPop}>X</button>
        //         </div>
        //         <div className="accountDetails">
        //             <h1 style={{color:"black"}}>Edit Account Details</h1>
        //             <form typeof="submit">
        //                 <img src="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg"/>
        //                 <label >Avatar URL</label>
        //                 <input type="url" name="" id="" value="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg" placeholder="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg"/>
        //                 <label >First Name</label>
        //                 <input type="text" name="" id="" placeholder={userData.f_name}/>
        //                 <label >Last Name</label>
        //                 <input type="text" name="" id="" value="Phelan" placeholder="Phelan"/>
        //                 <label >Phone Number</label>
        //                 <input type="tel" name="" id="" value="908-685-1182" placeholder="908-685-1182"/>
        //                 <label >Email</label>
        //                 <input type="email" name="" id="" value="cody@fiserv.com" placeholder="cody@fiserv.com"/>
        //                 <button>Save Personal Details</button>
        //             </form>
        //         </div>
        //     </div>
        // </div>
        

// melissa's code
        //                 <input type="email" name="" id="" value="cody@fiserv.com" placeholder="cody@fiserv.com"/>
        //                 <button>Save Personal Details</button>
        //             </form>
        //         </div>
        //     </div>
        // </div>
        // :
        // <div className="accountBlock">
        //     <div className="accountLeft">
        //         <img src="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg"/>
        //         <Link onClick={handlePropPop}>Edit Account</Link>
        //     </div>
        //     <div className="accountRight">
        //         <h2>{fullName}</h2>
        //         <p>{userData.phoneNumber}</p>
        //         <p>{userData.email}</p>
        //     </div>
        //     <div className={propPopped ? "accountDetailsOpen" :"offscreen"} >
        //         <div className="closeRecord">
        //             <button onClick={handlePropPop}>X</button>
        //         </div>
        //         <div className="accountDetails" style={{ textAlign: "center" }}>
        //             <h1 style={{ color: "black", paddingTop: "60px" }}>Edit Account Details</h1>
        //             <div className="formContainer">
        //                 <form typeof="submit">
        //                 <img src="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg" alt="Avatar" />
        //                 <div className="labelInputContainer">
        //                 <div className="labelInputPair">
        //                     <label>Avatar URL</label>
        //                     <input type="url" name="" id="" value="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg" placeholder="Enter Avatar URL" />
        //                 </div>
        //                 <div className="labelInputPair">
        //                     <label>First Name</label>
        //                     <input type="text" name="" id="" value="Cody" placeholder="Enter First Name" />
        //                 </div>
        //                 <div className="labelInputPair">
        //                     <label>Last Name</label>
        //                     <input type="text" name="" id="" value="Phelan" placeholder="Enter Last Name" />
        //                 </div>
        //                 <div className="labelInputPair">
        //                     <label>Phone Number</label>
        //                     <input type="tel" name="" id="" value="908-685-1182" placeholder="Enter Phone Number" />
        //                 </div>
        //                 <div className="labelInputPair">
        //                     <label>Email</label>
        //                     <input type="email" name="" id="" value="cody@fiserv.com" placeholder="Enter Email" />
        //                 </div>
        //                 </div>
        //                 <button>Save</button>
        //                 </form>
        //             </div>
        //         </div>
        //     </div>
        // </div>

    );
}

export default AccountID;