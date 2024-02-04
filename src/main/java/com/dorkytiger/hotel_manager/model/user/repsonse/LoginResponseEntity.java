package com.dorkytiger.hotel_manager.model.user.repsonse;

import com.dorkytiger.hotel_manager.model.user.UserInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseEntity {
    String Id;
    Integer type;
    String username;
    String token;
}
