package net.fedustria.northcore.api.player.impl;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import net.fedustria.northcore.api.core.BaseCore;
import net.fedustria.northcore.api.player.IAPIPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class APIPlayer implements IAPIPlayer {

    private static HashMap<UUID, APIPlayer> players = new HashMap<>();

    private final UUID uniqueId;

    private APIPlayer(@NonNull final UUID uniqueId) {
        this.uniqueId = uniqueId;
        players.put(uniqueId, this);
    }

    public static APIPlayer get(@NonNull final UUID uniqueId) {
        return players.getOrDefault(uniqueId, new APIPlayer(uniqueId));
    }


    @Override
    public String getShortUniqueId() {
        return "";
    }

    @Override
    public String getName() {
        // TODO: Implement this method via getDatabasePlayer
        // return getDatabasePlayer().getPlayerName();
        return "Player";
    }

    @Override
    public String getDisplayName() {
        // Check if player is nicked, if yes return nickName from the nickProfile
        // TEMPORARY: return name

        return getName();
    }

    @Override
    public String replacePlaceholder(final String message) {
        return message
                .replaceAll("%test%", "Test Value");
    }

    @Override
    public String getColor1() {
        // TODO: Return via DesignUtils the too minecraft formatted hex color from the default settings
        return "";
    }

    @Override
    public String getColor2() {
        // TODO: Return via DesignUtils the too minecraft formatted hex color from the default settings
        return "";
    }

    @Override
    public String getPrefix() {
        // TODO:  getSettings().getString("prefixSplitter") + getSettings().getString("networkNameWithColors") + " " + getArrow() + " ";
        return "";
    }

    @Override
    public String getLanguage() {
        return "";
    }

    @Override
    public boolean isOnline() {
        return BaseCore.getCore().isOnline(uniqueId);
    }

    @Override
    public void sendMessage(final String message) {
        if (message == null || !isOnline()) {
            return;
        }

        BaseCore.getCore().sendMessage(this, replacePlaceholder(message));
    }

    @Override
    public void sendMessages(final List<String> messages) {
        messages.forEach(this::sendMessage);
    }

    @Override
    public void sendNoPermissionMessage() {
        sendMessage(getPrefix() + "§cYou don't have the permission to do that.");
    }

    @Override
    public void sendLobby() {
        // getCloudPlayer and send to Lobby
    }

    @Override
    public BukkitPlayer getBukkitPlayer() {
        return BaseCore.getCore().getBukkit().getBukkitPlayer(this.uniqueId);
    }
}
