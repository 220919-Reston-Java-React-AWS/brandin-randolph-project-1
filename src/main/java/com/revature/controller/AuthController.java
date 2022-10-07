package com.revature.controller;

import com.revature.exception.InvalidLoginException;
import com.revature.model.User;
import com.revature.service.AuthService;
import io.javalin.Javalin;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class AuthController {
    private AuthService authService = new AuthService();
    public void mapEndpoints(Javalin app){

        app.post("/register", (ctx) -> {
            User userToAdd = ctx.bodyAsClass(User.class);

            try {
                User addedUser = authService.register(userToAdd);

                ctx.json(addedUser);
                ctx.status(201);
            } catch (SQLException e) {
                ctx.result(e.getMessage());
                ctx.status(400);
            }
        });

        app.post("/login", (ctx) -> { //lambda expression. Basically a way of passing methods into another method as an argument indirectly.
            //By default, lambda expressions declares any exception
            User credentials = ctx.bodyAsClass(User.class); //This will take the JSON in the request body and place it into the user Object.

           try {
               User user = authService.login(credentials.getUsername(),credentials.getPassword());

               //Set the user object into a HTTP Session object
               HttpSession session = ctx.req.getSession(); //gets the http session
               session.setAttribute("user", user);
               ctx.result("You are logged in");
           } catch (InvalidLoginException e) {
               ctx.status(400);
               ctx.result(e.getMessage());// send back the string as a response to postman
           }


        });

        app.get("/currentUser", (ctx)->{
            HttpSession session = ctx.req.getSession(); //

            User user = (User) session.getAttribute("user");

            if (user != null) {
                ctx.json(user);
            }else {
                ctx.result("user is not logged in!");
                ctx.status(400); //status code
            }
        });

        //logout
        app.post("/logout", (ctx)->{
            ctx.req.getSession().invalidate(); //invalidates the active HTTP Session
        });

    }
}
