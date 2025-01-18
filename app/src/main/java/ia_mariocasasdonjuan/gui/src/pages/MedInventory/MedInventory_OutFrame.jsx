import React, { useState, useEffect } from "react";
import "../../styles/MedInventory_OutFrame.css";
import axios from "axios";

const MedInventoryOutFrame = () => {
  const [inventoryData, setInventoryData] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [pageSize] = useState(70);

  useEffect(() => {
    // Carga inicial de datos
    fetchInventoryData();
  }, []);

  const fetchInventoryData = async () => {
    try {
      const response = await axios.get("/api/inventory"); // Ruta API simulada
      setInventoryData(response.data);
    } catch (error) {
      console.error("Error fetching inventory data:", error);
      alert("Error al cargar los datos del inventario.");
    }
  };

  const getTotalPages = () => {
    return Math.ceil(inventoryData.length / pageSize);
  };

  const handlePreviousPage = () => {
    if (currentPage > 1) {
      setCurrentPage(currentPage - 1);
    }
  };

  const handleNextPage = () => {
    if (currentPage < getTotalPages()) {
      setCurrentPage(currentPage + 1);
    }
  };

  const handleBack = () => {
    // Lógica para regresar a la pantalla principal
    console.log("Regresar a la pantalla principal");
  };

  const paginatedData = () => {
    const start = (currentPage - 1) * pageSize;
    return inventoryData.slice(start, start + pageSize);
  };

  return (
    <div className="med-inventory-out-container">
      <h1>Inventario de Medicamentos</h1>
      <table className="inventory-table">
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Lote</th>
            <th>Fecha de Expiración</th>
            <th>Cantidad</th>
          </tr>
        </thead>
        <tbody>
          {paginatedData().map((item, index) => (
            <tr key={index}>
              <td>{item.name}</td>
              <td>{item.lote}</td>
              <td>{item.expDate}</td>
              <td>{item.quantity}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <div className="pagination">
        <button
          className="pagination-button"
          onClick={handlePreviousPage}
          disabled={currentPage === 1}
        >
          Anterior
        </button>
        <span>
          Página {currentPage} de {getTotalPages()}
        </span>
        <button
          className="pagination-button"
          onClick={handleNextPage}
          disabled={currentPage === getTotalPages()}
        >
          Siguiente
        </button>
      </div>
      <button className="back-button" onClick={handleBack}>
        Regresar
      </button>
    </div>
  );
};

export default MedInventoryOutFrame;
