package twobeefourcee.core.commands;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import twobeefourcee.core.Core;

public class Playtime implements CommandExecutor {
	DateTimeFormatter formatter = DateTimeFormatter
			.ofLocalizedDateTime(FormatStyle.SHORT)
			.withZone(ZoneId.from(ZoneOffset.UTC));
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 1) {
			Player player = Bukkit.getPlayer(args[0]);
			if(player != null) {
				double time = ((double)player.getStatistic(Statistic.PLAY_ONE_MINUTE)) / 20;
				double days = time / 86400;
				double hours = (time % 86400) / 3600;
				double minutes = ((time % 86400) % 3600) / 60;
				double seconds = ((time % 86400) % 3600) % 60;

				sender.sendMessage(Core.success + player.getName() + " played for " 
				+ String.format("%.2f", days) + "d, " + String.format("%.2f", hours) + "h, "
						+ String.format("%.2f", minutes) + "m, " + String.format("%.2f", seconds) + "s.");
			} else {
				sender.sendMessage(Core.error + "Player is not online.");
			}
		} else {
			if(sender instanceof Player) {
				double time = ((double)((Player)sender).getStatistic(Statistic.PLAY_ONE_MINUTE)) / 20;
				double days = time / 86400;
				double hours = (time % 86400) / 3600;
				double minutes = ((time % 86400) % 3600) / 60;
				double seconds = ((time % 86400) % 3600) % 60;

				sender.sendMessage(Core.success + "You have played for " + String.format("%.2f", days) + "d, " + String.format("%.2f", hours) + "h, "
						+ String.format("%.2f", minutes) + "m, " + String.format("%.2f", seconds) + "s.");
			}
		}

		return true;
	}
}
