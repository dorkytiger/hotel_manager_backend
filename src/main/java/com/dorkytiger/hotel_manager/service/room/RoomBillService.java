package com.dorkytiger.hotel_manager.service.room;

import com.dorkytiger.hotel_manager.model.common.ResponseEntity;

public interface RoomBillService
{
    ResponseEntity<Object> checkOut(String id);

    ResponseEntity<Object> getBillByTimeRange(String start, String end);
}
