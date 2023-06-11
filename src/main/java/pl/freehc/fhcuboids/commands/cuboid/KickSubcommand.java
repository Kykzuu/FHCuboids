package pl.freehc.fhcuboids.commands.cuboid;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.freehc.fhcuboids.CuboidHelper;
import pl.freehc.fhcuboids.CuboidModel;

import java.util.UUID;

import static pl.freehc.fhcuboids.CuboidHelper.*;

public class KickSubcommand {
    public static boolean KickSubcommandMain(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        //Czy gracz ma swój cuboid
        if (!IsPlayerHasCuboid(p)) {
            p.sendMessage(ColoredText("&6&lFree&b&lHC &7Poprawne użycie: &a/cuboid kick [gracz]"));
            return true;
        }

        //Czy gracz wpisał nick osoby, która chce dodać
        if (args.length < 2) {
            p.sendMessage(ColoredText("&6&lFree&b&lHC &7Poprawne użycie: &a/cuboid kick [gracz]"));
            return true;
        }

        //czy nie chce wyrzucic samego siebie
        if (args[1].equalsIgnoreCase(p.getName())) {
            p.sendMessage(ColoredText("&6&lFree&b&lHC &cNie możesz wyrzucić samego siebie!"));
            return true;
        }

        Player friend = Bukkit.getServer().getPlayer(args[1]);

        //Usun gracza jesli jest offline
        if(friend == null){
            for(UUID FriendCuboidUUID : CuboidHelper.GetCuboid(p).getFriendsUUID()){
                if(Bukkit.getOfflinePlayer(FriendCuboidUUID).hasPlayedBefore()){
                    if(Bukkit.getOfflinePlayer(FriendCuboidUUID).getName().equalsIgnoreCase(args[1])){
                        CuboidHelper.DeletePlayerFromCuboidByUUID(CuboidHelper.GetCuboid(p), FriendCuboidUUID);
                        p.sendMessage(ColoredText("&6&lFree&b&lHC &7Usunięto gracza &a"+ Bukkit.getOfflinePlayer(FriendCuboidUUID).getName() + " &7z Twojego cuboida"));
                        return true;
                    }
                }
            }
            p.sendMessage(ColoredText("&6&lFree&b&lHC &cTen gracz nie jest dodany do Twojego cuboida!"));
            return true;
        }

        //Usun gracza jezeli jest online
        for(CuboidModel cuboid : CuboidHelper.GetAllCuboids()){
            if(cuboid.getOwnerUUID().equals(p.getUniqueId())){
                DeletePlayerFromCuboid(CuboidHelper.GetCuboid(p), friend);
                p.sendMessage(ColoredText("&6&lFree&b&lHC &7Usunięto gracza &a" + friend.getName() + "&7 z Twojego cuboida"));
                friend.sendMessage(ColoredText("&6&lFree&b&lHC &7Zostałeś usunięty z cuboida gracza&a "+p.getName()));
                return true;
            }
        }

        //TODO: kickowanie

        p.sendMessage(ColoredText("&6&lFree&b&lHC &cTen gracz nie jest dodany do Twojego cuboida!"));
        return true;
    }
}
