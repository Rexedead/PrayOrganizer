package com.prayorganizer.rxdd.orthodox.content;

/**
 * Created by danbi on 31.12.2017.
 *
 */

public class Pray {
    private String text;
    private String head;
    private String id;

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }


    public String getHead() {
        return head;
    }

    public Pray(String text, String head, String pID) {

        this.text = text;
        this.head = head;
        this.id = pID;
    }
}
