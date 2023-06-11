package pl.freehc.fhcuboids.commands.cuboid;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import pl.freehc.fhcuboids.App;
import pl.freehc.fhcuboids.CuboidHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static pl.freehc.fhcuboids.CuboidHelper.*;


public class CuboidCommand {

    public static class CuboidCommandMain implements CommandExecutor {
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (cmd.getName().equalsIgnoreCase("cuboid")) {

                if(args.length == 0){
                    sender.sendMessage(ColoredText("&8&l========================"));
                    sender.sendMessage(ColoredText("&6&LFree&b&lHC &8- &f&lPOMOC"));
                    sender.sendMessage(ColoredText("&b&l/cuboid crafting &8- &7crafting cuboida."));
                    sender.sendMessage(ColoredText("&b&l/cuboid home &8- &7teleport na cuboid"));
                    sender.sendMessage(ColoredText("&b&l/cuboid home [gracz] &8- &7teleport do czyjegoś cuboida"));
                    sender.sendMessage(ColoredText("&b&l/cuboid info &8- &7informacje o Twoim cuboidzie"));
                    sender.sendMessage(ColoredText("&b&l/cuboid sethome &8- &7ustawia home Twojego cuboida"));
                    sender.sendMessage(ColoredText("&b&l/cuboid add [gracz] &8- &7dodaje gracza do cuboida."));
                    sender.sendMessage(ColoredText("&b&l/cuboid kick [gracz] &8- &7wyrzuca gracza z cuboida."));
                    sender.sendMessage(ColoredText("&b&l/cuboid leave [gracz] &8- &7opuszcza wybrany cuboid"));
                    //sender.sendMessage(ColoredText("&b&l/cuboid setwarp &8- &7ustawia warpa na Twoim cuboidzie"));
                    //sender.sendMessage(ColoredText("&b&l/cuboid delwarp &8- &7usuwa Twojego warpa z cuboida"));
                    //sender.sendMessage(ColoredText("&b&l/cuboid warp [gracz] &8- &7teleportuj się na cuboid innego gracza"));
                    sender.sendMessage(ColoredText("&b&l/cuboid delete &8- &7usuwa Twój cuboid"));
                    sender.sendMessage(ColoredText("&8&l========================"));
                    return true;
                }


                if(args[0].equalsIgnoreCase("help")){
                    sender.sendMessage(ColoredText("&8&l========================"));
                    sender.sendMessage(ColoredText("&6&LFree&b&lHC &8- &f&lPOMOC"));
                    sender.sendMessage(ColoredText("&b&l/cuboid crafting &8- &7crafting cuboida."));
                    sender.sendMessage(ColoredText("&b&l/cuboid home &8- &7teleport na cuboid"));
                    sender.sendMessage(ColoredText("&b&l/cuboid home [gracz] &8- &7teleport do czyjegoś cuboida"));
                    sender.sendMessage(ColoredText("&b&l/cuboid info &8- &7informacje o Twoim cuboidzie"));
                    sender.sendMessage(ColoredText("&b&l/cuboid sethome &8- &7ustawia home Twojego cuboida"));
                    sender.sendMessage(ColoredText("&b&l/cuboid add [gracz] &8- &7dodaje gracza do cuboida."));
                    sender.sendMessage(ColoredText("&b&l/cuboid kick [gracz] &8- &7wyrzuca gracza z cuboida."));
                    sender.sendMessage(ColoredText("&b&l/cuboid leave [gracz] &8- &7opuszcza wybrany cuboid"));
                    //sender.sendMessage(ColoredText("&b&l/cuboid setwarp &8- &7ustawia warpa na Twoim cuboidzie"));
                    //sender.sendMessage(ColoredText("&b&l/cuboid delwarp &8- &7usuwa Twojego warpa z cuboida"));
                    //sender.sendMessage(ColoredText("&b&l/cuboid warp [gracz] &8- &7teleportuj się na cuboid innego gracza"));
                    sender.sendMessage(ColoredText("&b&l/cuboid delete &8- &7usuwa Twój cuboid"));
                    sender.sendMessage(ColoredText("&8&l========================"));
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
                //if(args[0].equalsIgnoreCase("setwarp")){
                //    return SetwarpSubcommand.SetwarpSubcommandMain(sender, cmd, label, args);
                //}
                //if(args[0].equalsIgnoreCase("delwarp")){
                //    return DelwarpSubcommand.DelwarpSubcommandMain(sender, cmd, label, args);
                //}
                //if(args[0].equalsIgnoreCase("warp")){
                //    return WarpSubcommand.WarpSubcommandMain(sender, cmd, label, args);
                //}
                //if(args[0].equalsIgnoreCase("warps")){
                //    return WarpsSubcommand.WarpsSubcommandMain(sender, cmd, label, args);
                //}

                if(args[0].equalsIgnoreCase("reload")){
                    if (sender.hasPermission("FHCuboids.reload")) {
                        App.getInst().reloadConfig();
                        sender.sendMessage(CuboidHelper.ColoredText("Poprawnie przeładowano."));
                    }
                    return true;
                }

            }
            sender.sendMessage(ColoredText("&6&lFree&b&lHC &7Nieznana komenda! Wpisz &c/cuboid help &7aby uzyskać więcej informacji"));
            return true;
        }

    }

    public static class OnTabComplete implements TabCompleter {
        @Override
        public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
            if (args.length == 1)
                return StringUtil.copyPartialMatches(args[0], Arrays.asList("home", "help", "crafting", "add", "kick", "leave", "delete", "info"), new ArrayList<>());
            else
                return Collections.emptyList();
        }
    }

}
