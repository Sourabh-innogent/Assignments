package backend.ecommerce;

import backend.ecommerce.model.Product;
import backend.ecommerce.model.Rating;
import backend.ecommerce.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class ProductConfig {
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @Bean
    CommandLineRunner initDatabase(ProductRepository repository, RestTemplate restTemplate) {
        return args -> {
            if(repository.count() == 0) {
                loadProducts(repository, restTemplate);
            }
        };
    }
    
    private void loadProducts(ProductRepository repository, RestTemplate restTemplate) {
        try {
            String response = restTemplate.getForObject("https://fakestoreapi.com/products", String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode products = mapper.readTree(response);
            
            for(JsonNode node : products) {
                Product product = new Product();
                product.setTitle(node.has("title") ? node.get("title").asText() : "");
                product.setPrice(node.has("price") ? node.get("price").asDouble() : 0.0);
                product.setDescription(node.has("description") ? node.get("description").asText() : "");
                product.setCategory(node.has("category") ? node.get("category").asText() : "");
                product.setImage(node.has("image") ? node.get("image").asText() : "");
                
                JsonNode ratingNode = node.get("rating");
                if(ratingNode != null && ratingNode.has("rate") && ratingNode.has("count")) {
                    Rating rating = new Rating();
                    rating.setRate(ratingNode.get("rate").asDouble());
                    rating.setCount(ratingNode.get("count").asInt());
                    product.setRating(rating);
                }
                
                repository.save(product);
            }
            System.out.println("Products loaded successfully!");
        } catch (Exception e) {
            System.err.println("Error loading products: " + e.getMessage());
        }
    }
}