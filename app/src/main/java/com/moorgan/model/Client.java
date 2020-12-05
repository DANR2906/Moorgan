package com.moorgan.model;


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


    /**
     * Class constructor
     *
     * @param id
     * @param name
     * @param jobs
     */
    public Client(int id, String name, List<Job> jobs) {
        this.id = id;
        this.name = name;
        this.jobs = jobs;
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
}
