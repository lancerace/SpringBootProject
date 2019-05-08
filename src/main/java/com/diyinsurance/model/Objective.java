package com.diyinsurance.model;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Objective {
	int mObjectiveID;
	String mObjectiveName;
	
	@JsonIgnore
	DateTime mLastUpdated;
	
	public int getmObjectiveID() {
		return mObjectiveID;
	}
	public Objective setmObjectiveID(int mObjectiveID) {
		this.mObjectiveID = mObjectiveID;
		return this;
	}
	public String getmObjectiveName() {
		return mObjectiveName;
	}
	public void setmObjectiveName(String mObjectiveName) {
		this.mObjectiveName = mObjectiveName;
	}
	public DateTime getmLastUpdated() {
		return mLastUpdated;
	}
	public void setmLastUpdated(DateTime mLastUpdated) {
		this.mLastUpdated = mLastUpdated;
	}

}
