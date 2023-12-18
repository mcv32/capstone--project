import React from "react";

function Heading(){

        let headState = true;

        function setWelcome(){
            if (headState === true){
                return(
                    <h1>Welcome Home</h1>
                );
            } else {
                return(
                    <h1>Please Login to Continue</h1>
                );
            }
        }
    
    return(
        <div className="heading">
            {setWelcome()}
        </div>
    );
}

export default Heading;
