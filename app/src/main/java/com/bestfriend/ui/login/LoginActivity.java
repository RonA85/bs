package com.bestfriend.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.bestfriend.R;
import com.bestfriend.ui.base.BaseActivity;
import com.bestfriend.ui.maps.MapsActivity;
import com.facebook.FacebookSdk;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

public class LoginActivity extends BaseActivity {

    private static final int RC_SIGN_IN = 9017;
    private static final long SPLASH_DELAY = 1 * 1000;

    // Choose authentication providers
    List<AuthUI.IdpConfig> providers = Arrays.asList(
          /*  new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
            new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build(),*/
            new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
            new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create and launch sign-in intent
        startSplashTimer();

    }

    private void startSplashTimer() {
        Handler timerHandler = new Handler();
        timerHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //TODO: allow it after working on map
//                startActivityForResult(
//                        AuthUI.getInstance()
//                                .createSignInIntentBuilder()
//                                .setAvailableProviders(providers)
//                                .setLogo(R.mipmap.splash)
//                                .build(),
//                        RC_SIGN_IN);

                goToMapsActivity();
            }
        }, SPLASH_DELAY);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                goToMapsActivity();
                // ...
            } else {
                // Sign in failed, check response for error code
                // ...
            }
        }
    }

    private void goToMapsActivity() {
        Intent mapsIntent = new Intent(this, MapsActivity.class);
        startActivity(mapsIntent);
        finish();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initUi() {

    }

    @Override
    public Context getActivityContext() {
        return this;
    }
}
