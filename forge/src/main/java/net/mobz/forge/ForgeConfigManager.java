package net.mobz.forge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraftforge.fml.loading.FMLPaths;
import net.mobz.Configs;

// AutoConfig wrapper for Minecraft Forge
public class ForgeConfigManager {
	/**
	 * This is a read-only and simplified wrapper of ClothConfig.
	 * A restart is required after modifying the config JSON.
	 *
	 * We use this because:
	 *  1. The config file is compatible with the Neoforge and Fabric version.
	 *  2. Forge loads its managed config too late.
	 *
	 * Note:
	 *  1. Comments will not be added automatically.
	 *  2. Presence of comments wont trigger an error.
	 *  3. This is a self-managed config and may not compatible with the Mod Configured.
	 *  4. GUI is not available.
	 *
	 * @return the loaded config
	 */
	public static Configs loadFromFile() {
		Gson gson = new GsonBuilder().setLenient().setPrettyPrinting().create();
		File configFile = new File(FMLPaths.CONFIGDIR.get() + "/mobz.json5");
		Configs config = null;
		try (FileReader reader = new FileReader(configFile, StandardCharsets.UTF_8)) {
			config = gson.fromJson(reader, Configs.class);
		} catch (FileNotFoundException err) {
			// Set a default file
			config = new Configs();
			try {
				FileUtils.writeStringToFile(configFile, gson.toJson(config), StandardCharsets.UTF_8);
			} catch (IOException err2) {
				err2.printStackTrace();
			}
		} catch (IOException err) {
			err.printStackTrace();
		}

		return config;
	}
}