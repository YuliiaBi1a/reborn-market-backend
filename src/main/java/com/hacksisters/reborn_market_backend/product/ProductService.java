package com.hacksisters.reborn_market_backend.product;

import com.hacksisters.reborn_market_backend.category.Category;
import com.hacksisters.reborn_market_backend.category.CategoryRepository;
import com.hacksisters.reborn_market_backend.user.User;
import com.hacksisters.reborn_market_backend.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private  final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductDtoResponse> findAllProducts(){
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(ProductDtoResponse::fromEntity).toList();
    }

    public ProductDtoResponse createProduct(ProductDtoRequest request){
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User with id " + request.userId()+ " not found"));

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new RuntimeException("Category with id " + request.categoryId()+ " not found"));

        Product saveProduct = productRepository.save(request.toEntity(user, category));
        return ProductDtoResponse.fromEntity(saveProduct);
    }

}
