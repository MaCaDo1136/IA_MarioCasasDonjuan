import React from "react";
import { Link } from "react-router-dom";
import "../styles/MainWindow.css";
import LogoSantaMariaReina from "../assets/images/LogoSantaMariaReina.jpg";

import "./MedRegister/MedRegister_InFrame..jsx";

const MainWindow = () => {
  return (
    <div className="container">
      <img src={LogoSantaMariaReina} alt="Parroquia Santa Maria Reina" />
      <h1>Dispensario</h1>
      <div className="button-container">
        <Link to="/med-register">
          <button className="primary">Registrar Medicamentos</button>
        </Link>
        {/* <Link to="/med-location"> */}
        <button className="primary">Ubicación</button>
        {/* </Link> */}
        {/* <Link to="/med-inventory"> */}
        <button className="primary">Inventario</button>
        {/* </Link> */}
        {/* <Link to="/med-expiration"> */}
        <button className="secondary">Datos de Expiración</button>
        {/* </Link> */}
        {/* <Link to="/med-logfile"> */}
        <button className="secondary">Historial de Movimientos</button>
        {/* </Link> */}
      </div>
    </div>
  );
};

export default MainWindow;
