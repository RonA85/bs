package com.bestfriend.ui.base;


/**
 * Created by Avishay on 22/01/2018.
 */

public abstract class BasePresenter<T extends BaseView> {

    private T mView;


    public BasePresenter() {

    }

    public T getView() {
        return mView;
    }


    public boolean isViewAttached() {
        return mView != null;
    }

    public void attachView(T view) {
        mView = view;
    }

    public void detachView() {
        mView = null;
    }
}
