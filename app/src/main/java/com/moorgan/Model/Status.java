package com.moorgan.Model;

import java.util.ArrayList;
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
    private int approved;

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
    public Status(int id, String name, long advancePayment, int approved, List<StatusTask> statusTasks,
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

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
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
