package twobeefourcee.core.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import twobeefourcee.core.Core;

public class AnvilDupe implements Listener {
    /*
     Inspired by https://www.youtube.com/watch?v=AGnOFTtalCY, but
     not entirely like it.
     */
    
    @EventHandler
    public void anvil(InventoryClickEvent e){
        if(e.getSlot() == 2 && e.getInventory().getType().equals(InventoryType.ANVIL)) {
            ItemStack itemToDupe = e.getInventory().getItem(0);
            ItemStack dupeToItem = e.getInventory().getItem(2);
            
            if(dupeToItem.hasItemMeta()) {
                if(dupeToItem.getItemMeta().hasDisplayName()) {
                    if(dupeToItem.getItemMeta().getDisplayName().equalsIgnoreCase("dupe")) {
                        Block b = e.getWhoClicked().getTargetBlockExact(8);

                        if(b.getType().equals(Material.ANVIL)) {
                            
                            e.getInventory().setItem(1, itemToDupe);
                            
                            e.getInventory().setItem(2, new ItemStack(Material.AIR));
                            
                            e.setCancelled(true);
                        } else {
                            e.getWhoClicked().sendMessage(Core.error + "You have to look at your anvil to dupe.");
                        }
                    }
                }
            }
        }
    }
}