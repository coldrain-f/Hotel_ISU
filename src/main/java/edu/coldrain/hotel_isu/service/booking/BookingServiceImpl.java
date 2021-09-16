package edu.coldrain.hotel_isu.service.booking;

import edu.coldrain.hotel_isu.domain.booking.Booking;
import edu.coldrain.hotel_isu.domain.booking.ReservationPossibleRoom;
import edu.coldrain.hotel_isu.mapper.booking.BookingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingMapper bookingMapper;

    @Override
    public boolean register(Booking booking) {
        return bookingMapper.insert(booking);
    }

    @Override
    public List<ReservationPossibleRoom> findByReservationPossibleRoom(String checkin, String checkout) {
        return bookingMapper.findByReservationPossibleRoom(checkin, checkout);
    }

    @Override
    public List<Booking> findByReservationNotPossibleRoom(String checkin, String checkout) {
        return bookingMapper.findByReservationNotPossibleRoom(checkin, checkout);
    }

    @Override
    public boolean modify(Booking booking) {
        log.info("BookingServiceImpl.modify()");
        log.info("booking = {}", booking);
        return bookingMapper.update(booking);
    }

    @Override
    public boolean remove(Booking booking) {
        return bookingMapper.delete(booking);
    }
}
