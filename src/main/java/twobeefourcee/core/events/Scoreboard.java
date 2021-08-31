package twobeefourcee.core.events;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.minuskube.netherboard.Netherboard;
import fr.minuskube.netherboard.bukkit.BPlayerBoard;
import net.md_5.bungee.api.ChatColor;
import twobeefourcee.core.Core;

public class Scoreboard implements Listener {
	
    @EventHandler
    public void joinEvent(PlayerJoinEvent event) {
    	Player player = event.getPlayer();
    	BPlayerBoard board = Netherboard.instance().createBoard(player, Core.error);
    	 
    	int seconds = player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20;
    	
    	board.set(ChatColor.translateAlternateColorCodes('&', "&cYou have played for &l" + seconds/86400 + "d, " + seconds%86400/3600 + "h."), 2);
    	board.set(ChatColor.translateAlternateColorCodes('&', "&cThere are &l" + Bukkit.getOnlinePlayers().size() + " players online."), 3);
    	
    	board.set("", 1);
    	
    	board.set(ChatColor.translateAlternateColorCodes('&', "&cdiscord.gg/&4zXyQ7WsyJp"), 0);
    	
    	Bukkit.getOnlinePlayers().forEach(playerr -> {
    		if(player != playerr) {
    			BPlayerBoard bboard = Netherboard.instance().getBoard(playerr);
    	    	
    			bboard.set(ChatColor.translateAlternateColorCodes('&', "&cThere are &l" + Bukkit.getOnlinePlayers().size() + " players online."), 3);

    		};
    	});
    }
    
    @EventHandler
    public void leaveEvent(PlayerQuitEvent event) {
    	Player player = event.getPlayer();
    	
    	Bukkit.getOnlinePlayers().forEach(playerr -> {
    		if(player != playerr) {
    			BPlayerBoard board = Netherboard.instance().getBoard(playerr);
    	    	
    			board.set(ChatColor.translateAlternateColorCodes('&', "&cThere are &l" + Bukkit.getOnlinePlayers().size() + " players online."), 3);

    		};
    	});
    }
}