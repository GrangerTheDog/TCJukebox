package nl.grangerthedog.tcjukebox;

import com.bergerkiller.bukkit.tc.signactions.SignAction;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        SignAction.register(new SignActionTCJukebox());
        getLogger().info("TCJukebox 1.1-SNAPSHOT has started!");
    }

    @Override
    public void onDisable() {
        getLogger().info("TCJukebox 1.1-SNAPSHOT has stopped!");
    }
}
