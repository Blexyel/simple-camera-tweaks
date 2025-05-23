package wtf.blexyel.simple_camera_tweaks.mixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import wtf.blexyel.simple_camera_tweaks.Config;

@Mixin(HeldItemRenderer.class)
public abstract class HandMixin {
    @Shadow
    protected abstract void renderArmHoldingItem(MatrixStack pPoseStack, VertexConsumerProvider pBuffer, int pCombinedLight, float pEquippedProgress, float pSwingProgress, Arm pSide);

    @Inject(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;push()V", shift = At.Shift.AFTER))
    private void renderArmWithItem(AbstractClientPlayerEntity abstractClientPlayer, float f, float g, Hand interactionHand, float swingProgress, ItemStack itemStack, float equippedProgress, MatrixStack poseStack, VertexConsumerProvider multiBufferSource, int combinedLight, CallbackInfo ci) {
        boolean mainHand = interactionHand == Hand.MAIN_HAND;
        Arm offArm = mainHand ? abstractClientPlayer.getMainArm() : abstractClientPlayer.getMainArm().getOpposite();
        if (itemStack.isEmpty() && (Config.enabled) && (!mainHand && !abstractClientPlayer.isInvisible())) {
            this.renderArmHoldingItem(poseStack, multiBufferSource, combinedLight, equippedProgress, swingProgress, offArm);
        }
    }
}