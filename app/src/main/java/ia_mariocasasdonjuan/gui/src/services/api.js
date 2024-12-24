import axios from "axios";

// URL base de tu backend (cambia según tu configuración)
const API_BASE_URL = "http://localhost:8080"; // Este lo tengo que cambiar si lo corro en otra computadora

// Función para obtener datos
export const getData = async (endpoint) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/${endpoint}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching data:", error);
    throw error;
  }
};

// Función para enviar datos
export const postData = async (endpoint, data) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/${endpoint}`, data);
    return response.data;
  } catch (error) {
    console.error("Error posting data:", error);
    throw error;
  }
};
