package com.moorgan.IRepositories;

import androidx.annotation.NonNull;

import com.moorgan.Model.Income;
import com.moorgan.Model.Job;

import java.util.List;

public interface IJobRepository {

    /**
     *
     * @param name
     * @param creationDate
     * @param endDate
     * @param payment
     * @param finished
     * @param userID
     * @return
     */
    boolean insert(@NonNull String name, @NonNull String creationDate, @NonNull String endDate,
                   long payment, int finished, @NonNull List<Integer> clientsID, int userID);

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
    List<Job> findAll();

    /**
     *
     * @param id
     * @return
     */
    Job findById(int id);

    /**
     *
     * @return
     */
    int getLastID();

    /**
     *
     * @param balanceHistoryID
     * @param jobID
     * @return
     */
    boolean insertJobBalanceHistory(int balanceHistoryID, int jobID);




}
