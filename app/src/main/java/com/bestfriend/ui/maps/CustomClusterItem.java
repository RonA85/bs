package com.bestfriend.ui.maps;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by Avishay Peretz on 3/15/2018.
 */

public class CustomClusterItem implements ClusterItem {
    private final LatLng mPosition;
    private String mTitle="garden";
    private String mSnippet="descr";

    public CustomClusterItem(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
    }

    public CustomClusterItem(double lat, double lng, String title, String snippet) {
        mPosition = new LatLng(lat, lng);
        mTitle = title;
        mSnippet = snippet;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getSnippet() {
        return mSnippet;
    }
}
