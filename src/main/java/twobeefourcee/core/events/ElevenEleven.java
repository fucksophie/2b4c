package twobeefourcee.core.events;


import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import twobeefourcee.core.Core;

public class ElevenEleven implements Listener {
	private HashMap<Material, ItemStack> drops = new HashMap<Material, ItemStack>();
	
    @EventHandler
	public void onItemSpawn(PlayerDropItemEvent event) {
    	Material m = event.getItemDrop().getItemStack().getType();

	    System.out.println(m);
	    
	    if(drops.containsKey(m)) {
	    	ItemStack fart = event.getItemDrop().getItemStack();
	    	fart.setAmount(fart.getAmount() + event.getItemDrop().getItemStack().getAmount());
	    	drops.put(m, fart);
	    } else {
	    	drops.put(m, event.getItemDrop().getItemStack());
	    }

	    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Core.getPlugin(Core.class), new Runnable() {
	    	public void run() {
	    		System.out.println("removed");
	    		drops.remove(m);
	    	}
	    }, 30);
	}
	
    @EventHandler
    public void leaveEvent(PlayerQuitEvent event) {
    	Location location = event.getPlayer().getLocation();
  
    	drops.forEach((m, i) -> {
    		if(m != Material.AIR) {
    			location.getWorld().dropItemNaturally(location, i);
    		}
    	});
    }
}