package edu.coldrain.hotel_isu.service.room;

import edu.coldrain.hotel_isu.domain.room.Room;
import edu.coldrain.hotel_isu.mapper.room.RoomMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomMapper roomMapper;

    @Override
    public List<Room> findByAll() {
        return roomMapper.findByAll();
    }

    @Override
    public boolean register(Room room) {
        return roomMapper.insert(room);
    }

    @Override
    public boolean modify(Room room) {
        return roomMapper.update(room);
    }

    @Override
    public boolean remove(Long roomCode) {
        return roomMapper.delete(roomCode);
    }
}
