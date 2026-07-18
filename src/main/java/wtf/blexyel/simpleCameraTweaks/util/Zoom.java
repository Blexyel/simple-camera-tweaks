package wtf.blexyel.simpleCameraTweaks.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import wtf.blexyel.simpleCameraTweaks.SimpleCameraTweaks;

public class Zoom {
  private static boolean wasZooming = false;

  public static final float DEFAULT_FOV_SCALE = 1.0F;
  public static final float ZOOMED_FOV_SCALE_DEFAULT = 0.3F;

  public static float targetZoomLevel = DEFAULT_FOV_SCALE;
  public static float actualZoomLevel = DEFAULT_FOV_SCALE;

  public static float zoomedFovScale = 0.3F;

  public static float zoomSpeed = 0.1F;

  public static boolean isZoomin = false;

  public static void updateZoomState() {
    Minecraft client = Minecraft.getInstance();

    if (isZoomin) {
      if (!wasZooming) {
        wasZooming = true;
        client.options.smoothCamera = true;
      }
      targetZoomLevel = zoomedFovScale;
    } else {
      if (wasZooming) {
        client.options.smoothCamera = false;
        zoomedFovScale = ZOOMED_FOV_SCALE_DEFAULT;
        actualZoomLevel = Mth.lerp(zoomSpeed, actualZoomLevel, DEFAULT_FOV_SCALE);
        if (Math.abs(actualZoomLevel -1) < 0.01F) {
          actualZoomLevel = DEFAULT_FOV_SCALE;
          wasZooming = false;
        }
      }
      targetZoomLevel = DEFAULT_FOV_SCALE;

      //actualZoomLevel = client.options.fov().get();
    }

    actualZoomLevel = Mth.lerp(zoomSpeed, actualZoomLevel, targetZoomLevel);
  }
}
