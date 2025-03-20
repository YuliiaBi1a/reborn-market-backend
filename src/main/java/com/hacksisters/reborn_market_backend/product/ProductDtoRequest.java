package com.hacksisters.reborn_market_backend.product;

import com.hacksisters.reborn_market_backend.category.Category;
import com.hacksisters.reborn_market_backend.user.User;

public record ProductDtoRequest(String name,
                                String image,
                                String description,
                                double price,
                                int age,
                                Condition condition,
                                Long userId,
                                Long categoryId
                                ) {

    public Product toEntity(User user, Category category) {
        return new Product(
                this.name,
                this.image,
                this.description,
                this.price,
                this.age,
                this.condition,
                user,
                category
        );
    }
}
