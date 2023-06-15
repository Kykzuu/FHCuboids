package pl.freehc.fhcuboids.commands.cuboid;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.freehc.fhcuboids.services.CuboidService;
import pl.freehc.fhcuboids.database.CuboidModel;

public class SethomeSubcommand {
    public static boolean SethomeSubcommandMain(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        //Czy ma cuboid
        if (!CuboidService.IsPlayerHasCuboid(p)) {
            p.sendMessage(CuboidService.ColoredText("&6&lFree&b&lHC &cNie posiadasz cuboida!"));
            return true;
        }

        //Czy jest na swoim cuboidzie
        CuboidModel cuboid = CuboidService.GetCuboid(p.getLocation());
        if(cuboid == null || !CuboidService.HasPermissionToCuboid(cuboid, p)){
            p.sendMessage(CuboidService.ColoredText("&6&lFree&b&lHC &cMusisz być na terenie swojego cuboida!"));
            return true;
        }

        CuboidModel cuboidPlayer = CuboidService.GetCuboid(p);
        CuboidService.UpdateHomeLocation(cuboidPlayer, p.getLocation());
        p.sendMessage(CuboidService.ColoredText("&6&lFree&b&lHC &7Zmieniłeś home swojego cuboida"));
        return true;
    }
}
