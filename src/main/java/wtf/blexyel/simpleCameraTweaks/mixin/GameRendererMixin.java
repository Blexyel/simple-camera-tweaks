package wtf.blexyel.simpleCameraTweaks.mixin;

import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wtf.blexyel.simpleCameraTweaks.util.Zoom;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
  @Shadow @Final private Minecraft minecraft;

  @Shadow @Final private Camera mainCamera;

  @SuppressWarnings("unchecked")
  @Inject(method = "render", at = @At("TAIL"), cancellable = true)
  private void onGetFov(DeltaTracker deltaTracker, boolean advanceGameTime, CallbackInfo ci) {
    handleNewVersion((CallbackInfoReturnable<Float>) ci);
  }

  // remove later

  /*
    @SuppressWarnings("unchecked")
    // Don't remove, required for <=1.21.1
    @Inject(method = "getFov", at = @At("TAIL"), cancellable = true)
    private void onGetFovOld(
        Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<?> cir) {
        handleOldVersion(camera, tickDelta, changingFov, (CallbackInfoReturnable<Double>) cir);
    }
  */
  @Unique
  private void handleNewVersion(CallbackInfoReturnable<Float> cir) {

    float baseFov = cir.getReturnValue();

    Zoom.updateZoomState();

    float targetFov =
        Zoom.isZoomin ? Mth.clamp(baseFov * Zoom.zoomedFovScale, 1.0F, 110.0F) : baseFov;

    Zoom.actualZoomLevel = Zoom.targetZoomLevel;

    cir.setReturnValue(targetFov);
  }
  /*
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
  */
}
