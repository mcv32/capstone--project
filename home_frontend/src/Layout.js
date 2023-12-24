import { Outlet } from "react-router-dom";
import Heading from "./Components/Heading";
import Footing from "./Components/Footing";
import backgroundVideo from "./Static/backgroundVideo_crop.mp4";

const Layout = () => {
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