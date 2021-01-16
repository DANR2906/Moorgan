package com.moorgan.IRepositories;

import androidx.annotation.NonNull;

import com.moorgan.Model.Income;

import java.util.List;

public interface IIncomeRepository {
    /**
     *
     * @param title
     * @param walletID
     * @return
     */
    boolean insert(@NonNull String title, int walletID,  int balanceHistoryID);

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
    List<Income> findAll();

    /**
     *
     * @param id
     * @return
     */
    Income findById(int id);

    /**
     *
     * @return
     */
    int getLastID();

}
