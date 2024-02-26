package com.dorkytiger.hotel_manager.service.room;

import com.dorkytiger.hotel_manager.model.common.ResponseEntity;
import com.dorkytiger.hotel_manager.model.room.RoomBookEntity;

public interface RoomBookService {

    ResponseEntity<Object> roomBook(RoomBookEntity roomBook);

    ResponseEntity<Object> roomUse(String roomId);

}
