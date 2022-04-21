package com.app.ce216_project;

import java.util.ArrayList;

public class Catalog {

    private static Catalog catalogInstance = null;

    public ArrayList<Item> items;
    public ArrayList<Type> types;
    public ArrayList<Tag> tags;

    public Catalog() {

        items = new ArrayList<>();
        types = new ArrayList<>();
        tags = new ArrayList<>();

    }

    public static Catalog getCatalogInstance() {
        if (catalogInstance == null)
            catalogInstance = new Catalog();

        return catalogInstance;
    }


    public Type createType(Type type){

        types.add(type);
        return type;
    }

    
    public void createItem(Item item){ }
    public void editType(Type type){ }
    public void editItem(Item item){ }
    public void deleteItem(Item item){ }
    public void deleteType(Type type){ }
    public Object searchWithText(String text){
        return null;
    }
    public Item searchWithTag(Tag tag){
        return null;
    }




    // Getters & Setters
    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Type> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<Type> types) {
        this.types = types;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }
}
