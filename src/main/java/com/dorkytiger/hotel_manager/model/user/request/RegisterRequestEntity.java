package com.dorkytiger.hotel_manager.model.user.request;


import lombok.Data;

@Data

public class RegisterRequestEntity {
    private String username;
    private String password;
    private Integer type;
}
