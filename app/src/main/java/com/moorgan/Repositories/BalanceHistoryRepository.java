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
                          int walletID) {
        ContentValues values = new ContentValues();

        values.put("bal_amount", amount);
        values.put("bal_entry_date", entryDate);
        values.put("bal_description", description);
        values.put("bal_wallet", walletID);

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
                query(AdminDBHelper.MOORGAN_TABLE_WALLET,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

        if(cursor.moveToFirst()){
            do{

                List<Integer> balanceHistoryType = balanceHistoryType(cursor);
                switch (balanceHistoryType.get(0)){

                    case 0:
                        break;

                    case 1:
                        balanceHistories.add( new BalanceHistory(cursor.getInt(0), cursor.getLong(1),
                                cursor.getString(2), cursor.getString(3),
                                (new WalletRepository(this.context)).findByID(cursor.getInt(4)),
                                (new JobRepository(this.context)).findById(balanceHistoryType.get(1))));
                        break;

                    case 2:
                        balanceHistories.add(new BalanceHistory(cursor.getInt(0), cursor.getLong(1),
                                cursor.getString(2), cursor.getString(3),
                                (new WalletRepository(this.context)).findByID(cursor.getInt(4)),
                                (new IncomeRepository(this.context)).findById(balanceHistoryType.get(1))));
                        break;

                    case 3:
                        balanceHistories.add( new BalanceHistory(cursor.getInt(0), cursor.getLong(1),
                                cursor.getString(2), cursor.getString(3),
                                (new WalletRepository(this.context)).findByID(cursor.getInt(4)),
                                (new ExpenseRepository(this.context)).findById(balanceHistoryType.get(1))));
                        break;

                    case 4:
                        balanceHistories.add( new BalanceHistory(cursor.getInt(0), cursor.getLong(1),
                                cursor.getString(2), cursor.getString(3),
                                (new WalletRepository(this.context)).findByID(cursor.getInt(4)),
                                (new StatusRepository(this.context)).findById(balanceHistoryType.get(1))));
                        break;
                }

            }while(cursor.moveToNext());
        }
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

            List<Integer> balanceHistoryType = balanceHistoryType(cursor);
            switch (balanceHistoryType.get(0)){

                case 0:
                    break;

                case 1:
                    balanceHistory = new BalanceHistory(cursor.getInt(0), cursor.getLong(1),
                            cursor.getString(2), cursor.getString(3),
                            (new WalletRepository(this.context)).findByID(cursor.getInt(4)),
                            (new JobRepository(this.context)).findById(balanceHistoryType.get(1)));
                    break;

                case 2:
                    balanceHistory = new BalanceHistory(cursor.getInt(0), cursor.getLong(1),
                            cursor.getString(2), cursor.getString(3),
                            (new WalletRepository(this.context)).findByID(cursor.getInt(4)),
                            (new IncomeRepository(this.context)).findById(balanceHistoryType.get(1)));
                    break;

                case 3:
                    balanceHistory = new BalanceHistory(cursor.getInt(0), cursor.getLong(1),
                            cursor.getString(2), cursor.getString(3),
                            (new WalletRepository(this.context)).findByID(cursor.getInt(4)),
                            (new ExpenseRepository(this.context)).findById(balanceHistoryType.get(1)));
                    break;

                case 4:
                    balanceHistory = new BalanceHistory(cursor.getInt(0), cursor.getLong(1),
                            cursor.getString(2), cursor.getString(3),
                            (new WalletRepository(this.context)).findByID(cursor.getInt(4)),
                            (new StatusRepository(this.context)).findById(balanceHistoryType.get(1)));
                    break;
            }


        }



        this.connection.getReadableDatabase().close();

        return balanceHistory;
    }

    /**
     *
     * @param cursor
     * @return
     */
    private List<Integer> balanceHistoryType(Cursor cursor){
        List<Integer> typeAndID = new ArrayList<>();

        Cursor cursor2 = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_BALANCE_HISTORY_JOB +
                        " WHERE balanceHistory_id ='" + cursor.getInt(0) + "'", null);


        if (cursor2.moveToFirst()) {

            typeAndID.add(1);
            typeAndID.add(cursor2.getInt(1));

            this.connection.getReadableDatabase().close();

            return typeAndID;

        }

        cursor2 = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_BALANCE_HISTORY_INCOME +
                        " WHERE balanceHistory_id ='" + cursor.getInt(0) + "'", null);


        if (cursor2.moveToFirst()) {

            typeAndID.add(2);
            typeAndID.add(cursor2.getInt(1));

            this.connection.getReadableDatabase().close();

            return typeAndID;
        }

        cursor2 = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_BALANCE_HISTORY_EXPENSE +
                        " WHERE balanceHistory_id ='" + cursor.getInt(0) + "'", null);


        if (cursor2.moveToFirst()) {

            typeAndID.add(3);
            typeAndID.add(cursor2.getInt(1));

            this.connection.getReadableDatabase().close();

            return typeAndID;
        }

        cursor2 = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_BALANCE_HISTORY_STATUS +
                        " WHERE balanceHistory_id ='" + cursor.getInt(0) + "'", null);


        if (cursor2.moveToFirst()) {

            typeAndID.add(4);
            typeAndID.add(cursor2.getInt(1));

            this.connection.getReadableDatabase().close();

            return typeAndID;
        }

        typeAndID.add(0);

        return typeAndID;

    }
}
