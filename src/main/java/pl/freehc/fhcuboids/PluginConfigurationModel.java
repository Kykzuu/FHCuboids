package pl.freehc.fhcuboids;

public class PluginConfigurationModel {
    private String databaseHost;
    private int databasePort;
    private String databaseUser;
    private String databasePassword;
    private String databaseDatabase;
    private boolean isDebug;


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


}
