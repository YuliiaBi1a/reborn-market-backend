package com.hacksisters.reborn_market_backend.product;

import com.hacksisters.reborn_market_backend.category.Category;
import com.hacksisters.reborn_market_backend.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String name;
    private String image;
    private String description;
    private double price;
    private int age;
    @Enumerated(EnumType.STRING)
    private Condition condition;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(String name, String image, String description, double price, int age, Condition condition, User user, Category category) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.age = age;
        this.condition = condition;
        this.user = user;
        this.category = category;
    }
}
