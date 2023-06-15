package pl.freehc.fhcuboids.events;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import pl.freehc.fhcuboids.services.CuboidService;


public class DisablePvpEvent implements Listener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerDamage(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity damageTaker = e.getEntity();
        if (damageTaker instanceof Player) {
            Player damageTakerPlayer = ((Player) damageTaker).getPlayer();
            if(CuboidService.IsOnAnyCuboidArea(damageTakerPlayer.getLocation())){
                if(
                        damager instanceof Monster
                                || damager instanceof FallingBlock
                                || damager instanceof Animals
                ){
                    return;
                }
                e.setCancelled(true);
            }
        }
    }
}
