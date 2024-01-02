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
                    <h1>This is our Hero Section</h1>
                </div>

                <div className="media-element homeDemo1" id="homeDemo1">
                    <h1>This is for our first Demo</h1>
                </div>

                <div className="homeFeatures">
            <div className="boxDiagram">
                    <div className="box">Improved Security</div>
                    <div className="box">Tenant / Dashboard Portal</div>
                    <div className="box">Messaging Service</div>
                    <div className="box">User Registration and Authentication</div>
                    <div className="box">Transaction Processing</div>

            </div>
            </div>
               
               
                {/* <div className="media-element homeFeatures" id="homeFeatures">
                    <h1>Diagram of Features</h1>
                </div> */}

                {/* <div className="media-element">
                </div> */}
                <div className="homeTeam" id="homeTeam">
                    <div> </div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                </div>

                <div className="media-element homeTechStack" id="homeTechStack">
                    <Spline scene="https://prod.spline.design/jTj8tnjowJhFSwVa/scene.splinecode" />
                </div>

                <div className="media-element homeDemo2" id="homeDemo2">
                    <h1>This is for our second Demo</h1>
                </div>

                <div className="media-element homeERD" id="homeERD">
                    <h1>Entity Relationship Diagram</h1>
                    <ImageMagnifier imgUrl={HomeERD} />
                </div>

                <div className="media-element homeDemo3" id="homeDemo3">
                    <h1>This is for our third Demo</h1>
                </div>

            </section>

    );
}

export default Home;