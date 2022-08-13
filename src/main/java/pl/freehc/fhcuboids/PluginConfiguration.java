package pl.freehc.fhcuboids;

import org.bukkit.configuration.file.FileConfiguration;

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
        return pluginConfigurationModel;
    }
}
