package com.dorkytiger.hotel_manager.model.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestEntity {
    private String username;
    private String password;
}
