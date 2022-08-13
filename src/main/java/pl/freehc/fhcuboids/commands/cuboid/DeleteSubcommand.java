package pl.freehc.fhcuboids.commands.cuboid;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.freehc.fhcuboids.CuboidModel;

import static pl.freehc.fhcuboids.CuboidHelper.*;

public class DeleteSubcommand{
    public static boolean DeleteSubcommandMain(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
            //Czy ma cuboid
            if (!IsPlayerHasCuboid(p)) {
                p.sendMessage(GetPrefixedText("Nie posiadasz cuboida!"));
                return true;
            }

            //cuboid delete confirm
            //Usun cuboid jezeli potwierdzi
            if(args.length == 2){
                if(args[1].equalsIgnoreCase("confirm")){
                    CuboidModel cuboid = GetCuboid(p);
                    DeleteCuboid(cuboid);
                    p.sendMessage(GetPrefixedText("Usunąłeś swój cuboid"));
                    return true;
                }
            }


            p.sendMessage(GetPrefixedText("Tej operacji nie można cofnąć! Potwierdź działanie za pomocą komendy /cuboid delete confirm"));
            return true;

    }
}