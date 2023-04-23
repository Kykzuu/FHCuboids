package pl.freehc.fhcuboids.commands.cuboid;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.freehc.fhcuboids.CuboidHelper;
import pl.freehc.fhcuboids.CuboidModel;

public class SethomeSubcommand {
    public static boolean SethomeSubcommandMain(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        //Czy ma cuboid
        if (!CuboidHelper.IsPlayerHasCuboid(p)) {
            p.sendMessage(CuboidHelper.ColoredText("&6&lFree&b&lHC &cNie posiadasz cuboida!"));
            return true;
        }

        //Czy jest na swoim cuboidzie
        CuboidModel cuboid = CuboidHelper.GetCuboid(p.getLocation());
        if(cuboid == null || !CuboidHelper.HasPermissionToCuboid(cuboid, p)){
            p.sendMessage(CuboidHelper.ColoredText("&6&lFree&b&lHC &cMusisz być na terenie swojego cuboida!"));
            return true;
        }

        CuboidModel cuboidPlayer = CuboidHelper.GetCuboid(p);
        CuboidHelper.UpdateHomeLocation(cuboidPlayer, p.getLocation());
        p.sendMessage(CuboidHelper.ColoredText("&6&lFree&b&lHC &7Zmieniłeś home swojego cuboida"));
        return true;
    }
}
