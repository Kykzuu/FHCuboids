package pl.freehc.fhcuboids.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import pl.freehc.fhcuboids.services.CuboidService;
import pl.freehc.fhcuboids.database.CuboidModel;

import static pl.freehc.fhcuboids.services.CuboidService.ColoredText;

public class BlockTeleportsOnCuboidEvents implements Listener {

    @EventHandler
    public void OnPlayerTeleport(PlayerTeleportEvent e){
        if(e.getCause().toString().equals("CHORUS_FRUIT") || e.getCause().toString().equals("ENDER_PEARL")){
            Player player = e.getPlayer();
            if(CuboidService.IsOnAnyCuboidArea(e.getTo())){
                CuboidModel cuboid = CuboidService.GetCuboid(e.getTo());
                if(!CuboidService.HasPermissionToCuboid(cuboid, player)){
                    player.sendMessage(ColoredText("&6&lFree&b&lHC &cNie możesz teleportować się na czyimś cuboidzie!"));
                    e.setCancelled(true);
                }
            }
        }
    }
}