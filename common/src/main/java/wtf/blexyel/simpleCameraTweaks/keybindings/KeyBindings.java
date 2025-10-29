/*package wtf.blexyel.simpleCameraTweaks.keybindings;

import com.mojang.blaze3d.platform.InputConstants;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.KeyMapping.Category;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
  public static Category CATEGORY = KeyMapping.Category.register(ResourceLocation.parse("simple_camera_tweaks.main"));

  public static final KeyMapping FREELOOK_KEY = new KeyMapping("key.simple_camera_tweaks.freelook",
      InputConstants.Type.MOUSE,
      4,
      CATEGORY);

  public static final KeyMapping ZOOM_KEY = new KeyMapping("key.simple_camera_tweaks.zoom",
      InputConstants.KEY_C,
      CATEGORY);

  public static final KeyMapping OFFHAND_KEY = new KeyMapping("key.simple_camera_tweaks.offhand",
      InputConstants.KEY_APOSTROPHE,
      CATEGORY);

  public static void init() {
    KeyMappingRegistry.register(FREELOOK_KEY);
    KeyMappingRegistry.register(ZOOM_KEY);
    KeyMappingRegistry.register(OFFHAND_KEY);
  }
}
*/