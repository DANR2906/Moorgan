package com.moorgan.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import androidx.annotation.NonNull;

import com.moorgan.Database.AdminDBHelper;
import com.moorgan.Database.DatabaseConnection;
import com.moorgan.IRepositories.IExpenseRepository;
import com.moorgan.Model.BalanceHistory;
import com.moorgan.Model.Expense;
import com.moorgan.Model.Income;

import java.util.ArrayList;
import java.util.List;

public class ExpenseRepository implements IExpenseRepository {

    private DatabaseConnection connection;

    private Context context;

    /**
     * Class constructor
     * @param context
     */
    public ExpenseRepository(Context context) {
        this.context = context;

        this.connection = new DatabaseConnection(this.context);

    }

    @Override
    public boolean insert(@NonNull String title, int walletID, int balanceHistoryID) {

        ContentValues values = new ContentValues();

        values.put("exp_title", title);
        values.put("exp_wallet", walletID);

        try {
            this.connection.getWritableDatabase().insert(AdminDBHelper.MOORGAN_TABLE_EXPENSE, null, values);
            this.connection.getWritableDatabase().close();

            insertExpenseBalanceHistory(balanceHistoryID, getLastID());

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
                    delete(AdminDBHelper.MOORGAN_TABLE_EXPENSE,
                            "exp_id='" + id + "'",
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
    public List<Expense> findAll() {
        List<Expense> expenses = new ArrayList<>();

        Cursor cursor = this.connection.getReadableDatabase().
                query(AdminDBHelper.MOORGAN_TABLE_EXPENSE,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

        if(cursor.moveToFirst()){
            do{
                expenses.add(new Expense(cursor.getInt(0), cursor.getString(1),
                        findBalanceHistory(cursor),
                        (new WalletRepository(this.context).findByID(cursor.getInt(2)))));

            }while(cursor.moveToNext());
        }
        this.connection.getReadableDatabase().close();

        return expenses;
    }

    @Override
    public Expense findById(int id) {

        Expense expense = null;

        Cursor cursor = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_EXPENSE + " WHERE exp_id ='" + id + "'" , null);

        if(cursor.moveToFirst())
            expense = new Expense(cursor.getInt(0), cursor.getString(1),
                    findBalanceHistory(cursor),
                    (new WalletRepository(this.context).findByID(cursor.getInt(2))));;


        this.connection.getReadableDatabase().close();

        return expense;
    }

    @Override
    public int getLastID() {
        int id = 0;
        Cursor cursor = this.connection.getReadableDatabase().
                query(AdminDBHelper.MOORGAN_TABLE_EXPENSE,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

        if(cursor.moveToLast())
            id = cursor.getInt(0);

        this.connection.getReadableDatabase().close();

        return id;

    }

    /**
     *
     * @param balanceHistoryID
     * @param expenseID
     * @return
     */
    private boolean insertExpenseBalanceHistory(int balanceHistoryID, int expenseID) {
        ContentValues values = new ContentValues();

        values.put("balanceHistory_id", balanceHistoryID);
        values.put("expense_id", expenseID);

        try {
            this.connection.getWritableDatabase().
                    insert(AdminDBHelper.MOORGAN_TABLE_BALANCE_HISTORY_EXPENSE, null, values);
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
     * @param cursor
     * @return
     */
    private BalanceHistory findBalanceHistory(Cursor cursor) {

        int balanceHistoryID = 0;


        Cursor cursor2 = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_BALANCE_HISTORY_EXPENSE +
                        " WHERE expense_id ='" + cursor.getInt(0) + "'", null);


        if (cursor2.moveToFirst())
            balanceHistoryID = cursor2.getInt(0);


        return (new BalanceHistoryRepository(this.context)).findByID(balanceHistoryID);
    }
}
