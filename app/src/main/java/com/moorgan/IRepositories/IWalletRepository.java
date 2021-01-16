package com.moorgan.IRepositories;

import androidx.annotation.NonNull;

import com.moorgan.Model.Wallet;

import java.util.List;

public interface IWalletRepository {

    /**
     *
     * @param id
     * @param balance
     * @return
     */
    boolean insert(int id, long balance);

    /**
     *
     * @param id
     * @return
     */
    boolean deleteByID(int id);

    /**
     *
     * @param id
     * @return
     */
    Wallet findByID(int id);

    /**
     *
     * @return
     */
    List<Wallet> findAll();

}
