package com.dorkytiger.hotel_manager.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResetEntity {
    private String id;
    private String password;
}
