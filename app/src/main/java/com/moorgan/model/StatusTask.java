package com.moorgan.model;


public class StatusTask {

    //
    private int id;

    //
    private String name;

    //
    private String description;

    //
    private boolean approve;

    /**
     * Class constructor
     *
     * @param id
     * @param name
     * @param description
     * @param approve
     */
    public StatusTask(int id, String name, String description, boolean approve) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.approve = approve;
    }

    /**
     * Class constructor
     *
     */
    public StatusTask(){

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }
}
