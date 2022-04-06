
import { BrowserRouter, Route, Routes } from "react-router-dom"
import './App.css';
import {Dashboard} from "./pages/dashboard/Dashboard";
import LoginPage from './pages/LoginPage'
import RegisterPage from "./pages/RegisterPage";


function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LoginPage/>} />
        <Route path="/register" element={<RegisterPage/>}/>
        <Route path="/dashboard" element={<Dashboard/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
