package wtf.blexyel.simple_camera_tweaks.mixin;

import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.blexyel.simple_camera_tweaks.Config;
import wtf.blexyel.simple_camera_tweaks.accessor.OptionsScreenAccessor;

@Mixin(OptionsScreen.class)
public class OptionsScreenMixin implements OptionsScreenAccessor {
    @Unique
    private ButtonWidget dhToggleButton;

    @Inject(method = "init", at = @At("TAIL"))
    private void onInit(CallbackInfo ci) {
        OptionsScreen screen = (OptionsScreen)(Object)this;

        int buttonWidth = 40;
        int buttonHeight = 20;
        int marginX = (int)(screen.width * 0.03);
        int marginY = (int)(screen.height * 0.03);
        int x = screen.width - buttonWidth - marginX;
        int y = marginY;

        dhToggleButton = ButtonWidget.builder(
                Text.literal("DH: " + (Config.offhand ? "ON" : "OFF")),
                button -> {
                    Config.offhand = !Config.offhand;
                    button.setMessage(Text.literal("DH: " + (Config.offhand ? "ON" : "OFF")));
                    Config.save();
                }
        ).dimensions(x, y, buttonWidth, buttonHeight).build();

        ((ScreenAccessor) screen).invokeAddDrawableChild(dhToggleButton);
    }

    @Override
    public ButtonWidget simpleCameraTweaks$getDhToggleButton() {
        return dhToggleButton;
    }
}