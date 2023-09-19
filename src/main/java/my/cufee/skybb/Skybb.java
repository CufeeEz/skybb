package my.cufee.skybb;

import my.cufee.skybb.CMD.Events;
import my.cufee.skybb.CMD.sbCMD;
import my.cufee.skybb.CMD.startgen;
import org.bukkit.plugin.java.JavaPlugin;

public final class Skybb extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("sbstart").setExecutor(new sbCMD());
        getCommand("startgen").setExecutor(new startgen(this));
        getCommand("startEvents").setExecutor(new Events());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
