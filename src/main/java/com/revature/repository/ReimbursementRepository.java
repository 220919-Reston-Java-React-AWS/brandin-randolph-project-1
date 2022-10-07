package com.revature.repository;

import com.revature.model.Reimbursement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementRepository {

    public List<Reimbursement> getAllReimbursements() throws SQLException {

        try (Connection connectionObject = ConnectionFactory.createConnection()){

            List<Reimbursement> reimbursements = new ArrayList<>();
            String sql = "SELECT * FROM reimbursements";

            Statement stmt = connectionObject.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            //Iterate through result set
            while (rs.next()) {
                int id = rs.getInt("id");
                String ticketNumber = rs.getString("ticket_descrip");
                int ticketStatus = rs.getInt("ticket_status");
                int employeeId = rs.getInt("employee_id");
                int managerId = rs.getInt("manager_id");

                Reimbursement reimbursement = new Reimbursement(id,ticketNumber, ticketStatus, employeeId, managerId);
                reimbursements.add(reimbursement); //add reimbursement to list
            }

            return reimbursements;
        }
    }

    public List<Reimbursement> getAllReimbursementsForEmployee(int employeeId) throws SQLException {

        try (Connection connectionObject = ConnectionFactory.createConnection()) {

            List<Reimbursement> reimbursements = new ArrayList<>();
            String sql = "SELECT * FROM reimbursements WHERE employee_id = ?";

            PreparedStatement pstmt = connectionObject.prepareStatement(sql);

             pstmt.setInt(1, employeeId);
             ResultSet rs = pstmt.executeQuery();

            //Iterate through result set
            while (rs.next()) {
                int id = rs.getInt("id");
                String ticketNumber = rs.getString("ticket_descrip");
                int ticketStatus = rs.getInt("ticket_status");
                int eId = rs.getInt("employee_id");
                int managerId = rs.getInt("manager_id");

                Reimbursement reimbursement = new Reimbursement(id, ticketNumber, ticketStatus, eId, managerId);
                reimbursements.add(reimbursement);
            }

            return reimbursements;
        }
    }

    public boolean reimbursementStatus (int reimbursementId, int ticketStatus, int managerId ) throws SQLException {
        try (Connection connectionObj = ConnectionFactory.createConnection()) {
            String sql = "UPDATE reimbursements SET ticket_status = ?, manager_id = ? WHERE id = ?";

            PreparedStatement pstmt = connectionObj.prepareStatement(sql);
            pstmt.setInt(1, ticketStatus);
            pstmt.setInt(2, managerId);
            pstmt.setInt(3, reimbursementId);

            int numberOfRecordsUpdated = pstmt.executeUpdate();
            return numberOfRecordsUpdated == 1;
        }

    }

    public Reimbursement getReimbursementByID(int id) throws SQLException {
        try (Connection connectionObj = ConnectionFactory.createConnection()) {
            String sql = "SELECT * FROM reimbursements WHERE id = ?";

            PreparedStatement pstmt = connectionObj.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                int reimbursementId = rs.getInt("id");
                String ticketNumber = rs.getString("ticket_descrip");
                int status = rs.getInt("ticket_status");
                int employeeId = rs.getInt("employee_id");
                int managerId = rs.getInt("manager_id");

                return new Reimbursement(reimbursementId,ticketNumber, status, employeeId, managerId);

            }else {
                return null;
            }
        }
    }

    //reimbursement request sent by employee

//    public Reimbursement reimbursementRequest (Reimbursement reimbursement) throws SQLException {
//
//        try(Connection connectionObject = ConnectionFactory.createConnection()){
//            String sql = "INSERT into reimbursements ( ticket_descrip, employee_id) values (?, ?)";
//            PreparedStatement pstmt = connectionObject.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//
//            pstmt.setString(1,reimbursement.getTicketDescrip());
//            pstmt.setInt(2,reimbursement.getEmployeeId());
//
//            int numberOfReimbursementsAdded =  pstmt.executeUpdate();//applies to insert, update, and delete
//
//            ResultSet rs = pstmt.getGeneratedKeys(); //creates a temporary table for the automatically generated primaryKey
//            rs.next(); //retrieves first row
//
//            int id = rs.getInt(1); //retrieves first column
//
//                return new Reimbursement(id, reimbursement.getTicketDescrip(), reimbursement.getEmployeeId());
//
//
//            }
//        }


//    public boolean reimbursementRequest(String ticketDescrip, int employeeId) {
//        return false;
    //}
}



