package backend.ecommerce.repo;

import backend.ecommerce.model.Order;
import backend.ecommerce.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    List<Order> findByShippingAddress_FullName(String fullName);
    List<Order> findByStatus(OrderStatus status);
    List<Order> findAllByOrderByOrderDateDesc();
    List<Order> findByShippingAddress_FullNameOrderByOrderDateDesc(String fullName);
}