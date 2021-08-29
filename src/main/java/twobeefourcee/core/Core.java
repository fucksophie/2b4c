
package twobeefourcee.core;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

public class Core extends JavaPlugin {
	public static String success = ChatColor.translateAlternateColorCodes('&', "&8[&a2b4c&8]&7 ");
	public static String error = ChatColor.translateAlternateColorCodes('&', "&8[&c2b4c&8]&7 ");
	public static String info = ChatColor.translateAlternateColorCodes('&', "&8[&72b4c&8]&7 ");

    private Server mc = Bukkit.getServer();
    private Logger log = this.getLogger();
    private PluginManager pm = mc.getPluginManager();

    public static File homesFile = new File("plugins/2b4cCore/Homes.yml");
    public static YamlConfiguration homes = YamlConfiguration.loadConfiguration(homesFile);
    
	@Override
	public void onEnable() {
		Reflections commandReflections = new Reflections("twobeefourcee.core.commands");
		Reflections listenerReflections = new Reflections("twobeefourcee.core.events");
		
		 for (Class <? extends CommandExecutor> command : commandReflections.getSubTypesOf(CommandExecutor.class)) {
			 try {
				this.getCommand(command.getSimpleName().toLowerCase()).setExecutor(command.getDeclaredConstructor().newInstance());
				log.info("Registered command " + command.getSimpleName());
			 } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			};
		 }
		 
	        for (Class <? extends Listener> listener : listenerReflections.getSubTypesOf(Listener.class)) {
	        	try {
	        		pm.registerEvents(listener.getDeclaredConstructor().newInstance(), this);
	        		log.info("Registered event " + listener.getSimpleName());
	        	} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
	        		e.printStackTrace();
	        	}
	        }
		
		System.out.println("[2b4cCore] Enabled sucessfully!");
	}
	
	@Override
	public void onDisable() {
		System.out.println("[2b4cCore] Disabled.");
	}
}
