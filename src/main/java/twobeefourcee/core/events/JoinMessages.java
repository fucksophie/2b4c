package twobeefourcee.core.events;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import twobeefourcee.core.Core;

public class JoinMessages implements Listener {
	
    @EventHandler
    static public void joinEvent(PlayerJoinEvent event) {
    	Player player = event.getPlayer();
    	
    	Boolean refreshed = event.getJoinMessage() == null;
    	
    	if(Core.database.getLong(player.getUniqueId().toString() + ".joinDate") == 0) {
    		Bukkit.broadcastMessage(Core.info + player.getName() + " joined for the first time.");
    		
    		player.sendMessage(Core.success + "Welcome to 2b4c, hacks are allowed and there are &lno rules!");
    		long unixTime = System.currentTimeMillis() / 1000L; // Using this as a long protects against Y38.
    		
    		Core.database.set(player.getUniqueId().toString() + ".joinDate", unixTime);
    		
			try {
				Core.database.save(Core.databaseFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {    
	    	event.setJoinMessage(Core.success + player.getName() + " joined.");
	    	player.sendMessage(Core.success + "Welcome back " + player.getName() + "!");
		}

    	if(refreshed) {
        	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Core.getPlugin(Core.class), new Runnable() {
        		public void run() {
            		Bukkit.broadcastMessage(event.getJoinMessage());
        		}
        	}, 20);
    	}
    }
    
    @EventHandler
    public void leaveEvent(PlayerQuitEvent event) {
    	event.setQuitMessage(Core.error + event.getPlayer().getName() + " left.");
    }
}