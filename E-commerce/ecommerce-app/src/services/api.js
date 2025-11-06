import axios from "axios";

const BASE_URL = "https://fakestoreapi.com";

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
