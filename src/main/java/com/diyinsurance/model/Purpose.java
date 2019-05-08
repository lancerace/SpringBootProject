package com.diyinsurance.model;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Purpose {

	int mPurposeID;
	String mPurposeName;
	
	@JsonIgnore
	DateTime mLastUpdated;
	
	public int getmPurposeID() {
		return mPurposeID;
	}
	public Purpose setmPurposeID(int mPurposeID) {
		this.mPurposeID = mPurposeID;
		return this;
	}
	public String getmPurposeName() {
		return mPurposeName;
	}
	public Purpose setmPurposeName(String mPurposeName) {
		this.mPurposeName = mPurposeName;
		
		return this;
	}
	public DateTime getmLastUpdated() {
		return mLastUpdated;
	}
	public Purpose setmLastUpdated(DateTime mLastUpdated) {
		this.mLastUpdated = mLastUpdated;
		
		return this;
	}

}
