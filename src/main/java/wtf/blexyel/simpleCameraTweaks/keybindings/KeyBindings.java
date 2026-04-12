package wtf.blexyel.simpleCameraTweaks.keybindings;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.KeyMapping.Category;
import net.minecraft.resources.Identifier;

public class KeyBindings {
  public static Category CATEGORY =
      KeyMapping.Category.register(Identifier.parse("simple_camera_tweaks.main"));

  public static final KeyMapping FREELOOK_KEY =
      new KeyMapping("key.simple_camera_tweaks.freelook", InputConstants.Type.MOUSE, 4, CATEGORY);

  public static final KeyMapping ZOOM_KEY =
      new KeyMapping("key.simple_camera_tweaks.zoom", InputConstants.KEY_C, CATEGORY);

  public static final KeyMapping OFFHAND_KEY =
      new KeyMapping("key.simple_camera_tweaks.offhand", InputConstants.KEY_APOSTROPHE, CATEGORY);

  public static void init() {
    KeyMappingHelper.registerKeyMapping(FREELOOK_KEY);
    KeyMappingHelper.registerKeyMapping(ZOOM_KEY);
    KeyMappingHelper.registerKeyMapping(OFFHAND_KEY);
  }
}
