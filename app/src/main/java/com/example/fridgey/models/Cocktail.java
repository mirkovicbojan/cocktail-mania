package com.example.fridgey.models;

import java.util.List;

public class Cocktail {
    public String id;
    public String name;
    //public List<String> ingredients;
    public String imgurl;
    public String instructions;

    public Cocktail() {
    }

    public Cocktail(String id, String name, String instructions, String imgurl) {
        this.id = id;
        this.name = name;
        this.imgurl = imgurl;
        this.instructions = instructions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }*/

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
