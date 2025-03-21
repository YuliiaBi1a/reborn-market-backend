package com.hacksisters.reborn_market_backend.register;

import com.hacksisters.reborn_market_backend.user.User;
import com.hacksisters.reborn_market_backend.user.UserDtoRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${api-endpoint}")
public class UserRegisterController {

    private final UserRegisterService userRegisterService;

    public UserRegisterController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }
    @GetMapping("/private/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userRegisterService.findAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> registerNewUser(@RequestBody UserDtoRequest request){
        Map<String, String> response = userRegisterService.registerUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUserAccount(@PathVariable Long id) {
        userRegisterService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
