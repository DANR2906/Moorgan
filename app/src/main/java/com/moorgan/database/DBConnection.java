package com.moorgan.database;

public class DBConnection {

    private static DBConnection connection;

    /**
     *
     */
    private DBConnection(){

    }

    /**
     *
     * @return
     */
    public static DBConnection getConnection(){

        if(connection == null){
            connection = new DBConnection();
        }

        return connection;

    }
}
