package pl.freehc.fhcuboids.commands.cuboid;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
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
            p.sendMessage(GetPrefixedText("Nie posiadasz cuboida!"));
            return true;
        }

        //Czy gracz wpisał nick osoby, która chce dodać
        if (args.length < 2) {
            p.sendMessage(GetPrefixedText("Poprawne użycie: /cuboid kick [gracz]"));
            return true;
        }

        Player friend = Bukkit.getServer().getPlayer(args[1]);

        //Usun gracza jesli jest offline
        if(friend == null){
            for(UUID FriendCuboidUUID : CuboidHelper.GetCuboid(p).getFriendsUUID()){
                if(Bukkit.getOfflinePlayer(FriendCuboidUUID).hasPlayedBefore()){
                    if(Bukkit.getOfflinePlayer(FriendCuboidUUID).getName().equalsIgnoreCase(args[1])){
                        CuboidHelper.DeletePlayerFromCuboidByUUID(CuboidHelper.GetCuboid(p), FriendCuboidUUID);
                        p.sendMessage(GetPrefixedText("Usunięto gracza "+ Bukkit.getOfflinePlayer(FriendCuboidUUID).getName() + " z Twojego cuboida"));
                        return true;
                    }
                }
            }
            p.sendMessage(GetPrefixedText("Ten gracz nie jest dodany do Twojego cuboida"));
            return true;
        }

        //Usun gracza jezeli jest online
        for(CuboidModel cuboid : CuboidHelper.GetAllCuboids()){
            if(cuboid.getOwnerUUID().equals(p.getUniqueId())){
                DeletePlayerFromCuboid(CuboidHelper.GetCuboid(p), friend);
                p.sendMessage(GetPrefixedText("Usunięto gracza " + friend.getName() + " z Twojego cuboida"));
                friend.sendMessage(GetPrefixedText("Zostałeś usunięty z cuboida gracza "+p.getName()));
                return true;
            }
        }

        //TODO: kickowanie

        p.sendMessage(GetPrefixedText("Ten gracz nie jest dodany do Twojego cuboida"));
        return true;
    }
}
