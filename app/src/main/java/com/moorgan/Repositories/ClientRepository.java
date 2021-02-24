package com.moorgan.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import androidx.annotation.NonNull;

import com.moorgan.Database.AdminDBHelper;
import com.moorgan.Database.DatabaseConnection;
import com.moorgan.IRepositories.IClientRepository;
import com.moorgan.Model.Client;
import com.moorgan.Model.Job;
import com.moorgan.Model.Status;
import com.moorgan.Model.Wallet;

import java.util.ArrayList;
import java.util.List;

public class ClientRepository implements IClientRepository {

    private DatabaseConnection connection;

    private Context context;

    /**
     * Class constructor
     * @param context
     */
    public ClientRepository(Context context) {
        this.context = context;

        this.connection = new DatabaseConnection(this.context);

    }

    @Override
    public boolean insert(@NonNull String name, @NonNull String phoneNumber, int userID) {

        ContentValues values = new ContentValues();

        values.put("cli_name", name);
        values.put("cli_phone_number", phoneNumber);
        values.put("cli_user", userID);

        try {
            this.connection.getWritableDatabase().insert(AdminDBHelper.MOORGAN_TABLE_CLIENT, null, values);
            this.connection.getWritableDatabase().close();

            insertUserClient(userID, getLastID());
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
                    delete(AdminDBHelper.MOORGAN_TABLE_CLIENT,
                            "cli_id='" + id + "'",
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
    public List<Client> findAll() {

        List<Client> clients = new ArrayList<>();

        Cursor cursor = this.connection.getReadableDatabase().
                query(AdminDBHelper.MOORGAN_TABLE_CLIENT,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

        if(cursor.moveToFirst()){
            do{
                clients.add(new Client(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2),
                        findAllJobs(cursor),
                        cursor.getInt(2)));

            }while(cursor.moveToNext());
        }
        this.connection.getReadableDatabase().close();

        return clients;
    }

    @Override
    public Client findById(int id) {
        Client client = null;

        Cursor cursor = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_CLIENT + " WHERE cli_id ='" + id + "'" , null);

        if(cursor.moveToFirst())
            client = new Client(cursor.getInt(0), cursor.getString(1),
                                cursor.getString(2),
                                findAllJobs(cursor),
                                cursor.getInt(2));


        this.connection.getReadableDatabase().close();

        return client;
    }

    @Override
    public int getLastID() {

        int clientID = 0;
        Cursor cursor = this.connection.getReadableDatabase().
                query(AdminDBHelper.MOORGAN_TABLE_CLIENT,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

        if(cursor.moveToLast())
            clientID = cursor.getInt(0);

        this.connection.getReadableDatabase().close();

        return clientID;
    }

    @Override
    public boolean insertUserClient(int userID, int clientID) {
        ContentValues values = new ContentValues();

        values.put("user_id", userID);
        values.put("client_id", clientID);

        try {
            this.connection.getWritableDatabase().insert(AdminDBHelper.MOORGAN_TABLE_USER_CLIENT, null, values);
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
    private List<Job> findAllJobs(Cursor cursor) {

        List<Integer> jobsID = new ArrayList<>();
        List<Job> jobs = new ArrayList<>();

        Cursor cursor2 = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_CLIENT_JOB +
                        " WHERE client_id ='" + cursor.getInt(0) + "'", null);


        if (cursor2.moveToFirst()) {
            do {
                jobsID.add(cursor2.getInt(1));
            } while (cursor2.moveToNext());
        }


        for (Integer jobID: jobsID)
            jobs.add((new JobRepository(this.context)).findById(jobID));

        return jobs;
    }
}
