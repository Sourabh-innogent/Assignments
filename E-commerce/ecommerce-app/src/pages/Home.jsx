import { useEffect, useState } from "react";
import { getAllProducts, getAllCategories } from "../services/api";
import ProductCard from "../components/ProductCard";

import "./Home.css";

function Home({ searchQuery }) {
  const [products, setProducts] = useState([]);
  const [filtered, setFiltered] = useState([]);
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState("all");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

useEffect(() => {
  async function loadData() {
    try {
      setLoading(true);
      const [prodData, catData] = await Promise.all([
        getAllProducts(),
        getAllCategories(),
      ]);
      setProducts(prodData);
      setFiltered(prodData);
      setCategories(catData);
      setError(null);
    } catch (error) {
      console.error('Failed to load data:', error);
      setError('Server is currently unavailable. Please try again later.');
      setProducts([]);
      setFiltered([]);
      setCategories([]);
    } finally {
      setLoading(false);
    }
  }
  loadData();
}, []);

  useEffect(() => {
    let result = products;
    if (searchQuery) {
      result = result.filter((p) =>
        p.title.toLowerCase().includes(searchQuery.toLowerCase())
      );
    }
    if (selectedCategory !== "all") {
      result = result.filter((p) => p.category === selectedCategory);
    }
    setFiltered(result);
  }, [products, searchQuery, selectedCategory]);

  const handleCategory = (category) => {
    setSelectedCategory(category);
  };

  if (loading) {
    return <div className="home" style={{textAlign:"center"}}><h2>Loading products...</h2></div>;
  }

  if (error) {
    return <div className="home" style={{textAlign:"center", color:"red"}} ><h2>{error}</h2></div>;
  }

  return (
    <div className="home">
      <div className="category-filter">
        <button
          className={selectedCategory === "all" ? "active" : ""}
          onClick={() => handleCategory("all")}
        >
          All
        </button>
        {categories.map((cat) => (
          <button
            key={cat}
            className={selectedCategory === cat ? "active" : ""}
            onClick={() => handleCategory(cat)}
          >
            {cat.charAt(0).toUpperCase() + cat.slice(1)}
          </button>
        ))}
      </div>

      <div className="product-grid">
        {filtered.map((p) => (
          <ProductCard key={p.id} product={p} />
        ))}
      </div>
    </div>
  );
}

export default Home;
