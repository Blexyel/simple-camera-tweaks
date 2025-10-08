package wtf.blexyel.simple_camera_tweaks.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.blexyel.simple_camera_tweaks.Config;
import wtf.blexyel.simple_camera_tweaks.util.Keybindings;

@Mixin(HeldItemRenderer.class)
public abstract class HandMixin {
  @Shadow
  protected abstract void renderArmHoldingItem(
      MatrixStack pPoseStack,
      OrderedRenderCommandQueue pQueue,
      int pCombinedLight,
      float pEquippedProgress,
      float pSwingProgress,
      Arm pSide);

  @Inject(
      method = "renderFirstPersonItem",
      at =
          @At(
              value = "INVOKE",
              target = "Lnet/minecraft/client/util/math/MatrixStack;push()V",
              shift = At.Shift.AFTER))
  private void renderArmWithItem(
      AbstractClientPlayerEntity abstractClientPlayer,
      float f,
      float g,
      Hand interactionHand,
      float swingProgress,
      ItemStack itemStack,
      float equippedProgress,
      MatrixStack poseStack,
      OrderedRenderCommandQueue queue,
      int combinedLight,
      CallbackInfo ci) {
    if (Keybindings.offhandKey.wasPressed()) {
      Config.offhand = !Config.offhand;
      Config.save();
    }
    boolean mainHand = interactionHand == Hand.MAIN_HAND;
    Arm offArm =
        mainHand
            ? abstractClientPlayer.getMainArm()
            : abstractClientPlayer.getMainArm().getOpposite();
    String heldItem = abstractClientPlayer.getMainHandStack().toString();
    // System.out.println("Held Item: " + heldItem.contains("pointblank:"));
    if (itemStack.isEmpty()
        && !heldItem.contains("pointblank:")
        && (Config.offhand)
        && (!mainHand && !abstractClientPlayer.isInvisible())) {
      this.renderArmHoldingItem(
          poseStack, queue, combinedLight, equippedProgress, swingProgress, offArm);
    }
  }
}
