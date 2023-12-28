import './App.css';
import RequireAuth from './Routes/RequireAuth';
import {Routes, Route} from 'react-router-dom';
import Layout from './Layout';
import Home from './Routes/Home';
import About from "./Routes/About";
import Contact from './Routes/Contact';
import Login from './Routes/Login';
import Unauthorized from './Routes/Unauthorized';
import Dashboard from './Routes/Dashboard';
import Missing from './Routes/Missing';


function App() {

  return (
    <Routes>
      <Route path="/" element={<Layout/>}>
         {/* public routes */}
        <Route path="/" element={<Home />} />
        <Route path="about" element={<About />} />
        <Route path="contact" element={<Contact />} />
        <Route path="login" element={<Login />} />
        <Route path="unauthorized" element={<Unauthorized />} />

        {/* we want to protect these routes */}
        <Route element={<RequireAuth allowedRoles={['USER', 'MANAGER']}/>}>
          <Route path="dashboard" element={<Dashboard />} />
        </Route>

        {/* catch all */}
        <Route path="*" element={<Missing />} />
      </Route>
    </Routes>
  );
}

export default App;
