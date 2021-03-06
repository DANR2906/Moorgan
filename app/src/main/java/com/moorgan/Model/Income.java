package com.moorgan.Model;

/**
 * Income model
 *
 * @author DANR
 */
public class Income {

    //
    private int id;

    //
    private String title;

    //
    private BalanceHistory balanceHistory;

    //
    private int wallet;

    /**
     * Class constructor
     *
     * @param id
     * @param title
     * @param balanceHistory
     * @param wallet
     */
    public Income(int id, String title, BalanceHistory balanceHistory, int wallet) {
        this.id = id;
        this.title = title;
        this.balanceHistory = balanceHistory;
        this.wallet = wallet;
    }

    /**
     * Class constructor
     * @param id
     * @param title
     */
    public Income(int id, String title) {
        this.id = id;
        this.title = title;
    }

    /**
     * Class constructor
     *
     */
    public Income(){

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

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }
}
