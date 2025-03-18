package com.hacksisters.reborn_market_backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User save(UserDtoRequest userDtoRequest) {
        User user  = userMapper.toUser(userDtoRequest);
        return userRepository.save(user);
    }

}
