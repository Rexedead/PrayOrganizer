package com.prayorganizer.rxdd.orthodox.content;

/**
 * Created by Rexedead on 03.01.2018.
 *
 */

public class Psalm {
    private String[] linesRU;
    private String[] linesCSL;
    private String headRU;
    private String headCSL;




    public Psalm(String[] linesRU, String[] linesCSL, String headRU, String headCSL) {
        this.linesRU = linesRU;
        this.linesCSL = linesCSL;
        this.headRU = headRU;
        this.headCSL = headCSL;

    }

    public String[] getLinesRU() {
        return linesRU;
    }

    public String[] getLinesCSL() {
        return linesCSL;
    }

    public String getHeadRU() {
        return headRU;
    }

    public String getHeadCSL() {
        return headCSL;
    }


}
