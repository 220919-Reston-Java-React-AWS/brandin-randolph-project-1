package com.revature;

import com.revature.controller.AuthController;
import com.revature.controller.ReimburseController;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.repository.ConnectionFactory;
import com.revature.repository.ReimbursementRepository;
import com.revature.repository.UserRepository;
import io.javalin.Javalin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Javalin app = Javalin.create();

        AuthController ac = new AuthController();
        ac.mapEndpoints(app);

        ReimburseController reimburseController = new ReimburseController();
        reimburseController.mapEndpoints(app); //map the endpoints to the Javalin object

        app.start(8080);




    }
}


//    UserRepository ur = new UserRepository();
//    ReimbursementRepository rr = new ReimbursementRepository();
//
//
//
//        try {
//            User addedUser = ur.addUser(new User(0, "jane_doe", "tx123", "Jane", "Doe", -100));
//
//            System.out.println(addedUser);
//            User user = ur.getUserByUsernameAndPassword("jane_smith", "pass1234");
//            System.out.println(user);
//
//            List<Reimbursement> reimbursements = rr.getAllReimbursements();
//            System.out.println(reimbursements);
//
//                rr.reimbursementStatus(2,"Approved",2);
//                List<Reimbursement> reimbursements = rr.getAllReimbursements();
//        System.out.println(reimbursements);
//
//
//
//            for (int i = 0; i < reimbursements.size(); i++){
//                System.out.println(reimbursements.get(i));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }