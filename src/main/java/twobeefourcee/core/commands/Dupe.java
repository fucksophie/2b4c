package twobeefourcee.core.commands;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import twobeefourcee.core.Core;


public class Dupe implements CommandExecutor {
	
	private HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
			if(!cooldown.containsKey(player.getUniqueId())) {
				ItemStack item = player.getInventory().getItemInMainHand();
				player.getInventory().addItem(item);
					
				sender.sendMessage(Core.success + "Duped item " + item.getType().toString() + "!");
				
				cooldown.put(player.getUniqueId(), System.currentTimeMillis());
			} else {
				if(cooldown.get(player.getUniqueId()) + 5000 < System.currentTimeMillis()) {
					cooldown.remove(player.getUniqueId());
					this.onCommand(sender, cmd, label, args);
				} else {
					sender.sendMessage(Core.error + "You are on cooldown for " + String.valueOf(cooldown.get(player.getUniqueId())/1000+5-System.currentTimeMillis()/1000) + "s.");
				}
			}
			
		}
		
		return true;
	}
}
