import { useState, useEffect } from "react";
import { getAllOrders, cancelOrder } from "../services/api";
import "./Orders.css";

function Orders() {
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const data = await getAllOrders();
        setOrders(data);
      } catch (error) {
        console.error('Failed to fetch orders:', error);
      } finally {
        setLoading(false);
      }
    };
    fetchOrders();
  }, []);

  const handleCancelOrder = async (orderId) => {
    try {
      await cancelOrder(orderId);
      setOrders(orders.map(order => 
        order.id === orderId ? {...order, status: "Cancelled"} : order
      ));
    } catch (error) {
      console.error('Failed to cancel order:', error);
    }
  };

  if (loading) return <div>Loading orders...</div>;
  if (orders.length === 0) return <div><h2>No orders yet</h2></div>;

  return (
    <div className="orders">
      <h2>My Orders</h2>
      {orders.map((order) => (
        <div key={order.id} className="order-card">
          <div className="order-header">
            <h3>Order #{order.id}</h3>
            <span className={`status ${order.status?.toLowerCase()}`}>
              {order.status || 'Pending'}
            </span>
          </div>
          <div className="order-items">
            {order.items.map((item) => (
              <div key={item.id} className="order-item">
                <span>{item.title} x{item.quantity}</span>
                <span>${(item.price * item.quantity).toFixed(2)}</span>
              </div>
            ))}
          </div>
          <div className="order-address">
            <p>{order.address.fullName}</p>
            <p>{order.address.street}, {order.address.city}</p>
            <p>{order.address.phone}</p>
          </div>
          <div className="order-total">
            <strong>Total: ${order.total.toFixed(2)}</strong>
          </div>
          {order.status.toLowerCase() === "pending" && (
            <button 
              className="cancel-btn"
              onClick={() => handleCancelOrder(order.id)}
            >
              Cancel Order
            </button>
          )}
        </div>
      ))}
    </div>
  );
}

export default Orders;
