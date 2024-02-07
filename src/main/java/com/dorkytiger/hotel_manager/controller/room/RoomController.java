package com.dorkytiger.hotel_manager.controller.room;

import com.dorkytiger.hotel_manager.model.common.ResponseEntity;
import com.dorkytiger.hotel_manager.model.room.RoomInfoEntity;
import com.dorkytiger.hotel_manager.service.room.RoomInfoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomInfoService roomInfoService;

    public RoomController(RoomInfoService roomInfoService) {
        this.roomInfoService = roomInfoService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createRoom(@RequestBody RoomInfoEntity roomInfoEntity) {
        return roomInfoService.createRoomInfo(roomInfoEntity);
    }


    @GetMapping("/list")
    public ResponseEntity<List<RoomInfoEntity>> roomList() {
        return roomInfoService.roomList();
    }

}
