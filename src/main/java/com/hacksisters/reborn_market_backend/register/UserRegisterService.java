package com.hacksisters.reborn_market_backend.register;

import com.hacksisters.reborn_market_backend.user.User;
import com.hacksisters.reborn_market_backend.user.UserDtoRequest;
import com.hacksisters.reborn_market_backend.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserRegisterService {

    private final UserRepository userRepository;

    public UserRegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public Map<String, String> registerUser(UserDtoRequest request) {

        Optional<User> optionalUser = userRepository.findByUsername(request.username());
        if(optionalUser.isPresent()){
            throw new RuntimeException("User with the email " + request.username() + " already exist");
        }

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedBytes = decoder.decode(request.password());
        String passwordDecoded = new String(decodedBytes);

        System.out.println("<------------ " + passwordDecoded);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordEncoded = encoder.encode(passwordDecoded);

        User newUser = request.toEntity();
        newUser.setPassword(passwordEncoded);

        userRepository.save(newUser);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Success");

        return response;
    }
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw  new RuntimeException("Category with id " + id + " not found.");
        }
        userRepository.deleteById(id);
    }
}
