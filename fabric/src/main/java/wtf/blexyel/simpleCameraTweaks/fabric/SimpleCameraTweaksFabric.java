package wtf.blexyel.simpleCameraTweaks.fabric;

import wtf.blexyel.simpleCameraTweaks.SimpleCameraTweaks;
import net.fabricmc.api.ModInitializer;

public final class SimpleCameraTweaksFabric implements ModInitializer {

  @Override
  public void onInitialize() {
    // This code runs as soon as Minecraft is in a mod-load-ready state.
    // However, some things (like resources) may still be uninitialized.
    // Proceed with mild caution.

    // Run our common setup.
    SimpleCameraTweaks.init();
  }
}
