package wtf.blexyel.simple_camera_tweaks.mixin;

import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wtf.blexyel.simple_camera_tweaks.util.Zoom;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(method = "getFov", at = @At("TAIL"), cancellable = true)
    private void onGetFov(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Double> cir) {
        double baseFov = cir.getReturnValue();

        // Handle key state and smooth camera toggle
        Zoom.smoothCam();

        // Set the desired target zoom level based on key state
        if (Zoom.zoomin()) {
            Zoom.currentZoomLevel = baseFov * Zoom.zoomedFov;
        } else {
            Zoom.currentZoomLevel = baseFov;
        }

        // Smoothly interpolate toward target
        Zoom.calculateZoom();

        // Override FOV with interpolated zoom value
        cir.setReturnValue(Zoom.actualZoomLevel);
    }
}
