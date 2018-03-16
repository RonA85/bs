package com.bestfriend.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Avishay on 06/03/2018.
 */

public class Dog implements Parcelable {
//	@SerializedName("id")
//	private String id;
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
	@SerializedName("image")
	private  String  image;

	public Dog(String name,String breed, String birth, String gender) {
		this.dogName = name;
		this.dogBreed = breed;
		this.dogBirth = birth;
		this.dogGender = gender;
	}

//	public String getId()
//	{
//		return id;
//	}
	
	public String getDogName()
	{
		return dogName;
	}
	
	public int getUserId()
	{
		return userId;
	}
	
//	public List<String> getImages()
//	{
//		return images;
//	}

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

	public String getDogAge() {
	return dogAge;
	}

	public void setDogAge(String dogAge) {
		this.dogAge = dogAge;
	}


	public String getImage() {
		return image;
	}

	public String getDogBreed() {
		return dogBreed;
	}

	public void setDogBreed(String dogBreed) {
		this.dogBreed = dogBreed;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.dogName);
		dest.writeString(this.dogBreed);
		dest.writeString(this.dogGender);
		dest.writeString(this.dogBirth);
		dest.writeInt(this.userId);
		dest.writeString(this.image);
	}

	protected Dog(Parcel in) {
		this.dogName = in.readString();
		this.dogBreed = in.readString();
		this.dogGender = in.readString();
		this.dogBirth = in.readString();
		this.userId = in.readInt();
		this.image = in.readString();
	}

	public static final Parcelable.Creator<Dog> CREATOR = new Parcelable.Creator<Dog>() {
		@Override
		public Dog createFromParcel(Parcel source) {
			return new Dog(source);
		}

		@Override
		public Dog[] newArray(int size) {
			return new Dog[size];
		}
	};
}
