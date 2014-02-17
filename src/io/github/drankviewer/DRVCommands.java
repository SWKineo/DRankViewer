package io.github.drankviewer;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
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
		}
		
		Player player = (Player) sender;
		
		// Checks if the command used can decide quantities
		int q;
		if (cmd.getName().equalsIgnoreCase("drvgive")) {
			q = Integer.parseInt(args[0]);
			//plugin.viewItem.setAmount(Integer.parseInt(args[0]));
		} else q = 1;
		
		
		// Adds item(s) to player inventory
		player.getInventory().addItem(new ItemStack(Material.SPONGE, 1));
		
		return false;
	}
	
	private boolean performDRVSetItem (CommandSender sender, String[] args) {
		
		return false;
	}
	
	private boolean performDRVSetChest (CommandSender sender, String[] args) {
		
		return false;
	}

}
