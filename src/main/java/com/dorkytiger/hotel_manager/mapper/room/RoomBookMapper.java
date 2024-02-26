package com.dorkytiger.hotel_manager.mapper.room;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dorkytiger.hotel_manager.model.room.RoomBookEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoomBookMapper extends BaseMapper<RoomBookEntity> {
}
