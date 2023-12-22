import { Outlet } from "react-router-dom";
import Heading from "./Components/Heading";
import Footing from "./Components/Footing";
import backgroundVideo from "./static/backgroundVideo_crop.mp4";

const Layout = () => {
    return (
        <main className="App">
            <video src={backgroundVideo} autoPlay loop muted/>
            <div className="content">
            <Heading />
            <Outlet/>
            <Footing />
            </div>
        </main>
    );
}

export default Layout;