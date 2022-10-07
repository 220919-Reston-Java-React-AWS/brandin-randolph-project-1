package com.revature.repository;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection createConnection() throws SQLException {
        Driver postgresDriver = new Driver(); //1. postgres driver object with the DriverManager class from JDBC
        DriverManager.registerDriver(postgresDriver);

        //2.Define the database location(URL/Connection String) and username and password
        String url = "jdbc:postgresql://localhost:5432/postgres";

        //Environment Variable
        String username = System.getenv("database_username");//.getenv() method is used to read value of Environment Variable.
        String password = System.getenv("database_password");

        //not best practice!!!!
//        String username = "postgres";
//        String password = "myPassword";

        Connection connectionObject = DriverManager.getConnection(url, username, password);
        return connectionObject;


    }
}
