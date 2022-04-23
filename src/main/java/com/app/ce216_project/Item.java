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

    public String showInformation(){

        StringBuilder i = new StringBuilder();

        StringBuilder a = new StringBuilder();

        for (Tag tag:tags){

            assert false;
            i.append("* ").append(tag.getName()).append("\n");

        }

        for (Attribute attribute:type.getDefaultAttributes()){

            assert false;
            a.append("* ").append(attribute.getName()).append(": ").append(attribute.getValue()).append("\n");

        }

        String s = "\nItem Name: " + name +"\n"+
                "\nTags:\n" + i +
                "\nAttributes:\n" + a;




        return s;

    }

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

    @Override
    public String toString() {
        return name;
    }
}
