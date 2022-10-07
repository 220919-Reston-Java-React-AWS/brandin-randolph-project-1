package com.revature.controller;

import com.revature.exception.ReimbursementAlreadyUpdatedException;
import com.revature.exception.ReimbursementNotFoundException;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.ReimburseService;
import io.javalin.Javalin;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class ReimburseController {

    private ReimburseService reimburseService = new ReimburseService();

    public void mapEndpoints(Javalin app){

        app.get("/reimbursements", (ctx) -> {

            //must be logged in as a manager
            HttpSession httpSession = ctx.req.getSession();

            //retrieve the user attribute
            User user = (User) httpSession.getAttribute("user");
            if (user != null) {
                if (user.getRoleId() == 2) { //Check if they are a manager
                    List<Reimbursement> reimbursements = reimburseService.getAllReimbursements();

                    ctx.json(reimbursements);

                } else if (user.getRoleId()==1) {
                    int employeeId = user.getId();

                    List<Reimbursement> reimbursements = reimburseService.getAllReimbursementsForEmployee(employeeId);

                    ctx.json(reimbursements);

                } else {
                    ctx.result("Your are logged in, but your not a Manager");
                    ctx.status(401);
                }

            }
            else {
                ctx.result("You are not logged in");
                ctx.status(401);
            }
        });


        //Manager view Reimbursements, and set approval
        app.patch("/reimbursements/{reimbursementId}", (ctx) ->{
            HttpSession httpSession = ctx.req.getSession();
            User user = (User) httpSession.getAttribute("user");

            if (user != null){ //check if person is actually logged in

                if (user.getRoleId() ==2) { //check if user is manager

                    int managerId = user.getId(); //The person logged in is the Manager
                    int reimbursementId = Integer.parseInt(ctx.pathParam("reimbursementId"));

                    Reimbursement r = ctx.bodyAsClass(Reimbursement.class); //JSON: {status: 100}
                    int ticketStatus = r.getTicketStatus();

                    try {
                        reimburseService.updateReimbursement(reimbursementId, ticketStatus, managerId);
                        ctx.result("Reimbursement status updated");
                    } catch (ReimbursementAlreadyUpdatedException | IllegalArgumentException e){
                        ctx.result(e.getMessage());
                        ctx.status(400);
                    }catch (ReimbursementNotFoundException e) {
                        ctx.result(e.getMessage());
                        ctx.status(404);
                    }

                } else {
                    ctx.result("You are logged in, not as a manager");
                    ctx.status(401);
                }
            }else {
                ctx.result("You are not logged in");
                ctx.status(401);
            }

        });

        //Reimbursement request
        app.post("/{userID}/reimbursements", (ctx) -> {
            HttpSession httpSession = ctx.req.getSession();
            User user = (User) httpSession.getAttribute("user");
            //Reimbursement rr = ctx.bodyAsClass(Reimbursement.class);

            if (user != null){//check if person is actually logged in
                ctx.result("You are not logged in");
                ctx.status(401);
//                if (user.getRoleId() ==1) { //check if user is employee
//                    int userId = Integer.parseInt(ctx.pathParam("userId"));
//                    if (user.getId()==userId){
//                        try{
                            //r.setEmployeeId(user.getId());
//                            r = reimburseService.addNewReimbursement(r);
//
//                            ctx.json(r);
//                            ctx.status(201);
//                        }catch (SQLException e){
//                            ctx.result(e.getMessage());
//                            ctx.status(400);
//
//                        }
//                    }else{
//                        ctx.result("You are not the right user.");
//                        ctx.status(401);
//                    }
//
//                }else{
//                    ctx.result("Your are logged in as Manager");
//                    ctx.status(401);
                //}

            }else {
                ctx.result("You are not logged in");
                ctx.status(401);
            }

        });
    }
}





