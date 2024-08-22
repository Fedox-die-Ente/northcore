package net.fedustria.northcore.api.core;

import lombok.Getter;
import lombok.NonNull;
import net.fedustria.northcore.api.player.impl.APIPlayer;
import net.fedustria.northcore.api.player.impl.BukkitPlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

@Getter
public class BukkitCore extends BaseCore {

    private final JavaPlugin plugin;

    public BukkitCore(@NonNull JavaPlugin plugin) {
        this.plugin = plugin;

    }

    public BukkitPlayer getBukkitPlayer(@NonNull UUID uniqueId) {
        return BukkitPlayer.get(uniqueId);
    }

    @Override
    public void sendMessage(APIPlayer apiPlayer, String message) {
        BukkitPlayer bukkitPlayer = getBukkitPlayer(apiPlayer.getUniqueId());
        if (!bukkitPlayer.isOnline()) return;
        bukkitPlayer.sendMessage(message);
    }

    @Override
    public boolean isOnline(UUID uniqueId) {
        return getBukkitPlayer(uniqueId).isOnline();
    }
}
