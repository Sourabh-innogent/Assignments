package backend.ecommerce.controller;

import backend.ecommerce.model.Product;
import backend.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        if (id == null) throw new IllegalArgumentException("Product ID cannot be null");
        Product product = productService.getProductById(id);
        if (product == null) throw new RuntimeException("Product not found with ID: " + id);
        return product;
    }
    
    @GetMapping("/categories")
    public List<String> getAllCategories() {
        return productService.getAllCategories();
    }
    
    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        if (category == null || category.trim().isEmpty()) throw new IllegalArgumentException("Category cannot be null or empty");
        return productService.getProductsByCategory(category);
    }
}