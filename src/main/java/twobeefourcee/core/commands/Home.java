package twobeefourcee.core.commands;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import twobeefourcee.core.Core;


public class Home implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
			
			if(Core.database.contains(player.getUniqueId().toString() + ".home.X")) {
				double x = Core.database.getDouble(player.getUniqueId().toString() + ".home.X");
				double y = Core.database.getDouble(player.getUniqueId().toString() + ".home.Y");
				double z = Core.database.getDouble(player.getUniqueId().toString() + ".home.Z");
				float yaw = (float) Core.database.getLong(player.getUniqueId().toString() +  ".home.yaw");
				float pitch = (float) Core.database.getLong(player.getUniqueId().toString() + ".home.pitch");

				World world = Bukkit.getWorld(Core.database.getString(player.getUniqueId().toString() + ".home.world"));
				
				Location home = new Location(world, x, y, z, yaw, pitch);
				
				player.teleport(home);
					
				sender.sendMessage(Core.success + "Teleported to your home.");
			} else {
				sender.sendMessage(Core.error + "Your home has not been set.");
			}
		}
		
		return true;
	}
}
