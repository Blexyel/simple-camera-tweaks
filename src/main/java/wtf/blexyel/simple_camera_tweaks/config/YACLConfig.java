package wtf.blexyel.simple_camera_tweaks.config;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import wtf.blexyel.simple_camera_tweaks.Config;

public class YACLConfig {
    public static Screen create(Screen parent) {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.literal("Simple Camera Tweaks Config"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("General"))
                        .option(Option.<Boolean>createBuilder()
                                .name(Text.literal("Always show offhand"))
                                .description(OptionDescription.of(Text.literal("Makes offhand visible at all times in first person")))
                                .binding(Config.offhand, () -> Config.offhand, newVal -> Config.offhand = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.<Boolean>createBuilder()
                                .name(Text.literal("Smooth zoom"))
                                .description(OptionDescription.of(Text.literal("Smoothly zooms in and out when using the camera")))
                                .binding(Config.smooth, () -> Config.smooth, newVal -> Config.smooth = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .build()
                )
                .save(Config::save)
                .build()
                .generateScreen(parent);
    }
}
