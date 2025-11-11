import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { getProductById } from "../services/api";
import "./ProductDetails.css";
import { useDispatch } from "react-redux";
import { addToCart } from "../redux/cartSlice";
import SimilarProducts from "../components/SimilarProducts";

function ProductDetails() {
  const { id } = useParams();
  const [quantity, setQuantity] = useState(1);
  const [product, setProduct] = useState(null);
  const dispatch = useDispatch();

  useEffect(() => {
    getProductById(id).then((data) => setProduct(data));
  }, [id]);

  if (!product) return <h3>Loading...</h3>;

  return (
    <div className="product-details-page">
      <div className="product-details">
        <img src={product.image} alt={product.title} />
        <div className="info">
          <h2>{product.title}</h2>
          <p className="category">{product.category}</p>
          <div className="rating">
            <span className="stars">{'★'.repeat(Math.floor(product.rating?.rate || 0))}{'☆'.repeat(5 - Math.floor(product.rating?.rate || 0))}</span>
            <span className="rating-text">({product.rating?.count || 0} reviews)</span>
          </div>
          <p>{product.description}</p>
          <h3>${product.price}</h3>
          <div className="quantity-section">
            <div className="quantity-controls">
              <button onClick={() => setQuantity(Math.max(1, quantity - 1))}>-</button>
              <span>{quantity}</span>
              <button onClick={() => setQuantity(quantity + 1)}>+</button>
            </div>
            <button 
              className="add-to-cart" 
              onClick={() => {
                for(let i = 0; i < quantity; i++) {
                  dispatch(addToCart(product));
                }
              }}
            >
              Add to Cart
            </button>
          </div>
        </div>
      </div>
      
      <SimilarProducts currentCategory={product.category} excludeId={product.id} />
    </div>
  );
}

export default ProductDetails;
