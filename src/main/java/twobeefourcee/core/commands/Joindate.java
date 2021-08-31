package twobeefourcee.core.commands;


import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import twobeefourcee.core.Core;


public class Joindate implements CommandExecutor {

	DateTimeFormatter formatter = DateTimeFormatter
			.ofLocalizedDateTime(FormatStyle.SHORT)
			.withZone(ZoneId.from(ZoneOffset.UTC));
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 1) {
			Player player = Bukkit.getPlayer(args[0]);
			if(player != null) {
				long time = Core.database.getLong(player.getUniqueId().toString() + ".joinDate");
				
				sender.sendMessage(Core.success + "Player " + player.getName() + " joined " + formatter.format(Instant.ofEpochSecond(time)));
			} else {
				sender.sendMessage(Core.error + "Player is not online.");
			}
		} else {
			if(sender instanceof Player) {
				long time = Core.database.getLong(((Player) sender).getUniqueId().toString() + ".joinDate");
				
				sender.sendMessage(Core.success + "You joined " + formatter.format(Instant.ofEpochSecond(time)));
			}
		}

		return true;
	}
}
