package backend.ecommerce.controller;

import backend.ecommerce.dto.OrderRequestDTO;
import backend.ecommerce.dto.OrderResponseDTO;
import backend.ecommerce.model.OrderStatus;
import backend.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @PostMapping
    public OrderResponseDTO createOrder(@RequestBody OrderRequestDTO orderRequest) {
        try {
            if (orderRequest == null) throw new IllegalArgumentException("Order request cannot be null");
            return orderService.createOrder(orderRequest);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    @GetMapping("/customer/{fullName}")
    public List<OrderResponseDTO> getOrdersByCustomer(@PathVariable String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) throw new IllegalArgumentException("Customer name cannot be null or empty");
        return orderService.getOrdersByCustomerName(fullName);
    }

    @GetMapping
    public List<OrderResponseDTO> getOrders() {
        return orderService.getAllOrders();
    }
    @PutMapping("/{orderId}/status")
    public OrderResponseDTO updateOrderStatus(@PathVariable Long orderId, @RequestBody OrderStatus status) {
        if (orderId == null) throw new IllegalArgumentException("Order ID cannot be null");
        if (status == null) throw new IllegalArgumentException("Status cannot be null");
        return orderService.updateOrderStatus(orderId, status);
    }
    
    @PatchMapping("/{orderId}/cancel")
    public OrderResponseDTO cancelOrder(@PathVariable Long orderId) {
        if (orderId == null) throw new IllegalArgumentException("Order ID cannot be null");
        return orderService.cancelOrder(orderId);
    }
}