package backend.ecommerce.dto;

import backend.ecommerce.model.Address;
import backend.ecommerce.model.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDTO {
    
    private Long id;
    private List<OrderItemDTO> items;
    private Address address;
    private Double total;
    private OrderStatus status;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryTime;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public List<OrderItemDTO> getItems() { return items; }
    public void setItems(List<OrderItemDTO> items) { this.items = items; }
    
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
    
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    
    public LocalDateTime getDeliveryTime() { return deliveryTime; }
    public void setDeliveryTime(LocalDateTime deliveryTime) { this.deliveryTime = deliveryTime; }
}