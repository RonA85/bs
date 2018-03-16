package com.bestfriend.ui.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by mac on 16/03/2018.
 */

public class PermissionsUtils {
    public static final int REQUEST_CODE_LOCATION = 0x1;
    public static final String[] REQUEST_PERMS_LOCATION = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    public static boolean verifyPermissionLocationServices(final Activity activity) {
        int permissions = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) +
                ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION);
        if(permissions != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    activity,
                    REQUEST_PERMS_LOCATION,
                    REQUEST_CODE_LOCATION);
            return false;
        }
        return true;
    }

    public static final int REQUEST_CODE_STORAGE = 0x2;


    public static final String[] REQUEST_PERMS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    public static final int REQUEST_CODE_RECORD = 0x3;

    public static final int REQUEST_CODE_CAMERA = 0x4;
}
