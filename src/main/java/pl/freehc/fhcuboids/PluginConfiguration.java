package pl.freehc.fhcuboids;

import org.apache.commons.collections.list.SynchronizedList;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PluginConfiguration {
    public static PluginConfigurationModel getPluginConfiguration() {
        FileConfiguration config = App.fileConfiguration;
        PluginConfigurationModel pluginConfigurationModel = new PluginConfigurationModel();
        pluginConfigurationModel.setDatabaseHost(config.getString("database.host"));
        pluginConfigurationModel.setDatabasePort(config.getInt("database.port"));
        pluginConfigurationModel.setDatabaseUser(config.getString("database.user"));
        pluginConfigurationModel.setDatabasePassword(config.getString("database.password"));
        pluginConfigurationModel.setDatabaseDatabase(config.getString("database.database"));
        pluginConfigurationModel.setIsDebug(config.getBoolean("Debug"));

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
