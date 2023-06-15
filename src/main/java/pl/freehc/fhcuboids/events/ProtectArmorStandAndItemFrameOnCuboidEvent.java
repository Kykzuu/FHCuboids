package pl.freehc.fhcuboids.events;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import pl.freehc.fhcuboids.services.CuboidService;
import pl.freehc.fhcuboids.database.CuboidModel;

public class ProtectArmorStandAndItemFrameOnCuboidEvent implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void entityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof ArmorStand
                || event.getEntity() instanceof ItemFrame
                || event.getEntity() instanceof GlowItemFrame){
            if(event.getDamager() instanceof Player damager){
                if(CuboidService.IsOnAnyCuboidArea(event.getEntity().getLocation())){
                    CuboidModel cuboid = CuboidService.GetCuboid(damager.getLocation());
                    if(CuboidService.HasPermissionToCuboid(cuboid, damager)) {
                        return;
                    }

                    event.setCancelled(true);
                }
            }

            if(event.getDamager() instanceof Projectile){
                if(((Projectile) event.getDamager()).getShooter() instanceof Player damager){
                    if(CuboidService.IsOnAnyCuboidArea(event.getEntity().getLocation())){
                        CuboidModel cuboid = CuboidService.GetCuboid(damager.getLocation());
                        if(CuboidService.HasPermissionToCuboid(cuboid, damager)) {
                            return;
                        }

                        event.setCancelled(true);
                    }
                }
            }

        }
    }

    @EventHandler
    public void FrameRotate(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType().equals(EntityType.ITEM_FRAME)
            || e.getRightClicked().getType().equals(EntityType.GLOW_ITEM_FRAME)) {
            if (CuboidService.IsOnAnyCuboidArea(e.getRightClicked().getLocation())) {
                CuboidModel cuboid = CuboidService.GetCuboid(e.getPlayer().getLocation());
                if(CuboidService.HasPermissionToCuboid(cuboid, e.getPlayer())) {
                    return;
                }

                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void PlayerArmorStandManipulate(PlayerArmorStandManipulateEvent e) {
        Player player = e.getPlayer();
        if(CuboidService.IsOnAnyCuboidArea(e.getPlayer().getLocation())){
            CuboidModel cuboid = CuboidService.GetCuboid(player.getLocation());
            if(CuboidService.HasPermissionToCuboid(cuboid, player)) {
                return;
            }
            e.setCancelled(true);
        }
    }

}
