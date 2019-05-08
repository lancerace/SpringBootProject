package com.diyinsurance.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "mTagID","mTagName" })
public class Tag {
	
	int mTagID;
	String mTagName;
	public int getmTagID() {
		return mTagID;
	}
	public Tag setmTagID(int mTagID) {
		this.mTagID = mTagID;
		
		return this;
	}
	public String getmTagName() {
		return mTagName;
	}
	public Tag setmTagName(String mTagName) {
		this.mTagName = mTagName;
		
		return this;
	}
	

}
