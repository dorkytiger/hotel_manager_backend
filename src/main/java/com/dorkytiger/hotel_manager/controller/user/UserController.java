package com.dorkytiger.hotel_manager.controller.user;

import com.dorkytiger.hotel_manager.model.common.ResponseEntity;
import com.dorkytiger.hotel_manager.model.user.repsonse.LoginResponseEntity;
import com.dorkytiger.hotel_manager.model.user.request.LoginRequestEntity;
import com.dorkytiger.hotel_manager.model.user.request.RegisterRequestEntity;
import com.dorkytiger.hotel_manager.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PutMapping("/login")
    public ResponseEntity<LoginResponseEntity> login(@RequestBody LoginRequestEntity loginRequestEntity) {
        return userService.Login(loginRequestEntity);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestEntity registerRequestEntity) {
        return userService.register(registerRequestEntity);
    }
}
