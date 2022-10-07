package com.revature.model;

import java.util.Objects;

public class Reimbursement {

    private int id;
    private String ticketDescrip;
    private int ticketStatus;
    private int employeeId;
    private int managerId;

    public Reimbursement(){
    }

    public Reimbursement(int id, String ticketDescrip, int ticketStatus, int employeeId, int managerId) {
        this.id = id;
        this.ticketDescrip = ticketDescrip;
        this.ticketStatus = ticketStatus;
        this.employeeId = employeeId;
        this.managerId = managerId;
    }

    public Reimbursement(int id, String ticketNumber, int ticketStatus, int employeeId) {

    }

    public Reimbursement(int id, String ticketDescrip, int employeeId) {
        this.id = id;
        this.ticketDescrip = ticketDescrip;
        this.employeeId = employeeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTicketDescrip() { return ticketDescrip; }

    public void setTicketDescrip(String ticketDescrip) {
        this.ticketDescrip = ticketDescrip;
    }

    public int getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(int ticketStatus) { this.ticketStatus = ticketStatus; }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return id == that.id && ticketStatus == that.ticketStatus && employeeId == that.employeeId && managerId == that.managerId && Objects.equals(ticketDescrip, that.ticketDescrip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticketDescrip, ticketStatus, employeeId, managerId);
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", ticketNumber='" + ticketDescrip + '\'' +
                ", ticketStatus=" + ticketStatus +
                ", employeeId=" + employeeId +
                ", managerId=" + managerId +
                '}';
    }
}

