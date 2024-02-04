package com.dorkytiger.hotel_manager.model.common;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data

public abstract class BaseEntity {
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
     String id;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
     LocalDateTime createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
     LocalDateTime updateTime;
}
