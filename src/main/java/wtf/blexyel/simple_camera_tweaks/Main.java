package wtf.blexyel.simple_camera_tweaks;

import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {

    @Override
    public void onInitialize() {
        Config.load();
    }
}
