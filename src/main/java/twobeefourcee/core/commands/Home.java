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
			
			if(Core.homes.contains("homes." + player.getUniqueId().toString() + ".X")) {
				double x = Core.homes.getDouble("homes." + player.getUniqueId().toString() + ".X");
				double y = Core.homes.getDouble("homes." + player.getUniqueId().toString() + ".Y");
				double z = Core.homes.getDouble("homes." + player.getUniqueId().toString() + ".Z");
				float yaw = (float) Core.homes.getLong("homes." + player.getUniqueId().toString() +  ".yaw");
				float pitch = (float) Core.homes.getLong("homes." + player.getUniqueId().toString() + ".pitch");

				World world = Bukkit.getWorld(Core.homes.getString("homes." + player.getUniqueId().toString() + ".world"));
				
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
