package com.company;

public abstract class Stage {

    protected float length;
    protected String description;
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);
}
