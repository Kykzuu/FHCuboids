package pl.freehc.fhcuboids.commands.cuboid;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.freehc.fhcuboids.database.CuboidModel;

import static pl.freehc.fhcuboids.services.CuboidService.*;

public class WarpSubcommand {

    public static boolean WarpSubcommandMain(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        //Czy gracz wpisał nazwe warpu
        if (args.length < 2) {
            p.sendMessage(ColoredText("&6&lFree&b&lHC &7Poprawne użycie: &a/cuboid warp [gracz]"));
            return true;
        }

            //Teleport do czyjegos warpa
            if (args.length == 2) {
                CuboidModel cuboid = GetAllCuboids().stream()
                        .filter(x -> x.isWarpPurchased())
                        .filter(x -> x.getOwnerNickname().equalsIgnoreCase(args[1]))
                        .findFirst().orElse(null);
                if(cuboid != null){
                    return TeleportPlayerToCuboidWarp(p, cuboid);
                }
            }
        p.sendMessage(ColoredText("&6&lFree&b&lHC &cTaki warp nie istnieje!"));
        return true;
    }

    private static boolean TeleportPlayerToCuboidWarp(Player p, CuboidModel cuboid){
        if(!p.getWorld().getName().equals("world")){
            p.sendMessage(ColoredText("&6&lFree&b&lHC  &cJesteś w złym świecie!"));
            return true;
        }
        Location homeLocation = new Location(p.getWorld(), cuboid.getWarpX(), cuboid.getWarpY(), cuboid.getWarpZ(), cuboid.getWarpYaw(), cuboid.getWarpPitch());
        p.teleport(homeLocation);
        p.sendMessage(ColoredText("&6&lFree&b&lHC  &7Teleportuję..."));
        return true;
    }
}
