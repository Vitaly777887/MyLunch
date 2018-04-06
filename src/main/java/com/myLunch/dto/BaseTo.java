package com.myLunch.dto;

public class BaseTo {
    protected Integer id;

    public BaseTo(Integer id) {
        this.id = id;
    }

    public BaseTo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew(){
        return getId() == null;
    }
}
