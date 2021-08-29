package twobeefourcee.core.commands;


import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import twobeefourcee.core.Core;


public class SetHome implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
			
			Location location = player.getLocation();
			
			Core.homes.set("homes." + player.getUniqueId().toString() + ".X", location.getX());
			Core.homes.set("homes." + player.getUniqueId().toString() + ".Y", location.getY());
			Core.homes.set("homes." + player.getUniqueId().toString() + ".Z", location.getZ());
			Core.homes.set("homes." + player.getUniqueId().toString() + ".yaw", location.getYaw());
			Core.homes.set("homes." + player.getUniqueId().toString() + ".pitch", location.getPitch());
			Core.homes.set("homes." + player.getUniqueId().toString() + ".world", player.getWorld().getName());
				
			try {
				Core.homes.save(Core.homesFile);
				sender.sendMessage(Core.success + "Home set.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

}
