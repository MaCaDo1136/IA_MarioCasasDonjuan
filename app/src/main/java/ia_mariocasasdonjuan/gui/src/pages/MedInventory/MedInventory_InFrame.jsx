import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../../styles/MedInventory_InFrame.css";
import axios from "axios";

const MedInventory_InFrame = () => {
  const [barcode, setBarcode] = useState("");
  const [newQuantity, setNewQuantity] = useState("");
  const [lotes, setLotes] = useState([]);
  const [selectedLote, setSelectedLote] = useState("");
  const [actualQuantity, setActualQuantity] = useState(null);
  const navigate = useNavigate();

  const handleSearch = async () => {
    try {
      const response = await axios.get(`/api/lotes?barcode=${barcode}`);
      if (response.data.length === 0) {
        alert("No se encontró ningún lote con ese código de barras.");
        setLotes([]);
      } else {
        setLotes(response.data);
      }
    } catch (error) {
      console.error("Error fetching lotes:", error);
      alert("Ocurrió un error al buscar los lotes.");
    }
  };

  const handleUpdate = async () => {
    if (!selectedLote || !newQuantity) {
      alert("Por favor selecciona un lote y especifica una nueva cantidad.");
      return;
    }
    try {
      await axios.put("/api/update-quantity", {
        barcode,
        lote: selectedLote,
        newQuantity,
      });
      alert("Cantidad actualizada correctamente.");
      resetFields();
    } catch (error) {
      console.error("Error updating quantity:", error);
      alert("No se pudo actualizar la cantidad.");
    }
  };

  const resetFields = () => {
    setBarcode("");
    setNewQuantity("");
    setLotes([]);
    setSelectedLote("");
    setActualQuantity(null);
  };

  return (
    <div className="med-inventory-in-container">
      <h1>Actualizar Inventario</h1>
      <div className="form-group">
        <label htmlFor="barcode">Código de Barras:</label>
        <input
          id="barcode"
          type="text"
          value={barcode}
          onChange={(e) => setBarcode(e.target.value)}
        />
        <button className="search-button" onClick={handleSearch}>
          Buscar
        </button>
      </div>

      {lotes.length > 0 && (
        <>
          <div className="form-group">
            <label htmlFor="lote">Seleccionar Lote:</label>
            <select
              id="lote"
              value={selectedLote}
              onChange={(e) => {
                setSelectedLote(e.target.value);
                const selected = lotes.find((lote) => lote === e.target.value);
                if (selected) {
                  // Simula obtener la cantidad actual para el lote seleccionado
                  axios
                    .get(`/api/quantity?barcode=${barcode}&lote=${selected}`)
                    .then((response) =>
                      setActualQuantity(response.data.quantity)
                    )
                    .catch((error) => console.error(error));
                }
              }}
            >
              <option value="">Seleccione un lote</option>
              {lotes.map((lote, index) => (
                <option key={index} value={lote}>
                  {lote}
                </option>
              ))}
            </select>
          </div>

          {actualQuantity !== null && <p>Cantidad actual: {actualQuantity}</p>}
        </>
      )}

      <div className="form-group">
        <label htmlFor="newQuantity">Nueva Cantidad:</label>
        <input
          id="newQuantity"
          type="text"
          value={newQuantity}
          onChange={(e) => setNewQuantity(e.target.value)}
        />
      </div>

      <div className="button-group">
        <button className="update-button" onClick={handleUpdate}>
          Actualizar
        </button>
        <button className="cancel-button" onClick={resetFields}>
          Cancelar
        </button>
        <button className="back-button" onClick={() => navigate("/")}>
          Regresar
        </button>
      </div>
    </div>
  );
};

export default MedInventory_InFrame;
