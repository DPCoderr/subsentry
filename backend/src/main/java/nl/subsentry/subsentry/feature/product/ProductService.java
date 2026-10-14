package nl.subsentry.subsentry.feature.product;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ProductService {

    final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    // Get all products
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // Get a product by ID
    public Optional<Product> getProductById(@PathVariable UUID id) {
        return repository.findById(id);
    }

    // Create a new product
    public Product createProduct(@RequestBody Product product) {
        return repository.save(product);
    }

    // Update an existing product
    public Product updateProduct(@PathVariable UUID id, @RequestBody Product product) {
        var existingProduct = repository.getReferenceById(id);

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());

        return repository.save(existingProduct);
    }

    // Delete a product by ID
    public boolean deleteProduct(@PathVariable UUID id) {
        var product = repository.findById(id);

        if (product.isEmpty()) {
            return false;
        }

        repository.delete(product.get());
        return true;
    }
}
