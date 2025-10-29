package wtf.blexyel.simpleCameraTweaks.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.InteractionHand;
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
      SubmitNodeCollector queue,
      int combinedLight,
      float equippedProgress,
      float swingProgress,
      HumanoidArm side
  ) {}

  @Inject(
      method = "renderArmWithItem",
      at =
      @At(
          value = "INVOKE",
          target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V",
          shift = At.Shift.AFTER))
  private void renderArmWithItem(
      AbstractClientPlayer abstractClientPlayer,
      float f,
      float g,
      InteractionHand interactionHand,
      float swingProgress,
      ItemStack itemStack,
      float equippedProgress,
      PoseStack poseStack,
      SubmitNodeCollector queue,
      int combinedLight,
      CallbackInfo ci) {
    boolean mainHand = interactionHand == InteractionHand.MAIN_HAND;
    HumanoidArm offArm =
        mainHand
            ? abstractClientPlayer.getMainArm()
            : abstractClientPlayer.getMainArm().getOpposite();
    String heldItem = abstractClientPlayer.getMainHandItem().toString();
    // System.out.println("Held Item: " + heldItem.contains("pointblank:"));
    if (itemStack.isEmpty()
        && !heldItem.contains("pointblank:")
        && (Config.offhand)
        && (!mainHand && !abstractClientPlayer.isInvisible())) {
      this.renderPlayerArm(
          poseStack, queue, combinedLight, equippedProgress, swingProgress, offArm);
    }
  }
}