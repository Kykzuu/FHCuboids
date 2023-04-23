package pl.freehc.fhcuboids.events;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import pl.freehc.fhcuboids.CuboidHelper;
import pl.freehc.fhcuboids.CuboidModel;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class BuildEvent implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void OnBuild(PlayerInteractEvent e) throws SQLException {

        if(e.getPlayer().hasPermission("fhcuboids.admin") || e.getPlayer().isOp()){
            return;
        }


        Location placedBlockLocation = e.getClickedBlock().getLocation();
        if (!placedBlockLocation.getWorld().getName().equalsIgnoreCase("world")){
            return;
        }
        List<CuboidModel> cuboids = CuboidHelper.GetAllCuboids();

        for (CuboidModel cuboid : cuboids) {
            if (placedBlockLocation.getX() >= cuboid.getMiX() && placedBlockLocation.getX() <= cuboid.getMaX() && placedBlockLocation.getZ() >= cuboid.getMiZ() && placedBlockLocation.getZ() <= cuboid.getMaZ()) {
                if (cuboid.getOwnerUUID().equals(e.getPlayer().getUniqueId())){
                    return;
                }
                if(cuboid.getFriendsUUID() != null){
                    for(UUID addedfrienduuid : cuboid.getFriendsUUID()){
                        if(addedfrienduuid.equals(e.getPlayer().getUniqueId())){
                            return;
                        }
                    }
                }
                e.setCancelled(true);
            }
        }
    }


    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().contains("§aCrafting cuboida"))
            return;
        if (event.getCurrentItem() == null)
            return;
        if (event.getCurrentItem().getItemMeta() == null)
            return;
        event.setCancelled(true);
    }
}

