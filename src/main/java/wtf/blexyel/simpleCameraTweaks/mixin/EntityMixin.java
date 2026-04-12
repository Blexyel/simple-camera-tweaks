package wtf.blexyel.simpleCameraTweaks.mixin;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.blexyel.simpleCameraTweaks.util.Freelook;
import wtf.blexyel.simpleCameraTweaks.util.FreelookUtils;

@Mixin(Entity.class)
public class EntityMixin implements Freelook {
  @Unique private float cameraY;
  @Unique private float cameraX;

  @Inject(method = "turn", at = @At("HEAD"), cancellable = true)
  public void changeLookDirection(double cursorDeltaX, double cursorDeltaY, CallbackInfo ci) {
    //noinspection ConstantConditions
    if (!((Object) this instanceof LocalPlayer)) return;
    if (FreelookUtils.active) {
      float g = (float) cursorDeltaX * 0.15F;
      float f = (float) cursorDeltaY * 0.15F;

      this.cameraX += g;
      this.cameraY = Mth.clamp(this.cameraY + f, -90F, 90F);
      ci.cancel();
    }
  }

  @Override
  public float getCameraX() {
    return this.cameraX;
  }

  @Override
  public float getCameraY() {
    return this.cameraY;
  }

  @Override
  public void setCameraX(float x) {
    this.cameraX = x;
  }

  @Override
  public void setCameraY(float y) {
    this.cameraY = y;
  }
}
