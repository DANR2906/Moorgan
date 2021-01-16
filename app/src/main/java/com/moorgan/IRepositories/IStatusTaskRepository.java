package com.moorgan.IRepositories;

import androidx.annotation.NonNull;

import com.moorgan.Model.Status;
import com.moorgan.Model.StatusTask;

import java.util.List;

public interface IStatusTaskRepository {

    /**
     *
     * @param name
     * @param description
     * @param approve
     * @return
     */
    boolean insert(@NonNull String name, @NonNull String description, int approve, int statusID);

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
    List<StatusTask> findAll();

    /**
     *
     * @param id
     * @return
     */
    StatusTask findById(int id);

    /**
     *
     * @return
     */
    int getLastID();
}
