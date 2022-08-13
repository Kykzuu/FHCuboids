package pl.freehc.fhcuboids.events;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import pl.freehc.fhcuboids.CuboidHelper;
import pl.freehc.fhcuboids.CuboidModel;

public class ProtectArmorStandAndItemFrameOnCuboidEvent implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void entityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof ArmorStand
                || event.getEntity() instanceof ItemFrame
                || event.getEntity() instanceof GlowItemFrame){
            if(event.getDamager() instanceof Player){
                Player damager = (Player) event.getDamager();
                if(CuboidHelper.IsOnAnyCuboidArea(event.getEntity().getLocation())){
                    CuboidModel cuboid = CuboidHelper.GetCuboid(damager.getLocation());
                    if(CuboidHelper.HasPermissionToCuboid(cuboid, damager)) {
                        return;
                    }

                    event.setCancelled(true);
                }
            }

            if(event.getDamager() instanceof Projectile){
                if(((Projectile) event.getDamager()).getShooter() instanceof Player){
                    Player damager = (Player)((Projectile) event.getDamager()).getShooter();
                    if(CuboidHelper.IsOnAnyCuboidArea(event.getEntity().getLocation())){
                        CuboidModel cuboid = CuboidHelper.GetCuboid(damager.getLocation());
                        if(CuboidHelper.HasPermissionToCuboid(cuboid, damager)) {
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
            if (CuboidHelper.IsOnAnyCuboidArea(e.getRightClicked().getLocation())) {
                CuboidModel cuboid = CuboidHelper.GetCuboid(e.getPlayer().getLocation());
                if(CuboidHelper.HasPermissionToCuboid(cuboid, e.getPlayer())) {
                    return;
                }

                e.setCancelled(true);
            }
        }
    }

}
