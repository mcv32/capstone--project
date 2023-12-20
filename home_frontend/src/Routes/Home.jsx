import React from "react";
import { useNavigate } from "react-router-dom";

function Home(){
const navigate = useNavigate();

    return(
        <section className="bodySection">
            <h1>Home Page</h1>
        </section>
    );
}

export default Home;