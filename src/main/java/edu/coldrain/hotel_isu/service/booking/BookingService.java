package edu.coldrain.hotel_isu.service.booking;

import edu.coldrain.hotel_isu.domain.booking.Booking;
import edu.coldrain.hotel_isu.domain.booking.ReservationPossibleRoom;

import java.util.List;

public interface BookingService {

    List<ReservationPossibleRoom> findByReservationPossibleRoom(String checkin, String checkout);

    List<Booking> findByReservationNotPossibleRoom(String checkin, String checkout);

    boolean register(Booking booking);

    boolean modify(Booking booking);

    boolean remove(Booking booking);
}
