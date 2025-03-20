package com.hacksisters.reborn_market_backend.category;

public record CategoryDtoRequest(String name) {
    public Category toEntity() {
        return new Category(this.name);
    }
}
