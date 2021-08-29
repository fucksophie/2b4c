package twobeefourcee.core.commands;


import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import twobeefourcee.core.Core;


public class DelHome implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
						
			Core.homes.set("homes." + player.getUniqueId().toString(), null);
				
			try {
				Core.homes.save(Core.homesFile);
				sender.sendMessage(Core.success + "Home deleted.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

}
