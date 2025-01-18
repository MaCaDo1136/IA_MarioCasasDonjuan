import React, { useState } from "react";
import "../../styles/MedRegister_InFrame.css";
import axios from "axios"; // Si utilizas axios para la comunicación con el backend

const MedRegister = () => {
  // Estado para los campos del formulario
  const [barcode, setBarcode] = useState("");
  const [name, setName] = useState("");
  const [lote, setLote] = useState("");
  const [expDate, setExpDate] = useState("");
  const [quantity, setQuantity] = useState("");
  const [location, setLocation] = useState("");
  const [description, setDescription] = useState("");

  // Función para manejar el registro
  const handleRegister = async () => {
    if (!barcode || !name || !lote || !expDate || !quantity || !location) {
      alert("Please fill all the fields");
      return;
    }

    try {
      // Verifica si el medicamento ya existe
      const response = await axios.get(`/api/medicine/${barcode}`);

      if (response.data.exists) {
        // Si ya existe, actualizar el medicamento
        await axios.put("/api/medicine", {
          barcode,
          name,
          lote,
          expDate,
          quantity,
          location,
          description,
        });
        alert("Inventory updated successfully");
      } else {
        // Si no existe, registrar el nuevo medicamento
        await axios.post("/api/medicine", {
          barcode,
          name,
          lote,
          expDate,
          quantity,
          location,
          description,
        });
        alert("Medicine registered successfully");
      }
    } catch (error) {
      console.error(error);
      alert("Error connecting to the database");
    }
  };

  return (
    <div className="med-register-container">
      <h1>Register Medicine</h1>

      <label>Barcode:</label>
      <input
        type="text"
        value={barcode}
        onChange={(e) => setBarcode(e.target.value)}
      />

      <label>Name:</label>
      <input
        type="text"
        value={name}
        onChange={(e) => setName(e.target.value)}
      />

      <label>Lote:</label>
      <input
        type="text"
        value={lote}
        onChange={(e) => setLote(e.target.value)}
      />

      <label>Expiration Date:</label>
      <input
        type="text"
        value={expDate}
        onChange={(e) => setExpDate(e.target.value)}
      />

      <label>Quantity:</label>
      <input
        type="text"
        value={quantity}
        onChange={(e) => setQuantity(e.target.value)}
      />

      <label>Location:</label>
      <input
        type="text"
        value={location}
        onChange={(e) => setLocation(e.target.value)}
      />

      <label>Description:</label>
      <input
        type="text"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
      />

      <div className="buttons">
        <button onClick={handleRegister}>Register</button>
        <button onClick={() => window.history.back()}>Cancel</button>
      </div>
    </div>
  );
};

export default MedRegister;
