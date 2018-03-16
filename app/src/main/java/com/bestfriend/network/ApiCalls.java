package com.bestfriend.network;

import android.util.Log;

import com.bestfriend.model.Dog;
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
        ApiService api = RetrofitClient.getInstance().create(ApiService.class);
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
        ApiService api = RetrofitClient.getInstance().create(ApiService.class);
        Call<List<User>> call = api.loadUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                List<User> usersList = response.body();
                observer.onRecieved(usersList);

                Log.d(TAG, "onResponse: \n" +
                        "name: " + usersList.get(0).getId() + "\n" +
                        "address: " + usersList.get(0).getFullName() + "\n" );
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d(TAG, "onFailure" + "\n" );
            }
        });
    }

    public static void createNewUser(final DataObserver<User> observer, final User user){
        ApiService api = RetrofitClient.getInstance().create(ApiService.class);
        Call<User> call = api.createUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user1 = response.body();
                observer.onRecieved(user1);
                Log.d(TAG, "onResponse: \n" +
//                        "name: " + user.getFullName() + "\n" +
//                        "gender: " + dog1.getDogGender() + "\n" +
                        "name: " + user.getFullName() + "\n" );
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

//    public static void uploadDogDetails(){
//        ApiService api = RetrofitClient.getInstance().create(ApiService.class);
//        Dog dog = new Dog("Dubi","3","male");
//        Call<Dog> call = api.createDog(dog);
//        call.enqueue(new Callback<Dog>() {
//            @Override
//            public void onResponse(Call<Dog> call, Response<Dog> response) {
//                Dog dog1 = response.body();
//                Log.d(TAG, "onResponse: \n" +
//                        "name: " + dog1.getDogName() + "\n" +
//                        "gender: " + dog1.getDogGender() + "\n" +
//                        "age: " + dog1.getDogAge() + "\n" );
//            }
//
//            @Override
//            public void onFailure(Call<Dog> call, Throwable t) {
//
//            }
//        });
//    }
}
