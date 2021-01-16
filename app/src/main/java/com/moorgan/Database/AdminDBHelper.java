package com.moorgan.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminDBHelper extends SQLiteOpenHelper {

    //
    public static final String MOORGAN_DB_NAME = "moorganDB";
    //
    public static final int MOORGAN_DB_VERSION = 1;
    //
    public static final String MOORGAN_TABLE_USER = "user";
    //
    public static final String MOORGAN_TABLE_USER_JOB = "user_job";
    //
    public static final String MOORGAN_TABLE_USER_CLIENT = "user_client";
    //
    public static final String MOORGAN_TABLE_WALLET = "wallet";
    //
    public static final String MOORGAN_TABLE_INCOME = "income";
    //
    public static final String MOORGAN_TABLE_EXPENSE = "expense";
    //
    public static final String MOORGAN_TABLE_BALANCE_HISTORY = "balanceHistory";
    //
    public static final String MOORGAN_TABLE_BALANCE_HISTORY_JOB = "balanceHistory_job";
    //
    public static final String MOORGAN_TABLE_BALANCE_HISTORY_INCOME = "balanceHistory_income";
    //
    public static final String MOORGAN_TABLE_BALANCE_HISTORY_EXPENSE = "balanceHistory_expense";
    //
    public static final String MOORGAN_TABLE_BALANCE_HISTORY_STATUS = "balanceHistory_status";
    //
    public static final String MOORGAN_TABLE_JOB = "job";
    //
    public static final String MOORGAN_TABLE_CLIENT = "client";
    //
    public static final String MOORGAN_TABLE_CLIENT_JOB= "client_job";
    //
    public static final String MOORGAN_TABLE_JOB_STATUS = "job_status";
    //
    public static final String MOORGAN_TABLE_STATUS = "status";
    //
    public static final String MOORGAN_TABLE_TASK = "task";
    //
    public static final String MOORGAN_TABLE_STATUS_TASK = "status_task";


    /**
     * Class constructor
     *
     * @param context
     */
    public AdminDBHelper(@Nullable Context context) {
        super(context, MOORGAN_DB_NAME, null, MOORGAN_DB_VERSION);
    }

    /**
     * Class constructor
     *
     * @param context
     * @param name
     * @param version
     */
    public AdminDBHelper(@Nullable Context context, @Nullable String name, int version) {
        super(context, name, null, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("CREATE TABLE " + MOORGAN_TABLE_USER  + "(" +
                "use_id integer PRIMARY KEY AUTOINCREMENT," +
                "use_name varchar(30) NOT NULL," +
                "use_last_name varchar(30) NOT NULL," +
                "use_birthdate Date NOT NULL DEFAULT '00/00/0000'," +
                "use_career varchar(30) NOT NULL DEFAULT 'unknow'," +
                "use_wallet integer NOT NULL," +
                "FOREIGN KEY (use_wallet)" +
                "   REFERENCES " + MOORGAN_TABLE_WALLET + " (wal_id) )");

        db.execSQL("CREATE TABLE " + MOORGAN_TABLE_USER_JOB + "(" +
                "user_id integer NOT NULL," +
                "job_id integer NOT NULL," +
                "FOREIGN KEY (user_id)" +
                "   REFERENCES " + MOORGAN_TABLE_USER + "(use_id)," +
                "FOREIGN KEY (job_id) " +
                "   REFERENCES " + MOORGAN_TABLE_JOB + "(job_id)," +
                "PRIMARY KEY (user_id, job_id) )");

        db.execSQL("CREATE TABLE " + MOORGAN_TABLE_USER_CLIENT + "(" +
                "user_id integer NOT NULL," +
                "client_id integer NOT NULL," +
                "FOREIGN KEY (user_id)" +
                "   REFERENCES " + MOORGAN_TABLE_USER + "(use_id)," +
                "FOREIGN KEY (client_id) " +
                "   REFERENCES " + MOORGAN_TABLE_CLIENT + "(cli_id)," +
                "PRIMARY KEY (user_id, client_id) )");

        db.execSQL("CREATE TABLE " + MOORGAN_TABLE_WALLET + "(" +
                "wal_id integer PRIMARY KEY AUTOINCREMENT," +
                "wal_balance integer NOT NULL DEFAULT 0)");

        db.execSQL("CREATE TABLE " + MOORGAN_TABLE_INCOME + "(" +
                "inc_id integer PRIMARY KEY AUTOINCREMENT," +
                "inc_title varchar(100) NOT NULL," +
                "inc_wallet integer," +
                "FOREIGN KEY (inc_wallet)" +
                "   REFERENCES "+ MOORGAN_TABLE_WALLET + " (wal_id) )");

        db.execSQL("CREATE TABLE " + MOORGAN_TABLE_EXPENSE + "(" +
                "exp_id integer PRIMARY KEY AUTOINCREMENT," +
                "exp_title varchar(100) NOT NULL," +
                "exp_wallet integer," +
                "FOREIGN KEY (exp_wallet)" +
                "   REFERENCES "+ MOORGAN_TABLE_WALLET + " (wal_id) )");

        db.execSQL("CREATE TABLE " + MOORGAN_TABLE_BALANCE_HISTORY + "(" +
                "bal_id integer PRIMARY KEY AUTOINCREMENT," +
                "bal_amount integer NOT NULL," +
                "bal_entry_date DateTime NOT NULL DEFAULT (CURRENT_TIMESTAMP)," +
                "bal_description text," +
                "bal_wallet integer," +
                "FOREIGN KEY (bal_wallet)" +
                "   REFERENCES " + MOORGAN_TABLE_WALLET + "(wal_id) )");


        db.execSQL("CREATE TABLE " + MOORGAN_TABLE_BALANCE_HISTORY_JOB + "(" +
                "balanceHistory_id integer," +
                "job_id integer," +
                "FOREIGN KEY (balanceHistory_id)" +
                "   REFERENCES " + MOORGAN_TABLE_BALANCE_HISTORY + "(bal_id)," +
                "FOREIGN KEY (job_id)" +
                "   REFERENCES " + MOORGAN_TABLE_JOB + "(job_id),"+
                "PRIMARY KEY (balanceHistory_id, job_id))");

        db.execSQL("CREATE TABLE " + MOORGAN_TABLE_BALANCE_HISTORY_INCOME + "(" +
                "balanceHistory_id integer," +
                "income_id integer," +
                "FOREIGN KEY (balanceHistory_id)" +
                "   REFERENCES " + MOORGAN_TABLE_BALANCE_HISTORY + "(bal_id)," +
                "FOREIGN KEY (income_id)" +
                "   REFERENCES " + MOORGAN_TABLE_INCOME + "(inc_id)," +
                "PRIMARY KEY (balanceHistory_id, income_id))");

        db.execSQL("CREATE TABLE " + MOORGAN_TABLE_BALANCE_HISTORY_EXPENSE + "(" +
                "balanceHistory_id integer," +
                "expense_id integer," +
                "FOREIGN KEY (balanceHistory_id)" +
                "   REFERENCES " + MOORGAN_TABLE_BALANCE_HISTORY + "(bal_id)," +
                "FOREIGN KEY (expense_id)" +
                "   REFERENCES " + MOORGAN_TABLE_EXPENSE + "(exp_id)," +
                "PRIMARY KEY (balanceHistory_id, expense_id))");

        db.execSQL("CREATE TABLE " + MOORGAN_TABLE_BALANCE_HISTORY_STATUS + "(" +
                "balanceHistory_id integer," +
                "status_id integer," +
                "FOREIGN KEY (balanceHistory_id)" +
                "   REFERENCES " + MOORGAN_TABLE_BALANCE_HISTORY + "(bal_id)," +
                "FOREIGN KEY (status_id)" +
                "   REFERENCES " + MOORGAN_TABLE_STATUS + "(sta_id)," +
                "PRIMARY KEY (balanceHistory_id, status_id))");


        db.execSQL("CREATE TABLE " + MOORGAN_TABLE_CLIENT + "(" +
                "cli_id integer PRIMARY KEY AUTOINCREMENT," +
                "cli_name varchar(40) NOT NULL," +
                "cli_user integer," +
                "FOREIGN KEY (cli_user)" +
                "   REFERENCES "+ MOORGAN_TABLE_USER + "(use_id) )");

        db.execSQL("CREATE TABLE " + MOORGAN_TABLE_JOB + "(" +
                "job_id integer PRIMARY KEY AUTOINCREMENT," +
                "job_name varchar(100) NOT NULL," +
                "job_creation_date Date NOT NULL DEFAULT (CURRENT_DATE)," +
                "job_payment integer," +
                "job_finished integer NOT NULL," +
                "job_end_date Date," +
                "job_user integer," +
                "FOREIGN KEY (job_user)" +
                "   REFERENCES "  + MOORGAN_TABLE_USER + "(use_id)" + ")");

        db.execSQL("CREATE TABLE " + MOORGAN_TABLE_CLIENT_JOB + "(" +
                "client_id integer NOT NULL," +
                "job_id integer NOT NULL," +
                "FOREIGN KEY (client_id)" +
                "   REFERENCES " + MOORGAN_TABLE_CLIENT + "(cli_id)," +
                "FOREIGN KEY (job_id) " +
                "   REFERENCES " + MOORGAN_TABLE_JOB + "(job_id)," +
                "PRIMARY KEY (client_id, job_id) )");

        db.execSQL("CREATE TABLE " + MOORGAN_TABLE_JOB_STATUS + "(" +
                "job_id integer," +
                "status_id integer," +
                "FOREIGN KEY (job_id)" +
                "   REFERENCES " + MOORGAN_TABLE_JOB + "(job_id)," +
                "FOREIGN KEY (status_id) " +
                "   REFERENCES " + MOORGAN_TABLE_STATUS + "(sta_id)," +
                "PRIMARY KEY (job_id, status_id) )");

        db.execSQL("CREATE TABLE " + MOORGAN_TABLE_STATUS + "(" +
                "sta_id integer PRIMARY KEY AUTOINCREMENT," +
                "sta_name varchar(50) NOT NULL," +
                "sta_advance_payment integer," +
                "sta_approve integer NOT NULL)");

        db.execSQL("CREATE TABLE " + MOORGAN_TABLE_TASK + "(" +
                "tas_id integer PRIMARY KEY AUTOINCREMENT," +
                "tas_name varchar(100) NOT NULL, " +
                "tas_description text," +
                "tas_approve integer NOT NULL)");

        db.execSQL("CREATE TABLE " + MOORGAN_TABLE_STATUS_TASK + "(" +
                "status_id integer," +
                "task_id integer," +
                "FOREIGN KEY (status_id)" +
                "   REFERENCES " + MOORGAN_TABLE_STATUS + "(sta_id)," +
                "FOREIGN KEY (task_id) " +
                "   REFERENCES " + MOORGAN_TABLE_TASK + "(tas_id)," +
                "PRIMARY KEY (status_id, task_id) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
