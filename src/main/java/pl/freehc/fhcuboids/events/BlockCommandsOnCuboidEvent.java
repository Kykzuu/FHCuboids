package pl.freehc.fhcuboids.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import pl.freehc.fhcuboids.CuboidHelper;
import pl.freehc.fhcuboids.CuboidModel;
import pl.freehc.fhcuboids.PluginConfiguration;
import pl.freehc.fhcuboids.PluginConfigurationModel;

import java.util.List;

public class BlockCommandsOnCuboidEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerRunCommand(PlayerCommandPreprocessEvent e) {
        Player player = e.getPlayer();
        if(CuboidHelper.IsOnAnyCuboidArea(player.getLocation())){
            CuboidModel cuboid = CuboidHelper.GetCuboid(player.getLocation());
            if(!CuboidHelper.HasPermissionToCuboid(cuboid, player)) {
                PluginConfigurationModel pluginConfigurationModel = PluginConfiguration.getPluginConfiguration();
                List<String> allowedCommands = pluginConfigurationModel.getAllowedCommandsOnCuboid();
                String command = e.getMessage();
                for (String allowedCommand : allowedCommands) {
                    if (command.startsWith(allowedCommand)) {
                        return;
                    }
                }
                player.sendMessage(CuboidHelper.ColoredText("&6&lFree&b&lHC &cNie możesz wykonać tego polecenia na czyimś cuboidzie!"));
                e.setCancelled(true);
            }
        }
    }
}
