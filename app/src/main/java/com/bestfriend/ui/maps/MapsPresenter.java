package com.bestfriend.ui.maps;

import android.graphics.Color;
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
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.clustering.ClusterManager;

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
                Marker marker = map.addMarker(
                        new MarkerOptions().position(latLng)

                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_user)));
                marker.setTag(0);
            }


//            ClusterManager clusterManager = getView().getClusterManager();
//            // Add ten cluster items in close proximity, for purposes of this example.
//            for (User user : mUsersList) {
//                CustomClusterItem offsetItem = new CustomClusterItem(user.getLat(), user.getLng());
//                clusterManager.addItem(offsetItem);
//            }


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
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.oval)));
//                marker.showInfoWindow(); // always show marker info
                marker.setTag(1);
            }

//            for (Park park : mParksList) {
//                LatLng latLng = new LatLng(park.getLat(), park.getLng());
//                // Instantiates a new CircleOptions object and defines the center and radius
//                CircleOptions circleOptions = new CircleOptions()
//                        .center(latLng)
//                        .radius(200); // In meters
//
//                // Get back the mutable Circle
//                Circle circle = map.addCircle(circleOptions);
//                circle.setClickable(true);
//                circle.setTag(1);
//                circle.setStrokeColor(Color.RED);
//
//            }

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
        mParksList.add(new Park("2", "Assuta park", "Alon 94 TLV", 32.069578, 34.793731));

    }

    private void loadParks() {
          /*TODO Hila : get the parks from the server
        * */
        DataObserver<List<Park>> observer = new DataObserver<List<Park>>() {
            @Override
            public void onReceived(List<Park> parks) {
                Log.d("Parks recieved", "Recieved!" + parks.toString());
            }
        };

        ApiCalls.getParks(observer);
    }


    private void loadUsersTest() {
        mUsersList = new ArrayList<>();
        mUsersList.add(new User("1", "itay",  "itay@gmail.com", "0123", 32.070080f, 34.794145f, null, null));
        mUsersList.add(new User("2", "itay",  "itay@gmail.com", "0123", 32.070085f, 34.794145f, null, null));
        mUsersList.add(new User("3", "itay",  "itay@gmail.com", "0123", 32.070090f, 34.794145f, null, null));
        mUsersList.add(new User("4", "itay",  "itay@gmail.com", "0123", 32.070080f, 34.794155f, null, null));
        mUsersList.add(new User("5", "itay",  "itay@gmail.com", "0123", 32.070080f, 34.794165f, null, null));
    }

    @Override
    public void loadUsers() {
        /*TODO Hila : get the users from the server
        *       then, parse the users to the mUsersList
        * */
        DataObserver<List<User>> observer = new DataObserver<List<User>>() {
            @Override
            public void onReceived(List<User> users) {
                Log.d("Parks recieved", "Recieved!" + users.toString());
            }
        };

        ApiCalls.getUsers(observer);

    }

    @Override
    public void createDog() {
      //  ApiCalls.uploadDogDetails();
    }


}
