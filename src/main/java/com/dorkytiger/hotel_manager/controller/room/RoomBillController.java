package com.dorkytiger.hotel_manager.controller.room;

import com.dorkytiger.hotel_manager.model.common.ResponseEntity;
import com.dorkytiger.hotel_manager.service.room.RoomBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
public class RoomBillController {

    private final RoomBillService roomBillService;

    @Autowired
    public RoomBillController(RoomBillService roomBillService) {
        this.roomBillService = roomBillService;
    }

    @PostMapping("/check/{id}")
    public ResponseEntity<Object> checkOut(@PathVariable String id) {
        return roomBillService.checkOut(id);
    }

}
