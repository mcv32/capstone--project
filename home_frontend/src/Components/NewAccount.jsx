import React from "react";

function NewAccount(){
    return(
        <div className="newAccount">
            <h2>Enter New Account Details</h2>
            <form action="submit">
                <label >First Name</label>
                <input type="text" />
                
                <label >Last Name</label>
                <input type="text" />
                <input type="text" />

                <label >Email</label>
                <input type="text" />

                <label >Property</label>
                <div>
                    <select>
                        <option >123 Main St.</option>
                        <option >103 Catherine Lane</option>
                        <option >5 Lee Place</option>
                    </select>
                </div>

                <button>Add New Account</button>
            </form>
        </div>
    );
}

export default NewAccount;