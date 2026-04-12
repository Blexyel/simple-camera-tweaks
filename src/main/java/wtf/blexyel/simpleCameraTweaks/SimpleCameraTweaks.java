package wtf.blexyel.simpleCameraTweaks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wtf.blexyel.simpleCameraTweaks.config.Config;
import wtf.blexyel.simpleCameraTweaks.keybindings.KeybindHelper;
import wtf.blexyel.simpleCameraTweaks.util.FreelookUtils;

public final class SimpleCameraTweaks implements ModInitializer {

  public static final String MOD_ID = "simple_camera_tweaks";

  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  public void onInitialize() {
    Config.load();
    KeybindHelper.load();

    ClientTickEvents.END_CLIENT_TICK.register(
        (client) -> {
          FreelookUtils.tick();
        });
  }
}
