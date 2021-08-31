package twobeefourcee.core.events;


import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import twobeefourcee.core.Core;

public class FuckYourFlight implements Listener {
	
    @EventHandler
    public void moveEvent(PlayerMoveEvent event) {
    	Player player = event.getPlayer();
    	
    	LivingEntity le = (LivingEntity) player;
    	double distance = event.getFrom().distance(event.getTo());
    	
    	if(distance > 1.6 && !le.isOnGround()) {
    		sendActionBar(player, Core.error + "You are flying too quick. " + String.format("%.2f", distance) + "/1.6");
    		event.setCancelled(true);
    	} else {
    		sendActionBar(player, Core.success + String.format("%.2f", distance) + "/1.6" + le.isOnGround());
    	}
    }
        
    public void sendActionBar(Player player, String message) {
    	player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
    }
}