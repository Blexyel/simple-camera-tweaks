package wtf.blexyel.simple_camera_tweaks.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.blexyel.simple_camera_tweaks.accessor.OptionsScreenAccessor;

@Mixin(Screen.class)
public class ScreenMixin {
    @Inject(method = "render", at = @At("HEAD"))
    private void onRender(DrawContext context, int mouseX, int mouseY, float deltaTicks, CallbackInfo ci) {
        if ((Object) this instanceof OptionsScreen optionsScreen) {

            OptionsScreenAccessor accessor = (OptionsScreenAccessor) optionsScreen;
            ButtonWidget dhToggleButton = accessor.simpleCameraTweaks$getDhToggleButton();

            if (dhToggleButton != null) {
                int buttonWidth = 40;
                int marginX = (int)(optionsScreen.width * 0.03);
                int marginY = (int)(optionsScreen.height * 0.03);
                int x = optionsScreen.width - buttonWidth - marginX;

                dhToggleButton.setX(x);
                dhToggleButton.setY(marginY);
            }
        }
    }
}
