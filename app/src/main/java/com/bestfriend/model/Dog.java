package com.bestfriend.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Avishay on 06/03/2018.
 */

public class Dog
{
	@SerializedName("id")
	private String id;
	@SerializedName("dogName")
	private String dogName;
	@SerializedName("userId")
	private int userId;
	@SerializedName("images")
	private List<String> images;
	
	
	public String getId()
	{
		return id;
	}
	
	public String getDogName()
	{
		return dogName;
	}
	
	public int getUserId()
	{
		return userId;
	}
	
	public List<String> getImages()
	{
		return images;
	}
}
