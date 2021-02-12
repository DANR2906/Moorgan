package com.moorgan.IRepositories;

import androidx.annotation.NonNull;

import com.moorgan.Model.BalanceHistory;

import java.util.List;

public interface IBalanceHistoryRepository {

    /**
     *
     * @param amount
     * @param entryDate
     * @param description
     * @param walletID
     * @return
     */
    boolean insert(long amount, @NonNull String entryDate, @NonNull String description,
                   int walletID, int type);

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
    List<BalanceHistory> findAll();

    /**
     *
     * @param id
     * @return
     */
    BalanceHistory findByID(int id);


}
