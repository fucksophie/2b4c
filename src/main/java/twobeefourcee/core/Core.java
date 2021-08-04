
package twobeefourcee.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import twobeefourcee.core.commands.Update;

@SuppressWarnings({ "rawtypes" })
public class Core extends JavaPlugin {
	// DB db = DBMaker.fileDB("file.db").make();
	// ConcurrentMap map = db.hashMap("map").createOrOpen();

	@Override
	public void onEnable() {

		this.getCommand("update").setExecutor(new Update());
		System.out.println(" i am online ");
		if (Bukkit.getOnlinePlayers().size() != 0) {
			System.out.println(
					"I'm not really a fan of reloads, but because this is probably a development environment, I don't care.");
			// do some shit here idk
		}
	}

	@Override
	public void onDisable() {
		System.out.println(";( bye");
		// db.close();
	}
}
