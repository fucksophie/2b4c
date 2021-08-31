package twobeefourcee.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import twobeefourcee.core.Core;


public class Kill implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
			
			player.setHealth(0);
			
			if(player.isDead()) {
				Bukkit.broadcastMessage(Core.success + player.getName() + " drank bleach.");
			} else {
				sender.sendMessage(Core.error + "how.. did you just avoid death??");
			}
		}
		
		return true;
	}
}
