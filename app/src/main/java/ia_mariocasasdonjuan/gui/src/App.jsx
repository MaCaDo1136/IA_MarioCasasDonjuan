/*
Written by: Mario Casas
 */

import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MainWindow from "./pages/MainWindow";
import MedRegister_InFrame from "./pages/MedRegister/MedRegister_InFrame..jsx";
// import MedLocation from "./pages/MedLocation";
import MedInventory from "./pages/MedInventory/MedInventory_MainFrame.jsx";
import MedInventoryInput from "./pages/MedInventory/MedInventory_InFrame.jsx";
import MedInventoryOutput from "./pages/MedInventory/MedInventory_OutFrame.jsx";
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
          <Route path="/med-inventory" element={<MedInventory />} />
          <Route path="/med-inventory/input" element={<MedInventoryInput />} />
          <Route
            path="/med-inventory/output"
            element={<MedInventoryOutput />}
          />
          {/* <Route path="/med-location" element={<MedLocation />} />
          <Route path="/med-expiration" element={<MedExpiration />} />
          <Route path="/med-logfile" element={<MedLogFile />} /> */}
        </Routes>
      </div>
    </Router>
  );
};

export default App;
