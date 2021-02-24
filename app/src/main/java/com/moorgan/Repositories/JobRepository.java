package com.moorgan.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import androidx.annotation.NonNull;

import com.moorgan.Database.AdminDBHelper;
import com.moorgan.Database.DatabaseConnection;
import com.moorgan.IRepositories.IJobRepository;
import com.moorgan.Model.BalanceHistory;
import com.moorgan.Model.Client;
import com.moorgan.Model.Job;
import com.moorgan.Model.Status;
import com.moorgan.Model.User;
import com.moorgan.Model.Wallet;

import java.util.ArrayList;
import java.util.List;

public class JobRepository implements IJobRepository {

    private DatabaseConnection connection;

    private Context context;

    /**
     * Class constructor
     * @param context
     */
    public JobRepository(Context context) {
        this.context = context;

        this.connection = new DatabaseConnection(this.context);

    }

    @Override
    public boolean insert(@NonNull String name, @NonNull String creationDate, @NonNull String endDate,
                          long payment, int finished,@NonNull List<Integer> clientsID, int userID) {

        ContentValues values = new ContentValues();

        values.put("job_name", name);
        values.put("job_creation_date", creationDate);
        values.put("job_end_date", endDate);
        values.put("job_payment", payment);
        values.put("job_finished", finished);
        values.put("job_user", userID);

        try {
            this.connection.getWritableDatabase().insert(AdminDBHelper.MOORGAN_TABLE_JOB, null, values);
            this.connection.getWritableDatabase().close();

            for (Integer clientID : clientsID)
                insertClientJob(clientID, getLastID());

            insertUserJob(userID, getLastID());

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
                    delete(AdminDBHelper.MOORGAN_TABLE_JOB,
                            "job_id='" + id + "'",
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
    public List<Job> findAll() {
        List<Job> jobs = new ArrayList<>();

        Cursor cursor = this.connection.getReadableDatabase().
                query(AdminDBHelper.MOORGAN_TABLE_JOB,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

        if(cursor.moveToFirst()){
            do{
                jobs.add(new Job(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(5),
                        cursor.getLong(3), cursor.getInt(4),
                        cursor.getInt(6), findAllBalanceHistories(cursor), findAllClients(cursor),
                        findAllStatus(cursor)));
            }while(cursor.moveToNext());
        }
        this.connection.getReadableDatabase().close();

        return jobs;
    }

    @Override
    public Job findById(int id) {
        Job job = null;

        Cursor cursor = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_JOB + " WHERE job_id ='" + id + "'" , null);

        if(cursor.moveToFirst()){

            job = new Job(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(5),
                    cursor.getLong(3), cursor.getInt(4),
                    cursor.getInt(6), findAllBalanceHistories(cursor), findAllClients(cursor),
                    findAllStatus(cursor));

        }


        this.connection.getReadableDatabase().close();

        return job;
    }

    @Override
    public int getLastID() {
        int jobID = 0;
        Cursor cursor = this.connection.getReadableDatabase().
                query(AdminDBHelper.MOORGAN_TABLE_JOB,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

        if(cursor.moveToLast())
            jobID = cursor.getInt(0);

        this.connection.getReadableDatabase().close();

        return jobID;

    }

    @Override
    public boolean insertJobBalanceHistory(int balanceHistoryID, int jobID) {
        ContentValues values = new ContentValues();

        values.put("balanceHistory_id", balanceHistoryID);
        values.put("job_id", jobID);

        try {
            this.connection.getWritableDatabase().
                    insert(AdminDBHelper.MOORGAN_TABLE_BALANCE_HISTORY_JOB, null, values);
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
     * @param userID
     * @param jobID
     * @return
     */
    private boolean insertUserJob(int userID, int jobID) {
        ContentValues values = new ContentValues();

        values.put("user_id", userID);
        values.put("job_id", jobID);

        try {
            this.connection.getWritableDatabase().insert(AdminDBHelper.MOORGAN_TABLE_USER_JOB, null, values);
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
     * @param clientID
     * @param jobID
     * @return
     */
    private boolean insertClientJob(int clientID, int jobID) {
        ContentValues values = new ContentValues();

        values.put("client_id", clientID);
        values.put("job_id", jobID);

        try {
            this.connection.getWritableDatabase().insert(AdminDBHelper.MOORGAN_TABLE_CLIENT_JOB, null, values);
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
    private List<Integer> findAllBalanceHistories(Cursor cursor) {

        List<Integer> balanceHistoriesID = new ArrayList<>();

        Cursor cursor2 = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_BALANCE_HISTORY_JOB +
                        " WHERE job_id ='" + cursor.getInt(0) + "'", null);


        if (cursor2.moveToFirst()) {
            do {
                balanceHistoriesID.add(cursor2.getInt(0));
            } while (cursor2.moveToNext());
        }




        return balanceHistoriesID;
    }

    /**
     *
     * @param cursor
     * @return
     */
    private List<Integer> findAllClients(Cursor cursor) {

        List<Integer> clientsID = new ArrayList<>();

        Cursor cursor2 = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_CLIENT_JOB +
                        " WHERE job_id ='" + cursor.getInt(0) + "'", null);


        if (cursor2.moveToFirst()) {
            do {
                clientsID.add(cursor2.getInt(0));
            } while (cursor2.moveToNext());
        }




        return clientsID;
    }

    /**
     *
     * @param cursor
     * @return
     */
    private List<Status> findAllStatus(Cursor cursor) {

        List<Integer> statusesID = new ArrayList<>();
        List<Status> statuses = new ArrayList<>();

        Cursor cursor2 = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_JOB_STATUS +
                        " WHERE job_id ='" + cursor.getInt(0) + "'", null);


        if (cursor2.moveToFirst()) {
            do {
                statusesID.add(cursor2.getInt(1));
            } while (cursor2.moveToNext());
        }


        for (Integer statusID : statusesID)
            statuses.add((new StatusRepository(this.context)).findById(statusID));

        return statuses;
    }









}
