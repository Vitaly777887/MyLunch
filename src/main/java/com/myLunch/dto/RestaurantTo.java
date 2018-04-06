package com.myLunch.dto;


public class RestaurantTo {
    private int id;
    private String name;
    private int votesCount;

    public RestaurantTo(int id, String name, int votesCount) {
        this.id = id;
        this.name = name;
        this.votesCount = votesCount;
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

    public int getVotesCount() {
        return votesCount;
    }

    public void setVotesCount(int votesCount) {
        this.votesCount = votesCount;
    }
}
