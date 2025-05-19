package wtf.blexyel.simple_camera_tweaks.mixin;

import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.blexyel.simple_camera_tweaks.util.Zoom;

@Mixin(Mouse.class)
public class MouseMixin {
    @Inject(method = "onMouseScroll", at = @At("HEAD"), cancellable = true)
    private void onMouseScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
        if (Zoom.zoomin()) {
            ci.cancel(); // Prevent default scroll behavior while zooming

            // Adjust zoomed FOV modifier within safe bounds
            if (vertical > 0) {
                Zoom.zoomedFov = Math.max(0.05F, Zoom.zoomedFov - 0.05F);
            } else {
                Zoom.zoomedFov = Math.min(1.0F, Zoom.zoomedFov + 0.05F);
            }

            // Update target zoom level to reflect scroll
            Zoom.currentZoomLevel = Zoom.actualZoomLevel * Zoom.zoomedFov;
        }
    }
}
