package pl.freehc.fhcuboids.commands.cuboid;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.freehc.fhcuboids.CuboidHelper;
import pl.freehc.fhcuboids.CuboidModel;

import java.util.UUID;

public class InfoSubcommand {
    public static boolean InfoSubcommandMain(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

            //Czy ma cuboid
            if (!CuboidHelper.IsPlayerHasCuboid(p)) {
                p.sendMessage(CuboidHelper.GetPrefixedText("Nie posiadasz cuboida!"));
                return true;
            }

            CuboidModel cuboid = CuboidHelper.GetCuboid(p);
            sender.sendMessage(CuboidHelper.GetPrefixedText("Informacje"));
            sender.sendMessage(CuboidHelper.GetPrefixedBulletText("§aLokalizacja: §rX: "+cuboid.getjajX()+" Y: "+cuboid.getjajZ() + " Z: "+cuboid.getjajZ()));
            sender.sendMessage(CuboidHelper.GetPrefixedBulletText("§aWielkość:§r "+cuboid.getSize()*2 + "x"+cuboid.getSize()*2));
            String friends = "";
            if(cuboid.getFriendsUUID().size() > 1){
                StringBuilder stringBuilder = new StringBuilder();
                int i = 0;
                for(UUID friend : cuboid.getFriendsUUID()){
                    if(i == 0){
                        stringBuilder.append(Bukkit.getOfflinePlayer(friend).getName());
                    }else{
                        stringBuilder.append(", "+Bukkit.getOfflinePlayer(friend).getName());
                    }
                    i++;
                }
                friends = stringBuilder.toString();
            }else{
                friends = "brak";
            }
            sender.sendMessage(CuboidHelper.GetPrefixedBulletText("§aDodani gracze:§r "+friends));
            return true;
    }
}
