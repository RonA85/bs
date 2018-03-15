package com.bestfriend.network;

import com.bestfriend.model.Park;
import com.bestfriend.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by hilama on 15/03/2018.
 */

public interface UsersApi {
    String BASE_URL= "https://bestfriendapp.herokuapp.com/";

    @GET("parks")
    Call<List<Park>> loadParks();

    @GET("Users")
    Call<List<User>> loadUsers();
}
