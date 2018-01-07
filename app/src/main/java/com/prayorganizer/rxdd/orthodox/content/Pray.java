package com.prayorganizer.rxdd.orthodox.content;

/**
 * Created by danbi on 31.12.2017.
 *
 */

public class Pray {
    private String text;
    private String fav;
    private String head;

    public String getText() {
        return text;
    }

    public String getFav() {
        return fav;
    }

    public String getHead() {
        return head;
    }

    public Pray(String text, String fav, String head) {

        this.text = text;
        this.fav = fav;
        this.head = head;
    }
}
