package pl.freehc.fhcuboids;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.dynmap.DynmapAPI;
import pl.freehc.fhcuboids.commands.cuboid.CraftingSubcommand;
import pl.freehc.fhcuboids.commands.cuboid.CuboidCommand;
import pl.freehc.fhcuboids.events.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class App extends JavaPlugin {
    private static App instance;
    private Connection connection;
    private String host, database, username, password;
    private int port;
    public Statement statement;
    public CacheHelper<String, List<CuboidModel>> cuboidCache;
    public static Economy econ = null;
    public static FileConfiguration fileConfiguration;
    public static DynmapAPI dynmapApi = null;


    public static ItemStack CuboidItem(int size, double price, Material item) {
        ItemStack it = new ItemStack(item, 1);
        ItemMeta im = it.getItemMeta();
        im.setDisplayName("§aCuboid");
        im.setLore(Arrays.asList(
                "§cPostaw aby zabezpieczyć teren!",
                "§cWielkość: §a"+size*2+"x"+size*2,
                "§cCena: §a"+price+"$",
                "§cMożna umieścić w Netherze: §aNIE"));
        it.setItemMeta(im);
        return it;
    }

    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        fileConfiguration = getConfig();

        //register events
        Bukkit.getPluginManager().registerEvents(new PlaceEvent(), this);
        Bukkit.getPluginManager().registerEvents(new BuildEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MoveEvent(), this);
        //Bukkit.getPluginManager().registerEvents((Listener)new DisablePvpEvent(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new BlockCommandsOnCuboidEvent(), this);
        Bukkit.getPluginManager().registerEvents(new CraftingSubcommand(), this);
        Bukkit.getPluginManager().registerEvents(new BlockLiquidsOnCuboidEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ProtectMobsOnCuboidEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ProtectCuboidAgainstFireEvents(), this);
        Bukkit.getPluginManager().registerEvents(new ProtectArmorStandAndItemFrameOnCuboidEvent(), this);
        Bukkit.getPluginManager().registerEvents(new BlockTeleportsOnCuboidEvents(), this);
        //TODO RAMKI FRAME
        //TODO TNT

        //register commands
        getCommand("cuboid").setExecutor(new CuboidCommand.CuboidCommandMain());
        getCommand("cuboid").setTabCompleter(new CuboidCommand.OnTabComplete());


        //initialize cache
        cuboidCache = new CacheHelper<String, List<CuboidModel>>(5, 10, 1);

        //register recipes
        PluginConfigurationModel pluginConfigurationModel = PluginConfiguration.getPluginConfiguration();
        int i = 0;
        for(CuboidConfigurationModel cuboidConfigurationModel : pluginConfigurationModel.getCuboidsConfig()){
            NamespacedKey recipeNamespace = new NamespacedKey(this, "FHcuboidlvl"+i);
            List<String> configRecipe = cuboidConfigurationModel.getCrafting();
            ShapedRecipe recipe = (new ShapedRecipe(recipeNamespace, CuboidItem(cuboidConfigurationModel.getSize(), cuboidConfigurationModel.getPrice(), Material.getMaterial(cuboidConfigurationModel.getItem()) ))).shape("123", "456", "789").setIngredient('1', Material.getMaterial(configRecipe.get(0))).setIngredient('2', Material.getMaterial(configRecipe.get(1))).setIngredient('3', Material.getMaterial(configRecipe.get(2))).setIngredient('4', Material.getMaterial(configRecipe.get(3))).setIngredient('5', Material.getMaterial(configRecipe.get(4))).setIngredient('6', Material.getMaterial(configRecipe.get(5))).setIngredient('7',Material.getMaterial(configRecipe.get(6))).setIngredient('8', Material.getMaterial(configRecipe.get(7))).setIngredient('9', Material.getMaterial(configRecipe.get(8)));
            Bukkit.addRecipe(recipe);
            i++;
        }
        CraftingSubcommand.initializeItems();

        //register economy
        if (!SetupEconomy() ) {
            System.out.printf("[%s] - Disabled due to no Vault dependency found!%n", getDescription().getName());
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        //register dynmap
        if (getServer().getPluginManager().getPlugin("dynmap") != null) {
            dynmapApi = (DynmapAPI) Bukkit.getServer().getPluginManager().getPlugin("dynmap");
        }
    }

    public static App getInst() {
        return instance;
    }


    private boolean SetupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }
}

