package pl.freehc.fhcuboids.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import pl.freehc.fhcuboids.CuboidHelper;

public class ProtectCuboidAgainstFireEvents implements Listener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void blockSpreadEvent(BlockSpreadEvent e) {
        if(e.getNewState().getType()== Material.FIRE){
            if(CuboidHelper.IsOnAnyCuboidArea(e.getSource().getLocation())){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void blockBurnEvent(BlockBurnEvent e) {
        if(CuboidHelper.IsOnAnyCuboidArea(e.getBlock().getLocation())){
            e.setCancelled(true);
        }

    }
}