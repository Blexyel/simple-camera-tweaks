package wtf.blexyel.simpleCameraTweaks.config;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class YACLConfig {
  public static Screen create(Screen parent) {
    return YetAnotherConfigLib.createBuilder()
        .title(Component.literal("Simple Camera Tweaks Config"))
        .category(
            ConfigCategory.createBuilder()
                .name(Component.literal("General"))
                .option(
                    Option.<Boolean>createBuilder()
                        .name(Component.literal("Always show offhand"))
                        .description(
                            OptionDescription.of(
                                Component.literal(
                                    "Makes offhand visible at all times in first person")))
                        .binding(
                            Config.offhand, () -> Config.offhand, newVal -> Config.offhand = newVal)
                        .controller(TickBoxControllerBuilder::create)
                        .build())
                .option(
                    Option.<Boolean>createBuilder()
                        .name(Component.literal("Smooth zoom (now implemented)"))
                        .description(
                            OptionDescription.of(
                                Component.literal(
                                    "Smoothly zooms in and out (used to not be implemented, due to the lack of my math skills, which miraculously disappeared)")))
                        .binding(
                            Config.smooth, () -> Config.smooth, newVal -> Config.smooth = newVal)
                        .controller(TickBoxControllerBuilder::create)
                        .available(true)
                        .build())
                .build())
        .save(Config::save)
        .build()
        .generateScreen(parent);
  }
}
