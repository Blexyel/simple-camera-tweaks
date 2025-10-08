package wtf.blexyel.simple_camera_tweaks;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
  public static final Logger LOGGER = LoggerFactory.getLogger("simple_camera_tweaks");

  @Override
  public void onInitialize() {
    Config.load();
  }
}
