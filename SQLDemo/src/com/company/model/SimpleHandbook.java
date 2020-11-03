package com.company.model;

public class SimpleHandbook extends BaseHandbook {
    private String name;

    public SimpleHandbook(long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
