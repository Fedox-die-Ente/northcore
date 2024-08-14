package net.fedustria.northcore.bootstrap.bukkit;

import net.fedustria.northcore.api.coupling.ClassLoaderService;
import net.fedustria.northcore.bootstrap.bukkit.commands.TestCommand;
import org.bukkit.plugin.java.JavaPlugin;

import static net.fedustria.northcore.api.coupling.ComponentScope.Bukkit;

public class BukkitBootstrap extends JavaPlugin {

//	private BukkitCore bukkitCore;

	@Override
	public void onEnable() {
		ClassLoaderService.create(Bukkit);

		getCommand("testcmd").setExecutor(new TestCommand());
		
//		this.bukkitCore = new BukkitCore(this);
//		this.bukkitCore.setEnabled(true);
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}
}