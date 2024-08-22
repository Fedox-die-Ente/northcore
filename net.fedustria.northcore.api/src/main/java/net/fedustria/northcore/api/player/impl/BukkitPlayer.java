package net.fedustria.northcore.api.player.impl;

import lombok.Getter;
import lombok.Setter;
import net.fedustria.northcore.api.core.BaseCore;
import net.fedustria.northcore.api.player.IAPIPlayer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class BukkitPlayer implements IAPIPlayer {

    @Getter
    private static HashMap<UUID, BukkitPlayer> players = new HashMap<>();

    private final APIPlayer handle;
    private final Player player;

    private BukkitPlayer(APIPlayer handle) {
        this.handle = handle;

        this.player = Bukkit.getPlayer(handle.getUniqueId());

        players.put(handle.getUniqueId(), this);
    }

    public static BukkitPlayer get(UUID uniqueId) {
        if (players.containsKey(uniqueId)) return players.get(uniqueId);
        return new BukkitPlayer(BaseCore.getCore().getAPIPlayer(uniqueId));
    }

    @Override
    public UUID getUniqueId() {
        return this.handle.getUniqueId();
    }

    @Override
    public String getShortUniqueId() {
        return this.handle.getShortUniqueId();
    }

    @Override
    public String getName() {
        return this.handle.getName();
    }

    @Override
    public String getDisplayName() {
        return this.handle.getDisplayName();
    }

    @Override
    public String replacePlaceholder(String message) {
        if (message == null) return "";

        String replaced = message
                .replaceAll("%test%", "Test Value");

        return this.handle.replacePlaceholder(replaced);
    }

    @Override
    public String getColor1() {
        return this.handle.getColor1();
    }

    @Override
    public String getColor2() {
        return this.handle.getColor2();
    }

    @Override
    public String getPrefix() {
        return this.handle.getPrefix();
    }

    @Override
    public String getLanguage() {
        return this.handle.getLanguage();
    }

    @Override
    public boolean isOnline() {
        if (BaseCore.getCore().getBukkit().getPlugin().getServer().getPlayer(this.getUniqueId()) == null) return false;
        return BaseCore.getCore().getBukkit().getPlugin().getServer().getPlayer(this.getUniqueId()).isOnline();
    }

    @Override
    public void sendMessage(String message) {
        if (this.player != null) {
            if (this.player.isOnline()) {
                this.player.sendMessage(this.replacePlaceholder(message));
                return;
            }
        }

        getHandle().sendMessage(message);
    }

    @Override
    public void sendMessages(List<String> messages) {
        for (String message : messages) {
            sendMessage(message);
        }
    }

    @Override
    public void sendNoPermissionMessage() {
        this.handle.sendNoPermissionMessage();
    }

    @Override
    public void sendLobby() {
        this.handle.sendLobby();
    }

    @Override
    public BukkitPlayer getBukkitPlayer() {
        return this;
    }

    public void setGameMode(GameMode gameMode) {
        getPlayer().setGameMode(gameMode);
    }

}
