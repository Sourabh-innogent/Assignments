import { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { clearCart } from "../redux/cartSlice";
import { useNavigate } from "react-router-dom";
import "./Checkout.css";
import { createOrder } from "../services/api";


function Checkout() {
  const { cartItems, totalAmount, discount } = useSelector((state) => state.cart);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  
  const [address, setAddress] = useState({
    fullName: "",
    street: "",
    city: "",
    phone: ""
  });

const finalAmount = totalAmount - ((totalAmount * discount) / 100);

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const orderData = {
      total: totalAmount,
      discount: discount,
      address: {
        fullName: address.fullName,
        street: address.street,
        city: address.city,
        phone: address.phone
      },
      items: cartItems.map(item => ({
        id: item.id,
        title: item.title,
        price: item.price,
        quantity: item.quantity
      }))
    };
      
       await createOrder(orderData);
      
      dispatch(clearCart());
      alert("Order placed successfully!");
      navigate("/orders");
    } catch (error) {
      console.error('Order creation failed:', error);
      alert("Failed to place order. Please try again.");
    }
  };


  return (
    <div className="checkout">
      <h2>Checkout</h2>
      <div className="checkout-content">
        <form onSubmit={handleSubmit} className="address-form">
          <h3>Delivery Address</h3>
          <input
            type="text"
            placeholder="Full Name"
            value={address.fullName}
            onChange={(e) => setAddress({...address, fullName: e.target.value})}
            required
          />
          <input
            type="text"
            placeholder="Street Address"
            value={address.street}
            onChange={(e) => setAddress({...address, street: e.target.value})}
            required
          />
          <input
            type="text"
            placeholder="City"
            value={address.city}
            onChange={(e) => setAddress({...address, city: e.target.value})}
            required
          />
          <input
            type="tel"
            placeholder="Phone Number"
            value={address.phone}
            onChange={(e) => setAddress({...address, phone: e.target.value})}
            required
          />
          <button type="submit">Place Order - ${finalAmount.toFixed(2)}</button>
        </form>
        
        <div className="order-summary">
          <h3>Order Summary</h3>
          {cartItems.map((item) => (
            <div key={item.id} className="summary-item">
              <span>{item.title} x{item.quantity}</span>
              <span>${(item.price * item.quantity).toFixed(2)}</span>
            </div>
          ))}
          {discount > 0 && <div>Discount: -${((totalAmount * discount) / 100).toFixed(2)}</div>}
          <div className="total">Total: ${finalAmount.toFixed(2)}</div>
        </div>
      </div>
    </div>
  );
}

export default Checkout;
