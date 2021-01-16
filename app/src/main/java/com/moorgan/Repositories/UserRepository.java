package com.moorgan.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.moorgan.Database.AdminDBHelper;
import com.moorgan.Database.DatabaseConnection;
import com.moorgan.IRepositories.IUserRepository;
import com.moorgan.Model.Client;
import com.moorgan.Model.Job;
import com.moorgan.Model.User;
import com.moorgan.Model.Wallet;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class UserRepository implements IUserRepository {

    private DatabaseConnection connection;

    private Context context;
    /**
     * Class constructor
     * @param context
     */
    public UserRepository(Context context) {
        this.context = context;

        this.connection = new DatabaseConnection(this.context);

    }


    @Override
    public boolean insert(@NonNull String name, @NonNull String lastName, @NonNull String birthDate,
                          @NonNull String career, int walletID) {

        this.connection.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("use_name", name);
        values.put("use_last_name", lastName);
        values.put("use_birthdate", birthDate);
        values.put("use_career", career);
        values.put("use_wallet", walletID);

        try {
            this.connection.getWritableDatabase().insert(AdminDBHelper.MOORGAN_TABLE_USER, null, values);
            this.connection.getWritableDatabase().close();
            return true;
        }catch (Exception ex) {
            Log.e("Insertion DB error", ex.getMessage());
            this.connection.getWritableDatabase().close();
            return false;
        }

    }

    @Override
    public User findByID(int id) {
        User user = null;

        Cursor cursor = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_USER + " WHERE use_id ='" + id + "'" , null);

        if(cursor.moveToFirst()){
            int walletID = cursor.getInt(5);
            int userID = cursor.getInt(0);

            Wallet userWallet = (new WalletRepository(this.context)).findByID(walletID);



            if(userWallet != null && findUserJobs(userID) != null && findUserClients(userID) != null)
                user = new User(userID, cursor.getString(1),
                                cursor.getString(2), cursor.getString(3),
                                cursor.getString(4), userWallet,
                                findUserJobs(userID), findUserClients(userID));

        }



        this.connection.getReadableDatabase().close();

        return user;
    }

    @Override
    public List<Job> findUserJobs(int userID) {

        List<Integer> jobsID = new ArrayList<>();

        Cursor cursor = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_USER_JOB + " WHERE user_id ='" + userID + "'" , null);

        if(cursor.moveToFirst()){
            do{
               jobsID.add(cursor.getInt(1));
            }while(cursor.moveToNext());
        }

        List<Job> jobs = new ArrayList<>();

        for(Integer jobId : jobsID)
            jobs.add((new JobRepository(this.context)).findById(jobId));

        return jobs;

    }

    @Override
    public List<Client> findUserClients(int userID) {

        List<Integer> clientsID = new ArrayList<>();

        Cursor cursor = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_USER_CLIENT + " WHERE user_id ='" + userID + "'" , null);

        if(cursor.moveToFirst()){
            do{
                clientsID.add(cursor.getInt(1));
            }while(cursor.moveToNext());
        }

        List<Client> clients = new ArrayList<>();

        for(Integer clientID : clientsID)
            clients.add((new ClientRepository(this.context)).findById(clientID));

        return clients;

    }
}
