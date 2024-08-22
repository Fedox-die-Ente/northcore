package net.fedustria.northcore.api.player;

import net.fedustria.northcore.api.player.impl.BukkitPlayer;

import java.util.List;
import java.util.UUID;

public interface IAPIPlayer {

    UUID getUniqueId();

    // CloudPlayer
    String getShortUniqueId();

    String getName();

    String getDisplayName();

    // getDisplayName with BukkitPlayer

    String replacePlaceholder(String message);

    String getColor1();

    String getColor2();

    String getPrefix();

    String getLanguage();

    boolean isOnline();

    void sendMessage(String message);

    void sendMessages(List<String> messages);

    void sendNoPermissionMessage();

    // TODO: Add coin shit

    // TODO: add clan shit

    // TODO: Cloud Permission shit

    void sendLobby();

    BukkitPlayer getBukkitPlayer();
}
