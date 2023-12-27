import { Outlet } from "react-router-dom";
import Heading from "./Components/Heading";
import Footing from "./Components/Footing";
import backgroundVideo from "./static/backgroundVideo_crop.mp4";
import { useLocation } from "react-router-dom";

const Layout = () => {
    const location = useLocation();
    const isDashboard = location.pathname.includes("dashboard");
    
    
    return (
        <main className="App">
            <div className="content">
                <video src={backgroundVideo} autoPlay loop muted/>
                <Heading />
                <Outlet/>
                <Footing />
            </div>
        </main>
    );
}

export default Layout;