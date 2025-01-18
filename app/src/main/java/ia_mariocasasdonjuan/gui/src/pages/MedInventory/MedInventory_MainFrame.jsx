/*
Written by: Mario Casas
 */
import React from "react";
import { Link } from "react-router-dom"; // Asumiendo que estás usando react-router para navegación
import "../../styles/MedInventory_MainFrame.css";

const MedInventory_MainFrame = () => {
  return (
    <div className="med-inventory-main-container">
      <h1>Medicine Inventory</h1>
      <div className="button-group">
        <Link to="/med-inventory/input">
          <button className="primary">Input</button>
        </Link>
        <Link to="/med-inventory/output">
          <button className="menu-button">Output</button>
        </Link>
        <Link to="/">
          <button className="back-button">Back</button>
        </Link>
      </div>
    </div>
  );
};

export default MedInventory_MainFrame;
