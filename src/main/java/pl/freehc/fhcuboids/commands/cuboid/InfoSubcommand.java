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

public class InfoSubcommand {
    public static boolean InfoSubcommandMain(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

            //Czy ma cuboid
            if (!CuboidHelper.IsPlayerHasCuboid(p)) {
                p.sendMessage(CuboidHelper.ColoredText("&6&lFree&b&lHC &cNie posiadasz cuboida!"));
                return true;
            }

            CuboidModel cuboid = CuboidHelper.GetCuboid(p);
            sender.sendMessage(CuboidHelper.ColoredText("&6&lFree&b&lHC &8- &f&lINFORMACJE"));
            sender.sendMessage(CuboidHelper.ColoredText("&7Lokalizacja: &rX: "+Math.round(cuboid.getjajX())+" Y: "+Math.round(cuboid.getjajZ()) + " Z: "+Math.round(cuboid.getjajZ())));
            sender.sendMessage(CuboidHelper.ColoredText("&7Wielkość:&f "+cuboid.getSize()*2 + "x"+cuboid.getSize()*2));
            String friends = "";
            if(cuboid.getFriendsUUID().size() > 0){
                List<UUID> friendUUIDs = cuboid.getFriendsUUID();
                for (UUID s : friendUUIDs)
                {
                    friends += Bukkit.getOfflinePlayer(s).getName() + ", ";
                }
            }else{
                friends = "brak";
            }
            sender.sendMessage(CuboidHelper.ColoredText("&7Dodani gracze:&f "+friends));
            return true;
    }
}
