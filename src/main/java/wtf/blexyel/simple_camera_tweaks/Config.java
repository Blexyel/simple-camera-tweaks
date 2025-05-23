package wtf.blexyel.simple_camera_tweaks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    public static boolean enabled = false; // default

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File("config/simple_camera_tweaks.json");

    // Call this at mod startup/load
    public static void load() {
        if (!CONFIG_FILE.exists()) {
            save(); // create default config file if missing
            return;
        }

        try (FileReader reader = new FileReader(CONFIG_FILE)) {
            ConfigData data = GSON.fromJson(reader, ConfigData.class);
            if (data != null) {
                enabled = data.enabled;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Call this whenever you want to save current config
    public static void save() {
        try {
            // Ensure config folder exists
            CONFIG_FILE.getParentFile().mkdirs();

            try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
                ConfigData data = new ConfigData();
                data.enabled = enabled;
                GSON.toJson(data, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper data class for Gson serialization
    private static class ConfigData {
        boolean enabled;
    }
}
