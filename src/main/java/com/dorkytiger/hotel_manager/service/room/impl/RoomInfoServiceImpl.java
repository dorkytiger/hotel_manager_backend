package com.dorkytiger.hotel_manager.service.room.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dorkytiger.hotel_manager.emun.RoomStatus;
import com.dorkytiger.hotel_manager.mapper.room.RoomInfoMapper;
import com.dorkytiger.hotel_manager.model.common.ResponseEntity;
import com.dorkytiger.hotel_manager.model.room.RoomInfoEntity;
import com.dorkytiger.hotel_manager.service.room.RoomInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomInfoServiceImpl implements RoomInfoService {

    private final RoomInfoMapper roomInfoMapper;

    @Autowired
    public RoomInfoServiceImpl(RoomInfoMapper roomInfoMapper) {
        this.roomInfoMapper = roomInfoMapper;
    }

    @Override
    public ResponseEntity<Object> createRoomInfo(RoomInfoEntity roomInfoEntity) {
        roomInfoEntity.setStatus(RoomStatus.SPACE.getStatus());
        roomInfoMapper.insert(roomInfoEntity);
        return  ResponseEntity.success();
    }

    @Override
    public ResponseEntity<List<RoomInfoEntity>> roomList() {
        List<RoomInfoEntity> roomInfoEntities = roomInfoMapper.selectList(new LambdaQueryWrapper<>());
        return  ResponseEntity.success(roomInfoEntities);
    }

    @Override
    public ResponseEntity<Object> updateRoomInfo(RoomInfoEntity roomInfoEntity) {
        roomInfoMapper.updateById(roomInfoEntity);
        return  ResponseEntity.success();
    }

    @Override
    public ResponseEntity<Object> deleteRoomInfo(String id) {
        RoomInfoEntity roomInfoEntity = Optional.ofNullable(roomInfoMapper.selectById(id)).orElseThrow(() -> new IllegalArgumentException("房间不存在"));
        if (roomInfoEntity.getStatus().equals(RoomStatus.IN_USE.getStatus())) {
            return ResponseEntity.fail("房间正在使用中");
        }
        if (roomInfoEntity.getStatus().equals(RoomStatus.BOOK.getStatus())) {
            return ResponseEntity.fail("房间1️⃣预定");
        }
        roomInfoMapper.deleteById(id);
        return ResponseEntity.success();
    }
}
