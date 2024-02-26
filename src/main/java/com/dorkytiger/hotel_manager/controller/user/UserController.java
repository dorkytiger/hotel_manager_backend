package com.dorkytiger.hotel_manager.controller.user;

import com.dorkytiger.hotel_manager.model.common.ResponseEntity;
import com.dorkytiger.hotel_manager.model.user.UserInfoEntity;
import com.dorkytiger.hotel_manager.model.user.UserResetEntity;
import com.dorkytiger.hotel_manager.model.user.repsonse.LoginResponseEntity;
import com.dorkytiger.hotel_manager.model.user.request.LoginRequestEntity;
import com.dorkytiger.hotel_manager.model.user.UserCreateEntity;
import com.dorkytiger.hotel_manager.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody UserCreateEntity userCreateEntity) {
        return userService.create(userCreateEntity);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserInfoEntity>> list() {
        return userService.list();
    }

    @PostMapping("/reset")
    public ResponseEntity<Object> reset(@RequestBody UserResetEntity userResetEntity) {
        return userService.reset(userResetEntity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        return userService.delete(id);
    }
}
