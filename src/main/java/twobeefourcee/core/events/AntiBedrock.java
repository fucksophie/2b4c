package twobeefourcee.core.events;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import twobeefourcee.core.Core;

public class AntiBedrock implements Listener {
	
    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {
    	Player player = event.getPlayer();
    	
    	if(!player.isOp()) {
	    	Material mat = event.getBlock().getType();
	    	
	    	if(mat.equals(Material.BEDROCK)) {
	    		event.setCancelled(true);
	    		player.sendMessage(Core.error + "Bedrock placing is patched.");
	    	}
    	}
    }
}