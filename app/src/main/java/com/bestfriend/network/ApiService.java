package com.bestfriend.network;

import com.bestfriend.model.Dog;
import com.bestfriend.model.Park;
import com.bestfriend.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by hilama on 15/03/2018.
 */

public interface ApiService {

    String BASE_URL= "https://bestfriendapp.herokuapp.com/";

    @GET("parks")
    Call<List<Park>> loadParks();

    @GET("Users")
    Call<List<User>> loadUsers();

    @POST("users/new")
    Call<User> createUser(@Body User user);

    @POST("dogs/new")
    Call<Dog> createDog(@Body Dog dog);

}
