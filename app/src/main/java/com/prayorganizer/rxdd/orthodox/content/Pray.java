package com.prayorganizer.rxdd.orthodox.content;

/**
 * Created by danbi on 31.12.2017.
 *
 */

public class Pray {
    private String text;
    private String fav;


    public Pray(String text, String fav) {
        this.text = text;
        this.fav = fav;

    }

    public String getText() {
        return text;
    }

    public String getFav() {
        return fav;
    }

}
