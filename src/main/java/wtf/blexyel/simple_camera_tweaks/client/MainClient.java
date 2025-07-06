package wtf.blexyel.simple_camera_tweaks.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import wtf.blexyel.simple_camera_tweaks.util.FreelookUtils;
import wtf.blexyel.simple_camera_tweaks.util.Keybindings;
import wtf.blexyel.simple_camera_tweaks.util.Zoom;

public class MainClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Keybindings.init();
        ClientTickEvents.START_CLIENT_TICK.register((client) -> {
            FreelookUtils.tick();
        });
    }
}
