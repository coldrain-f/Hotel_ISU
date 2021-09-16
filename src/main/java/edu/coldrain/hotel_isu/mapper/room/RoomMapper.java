package edu.coldrain.hotel_isu.mapper.room;

import edu.coldrain.hotel_isu.domain.room.Room;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomMapper {

    List<Room> findByAll();

    boolean insert(Room room);

    boolean delete(Long roomCode);

    boolean update(Room room);
}
