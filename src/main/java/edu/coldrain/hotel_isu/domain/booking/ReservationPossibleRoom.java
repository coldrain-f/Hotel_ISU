package edu.coldrain.hotel_isu.domain.booking;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservationPossibleRoom {

    private Long roomCode;

    private String name;

    private Integer howMany;

    private Integer howMuch;

    private String typeName;
}
