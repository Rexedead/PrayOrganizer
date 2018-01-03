package com.prayorganizer.rxdd.orthodox.content;

/**
 * Created by Rexedead on 03.01.2018.
 *
 */

public class PsalmsCategories {
    private String mTitleId;
    private String mKafId;

    public PsalmsCategories(String titleId, String kafId) {
        this.mTitleId = "Псалом "+titleId;
        this.mKafId = kafId;
    }


    public String getTitle() {
        return mTitleId;
    }

    public String getKafId() {
        return mKafId;
    }
}
