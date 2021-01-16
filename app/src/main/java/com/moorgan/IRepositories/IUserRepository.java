package com.moorgan.IRepositories;

import androidx.annotation.NonNull;

import com.moorgan.Model.Client;
import com.moorgan.Model.Job;
import com.moorgan.Model.User;
import com.moorgan.Model.Wallet;

import java.util.Date;
import java.util.List;

public interface IUserRepository {

    /**
     *
     * @param name
     * @param lastName
     * @param birthDate
     * @param career
     * @param walletID
     * @return
     */
    boolean insert(@NonNull String name, @NonNull String lastName, @NonNull String birthDate,
                   @NonNull String career, int walletID);

    /**
     *
     * @param id
     * @return
     */
    User findByID(int id);

    /**
     *
     * @param userID
     * @return
     */
    List<Job> findUserJobs(int userID);

    /**
     *
     * @param userID
     * @return
     */
    List<Client> findUserClients(int userID);

}
