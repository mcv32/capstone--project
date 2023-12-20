import React from "react";
import { useNavigate } from "react-router-dom";

function About(){
    const navigate = useNavigate();

    return(
        <section className="bodySection">
            <h1>About Page</h1>
        </section>
    )
}

export default About;