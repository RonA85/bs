package com.bestfriend.ui.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestfriend.R;
import com.bestfriend.model.User;
import com.bestfriend.ui.base.BaseActivity;
import com.bestfriend.ui.utils.Constants;
import com.bumptech.glide.Glide;

import butterknife.ButterKnife;

/**
 * Created by hilama on 16/03/2018.
 */

public class ProfileActivity extends AppCompatActivity {

    private User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (getIntent() != null) {
            user = getIntent().getParcelableExtra(Constants.USER_DATA_KEY);
        }

        ImageView dogImage = findViewById(R.id.dogPicture);
        ImageView userProfileImage = findViewById(R.id.userProfileImage);
        TextView userProfilename = findViewById(R.id.userProfileName);
        TextView dogName = findViewById(R.id.dogName);
        TextView dogGender = findViewById(R.id.dogGender);
        TextView dogBreed = findViewById(R.id.dogBreed);
        TextView dogAge = findViewById(R.id.dogAge);
        TextView dogVaccinated = findViewById(R.id.dogVaccinated);
        TextView dogNeutered = findViewById(R.id.dogNeutered);
        TextView dogFavoritegardens = findViewById(R.id.favoriteGarden);
        TextView dogLoveToDo = findViewById(R.id.dogLoveToDo);


        if (user != null) {
            Glide.with(this).load(user.getImageProfile()).into(userProfileImage);
            userProfilename.setText(user.getFullName());

            if (user.getDog() != null) {
                dogName.setText(user.getDog().getDogName());
                dogGender.setText(user.getDog().getDogGender());
                dogBreed.setText(user.getDog().getDogBreed());
//                dogAge.setText(user.getDog().getDogAge());
                dogVaccinated.setText("Yes");
                dogNeutered.setText("No");
                dogFavoritegardens.setText("Meir Garden (20:00-22:00)");
                dogLoveToDo.setText("What do I love to do? I love to bit my tail and to eat everything I see, especially rocks and balls. If you have food in your hand you are my temporary god and I will supert happy to do everthing you want, including jumping on 2 legs :) ");
                if (user.getDog() != null && !TextUtils.isEmpty(user.getDog().getImage())) {
                    Glide.with(this).load(user.getDog().getImage()).into(dogImage);
                }
            }
        }


    }

}
