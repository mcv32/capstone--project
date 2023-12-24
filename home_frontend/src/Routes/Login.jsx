import React, {useState} from "react";
import LoginForm from "../Components/LoginForm";
import { Link } from "react-router-dom";

function Login(){


    return(
        <section className="bodySection">
            <LoginForm />
            {/* <Link to="/dashboard">Dev Link to Dashboard</Link>/ */}
        </section>
    );
}

export default Login;