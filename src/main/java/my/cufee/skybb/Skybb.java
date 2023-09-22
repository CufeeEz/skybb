package my.cufee.skybb;

import my.cufee.skybb.CMD.Events;
import org.bukkit.plugin.java.JavaPlugin;

public final class Skybb extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("startEvents").setExecutor(new Events(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
