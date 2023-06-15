package pl.freehc.fhcuboids.commands.cuboid;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.freehc.fhcuboids.App;
import pl.freehc.fhcuboids.database.CuboidModel;

import static pl.freehc.fhcuboids.services.CuboidService.*;

public class DeleteSubcommand{
    public static boolean DeleteSubcommandMain(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
            //Czy ma cuboid
            if (!IsPlayerHasCuboid(p)) {
                p.sendMessage(ColoredText("&6&lFree&b&lHC &cNie posiadasz cuboida!"));
                return true;
            }

          double charge = GetCuboid(p).getPricePaid() * 0.8;

            //cuboid delete confirm
            //Usun cuboid jezeli potwierdzi
            if(args.length == 2){
                if(args[1].equalsIgnoreCase("confirm")){
                    CuboidModel cuboid = GetCuboid(p);
                    DeleteCuboid(cuboid);
                    p.sendMessage(ColoredText("&6&lFree&b&lHC  &cUsunąłeś swój cuboid"));
                    App.getEconomy().depositPlayer(p, charge);
                    return true;
                }
            }

        p.sendMessage(ColoredText("&6&lFree&b&lHC &7Zostanie Ci &azwrócone &780% ceny cuboida - &a"+ charge + "$"));
            p.sendMessage(ColoredText("&6&lFree&b&lHC &7Tej operacji nie można cofnąć! Potwierdź działanie za pomocą komendy &a/cuboid delete confirm"));
            return true;

    }
}