package com.hacksisters.reborn_market_backend.user;

public record UserDtoResponse (String email, String username) {

    public static UserDtoResponse fromEntity(User user) {
        return new UserDtoResponse(
                user.getEmail(),
                user.getUsername()
        );
    }
}
