package com.dorkytiger.hotel_manager.service.room.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dorkytiger.hotel_manager.emun.RoomStatus;
import com.dorkytiger.hotel_manager.mapper.room.RoomBillMapper;
import com.dorkytiger.hotel_manager.mapper.room.RoomBookMapper;
import com.dorkytiger.hotel_manager.mapper.room.RoomInfoMapper;
import com.dorkytiger.hotel_manager.model.common.ResponseEntity;
import com.dorkytiger.hotel_manager.model.room.RoomBillEntity;
import com.dorkytiger.hotel_manager.model.room.RoomBookEntity;
import com.dorkytiger.hotel_manager.model.room.RoomInfoEntity;
import com.dorkytiger.hotel_manager.service.room.RoomBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomBillServiceImpl implements RoomBillService {

    private final RoomBillMapper roomBillMapper;
    private final RoomBookMapper roomBookMapper;
    private final RoomInfoMapper roomInfoMapper;

    @Autowired
    public RoomBillServiceImpl(RoomBillMapper roomBillMapper, RoomBookMapper roomBookMapper, RoomInfoMapper roomInfoMapper) {
        this.roomBillMapper = roomBillMapper;
        this.roomBookMapper = roomBookMapper;
        this.roomInfoMapper = roomInfoMapper;
    }

    @Override
    public ResponseEntity<Object> checkOut(String id) {
        RoomInfoEntity roomInfoEntity = Optional.ofNullable(roomInfoMapper.selectById(id)).orElseThrow(() -> new IllegalArgumentException("房间不存在"));
        RoomBookEntity roomBookEntity = Optional.ofNullable(roomBookMapper.selectOne(new LambdaQueryWrapper<RoomBookEntity>().eq(RoomBookEntity::getRoomId, id))).orElseThrow(() -> new IllegalArgumentException("订单不存在"));
        RoomBillEntity roomBillEntity = new RoomBillEntity();
        roomBillEntity.setRoomId(roomBookEntity.getRoomId());
        roomBillEntity.setSerialNumber(roomBookEntity.getSerialNumber());
        roomBillEntity.setPrice(roomInfoEntity.getPrice());
        roomBillEntity.setCustomerName(roomBookEntity.getCustomerName());
        roomBillEntity.setCustomerPhone(roomBookEntity.getCustomerPhone());
        roomBillMapper.insert(roomBillEntity);
        roomBookMapper.deleteById(roomBookEntity.getId());
        roomInfoEntity.setStatus(RoomStatus.SPACE.getStatus());
        roomInfoMapper.updateById(roomInfoEntity);
        return new ResponseEntity<>().success();
    }
}
