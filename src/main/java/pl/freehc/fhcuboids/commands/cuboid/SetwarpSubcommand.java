package pl.freehc.fhcuboids.commands.cuboid;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.freehc.fhcuboids.App;
import pl.freehc.fhcuboids.CuboidHelper;
import pl.freehc.fhcuboids.CuboidModel;

import static pl.freehc.fhcuboids.CuboidHelper.ColoredText;

public class SetwarpSubcommand {
    public static boolean SetwarpSubcommandMain(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        //Czy ma cuboid
        if (!CuboidHelper.IsPlayerHasCuboid(p)) {
            p.sendMessage(CuboidHelper.ColoredText("&6&lFree&b&lHC &cNie posiadasz cuboida!"));
            return true;
        }

        //Czy jest na swoim cuboidzie
        CuboidModel cuboid = CuboidHelper.GetCuboid(p.getLocation());
        if(cuboid == null || !CuboidHelper.HasPermissionToCuboid(cuboid, p)){
            p.sendMessage(CuboidHelper.ColoredText("&6&lFree&b&lHC &cMusisz być na terenie swojego cuboida!"));
            return true;
        }

        CuboidModel cuboidPlayer = CuboidHelper.GetCuboid(p);
        boolean IsWarpPurchased = cuboidPlayer.isWarpPurchased();

        //czy ma kupionego warpa
        if(IsWarpPurchased){
            CuboidHelper.UpdateWarpLocation(cuboidPlayer, p.getLocation());
            p.sendMessage(CuboidHelper.ColoredText("&6&lFree&b&lHC &7Zmieniłeś lokalizacje warpa cuboida"));
            return true;
        }else{
            //nie ma kupionego warpa
            //zakup warpa
            if(args.length == 2) {
                if (args[1].equalsIgnoreCase("confirm")) {
                    Economy economy = App.getEconomy();
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(p.getUniqueId());
                    if (economy.getBalance(offlinePlayer) <= 15000) {
                        p.sendMessage(ColoredText("&6&lFree&b&lHC &cNie masz tylu pieniędzy!"));
                        return true;
                    }
                    //buy warp
                    CuboidHelper.UpdateWarpLocation(cuboidPlayer, p.getLocation());
                    CuboidHelper.UpdateIsWarpPurchased(cuboidPlayer, true);
                    economy.withdrawPlayer(offlinePlayer, 15000);
                    p.sendMessage(CuboidHelper.ColoredText("&6&lFree&b&lHC &7Zakupiłeś i ustawiłeś swojego warpa!"));
                    return true;
                }
            }
            //potwierdz zakup!
            p.sendMessage(ColoredText("&6&lFree&b&lHC &7Koszt zakupienia warpa to &a15 000$&7! Potwierdź zakup wpisując &a/cuboid setwarp confirm"));
            return true;

        }
    }
}
