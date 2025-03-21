package com.hacksisters.reborn_market_backend.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    void deleteByUserId(Long id);
}
