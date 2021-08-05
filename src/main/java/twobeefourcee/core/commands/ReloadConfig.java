package twobeefourcee.core.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import twobeefourcee.core.Core;

public class ReloadConfig implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender.isOp()) {
			Core plugin = Core.getPlugin(Core.class);
			
			plugin.reloadConfig();
			
			plugin.loadLocations();
			
			sender.sendMessage(Core.success + "Config reloaded.");
		}
		return true;
	}
}
