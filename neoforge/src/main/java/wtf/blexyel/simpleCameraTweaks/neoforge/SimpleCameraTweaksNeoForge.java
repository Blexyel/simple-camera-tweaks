package wtf.blexyel.simpleCameraTweaks.neoforge;

import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import wtf.blexyel.simpleCameraTweaks.SimpleCameraTweaks;
import net.neoforged.fml.common.Mod;
import wtf.blexyel.simpleCameraTweaks.config.YACLConfig;

@Mod(SimpleCameraTweaks.MOD_ID)
public final class SimpleCameraTweaksNeoForge {

  public SimpleCameraTweaksNeoForge() {
    ModLoadingContext.get()
        .registerExtensionPoint(
            IConfigScreenFactory.class, () -> (client, parent) -> YACLConfig.create(parent));
    // Run our common setup.
    SimpleCameraTweaks.init();
  }
}
