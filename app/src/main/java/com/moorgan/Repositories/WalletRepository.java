package com.moorgan.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import androidx.annotation.NonNull;

import com.moorgan.Database.AdminDBHelper;
import com.moorgan.Database.DatabaseConnection;
import com.moorgan.IRepositories.IWalletRepository;
import com.moorgan.Model.BalanceHistory;
import com.moorgan.Model.Expense;
import com.moorgan.Model.Income;
import com.moorgan.Model.Wallet;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.AEADBadTagException;

public class WalletRepository implements IWalletRepository {


    private DatabaseConnection connection;

    private Context context;

    /**
     * Class constructor
     * @param context
     */
    public WalletRepository(Context context) {
        this.context = context;

        this.connection = new DatabaseConnection(this.context);

    }

    @Override
    public boolean insert(int id, long balance) {

        ContentValues values = new ContentValues();

        values.put("wal_id", id);
        values.put("wal_balance", balance);

        try {
            this.connection.getWritableDatabase().insert(AdminDBHelper.MOORGAN_TABLE_WALLET, null, values);
            this.connection.getWritableDatabase().close();
            return true;
        }catch (Exception ex) {
            Log.e("Insertion DB error", ex.getMessage());
            this.connection.getWritableDatabase().close();
            return false;
        }
    }

    @Override
    public boolean deleteByID(int id) {

        try{
            this.connection.getWritableDatabase().
                            delete(AdminDBHelper.MOORGAN_TABLE_WALLET,
                                    "wal_id='" + id + "'",
                                    null);

            this.connection.getWritableDatabase().close();
            return true;

        }catch (Exception ex){

            Log.e("Deleting DB error", ex.getMessage());

            this.connection.getWritableDatabase().close();
            return false;
        }
    }

    @Override
    public Wallet findByID(int id) {
        Wallet wallet = new Wallet();

        Cursor cursor = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_WALLET + " WHERE wal_id ='" + id + "'" , null);

        if(cursor.moveToFirst()){

            wallet = new Wallet(cursor.getInt(0), cursor.getLong(1));

        }

        cursor.close();
        this.connection.getReadableDatabase().close();


        wallet.setBalanceHistory(findAllBalanceHistories(id));
        wallet.setIncomes(findAllIncomes(id));
        wallet.setExpenses(findAllExpenses(id));


        return wallet;
    }

    @Override
    public List<Wallet> findAll() {
        List<Wallet> wallets = new ArrayList<>();

        Cursor cursor = this.connection.getReadableDatabase().
                query(AdminDBHelper.MOORGAN_TABLE_WALLET,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

        if(cursor.moveToFirst()){
            do{


                wallets.add(new Wallet(cursor.getInt(0), cursor.getLong(1),
                                        findAllBalanceHistories(cursor.getInt(0)),
                                        findAllIncomes(cursor.getInt(0)),
                                        findAllExpenses(cursor.getInt(0))));

            }while(cursor.moveToNext());
        }

        cursor.close();
        this.connection.getReadableDatabase().close();

        return wallets;
    }

    @Override
    public boolean updateBalance(int walletID, long balance) {

        ContentValues values = new ContentValues();
        values.put("wal_balance", balance);

        try{
            this.connection.getWritableDatabase()
                    .update(AdminDBHelper.MOORGAN_TABLE_WALLET, values, "wal_id='" + walletID + "'", null);

            this.connection.getWritableDatabase().close();
            return true;
        }catch (Exception ex){
            Log.e("Update DB error: ", ex.getMessage());
            this.connection.getWritableDatabase().close();
            return false;
        }
    }

    /**
     *
     * @param walletID
     * @return
     */
    private List<BalanceHistory> findAllBalanceHistories(int walletID){
        List<BalanceHistory> balanceHistories = new ArrayList<>();


        for(BalanceHistory balanceHistory : (new BalanceHistoryRepository(this.context)).findAll() ){
            if(balanceHistory.getWallet() == walletID)
                balanceHistories.add(balanceHistory);
        }



        return balanceHistories;
    }


    /**
     *
     * @param walletID
     * @return
     */
    private List<Income> findAllIncomes(int walletID){
        List<Income> incomes = new ArrayList<>();


        for(Income income : (new IncomeRepository(this.context)).findAll() )
            if(income.getWallet() == walletID)
                incomes.add(income);

        return incomes;
    }

    /**
     *
     * @param walletID
     * @return
     */
    private List<Expense> findAllExpenses(int walletID){
        List<Expense> expenses = new ArrayList<>();


        for(Expense expense : (new ExpenseRepository(this.context)).findAll() )
            if(expense.getWallet() == walletID)
                expenses.add(expense);

        return expenses;
    }
}
