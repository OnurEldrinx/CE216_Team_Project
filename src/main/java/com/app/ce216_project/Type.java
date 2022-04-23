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

    public String showInformation(){

        StringBuilder i = new StringBuilder();

        StringBuilder a = new StringBuilder();

        for (Item item:items){

            assert false;
            i.append("* ").append(item.getName()).append("\n");

        }

        for (Attribute attribute:defaultAttributes){

            assert false;
            a.append("* ").append(attribute.getName()).append("\n");

        }

        String s = "\nType Name: " + name +"\n"+
                   "\nCreated Items:\n" + i +
                   "\nDefault Attributes:\n" + a;




        return s;

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
        return name ;
    }

}
