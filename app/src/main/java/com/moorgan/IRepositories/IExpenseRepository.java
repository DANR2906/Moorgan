package com.moorgan.IRepositories;



import androidx.annotation.NonNull;

import com.moorgan.Model.Client;
import com.moorgan.Model.Expense;

import java.util.List;

public interface IExpenseRepository {
    /**
     *
     * @param title
     * @param walletID
     * @return
     */
    boolean insert(@NonNull String title, int walletID, int balanceHistoryID);

    /**
     *
     * @param id
     * @return
     */
    boolean deleteByID(int id);

    /**
     *
     * @return
     */
    List<Expense> findAll();

    /**
     *
     * @param id
     * @return
     */
    Expense findById(int id);

    /**
     *
     * @return
     */
    int getLastID();

}
