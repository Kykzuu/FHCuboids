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


    public static ItemStack CuboidItem(int size, double price) {
        ItemStack it = new ItemStack(Material.SPONGE, 1);
        ItemMeta im = it.getItemMeta();
        im.setDisplayName("§aCuboid");
        im.setLore(Arrays.asList(
                "§cPostaw aby zabezpieczyć teren!",
                "§cWielkość: §a"+size*2+"x"+size*2,
                "§cCena: §a"+price+"$"));
        it.setItemMeta(im);
        return it;
    }

    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        fileConfiguration = getConfig();
        if(!(this.getConfig().getString("Debug").equalsIgnoreCase("True"))){
            java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF);
            java.util.logging.Logger.getLogger("com.zaxxer.hikari.HikariDataSource").setLevel(java.util.logging.Level.OFF);
        }
        Bukkit.getPluginManager().registerEvents((Listener)new PlaceEvent(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new BuildEvent(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new MoveEvent(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new DisablePvpEvent(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new BlockCommandsOnCuboidEvent(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new CraftingSubcommand(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new BlockLiquidsOnCuboidEvent(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new ProtectMobsOnCuboidEvent(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new ProtectCuboidAgainstFireEvents(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new ProtectArmorStandAndItemFrameOnCuboidEvent(), (Plugin)this);
        //TODO RAMKI FRAME
        //TODO TNT
        getCommand("cuboid").setExecutor(new CuboidCommand.CuboidCommandMain());


        host = this.getConfig().getString("database.host");;
        port = Integer.parseInt(this.getConfig().getString("database.port"));;
        //database = "minecraft?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true";
        database = this.getConfig().getString("database.database");
        username = this.getConfig().getString("database.user");;
        password = this.getConfig().getString("database.password");;
        try {
            openConnection();
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE if not EXISTS `mymc_cuboids` ( `id` INT NOT NULL AUTO_INCREMENT , `max` DOUBLE NOT NULL , `mix` DOUBLE NOT NULL , `maz` DOUBLE NOT NULL , `miz` DOUBLE NOT NULL , `jajx` DOUBLE NOT NULL , `jajy` DOUBLE NOT NULL , `size` INT NOT NULL , `jajz` DOUBLE NOT NULL , `owneruuid` TINYTEXT NOT NULL , `ownernickname` TINYTEXT NOT NULL , `friendsuuid` TEXT DEFAULT NULL , `expiretime` BIGINT NOT NULL , `createdtime` BIGINT NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            this.getPluginLoader().disablePlugin(this);
        }

        cuboidCache = new CacheHelper<String, List<CuboidModel>>(5, 10, 1);
        NamespacedKey cuboidlvl1 = new NamespacedKey(this, "cuboidlvl1");
        NamespacedKey cuboidlvl2 = new NamespacedKey(this, "cuboidlvl2");
        NamespacedKey cuboidlvl3 = new NamespacedKey(this, "cuboidlvl3");
        NamespacedKey cuboidlvl4 = new NamespacedKey(this, "cuboidlvl4");
        NamespacedKey cuboidlvl5 = new NamespacedKey(this, "cuboidlvl5");
        NamespacedKey cuboidlvl6 = new NamespacedKey(this, "cuboidlvl6");
        NamespacedKey cuboidlvl7 = new NamespacedKey(this, "cuboidlvl7");
        ShapedRecipe cuboidlvl1recipe = (new ShapedRecipe(cuboidlvl1, CuboidItem(40, 0))).shape(new String[] { "123", "456", "789" }).setIngredient('1', Material.COBBLESTONE).setIngredient('2', Material.COBBLESTONE).setIngredient('3', Material.COBBLESTONE).setIngredient('4', Material.COBBLESTONE).setIngredient('5', Material.DIRT).setIngredient('6', Material.COBBLESTONE).setIngredient('7', Material.COBBLESTONE).setIngredient('8', Material.COBBLESTONE).setIngredient('9', Material.COBBLESTONE);
        ShapedRecipe cuboidlvl2recipe = (new ShapedRecipe(cuboidlvl2, CuboidItem(50, 25000))).shape(new String[] { "123", "456", "789" }).setIngredient('1', Material.COBBLESTONE).setIngredient('2', Material.COBBLESTONE).setIngredient('3', Material.COBBLESTONE).setIngredient('4', Material.COBBLESTONE).setIngredient('5', Material.IRON_BLOCK).setIngredient('6', Material.COBBLESTONE).setIngredient('7', Material.COBBLESTONE).setIngredient('8', Material.COBBLESTONE).setIngredient('9', Material.COBBLESTONE);
        ShapedRecipe cuboidlvl3recipe = (new ShapedRecipe(cuboidlvl3, CuboidItem(60, 75000))).shape(new String[] { "123", "456", "789" }).setIngredient('1', Material.IRON_BLOCK).setIngredient('2', Material.IRON_BLOCK).setIngredient('3', Material.IRON_BLOCK).setIngredient('4', Material.IRON_BLOCK).setIngredient('5', Material.DIAMOND_BLOCK).setIngredient('6', Material.IRON_BLOCK).setIngredient('7', Material.IRON_BLOCK).setIngredient('8', Material.IRON_BLOCK).setIngredient('9', Material.IRON_BLOCK);
        ShapedRecipe cuboidlvl4recipe = (new ShapedRecipe(cuboidlvl4, CuboidItem(70, 250000))).shape(new String[] { "123", "456", "789" }).setIngredient('1', Material.IRON_BLOCK).setIngredient('2', Material.IRON_BLOCK).setIngredient('3', Material.IRON_BLOCK).setIngredient('4', Material.IRON_BLOCK).setIngredient('5', Material.EMERALD_BLOCK).setIngredient('6', Material.IRON_BLOCK).setIngredient('7', Material.IRON_BLOCK).setIngredient('8', Material.IRON_BLOCK).setIngredient('9', Material.IRON_BLOCK);
        ShapedRecipe cuboidlvl5recipe = (new ShapedRecipe(cuboidlvl5, CuboidItem(80, 500000))).shape(new String[] { "123", "456", "789" }).setIngredient('1', Material.DIAMOND_BLOCK).setIngredient('2', Material.DIAMOND_BLOCK).setIngredient('3', Material.DIAMOND_BLOCK).setIngredient('4', Material.DIAMOND_BLOCK).setIngredient('5', Material.NETHERITE_BLOCK).setIngredient('6', Material.DIAMOND_BLOCK).setIngredient('7', Material.DIAMOND_BLOCK).setIngredient('8', Material.DIAMOND_BLOCK).setIngredient('9', Material.DIAMOND_BLOCK);
        ShapedRecipe cuboidlvl6recipe = (new ShapedRecipe(cuboidlvl6, CuboidItem(90, 1000000))).shape(new String[] { "123", "456", "789" }).setIngredient('1', Material.DIAMOND_BLOCK).setIngredient('2', Material.DIAMOND_BLOCK).setIngredient('3', Material.DIAMOND_BLOCK).setIngredient('4', Material.DIAMOND_BLOCK).setIngredient('5', Material.BEACON).setIngredient('6', Material.DIAMOND_BLOCK).setIngredient('7', Material.DIAMOND_BLOCK).setIngredient('8', Material.DIAMOND_BLOCK).setIngredient('9', Material.DIAMOND_BLOCK);
        ShapedRecipe cuboidlvl7recipe = (new ShapedRecipe(cuboidlvl7, CuboidItem(100, 5000000))).shape(new String[] { "123", "456", "789" }).setIngredient('1', Material.BEACON).setIngredient('2', Material.BEACON).setIngredient('3', Material.BEACON).setIngredient('4', Material.BEACON).setIngredient('5', Material.BEACON).setIngredient('6', Material.BEACON).setIngredient('7', Material.BEACON).setIngredient('8', Material.BEACON).setIngredient('9', Material.BEACON);
        getServer().addRecipe((Recipe)cuboidlvl1recipe);
        getServer().addRecipe((Recipe)cuboidlvl2recipe);
        getServer().addRecipe((Recipe)cuboidlvl3recipe);
        getServer().addRecipe((Recipe)cuboidlvl4recipe);
        getServer().addRecipe((Recipe)cuboidlvl5recipe);
        getServer().addRecipe((Recipe)cuboidlvl6recipe);
        getServer().addRecipe((Recipe)cuboidlvl7recipe);
        CraftingSubcommand.initializeItems();
        if (!SetupEconomy() ) {
            System.out.println(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
    }

    public static App getInst() {
        return instance;
    }


    public void openConnection() throws SQLException, ClassNotFoundException {
        if (connection != null && !connection.isClosed()) {
            return;
        }

        synchronized(this) {
            if (connection != null && !connection.isClosed()) {
                return;
            }
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password);
        }
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

