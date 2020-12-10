package com.moorgan.model;

import java.util.List;

/**
 * Status model
 *
 * @author DANR
 */
public class Status {

    //
    private int id;

    //
    private String name;

    //
    private long advancePayment;

    //
    private boolean approved;

    //
    private List<StatusTask> statusTasks;

    //
    private List<BalanceHistory> balanceHistory;

    /**
     * Class constructor
     *
     * @param id
     * @param name
     * @param advancePayment
     * @param approved
     * @param statusTasks
     * @param balanceHistory
     */
    public Status(int id, String name, long advancePayment, boolean approved, List<StatusTask> statusTasks,
                  List<BalanceHistory> balanceHistory) {

        this.id = id;
        this.name = name;
        this.advancePayment = advancePayment;
        this.approved = approved;
        this.statusTasks = statusTasks;
        this.balanceHistory = balanceHistory;
    }

    /**
     * Class constructor
     *
     */
    public Status(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAdvancePayment() {
        return advancePayment;
    }

    public void setAdvancePayment(long advancePayment) {
        this.advancePayment = advancePayment;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public List<StatusTask> getStatusTasks() {
        return statusTasks;
    }

    public void setStatusTasks(List<StatusTask> statusTasks) {
        this.statusTasks = statusTasks;
    }

    public List<BalanceHistory> getBalanceHistory() {
        return balanceHistory;
    }

    public void setBalanceHistory(List<BalanceHistory> balanceHistory) {
        this.balanceHistory = balanceHistory;
    }
}
