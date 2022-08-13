package pl.freehc.fhcuboids.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import pl.freehc.fhcuboids.CuboidHelper;
import pl.freehc.fhcuboids.CuboidModel;

public class BlockCommandsOnCuboidEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerRunCommand(PlayerCommandPreprocessEvent e) {
        Player player = e.getPlayer();
        if(e.getMessage().split(" ")[0].equalsIgnoreCase("/sethome")){
            if(CuboidHelper.IsOnAnyCuboidArea(player.getLocation())){
                CuboidModel cuboid = CuboidHelper.GetCuboid(player.getLocation());
                if(!CuboidHelper.HasPermissionToCuboid(cuboid, player)) {
                    player.sendMessage(CuboidHelper.GetPrefixedText("Nie możesz wykonać tego polecenia na czyimś cuboidzie! "));
                    e.setCancelled(true);
                }
            }
        }
    }
}
