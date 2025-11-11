import { useSelector, useDispatch } from "react-redux";
import { useState } from "react";
import { removeFromCart, increaseQuantity, decreaseQuantity, clearCart, applyPromocode, removePromocode } from "../redux/cartSlice";
import "./Cart.css";
import { useNavigate } from "react-router-dom";
import SimilarProducts from "../components/SimilarProducts";
import { validatePromoCode } from "../services/api";

function Cart() {
  const { cartItems, totalQuantity, totalAmount, promocode, discount } = useSelector((state) => state.cart);
  const dispatch = useDispatch();
  const [promoInput, setPromoInput] = useState("");
  const navigate = useNavigate();

  const cartCategories = [...new Set(cartItems.map(item => item.category))];
  if (cartItems.length === 0) {
    return (
      <div className="cart-empty">
        <h2>Your cart is empty</h2>
      </div>
    );
  }

  const validatePromo = async () => {
    try {
      const promo = await validatePromoCode(promoInput);
      dispatch(applyPromocode({ code: promoInput, discount: promo.discount }));
      setPromoInput("");
    } catch (error) {
      alert("Invalid or expired promo code");
    }
  };

  return (
    <div className="cart-page">
      <div className="cartPage">
        <h2>Shopping Cart ({totalQuantity} items)</h2>
        <div className="cart-items">
          {cartItems.map((item) => (
            <div key={item.id} className="cart-item">
              <img src={item.image} alt={item.title} />
              <div className="item-details">
                <h3>{item.title}</h3>
                <p>${item.price}</p>
              </div>
              <div className="quantity-controls">
                <button onClick={() => dispatch(decreaseQuantity(item.id))}>-</button>
                <span>{item.quantity}</span>
                <button onClick={() => dispatch(increaseQuantity(item.id))}>+</button>
              </div>
              <button onClick={() => dispatch(removeFromCart(item.id))}>Remove</button>
            </div>
          ))}

          <div className="promocode-section">
            <input 
              type="text" 
              placeholder="Enter promocode" 
              value={promoInput}
              onChange={(e) => setPromoInput(e.target.value.toUpperCase())}
            />
            <button onClick={validatePromo}>Apply</button>
            {promocode && (
              <div className="applied-promo">
                <span>{promocode} (-{discount}%)</span>
                <button onClick={() => dispatch(removePromocode())}>Remove</button>
              </div>
            )}
          </div>
        </div>
        
        <div className="cart-summary">
          {discount > 0 && <div>Subtotal: ${totalAmount.toFixed(2)}</div>}
          {discount > 0 && <div>Discount: -${((totalAmount * discount) / 100).toFixed(2)}</div>}
          <h3>Total: ${(totalAmount - (totalAmount * discount) / 100).toFixed(2)}</h3>
          <div className="cart-actions">
            <button onClick={() => dispatch(clearCart())}>Clear Cart</button>
            <button 
              className="checkout-btn" 
              onClick={() => navigate("/checkout")}
            >
              Checkout
            </button>
          </div>
        </div>
      </div>
      
      {cartCategories.slice(0, 2).map(category => (
        <SimilarProducts 
          key={category} 
          currentCategory={category} 
          excludeId={null}
        />
      ))}
    </div>
  );
}

export default Cart;
