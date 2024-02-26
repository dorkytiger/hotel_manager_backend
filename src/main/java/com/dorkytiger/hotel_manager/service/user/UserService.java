package com.dorkytiger.hotel_manager.service.user;

import com.dorkytiger.hotel_manager.model.common.ResponseEntity;
import com.dorkytiger.hotel_manager.model.user.UserInfoEntity;
import com.dorkytiger.hotel_manager.model.user.UserResetEntity;
import com.dorkytiger.hotel_manager.model.user.repsonse.LoginResponseEntity;
import com.dorkytiger.hotel_manager.model.user.request.LoginRequestEntity;
import com.dorkytiger.hotel_manager.model.user.UserCreateEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<String> create(UserCreateEntity userCreateEntity);

    ResponseEntity<LoginResponseEntity> Login(LoginRequestEntity loginRequestEntity);

    ResponseEntity<List<UserInfoEntity>> list();

    ResponseEntity<Object> reset(UserResetEntity userResetEntity);

    ResponseEntity<Object> delete(String id);
}
