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
	@SerializedName("fullName")
	private String fullName;
	@SerializedName("dog")
	private Dog dog;
//	@SerializedName("lastName")
//	private String lastName;
	@SerializedName("imageProfile")
	private String imageProfile;
	@SerializedName("email")
	private String email;
	@SerializedName("phone")
	private String phone;
	@SerializedName("lat")
	private double lat;
	@SerializedName("lng")
	private double lng;
	@SerializedName("dogList")
	private List<Dog> dogList;
	@SerializedName("images")
	private List<String> images;

	//Test constructor
	public User(String id, String fullName, String email, String phone, double lat, double lng, List<Dog> dogList, List<String> images) {
		this.id = id;
		this.fullName = fullName;
		//this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.lat = lat;
		this.lng = lng;
		this.dogList = dogList;
		this.images = images;
	}

	public User(String fullName, Dog dog) {
		this.fullName = fullName;
		this.dog = dog;
	}

	public String getId()
	{
		return id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}

	public String getImageProfile() {
		return imageProfile;
	}

	public void setImageProfile(String imageProfile) {
		this.imageProfile = imageProfile;
	}

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}
}
