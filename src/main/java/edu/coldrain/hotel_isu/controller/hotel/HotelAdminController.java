package edu.coldrain.hotel_isu.controller.hotel;

import edu.coldrain.hotel_isu.domain.room.RoomType;
import edu.coldrain.hotel_isu.mapper.room.RoomTypeMapper;
import edu.coldrain.hotel_isu.service.booking.BookingService;
import edu.coldrain.hotel_isu.service.room.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/hotel/admin/*")
@RequiredArgsConstructor
public class HotelAdminController {

    private final RoomTypeMapper roomTypeMapper;
    private final RoomService roomService;
    private final BookingService bookingService;

    @GetMapping("/room")
    public String roomForm(Model model) {
        log.info("HotelAdminController.roomForm()");

        final List<RoomType> roomTypeList = roomTypeMapper.findByAll();
        model.addAttribute("roomTypeList", roomTypeList);

        return "hotel-admin/room-admin";
    }

    @GetMapping("/reservation")
    public String reservationForm(Model model) {
        log.info("HotelAdminController.reservationForm()");

//        final List<ReservationPossibleRoom> reservationPossibleRoomList
//                = bookingService.findByReservationPossibleRoom("2021-09-08", "2021-09-09");
//        model.addAttribute("reservationPossibleRoomList", reservationPossibleRoomList);
//
//        final List<Booking> reservationNotPossibleRoomList
//                = bookingService.findByReservationNotPossibleRoom("2021-09-08", "2021-09-09");
//        model.addAttribute("reservationNotPossibleRoomList", reservationNotPossibleRoomList);

        return "hotel-admin/reservation-admin";
    }

}
