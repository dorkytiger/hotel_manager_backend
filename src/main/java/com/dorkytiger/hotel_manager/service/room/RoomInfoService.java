package com.dorkytiger.hotel_manager.service.room;

import com.dorkytiger.hotel_manager.model.common.ResponseEntity;
import com.dorkytiger.hotel_manager.model.room.RoomInfoEntity;

import java.util.List;

public interface RoomInfoService {

    ResponseEntity<Object> createRoomInfo(RoomInfoEntity roomInfoEntity);

    ResponseEntity<List<RoomInfoEntity>> roomList();

    ResponseEntity<Object> updateRoomInfo(RoomInfoEntity roomInfoEntity);

    ResponseEntity<Object> deleteRoomInfo(String id);
}
