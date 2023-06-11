package pl.freehc.fhcuboids.commands.cuboid;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.freehc.fhcuboids.CuboidHelper;
import pl.freehc.fhcuboids.CuboidModel;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class WarpsSubcommand {
    public static boolean WarpsSubcommandMain(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;


            List<CuboidModel> cuboids = CuboidHelper.GetAllCuboids().stream()
                    .filter(CuboidModel::isWarpPurchased).collect(Collectors.toList());
        String listString = "";
        for (CuboidModel s : cuboids)
        {
            listString += s.getOwnerNickname() + ", ";
        }

            sender.sendMessage(CuboidHelper.ColoredText("&6&lFree&b&lHC &8- &f&lDostępne warp cuboidy:"));
        sender.sendMessage(CuboidHelper.ColoredText("&7"+listString));

            return true;
    }
}
