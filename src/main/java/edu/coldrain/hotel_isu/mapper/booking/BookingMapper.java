package edu.coldrain.hotel_isu.mapper.booking;

import edu.coldrain.hotel_isu.domain.booking.Booking;
import edu.coldrain.hotel_isu.domain.booking.ReservationPossibleRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookingMapper {

    List<ReservationPossibleRoom> findByReservationPossibleRoom(
            @Param("checkin") String checkin, @Param("checkout") String checkout);

    List<Booking> findByReservationNotPossibleRoom(
            @Param("checkin") String checkin, @Param("checkout") String checkout);

    boolean insert(Booking booking);

    boolean update(Booking booking);

    boolean delete(Booking booking);
}
