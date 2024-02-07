package com.dorkytiger.hotel_manager.service.room.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dorkytiger.hotel_manager.mapper.RoomInfoMapper;
import com.dorkytiger.hotel_manager.model.common.ResponseEntity;
import com.dorkytiger.hotel_manager.model.room.RoomInfoEntity;
import com.dorkytiger.hotel_manager.service.room.RoomInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomInfoServiceImpl implements RoomInfoService {

    private final RoomInfoMapper roomInfoMapper;

    @Autowired
    public RoomInfoServiceImpl(RoomInfoMapper roomInfoMapper) {
        this.roomInfoMapper = roomInfoMapper;
    }

    @Override
    public ResponseEntity<Object> createRoomInfo(RoomInfoEntity roomInfoEntity) {
        roomInfoMapper.insert(roomInfoEntity);
        return new ResponseEntity<>().success();
    }

    @Override
    public ResponseEntity<List<RoomInfoEntity>> roomList() {
        List<RoomInfoEntity> roomInfoEntities = roomInfoMapper.selectList(new LambdaQueryWrapper<>());
        return new ResponseEntity<List<RoomInfoEntity>>().success(roomInfoEntities);
    }
}
