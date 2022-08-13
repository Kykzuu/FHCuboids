package pl.freehc.fhcuboids.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import pl.freehc.fhcuboids.CuboidHelper;
import pl.freehc.fhcuboids.CuboidModel;

import java.sql.SQLException;

public class BlockLiquidsOnCuboidEvent implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void OnPlaceLiquid(PlayerBucketEmptyEvent e) throws SQLException {
        Player player = e.getPlayer();
        if(CuboidHelper.IsOnAnyCuboidArea(player.getLocation())){
            CuboidModel cuboid = CuboidHelper.GetCuboid(player.getLocation());
            if(!CuboidHelper.HasPermissionToCuboid(cuboid, player)) {
                e.setCancelled(true);
            }
        }

    }
}
