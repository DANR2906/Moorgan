package com.moorgan.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdminSQLiteDB extends SQLiteOpenHelper {

    //
    public static final int DB_DEFAULT_VERSION = 1;
    //Name of the data base
    public static final String DB_NAME = "";
    //
    public static final String DB_TABLE_NAME_USER = "user";
    //
    public static final String DB_TABLE_NAME_WALLET = "wallet";

    /**
     * Class constructor
     *
     * @param context
     * @param name name of the database
     */
    public AdminSQLiteDB(@Nullable Context context, @Nullable String name) {

        super(context, name, null, DB_DEFAULT_VERSION);

    }

    /**
     * Class constructor
     *
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public AdminSQLiteDB(@Nullable Context context, @Nullable String name,
                         @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE user(" +
                "use_id integer PRIMARY KEY AUTOINCREMENT," +
                "use_name varchar(30) NOT NULL," +
                "use_last_name varchar(30) NOT NULL," +
                "use_birthdate date NOT NULL DEFAULT '00/00/2000'," +
                "use_career varchar(30) NOT NULL DEFAULT 'unknow'," +
                "use_wallet integer NOT NULL," +
                "FOREIGN KEY (use_wallet)" +
                "   REFERENCES wallet (wal_id))");


        db.execSQL("CREATE TABLE wallet(" +
                "wal_id integer PRIMARY KEY AUTOINCREMENT," +
                "wal_balance integer NOT NULL DEFAULT 0)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
