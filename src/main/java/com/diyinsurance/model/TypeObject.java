package com.diyinsurance.model;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TypeObject {

	int mTypeID;
	String mTypeName;
	
	@JsonIgnore
	DateTime mLastUpdated;
	
	public int getmTypeID() {
		return mTypeID;
	}
	public TypeObject setmTypeID(int mTypeID) {
		this.mTypeID = mTypeID;
		
		return this;
	}
	public String getmTypeName() {
		return mTypeName;
	}
	public TypeObject setmTypeName(String mTypeName) {
		this.mTypeName = mTypeName;
		
		return this;
	}
	public DateTime getmLastUpdated() {
		return mLastUpdated;
	}
	public TypeObject setmLastUpdated(DateTime mLastUpdated) {
		this.mLastUpdated = mLastUpdated;
		
		return this;
	}

}
