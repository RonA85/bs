package com.bestfriend.ui.maps;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bestfriend.R;
import com.bestfriend.model.Park;
import com.bestfriend.model.User;
import com.bestfriend.ui.Activities.ProfileActivity;
import com.bestfriend.ui.adapters.InfoWindowPinGardenAdapter;
import com.bestfriend.ui.base.BaseActivity;
import com.bestfriend.ui.login.LoginActivity;
import com.bestfriend.ui.utils.Constants;
import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.location.places.GeoDataClient;
//import com.google.android.gms.location.places.PlaceDetectionClient;
//import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.android.clustering.ClusterManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Avishay on 06/03/2018.
 */
public class MapsActivity extends BaseActivity implements OnMapReadyCallback, MapsContract.View {

    private static final float DEFAULT_ZOOM = 13.0f;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 7485;

    private GoogleMap mMap;
    private boolean mLocationPermissionGranted;
    private LatLng mDefaultLocation = new LatLng(32.070080, 34.794145);
    private Location mLastKnownLocation;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    //	private GeoDataClient mGeoDataClient;
    //	private PlaceDetectionClient mPlaceDetectionClient;

    private MapsPresenter mPresenter;
    private BottomSheetBehavior mBottomSheetBehavior;

    @BindView(R.id.rv_users_garden)
     RecyclerView mRvUsersGarden;
    @BindView(R.id. tv_garden_name)
    TextView mTvGardenName;
    @BindView(R.id. tv_garden_address)
    TextView mTvGardenAddress;
    @BindView(R.id. iv_cover_img_garden)
    ImageView mIvCoverGarden;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter == null) {
            mPresenter = new MapsPresenter();
        }
        mPresenter.attachView(this);
        // Construct a GeoDataClient.
        //		mGeoDataClient = Places.getGeoDataClient(this, null);

        // Construct a PlaceDetectionClient.
        //		mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);

        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_maps;
    }

    @Override
    protected void initUi() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // get the bottom sheet view
        LinearLayout llBottomSheet = (LinearLayout) findViewById(R.id.bottom_sheet);

        // init the bottom sheet behavior
        mBottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);

        // change the state of the bottom sheet
//        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);


        mRvUsersGarden.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }


    private void getLocationPermission() {
    /*
     * Request location permission, so that we can get the location of the
     * device. The result of the permission request is handled by a callback,
     * onRequestPermissionsResult.
     */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        setupMap(googleMap);

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();

        // Get the current location of the device and set the position of the map.
        getDeviceLocation();

//        mPresenter.setUsersAsMarkersOnMap();
//        mPresenter.setParksAsMarkersOnMap();
    }

    private void setupMap(GoogleMap googleMap) {
        mMap = googleMap;
        //Set Custom InfoWindow Adapter
        InfoWindowPinGardenAdapter adapter = new InfoWindowPinGardenAdapter(getLayoutInflater());
        mMap.setInfoWindowAdapter(adapter);
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker != null && marker.getTag() != null) {
//                    if ((Integer) marker.getTag() == 1) {
                        marker.showInfoWindow();
//                    } else {
                        return false;
//                    }
                }
                return true;
            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if (marker != null && marker.getTag() != null) {
                        showGardenDetailsScreen((Park) marker.getTag());

                }
            }
        });

        setUpClusterer();
    }

    private void showGardenDetailsScreen(Park park) {
        Log.d("Maina", "showGardenDetailsScreen");
        mTvGardenName.setText(park.getParkName());
        mTvGardenAddress.setText(park.getAddress());
        Glide.with(this).load(park.getImage()).into(mIvCoverGarden);

        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }


    private void getDeviceLocation() {
    /*
     * Get the best and most recent location of the device, which may be null in rare
     * cases when a location is not available.
     */
        try {
            if (mLocationPermissionGranted) {
                Task locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = (Location) task.getResult();
                            LatLng latLng = new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM));
                            mMap.addMarker(new MarkerOptions().position(latLng));
                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }


    @Override
    public Context getActivityContext() {
        return this;
    }


    @OnClick(R.id.btn_logout_maps)
    protected void doLogout() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        goToLoginActivity();
                    }
                });
    }

    private void goToLoginActivity() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    @Override
    public RecyclerView getRvUsersGarden() {
        return mRvUsersGarden;
    }

    @Override
    public GoogleMap getMap() {
        return mMap;
    }

    @Override
    public ClusterManager getClusterManager() {
        return mClusterManager;
    }

    @Override
    public void moveToProfileScreen(User user) {
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        profileIntent.putExtra(Constants.USER_DATA_KEY, user);
        startActivity(profileIntent);
    }


    // Declare a variable for the cluster manager.
    private ClusterManager<CustomClusterItem> mClusterManager;

    private void setUpClusterer() {
        // Position the map.
//        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.503186, -0.126446), 10));

        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        mClusterManager = new ClusterManager<CustomClusterItem>(this, mMap);

        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
//        getMap().setOnCameraIdleListener(mClusterManager);
//        getMap().setOnMarkerClickListener(mClusterManager);
//
//        // Add cluster items (markers) to the cluster manager.
//        addItems();
    }

    private void addItems() {

        // Set some lat/lng coordinates to start with.
        double lat = 51.5145160;
        double lng = -0.1270060;

        // Add ten cluster items in close proximity, for purposes of this example.
        for (int i = 0; i < 10; i++) {
            double offset = i / 60d;
            lat = lat + offset;
            lng = lng + offset;
            CustomClusterItem offsetItem = new CustomClusterItem(lat, lng);
            mClusterManager.addItem(offsetItem);
        }
    }

}
