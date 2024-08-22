package net.fedustria.northcore.bootstrap.bukkit.commands;

import net.fedustria.northcore.api.core.BukkitCore;
import net.fedustria.northcore.api.player.impl.APIPlayer;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {


    @Override
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] strings) {

        if (commandSender instanceof final Player player) {


            // After rejoin the message is not sent, but the api player is not null

            final APIPlayer apiPlayer = BukkitCore.getCore().getAPIPlayer(player.getUniqueId());

            System.out.println("APIPlayer: " + apiPlayer.getBukkitPlayer().getPlayer().getName());

            if (apiPlayer == null) {
                player.sendMessage("APIPlayer could not be found or created.");
                return true;
            }

            apiPlayer.sendMessage("Hallo hier ist ein Placeholder: %test%");
            apiPlayer.sendMessage("Deine UUID: " + apiPlayer.getUniqueId());

            apiPlayer.getBukkitPlayer().setGameMode(GameMode.CREATIVE);

            player.sendMessage("Command executed successfully.");
        }

        return true;
    }
}
