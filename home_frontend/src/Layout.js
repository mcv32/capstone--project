import { Outlet } from "react-router-dom";
import Heading from "./Components/Heading";
import Footing from "./Components/Footing";

const Layout = () => {
    return (
        <main className="App">
            <Heading />
            <Outlet/>
            <Footing />
        </main>
    );
}

export default Layout;