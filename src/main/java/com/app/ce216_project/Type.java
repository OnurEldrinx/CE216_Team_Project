package com.app.ce216_project;

import java.util.ArrayList;

public class Type {

    private String name;
    private ArrayList<Item> items;
    private ArrayList<Attribute> defaultAttributes;

    public Type(String name) {
        this.name = name;

        items = new ArrayList<>();
        defaultAttributes = new ArrayList<>();

    }

    public void edit(){ }

    public void delete(){ }

    public void addAttribute(String name,String value,String description){

        Attribute attribute = new Attribute(name,value);
        attribute.setDescription(description);
        defaultAttributes.add(attribute);

    }


    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Attribute> getDefaultAttributes() {
        return defaultAttributes;
    }

    public void setDefaultAttributes(ArrayList<Attribute> defaultAttributes) {
        this.defaultAttributes = defaultAttributes;
    }

    @Override
    public String toString() {
        return name;
    }
}
