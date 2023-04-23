package pl.freehc.fhcuboids;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import com.zaxxer.hikari.hibernate.HikariConnectionProvider;
public class HibernateFactory {
    private Configuration getHibernateConfig() {
        PluginConfigurationModel pluginConfigurationModel = PluginConfiguration.getPluginConfiguration();
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://"+pluginConfigurationModel.getDatabaseHost()+":"+pluginConfigurationModel.getDatabasePort()+"/"+pluginConfigurationModel.getDatabaseDatabase());
        configuration.setProperty("hibernate.connection.username", pluginConfigurationModel.getDatabaseUser());
        configuration.setProperty("hibernate.connection.password", pluginConfigurationModel.getDatabasePassword());
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL55Dialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        //configuration.setProperty("hibernate.connection.provider_class", "org.hibernate.hikaricp.internal.HikariCPConnectionProvider");
        //configuration.setProperty("hibernate.hikari.connectionTimeout", "20000");
        //configuration.setProperty("hibernate.hikari.minimumIdle", "10");
        //configuration.setProperty("hibernate.hikari.maximumPoolSize", "20");
        //configuration.setProperty("hibernate.hikari.idleTimeout", "300000");

        if(pluginConfigurationModel.getIsDebug()){
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
