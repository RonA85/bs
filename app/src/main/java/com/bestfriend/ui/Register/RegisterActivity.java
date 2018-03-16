package com.bestfriend.ui.Register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bestfriend.R;
import com.bestfriend.model.Dog;
import com.bestfriend.model.User;
import com.bestfriend.ui.base.BaseActivity;
import com.bestfriend.ui.maps.MapsActivity;
import com.bestfriend.ui.utils.CircleTransform;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by mac on 15/03/2018.
 */

public class RegisterActivity extends BaseActivity implements RegisterContract.View {

    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.et_full_name)
    EditText etFullName;
    @BindView(R.id.et_dog_name)
    EditText etDogName;
    @BindView(R.id.et_dog_breed)
    EditText etDogBreed;
    @BindView(R.id.et_dog_birth)
    EditText etDogBirth;
    @BindView(R.id.et_gender)
    Spinner spinnerGender;

    PresenterImp presenterImp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenterImp = new PresenterImp();
        presenterImp.attachView(this);

    }

    @OnClick(R.id.btn_next)
    public void goToMap() {
        presenterImp.onRegisterClick();
    }

    @Override
    public Context getActivityContext() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initUi() {
        Glide.with(this)
                .load("https://image.ibb.co/ee0haH/user_alex.png")
                .crossFade()
                //.thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivAvatar);
        etFullName.setText("Ron Amitai");
        populateSpinner();
    }

    private void populateSpinner() {
        // Spinner Drop down elements
        List<String> gender = new ArrayList<String>();
        gender.add("Male");
        gender.add("Female");
        gender.add("Male Neutered");
        gender.add("Female Neutered");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gender);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerGender.setAdapter(dataAdapter);
    }

    @Override
    public void moveToMapScreen() {
        Intent mapsIntent = new Intent(this, MapsActivity.class);
        startActivity(mapsIntent);
        finish();
    }

    @Override
    public void showError() {
        showToast("Failed to register");
    }

    @Override
    public User getUser() {
        String fullName = etFullName.getText().toString().trim();
        String dogName = etDogName.getText().toString().trim();
        String dogBreed = etDogBreed.getText().toString().trim();
        String dogBirth = etDogBirth.getText().toString().trim();
        String dogGender = spinnerGender.getSelectedItem().toString();
        Dog dog = new Dog(dogName, dogBirth, dogBreed, dogGender);

        User user = new User(fullName, dog);
        return user;
    }

    @Override
    protected void onDestroy() {
        presenterImp.detachView();
        super.onDestroy();
    }
}
