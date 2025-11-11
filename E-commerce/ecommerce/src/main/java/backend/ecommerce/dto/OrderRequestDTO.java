package backend.ecommerce.dto;

import backend.ecommerce.model.Address;
import java.util.List;

public class OrderRequestDTO {
    
    private List<OrderItemDTO> items;
    private Address address;
    private Double total;
    private Double discount;
    
    // Getters and Setters
    public List<OrderItemDTO> getItems() { return items; }
    public void setItems(List<OrderItemDTO> items) { this.items = items; }
    
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
    
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    
    public Double getDiscount() { return discount; }
    public void setDiscount(Double discount) { this.discount = discount; }
}