package com.moorgan.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseConnection {

    private AdminDBHelper admin;

    private Context context;

    /**
     *
     * @param context
     */
    public DatabaseConnection(Context context){

        this.context = context;
        this.admin = new AdminDBHelper(context);

    }

    /**
     *
     * @return
     */
    public SQLiteDatabase getReadableDatabase(){
        return this.admin.getReadableDatabase();
    }

    /**
     *
     * @return
     */
    public SQLiteDatabase getWritableDatabase(){
        return this.admin.getWritableDatabase();
    }



}
