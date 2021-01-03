package com.moorgan.model;


import java.util.List;

/**
 * Client model
 *
 * @author DANR
 */
public class Client {

    //
    private int id;

    //
    private String name;

    //
    private List<Job> jobs;

    //
    private User user;


    /**
     * Class constructor
     *
     * @param id
     * @param name
     * @param jobs
     * @param user
     */
    public Client(int id, String name, List<Job> jobs, User user) {
        this.id = id;
        this.name = name;
        this.jobs = jobs;
        this.user = user;
    }

    /**
     * Class constructor
     *
     */
    public Client(){

    }


    public int getId() {

        return id;

    }

    public void setId(int id) {

        this.id = id;

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public List<Job> getJobs() {

        return jobs;
    }

    public void setJobs(List<Job> jobs) {

        this.jobs = jobs;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
