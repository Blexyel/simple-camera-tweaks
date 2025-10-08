package wtf.blexyel.simple_camera_tweaks.util;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class Keybindings {
  public static final KeyBinding.Category SCT_MAIN =
      KeyBinding.Category.create(Identifier.of("category.simple_camera_tweaks.main"));

  public static final KeyBinding zoomKey =
      new KeyBinding("key.simple_camera_tweaks.zoom", GLFW.GLFW_KEY_C, SCT_MAIN);

  public static final KeyBinding offhandKey =
      new KeyBinding("key.simple_camera_tweaks.offhand", GLFW.GLFW_KEY_APOSTROPHE, SCT_MAIN);

  public static final KeyBinding freelookKey =
      new KeyBinding(
          "key.simple_camera_tweaks.freelook",
          InputUtil.Type.MOUSE,
          GLFW.GLFW_MOUSE_BUTTON_5,
          SCT_MAIN);

  public static void init() {
    KeyBindingHelper.registerKeyBinding(zoomKey);
    KeyBindingHelper.registerKeyBinding(offhandKey);
    KeyBindingHelper.registerKeyBinding(freelookKey);
  }
}
