package edu.coldrain.hotel_isu.service.room;

import edu.coldrain.hotel_isu.domain.room.Room;

import java.util.List;

public interface RoomService {

    List<Room> findByAll();

    boolean register(Room room);

    boolean modify(Room room);

    boolean remove(Long roomCode);
}
