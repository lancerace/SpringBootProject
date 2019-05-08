package com.diyinsurance.model;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;



public class Insurer {
	
String mInsurerID,mInsurerName;
@JsonIgnore 
String mLogo;
@JsonIgnore 
String mURL;
@JsonIgnore 
DateTime mLastUpdated;


public String getmInsurerID() {
	return mInsurerID;
}
public Insurer setmInsurerID(String mInsurerID) {
	this.mInsurerID = mInsurerID;
	
	return this;
}
public String getmInsurerName() {
	return mInsurerName;
}
public Insurer setmInsurerName(String mInsurerName) {
	this.mInsurerName = mInsurerName;
	return this;
}
public String getmLogo() {
	return mLogo;
}
public Insurer setmLogo(String mLogo) {
	this.mLogo = mLogo;	
	return this;
}

public String getmURL() {
	return mURL;
}
public Insurer setmURL(String mURL) {
	this.mURL = mURL;
	return this;
}

public DateTime getmLastUpdated() {
	return mLastUpdated;
}
public Insurer setmLastUpdated(DateTime mLastUpdated) {
	this.mLastUpdated = mLastUpdated;
	
	return this;
}


@Override
public String toString(){
	
	return this.getmInsurerID() + ":" + this.getmInsurerName() + ":"  + this.getmLogo()+ ":" + this.getmURL()
	+ ":" + this.getmLastUpdated();
	
}
}
