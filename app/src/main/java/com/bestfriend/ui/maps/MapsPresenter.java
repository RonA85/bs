package com.bestfriend.ui.maps;

import android.util.Log;

import com.bestfriend.model.Park;
import com.bestfriend.R;
import com.bestfriend.model.Park;
import com.bestfriend.model.User;
import com.bestfriend.network.ApiCalls;
import com.bestfriend.network.DataObserver;
import com.bestfriend.ui.base.BasePresenter;
import com.bestfriend.ui.base.BaseView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Avishay on 07/03/2018.
 */

public class MapsPresenter extends BasePresenter<MapsContract.View> implements MapsContract.Presenter {

    private List<User> mUsersList;
    private List<Park> mParksList;

    @Override
    public void attachView(MapsContract.View view) {
        super.attachView(view);
        loadData();
    }

    public void setUsersAsMarkersOnMap() {
        if (mUsersList != null) {
            GoogleMap map = getView().getMap();
            for (User user : mUsersList) {
                LatLng latLng = new LatLng(user.getLat(), user.getLng());
                map.addMarker(new MarkerOptions().position(latLng).title(user.getFirstName())
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_user)));
            }

        }
    }

    public void setParksAsMarkersOnMap() {
        if (mParksList != null) {
            GoogleMap map = getView().getMap();
            for (Park park : mParksList) {
                LatLng latLng = new LatLng(park.getLat(), park.getLng());
                Marker marker = map.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(park.getParkName())
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.transparent_pin)));
                marker.showInfoWindow(); // always show marker info
            }

//            PolylineOptions rectOptions = new PolylineOptions()
//                    .add(new LatLng(32.053365, 34.785400))
//                    .add(new LatLng(32.052251, 34.785400))  // North of the previous point, but at the same longitude
//                    .add(new LatLng(32.052251, 34.788157))  // Same latitude, and 30km to the west
//                    .add(new LatLng(32.0472269, 34.788157))  // Same longitude, and 16km to the south
//                    .add(new LatLng(32.053365, 34.785400))  // Closes the polyline.
//                  ; // Closes the polyline.
//            map.addPolyline(rectOptions);
        }
    }


    private void loadData() {
//        loadUsers();
//        loadParks();
        loadUsersTest();
        loadParksTest();
    }

    private void loadParksTest() {
        mParksList = new ArrayList<>();
        mParksList.add(new Park("1", "Google park", "Alon 98 TLV", 32.070080, 34.794145));

    }

    private void loadParks() {
          /*TODO Hila : get the parks from the server
        * */
        DataObserver<List<Park>> observer = new DataObserver<List<Park>>() {
            @Override
            public void onRecieved(List<Park> parks) {
                Log.d("Parks recieved", "Recieved!" + parks.toString());
            }
        };

        ApiCalls.getParks(observer);
    }


    private void loadUsersTest() {
        mUsersList = new ArrayList<>();
        mUsersList.add(new User("1", "itay", "cobo", "itay@gmail.com", "0123", 32.070080f, 34.794145f, null, null));
        mUsersList.add(new User("2", "itay", "cobo", "itay@gmail.com", "0123", 32.070085f, 34.794155f, null, null));
        mUsersList.add(new User("3", "itay", "cobo", "itay@gmail.com", "0123", 32.070090f, 34.794165f, null, null));
        mUsersList.add(new User("4", "itay", "cobo", "itay@gmail.com", "0123", 32.070280f, 34.794155f, null, null));
        mUsersList.add(new User("5", "itay", "cobo", "itay@gmail.com", "0123", 32.070180f, 34.794165f, null, null));
    }

    @Override
    public void loadUsers() {
        /*TODO Hila : get the users from the server
        *       then, parse the users to the mUsersList
        * */
        DataObserver<List<User>> observer = new DataObserver<List<User>>() {
            @Override
            public void onRecieved(List<User> users) {
                Log.d("Parks recieved", "Recieved!" + users.toString());
            }
        };

        ApiCalls.getUsers(observer);

    }


}
