import React from "react";
import { useNavigate } from "react-router-dom";
import ImageMagnifier from "../Components/ImageMagnifier";
import HomeERD from "../static/ERD_Capstone.png"
import cavemanMeme from "../static/cavemanMemepic.png"
import Spline from '@splinetool/react-spline';

function Home() {
    const navigate = useNavigate();

    return (
        <section className="bodySection snaps-inline">
                {/* <div className="media-scroller snaps-inline">
                </div> */}

        <div className="cavemanMeme">
        <img src={cavemanMeme} />
           <div className = "cavemanText">
                <h1 id="upperquote">"</h1>
                <h1>So Easy I NEVER Miss Rent Payments on my Cave! </h1>
                <h1 id="lower_quote">"</h1>
           </div>
        </div>

       
       
        <div className="media-element homeHero" id="homeHero">
        <div className="text-container">
            <h1 className="homeherrosolo">
        Welcome to our Easilease Application, Powered by ESJ INC.
        </h1>
            <h4>• Seamless Transactions</h4>
            <h4>• Secure Processing</h4>
            <h4>• User-Friendly</h4>

        </div>
        </div>

        <div className="homeTeam" id="homeTeam">
                    <div> </div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                </div>
                
        <div className="homeFeatures">
            <h1 className="features">
            Our Goals
            </h1>
                <div className="boxDiagram">
                    <div className="box">Improved Security</div>
                    <div className="box">Tenant / Dashboard Portal</div>
                    <div className="box">User Registration and Authentication</div>
                    <div className="box">Transaction Processing / Payment Portal</div>
        </div>
        </div>
               
               
                {/* <div className="media-element homeFeatures" id="homeFeatures">
                    <h1>Diagram of Features</h1>
                </div> */}

                {/* <div className="media-element">
                </div> */}

                <div className="media-element homeTechStack" id="homeTechStack">
                    <Spline scene="https://prod.spline.design/jTj8tnjowJhFSwVa/scene.splinecode" />
                </div>

        <div className="media-element homeERD snaps-inline_child" id="homeERD">
            <h1 className="erd">
                Entity Relationship Diagram</h1>
            <ImageMagnifier imgUrl={HomeERD} />
        </div>

                <div className="problemStatement">
            <h1 className="problem">
                Challenges
            </h1>
            <div className="problemDiagram">
                <div className="problemBox">JWT</div>
                <div className="problemBox">Merge Conflicts</div>
                <div className="problemBox">Routes</div>
                <div className="problemBox">Optimization</div>
            </div>
            </div>

            </section>

    );
}

export default Home;