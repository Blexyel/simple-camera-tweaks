package wtf.blexyel.simple_camera_tweaks.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.Perspective;

public class FreelookUtils {
    private static boolean freeLooking = false;
    private static Perspective freeLookLastPerspective;

    public static boolean active() {
        return Keybindings.freelookKey.isPressed();
    }

    public static void tick() {
        if (FreelookUtils.active()) {
            if (!freeLooking) {
                freeLookLastPerspective = MinecraftClient.getInstance().options.getPerspective();
                if (freeLookLastPerspective == Perspective.FIRST_PERSON)
                    MinecraftClient.getInstance().options.setPerspective(Perspective.THIRD_PERSON_BACK);
                freeLooking = true;
            }
        } else {
            if (MinecraftClient.getInstance().player != null) {
                ((Freelook) (MinecraftClient.getInstance().player)).setCameraX(MinecraftClient.getInstance().player.getYaw());
                ((Freelook) (MinecraftClient.getInstance().player)).setCameraY(MinecraftClient.getInstance().player.getPitch());
            }

            if (freeLooking) {
                MinecraftClient.getInstance().options.setPerspective(freeLookLastPerspective);
                freeLooking = false;
            }
        }
    }
}