package pl.freehc.fhcuboids;

import java.util.List;

public class PluginConfigurationModel {
    private String databaseMySQLHost;
    private int databaseMySQLPort;
    private String databaseMySQLUser;
    private String databaseMySQLPassword;
    private String databaseMySQLDatabase;

    private String databaseH2File;

    private String databaseType;
    private List<String> allowedCommandsOnCuboid;
    private boolean isDebug;
    private List<CuboidConfigurationModel> cuboidsConfig;


    public List<String> getAllowedCommandsOnCuboid() {
        return allowedCommandsOnCuboid;
    }
    public void setAllowedCommandsOnCuboid(List<String> allowedCommandsOnCuboid) {
        this.allowedCommandsOnCuboid = allowedCommandsOnCuboid;
    }

    public String getDatabaseType() { return databaseType;}
    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public String getDatabaseH2File() { return databaseH2File;}
    public void setDatabaseH2File(String databaseFile) {
        this.databaseH2File = databaseFile;
    }

    public String getDatabaseMySQLHost() {
        return databaseMySQLHost;
    }
    public void setDatabaseMySQLHost(String databaseHost) {
        this.databaseMySQLHost = databaseHost;
    }

    public int getDatabaseMySQLPort() {
        return databaseMySQLPort;
    }
    public void setDatabaseMySQLPort(int databasePort) {
        this.databaseMySQLPort = databasePort;
    }

    public String getDatabaseMySQLUser() {
        return databaseMySQLUser;
    }
    public void setDatabaseMySQLUser(String databaseUser) {
        this.databaseMySQLUser = databaseUser;
    }

    public String getDatabaseMySQLPassword() {
        return databaseMySQLPassword;
    }
    public void setDatabaseMySQLPassword(String databasePassword) {
        this.databaseMySQLPassword = databasePassword;
    }

    public String getDatabaseMySQLDatabase() {
        return databaseMySQLDatabase;
    }
    public void setDatabaseMySQLDatabase(String databaseDatabase) {
        this.databaseMySQLDatabase = databaseDatabase;
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

