package com.moorgan;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesController {

    private Context context;

    /**
     * Class consatructor
     * @param context
     */
    public PreferencesController(Context context){

        this.context = context;

    }

    /**
     *
     */
    public void uploadRegiterInformation(){
        SharedPreferences preferences = context.getSharedPreferences("logInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("isRegister", "1");
        editor.commit();
    }

    /**
     *
     * @param userID
     */
    public void setCurrentUser(String userID){
        SharedPreferences preferences = context.getSharedPreferences("currentUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userID", userID);
        editor.commit();
    }

    /**
     *
     * @return
     */
    public int getCurrentUser(){
        SharedPreferences preferences = context.getSharedPreferences("currentUser", Context.MODE_PRIVATE);
        return Integer.parseInt(preferences.getString("userID", "0"));
    }
}
