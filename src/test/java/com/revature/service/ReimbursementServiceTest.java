package com.revature.service;

import com.revature.exception.ReimbursementAlreadyUpdatedException;
import com.revature.exception.ReimbursementNotFoundException;
import com.revature.model.Reimbursement;
import com.revature.repository.ReimbursementRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.*;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReimbursementServiceTest {

    @Mock
    private ReimbursementRepository reimbursementRepository;

    @InjectMocks
    private ReimburseService rs;


    @Test
    public void testUpdateReimbursementIsNegative() throws ReimbursementNotFoundException, SQLException, ReimbursementAlreadyUpdatedException {

        //AA
        //Arrange
//        ReimburseService rs = new ReimburseService();
        //Act and Assert
        Assertions.assertThrows(IllegalArgumentException.class,() ->{
            rs.updateReimbursement(1,-10,100);
        });

    }

    @Test
    public void testUpdateReimbursementDoesNotExist () throws SQLException {
        //AA
        //Arrange
//        ReimburseService rs = new ReimburseService();
        when(reimbursementRepository.getReimbursementByID(eq(100))).thenReturn(null);
        //Act and Assert
        Assertions.assertThrows(ReimbursementNotFoundException.class, ()-> {
            rs.updateReimbursement(100,85, 2);
        });

    }

    @Test
    public void testForExistingUpdatedReimbursements() throws SQLException {
        //Arrange
        when(reimbursementRepository.getReimbursementByID(eq(1))).thenReturn(new Reimbursement(1, "Reimbursement 6", 50,10,2 ));

        //Act and Assert
        Assertions.assertThrows(ReimbursementAlreadyUpdatedException.class, ()->{
            rs.updateReimbursement(1, 80, 12);
        });

    }

    @Test
    public void ApprovedReimbursement() throws SQLException, ReimbursementNotFoundException, ReimbursementAlreadyUpdatedException {
        //Arrange
        when(reimbursementRepository.getReimbursementByID(eq(1))).thenReturn(new Reimbursement(1, "Reimbursement 8", 0, 4, 0));

        when(reimbursementRepository.reimbursementStatus(eq(1),eq(3),eq(2))).thenReturn(true);
        //Act
        boolean actual = rs.updateReimbursement(1, 3, 2);

        //Assert
        Assertions.assertTrue(actual);
    }
}
