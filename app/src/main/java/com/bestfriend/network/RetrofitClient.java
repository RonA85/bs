package com.bestfriend.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hilama on 15/03/2018.
 */

public class RetrofitClient {

    private static Retrofit instance;

    public static Retrofit getInstance (){
        if (instance== null){
             instance = new Retrofit.Builder()
                    .baseUrl(UsersApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }
}
