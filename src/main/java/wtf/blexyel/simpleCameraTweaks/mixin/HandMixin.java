package wtf.blexyel.simpleCameraTweaks.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.blexyel.simpleCameraTweaks.config.Config;

@Mixin(ItemInHandRenderer.class)
public abstract class HandMixin {

  @Shadow
  private void renderPlayerArm(
      PoseStack poseStack,
      SubmitNodeCollector submitNodeCollector,
      int lightCoords,
      float inverseArmHeight,
      float attackValue,
      HumanoidArm arm) {}

  @Inject(
      method = "submitArmWithItem",
      at =
          @At(
              value = "INVOKE",
              target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V",
              shift = At.Shift.AFTER))
  private void renderArmWithItem(
      AbstractClientPlayer player,
      float frameInterp,
      float xRot,
      InteractionHand hand,
      float attack,
      ItemStack itemStack,
      float inverseArmHeight,
      PoseStack poseStack,
      SubmitNodeCollector submitNodeCollector,
      int lightCoords,
      CallbackInfo ci) {
    boolean mainHand = hand == InteractionHand.MAIN_HAND;
    HumanoidArm offArm = mainHand ? player.getMainArm() : player.getMainArm().getOpposite();
    String heldItem = player.getMainHandItem().toString();
    // System.out.println("Held Item: " + heldItem.contains("pointblank:"));
    if (itemStack.isEmpty()
        && !heldItem.contains("pointblank:")
        && (Config.offhand)
        && (!mainHand && !player.isInvisible())) {
      this.renderPlayerArm(
          poseStack, submitNodeCollector, lightCoords, inverseArmHeight, attack, offArm);
    }
  }
}
