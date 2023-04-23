package pl.freehc.fhcuboids;

import java.util.List;

public class PluginConfigurationModel {
    private String databaseHost;
    private int databasePort;
    private String databaseUser;
    private String databasePassword;
    private String databaseDatabase;
    private List<String> allowedCommandsOnCuboid;
    private boolean isDebug;
    private List<CuboidConfigurationModel> cuboidsConfig;


    public List<String> getAllowedCommandsOnCuboid() {
        return allowedCommandsOnCuboid;
    }
    public void setAllowedCommandsOnCuboid(List<String> allowedCommandsOnCuboid) {
        this.allowedCommandsOnCuboid = allowedCommandsOnCuboid;
    }
    public String getDatabaseHost() {
        return databaseHost;
    }
    public void setDatabaseHost(String databaseHost) {
        this.databaseHost = databaseHost;
    }

    public int getDatabasePort() {
        return databasePort;
    }
    public void setDatabasePort(int databasePort) {
        this.databasePort = databasePort;
    }

    public String getDatabaseUser() {
        return databaseUser;
    }
    public void setDatabaseUser(String databaseUser) {
        this.databaseUser = databaseUser;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }
    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

    public String getDatabaseDatabase() {
        return databaseDatabase;
    }
    public void setDatabaseDatabase(String databaseDatabase) {
        this.databaseDatabase = databaseDatabase;
    }

    public boolean getIsDebug() {
        return isDebug;
    }
    public void setIsDebug(boolean isDebug) {
        this.isDebug = isDebug;
    }

    public List<CuboidConfigurationModel> getCuboidsConfig() {
        return cuboidsConfig;
    }
    public void setCuboidsConfig(List<CuboidConfigurationModel> cuboidsConfig) {
        this.cuboidsConfig = cuboidsConfig;
    }
}

