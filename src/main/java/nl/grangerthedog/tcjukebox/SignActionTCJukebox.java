package nl.grangerthedog.tcjukebox;

import com.bergerkiller.bukkit.tc.events.SignActionEvent;
import com.bergerkiller.bukkit.tc.events.SignChangeActionEvent;
import com.bergerkiller.bukkit.tc.signactions.SignAction;
import com.bergerkiller.bukkit.tc.signactions.SignActionType;
import net.mcjukebox.plugin.bukkit.api.JukeboxAPI;
import net.mcjukebox.plugin.bukkit.api.ResourceType;
import net.mcjukebox.plugin.bukkit.api.models.Media;
import net.mcjukebox.plugin.bukkit.libs.json.JSONObject;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

public class SignActionTCJukebox extends SignAction {
    @Override
    public boolean match(SignActionEvent info) {
        return info.isType("tcjukebox");
    }


    @Override
    public void execute(SignActionEvent info) {
        if (!info.isAction(SignActionType.GROUP_ENTER)) {
            return;
        }
        Bukkit.broadcastMessage("Sing activated: " + info.getLine(2));
        if (info.getLine(2).equalsIgnoreCase("stop")) {
            String identifier = info.getGroup().getProperties().getTrainName();
            JukeboxAPI.getShowManager().getShow(identifier).stopMusic();
            return;
        }
        String identifier = info.getGroup().getProperties().getTrainName();
        String music = info.getLine(2) + info.getLine(3);
        Media m = new Media(ResourceType.MUSIC, music, new JSONObject("{\"looping\":false}"));
        JukeboxAPI.getShowManager().getShow(identifier).play(m);
        return;

    }


    @Override
    public boolean build(SignChangeActionEvent event) {
        if (event.isCartSign()) {
            return false;
        } else if (event.isTrainSign()) {
            if (event.getPlayer().hasPermission("tcjukebox.build")) {
                if(event.getLine(2).equalsIgnoreCase("stop")) {
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aTCJukebox&7: Jukebox audio stop sign has been succesfully placed!"));

                    return true;
                }
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aTCJukebox&7: Jukebox sing with the URL: &9" + event.getLine(2) + event.getLine(3) + "&7 has been successfully placed!"));
                return true;
            }
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aTCJukebox&7: You don't have the permission to build a Jukebox sign!"));
            return false;
        } else if (event.isRCSign()) {
            return false;
        }
        return false;
    }


    @Override
    public boolean canSupportRC() {
        return false;
    }

}