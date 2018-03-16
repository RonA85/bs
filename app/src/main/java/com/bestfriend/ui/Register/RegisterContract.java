package com.bestfriend.ui.Register;

import com.bestfriend.model.User;
import com.bestfriend.ui.base.BaseView;

import butterknife.BindView;

/**
 * Created by mac on 16/03/2018.
 */

public interface RegisterContract {

    interface View extends BaseView
    {
        void moveToMapScreen();
        void showError();
        User getUser();
    }

    interface Presenter {
        void onRegisterClick();
    }
}
