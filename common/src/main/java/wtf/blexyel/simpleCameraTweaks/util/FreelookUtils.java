package wtf.blexyel.simpleCameraTweaks.util;

import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;

public class FreelookUtils {
  private static boolean freeLooking = false;
  private static CameraType freeLookLastPerspective;

  public static boolean active = false;

  public static void tick() {
    if (active) {
      if (!freeLooking) {
        freeLookLastPerspective = Minecraft.getInstance().options.getCameraType();
        if (freeLookLastPerspective == CameraType.FIRST_PERSON)
          Minecraft.getInstance().options.setCameraType(CameraType.THIRD_PERSON_BACK);
        freeLooking = true;
      }
    } else {
      if (Minecraft.getInstance().player != null) {
        ((Freelook) (Minecraft.getInstance().player))
            .setCameraX(Minecraft.getInstance().player.getYRot());
        ((Freelook) (Minecraft.getInstance().player))
            .setCameraY(Minecraft.getInstance().player.getXRot());
      }

      if (freeLooking) {
        Minecraft.getInstance().options.setCameraType(freeLookLastPerspective);
        freeLooking = false;
      }
    }
  }
}