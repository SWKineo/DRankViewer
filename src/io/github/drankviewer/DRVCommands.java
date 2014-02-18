package io.github.drankviewer;

import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DRVCommands implements CommandExecutor {
	
	private DRankViewer plugin;
	
	public DRVCommands (DRankViewer plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("drv")) {
			return performDRV(sender, cmd, args);
		} else if (cmd.getName().equalsIgnoreCase("drvgive")) {
			return performDRV(sender, cmd, args);			
		} else if (cmd.getName().equalsIgnoreCase("drvsetitem")) {
			return performDRVSetItem (sender, args);
		} else if (cmd.getName().equalsIgnoreCase("drvsetchest")) {
			return performDRVSetChest (sender, args);
		}
		return false;		
	}
	
	// Execution of both /DRV and /DRVGive is done through the same method, but /DRV doesn't get to decide quantities
	private boolean performDRV (CommandSender sender, Command cmd, String[] args) {
		// Checks that sender is a player
		if (! (sender instanceof Player)) {
			sender.sendMessage("This command can only be run by a player.");
			return true;
		}
		
		Player player = (Player) sender;
		ItemStack viewItem = plugin.config.getItemStack("Item for Viewing");
		
		// Checks if the command used can decide quantities
		if (cmd.getName().equalsIgnoreCase("drvgive") && args.length == 1) {
			viewItem.setAmount(Integer.parseInt(args[0]));
		} else if (!cmd.getName().equalsIgnoreCase("drv") || !cmd.getName().equalsIgnoreCase("drvgive")) {
			return false;
		}
		
		// Adds item(s) to player inventory
		player.getInventory().addItem(viewItem);
		
		return true;
	}
	
	private boolean performDRVSetItem (CommandSender sender, String[] args) {
		// Checks that sender is a player
		if (! (sender instanceof Player)) {
			sender.sendMessage("This command can only be run by a player.");
			return true;
		}
		
		if (args.length != 1) {
			sender.sendMessage(ChatColor.DARK_RED + "If you are sure you want to proceed, type " + ChatColor.GRAY +"/DRVSetItem Confirm");
			return true;
		}
		if (!args[0].equalsIgnoreCase("Confirm")) return true;
		
		// Gets the item in the player's hand
		Player player = (Player) sender;
		ItemStack item = player.getItemInHand();
		item.setAmount(1);
		
		// Saves the item in the config
		plugin.config.set("Item for Viewing", item);
		try {
			plugin.config.save(plugin.configFile);
		} catch (IOException e) {
			plugin.logger.info("Configuration saving failed");
			e.printStackTrace();
			return true;
		}
		sender.sendMessage(ChatColor.DARK_RED + "Saved sucessfully.");

		return true;
	}
	
	private boolean performDRVSetChest (CommandSender sender, String[] args) {
		// Checks that sender is a player
		if (! (sender instanceof Player)) {
			sender.sendMessage("This command can only be run by a player.");
			return true;
		}
		
		if (args.length != 1) {
			sender.sendMessage(ChatColor.DARK_RED + "If you are sure you want to proceed, type " + ChatColor.GRAY +"/DRVSetChest Confirm");
			return true;
		}
		if (!args[0].equalsIgnoreCase("Confirm")) return true;
		
		
		return true;
	}

}
