package wtf.blexyel.simpleCameraTweaks.keybindings;

import dev.architectury.event.events.client.ClientTickEvent;
import wtf.blexyel.simpleCameraTweaks.SimpleCameraTweaks;
import wtf.blexyel.simpleCameraTweaks.config.Config;
import wtf.blexyel.simpleCameraTweaks.util.FreelookUtils;
import wtf.blexyel.simpleCameraTweaks.util.Zoom;

public class KeybindHelper {
  static boolean keybindingsLoaded = false;

  public static void load() {
    ClientTickEvent.CLIENT_POST.register(
        mc -> {
          FreelookUtils.active = KeyBindingsCompat.FREELOOK_KEY.isDown();
          if (KeyBindingsCompat.OFFHAND_KEY.isDown()) {
            Config.offhand = !Config.offhand;
            Config.save();
          }
          Zoom.isZoomin = KeyBindingsCompat.ZOOM_KEY.isDown();
        });
    /*
    try {
      // Try new Keybindings
      Class<?> keybindingsClass = Class.forName("wtf.blexyel.simpleCameraTweaks.keybindings.KeyBindings");
      keybindingsClass.getMethod("init").invoke(null);

      ClientTickEvent.CLIENT_POST.register(
          mc -> {
            FreelookUtils.active = KeyBindings.FREELOOK_KEY.isDown();
            if (KeyBindings.OFFHAND_KEY.isDown()) {
              Config.offhand = !Config.offhand;
              Config.save();
            }
            Zoom.isZoomin = KeyBindings.ZOOM_KEY.isDown();
          });

      keybindingsLoaded = true; // success

    } catch (Throwable t) {
      // only log if we actually need the fallback
      SimpleCameraTweaks.LOGGER.info("New Keybindings failed to load, trying fallback...");
    }

    // only attempt fallback if first attempt failed
    if (!keybindingsLoaded) {
      try {
        Class<?> compatClass = Class.forName("wtf.blexyel.simpleCameraTweaks.keybindings.KeyBindingsCompat");
        compatClass.getMethod("init").invoke(null);

        // Object enabledKeyCompat = compatClass.getField("ENABLED_KEY").get(null);
        // var consumeClickCompat = enabledKeyCompat.getClass().getMethod("consumeClick");

        ClientTickEvent.CLIENT_POST.register(
            mc -> {
              FreelookUtils.active = KeyBindingsCompat.FREELOOK_KEY.isDown();
              if (KeyBindingsCompat.OFFHAND_KEY.isDown()) {
                Config.offhand = !Config.offhand;
                Config.save();
              }
              Zoom.isZoomin = KeyBindingsCompat.ZOOM_KEY.isDown();
            });

        SimpleCameraTweaks.LOGGER.info("Fallback KeybindingsCompat loaded successfully.");

      } catch (Throwable fallbackError) {
        SimpleCameraTweaks.LOGGER.error("No compatible keybindings class could be loaded!", fallbackError);
      }
    }
    */
  }
}
