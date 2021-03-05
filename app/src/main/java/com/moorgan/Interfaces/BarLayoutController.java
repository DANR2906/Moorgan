package com.moorgan.Interfaces;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.moorgan.AddClientJob;
import com.moorgan.MainActivity;

public interface BarLayoutController {

    /**
     *
     * @param view
     */
    void goToAdd(View view);

    /**
     *
     * @param view
     */
    void goToMain(View view);

    /**
     *
     * @param view
     */
    void goToWallet(View view);
}
