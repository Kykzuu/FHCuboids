package pl.freehc.fhcuboids.commands.cuboid;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.freehc.fhcuboids.App;
import pl.freehc.fhcuboids.CuboidModel;

import static pl.freehc.fhcuboids.CuboidHelper.*;

public class DelwarpSubcommand {
    public static boolean DelwarpSubcommandMain(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
            //Czy ma cuboid
            if (!IsPlayerHasCuboid(p)) {
                p.sendMessage(ColoredText("&6&lFree&b&lHC &cNie posiadasz cuboida!"));
                return true;
            }
            CuboidModel cuboid = GetCuboid(p);
            if(!cuboid.getisWarpPurchased()){
                p.sendMessage(ColoredText("&6&lFree&b&lHC  &cNie posiadasz warpa!"));
                return true;
            }
            //cuboid warp confirm
            //Usun cuboid jezeli potwierdzi
            if(args.length == 2){
                if(args[1].equalsIgnoreCase("confirm")){
                    UpdateIsWarpPurchased(cuboid, false);
                    p.sendMessage(ColoredText("&6&lFree&b&lHC  &cUsunąłeś swojego warpa"));
                    App.getEconomy().depositPlayer(p, 12000);
                    return true;
                }
            }

        p.sendMessage(ColoredText("&6&lFree&b&lHC  &8Zostanie Ci zwrócone 80% ceny warpa - &a12 000$"));
            p.sendMessage(ColoredText("&6&lFree&b&lHC &7Tej operacji nie można cofnąć! Potwierdź działanie za pomocą komendy &a/cuboid delwarp confirm"));
            return true;

    }
}