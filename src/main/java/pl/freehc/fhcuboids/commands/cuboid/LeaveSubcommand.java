package pl.freehc.fhcuboids.commands.cuboid;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.freehc.fhcuboids.CuboidHelper;
import pl.freehc.fhcuboids.CuboidModel;

import static pl.freehc.fhcuboids.CuboidHelper.ColoredText;

public class LeaveSubcommand {
    public static boolean LeaveSubcommandMain(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        //Czy jest dodany do jakiegos cuboida
        if(CuboidHelper.GetCuboidsWherePlayerIsAdded(p).size() == 0){
            p.sendMessage(ColoredText("&6&lFree&b&lHC &cNie posiadasz cuboida!"));
            return true;
        }

        //Czy gracz wpisał nick osoby, której cuboid chce opuscic
        if (args.length < 2) {
            p.sendMessage(ColoredText("&6&lFree&b&lHC &7Poprawne użycie: &a/cuboid leave [gracz]"));
            return true;
        }

        //Usuń gracza z cuboida
        CuboidModel cuboid = CuboidHelper.GetCuboidsWherePlayerIsAdded(p).stream()
                .filter(x -> x.getOwnerNickname().equalsIgnoreCase(args[1])).findFirst().orElse(null);
        if(cuboid != null){
            CuboidHelper.DeletePlayerFromCuboid(cuboid, p);
            p.sendMessage(ColoredText("&6&lFree&b&lHC &7Opuściłeś cuboid gracza &a "+ Bukkit.getOfflinePlayer(cuboid.getOwnerUUID()).getName()));
            return true;
        }

        p.sendMessage(ColoredText("&6&lFree&b&lHC &cNie jesteś dodany do tego cuboida!"));
        return true;

    }
}