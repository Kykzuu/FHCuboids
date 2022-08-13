package pl.freehc.fhcuboids.commands.cuboid;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.freehc.fhcuboids.App;
import pl.freehc.fhcuboids.CuboidHelper;

import static pl.freehc.fhcuboids.CuboidHelper.GetPrefixedBulletText;
import static pl.freehc.fhcuboids.CuboidHelper.GetPrefixedText;


public class CuboidCommand {

    public static class CuboidCommandMain implements CommandExecutor {
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (cmd.getName().equalsIgnoreCase("cuboid")) {

                if(args.length == 0){
                    sender.sendMessage(GetPrefixedText("pomoc"));
                    sender.sendMessage(GetPrefixedBulletText("§b/cuboid crafting §8- §7crafting cuboida."));
                    sender.sendMessage(GetPrefixedBulletText("§b/cuboid home §8- §7teleport na cuboid"));
                    sender.sendMessage(GetPrefixedBulletText("§b/cuboid home [gracz] §8- §7teleport do czyjegoś cuboida"));
                    sender.sendMessage(GetPrefixedBulletText("§b/cuboid info §8- §7informacje o Twoim cuboidzie"));
                    sender.sendMessage(GetPrefixedBulletText("§b/cuboid sethome §8- §7ustawia home Twojego cuboida"));
                    sender.sendMessage(GetPrefixedBulletText("§b/cuboid add [gracz] §8- §7dodaje gracza do cuboida."));
                    sender.sendMessage(GetPrefixedBulletText("§b/cuboid kick [gracz] §8- §7wyrzuca gracza z cuboida."));
                    sender.sendMessage(GetPrefixedBulletText("§b/cuboid leave [gracz] §8- §7opuszcza wybrany cuboid"));
                    sender.sendMessage(GetPrefixedBulletText("§b/cuboid delete §8- §7usuwa Twój cuboid"));
                    return true;
                }


                if(args[0].equalsIgnoreCase("help")){
                    sender.sendMessage(GetPrefixedText("pomoc"));
                    sender.sendMessage(GetPrefixedBulletText("§b/cuboid crafting §8- §7crafting cuboida."));
                    sender.sendMessage(GetPrefixedBulletText("§b/cuboid home §8- §7teleport na cuboid"));
                    sender.sendMessage(GetPrefixedBulletText("§b/cuboid home [gracz] §8- §7teleport do czyjegoś cuboida"));
                    sender.sendMessage(GetPrefixedBulletText("§b/cuboid info §8- §7informacje o Twoim cuboidzie"));
                    sender.sendMessage(GetPrefixedBulletText("§b/cuboid sethome §8- §7ustawia home Twojego cuboida"));
                    sender.sendMessage(GetPrefixedBulletText("§b/cuboid add [gracz] §8- §7dodaje gracza do cuboida."));
                    sender.sendMessage(GetPrefixedBulletText("§b/cuboid kick [gracz] §8- §7wyrzuca gracza z cuboida."));
                    sender.sendMessage(GetPrefixedBulletText("§b/cuboid leave [gracz] §8- §7opuszcza wybrany cuboid"));
                    sender.sendMessage(GetPrefixedBulletText("§b/cuboid delete §8- §7usuwa Twój cuboid"));
                    return true;
                }

                if(args[0].equalsIgnoreCase("home")){
                    return HomeSubcommand.HomeSubcommandMain(sender, cmd, label, args);
                }

                if(args[0].equalsIgnoreCase("crafting")){
                    return CraftingSubcommand.CraftingSubcommandMain(sender, cmd, label, args);
                }

                if(args[0].equalsIgnoreCase("add")){
                    return AddSubcommand.AddSubcommandMain(sender, cmd, label, args);
                }

                if(args[0].equalsIgnoreCase("kick")){
                    return KickSubcommand.KickSubcommandMain(sender, cmd, label, args);
                }

                if(args[0].equalsIgnoreCase("leave")){
                    return LeaveSubcommand.LeaveSubcommandMain(sender, cmd, label, args);
                }

                if(args[0].equalsIgnoreCase("delete")){
                    return DeleteSubcommand.DeleteSubcommandMain(sender, cmd, label, args);
                }

                if(args[0].equalsIgnoreCase("info")){
                    return InfoSubcommand.InfoSubcommandMain(sender, cmd, label, args);
                }

                if(args[0].equalsIgnoreCase("sethome")){
                    return SethomeSubcommand.SethomeSubcommandMain(sender, cmd, label, args);
                }

                if(args[0].equalsIgnoreCase("reload")){
                    if (sender.hasPermission("FHCuboids.reload")) {
                        App.getInst().reloadConfig();
                        sender.sendMessage(CuboidHelper.GetPrefixedText("Poprawnie przeładowano."));
                    }
                    return true;
                }

            }
            sender.sendMessage(GetPrefixedText("Nieznana komenda! Wpisz /cuboid help aby uzyskać więcej informacji"));
            return true;
        }
    }

}
