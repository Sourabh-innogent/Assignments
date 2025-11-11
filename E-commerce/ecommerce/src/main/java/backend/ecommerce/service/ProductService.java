package backend.ecommerce.service;

import backend.ecommerce.model.Product;
import backend.ecommerce.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    
    public List<String> getAllCategories() {
        return productRepository.findAll()
                .stream()
                .map(Product::getCategory)
                .filter(cat -> cat != null && !cat.trim().isEmpty())
                .distinct()
                .collect(Collectors.toList());
    }
    
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getCategory() != null && product.getCategory().equals(category))
                .collect(Collectors.toList());
    }
}