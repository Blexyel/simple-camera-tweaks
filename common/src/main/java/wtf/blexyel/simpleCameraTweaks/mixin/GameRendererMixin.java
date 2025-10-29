package wtf.blexyel.simpleCameraTweaks.mixin;

import dev.architectury.platform.Platform;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wtf.blexyel.simpleCameraTweaks.util.Zoom;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
  @SuppressWarnings("unchecked")
  @Inject(method = "getFov", at = @At("TAIL"), cancellable = true)
  private void onGetFov(
      Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<?> cir) {
      if (!Platform.getMinecraftVersion().matches("^(1\\.21\\.1|1\\.21)$")) {
        handleNewVersion(camera, tickDelta, changingFov, (CallbackInfoReturnable<Float>) cir);
      } else {
        handleOldVersion(camera, tickDelta, changingFov, (CallbackInfoReturnable<Double>) cir);
      }
  }

  // remove later

  @Unique
  private void handleNewVersion(
      Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Float> cir) {

    float baseFov = cir.getReturnValue();

    Zoom.updateZoomState();

    float targetFov = Zoom.isZoomin
        ? Mth.clamp(baseFov * Zoom.zoomedFovScale, 1.0F, 110.0F)
        : baseFov;

    Zoom.actualZoomLevel = Zoom.targetZoomLevel;

    cir.setReturnValue(targetFov);
  }

  @Unique
  private void handleOldVersion(
      Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Double> cir) {

    double baseFov = cir.getReturnValue();

    Zoom.updateZoomState();

    float targetFov = (float) (Zoom.isZoomin
            ? Mth.clamp(baseFov * Zoom.zoomedFovScale, 1.0F, 110.0F)
            : baseFov);

    Zoom.actualZoomLevel = Zoom.targetZoomLevel;

    cir.setReturnValue((double) targetFov);
  }
}