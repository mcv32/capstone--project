import React from "react";

function NewProperty(){
    return(
        <div className="newProperty">
            <h2>Enter New Property Details</h2>
            <form action="submit">
                <label htmlFor="PropertyName">Property Name</label>
                <input type="text" />
                
                <label htmlFor="StreetAddress">Street Address</label>
                <input type="text" />
                <input type="text" />

                <label htmlFor="City">City</label>
                <input type="text" />

                <label htmlFor="State">State</label>
                <input type="text" />

                <label htmlFor="Postal Code">Postal Code</label>
                <input type="text" />

                <button>Add New Property</button>
            </form>
        </div>
    );
}

export default NewProperty;