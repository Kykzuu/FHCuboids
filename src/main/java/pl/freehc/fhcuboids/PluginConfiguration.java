package pl.freehc.fhcuboids;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PluginConfiguration {
    public static PluginConfigurationModel getPluginConfiguration() {
        FileConfiguration config = App.fileConfiguration;
        PluginConfigurationModel pluginConfigurationModel = new PluginConfigurationModel();

        pluginConfigurationModel.setDatabaseType(config.getString("database.type"));

        pluginConfigurationModel.setDatabaseMySQLHost(config.getString("database.MySQL.host"));
        pluginConfigurationModel.setDatabaseMySQLPort(config.getInt("database.MySQL.port"));
        pluginConfigurationModel.setDatabaseMySQLUser(config.getString("database.MySQL.username"));
        pluginConfigurationModel.setDatabaseMySQLPassword(config.getString("database.MySQL.password"));
        pluginConfigurationModel.setDatabaseMySQLDatabase(config.getString("database.MySQL.database"));
        pluginConfigurationModel.setIsDebug(config.getBoolean("Debug"));

        pluginConfigurationModel.setDatabaseH2File(config.getString("database.H2.file"));

        List<CuboidConfigurationModel> cuboidsConfig = new ArrayList<>();

        List<Map<?, ?>> cuboidLevels = config.getMapList("CuboidLevels");
        for (Map<?, ?> level : cuboidLevels) {
            CuboidConfigurationModel cuboidConfigurationModel = new CuboidConfigurationModel();
            int size = (int) Math.floor(((int)level.get("size"))/2);
            int price = (int) level.get("price");
            String item = (String) level.get("item");
            List<String> crafting = (List<String>) level.get("crafting");
            cuboidConfigurationModel.setSize(size);
            cuboidConfigurationModel.setPrice(price);
            cuboidConfigurationModel.setCrafting(crafting);
            cuboidConfigurationModel.setItem(item);
            cuboidsConfig.add(cuboidConfigurationModel);
        }
        pluginConfigurationModel.setCuboidsConfig(cuboidsConfig);

        List<String> allowedCommands = config.getStringList("AllowedCommandsOnCuboid");
        pluginConfigurationModel.setAllowedCommandsOnCuboid(allowedCommands);
        return pluginConfigurationModel;
    }
}
