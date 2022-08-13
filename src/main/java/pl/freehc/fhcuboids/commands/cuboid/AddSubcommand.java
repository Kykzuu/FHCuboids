package pl.freehc.fhcuboids.commands.cuboid;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static pl.freehc.fhcuboids.CuboidHelper.*;

public class AddSubcommand {
    public static boolean AddSubcommandMain(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        //Czy gracz ma swój cuboid
        if (!IsPlayerHasCuboid(p)) {
            p.sendMessage(GetPrefixedText("Nie posiadasz cuboida!"));
            return true;
        }

        //Czy gracz wpisał nick osoby, która chce dodać
        if(args.length < 2){
            p.sendMessage(GetPrefixedText("Poprawne użycie: /cuboid add [gracz]"));
            return true;
        }

        Player friend = Bukkit.getServer().getPlayer(args[1].toString());

        //Czy gracz jest online
        if(friend == null){
            p.sendMessage(GetPrefixedText("Ten gracz nie jest online!"));
            return true;
        }

        //Czy gracz próbuje dodać sam siebie
        if(friend.getUniqueId().equals(p.getUniqueId())){
            p.sendMessage(GetPrefixedText("Nie możesz dodać samego siebie!"));
            return true;
        }

        //Czy gracz jest już dodany do tego cuboida
        if(GetCuboid(p).getFriendsUUID().stream().anyMatch(x -> x.equals(friend.getUniqueId()))){
            p.sendMessage(GetPrefixedText("Ten gracz jest już dodany do Twojego cuboida!"));
            return true;
        }

        //Dodaj do cuboida
        AddPlayerToCuboid(GetCuboid(p), friend);
        p.sendMessage(GetPrefixedText("Dodano gracza "+friend.getName()+" do Twojego cuboida"));
        friend.getPlayer().sendMessage(GetPrefixedText("Zostałeś dodany do cuboida gracza "+p.getName()));
        return true;

    }
}
