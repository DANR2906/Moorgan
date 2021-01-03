package com.moorgan.model;


import java.util.Date;
import java.util.List;

/**
 * User model
 *
 * @author DANR
 */
public class User {

    //
    private int id;

    //
    private String name;

    //
    private String lastName;

    //
    private Date birthDate;

    //
    private String career;

    //
    private Wallet wallet;

    //
    private List<Job> jobs;

    //
    private List<Client> clients;

    //Este es mi comentario // Base de datos sqlite android

    /**
     * Class constructor
     *
     * @param id        User id
     * @param name      User name
     * @param lastName  User last name
     * @param birthDate User birth date
     * @param career    User career
     * @param wallet    User wallet id
     * @param jobs      User jobs id
     * @param clients   User clients id
     *
     */
    public User(int id, String name, String lastName, Date birthDate, String career,
                Wallet wallet, List<Job> jobs, List<Client> clients) {

        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.career = career;
        this.wallet = wallet;
        this.jobs = jobs;
        this.clients = this.clients;

    }

    /**
     * Class constructor
     *
     */
    public User(){}


    /**
     * Getter method for id
     *
     * @return User id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method for id
     *
     * @param id User id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter method for name
     *
     * @return User name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for name
     *
     * @param name User name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for last name
     *
     * @return User last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter method for last name
     *
     * @param lastName User last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter method for birth date
     *
     * @return User birth date
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Setter method for birth date
     *
     * @param birthDate User birth date
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Getter method for career
     *
     * @return User career
     */
    public String getCareer() {
        return career;
    }

    /**
     * Setter method for carrer
     *
     * @param career User career
     */
    public void setCareer(String career) {
        this.career = career;
    }

    /**
     * Getter method for wallet id
     *
     * @return User wallet id
     */
    public Wallet getWallet() {
        return wallet;
    }

    /**
     * Setter method for wallet id
     *
     * @param wallet User wallet id
     */
    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    /**
     * Getter method for jobs id
     *
     * @return User jobs id
     */
    public List<Job> getJobs() {
        return jobs;
    }

    /**
     * Setter method for jobs id
     *
     * @param jobs User jobs id
     */
    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    /**
     * Getter method for clients id
     *
     * @return  User clients id
     */
    public List<Client> getClients() {
        return clients;
    }

    /**
     * Setter method for clients id
     *
     * @param clients User clients id
     */
    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
