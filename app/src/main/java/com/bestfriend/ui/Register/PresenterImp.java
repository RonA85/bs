package com.bestfriend.ui.Register;

import com.bestfriend.model.User;
import com.bestfriend.network.ApiCalls;
import com.bestfriend.network.DataObserver;
import com.bestfriend.ui.base.BasePresenter;


/**
 * Created by mac on 16/03/2018.
 */

public class PresenterImp extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    @Override
    public void onRegisterClick() {
        DataObserver<User> observer = new DataObserver<User>() {
            @Override
            public void onReceived(User user) {
                if (user == null) {
                    getView().showError();
                } else getView().moveToMapScreen();
            }
        };
        User user = getView().getUser();
        ApiCalls.createNewUser(observer, user);
    }
//    @Override
//    public void perfromRegister(String fullName, Dog dog) {
//
//        DataObserver<User> observer = new DataObserver<User>() {
//            @Override
//            public void onRecieved(User users) {
//                Log.d("Parks recieved", "Recieved!" + users.toString());
//            }
//        };
//        User user = new User(fullName,dog);
//        ApiCalls.createNewUser(observer,user);
//
//    }
}
