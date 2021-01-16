package com.moorgan.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import androidx.annotation.NonNull;

import com.moorgan.Database.AdminDBHelper;
import com.moorgan.Database.DatabaseConnection;
import com.moorgan.IRepositories.IStatusTaskRepository;
import com.moorgan.Model.StatusTask;
import com.moorgan.Model.Wallet;

import java.util.ArrayList;
import java.util.List;

public class StatusTaskRepository implements IStatusTaskRepository {

    private DatabaseConnection connection;

    private Context context;

    /**
     * Class constructor
     * @param context
     */
    public StatusTaskRepository(Context context) {
        this.context = context;

        this.connection = new DatabaseConnection(this.context);

    }

    @Override
    public boolean insert(@NonNull String name, @NonNull String description, int approve, int statusID) {
        this.connection.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("tas_name", name);
        values.put("tas_description", description);
        values.put("tas_approve", approve);

        try {
            this.connection.getWritableDatabase().insert(AdminDBHelper.MOORGAN_TABLE_TASK, null, values);
            this.connection.getWritableDatabase().close();

            insertStatusTask(statusID, getLastID());

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
                    delete(AdminDBHelper.MOORGAN_TABLE_TASK,
                            "tas_id='" + id + "'",
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
    public List<StatusTask> findAll() {
        List<StatusTask> statusTasks = new ArrayList<>();

        Cursor cursor = this.connection.getReadableDatabase().
                query(AdminDBHelper.MOORGAN_TABLE_TASK,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

        if(cursor.moveToFirst()){
            do{
                statusTasks.add(new StatusTask(cursor.getInt(0), cursor.getString(1),
                                cursor.getString(2), cursor.getInt(3) ));
            }while(cursor.moveToNext());
        }
        this.connection.getReadableDatabase().close();

        return statusTasks;
    }

    @Override
    public StatusTask findById(int id) {
        StatusTask statusTask = null;

        Cursor cursor = this.connection.getReadableDatabase().
                rawQuery("SELECT * FROM "
                        + AdminDBHelper.MOORGAN_TABLE_TASK + " WHERE tas_id ='" + id + "'" , null);

        if(cursor.moveToFirst())
            statusTask = new StatusTask(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getInt(3) );


        this.connection.getReadableDatabase().close();

        return statusTask;
    }

    @Override
    public int getLastID() {
        int id = 0;
        Cursor cursor = this.connection.getReadableDatabase().
                query(AdminDBHelper.MOORGAN_TABLE_TASK,
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

    private boolean insertStatusTask(int statusID, int taskID) {
        ContentValues values = new ContentValues();

        values.put("status_id", statusID);
        values.put("task_id", taskID);

        try {
            this.connection.getWritableDatabase().insert(AdminDBHelper.MOORGAN_TABLE_STATUS_TASK, null, values);
            this.connection.getWritableDatabase().close();
            return true;
        }catch (Exception ex) {
            Log.e("Insertion DB error", ex.getMessage());
            this.connection.getWritableDatabase().close();
            return false;
        }
    }


}
