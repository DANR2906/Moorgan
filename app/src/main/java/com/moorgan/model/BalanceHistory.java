package com.moorgan.model;

import java.util.Date;

/**
 * Balance history model
 *
 * @author DANR
 */
public class BalanceHistory {

    //
    private int id;

    //
    private long amount;

    //
    private Date entryDate;

    //
    private String description;

    //
    private Wallet wallet;

    //
    private Job job;

    //
    private Income income;

    //
    private Expense expense;

    //
    private Status status;

    /**
     * Class constructor
     *
     * @param id
     * @param amount
     * @param entryDate
     * @param description
     * @param job
     * @param income
     * @param expense
     * @param status
     */
    public BalanceHistory(int id, long amount, Date entryDate, String description, Wallet wallet,
                          Job job, Income income, Expense expense, Status status) {

        this.id = id;
        this.amount = amount;
        this.entryDate = entryDate;
        this.description = description;
        this.wallet = wallet;
        this.job = job;
        this.income = income;
        this.expense = expense;
        this.status = status;
    }

    /**
     * Class constructor
     *
     */
    public BalanceHistory(){


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
