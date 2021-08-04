
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

import twobeefourcee.core.commands.Update;

public class Core extends JavaPlugin {
HashMap<Location, Material> locations = new HashMap<Location, Material>();

	FileConfiguration config = this.getConfig();

	@Override
	public void onEnable() {
		
		this.saveDefaultConfig();
		
		Map<String, Object> worlds = this.getConfig().getConfigurationSection("worlds").getValues(false);

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
							this.locations.put(lllocation, mmaterial);
							System.out.println(lllocation);
							System.out.println(mmaterial);
						}
					});
				});
			}
		});

		this.getCommand("update").setExecutor(new Update());

		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				locations.forEach((loc, mt) -> {
					System.out.println(loc);
					loc.getWorld().dropItemNaturally(loc, new ItemStack(mt));
				});
				
			}
		}, 0L, 3);
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
		System.out.println(";( bye");
	}
}
