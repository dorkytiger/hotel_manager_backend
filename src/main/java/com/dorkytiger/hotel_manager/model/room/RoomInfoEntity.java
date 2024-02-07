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
@TableName("room_info")
public class RoomInfoEntity extends BaseEntity {
    String name;
    String type;
    Integer price;
    Integer status;
    String description;
    Integer floor;
    Integer roomNumber;
}
