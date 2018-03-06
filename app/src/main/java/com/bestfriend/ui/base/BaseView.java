package com.bestfriend.ui.base;

import android.content.Context;

/**
 * Created by Avishay on 22/01/2018.
 */

public interface BaseView {

    public Context getAppContext();

    public Context getActivityContext();

    void showLoading();

    void hideLoading();
}
