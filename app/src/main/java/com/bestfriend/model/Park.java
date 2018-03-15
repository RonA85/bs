package com.bestfriend.model;


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
    private double lat;
    @SerializedName("lng")
    private double lng;

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

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
