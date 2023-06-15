package pl.freehc.fhcuboids.commands.cuboid;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.freehc.fhcuboids.services.CuboidService;
import pl.freehc.fhcuboids.database.CuboidModel;

import java.util.List;
import java.util.UUID;

public class InfoSubcommand {
    public static boolean InfoSubcommandMain(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

            //Czy ma cuboid
            if (!CuboidService.IsPlayerHasCuboid(p)) {
                p.sendMessage(CuboidService.ColoredText("&6&lFree&b&lHC &cNie posiadasz cuboida!"));
                return true;
            }

            CuboidModel cuboid = CuboidService.GetCuboid(p);
            sender.sendMessage(CuboidService.ColoredText("&6&lFree&b&lHC &8- &f&lINFORMACJE"));
            sender.sendMessage(CuboidService.ColoredText("&7Nazwa: &f"+cuboid.getName()));
            sender.sendMessage(CuboidService.ColoredText("&7Lokalizacja: &rX: "+Math.round(cuboid.getJajX())+" Y: "+Math.round(cuboid.getJajZ()) + " Z: "+Math.round(cuboid.getJajZ())));
            sender.sendMessage(CuboidService.ColoredText("&7Wielkość:&f "+cuboid.getSize()*2 + "x"+cuboid.getSize()*2));
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
            sender.sendMessage(CuboidService.ColoredText("&7Dodani gracze:&f "+friends));
            return true;
    }
}
