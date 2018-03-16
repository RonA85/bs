package com.bestfriend.ui.Register;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bestfriend.R;
import com.bestfriend.model.Dog;
import com.bestfriend.model.User;
import com.bestfriend.ui.base.BaseActivity;
import com.bestfriend.ui.maps.MapsActivity;
import com.bestfriend.ui.utils.CircleTransform;
import com.bestfriend.ui.utils.PermissionsUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by mac on 15/03/2018.
 */

public class RegisterActivity extends BaseActivity implements RegisterContract.View,DatePickerDialog.OnDateSetListener {

    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.fab_add_picture)
    FloatingActionButton fabAddPicture;
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
    private Calendar calendar;
    private int year, month, day;
    DatePickerDialog datePickerDialog;
    PresenterImp presenterImp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenterImp = new PresenterImp();
        presenterImp.attachView(this);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(
                this,this, year, month, day);

    }

    @OnClick({R.id.btn_next, R.id.fab_add_picture})
    public void goToMap(View view) {
        switch (view.getId()) {
            case R.id.fab_add_picture:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, PermissionsUtils.REQUEST_PERMS_STORAGE, PermissionsUtils.REQUEST_CODE_STORAGE);
                } else {
                    showToast("Not available yet");
                }
                break;
            case R.id.btn_next:
                presenterImp.onRegisterClick();
                break;
        }

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PermissionsUtils.REQUEST_CODE_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showToast("Storage permission accepted");
            } else {
                showToast("Storage permission denied");
            }

        }
    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        datePickerDialog.show();
    }
}

