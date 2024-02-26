package com.dorkytiger.hotel_manager.service.room.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dorkytiger.hotel_manager.emun.RoomStatus;
import com.dorkytiger.hotel_manager.mapper.room.RoomBookMapper;
import com.dorkytiger.hotel_manager.mapper.room.RoomInfoMapper;
import com.dorkytiger.hotel_manager.model.common.ResponseEntity;
import com.dorkytiger.hotel_manager.model.room.RoomBookEntity;
import com.dorkytiger.hotel_manager.model.room.RoomInfoEntity;
import com.dorkytiger.hotel_manager.service.room.RoomBookService;
import com.dorkytiger.hotel_manager.util.BillNumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomBookServiceImpl implements RoomBookService {

    private final RoomBookMapper roomBookMapper;
    private final RoomInfoMapper roomInfoMapper;

    @Autowired
    public RoomBookServiceImpl(RoomBookMapper roomBookMapper, RoomInfoMapper roomInfoMapper) {
        this.roomBookMapper = roomBookMapper;
        this.roomInfoMapper = roomInfoMapper;
    }

    @Override
    public ResponseEntity<Object> roomBook(RoomBookEntity roomBook) {
        boolean isPresent = Optional.ofNullable(roomBookMapper.selectOne(new LambdaQueryWrapper<RoomBookEntity>().eq(RoomBookEntity::getRoomId, roomBook.getRoomId()))).isPresent();
        if (isPresent) {
            return new ResponseEntity<>().fail("订单已存在");
        }
        String serialNumber = BillNumberUtil.generateBillNumber();
        roomBook.setStatus(RoomStatus.BOOK.getStatus());
        roomBook.setSerialNumber(serialNumber);
        LambdaQueryWrapper<RoomInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoomInfoEntity::getId, roomBook.getRoomId());
        RoomInfoEntity roomInfoEntity = new RoomInfoEntity();
        roomInfoEntity.setStatus(RoomStatus.BOOK.getStatus());
        roomInfoMapper.update(roomInfoEntity, queryWrapper);
        roomBookMapper.insert(roomBook);
        return new ResponseEntity<>().success();
    }

    @Override
    public ResponseEntity<Object> roomUse(String roomId) {
        boolean isPresent = Optional.ofNullable(roomBookMapper.selectOne(new LambdaQueryWrapper<RoomBookEntity>().eq(RoomBookEntity::getRoomId, roomId))).isPresent();
        if (!isPresent) {
            return new ResponseEntity<>().fail("预定不存在");
        }
        RoomInfoEntity roomInfoEntity = Optional.ofNullable(roomInfoMapper.selectById(roomId)).orElseThrow(() -> new IllegalArgumentException("房间不存在"));
        if (roomInfoEntity.getStatus().equals(RoomStatus.IN_USE.getStatus())) {
            return new ResponseEntity<>().fail("房间正在使用中");
        }
        roomInfoEntity.setStatus(RoomStatus.IN_USE.getStatus());
        roomInfoMapper.updateById(roomInfoEntity);
        return new ResponseEntity<>().success();
    }

}
