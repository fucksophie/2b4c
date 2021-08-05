
package twobeefourcee.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import twobeefourcee.core.commands.ReloadConfig;
import twobeefourcee.core.commands.Update;

public class Core extends JavaPlugin {
	static HashMap<Location, Material> locations = new HashMap<Location, Material>();

	FileConfiguration config = this.getConfig();
	
	public static String success = ChatColor.translateAlternateColorCodes('&', "&8[&a2b4c&8]&7 ");
	public static String error = ChatColor.translateAlternateColorCodes('&', "&8[&c2b4c&8]&7 ");
	public static String info = ChatColor.translateAlternateColorCodes('&', "&8[&72b4c&8]&7 ");
	
	@Override
	public void onEnable() {

		this.saveDefaultConfig();
	
		loadLocations();
		
		this.getCommand("update").setExecutor(new Update());
		this.getCommand("reloadconfig").setExecutor(new ReloadConfig());

		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				locations.forEach((loc, mt) -> {
					loc.getWorld().dropItemNaturally(loc, new ItemStack(mt));
				});
				
			}
		}, 0L, 4);
		
		System.out.println("[2b4cCore] Enabled sucessfully!");
	}
	
	public void loadLocations() {
		Core.locations = new HashMap<Location, Material>();
		Map<String, Object> worlds = getConfig().getConfigurationSection("worlds").getValues(false);

		worlds.forEach((world, section) -> {
			World wworld = Bukkit.getWorld(world);
			
			if(wworld == null) {
				System.out.println("Configuration faulty. World " + world + " doesn't exist.");
			} else {
				MemorySection ssection = (MemorySection) section;
				
				Map<String, Object> blocks = ssection.getValues(true);
				
				blocks.forEach((material, locations) -> {
					Material mmaterial = Material.matchMaterial(material);
					@SuppressWarnings("unchecked")
					ArrayList<String> locationss = (ArrayList<String>) locations;
					
					locationss.forEach(location -> {
						String[] llocation = ((String)location).split(",");
						
						Location lllocation = new Location(wworld,Integer.parseInt(llocation[0]), Integer.parseInt(llocation[1]), Integer.parseInt(llocation[2]));
						
						if(mmaterial == null) {
							System.out.println("Could not find " + material + " material!");
						} else {
							Core.locations.put(lllocation, mmaterial);
						}
					});
				});
			}
		});
	}
	
	public Material lookupMaterial(String s) {
        try {
            Package cbroot = getServer().getClass().getPackage();
            Class<?> CraftMagicNumbers = Class.forName(cbroot.getName()+".util.CraftMagicNumbers", false, getServer().getClass().getClassLoader());
            Method m = CraftMagicNumbers.getMethod("getMaterialFromInternalName", String.class);
            Material material = (Material) m.invoke(null, s);
            return material;
        } catch (NoSuchMethodException | SecurityException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            return null;
        }
    }
	
	
	@Override
	public void onDisable() {
		System.out.println("[2b4cCore] Disabled.");
	}
}
