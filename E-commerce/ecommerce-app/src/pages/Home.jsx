import { useEffect, useState } from "react";
import { getAllProducts, getAllCategories } from "../services/api";
import ProductCard from "../components/ProductCard";
import SearchBar from "../components/SearchBar";
import "./Home.css";

function Home() {
  const [products, setProducts] = useState([]);
  const [filtered, setFiltered] = useState([]);
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState("all");

  useEffect(() => {
    async function loadData() {
      const [prodData, catData] = await Promise.all([
        getAllProducts(),
        getAllCategories(),
      ]);
      setProducts(prodData);
      setFiltered(prodData);
      setCategories(catData);
    }
    loadData();
  }, []);

  const handleSearch = (query) => {
    const searchFiltered = products.filter((p) =>
      p.title.toLowerCase().includes(query.toLowerCase())
    );
    if (selectedCategory !== "all") {
      setFiltered(
        searchFiltered.filter((p) => p.category === selectedCategory)
      );
    } else {
      setFiltered(searchFiltered);
    }
  };

  const handleCategory = (category) => {
    setSelectedCategory(category);
    if (category === "all") {
      setFiltered(products);
    } else {
      setFiltered(products.filter((p) => p.category === category));
    }
  };

  return (
    <div className="home">
      <SearchBar onSearch={handleSearch} />

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
