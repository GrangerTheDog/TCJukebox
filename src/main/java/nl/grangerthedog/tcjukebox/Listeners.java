package nl.grangerthedog.tcjukebox;

import com.bergerkiller.bukkit.tc.controller.MinecartMember;
import com.bergerkiller.bukkit.tc.controller.MinecartMemberStore;
import net.mcjukebox.plugin.bukkit.api.JukeboxAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;

public class Listeners implements Listener {

    @EventHandler
    public void onVehicleEnter(VehicleEnterEvent event) {
        MinecartMember<?> member = MinecartMemberStore.getFromEntity(event.getVehicle());
        if (member == null) {
            return;
        }

        if (event.getEntered() instanceof Player) {
            Player player = (Player) event.getEntered();
            String identifier = member.getGroup().getProperties().getTrainName();
            JukeboxAPI.getShowManager().getShow(identifier).addMember(player, false);
            return;

        }
    }

    @EventHandler
    public void onVehicleExit(VehicleExitEvent event) {
        MinecartMember<?> member = MinecartMemberStore.getFromEntity(event.getVehicle());
        if (member == null) {
            return;
        }

        if (event.getExited() instanceof Player) {
            Player player = (Player) event.getExited();
            String identifier = member.getGroup().getProperties().getTrainName();
            JukeboxAPI.getShowManager().getShow(identifier).removeMember(player);
            return;

        }


    }

}
