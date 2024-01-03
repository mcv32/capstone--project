import React from "react";
import { useNavigate } from "react-router-dom";
import ImageMagnifier from "../Components/ImageMagnifier";
import HomeERD from "../static/Home_ERD_v2.png"
import Spline from '@splinetool/react-spline';

function Home() {
    const navigate = useNavigate();

    return (
            <section className="bodySection snaps-inline">
                {/* <div className="media-scroller snaps-inline">
                </div> */}
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
                    {/* <Spline scene="https://prod.spline.design/jTj8tnjowJhFSwVa/scene.splinecode" /> */}
                </div>

                <div className="media-element homeERD" id="homeERD">
                    <h1 className="erd">
                        Entity Relationship Diagram</h1>
                    <ImageMagnifier imgUrl={HomeERD} />
                </div>

            </section>

    );
}

export default Home;