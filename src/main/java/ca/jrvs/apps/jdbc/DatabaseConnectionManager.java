package ca.jrvs.apps.jdbc;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverAction;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnectionManager {

    private final String url;
    private final Properties properties;


    public DatabaseConnectionManager(String host, String databaseName, String userName, String password){
        this.url= "jdbc:postgresql://"+host+ "/"+databaseName;
        this.properties =new Properties();
        this.properties.setProperty("user",userName);
        this.properties.setProperty("password", password);

    }

    public Connection getConnection() throws Exception{

        return DriverManager.getConnection(this.url,this.properties);

    }


}
