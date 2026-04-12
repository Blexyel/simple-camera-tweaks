package wtf.blexyel.simpleCameraTweaks.keybindings;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import wtf.blexyel.simpleCameraTweaks.config.Config;
import wtf.blexyel.simpleCameraTweaks.util.FreelookUtils;
import wtf.blexyel.simpleCameraTweaks.util.Zoom;

public class KeybindHelper {
  public static void load() {
    ClientTickEvents.END_CLIENT_TICK.register(
        mc -> {
          FreelookUtils.active = KeyBindings.FREELOOK_KEY.isDown();
          if (KeyBindings.OFFHAND_KEY.isDown()) {
            Config.offhand = !Config.offhand;
            Config.save();
          }
          Zoom.isZoomin = KeyBindings.ZOOM_KEY.isDown();
        });
  }
}
