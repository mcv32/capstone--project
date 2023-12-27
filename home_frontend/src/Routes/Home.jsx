import React from "react";
import { useNavigate } from "react-router-dom";

function Home(){
const navigate = useNavigate();

    return(
        <section className="bodySection">

            <div className="homeHero">
                <h1>This is our Hero Section</h1>
            </div>

            <div className="homeDemo1">
                <h1>This is for our first Demo</h1>
            </div>

            <div className="homeFeatures">
                <h1>Diagram of Features</h1>
            </div>
            
            <div className="homeTeam">
                <div> </div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
            </div>

            <div className="homeTechStack">
                <h1>Display Tech Stack</h1>
            </div>

            <div className="homeDemo2">
                <h1>This is for our second Demo</h1>
            </div>
            
            <div className="homeERD">
                <h1>Entity Relationship Diagram</h1>
            </div>

            <div className="homeDemo3">
                <h1>This is for our third Demo</h1>
            </div>
        </section>
    );
}

export default Home;