package wtf.blexyel.simpleCameraTweaks.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Camera;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.blexyel.simpleCameraTweaks.util.Freelook;
import wtf.blexyel.simpleCameraTweaks.util.FreelookUtils;

@Mixin(Camera.class)
public abstract class CameraMixin {
  @Shadow
  protected abstract void setRotation(float yaw, float pitch);

  @Unique private boolean startFreelook = true;

  @Inject(
      method = "setup",
      at =
      @At(
          value = "INVOKE",
          target = "Lnet/minecraft/client/Camera;move(FFF)V",
          shift = At.Shift.BEFORE))
  // @Inject(method = "update", at = @At("TAIL"))
  public void setup(
      net.minecraft.world.level.Level level,
      Entity focusedEntity,
      boolean thirdPerson,
      boolean inverseView,
      float tickDelta,
      CallbackInfo ci) {
    if (!(focusedEntity instanceof Player)) return;

    if (FreelookUtils.active) {
      Freelook fl = (Freelook) focusedEntity;

      if (Minecraft.getInstance().player != null && startFreelook) {
        fl.setCameraX(Minecraft.getInstance().player.getYRot());
        fl.setCameraY(Minecraft.getInstance().player.getXRot());
        startFreelook = false;
      }

      this.setRotation(fl.getCameraX(), fl.getCameraY());
    }
  }
}
