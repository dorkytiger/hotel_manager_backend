package com.dorkytiger.hotel_manager.model.room;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dorkytiger.hotel_manager.model.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("room_bill")
public class RoomBillEntity extends BaseEntity {
    private String roomId;
    private String customerName;
    private String customerPhone;
    private String SerialNumber;
    private Integer money;
}