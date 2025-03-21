package com.hacksisters.reborn_market_backend.product;

import com.hacksisters.reborn_market_backend.category.Category;
import com.hacksisters.reborn_market_backend.user.User;

public record ProductDtoResponse(Long id,
                                 String name,
                                 String image,
                                 String description,
                                 double price,
                                 int age,
                                 Condition condition,
                                 User user,
                                 Category category) {

    public static ProductDtoResponse fromEntity(Product product){
        return new ProductDtoResponse(
                product.getId(),
                product.getName(),
                product.getImage(),
                product.getDescription(),
                product.getPrice(),
                product.getAge(),
                product.getCondition(),
                product.getUser(),
                product.getCategory()
        );
    }
}
