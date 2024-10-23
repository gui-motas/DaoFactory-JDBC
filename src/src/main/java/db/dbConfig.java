package db;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class dbConfig {

    private static Connection conn;

    public static Properties loadProperties() {

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/db.properties"))) {
            Properties props = new Properties();
            props.load(br);
            return props;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {

        if(conn == null){
            Properties props = loadProperties();
            String url = props.getProperty("dburl");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            try {
                conn = java.sql.DriverManager.getConnection(url, user, password);
            } catch (java.sql.SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }
}
