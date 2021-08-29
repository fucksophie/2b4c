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
			
			Core.database.set(player.getUniqueId().toString() + ".home.X", location.getX());
			Core.database.set(player.getUniqueId().toString() + ".home.Y", location.getY());
			Core.database.set(player.getUniqueId().toString() + ".home.Z", location.getZ());
			Core.database.set(player.getUniqueId().toString() + ".home.yaw", location.getYaw());
			Core.database.set(player.getUniqueId().toString() + ".home.pitch", location.getPitch());
			Core.database.set(player.getUniqueId().toString() + ".home.world", player.getWorld().getName());
				
			try {
				Core.database.save(Core.databaseFile);
				sender.sendMessage(Core.success + "Home set.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

}
