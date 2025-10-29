package wtf.blexyel.simpleCameraTweaks.keybindings;

import com.mojang.blaze3d.platform.InputConstants;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import java.lang.reflect.Constructor;
import net.minecraft.client.KeyMapping;

public class KeyBindingsCompat {
  public static KeyMapping FREELOOK_KEY;
  public static KeyMapping ZOOM_KEY;
  public static KeyMapping OFFHAND_KEY;
  public static String CATEGORY = "key.category.minecraft.simple_camera_tweaks.main";

  static {
    try {
      Constructor<KeyMapping> constructor = KeyMapping.class.getConstructor(String.class,
          InputConstants.Type.class, int.class, String.class);

      FREELOOK_KEY = constructor.newInstance("key.simple_camera_tweaks.freelook",
          InputConstants.Type.MOUSE,
          4,
          CATEGORY);

      ZOOM_KEY = constructor.newInstance("key.simple_camera_tweaks.zoom",
          InputConstants.Type.KEYSYM,
          InputConstants.KEY_C,
          CATEGORY);

      OFFHAND_KEY = constructor.newInstance("key.simple_camera_tweaks.offhand",
          InputConstants.Type.KEYSYM,
          InputConstants.KEY_APOSTROPHE,
          CATEGORY);

    } catch (Exception e) {
      // nothin
    }
  }
  public static void init() {
    try {
      KeyMappingRegistry.register(FREELOOK_KEY);
      KeyMappingRegistry.register(ZOOM_KEY);
      KeyMappingRegistry.register(OFFHAND_KEY);
    } catch (Exception e) {
      // nothin
    }
  }
}
