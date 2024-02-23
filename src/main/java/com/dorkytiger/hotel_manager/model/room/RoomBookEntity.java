package com.dorkytiger.hotel_manager.model.room;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dorkytiger.hotel_manager.model.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("room_book")
public class RoomBookEntity extends BaseEntity {
    private String customerName;
    private String customerPhone;
    private String roomId;
    private Integer status;
    private String serialNumber;
    private String bookTime;
}
