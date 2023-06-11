package pl.freehc.fhcuboids.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.sql.SQLException;

public class PlayerCuboidRespawnEvent implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDeath(PlayerRespawnEvent e) throws SQLException {
        //get the player


    }
}
