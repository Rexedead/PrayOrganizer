package com.prayorganizer.rxdd.orthodox.content;


public class PraysCategories {
    private String title, imageId;


    public PraysCategories(String title, String imageName) {
        this.title = title;
        this.imageId = imageName;
    }

    public String getTitle() {
        return title;
    }
    public String getImg() {
        return imageId;
    }

}