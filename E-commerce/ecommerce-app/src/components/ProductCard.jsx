import { Link } from "react-router-dom";
import "./ProductCard.css";

function ProductCard({ product }) {

  const handleAddToCart = (e) => {
    e.preventDefault(); 
    alert(` "${product.title}" has been added to your cart!`);
  };
  
  return (
    <div className="product-card">
      <Link to={`/product/${product.id}`} className="card-link">
        <img src={product.image} alt={product.title} />
        <h3>{product.title}</h3>
        <p>${product.price}</p>
        <button onClick={handleAddToCart} className="addtocart" > details </button>
      </Link>
    </div>
  );
}

export default ProductCard;
