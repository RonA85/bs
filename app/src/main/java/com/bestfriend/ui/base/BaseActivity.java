package com.bestfriend.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Avishay on 22/01/2018.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    protected String TAG = getClass().getSimpleName();

    protected ProgressDialog mProgressDialog;

    private Unbinder mUnbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        mUnbinder = ButterKnife.bind(this);

        initUi();
    }

    /*
     * get the activity layout.
     */
    protected abstract @LayoutRes
    int getLayout();


    /*
     * initialize the ui elements.
     */
    protected abstract void initUi();




    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public void showLoading() {
        if (getProgressDialog() == null) return;
        if (getProgressDialog() != null && !getProgressDialog().isShowing())
            getProgressDialog().show();
    }

    @Override
    public void hideLoading() {
        if (getProgressDialog() == null) return;
        getProgressDialog().dismiss();
    }


    private ProgressDialog getProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCancelable(false);
        }
        return mProgressDialog;
    }

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}