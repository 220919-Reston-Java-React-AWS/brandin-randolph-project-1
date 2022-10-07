package com.revature.repository;

import com.revature.model.User;

import java.sql.*;


//This Class will contain JDBC code to interact with the database
//It will help in supporting the various functionalities that our application requires
public class UserRepository {

    //Registration
    public User addUser(User user) throws SQLException { //Create in CRUD
        //connection object, PreparedStatement Object
        //try-with resources is used to automatically close the Connection object when the block of code is finished
        //it will essentially automatically call the connectionObject.close();
        try(Connection connectionObject = ConnectionFactory.createConnection()){
            String sql = "insert into users (username, password, first_name, last_name, role_id) values (?, ?, ?, ?, ?)";//"?" are used as placeholders.

            PreparedStatement pstmt = connectionObject.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//Prepared Statement object.

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getFirstName());
            pstmt.setString(4, user.getLastName());
            pstmt.setInt(5,1); //role_id 1 represents a employee)

            int numberOfRecordsAdded =  pstmt.executeUpdate();//applies to insert, update, and delete

            ResultSet rs = pstmt.getGeneratedKeys(); //creates a temporary table for the automatically generated primaryKey
            rs.next(); //retrieves first row

            int id = rs.getInt(1); //retrieves first column

            return new User(id, user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), 1);




        }

    }

    //Login
    public User getUserByUsernameAndPassword(String username, String password) throws SQLException{ //Read in CRUD

        try (Connection connectionObj = ConnectionFactory.createConnection()){
            String sql = "SELECT * FROM users as u WHERE u.username = ? AND u.password = ?";
            PreparedStatement pstmt = connectionObj.prepareStatement(sql);

            pstmt.setString(1,username);
            pstmt.setString(2,password);

            ResultSet rs = pstmt.executeQuery(); //resultset represents a temporary table that contains all data that we have queried

            if (rs.next()) { //returns a boolean indicating whether there is a record or not for the next row. And iterates to the next row
                int id = rs.getInt("id");
                String un = rs.getString("username");
                String pw = rs.getString("password");
                String firstname = rs.getString("first_name");
                String lastname = rs.getString("last_name");
                int roleid = rs.getInt("role_id");

                return new User(id, un, pw, firstname, lastname, roleid);
            } else {
                return null;
            }

        }
    }

}
