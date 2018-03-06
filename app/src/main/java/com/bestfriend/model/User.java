package com.bestfriend.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Avishay on 06/03/2018.
 */

public class User
{
	@SerializedName("id")
	private String id;
	@SerializedName("firstName")
	private String firstName;
	@SerializedName("lastName")
	private String lastName;
	@SerializedName("email")
	private String email;
	@SerializedName("phone")
	private String phone;
	@SerializedName("dogList")
	private List<Dog> dogList;
	@SerializedName("images")
	private List<String> images;
	
	public String getId()
	{
		return id;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getPhone()
	{
		return phone;
	}
	
	public List<Dog> getDogList()
	{
		return dogList;
	}
	
	public List<String> getImages()
	{
		return images;
	}
}
