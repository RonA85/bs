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
	@SerializedName("name")
	private String dogName;
	@SerializedName("age")
	private String dogAge;
	@SerializedName("breed")
	private String dogBreed;
	@SerializedName("gender")
	private String dogGender;
	@SerializedName("birth")
	private String dogBirth;
	@SerializedName("userId")
	private int userId;
	@SerializedName("images")
	private List<String> images;

	public Dog(String name,String breed, String birth, String gender) {
		this.dogName = name;
		this.dogBreed = breed;
		this.dogBirth = birth;
		this.dogGender = dogGender;
	}

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

	public void setDogName(String dogName) {
		this.dogName = dogName;
	}

	public String getDogGender() {
		return dogGender;
	}

	public void setDogGender(String dogGender) {
		this.dogGender = dogGender;
	}

	public String getDogBirth() {
		return dogBirth;
	}

	public void setDogBirth(String dogBirth) {
		this.dogBirth = dogBirth;
	}
}
