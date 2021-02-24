package com.moorgan.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import androidx.annotation.NonNull;

import com.moorgan.Database.AdminDBHelper;
import com.moorgan.Database.DatabaseConnection;
import com.moorgan.IRepositories.IBalanceHistoryRepository;
import com.moorgan.Model.BalanceHistory;
import com.moorgan.Model.Wallet;

import java.util.ArrayList;
import java.util.List;

public class BalanceHistoryRepository implements IBalanceHistoryRepository {

    private DatabaseConnection connection;

    private Context context;

    /**
     * Class constructor
     * @param context
     */
    public BalanceHistoryRepository(Context context) {
        this.context = context;

        this.connection = new DatabaseConnection(this.context);

    }


    @Override
    public boolean insert(long amount, @NonNull String entryDate, @NonNull String description,
                          int walletID, int type) {
        ContentValues values = new ContentValues();

        values.put("bal_amount", amount);
        values.put("bal_entry_date", entryDate);
        values.put("bal_description", description);
        values.put("bal_wallet", walletID);
        values.put("bal_type", type);

        try {
            this.connection.getWritableDatabase().insert(AdminDBHelper.MOORGAN_TABLE_BALANCE_HISTORY,
                                                            null, values);

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
     * @param id
     * @return
     */
    @Override
    public boolean deleteByID(int id) {

        try{
            this.connection.getWritableDatabase().
                    delete(AdminDBHelper.MOORGAN_TABLE_BALANCE_HISTORY,
                            "bal_id='" + id + "'",
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
    public List<BalanceHistory> findAll() {
        List<BalanceHistory> balanceHistories = new ArrayList<>();

        Cursor cursor = this.connection.getReadableDatabase().
                query(AdminDBHelper.MOORGAN_TABLE_BALANCE_HISTORY,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

        if(cursor.moveToFirst()){
            do{

                BalanceHistory balanceHistory = new BalanceHistory(cursor.getInt(0), cursor.getLong(1),
                        cursor.getString(2), cursor.getString(3),
                        cursor.getInt(4));

                balanceHistory.setType(cursor.getInt(5));

                balanceHistories.add(balanceHistory);


            }while(cursor.moveToNext());
        }

        cursor.close();
        this.connection.getReadableDatabase().close();

        return balanceHistories;
    }

    @Override
    public BalanceHistory findByID(int id) {
        BalanceHistory balanceHistory = null;

        Cursor cursor = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_BALANCE_HISTORY + " WHERE bal_id ='" + id + "'" , null);

        if(cursor.moveToFirst()){

            balanceHistory = new BalanceHistory(cursor.getInt(0), cursor.getLong(1),
                    cursor.getString(2), cursor.getString(3),
                    cursor.getInt(4));

            balanceHistory.setType(cursor.getInt(5));


        }


        cursor.close();
        this.connection.getReadableDatabase().close();


        return balanceHistory;
    }

    @Override
    public int getLastID() {
        int id = 0;
        Cursor cursor = this.connection.getReadableDatabase().
                query(AdminDBHelper.MOORGAN_TABLE_BALANCE_HISTORY,
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
     * @return
     */
    private int getJobID(int balanceHistoryID){
        int jobID = 0;

        Cursor cursor = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_BALANCE_HISTORY_JOB +
                        " WHERE balanceHistory_id ='" + balanceHistoryID + "'", null);


        if (cursor.moveToFirst()) {

            jobID = cursor.getInt(1);

            cursor.close();
            this.connection.getReadableDatabase().close();

            return jobID;

        }

        cursor.close();
        this.connection.getReadableDatabase().close();

        return  jobID;

    }

    /**
     *
     * @param balanceHistoryID
     * @return
     */
    private int getIncomeID(int balanceHistoryID){
        int incomeID = 0;

        Cursor cursor = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_BALANCE_HISTORY_INCOME +
                        " WHERE balanceHistory_id ='" + balanceHistoryID + "'", null);


        if (cursor.moveToFirst()) {

            incomeID = cursor.getInt(1);

            cursor.close();
            this.connection.getReadableDatabase().close();

            return incomeID;
        }

        cursor.close();
        this.connection.getReadableDatabase().close();

        return  incomeID;

    }

    /**
     *
     * @param balanceHistoryID
     * @return
     */
    private int getExpenseID(int balanceHistoryID){
        int expenseID = 0;

        Cursor cursor = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_BALANCE_HISTORY_EXPENSE +
                        " WHERE balanceHistory_id ='" + balanceHistoryID + "'", null);


        if (cursor.moveToFirst()) {

            expenseID = cursor.getInt(1);

            cursor.close();
            this.connection.getReadableDatabase().close();

            return expenseID;
        }

        cursor.close();
        this.connection.getReadableDatabase().close();

        return  expenseID;

    }

    /**
     *
     * @param balanceHistoryID
     * @return
     */
    private int getStatusID(int balanceHistoryID){
        int statusID = 0;

        Cursor cursor  = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_BALANCE_HISTORY_STATUS +
                        " WHERE balanceHistory_id ='" + balanceHistoryID + "'", null);


        if (cursor.moveToFirst()) {

            statusID = cursor.getInt(1);

            cursor.close();
            this.connection.getReadableDatabase().close();

            return statusID;
        }

        cursor.close();
        this.connection.getReadableDatabase().close();

        return  statusID;

    }

}
