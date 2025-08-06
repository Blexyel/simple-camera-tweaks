package wtf.blexyel.simple_camera_tweaks.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;
import wtf.blexyel.simple_camera_tweaks.Config;
import wtf.blexyel.simple_camera_tweaks.Main;

public class Zoom {
    static MinecraftClient client = MinecraftClient.getInstance();

    private static boolean wasZooming = false;

    public static float configFovScale = client.options.getFov().getValue();
    public static final float DEFAULT_FOV_SCALE = toSliderValue(configFovScale);
    public static final float ZOOMED_FOV_SCALE_DEFAULT = toSliderValue(30.0F); // Default zoomed FOV scale

    public static float targetZoomLevel = DEFAULT_FOV_SCALE;
    public static float actualZoomLevel = DEFAULT_FOV_SCALE;

    public static float zoomedFovScale = ZOOMED_FOV_SCALE_DEFAULT;

    public static boolean isZooming() {
        return Keybindings.zoomKey.isPressed();
    }

    public static float exponentialApproach(float current, float target, float smoothing) {
        return current + (target - current) * (1.0f - (float) Math.exp(-smoothing));
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
                targetZoomLevel = DEFAULT_FOV_SCALE;
            }

            Main.LOGGER.info("3 Target Zoom Level: " + targetZoomLevel);
            Main.LOGGER.info("4 Actual Zoom Level: " + actualZoomLevel);

            // Replace with something better, that works properly with smooth zooming out. I
            // hate this.
            // Been fighting this shit for like 5 hours, i am fucking done
            // actualZoomLevel = exponentialApproach(actualZoomLevel, configFovScale, 1.0f);
        }
    }
    // i fucking give up
    public static void calculateZoom() {
        if (Config.smooth) {
            Main.LOGGER.info("initial idfk??" + actualZoomLevel + targetZoomLevel + configFovScale);
            actualZoomLevel = MathHelper.lerp(0.2F, actualZoomLevel, targetZoomLevel);
            //actualZoomLevel = exponentialApproach(actualZoomLevel, targetZoomLevel, 1.0f);
        } else {
            actualZoomLevel = targetZoomLevel;
        }

        Main.LOGGER.info("1 Target Zoom Level: " + targetZoomLevel);
        Main.LOGGER.info("2 Actual Zoom Level: " + actualZoomLevel);
    }

    public static final float MIN_FOV = 30.0F;
    public static final float MAX_FOV = 110.0F;

    public static float toSliderValue(float fovDegrees) {
        return MathHelper.clamp((fovDegrees - MIN_FOV) / (MAX_FOV - MIN_FOV), 0.0F, 1.0F);
    }

    public static float toFovDegrees(float sliderValue) {
        return MathHelper.clamp(MIN_FOV + (MAX_FOV - MIN_FOV) * sliderValue, MIN_FOV, MAX_FOV);
    }
}
