package io.github.drankviewer;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.util.logging.Logger;

public class DRankViewer extends JavaPlugin {

	
	public Logger logger;
	//public ItemStack viewItem = new ItemStack (getConfig().getInt("viewItemID"))
	
	@Override
	public void onEnable () {
		logger = getLogger();
		
		// Save config.yml
		if (!new File(getDataFolder(), "config.yml").exists()) {
			saveDefaultConfig();
		}
		
		// Set command executors
		final DRVCommands drvCommands = new DRVCommands(this);
		getCommand("DRVSetChest").setExecutor(drvCommands);
		getCommand("DRVSetItem").setExecutor(drvCommands);
		getCommand("DRVGive").setExecutor(drvCommands);
		getCommand("DRV").setExecutor(drvCommands);		
	}
	
	@Override
	public void onDisable () {
		// TODO Insert logic to be performed when the plugin is disabled.
	}
	
}
