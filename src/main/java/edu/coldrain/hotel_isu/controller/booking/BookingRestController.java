package edu.coldrain.hotel_isu.controller.booking;

import edu.coldrain.hotel_isu.domain.booking.Booking;
import edu.coldrain.hotel_isu.domain.booking.ReservationPossibleRoom;
import edu.coldrain.hotel_isu.service.booking.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingRestController {

    private final BookingService bookingService;

    /**
     * 객실 예약 등록
     *
     * @param booking
     * @return
     */
    @PostMapping(
            value = "/new",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> register(@RequestBody Booking booking) {
        log.info("BookingRestController register()");

        ResponseEntity<String> responseEntity;

        final boolean success = bookingService.register(booking);
        if (success) {
            responseEntity = new ResponseEntity<>("success", HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    /**
     * 객실 예약정보 수정
     *
     * @param booking
     * @return
     */
    @PatchMapping(
            value = "/modify",
            produces = MediaType.TEXT_PLAIN_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> modify(@RequestBody Booking booking) {
        log.info("BookingRestController.modify()");
        ResponseEntity<String> responseEntity;
        log.info("booking = {}", booking);
        final boolean success = bookingService.modify(booking);
        if (success) {
            responseEntity = new ResponseEntity<>("success", HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    /**
     * 객실 예약 삭제
     *
     * @param booking
     * @return
     */
    @DeleteMapping(
            value = "/remove",
            produces = MediaType.TEXT_PLAIN_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> remove(@RequestBody Booking booking) {
        log.info("BookingRestController.remove()");
        ResponseEntity<String> responseEntity;
        final boolean success = bookingService.remove(booking);
        if (success) {
            responseEntity = new ResponseEntity<>("success", HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    /**
     * 예약 불가능한 객실 리스트 가져오기
     *
     * @return
     */
    @GetMapping("/notPossible")
    public ResponseEntity<List<Booking>> getNotPossibleRoomList(
            @RequestParam String checkin, @RequestParam String checkout) {
        log.info("BookingRestController.getNotPossibleRoomList()");

        log.info("checkin = {}", checkin);
        log.info("checkout = {}", checkout);

        final List<Booking> reservationNotPossibleRoomList
                = bookingService.findByReservationNotPossibleRoom(checkin, checkout);

        return new ResponseEntity<>(reservationNotPossibleRoomList, HttpStatus.OK);
    }

    /**
     * 예약 가능한 객실 리스트 가져오기
     *
     * @return
     */
    @GetMapping("/possible")
    public ResponseEntity<List<ReservationPossibleRoom>> getPossibleRoomList(
            @RequestParam String checkin, @RequestParam String checkout) {
        log.info("BookingRestController.getPossibleRoomList()");

        log.info("checkin = {}", checkin);
        log.info("checkout = {}", checkout);

        final List<ReservationPossibleRoom> reservationPossibleRoomList
                = bookingService.findByReservationPossibleRoom(checkin, checkout);

        return new ResponseEntity<>(reservationPossibleRoomList, HttpStatus.OK);
    }
}