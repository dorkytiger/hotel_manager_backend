package com.dorkytiger.hotel_manager.mapper.user;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dorkytiger.hotel_manager.model.user.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfoEntity> {
}
