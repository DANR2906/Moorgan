package com.moorgan.Model;


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
    private String phoneNumber;

    //
    private List<Job> jobs;

    //
    private int user;


    public Client(int id, String name, String phoneNumber, List<Job> jobs, int user) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
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

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
