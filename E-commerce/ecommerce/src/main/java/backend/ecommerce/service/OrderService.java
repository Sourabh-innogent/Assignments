package backend.ecommerce.service;

import backend.ecommerce.dto.*;
import backend.ecommerce.model.*;
import backend.ecommerce.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequest) {
        Order order = convertToEntity(orderRequest);
        order.setOrderDate(LocalDateTime.now());
        order.setDeliveryTime(LocalDateTime.now().plusHours(6));
        order.setStatus(OrderStatus.PENDING);
        
        Order savedOrder = orderRepository.save(order);
        return convertToResponseDTO(savedOrder);
    }

    public List<OrderResponseDTO> getAllOrders() {
        try {
            updateExpiredOrders();
            List<Order> orders = orderRepository.findAllByOrderByOrderDateDesc();
            return orders.stream().map(this::convertToResponseDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all orders", e);
        }
    }

    public List<OrderResponseDTO> getOrdersByCustomerName(String fullName) {
        updateExpiredOrders();
        List<Order> orders = orderRepository.findByShippingAddress_FullNameOrderByOrderDateDesc(fullName);
        return orders.stream().map(this::convertToResponseDTO).collect(Collectors.toList());
    }
    
    public OrderResponseDTO updateOrderStatus(Long orderId, OrderStatus status) {
        if (orderId == null) throw new IllegalArgumentException("Order ID cannot be null");
        if (status == null) throw new IllegalArgumentException("Status cannot be null");
        
        Order order = orderRepository.findById(orderId).orElse(null);
        if(order == null) throw new RuntimeException("Order not found with ID: " + orderId);
        
        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        return convertToResponseDTO(updatedOrder);
    }
    
    public OrderResponseDTO cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if(order == null) throw new RuntimeException("Order not found with ID: " + orderId);
        
        order.setStatus(OrderStatus.CANCELLED);
        Order updatedOrder = orderRepository.save(order);
        return convertToResponseDTO(updatedOrder);
    }
    
    private void updateExpiredOrders() {
        List<Order> pendingOrders = orderRepository.findByStatus(OrderStatus.PENDING);
        LocalDateTime now = LocalDateTime.now();
        
        for(Order order : pendingOrders) {
            if(order.getDeliveryTime().isBefore(now)) {
                order.setStatus(OrderStatus.DELIVERED);
                orderRepository.save(order);
            }
        }
    }
    
    private Order convertToEntity(OrderRequestDTO dto) {
        Order order = new Order();
        Double total = dto.getTotal() != null ? dto.getTotal() : 0.0;
        Double discount = dto.getDiscount() != null ? dto.getDiscount() : 0.0;
        
        order.setTotalAmount(total);
        Double discountAmount = total * discount / 100;
        order.setDiscount(discountAmount);
        order.setFinalAmount(total - discountAmount);
        order.setShippingAddress(dto.getAddress());
        
        List<OrderItem> orderItems = dto.getItems().stream().map(itemDTO -> {
            OrderItem item = new OrderItem();
            item.setProductId(itemDTO.getId());
            item.setProductTitle(itemDTO.getTitle());
            item.setProductPrice(itemDTO.getPrice());
            item.setQuantity(itemDTO.getQuantity());
            item.setSubtotal(itemDTO.getPrice() * itemDTO.getQuantity());
            item.setOrder(order);
            return item;
        }).collect(Collectors.toList());
        
        order.setOrderItems(orderItems);
        return order;
    }
    
    private OrderResponseDTO convertToResponseDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setAddress(order.getShippingAddress());
        dto.setTotal(order.getFinalAmount());
        dto.setStatus(order.getStatus());
        dto.setOrderDate(order.getOrderDate());
        dto.setDeliveryTime(order.getDeliveryTime());
        
        List<OrderItemDTO> itemDTOs = order.getOrderItems().stream().map(item -> {
            OrderItemDTO itemDTO = new OrderItemDTO();
            itemDTO.setId(item.getProductId());
            itemDTO.setTitle(item.getProductTitle());
            itemDTO.setPrice(item.getProductPrice());
            itemDTO.setQuantity(item.getQuantity());
            return itemDTO;
        }).collect(Collectors.toList());
        
        dto.setItems(itemDTOs);
        return dto;
    }
}