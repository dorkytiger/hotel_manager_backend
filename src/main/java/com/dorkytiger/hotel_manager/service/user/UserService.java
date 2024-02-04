package com.dorkytiger.hotel_manager.service.user;

import com.dorkytiger.hotel_manager.model.common.ResponseEntity;
import com.dorkytiger.hotel_manager.model.user.repsonse.LoginResponseEntity;
import com.dorkytiger.hotel_manager.model.user.request.LoginRequestEntity;
import com.dorkytiger.hotel_manager.model.user.request.RegisterRequestEntity;

public interface UserService {
    ResponseEntity<String> register(RegisterRequestEntity registerRequestEntity);

    ResponseEntity<LoginResponseEntity> Login(LoginRequestEntity loginRequestEntity);
}
