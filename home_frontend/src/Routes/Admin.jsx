import React, {useState, useEffect} from "react";
import useAuth from "../Hooks/useAuth";
import axios from "axios";


function Dashboard(){

    const { auth, setAuth } = useAuth();
    const [responseData, setResponse] = useState();
    const [userData, setUserData] = useState();

    const [refresh, setRefresh] = useState(true);
    function refreshData(){
        setRefresh(!refresh);
        console.log(refresh);
    }

    let config = {
        method: 'get',
        maxBodyLength: Infinity,
        url: 'http://localhost:8080/users',
        headers: { 
          'Authorization': 'Bearer ' + auth?.accessToken
        }
      };

    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const response = await axios.request(config);

                console.log("Admin Data Load", {...response.data});
                setResponse({...response.data});
                

            } catch (err) {
                console.log(err.response);

            }
        }

        fetchUsers();
      }, [refresh]);

    useEffect (() =>{
    console.log("Set Admin Data", responseData)
    }, [responseData] )

    const [rolePayload, setRolePayload] = useState({
        appUserRole: "USER",
        email: ""
    });
    
    function changeRole(e){
        const newRolePayload = {... rolePayload}
        newRolePayload [e.target.id] = e.target.value
        newRolePayload.email = e.target.name 
        setRolePayload(newRolePayload)
        console.log("Set Change Role Payload", rolePayload)
        
    }

    useEffect (() =>{
        const postRole = async () => {
            try {
                const response = await axios.request(roleConfig)

                console.log("Role Change Response", response);
                setRefresh(!refresh)
                // setAccountResponse({...response?.data})
                // console.log("Account Data State", Object.keys(accountData));

                

            } catch (err) {
                console.log(err?.response);

            }
        }
        postRole();
        }, [rolePayload] )

    let roleConfig = {
        method: 'post',
        maxBodyLength: Infinity,
        url: 'http://localhost:8080/users/update/role',
        headers: { 
          'Authorization': 'Bearer ' + auth?.accessToken
        },
        data : {
            role: rolePayload.appUserRole,
            email: rolePayload.email
        }
      };

    
    return(
        // auth?.roles === "ADMIN" ?
        <section className="dashboard">
            <div className="dashHead">
                <h1>Welcome, {userData?.f_name}</h1>
                <button onClick={refreshData}>refresh</button>

            </div>
            <div className="dashBody">
                <div className="Admin">
                    <table>
                        <thead>
                            <tr>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Email</th>
                                <th>Current Role</th>
                                <th>Set New Role</th>
                            </tr>
                        </thead>
                        <tbody>
                                {responseData !== null && responseData !== undefined && Object.keys(responseData).map((i) => (
                                <tr key = {i} >
                                    <td>{responseData[i]?.f_name}</td>
                                    <td>{responseData[i]?.l_name}</td>
                                    <td>{responseData[i]?.email}</td>
                                    <td>{responseData[i]?.appUserRole}</td>
                                    <select onChange={(e) => changeRole(e)} name = {responseData[i]?.email} id="appUserRole">
                                        <option value="">Select New Role</option>
                                        <option value="USER">USER</option>
                                        <option value="MANAGER">MANAGER</option>
                                        <option value="ADMIN">ADMIN</option>
                                    </select>
                                </tr>
                            ))} 
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
    );
    
}

export default Dashboard;