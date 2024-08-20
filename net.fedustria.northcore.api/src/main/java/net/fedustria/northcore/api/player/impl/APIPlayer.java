package net.fedustria.northcore.api.player.impl;

import lombok.Getter;
import lombok.NonNull;

public class APIPlayer {

    @Getter
    public String language = "german";

    public String replacePlaceholder(@NonNull String message) {
        return message
                .replaceAll("%prefix%", "cooler prefix")
                ;
    }

}
