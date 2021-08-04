package twobeefourcee.core.commands;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import twobeefourcee.core.UnzipUtility;

public class Update implements CommandExecutor {

	UnzipUtility unzipper = new UnzipUtility();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender.isOp()) {
			try {
				Bukkit.broadcastMessage("Started updating.");

				if (download("https://nightly.link/yourfriendoss/2b4c/workflows/build/main/2b4cCore.zip",
						"2b4cCoreUPDATE.zip") != 0) {
					Bukkit.broadcastMessage("Zip downloaded.");

					try {
						unzipper.unzip("2b4cCoreUPDATE.zip", "plugins/");
						File zip = new File("2b4cCoreUPDATE.zip");

						zip.delete();

						Bukkit.broadcastMessage("Zip unpacked.");

						System.gc();
						Bukkit.reload();

						Bukkit.broadcastMessage("I've attempted reloading the server. Update succeeded.");

					} catch (Exception ex) {
						Bukkit.broadcastMessage("Zip failed unpacking ");
						ex.printStackTrace();
					}

				} else {
					Bukkit.broadcastMessage("Download failed.");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return true;
	}

	static long download(String sourceUrl, String targetFileName) throws Exception {
		try (InputStream in = URI.create(sourceUrl).toURL().openStream()) {
			return Files.copy(in, Paths.get(targetFileName), StandardCopyOption.REPLACE_EXISTING);
		}
	}
}
