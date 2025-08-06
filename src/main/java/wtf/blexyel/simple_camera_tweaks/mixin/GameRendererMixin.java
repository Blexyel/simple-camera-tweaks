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

// I fucking hate this, holy shit
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

    // I don't care about unchecked shit, do not remove cancellable
    // targets method getFov
    @SuppressWarnings({ "unchecked" })
    @Inject(method = "method_3196", at = @At("TAIL"), cancellable = true, remap = false)
    private void onGetFov(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<?> cir) {
        if (IS_NEW_VERSION) {
            // noinspection unchecked
            handleNewVersion(camera, tickDelta, changingFov, (CallbackInfoReturnable<Float>) cir);
        } else {
            // noinspection unchecked
            handleOldVersion(camera, tickDelta, changingFov, (CallbackInfoReturnable<Double>) cir);
        }
    }

    @Unique
    private void handleNewVersion(Camera camera, float tickDelta, boolean changingFov,
            CallbackInfoReturnable<Float> cir) {
        float baseFov = cir.getReturnValue();

        Zoom.updateZoomState();

        if (Zoom.isZooming()) {
            Zoom.targetZoomLevel = Zoom.zoomedFovScale; // this is already in slider space
        } else {
            Zoom.targetZoomLevel = baseFov; // convert baseFov (e.g., 85) to slider
        }

        Zoom.calculateZoom();

        cir.setReturnValue(Zoom.actualZoomLevel);
    }

    @Unique
    private void handleOldVersion(Camera camera, float tickDelta, boolean changingFov,
            CallbackInfoReturnable<Double> cir) {
        double baseFov = cir.getReturnValue();

        Zoom.updateZoomState();

        if (Zoom.isZooming()) {
            Zoom.targetZoomLevel = Zoom.zoomedFovScale;
        } else {
            Zoom.targetZoomLevel = (float) baseFov;
        }

        Zoom.calculateZoom();

        cir.setReturnValue((double) Zoom.actualZoomLevel);
    }

}