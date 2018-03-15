package com.bestfriend.model;


import com.google.gson.annotations.SerializedName;

/**
 * Created by Avishay on 06/03/2018.
 */

//@Entity
public class Park
{
//	@PrimaryKey
	@SerializedName("_id")
	private String id;

//	@ColumnInfo(name = "park_name")
	@SerializedName("name")
	private String parkName;

//	@ColumnInfo(name = "park_address")
	@SerializedName("address")
	private String address;

    @SerializedName("lat")
    private double lat;
    @SerializedName("lng")
    private double lng;


    //Test Constructor
	public Park(String id, String parkName, String address, double lat, double lng) {
		this.id = id;
		this.parkName = parkName;
		this.address = address;
		this.lat = lat;
		this.lng = lng;
	}

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
