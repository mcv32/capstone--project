import React from "react";
import { Form } from "react-router-dom";

function Contact(){
    return(
        <section className="bodySection">

        <h2>Get in Touch</h2>
        <p>Have a question or feedback? Reach out to us using the form below:</p>
            <form className="contactForm">
                <label htmlFor="name">Your Name</label>
                <input type="text" id="name" name="name" required />

                <label htmlFor="email">Your Email</label>
                <input type="email" id="email" name="email" required />

                <label htmlFor="message">Your Message</label>
                <textarea id="message" name="message" rows="4" required></textarea>

                <button type="submit">Send Message</button>
            </form>

        </section>
    );
}

export default Contact;