import React, {useEffect, useState} from "react";
import { NavLink, Link } from "react-router-dom";
import useAuth from "../Hooks/useAuth";
import Axios from "axios";

function AccountID(props){

    console.log("AccountID userData prop pass", props);

    const { auth, setAuth } = useAuth();
    const fullName = props.userData?.f_name + " " + props.userData?.l_name;
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
            firstName: props.userData?.f_name,
            lastName: props.userData?.l_name,
            email: props.userData?.email,
            telephone: props.userData?.phone_number
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
            old_email: props.userData?.email,
            new_email: userDetailsPayload.email,
            phoneNumber: userDetailsPayload.telephone
        }
      };

    const saveDetails = async (form) => {
            try {
                console.log("Save data config", userConfig);
                form.preventDefault();
                const response = await Axios.request(userConfig)
                
                console.log({...userConfig})
                console.log("User Details Response", response);
                props.refresh();
                handlePop();

    
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
                <img src="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBwgHBhIIBxEPFhIXEBUPEBgRDg8WFRMVIBEiGCASFR8kHSggGCYlJxUeITEhJSkrLi4uFx8zODMsNygtLisBCgoKDQ0NEg0NDysZExkrKysrKysrKysrKysrKzcrKysrKysrKysrKysrKysrKysrKysrKysrKysrKysrKysrK//AABEIAMgAyAMBIgACEQEDEQH/xAAcAAEAAgIDAQAAAAAAAAAAAAAABwgFBgECAwT/xAA6EAACAQIDBAcFBQkBAAAAAAAAAQIDBQQGEQcSMWETISJBUYGhFDJxgpEjQrHB0RYzUmJyc5KywuH/xAAWAQEBAQAAAAAAAAAAAAAAAAAAAQL/xAAWEQEBAQAAAAAAAAAAAAAAAAAAEQH/2gAMAwEAAhEDEQA/AJxAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADrKSit6XDi9XwA7AjfNW1qz2icsNa08RVWqbhLdoxfOff8AKn8SOrltYzZjZPoKtKjHuVGjFtfNPVgWM1RyVc/b3Nm/ve34n/KGn00MtbNrGa8FJdPUpVo96rUYpv5o6MEWNBHGVdrVnu9SOGuieGqvRJzlvUZPlPu+ZL4kixkpR1j4armB2AAAAAAAAAAAAAAAAAAAAAAAB5VakKUHUqNJJOTbeiSXFtkAbSNomIv9eVttEpQwie7Jp6SxHN+EPCPfxfgbZtxzRPBYKOX8G9J1Y9JX0fWqW9oofM09eS5kIhQABQAACQtmu0TEWCvG23eUpYRvdi31yw3NeMPFd3FeBHoCLg0qkKsFUptNNKSaeqafBp+B6kT7Ds0SxmBll/GS1lSW/QbfW6W9o4fK2tOT5EsBAAAAAAAAAAAAAAAAAAAA+AOk3uxbXg2BVvPt0leM4YvFt6rppU6fKEPs4/hr5mAO1WTnVlKXfJt/Fs6hQABQAAAABn8h3SVnzfhMWnoumjTqc4T+zl+OvkWnXAp3Sk4VYyj3STXxTLgwe9FN+CYTXcABAAAAAAAAAAAAAAAAAPgABUi/YKVtvmIwU+MK9SHkpvT00PgJL24ZfngcwRvFGP2ddKM+rqVaMdPWKT8mRoFwAAUAAAAAffYcFK5XzD4KHGdenDyc1r6alt1wIE2H5fnjswSvFaP2dBOMHp1OtKOnpFt/MiewmgACAAAAAAAAAAAAAAAAAAAw+ZrHhMx2apbMd7s12Wl2oTXu1I80/wBCsmYrFjsu3SdvuMdJLri0uzUh3VIPvT9OBbMweacsWzNFveEucNdNXTmuqdOXjF/lwYFVQbvmnZhfrHUdTCQeIo9bUqMe2l/PT4+a1RpM04T3J6p8Gn1NeQacAHME5z3Iat8El1t+QHBk8u2PHZiukLfbo6yfXJtdmnDvqTfcl68DZMrbML9fJqpi4PD0eLlWjpNr+Snx83oic8rZYtmV7esJbIaa6OpN9c6kvGT/AC4IJXplmx4TLlmp2zA+7Bdptdqc371R82/0MwAEAAAAAAAAAAAAAAAAAAAAPOpUhSg5zaSSbbb0SS4tsD0MDmPNlly3T3rrXjGWmsYLtVJfCK6/N9RGufNrM5Slb8qvRdcZ4hrVv+wn/s/Jd5EletVxFZ1sRKUpye9KU5SlKT8W31sLEt3rbXUlJwsmFil3TxE9W+e5H9TRb3ni93uWuPeGfwwWHbXnKLfqa2AR36SXSb/Z1119yOn000NhsmeL3ZJa4B4ZfHBYdN+cYp+prYCpesu2uopKF7wsWu+eHno1z3JfqSVlzNllzJT3rVXjKWmrg+zUj8Yvr811FVzvQrVcPWVbDylGcXvRlCUoyi/FNdaCRcMEL5D2szjKNvzU9V1RhiEtGv76X+y813kyU6kKsFODTTSaaeqafBphHoAAAAAAAAAAAAAAAAAADIE2rZ+ndsROyWeb9mjLdrTi/wB/JcY6/wACf1fLQ3TbHmt2Syq14KWleunFtPrp0eEp8m/dXn4EABcAAFAAAAAAAACStlOf52nEQsl4m/ZpS3aM5P8AcSfCOv8AA39Hy1I1ARcYEcbG81u92V2zGy1r0FGKbfXUo8Iz5te6/LxJHCAAAAAAAAAAAAAAcN6LU5MBnq4O1ZQxeNhxjQmovwlLsr1kgK757vcswZqr49PWG+6VHlSh1R+vW/MwA00WnkA0AAAAAAAAAAAAAM/kS9yy/mqhj29Ib6pVudKfVL6dT8i0yeq1KdaarTyLT5FuDuuUMJjZ8ZUIKT8ZR7L9YsMs+AAAAAAAAAAAAAGh7aq0qWQqsV96rRg/h0qf5G+Gq7SrNVvuTq+Ewq1qJRrU0uMpQe9ufFrVeYFYwGtP/QGgAAAAAAAAAAAAALFbFazqZBpRf3ataC+HSt/mV14lm9mlmq2LJ1DCYpNVGpVqi74ynLe3PilovIJrawAEAAAAAAAAAAAAAEWbQtltO715XTL+7CvJudSnLs06su+af3JP6PlxIXulrx9oxXst0pVKc/CpDTXmnwa5ot2fJcLdgrlh/Z7hSp1Id8akFJeoFRAWCu+x/LeObngumoS7ujnvQXyy19GjUbhsTuUHrb8XQmu7pac4P6reQWorBvGJ2T5uovsUaU/6MTD/AK0MdV2eZupvSWBrfLKlL8JArWAbF+wmbNdPYMV/jH9T1pbPM3VHpHA1vmlSj+MgVrAN4w2yfN1Z9ujSh/XiYf8AOpnLfsTuVR63DF0ILv6KnOo/q91AqKz67Xa8fd8V7La6VSpPwpw105t8EubJ3s+x/LWBanjemry7+knuw/xjp6tm82+3YK24f2e30qdOHdGnBRXoCo32e7LadorxumYN2deLU6dOPap0pd02/vyX0XPiSmAEAAAAAAAAAAAAAAAAAAAAAAAAcaI5AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD//Z"/>
                {auth?.email === props.userData?.email && <Link onClick={handlePop}>Edit Account</Link>}
            </div>
            <div className="accountRight">
                {props.userData?.appUserRole === "MANAGER" && <h2>MANAGER</h2>}
                {props.userData?.appUserRole === "ADMIN" && <h2>MANAGER</h2>}
                <h3>{fullName}</h3>
               
               {/* M: tweaked title */}
                <p>Contact Information: </p>
                <p style={{ fontWeight: 'lighter' }}>{props.userData?.phoneNumber}</p>
                <p style={{ fontWeight: 'lighter' }}>{props.userData?.email}</p>
            </div>
            <div className={isPopped ? "accountDetailsOpen" :"offscreen"} >
                <div className="closeRecord">
                    <button onClick={handlePop}>X</button>
                </div>
                <div className="accountDetails">
                    <h1 style={{color:"black"}}>Personal Account Details</h1>
                    <form typeof="submit">
                    <img src="data:image/jpeg;base64,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" alt="avatar" />

                    {/* M: got rid of class name and changed avatar img */}
                        <label>Avatar URL</label>
                        <input 
                            type="url" 
                            name="" 
                            id="" value="data:image/jpeg;base64,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" />

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