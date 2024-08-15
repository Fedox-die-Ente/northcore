package net.fedustria.northcore.setup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NorthConfiguration {

    private String host;
    private int port;
    private String username;
    private String password;
    private String database;
    private String authDatabase;

}
