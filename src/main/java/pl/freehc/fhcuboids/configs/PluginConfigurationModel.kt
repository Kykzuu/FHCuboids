package pl.freehc.fhcuboids.configs

class PluginConfigurationModel {
    //mysql
    var databaseMySQLHost: String = ""
    var databaseMySQLPort: Int = 0
    var databaseMySQLUser: String = ""
    var databaseMySQLPassword: String = ""
    var databaseMySQLDatabase: String = ""
    //h2
    var databaseH2File: String = ""
    //database type
    var databaseType: String = ""
    var allowedCommandsOnCuboid: List<String> = emptyList()
    var isDebug: Boolean = false
    var cuboidsConfig: List<CuboidConfigurationModel> = emptyList()
    //groups
    var cuboidGroupsConfig: List<CuboidGroupConfigurationModel> = emptyList()
}