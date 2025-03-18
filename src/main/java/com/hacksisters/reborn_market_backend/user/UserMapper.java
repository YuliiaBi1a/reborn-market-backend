package com.hacksisters.reborn_market_backend.user;

import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User toUser(UserDtoRequest request) {
        return User.builder()
                .email(request.email())
                .username(request.username())
                .password(request.password())
                .build();
    }
    public UserDtoResponse toUserDtoResponse(User user) {
        return UserDtoResponse.builder()
                .username(user.getUsername())
                .build();
    }
}
