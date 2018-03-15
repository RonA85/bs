package com.bestfriend.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Avishay on 06/03/2018.
 */

@Entity
public class Park
{
	@PrimaryKey
	@SerializedName("id")
	private String id;

	@ColumnInfo(name = "park_name")
	@SerializedName("parkName")
	private String parkName;

	@ColumnInfo(name = "park_address")
	@SerializedName("address")
	private String address;

	@ColumnInfo(name = "park_latlng")
	@SerializedName("parkLatLng")
	private LatLng parkLatLng;
	
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
	
	public LatLng getParkLatLng()
	{
		return parkLatLng;
	}
}
