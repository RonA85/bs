package com.bestfriend.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bestfriend.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by Avishay Peretz on 3/15/2018.
 */

public class InfoWindowPinGardenAdapter implements GoogleMap.InfoWindowAdapter {

    private LayoutInflater layoutInflater;

    public InfoWindowPinGardenAdapter(LayoutInflater inflater){
        this.layoutInflater = inflater;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = layoutInflater.inflate(R.layout.info_window_pin_garden, null);

        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title_pin_garden);
//        TextView tvSubTitle = (TextView) view.findViewById(R.id.tv_subtitle);

        tvTitle.setText(marker.getTitle());
//        tvSubTitle.setText(marker.getSnippet());

        return view;
    }
}