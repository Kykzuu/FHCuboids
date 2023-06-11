package pl.freehc.fhcuboids.commands.cuboid;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.freehc.fhcuboids.CuboidModel;

import static pl.freehc.fhcuboids.CuboidHelper.*;

public class HomeSubcommand {

    public static boolean HomeSubcommandMain(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
            //Czy ma cuboid lub jest dodany do czyjegos
            if (!IsPlayerHasCuboid(p) && GetCuboidsWherePlayerIsAdded(p).size() == 0) {
                p.sendMessage(ColoredText("&6&lFree&b&lHC &cNie posiadasz cuboida!"));
                return true;
            }

            ///cuboid home nick
            //Teleport do czyjegos cuboida
            if (args.length == 2) {
                CuboidModel cuboid = GetCuboidsWherePlayerIsAdded(p).stream()
                        .filter(x -> x.getOwnerNickname().equalsIgnoreCase(args[1]))
                        .findFirst().orElse(null);
                if(cuboid == null || !HasPermissionToCuboid(cuboid, p)){
                    p.sendMessage(ColoredText("&6&lFree&b&lHC  &cNie jesteś dodany do cuboida tego gracza!"));
                    return true;
                }
                return TeleportPlayerToCuboid(p, cuboid);
            }

            //cuboid home
            //Teleport do swojego cuboida
            if (IsPlayerHasCuboid(p)) {
                CuboidModel cuboid = GetCuboid(p);
                return TeleportPlayerToCuboid(p, cuboid);
            }

            //cuboid home
            //Teleport do czyjegos cuboida jesli nie ma swojego
            //oraz dodany jest do tylko jednego
            if (GetCuboidsWherePlayerIsAdded(p).size() == 1) {
                CuboidModel cuboid = GetCuboidsWherePlayerIsAdded(p).stream()
                        .findFirst().orElse(null);
                return TeleportPlayerToCuboid(p, cuboid);
            } else if (GetCuboidsWherePlayerIsAdded(p).size() > 1) {
                p.sendMessage(ColoredText("&6&lFree&b&lHC  &cPodaj nick właściciela cuboida!"));
                return true;
            }


        return false;
    }

    private static boolean TeleportPlayerToCuboid(Player p, CuboidModel cuboid){
        if(!p.getWorld().getName().equals("world")){
            p.sendMessage(ColoredText("&6&lFree&b&lHC  &cJesteś w złym świecie!"));
            return true;
        }
        Location homeLocation = new Location(p.getWorld(), cuboid.getJajX(), cuboid.getJajY(), cuboid.getJajZ(), cuboid.getJajYaw(), cuboid.getJajPitch());
        p.teleport(homeLocation);
        p.sendMessage(ColoredText("&6&lFree&b&lHC  &7Teleportuję..."));
        return true;
    }
}
