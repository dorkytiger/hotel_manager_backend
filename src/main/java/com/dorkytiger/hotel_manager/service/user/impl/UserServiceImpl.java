package com.dorkytiger.hotel_manager.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dorkytiger.hotel_manager.mapper.user.UserInfoMapper;
import com.dorkytiger.hotel_manager.model.common.ResponseEntity;
import com.dorkytiger.hotel_manager.model.user.UserCreateEntity;
import com.dorkytiger.hotel_manager.model.user.UserInfoEntity;
import com.dorkytiger.hotel_manager.model.user.UserResetEntity;
import com.dorkytiger.hotel_manager.model.user.repsonse.LoginResponseEntity;
import com.dorkytiger.hotel_manager.model.user.request.LoginRequestEntity;
import com.dorkytiger.hotel_manager.service.user.UserService;
import com.dorkytiger.hotel_manager.util.JwtUtil;
import com.dorkytiger.hotel_manager.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public ResponseEntity<String> create(UserCreateEntity userCreateEntity) {
        String username = Optional.ofNullable(userCreateEntity.getUsername()).orElseThrow(() -> new IllegalArgumentException("用户名不能为空"));
        String password = Optional.ofNullable(userCreateEntity.getPassword()).orElseThrow(() -> new IllegalArgumentException("密码不能为空"));
        Integer type = Optional.ofNullable(userCreateEntity.getType()).orElseThrow(() -> new IllegalArgumentException("类型不能为空"));
        UserInfoEntity userInfo = userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfoEntity>().eq(UserInfoEntity::getUsername, username));
        if (userInfo != null) {
            return ResponseEntity.fail("用户名已存在");
        }
        String encodePassword = MD5Util.getMD5(password);
        UserInfoEntity userInfoEntity = new UserInfoEntity(
                username,
                encodePassword,
                type
        );
        int isInsert = userInfoMapper.insert(userInfoEntity);
        if (isInsert == 0) {
            return ResponseEntity.fail("创建失败");
        }
        return ResponseEntity.success("创建成功");
    }

    @Override
    public ResponseEntity<LoginResponseEntity> Login(LoginRequestEntity loginRequestEntity) {
        String username = Optional.ofNullable(loginRequestEntity.getUsername()).orElseThrow(() -> new IllegalArgumentException("用户名不能为空"));
        String password = Optional.ofNullable(loginRequestEntity.getPassword()).orElseThrow(() -> new IllegalArgumentException("密码不能为空"));
        String encodePassword = MD5Util.getMD5(password);
        UserInfoEntity userInfoEntity = userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfoEntity>().eq(UserInfoEntity::getUsername, username));
        if (userInfoEntity == null) {
            return ResponseEntity.fail("用户不存在");
        }
        if (!userInfoEntity.getPassword().equals(encodePassword)) {
            return ResponseEntity.fail("密码错误");
        }
        String token = JwtUtil.createToken(userInfoEntity);
        return ResponseEntity.success(new LoginResponseEntity(userInfoEntity.getId(), userInfoEntity.getType(), userInfoEntity.getUsername(), token));
    }

    @Override
    public ResponseEntity<List<UserInfoEntity>> list() {
        return ResponseEntity.success(userInfoMapper.selectList(null));
    }

    @Override
    public ResponseEntity<Object> reset(UserResetEntity userResetEntity) {
        UserInfoEntity userInfo = Optional.ofNullable(userInfoMapper.selectById(userResetEntity.getId())).orElseThrow(() -> new IllegalArgumentException("用户不存在"));
        String password = Optional.ofNullable(userResetEntity.getPassword()).orElseThrow(() -> new IllegalArgumentException("密码不能为空"));
        String encodePassword = MD5Util.getMD5(password);
        userInfo.setPassword(encodePassword);
        int isUpdate = userInfoMapper.updateById(userInfo);
        if (isUpdate == 0) {
            return ResponseEntity.fail("重置失败");
        }
        return ResponseEntity.success("重置成功");
    }

    @Override
    public ResponseEntity<Object> delete(String id) {
        userInfoMapper.deleteById(id);
        return ResponseEntity.success("删除成功");
    }


}
