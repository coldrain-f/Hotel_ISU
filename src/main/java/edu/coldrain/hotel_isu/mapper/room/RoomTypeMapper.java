package edu.coldrain.hotel_isu.mapper.room;

import edu.coldrain.hotel_isu.domain.room.RoomType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomTypeMapper {

    List<RoomType> findByAll();
}
