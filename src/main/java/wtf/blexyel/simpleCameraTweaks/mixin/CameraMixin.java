package wtf.blexyel.simpleCameraTweaks.mixin;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wtf.blexyel.simpleCameraTweaks.SimpleCameraTweaks;
import wtf.blexyel.simpleCameraTweaks.util.Freelook;
import wtf.blexyel.simpleCameraTweaks.util.FreelookUtils;
import wtf.blexyel.simpleCameraTweaks.util.Zoom;

@Mixin(Camera.class)
public abstract class CameraMixin {
  @Shadow
  protected abstract void setRotation(float yaw, float pitch);

  @Shadow private Entity entity;
  @Final @Shadow private Minecraft minecraft;

  @Unique private boolean startFreelook = true;
  @Unique private float lastFov;

  @Inject(method = "calculateFov", at = @At("TAIL"), cancellable = true)
  private void calculateFov(float partialTicks, CallbackInfoReturnable<Float> cir) {

    float baseFov = cir.getReturnValue();

    Zoom.updateZoomState();

    //float targetFov = Zoom.isZoomin ? Mth.clamp(baseFov * Zoom.zoomedFovScale, 1.0F, 110.0F) : baseFov;
    float targetFov = Mth.clamp(baseFov * Zoom.actualZoomLevel, 1.0F, 110.0F);

    //Zoom.actualZoomLevel = Zoom.targetZoomLevel;

    cir.setReturnValue(targetFov);
  }

  @Inject(method = "move", at = @At("HEAD"))
  // @Inject(method = "update", at = @At("TAIL"))
  public void setup(float forwards, float up, float right, CallbackInfo ci) {
    if (!(entity instanceof Player)) return;

    if (FreelookUtils.active) {
      Freelook fl = (Freelook) entity;

      if (Minecraft.getInstance().player != null && startFreelook) {
        fl.setCameraX(Minecraft.getInstance().player.getYRot());
        fl.setCameraY(Minecraft.getInstance().player.getXRot());
        startFreelook = false;
      }

      this.setRotation(fl.getCameraX(), fl.getCameraY());
    }
  }
}
