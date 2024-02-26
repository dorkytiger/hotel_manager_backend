package com.dorkytiger.hotel_manager.controller.room;


import com.dorkytiger.hotel_manager.model.common.ResponseEntity;
import com.dorkytiger.hotel_manager.model.room.RoomBookEntity;
import com.dorkytiger.hotel_manager.service.room.RoomBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class RoomBookController {



    @Autowired
    public RoomBookController(RoomBookService roomBookService) {
        this.roomBookService = roomBookService;
    }

    private final RoomBookService roomBookService;

    @PostMapping("/room")
    public ResponseEntity<Object> roomBook(@RequestBody RoomBookEntity roomBook) {
        return roomBookService.roomBook(roomBook);
    }

    @PutMapping("/use/{roomId}")
    public ResponseEntity<Object> roomUse(@PathVariable String roomId){
        return roomBookService.roomUse(roomId);
    }

}
