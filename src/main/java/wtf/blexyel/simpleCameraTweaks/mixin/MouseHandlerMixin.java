package wtf.blexyel.simpleCameraTweaks.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.blexyel.simpleCameraTweaks.util.Zoom;

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {
  @Unique Minecraft client = Minecraft.getInstance();

  @Inject(method = "onScroll", at = @At("HEAD"), cancellable = true)
  private void onScroll(long handle, double xoffset, double yoffset, CallbackInfo ci) {
    if (Zoom.isZoomin) {
      ci.cancel(); // Prevent default scroll behavior while zooming

      if (yoffset > 0) {
        Zoom.zoomedFovScale = Math.max(0.05F, Zoom.zoomedFovScale - 0.05F);
      } else {
        Zoom.zoomedFovScale = Math.min(1.0F, Zoom.zoomedFovScale + 0.05F);
      }

      Zoom.targetZoomLevel = client.options.fov().get() * Zoom.zoomedFovScale;
    }
  }
}
