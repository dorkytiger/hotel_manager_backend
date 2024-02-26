package com.dorkytiger.hotel_manager.model.room.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBillResponseEntity  {
    private String id;
    private String roomId;
    private String roomName;
    private Integer roomNumber;
    private String roomType;
    private String customerName;
    private String customerPhone;
    private Integer price;
    private String serialNumber;
    private String checkOutTime;
}
