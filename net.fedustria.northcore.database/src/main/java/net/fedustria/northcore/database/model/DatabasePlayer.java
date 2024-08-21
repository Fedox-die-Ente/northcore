package net.fedustria.northcore.database.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DatabasePlayer {

    private UUID uniqueId;
    private String playerName;
    private String currentIpAddress = "127.0.0.1";

    // Read / Write Data from / to Database

}
