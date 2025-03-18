package com.hacksisters.reborn_market_backend.product;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${api-endpoint}/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping()
    public ResponseEntity<?> getProducts() {
        List<ProductDtoResponse> products = productService.findAllProducts();
        if (products.isEmpty()) {
            return ResponseEntity.badRequest().body("No hay productos");
        }
        return ResponseEntity.ok(products);
    }
    

}
