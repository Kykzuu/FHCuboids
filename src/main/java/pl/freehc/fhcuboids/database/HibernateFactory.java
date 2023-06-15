package pl.freehc.fhcuboids.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import pl.freehc.fhcuboids.App;
import pl.freehc.fhcuboids.configs.PluginConfiguration;
import pl.freehc.fhcuboids.configs.PluginConfigurationModel;
import pl.freehc.fhcuboids.database.CuboidModel;

public class HibernateFactory {
    private Configuration getHibernateConfig() {
        PluginConfigurationModel pluginConfigurationModel = PluginConfiguration.Companion.getPluginConfiguration();
        Configuration configuration = new Configuration();
        if(pluginConfigurationModel.getDatabaseType().equalsIgnoreCase("MySQL")){
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://"+pluginConfigurationModel.getDatabaseMySQLHost()+":"+pluginConfigurationModel.getDatabaseMySQLPort()+"/"+pluginConfigurationModel.getDatabaseMySQLDatabase());
            configuration.setProperty("hibernate.connection.username", pluginConfigurationModel.getDatabaseMySQLUser());
            configuration.setProperty("hibernate.connection.password", pluginConfigurationModel.getDatabaseMySQLPassword());
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL55Dialect");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        }
        if(pluginConfigurationModel.getDatabaseType().equalsIgnoreCase("H2")){
            configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:h2:"+ App.getInst().AbsolutePath+"/"+pluginConfigurationModel.getDatabaseH2File()+";DB_CLOSE_DELAY=-1");
            configuration.setProperty("hibernate.connection.username", "sa");
            configuration.setProperty("hibernate.connection.password", "");
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        }
        //configuration.setProperty("hibernate.connection.provider_class", "org.hibernate.hikaricp.internal.HikariCPConnectionProvider");
        //configuration.setProperty("hibernate.hikari.connectionTimeout", "20000");
        //configuration.setProperty("hibernate.hikari.minimumIdle", "10");
        //configuration.setProperty("hibernate.hikari.maximumPoolSize", "20");
        //configuration.setProperty("hibernate.hikari.idleTimeout", "300000");

        if(pluginConfigurationModel.isDebug()){
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.format_sql", "true");
            configuration.setProperty("hibernate.use_sql_comments", "true");
        }else{
            configuration.setProperty("hibernate.show_sql", "false");
            configuration.setProperty("hibernate.format_sql", "false");
            configuration.setProperty("hibernate.use_sql_comments", "false");
        }

        //models
        configuration.addAnnotatedClass(CuboidModel.class);
        return configuration;
    }

    public SessionFactory getSessionFactory() {
        try {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().
                    applySettings(getHibernateConfig().getProperties()).build();
            return getHibernateConfig().buildSessionFactory(registry);
        } catch (Exception e){
            throw e;
        }
    }
}
