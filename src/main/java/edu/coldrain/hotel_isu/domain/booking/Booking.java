package edu.coldrain.hotel_isu.domain.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Booking {

    private Long bookCode;

    private Long roomCode;

    private String name;

    private String typeName; //join

    private String checkin;

    private String checkout;

    private Integer total;

    private String mobile;
}
