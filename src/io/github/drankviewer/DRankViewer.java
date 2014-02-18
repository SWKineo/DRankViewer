package io.github.drankviewer;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class DRankViewer extends JavaPlugin {

	// Generating global access to the logger and config File.
	public Logger logger;
	public FileConfiguration config = getConfig();
	public File configFile = new File(getDataFolder(), "config.yml");
	
	
	@Override
	public void onEnable () {
		logger = getLogger();
		
		// Save config.yml
		if (!configFile.exists()) {
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
		try {
			config.save(configFile);
		} catch (IOException e) {
			logger.info("Configuration saving failed");
			e.printStackTrace();
		}
	}
	
}
