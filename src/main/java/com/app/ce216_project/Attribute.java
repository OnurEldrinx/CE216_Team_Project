package com.app.ce216_project;

public class Attribute {

    private String name;
    private String value;
    private String description;

    public Attribute(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Attribute(String name){

        this.name = name;

    }

    public void edit(){ }
    public void delete(){ }


    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }
}
