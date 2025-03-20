package com.hacksisters.reborn_market_backend.user;

public record UserDtoRequest(
        String email,
        String username,
    String password
    ) {
    public User toEntity() {
        return new User (
                this.email,
                this.username,
                this.password
        );
    }
}
