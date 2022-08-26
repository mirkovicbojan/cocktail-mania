package com.example.fridgey;

public class CocktailSearchDTO {
    public String name;
    public String thumbnail;

    public CocktailSearchDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
