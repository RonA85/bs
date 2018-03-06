package com.bestfriend.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Avishay on 06/03/2018.
 */

public class Park
{
	@SerializedName("id")
	private String id;
	@SerializedName("parkName")
	private String parkName;
	@SerializedName("address")
	private String address;
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
