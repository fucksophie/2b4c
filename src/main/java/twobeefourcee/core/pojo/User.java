package twobeefourcee.core.pojo;

import java.util.HashMap;

import org.bukkit.Location;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

@Document(collection = "users", schemaVersion = "1.0")
public class User {
	@Id
	private String uuid;

	public String getUuid() { return uuid; }
	public void setUuid(String uuid) { this.uuid = uuid; }
	
	private HashMap<String, Location> homes;
	
	public HashMap<String, Location> getHomes() { return homes; }
	public void setHomes(HashMap<String, Location> homes) { this.homes = homes; }
}
