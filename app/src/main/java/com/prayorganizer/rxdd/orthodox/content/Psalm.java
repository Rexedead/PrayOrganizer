package com.prayorganizer.rxdd.orthodox.content;

/**
 * Created by Rexedead on 03.01.2018.
 *
 */

public class Psalm {
    private String lineRU;
    private String lineCSL;
    private String headRU;
    private String headCSL;
    private String lineID;



    public Psalm(String lineRU, String lineCSL, String headRU, String headCSL, String lineID) {
        this.lineRU = lineRU;
        this.lineCSL = lineCSL;
        this.headRU = headRU;
        this.headCSL = headCSL;
        this.lineID = lineID;
    }
    public String getLineID() {
        return lineID;
    }


    public String getHeadRU() {
        return headRU;
    }

    public String getHeadCSL() {
        return headCSL;
    }

    public String getLineRU() {

        return lineRU;
    }

    public String getLineCSL() {
        return lineCSL;
    }
}
