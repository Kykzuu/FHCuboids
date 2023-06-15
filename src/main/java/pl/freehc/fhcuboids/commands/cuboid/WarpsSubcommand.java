package pl.freehc.fhcuboids.commands.cuboid;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.freehc.fhcuboids.services.CuboidService;
import pl.freehc.fhcuboids.database.CuboidModel;

import java.util.List;
import java.util.stream.Collectors;

public class WarpsSubcommand {
    public static boolean WarpsSubcommandMain(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;


            List<CuboidModel> cuboids = CuboidService.GetAllCuboids().stream()
                    .filter(CuboidModel::isWarpPurchased).collect(Collectors.toList());
        String listString = "";
        for (CuboidModel s : cuboids)
        {
            listString += s.getOwnerNickname() + ", ";
        }

            sender.sendMessage(CuboidService.ColoredText("&6&lFree&b&lHC &8- &f&lDostępne warp cuboidy:"));
        sender.sendMessage(CuboidService.ColoredText("&7"+listString));

            return true;
    }
}
