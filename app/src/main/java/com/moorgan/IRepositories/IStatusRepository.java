package com.moorgan.IRepositories;

import androidx.annotation.NonNull;

import com.moorgan.Model.Income;
import com.moorgan.Model.Status;

import java.util.List;

public interface IStatusRepository {

    /**
     *
     * @param name
     * @param advancePayment
     * @param approve
     * @return
     */
    boolean insert(@NonNull String name, long advancePayment, int approve, int jobID);

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
    List<Status> findAll();

    /**
     *
     * @param id
     * @return
     */
    Status findById(int id);

    /**
     *
     * @return
     */
    int getLastID();

    /**
     *
     * @param balanceHistoryID
     * @param statusID
     * @return
     */
    boolean insertStatusBalanceHistory(int balanceHistoryID, int statusID);

}
