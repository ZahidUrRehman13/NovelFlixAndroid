package com.example.novelflex.Models;

public class DataModelClass {

    private String images;
    private String title;
    private String descriptions;
    private String author;

    public DataModelClass(String images , String title,String descriptions,String author){
        this.images = images;
        this.title = title;
        this.descriptions=descriptions;
        this.author=author;
    }

    public String getImage() {
        return images;
    }

    public String getTitle() {
        return title;
    }
    public String getDescriptions() {
        return descriptions;
    }
    public String getAuthor() {
        return author;
    }
}
