package net.fedustria.northcore.bootstrap.bungeecord;

import net.fedustria.northcore.api.component.ComponentLoader;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.logging.Level;

import static net.fedustria.northcore.api.component.ComponentScope.Bungeecord;

public class BungeeBootstrap extends Plugin {

    @Override
    public void onEnable() {
        ComponentLoader.create(Bungeecord);

        getLogger().log(Level.INFO, "Plugin is loaded, yippi kai yay! :D Fortnite is such a bad game, I'm glad I'm not playing it. Play Minecraft instead! :D");
    }

    @Override
    public void onDisable() {
    }
}
