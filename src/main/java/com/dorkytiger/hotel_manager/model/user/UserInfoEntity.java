package com.dorkytiger.hotel_manager.model.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dorkytiger.hotel_manager.model.common.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_info")
public class UserInfoEntity extends BaseEntity {
    private String username;
    private String password;
    private Integer type;
}
