import { Link } from "react-router-dom";
import "./ProductCard.css";
import { useDispatch } from "react-redux";
import { addToCart } from "../redux/cartSlice";

function ProductCard({ product }) {
  const dispatch = useDispatch();
  return (
    <div className="product-card">
      <Link to={`/product/${product.id}`} className="card-link">
        <img src={product.image} alt={product.title} />
        <h3>{product.title}</h3>
        <div className="rating">
          <span className="stars">{'★'.repeat(Math.floor(product.rating?.rate || 0))}{'☆'.repeat(5 - Math.floor(product.rating?.rate || 0))}</span>
          <span className="rating-text">({product.rating?.count || 0})</span>
        </div>
        <p>${product.price}</p>
      </Link>
      <button className="add-to-cart" onClick={() => dispatch(addToCart(product))}>
        Add to Cart
      </button>
    </div>
  );
}

export default ProductCard;
