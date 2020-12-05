package com.moorgan.model;

/**
 * Wallet model
 *
 * @author DANR
 */
public class Wallet {

    //
    private int id;

    //
    private long balance;

    //
    private List<BalanceHistory> balanceHistory;

    //
    private List<Income> incomes;

    //
    private List<Expense> expenses;


    /**
     * Class constructor
     *
     * @param id
     * @param balance
     * @param balanceHistory
     * @param incomes
     * @param expenses
     */
    public Wallet(int id, long balance, List<BalanceHistory> balanceHistory, List<Income> incomes,
                  List<Expense> expenses) {

        this.id = id;
        this.balance = balance;
        this.balanceHistory = balanceHistory;
        this.incomes = incomes;
        this.expenses = expenses;
    }

    /**
     * Class constructor
     *
     */
    public Wallet(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public List<BalanceHistory> getBalanceHistory() {
        return balanceHistory;
    }

    public void setBalanceHistory(List<BalanceHistory> balanceHistory) {
        this.balanceHistory = balanceHistory;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }
}
