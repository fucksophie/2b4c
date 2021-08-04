package twobeefourcee.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import twobeefourcee.core.Core;

public class Update implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender.isOp()) {
			String path = Core.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			Bukkit.broadcastMessage("Deleting " + path);

		}
		// If the player (or console) uses our command correct, we can return true
		return true;
	}
}
