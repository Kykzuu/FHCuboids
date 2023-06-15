package pl.freehc.fhcuboids.events;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import pl.freehc.fhcuboids.services.CuboidService;
import pl.freehc.fhcuboids.database.CuboidModel;

public class ProtectMobsOnCuboidEvent implements Listener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onMobDamage(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity damageTaker = e.getEntity();
        if (damageTaker instanceof Animals
        || damageTaker instanceof Villager) {
            if(CuboidService.IsOnAnyCuboidArea(damageTaker.getLocation())){
                if(damager instanceof Player damagerPlayer){
                    CuboidModel cuboid = CuboidService.GetCuboid(damagerPlayer.getLocation());
                    if(CuboidService.HasPermissionToCuboid(cuboid, damagerPlayer)) {
                        return;
                    }
                }
                e.setCancelled(true);
            }
        }
    }
}
