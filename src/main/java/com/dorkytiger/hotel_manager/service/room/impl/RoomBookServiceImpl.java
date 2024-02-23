package com.dorkytiger.hotel_manager.service.room.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dorkytiger.hotel_manager.emun.RoomStatus;
import com.dorkytiger.hotel_manager.mapper.room.RoomBookMapper;
import com.dorkytiger.hotel_manager.mapper.room.RoomInfoMapper;
import com.dorkytiger.hotel_manager.model.common.ResponseEntity;
import com.dorkytiger.hotel_manager.model.room.RoomBillEntity;
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
        RoomInfoEntity roomInfoEntity = new RoomInfoEntity();
        roomInfoEntity.setStatus(RoomStatus.IN_USE.getStatus());
        LambdaQueryWrapper<RoomInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoomInfoEntity::getId, roomId);
        roomInfoMapper.update(roomInfoEntity, queryWrapper);
        return new ResponseEntity<>().success();
    }

    @Override
    public ResponseEntity<Object> roomBill(RoomBillEntity roomBillEntity) {
        boolean isPresent = Optional.ofNullable(roomBookMapper.selectOne(new LambdaQueryWrapper<RoomBookEntity>().eq(RoomBookEntity::getRoomId, roomBillEntity.getRoomId()))).isPresent();
        if (!isPresent) {
            return new ResponseEntity<>().fail("预定不存在");
        }
        roomBookMapper.delete(new LambdaQueryWrapper<RoomBookEntity>().eq(RoomBookEntity::getRoomId, roomBillEntity.getRoomId()));
        return null;
    }
}
