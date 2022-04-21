package com.app.ce216_project;

import java.util.ArrayList;

public class Item {

    private String name;
    private ArrayList<Tag> tags;
    private Type type;

    public Item(String name, Type type) {
        this.name = name;
        this.type = type;

        tags = new ArrayList<>();

    }

    public void edit(){ }

    public void delete(){ }

    public void addAttribute(){ }

    public void addTag(){ }


    // Getters & Setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
