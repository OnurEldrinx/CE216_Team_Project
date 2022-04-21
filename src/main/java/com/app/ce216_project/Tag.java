package com.app.ce216_project;

import java.util.LinkedList;

public class Tag {

    private String name;
    private LinkedList<Item> attachedItems;

    public Tag(String name) {
        this.name = name;
        attachedItems = new LinkedList<>();
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

    public LinkedList<Item> getAttachedItems() {
        return attachedItems;
    }

    public void setAttachedItems(LinkedList<Item> attachedItems) {
        this.attachedItems = attachedItems;
    }
}
