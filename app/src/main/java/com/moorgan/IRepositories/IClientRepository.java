package com.moorgan.IRepositories;

import androidx.annotation.NonNull;

import com.moorgan.Model.Client;

import java.util.List;

public interface IClientRepository {

    /**
     *
     * @param name
     * @param phoneNumber
     * @param userID
     * @return
     */
    boolean insert(@NonNull String name, @NonNull String phoneNumber , int userID);

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
    List<Client> findAll();

    /**
     *
     * @param id
     * @return
     */
    Client findById(int id);

    /**
     *
     * @return
     */
    int getLastID();

    /**
     *
     * @param userID
     * @return
     */
    boolean insertUserClient(int userID, int clientID);
}
