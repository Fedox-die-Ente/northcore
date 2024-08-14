package net.fedustria.northcore.bootstrap.bukkit.commands;

import net.fedustria.northcore.bridge.enums.NorthSounds;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {


	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		((Player) commandSender).playSound(((Player) commandSender).getLocation(), NorthSounds.BLOCK_NOTE_BLOCK_PLING.asBukkitSound(), 1, 1);

		return false;
	}
}
