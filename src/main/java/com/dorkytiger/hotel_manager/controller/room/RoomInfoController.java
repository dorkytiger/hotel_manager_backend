package com.dorkytiger.hotel_manager.controller.room;

import com.dorkytiger.hotel_manager.model.common.ResponseEntity;
import com.dorkytiger.hotel_manager.model.room.RoomInfoEntity;
import com.dorkytiger.hotel_manager.service.room.RoomInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomInfoController {

    private final RoomInfoService roomInfoService;

    @Autowired
    public RoomInfoController(RoomInfoService roomInfoService) {
        this.roomInfoService = roomInfoService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createRoom(@RequestBody RoomInfoEntity roomInfoEntity) {
        return roomInfoService.createRoomInfo(roomInfoEntity);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateRoom(@RequestBody RoomInfoEntity roomInfoEntity) {
        return roomInfoService.updateRoomInfo(roomInfoEntity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteRoom(@PathVariable String id) {
        return roomInfoService.deleteRoomInfo(id);
    }

    @GetMapping("/list")
    public ResponseEntity<List<RoomInfoEntity>> roomList() {
        return roomInfoService.roomList();
    }


}
