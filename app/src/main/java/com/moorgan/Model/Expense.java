package com.moorgan.Model;

/**
 * Expense model
 *
 * @author DANR
 */
public class Expense {

    //
    private int id;

    //
    private String title;

    //
    private BalanceHistory balanceHistory;

    //
    private Wallet wallet;

    /**
     * Class constructor
     *
     * @param id
     * @param title
     * @param balanceHistory
     * @param wallet
     */
    public Expense(int id, String title, BalanceHistory balanceHistory, Wallet wallet) {
        this.id = id;
        this.title = title;
        this.balanceHistory = balanceHistory;
        this.wallet = wallet;
    }

    /**
     * Class constructor
     *
     */
    public Expense(){

    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BalanceHistory getBalanceHistory() {
        return balanceHistory;
    }

    public void setBalanceHistory(BalanceHistory balanceHistory) {
        this.balanceHistory = balanceHistory;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
