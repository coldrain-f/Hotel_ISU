package edu.coldrain.hotel_isu.controller.room;

import edu.coldrain.hotel_isu.domain.room.Room;
import edu.coldrain.hotel_isu.service.room.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomRestController {

    private final RoomService roomService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Room>> list() {
        log.info("RoomRestController.list()");

        final List<Room> roomList = roomService.findByAll();
        ResponseEntity<List<Room>> responseEntity = new ResponseEntity<>(roomList, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping(
            value = "/new",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> register(@RequestBody Room room) {
        log.info("RoomRestController.register()");

        log.info("room = {}", room);

        final boolean success = roomService.register(room);
        ResponseEntity<String> responseEntity;
        if (success) {
            responseEntity = new ResponseEntity<>("success", HttpStatus.OK);
            log.info("객실 등록에 성공했습니다.");
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            log.warn("객실 등록에 실패했습니다.");
        }

        return responseEntity;
    }

    @DeleteMapping(
            value = "/{roomCode}",
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> remove(@PathVariable Optional<Long> roomCode) {
        log.info("RoomRestController.remove()");
        ResponseEntity<String> responseEntity;

        if (roomCode.isPresent()) {
            final boolean success = roomService.remove(roomCode.get());
            if (success) {
                responseEntity = new ResponseEntity<>("success", HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @PatchMapping(
            value = "/{roomCode}",
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> modify(@RequestBody Optional<Room> room) {
        ResponseEntity<String> responseEntity;
        if (room.isPresent()) {
            final boolean success = roomService.modify(room.get());
            if (success) {
                responseEntity = new ResponseEntity<>("success", HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

}
