package com.dorkytiger.hotel_manager.controller.room;

import com.dorkytiger.hotel_manager.model.common.ResponseEntity;
import com.dorkytiger.hotel_manager.service.room.RoomBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value ={"/range/{start}/{end}", "/range"} )
    public ResponseEntity<Object> getBillByTimeRange(@PathVariable(value = "start",required = false) String start, @PathVariable(value = "end",required = false) String end) {
        return roomBillService.getBillByTimeRange(start, end);
    }

}
