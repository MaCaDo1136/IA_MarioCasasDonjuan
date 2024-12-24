/*
Written by: Mario Casas
 */

import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MainWindow from "./pages/MainWindow";
import MedRegister_InFrame from "./pages/MedRegister/MedRegister_InFrame..jsx";
// import MedLocation from "./pages/MedLocation";
// import MedInventory from "./pages/MedInventory";
// import MedExpiration from "./pages/MedExpiration";
// import MedLogFile from "./pages/MedLogFile";
import "./styles/MainWindow.css";

const App = () => {
  return (
    <Router>
      <div className="app-container">
        <Routes>
          {/* Main path*/}
          <Route path="/" element={<MainWindow />} />

          {/* complementary paths*/}
          <Route path="/med-register" element={<MedRegister_InFrame />} />
          {/* <Route path="/med-location" element={<MedLocation />} />
          <Route path="/med-inventory" element={<MedInventory />} />
          <Route path="/med-expiration" element={<MedExpiration />} />
          <Route path="/med-logfile" element={<MedLogFile />} /> */}
        </Routes>
      </div>
    </Router>
  );
};

export default App;
