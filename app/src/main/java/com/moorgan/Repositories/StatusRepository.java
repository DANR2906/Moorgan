package com.moorgan.Repositories;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import androidx.annotation.NonNull;

import com.moorgan.Database.AdminDBHelper;
import com.moorgan.Database.DatabaseConnection;
import com.moorgan.IRepositories.IStatusRepository;
import com.moorgan.Model.BalanceHistory;
import com.moorgan.Model.Status;
import com.moorgan.Model.StatusTask;

import java.util.ArrayList;
import java.util.List;

public class StatusRepository implements IStatusRepository {


    private DatabaseConnection connection;

    private Context context;

    /**
     * Class constructor
     * @param context
     */
    public StatusRepository(Context context) {
        this.context = context;

        this.connection = new DatabaseConnection(this.context);

    }


    @Override
    public boolean insert(@NonNull String name, long advancePayment, int approve, int jobID) {

        ContentValues values = new ContentValues();

        values.put("sta_name", name);
        values.put("sta_advance_payment", advancePayment);
        values.put("sta_approve", approve);

        try {
            this.connection.getWritableDatabase().insert(AdminDBHelper.MOORGAN_TABLE_STATUS, null, values);
            this.connection.getWritableDatabase().close();

            insertJobStatus(jobID, getLastID());
            //insertStatusBalanceHistory(balanceHistoryID, getLastID());

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
                    delete(AdminDBHelper.MOORGAN_TABLE_STATUS,
                            "sta_id='" + id + "'",
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
    public List<Status> findAll() {
        List<Status> statuses = new ArrayList<>();

        Cursor cursor = this.connection.getReadableDatabase().
                query(AdminDBHelper.MOORGAN_TABLE_STATUS,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

        if(cursor.moveToFirst()){
            do{
                statuses.add
                        (new Status(cursor.getInt(0), cursor.getString(1),
                                        cursor.getLong(2), cursor.getInt(3),
                                        findAllTasks(cursor), findAllBalanceHistories(cursor)));

            }while(cursor.moveToNext());
        }
        this.connection.getReadableDatabase().close();

        return statuses;
    }

    @Override
    public Status findById(int id) {

        Status status = null;

        Cursor cursor = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_STATUS + " WHERE sta_id ='" + id + "'" , null);

        if(cursor.moveToFirst()){

            status = new Status(cursor.getInt(0), cursor.getString(1),
                                cursor.getLong(2), cursor.getInt(3),
                                findAllTasks(cursor), findAllBalanceHistories(cursor));
        }



        this.connection.getReadableDatabase().close();

        return status;
    }

    @Override
    public int getLastID() {
        int id = 0;
        Cursor cursor = this.connection.getReadableDatabase().
                query(AdminDBHelper.MOORGAN_TABLE_STATUS,
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
     * @param statusID
     * @return
     */
    @Override
    public boolean insertStatusBalanceHistory(int balanceHistoryID, int statusID) {
        ContentValues values = new ContentValues();

        values.put("balanceHistory_id", balanceHistoryID);
        values.put("status_id", statusID);

        try {
            this.connection.getWritableDatabase().
                    insert(AdminDBHelper.MOORGAN_TABLE_BALANCE_HISTORY_STATUS, null, values);
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
     * @param jobID
     * @param statusID
     * @return
     */
    private boolean insertJobStatus(int jobID, int statusID) {
        ContentValues values = new ContentValues();

        values.put("job_id", jobID);
        values.put("status_id", statusID);

        try {
            this.connection.getWritableDatabase().insert(AdminDBHelper.MOORGAN_TABLE_JOB_STATUS, null, values);
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
    private List<StatusTask> findAllTasks(Cursor cursor){
        List<Integer> tasksID = new ArrayList<>();
        List<StatusTask> statusTasks = new ArrayList<>();

        Cursor cursor2 = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_STATUS_TASK +
                        " WHERE status_id ='" + cursor.getInt(0) + "'" , null);


        if(cursor2.moveToFirst()){
            do{
                tasksID.add(cursor2.getInt(1));
            }while(cursor2.moveToNext());
        }

        this.connection.getReadableDatabase().close();

        for(int x = 0; x < tasksID.size(); x++)
            statusTasks.add((new StatusTaskRepository(this.context)).findById(tasksID.get(x)));

        return statusTasks;
    }

    /**
     *
     * @param cursor
     * @return
     */
    private List<BalanceHistory> findAllBalanceHistories(Cursor cursor){

        List<Integer> balanceHistoriesID = new ArrayList<>();
        List<BalanceHistory> balanceHistories = new ArrayList<>();

        Cursor cursor2 = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_BALANCE_HISTORY_STATUS +
                        " WHERE status_id ='" + cursor.getInt(0) + "'" , null);


        if(cursor2.moveToFirst()){
            do{
                balanceHistoriesID.add(cursor2.getInt(0));
            }while(cursor2.moveToNext());
        }


        for (Integer balanceHistoryID : balanceHistoriesID)
            balanceHistories.add((new BalanceHistoryRepository(this.context)).
                                 findByID(balanceHistoryID));

        return balanceHistories;
    }



}
