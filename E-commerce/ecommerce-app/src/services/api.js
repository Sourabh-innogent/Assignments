import axios from "axios";

const BASE_URL = "http://localhost:8080";

export async function getAllProducts() {
  const response = await axios.get(`${BASE_URL}/products`);
  return response.data;
}

export async function getProductById(id) {
  const response = await axios.get(`${BASE_URL}/products/${id}`);
  return response.data;
}

export async function getAllCategories() {
  const response = await axios.get(`${BASE_URL}/products/categories`);
  return response.data;
}

export const createOrder = async (orderData) => {
  const response = await axios.post(`${BASE_URL}/orders`, orderData);
  return response.data;
};

export const getAllOrders = async () => {
  const response = await axios.get(`${BASE_URL}/orders`);
  return response.data;
};

export const cancelOrder = async (orderId) => {
  const response = await axios.patch(`${BASE_URL}/orders/${orderId}/cancel`);
  return response.data;
};

export const validatePromoCode = async (code) => {
  const response = await axios.post(`${BASE_URL}/promocodes/validate/${code}`);
  return response.data;
};