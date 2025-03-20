package com.hacksisters.reborn_market_backend.category;

public record CategoryDtoResponse(Long id,
                                  String name) {
    public static CategoryDtoResponse fromEntity(Category category){
        return new CategoryDtoResponse(
                category.getId(),
                category.getName()
        );
    }
}
