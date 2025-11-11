import { useEffect, useState } from "react";
import { getAllProducts } from "../services/api";
import ProductCard from "./ProductCard";
import "./SimilarProducts.css";

function SimilarProducts({ currentCategory, excludeId }) {
  const [similarProducts, setSimilarProducts] = useState([]);

  useEffect(() => {
    async function loadSimilarProducts() {
      try {
        const allProducts = await getAllProducts();
        const filtered = allProducts
          .filter(p => p.category === currentCategory && p.id !== excludeId)
          .slice(0, 4);
        setSimilarProducts(filtered);
      } catch (error) {
        console.error("Failed to load similar products");
      }
    }
    
    if (currentCategory) {
      loadSimilarProducts();
    }
  }, [currentCategory, excludeId]);

  if (similarProducts.length === 0) return null;

  return (
    <div className="similar-products">
      <h3>Similar Products - {currentCategory.charAt(0).toUpperCase() + currentCategory.slice(1)}</h3>
      <div className="similar-grid">
        {similarProducts.map(product => (
          <ProductCard key={product.id} product={product} />
        ))}
      </div>
    </div>
  );
}

export default SimilarProducts;