package edu.coldrain.hotel_isu.domain.room;

import lombok.Data;

@Data
public class Room {

    private Long roomCode;
    private String name;

    private Integer type;

    private Integer howMany;
    private Integer howMuch;

    private String roomType; //조인

}
