import React, { useState } from "react";
import axios from "axios";

const MedRegister_InFrame = () => {
  // Estado para los campos del formulario
  const [data, setData] = useState({
    barcode: "",
    name: "",
    lote: "",
    expDate: "",
    quantity: "",
    location: "",
    description: "",
  });

  // Función para manejar los cambios en los inputs
  const handleChange = (e) => {
    setData({
      ...data,
      [e.target.name]: e.target.value,
    });
  };

  // Función para manejar el registro y actualización
  const handleRegister = async () => {
    const { barcode, name, lote, expDate, quantity, location, description } =
      data;

    // Verifica si todos los campos están completos
    if (!barcode || !lote || !expDate || !quantity || !location || !name) {
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
      console.error("Error en la solicitud:", error);
      alert("Error connecting to the database");
    }
  };

  return (
    <div className="med-register-container">
      <h1>Register Medicine</h1>

      <label>Barcode:</label>
      <input
        type="text"
        name="barcode"
        value={data.barcode}
        onChange={handleChange}
      />

      <label>Name:</label>
      <input
        type="text"
        name="name"
        value={data.name}
        onChange={handleChange}
      />

      <label>Lote:</label>
      <input
        type="text"
        name="lote"
        value={data.lote}
        onChange={handleChange}
      />

      <label>Expiration Date:</label>
      <input
        type="text"
        name="expDate"
        value={data.expDate}
        onChange={handleChange}
      />

      <label>Quantity:</label>
      <input
        type="text"
        name="quantity"
        value={data.quantity}
        onChange={handleChange}
      />

      <label>Location:</label>
      <input
        type="text"
        name="location"
        value={data.location}
        onChange={handleChange}
      />

      <label>Description:</label>
      <input
        type="text"
        name="description"
        value={data.description}
        onChange={handleChange}
      />

      <div className="buttons">
        <button onClick={handleRegister}>Register</button>
        <button onClick={() => window.history.back()}>Cancel</button>
      </div>
    </div>
  );
};

export default MedRegister_InFrame;
