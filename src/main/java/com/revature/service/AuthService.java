package com.revature.service;

import com.revature.exception.InvalidLoginException;
import com.revature.model.User;
import com.revature.repository.UserRepository;

import java.sql.SQLException;

public class AuthService {

    private UserRepository userRepo = new UserRepository();

    public User register(User user) throws SQLException {
        if (userRepo.getUserByUsernameAndPassword(user.getUsername(), user.getPassword()) != null) {
            throw new SQLException("User with username " + user.getUsername() + " already exists!");
        }

        User addedUser = userRepo.addUser(user);

        return addedUser;
    }

    public User login(String username, String password) throws SQLException, InvalidLoginException {
        User user = userRepo.getUserByUsernameAndPassword(username, password);

        if (user == null) { //did not successfully login
            throw new InvalidLoginException("Invalid username and/or password!");
        }
            return user;

    }
}


    // DELETE ONCE YOU KNOW IT'S WORKING
//    public static void main(String[] args) throws SQLException {
//        AuthService as = new AuthService();
//
//        User user = new User(4,"username","password", "user", "name", 1 );
//
//        User addedUser = as.register(user);
//
//        System.out.println(addedUser);
//    }

