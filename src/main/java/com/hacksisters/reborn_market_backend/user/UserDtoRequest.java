package com.hacksisters.reborn_market_backend.user;

public record UserDtoRequest(
    String email,
    String username, 
    String password
    ) { 
}
