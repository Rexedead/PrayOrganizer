package com.prayorganizer.rxdd.orthodox.content;


public class PraysCategories {
    private String title, imageId, tag;


    public PraysCategories(String title, String imageId, String tag) {
        this.title = title;
        this.imageId = imageId;
        this.tag = tag;
    }

    public PraysCategories(String title, String imageId) {
        this.title = title;
        this.imageId = imageId;

    }

    public String getTag() {
        return tag;
    }

    public String getTitle() {
        return title;
    }

    public String getImageId() {
        return imageId;
    }


}