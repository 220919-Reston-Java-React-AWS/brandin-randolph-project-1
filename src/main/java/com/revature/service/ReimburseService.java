package com.revature.service;

import com.revature.exception.ReimbursementAlreadyUpdatedException;
import com.revature.exception.ReimbursementNotFoundException;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.repository.ReimbursementRepository;

import java.sql.SQLException;
import java.util.List;


public class ReimburseService {


    private ReimbursementRepository reimbursementRepository = new ReimbursementRepository();

    public List<Reimbursement> getAllReimbursements() throws SQLException {
       return reimbursementRepository.getAllReimbursements();
    }
    public List<Reimbursement> getAllReimbursementsForEmployee(int employeeId) throws SQLException {
       return reimbursementRepository.getAllReimbursementsForEmployee(employeeId);
    }

    public boolean updateReimbursement(int reimbursementId, int ticketStatus, int managerId ) throws SQLException, ReimbursementNotFoundException, ReimbursementAlreadyUpdatedException {
       //Checks if status is negative
        if(ticketStatus < 0) {
            throw new IllegalArgumentException("Status assigned must be 0 or higher"); //built in unchecked exception
        }
        //check if assignment does not exist
        Reimbursement reimbursement = (reimbursementRepository.getReimbursementByID(reimbursementId));
        if (reimbursement == null) {
            throw new ReimbursementNotFoundException("Reimbursement with id" + reimbursementId + "was not found");
        }
        if ( reimbursement.getTicketStatus() != 0 && reimbursement.getManagerId() !=0) {  //Not graded yet
            throw new ReimbursementAlreadyUpdatedException("Reimbursement with id" + reimbursementId + "has already been updated.");

        }
        //approving reimbursement
        return reimbursementRepository.reimbursementStatus(reimbursementId, ticketStatus, managerId);

    }


//      public Reimbursement addReimbursement(Reimbursement reimbursement) throws SQLException {
//
//          if(reimbursementRepository.reimbursementRequest(reimbursement.getTicketDescrip(),reimbursement.getEmployeeId())){
//              throw new SQLException("Reimbursement " + reimbursement.getEmployeeId() + " already added");
//          }
//
//          Reimbursement addedReimbursement = reimbursementRepository.reimbursementRequest(reimbursement);
//          return addedReimbursement;
//
//
   // }

}
