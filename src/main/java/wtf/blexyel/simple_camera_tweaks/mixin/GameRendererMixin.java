package wtf.blexyel.simple_camera_tweaks.mixin;

import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.fabricmc.loader.api.FabricLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wtf.blexyel.simple_camera_tweaks.util.Zoom;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Unique
    private static final boolean IS_NEW_VERSION = FabricLoader.getInstance()
            .getModContainer("minecraft")
            .get()
            .getMetadata()
            .getVersion()
            .getFriendlyString()
            .compareTo("1.21.2") >= 0;

    @Inject(method = "getFov", at = @At("TAIL"))
    private void onGetFov(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<?> cir) {
        if (IS_NEW_VERSION) {
            //noinspection unchecked
            handleNewVersion(camera, tickDelta, changingFov, (CallbackInfoReturnable<Float>) cir);
        } else {
            //noinspection unchecked
            handleOldVersion(camera, tickDelta, changingFov, (CallbackInfoReturnable<Double>) cir);
        }
    }

    @Unique
    private void handleNewVersion(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Float> cir) {
        float baseFov = cir.getReturnValue();

        Zoom.smoothCam();

        if (Zoom.zoomin()) {
            Zoom.currentZoomLevel = (float) (baseFov * Zoom.zoomedFov);
        } else {
            Zoom.currentZoomLevel = baseFov;
        }

        Zoom.calculateZoom();
        cir.setReturnValue(Zoom.actualZoomLevel);
    }

    @Unique
    private void handleOldVersion(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Double> cir) {
        double baseFov = cir.getReturnValue();

        Zoom.smoothCam();

        if (Zoom.zoomin()) {
            Zoom.currentZoomLevel = (float) (baseFov * Zoom.zoomedFov);
        } else {
            Zoom.currentZoomLevel = (float) baseFov;
        }

        Zoom.calculateZoom();
        cir.setReturnValue((double) Zoom.actualZoomLevel);
    }
}