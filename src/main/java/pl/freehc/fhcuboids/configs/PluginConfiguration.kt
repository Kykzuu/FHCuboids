package pl.freehc.fhcuboids.configs

import org.bukkit.configuration.file.FileConfiguration
import pl.freehc.fhcuboids.App

class PluginConfiguration {
    companion object {
        fun getPluginConfiguration(): PluginConfigurationModel {
            val config: FileConfiguration = App.fileConfiguration
            val pluginConfigurationModel = PluginConfigurationModel()

            pluginConfigurationModel.databaseType = config.getString("database.type") ?: ""

            pluginConfigurationModel.databaseMySQLHost = config.getString("database.MySQL.host") ?: ""
            pluginConfigurationModel.databaseMySQLPort = config.getInt("database.MySQL.port")
            pluginConfigurationModel.databaseMySQLUser = config.getString("database.MySQL.username") ?: ""
            pluginConfigurationModel.databaseMySQLPassword = config.getString("database.MySQL.password") ?: ""
            pluginConfigurationModel.databaseMySQLDatabase = config.getString("database.MySQL.database") ?: ""
            pluginConfigurationModel.isDebug = config.getBoolean("Debug")

            pluginConfigurationModel.databaseH2File = config.getString("database.H2.file") ?: ""

            val cuboidsConfig = ArrayList<CuboidConfigurationModel>()

            val cuboidLevels = config.getMapList("CuboidLevels")
            for (level in cuboidLevels) {
                val cuboidConfigurationModel = CuboidConfigurationModel()
                val size = (level["size"] as? Int)?.div(2) ?: 0
                val price = level["price"] as? Int ?: 0
                val item = level["item"] as? String ?: ""
                val crafting = level["crafting"] as? List<String> ?: emptyList()
                var groups = level["groups"] as? List<String> ?: emptyList()
                var worlds = level["worlds"] as? List<String> ?: emptyList()
                val pvp = level["pvp"] as? Boolean ?: false
                cuboidConfigurationModel.size = size
                cuboidConfigurationModel.price = price
                cuboidConfigurationModel.crafting = crafting
                cuboidConfigurationModel.item = item
                cuboidConfigurationModel.groups = groups
                cuboidConfigurationModel.worlds = worlds
                cuboidConfigurationModel.pvp = pvp
                cuboidsConfig.add(cuboidConfigurationModel)
            }

            //groups
            val cuboidGroupsConfig = ArrayList<CuboidGroupConfigurationModel>()
            val cuboidGroups = config.getMapList("CuboidGroups")
            for (group in cuboidGroups) {
                val cuboidGroupConfigurationModel = CuboidGroupConfigurationModel()
                val name = group["name"] as? String ?: ""
                val priority = group["priority"] as? Int ?: 0
                val limit = group["limit"] as? Int ?: 0
                cuboidGroupConfigurationModel.name = name
                cuboidGroupConfigurationModel.priority = priority
                cuboidGroupConfigurationModel.limit = limit
                cuboidGroupsConfig.add(cuboidGroupConfigurationModel)
            }
            pluginConfigurationModel.cuboidGroupsConfig = cuboidGroupsConfig

            pluginConfigurationModel.cuboidsConfig = cuboidsConfig

            val allowedCommands = config.getStringList("AllowedCommandsOnCuboid") ?: emptyList()
            pluginConfigurationModel.allowedCommandsOnCuboid = allowedCommands

            return pluginConfigurationModel
        }
    }
}
