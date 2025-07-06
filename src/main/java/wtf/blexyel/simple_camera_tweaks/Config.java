package wtf.blexyel.simple_camera_tweaks;

import com.google.gson.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    public static boolean smooth = true;
    public static boolean offhand = false;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File("config/simple_camera_tweaks.json");

    public static void load() {
        if (!CONFIG_FILE.exists()) {
            save(); // create default config file if missing
            return;
        }

        try (FileReader reader = new FileReader(CONFIG_FILE)) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

            // Migrate "enabled" -> "offhand" if present
            if (json.has("enabled") && !json.has("offhand")) {
                offhand = json.get("enabled").getAsBoolean();
                Main.LOGGER.info("Migrated 'enabled' to 'offhand'");
            } else if (json.has("offhand")) {
                offhand = json.get("offhand").getAsBoolean();
            }

            if (json.has("smooth")) {
                smooth = json.get("smooth").getAsBoolean();
            }

        } catch (IOException | JsonParseException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            CONFIG_FILE.getParentFile().mkdirs();

            JsonObject json = new JsonObject();
            json.addProperty("smooth", smooth);
            json.addProperty("offhand", offhand);

            try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
                GSON.toJson(json, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
