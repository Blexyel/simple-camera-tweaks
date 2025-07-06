package wtf.blexyel.simple_camera_tweaks.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;
import wtf.blexyel.simple_camera_tweaks.Config;
import wtf.blexyel.simple_camera_tweaks.Main;

public class Zoom {
    private static boolean wasZooming = false;

    public static final float DEFAULT_FOV_SCALE = 1.0F;
    public static final float ZOOMED_FOV_SCALE_DEFAULT = 0.2F;

    public static float targetZoomLevel = DEFAULT_FOV_SCALE;
    public static float actualZoomLevel = DEFAULT_FOV_SCALE;

    public static float zoomedFovScale = ZOOMED_FOV_SCALE_DEFAULT;
    
    public static boolean isZooming() {
        return Keybindings.zoomKey.isPressed();
    }

    public static void updateZoomState() {
        MinecraftClient client = MinecraftClient.getInstance();

        if (isZooming()) {
            if (!wasZooming) {
                wasZooming = true;
                client.options.smoothCameraEnabled = true;
            }
            targetZoomLevel = zoomedFovScale;
        } else {
            if (wasZooming) {
                wasZooming = false;
                client.options.smoothCameraEnabled = false;

                zoomedFovScale = ZOOMED_FOV_SCALE_DEFAULT;
            }

            targetZoomLevel = DEFAULT_FOV_SCALE;

            //Main.LOGGER.info("3 Target Zoom Level: " + Math.round(targetZoomLevel));
            //Main.LOGGER.info("4 Actual Zoom Level: " + Math.round(actualZoomLevel));

            // Replace with something better, that works properly with smooth zooming out. I hate this.
            // Been fighting this shit for like 5 hours, i am fucking done
            actualZoomLevel = client.options.getFov().getValue();
        }
    }

    public static void calculateZoom() {
        if (Config.smooth) {
            actualZoomLevel = MathHelper.lerp(0.25f, actualZoomLevel, targetZoomLevel);
        } else {
            actualZoomLevel = targetZoomLevel;
        }

        //Main.LOGGER.info("1 Target Zoom Level: " + Math.round(targetZoomLevel));
        //Main.LOGGER.info("2 Actual Zoom Level: " + Math.round(actualZoomLevel));
    }
}
