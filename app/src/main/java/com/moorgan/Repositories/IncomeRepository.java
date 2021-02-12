package com.moorgan.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import androidx.annotation.NonNull;

import com.moorgan.Database.AdminDBHelper;
import com.moorgan.Database.DatabaseConnection;
import com.moorgan.IRepositories.IIncomeRepository;
import com.moorgan.Model.BalanceHistory;
import com.moorgan.Model.Income;
import com.moorgan.Model.Wallet;

import java.util.ArrayList;
import java.util.List;

public class IncomeRepository implements IIncomeRepository {

    private DatabaseConnection connection;

    private Context context;

    /**
     * Class constructor
     * @param context
     */
    public IncomeRepository(Context context) {
        this.context = context;

        this.connection = new DatabaseConnection(this.context);

    }

    @Override
    public boolean insert(@NonNull String title, int walletID, int balanceHistoryID) {

        ContentValues values = new ContentValues();

        values.put("inc_title", title);
        values.put("inc_wallet", walletID);

        try {
            this.connection.getWritableDatabase().insert(AdminDBHelper.MOORGAN_TABLE_INCOME, null, values);
            this.connection.getWritableDatabase().close();

            insertIncomeBalanceHistory(balanceHistoryID, getLastID());
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
                    delete(AdminDBHelper.MOORGAN_TABLE_INCOME,
                            "inc_id='" + id + "'",
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
    public List<Income> findAll() {
        List<Income> incomes = new ArrayList<>();
        List<Integer> walletsID = new ArrayList<>();

        Cursor cursor = this.connection.getReadableDatabase().
                query(AdminDBHelper.MOORGAN_TABLE_INCOME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

        if(cursor.moveToFirst()){
            do{
                incomes.add(new Income(cursor.getInt(0), cursor.getString(1)));
                walletsID.add(cursor.getInt(2));
            }while(cursor.moveToNext());
        }

        cursor.close();
        this.connection.getReadableDatabase().close();


        for (int x = 0; x < incomes.size(); x++){
            incomes.get(x).setBalanceHistory(findBalanceHistory(incomes.get(x).getId()));
            incomes.get(x).setWallet(walletsID.get(x));
        }

        return incomes;
    }

    @Override
    public Income findById(int id) {

        Income income = null;

        Cursor cursor = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_INCOME + " WHERE inc_id ='" + id + "'" , null);

        if(cursor.moveToFirst()){
            income = new Income(cursor.getInt(0), cursor.getString(1));
            income.setWallet(cursor.getInt(2));
        }

        cursor.close();
        this.connection.getReadableDatabase().close();

        income.setBalanceHistory(findBalanceHistory(income.getId()));



        return income;
    }


    @Override
    public int getLastID() {
        int id = 0;
        Cursor cursor = this.connection.getReadableDatabase().
                query(AdminDBHelper.MOORGAN_TABLE_INCOME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

        if(cursor.moveToLast())
            id = cursor.getInt(0);

        cursor.close();
        this.connection.getReadableDatabase().close();

        return id;

    }

    /**
     *
     * @param balanceHistoryID
     * @param incomeID
     * @return
     */
    private boolean insertIncomeBalanceHistory(int balanceHistoryID, int incomeID) {
        ContentValues values = new ContentValues();

        values.put("balanceHistory_id", balanceHistoryID);
        values.put("income_id", incomeID);

        try {
            this.connection.getWritableDatabase().
                    insert(AdminDBHelper.MOORGAN_TABLE_BALANCE_HISTORY_INCOME, null, values);
            this.connection.getWritableDatabase().close();
            return true;
        }catch (Exception ex) {
            Log.e("Insertion DB error", ex.getMessage());
            this.connection.getWritableDatabase().close();
            return false;
        }
    }

    /**
     *
     * @param incomeID
     * @return
     */
    private BalanceHistory findBalanceHistory(int incomeID) {

        int balanceHistoryID = 0;


        Cursor cursor = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_BALANCE_HISTORY_INCOME +
                        " WHERE income_id ='" + incomeID + "'", null);


        if (cursor.moveToFirst())
            balanceHistoryID = cursor.getInt(0);

        cursor.close();
        this.connection.getReadableDatabase().close();

        return (new BalanceHistoryRepository(this.context)).findByID(balanceHistoryID);
    }
}
