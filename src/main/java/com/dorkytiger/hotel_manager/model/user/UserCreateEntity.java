package com.dorkytiger.hotel_manager.model.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateEntity {
    private String username;
    private String password;
    private Integer type;
}
