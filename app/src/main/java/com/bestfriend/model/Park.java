package com.bestfriend.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Avishay on 06/03/2018.
 */

//@Entity
public class Park
{
//	@PrimaryKey
	@SerializedName("id")
	private String id;

//	@ColumnInfo(name = "park_name")
	@SerializedName("park_name")
	private String parkName;

//	@ColumnInfo(name = "park_address")
	@SerializedName("park_address")
	private String address;

    @SerializedName("lat")
    private float lat;
    @SerializedName("lng")
    private float lng;

	public String getId()
	{
		return id;
	}

	public String getParkName()
	{
		return parkName;
	}

	public String getAddress()
	{
		return address;
	}

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }
}
