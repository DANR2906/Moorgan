package com.moorgan.Model;

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
    private String entryDate;

    //
    private String description;

    //
    private int wallet;

    private int type;

    /**
     * Class constructor
     *
     * @param id
     * @param amount
     * @param entryDate
     * @param description
     */
    public BalanceHistory(int id, long amount, String entryDate, String description,
                          int wallet, int type) {
        this.id = id;
        this.amount = amount;
        this.entryDate = entryDate;
        this.description = description;
        this.wallet = wallet;
        this.type = type;
    }

    /**
     * Class constructor
     * @param id
     * @param amount
     * @param entryDate
     * @param description
     * @param wallet
     */
    public BalanceHistory(int id, long amount, String entryDate, String description, int wallet) {
        this.id = id;
        this.amount = amount;
        this.entryDate = entryDate;
        this.description = description;
        this.wallet = wallet;
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

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
