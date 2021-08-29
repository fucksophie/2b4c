package twobeefourcee.core.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import twobeefourcee.core.Core;

public class JoinMessages implements Listener {
	
    @EventHandler
    public void joinEvent(PlayerJoinEvent event) {
    	Player player = event.getPlayer();
    
    	event.setJoinMessage(Core.success + player.getName() + " joined.");
    }
    
    @EventHandler
    public void leaveEvent(PlayerQuitEvent event) {
    	Player player = event.getPlayer();
    	
    	event.setQuitMessage(Core.error + player.getName() + " left.");
    }
}