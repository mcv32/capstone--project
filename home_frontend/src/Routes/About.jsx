import React from "react";
import { useNavigate } from "react-router-dom";

function About(){
    const navigate = useNavigate();

    return(
        <section className="bodySection">
            <div className="aboutHero">
                <h1>About Cody's Property Management Inc.</h1>
                <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjuuTIyQNo-GiN-b4X0ODCCAYRU2g8GuAuUpVQ-zgHCui78CB_HKwSVa5kr4DNyQaGRNA&usqp=CAU" alt="" />
            </div>
            <div className="aboutSection">
                <h2>Our Story</h2>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Enim, ea asperiores! Qui eos possimus voluptatum ducimus, illo ad deleniti aperiam maiores sunt natus suscipit officia? Eveniet reiciendis dicta minus est.</p>
            </div>
            <div className="aboutSection">
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Enim, ea asperiores! Qui eos possimus voluptatum ducimus, illo ad deleniti aperiam maiores sunt natus suscipit officia? Eveniet reiciendis dicta minus est.</p>
                <h2>Life in Martinsville</h2>
            </div>

        </section>
    )
}

export default About;