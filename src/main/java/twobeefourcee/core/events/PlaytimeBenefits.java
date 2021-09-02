package twobeefourcee.core.events;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.md_5.bungee.api.ChatColor;

public class PlaytimeBenefits implements Listener {
	
    @EventHandler
    static public void joinEvent(PlayerJoinEvent event) {
    	Player player = event.getPlayer();
    	double time = ((double)player.getStatistic(Statistic.PLAY_ONE_MINUTE)) / 20;
    	
    	if(time >= 259200) {
    		setColor(player, "&c❤&r ");
    	} else if(time >= 216000) {
    		setColor(player, "█ ");
    	} else if(time >= 172800) {
    		setColor(player, "▆ ");
    	} else if(time >= 129600) {
    		setColor(player, "▅ ");
    	} else if(time >= 86400) {
    		setColor(player, "▄ ");
    	} else if(time >= 600) {
    		setColor(player, "▃ ");
    	} else if(time >= 300) {
    		setColor(player, "▂ ");
    	} else if(time >= 60) {
    		setColor(player, "▁ ");
    	}
    }
    
    static public void setColor(Player player, String color) {
    	player.setDisplayName(ChatColor.translateAlternateColorCodes('&', color + player.getName() + "&r"));
    	player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', player.getDisplayName()));
    }
}