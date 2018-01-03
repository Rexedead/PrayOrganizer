package com.prayorganizer.rxdd.orthodox.presenters;

import com.prayorganizer.rxdd.orthodox.view.MainActivity;
import com.prayorganizer.rxdd.orthodox.content.PraysCategories;
import com.prayorganizer.rxdd.orthodox.database.HolyModel;

import java.util.List;

/**
 * Created by danbi on 31.12.2017.
 *
 */

public class HolyPresenter {

    private static HolyPresenter sHolyPresenter;

    private static final int EVENT_SHOW_PRAYS_CATEGORIES = 0;

    private MainActivity mMainActivity;
    private HolyModel mHolyModel;
    private int mCurrentEvent;

    public static HolyPresenter getInstance(MainActivity mainActivity){
        HolyPresenter holyPresenter = sHolyPresenter;
        if(holyPresenter == null){
            synchronized (HolyModel.class){
                holyPresenter = sHolyPresenter;
                if(holyPresenter == null){
                    holyPresenter = sHolyPresenter = createPresenter();
                }
            }
        }
        holyPresenter.mMainActivity = mainActivity;
        return holyPresenter;
    }

    private static HolyPresenter createPresenter(){
        return new HolyPresenter();
    }


    private HolyPresenter() {
        mHolyModel = HolyModel.getInstance();


        mCurrentEvent = EVENT_SHOW_PRAYS_CATEGORIES;
    }

    public void unBind(){
        mMainActivity = null;
    }

    public void doLatestEvent(){
        switch (mCurrentEvent){
            case EVENT_SHOW_PRAYS_CATEGORIES:
                showCategories();
                break;
        }
    }

    private void showCategories(){
        List<PraysCategories> praysCategories = mHolyModel.getMasterCategoriesOfPrays();
        mMainActivity.showPrayCategories(praysCategories);

    }


}
