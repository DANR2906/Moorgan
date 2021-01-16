package com.moorgan.Model;

import java.util.Date;
import java.util.List;

/**
 * Job model
 *
 * @author DANR
 */
public class Job {

    //
    private int id;

    //
    private String name;

    //
    private String creationDate;

    //
    private String endDate;

    //
    private long payment;

    //
    private int finished;

    //
    private User user;

    //
    private List<BalanceHistory> balanceHistory;

    //
    private List<Client> clients;

    //
    private List<Status> status;

    /**
     * Class constructor
     *
     * @param id
     * @param name
     * @param creationDate
     * @param endDate
     * @param payment
     * @param finished
     * @param user
     * @param balanceHistory
     * @param clients
     * @param status
     */
    public Job(int id, String name, String creationDate, String endDate, long payment,
               int finished, User user, List<BalanceHistory> balanceHistory,
               List<Client> clients, List<Status> status) {

        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.endDate = endDate;
        this.payment = payment;
        this.finished = finished;
        this.user = user;
        this.balanceHistory = balanceHistory;
        this.clients = clients;
        this.status = status;
    }

    /**
     * Class constructor
     *
     */
    public Job(){

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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public long getPayment() {
        return payment;
    }

    public void setPayment(long payment) {
        this.payment = payment;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<BalanceHistory> getBalanceHistory() {
        return balanceHistory;
    }

    public void setBalanceHistory(List<BalanceHistory> balanceHistory) {
        this.balanceHistory = balanceHistory;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Status> getStatus() {
        return status;
    }

    public void setStatus(List<Status> status) {
        this.status = status;
    }
}
