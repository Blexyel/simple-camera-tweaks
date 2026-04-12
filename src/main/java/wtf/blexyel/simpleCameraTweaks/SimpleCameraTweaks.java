package wtf.blexyel.simpleCameraTweaks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wtf.blexyel.simpleCameraTweaks.config.Config;
import wtf.blexyel.simpleCameraTweaks.keybindings.KeyBindings;
import wtf.blexyel.simpleCameraTweaks.util.FreelookUtils;
import wtf.blexyel.simpleCameraTweaks.util.Zoom;

public final class SimpleCameraTweaks implements ModInitializer {

  public static final String MOD_ID = "simple_camera_tweaks";

  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  public void onInitialize() {
    Config.load();
    KeyBindings.init();

    ClientTickEvents.END_CLIENT_TICK.register(
        (client) -> {
          FreelookUtils.tick();

          FreelookUtils.active = KeyBindings.FREELOOK_KEY.isDown();
          if (KeyBindings.OFFHAND_KEY.consumeClick()) {
            Config.offhand = !Config.offhand;
            Config.save();
          }
          Zoom.isZoomin = KeyBindings.ZOOM_KEY.isDown();
        });
  }
}
