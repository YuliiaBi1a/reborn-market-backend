package com.hacksisters.reborn_market_backend.product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api-endpoint}/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> getProducts(
            @RequestParam Optional<String> name,
            @RequestParam Optional<Long> categoryId,
            @RequestParam Optional<String> condition,
            @RequestParam Optional<String> ageGroup,
            @RequestParam Optional<String> priceGroup) {

        List<ProductDtoResponse> products;
        if (name.isEmpty() && categoryId.isEmpty() && condition.isEmpty() && ageGroup.isEmpty()
                && priceGroup.isEmpty()) {
            products = productService.findAllProducts();
        } else {
            products = productService.findProductsByFilters(name, categoryId, condition, ageGroup, priceGroup);
        }

        if (products.isEmpty()) {
            Map<String, String> response = Map.of("message", "No products found");
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDtoRequest request) {
        ProductDtoResponse product = productService.createProduct(request);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDtoResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDtoResponse> updateProduct(@PathVariable Long id,
            @RequestBody ProductDtoRequest request) {
        return ResponseEntity.ok(productService.updateProduct(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        Map<String, String> response = Map.of("message", "Product deleted successfully");
        return ResponseEntity.ok(response);
    }
}
