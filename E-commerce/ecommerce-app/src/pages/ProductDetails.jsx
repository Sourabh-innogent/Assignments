import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { getProductById } from "../services/api";
import "./ProductDetails.css";

function ProductDetails() {
  const { id } = useParams();
  const [product, setProduct] = useState(null);

  useEffect(() => {
    getProductById(id).then((data) => setProduct(data));
  }, [id]);

  if (!product) return <h3>Loading...</h3>;

  return (
    <div className="product-details">
      <img src={product.image} alt={product.title} />
      <div className="info">
        <h2>{product.title}</h2>
        <p className="category">{product.category}</p>
        <p>{product.description}</p>
        <h3>${product.price}</h3>
      </div>
    </div>
  );
}

export default ProductDetails;
