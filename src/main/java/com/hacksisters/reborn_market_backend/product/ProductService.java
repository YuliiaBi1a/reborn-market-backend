package com.hacksisters.reborn_market_backend.product;

import com.hacksisters.reborn_market_backend.category.Category;
import com.hacksisters.reborn_market_backend.category.CategoryRepository;
import com.hacksisters.reborn_market_backend.user.User;
import com.hacksisters.reborn_market_backend.user.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, UserRepository userRepository,
            CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductDtoResponse> findAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(ProductDtoResponse::fromEntity).toList();
    }

    public ProductDtoResponse createProduct(ProductDtoRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User with id " + request.userId() + " not found"));

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new RuntimeException("Category with id " + request.categoryId() + " not found"));

        Product saveProduct = productRepository.save(request.toEntity(user, category));
        return ProductDtoResponse.fromEntity(saveProduct);
    }

    public ProductDtoResponse findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));
        return ProductDtoResponse.fromEntity(product);
    }

    @Transactional
    public ProductDtoResponse updateProduct(Long id, ProductDtoRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));

        if (request.name() != null) {
            product.setName(request.name());
        }
        if (request.image() != null) {
            product.setImage(request.image());
        }
        if (request.description() != null) {
            product.setDescription(request.description());
        }
        if (request.price() != 0) {
            product.setPrice(request.price());
        }
        if (request.age() != 0) {
            product.setAge(request.age());
        }
        if (request.condition() != null) {
            product.setCondition(request.condition());
        }

        if (request.userId() != null) {
            User user = userRepository.findById(request.userId())
                    .orElseThrow(() -> new RuntimeException("User with id " + request.userId() + " not found"));
            product.setUser(user);
        }

        if (request.categoryId() != null) {
            Category category = categoryRepository.findById(request.categoryId())
                    .orElseThrow(() -> new RuntimeException("Category with id " + request.categoryId() + " not found"));
            product.setCategory(category);
        }

        return ProductDtoResponse.fromEntity(productRepository.save(product));
    }

    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product with id " + id + " not found");
        }
        productRepository.deleteById(id);
    }

}