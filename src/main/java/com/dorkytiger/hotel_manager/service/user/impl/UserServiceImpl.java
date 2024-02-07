package com.dorkytiger.hotel_manager.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dorkytiger.hotel_manager.mapper.UserInfoMapper;
import com.dorkytiger.hotel_manager.model.common.ResponseEntity;
import com.dorkytiger.hotel_manager.model.user.UserInfoEntity;
import com.dorkytiger.hotel_manager.model.user.repsonse.LoginResponseEntity;
import com.dorkytiger.hotel_manager.model.user.request.LoginRequestEntity;
import com.dorkytiger.hotel_manager.model.user.request.RegisterRequestEntity;
import com.dorkytiger.hotel_manager.service.user.UserService;
import com.dorkytiger.hotel_manager.util.JwtUtil;
import com.dorkytiger.hotel_manager.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserInfoMapper userInfoMapper;

    @Autowired
    public UserServiceImpl(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public ResponseEntity<String> register(RegisterRequestEntity registerRequestEntity) {
        String username = Optional.ofNullable(registerRequestEntity.getUsername()).orElseThrow(() -> new IllegalArgumentException("用户名不能为空"));
        String password = Optional.ofNullable(registerRequestEntity.getPassword()).orElseThrow(() -> new IllegalArgumentException("密码不能为空"));
        Integer type = Optional.ofNullable(registerRequestEntity.getType()).orElseThrow(() -> new IllegalArgumentException("类型不能为空"));
        UserInfoEntity userInfo = userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfoEntity>().eq(UserInfoEntity::getUsername, username));
        if (userInfo != null) {
            return new ResponseEntity<String>().fail("用户名已存在");
        }
        String encodePassword = MD5Util.getMD5(password);
        UserInfoEntity userInfoEntity = new UserInfoEntity(
                username,
                encodePassword,
                type
        );
        int isInsert = userInfoMapper.insert(userInfoEntity);
        if (isInsert == 0) {
            return new ResponseEntity<String>().fail("注册失败");
        }
        String token = JwtUtil.createToken(userInfoEntity);
        return new ResponseEntity<String>().success(token);
    }

    @Override
    public ResponseEntity<LoginResponseEntity> Login(LoginRequestEntity loginRequestEntity) {
        String username = Optional.ofNullable(loginRequestEntity.getUsername()).orElseThrow(() -> new IllegalArgumentException("用户名不能为空"));
        String password = Optional.ofNullable(loginRequestEntity.getPassword()).orElseThrow(() -> new IllegalArgumentException("密码不能为空"));
        String encodePassword = MD5Util.getMD5(password);
        UserInfoEntity userInfoEntity = userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfoEntity>().eq(UserInfoEntity::getUsername, username));
        if (userInfoEntity == null) {
            return new ResponseEntity<LoginResponseEntity>().fail("用户不存在");
        }
        if (!userInfoEntity.getPassword().equals(encodePassword)) {
            return new ResponseEntity<LoginResponseEntity>().fail("密码错误");
        }
        String token = JwtUtil.createToken(userInfoEntity);
        return new ResponseEntity<LoginResponseEntity>().success(new LoginResponseEntity(userInfoEntity.getId(), userInfoEntity.getType(), userInfoEntity.getUsername(), token));
    }
}
