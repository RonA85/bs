package com.bestfriend.ui.maps;

import com.bestfriend.model.User;
import com.bestfriend.ui.base.BasePresenter;
import com.bestfriend.ui.base.BaseView;

import java.util.List;

/**
 * Created by Avishay on 07/03/2018.
 */

public class MapsPresenter extends BasePresenter {

    private List<User> mUsersList;

    @Override
    public void attachView(BaseView view) {
        super.attachView(view);
        loadData();
    }

    private void loadData() {
        loadUsers();

    }

    private void loadUsers() {
        /*TODO Hila : get the users from the server
        *       then, parse the users to the mUsersList
        * */
    }


}
