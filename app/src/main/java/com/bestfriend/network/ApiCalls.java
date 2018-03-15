package com.bestfriend.network;

import android.util.Log;

import com.bestfriend.model.Park;
import com.bestfriend.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hilama on 15/03/2018.
 */

public class ApiCalls {

    private static final String TAG = "ApiCalls";

    public static void getParks (final DataObserver<List<Park>> observer){
        UsersApi api = RetrofitClient.getInstance().create(UsersApi.class);
        Call<List<Park>> call = api.loadParks();

        call.enqueue(new Callback<List<Park>>() {
            @Override
            public void onResponse(Call<List<Park>> call, Response<List<Park>> response) {

                List<Park> parksList = response.body();
                observer.onRecieved(parksList);

                Log.d(TAG, "onResponse: \n" +
                        "name: " + parksList.get(0).getParkName() + "\n" +
                        "address: " + parksList.get(0).getAddress() + "\n" );
            }

            @Override
            public void onFailure(Call<List<Park>> call, Throwable t) {
                Log.d(TAG, "onFailure" + "\n" );
            }
        });
    }

    public static void getUsers (final DataObserver<List<User>> observer){
        UsersApi api = RetrofitClient.getInstance().create(UsersApi.class);
        Call<List<User>> call = api.loadUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                List<User> usersList = response.body();
                observer.onRecieved(usersList);

                Log.d(TAG, "onResponse: \n" +
                        "name: " + usersList.get(0).getId() + "\n" +
                        "address: " + usersList.get(0).getFirstName() + "\n" );
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d(TAG, "onFailure" + "\n" );
            }
        });
    }
}
