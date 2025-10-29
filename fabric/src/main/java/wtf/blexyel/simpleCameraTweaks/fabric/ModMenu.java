package wtf.blexyel.simpleCameraTweaks.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import wtf.blexyel.simpleCameraTweaks.config.YACLConfig;

public class ModMenu implements ModMenuApi {
  @Override
  public ConfigScreenFactory<?> getModConfigScreenFactory() {
    return YACLConfig::create;
  }
}