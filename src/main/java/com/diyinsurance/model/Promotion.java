package com.diyinsurance.model;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Promotion {

	int mPromoID;
	@JsonIgnore
	String mInsurerID;
	@JsonIgnore
	String mThumbnail;
	@JsonIgnore
	String mDiscount;
	String mTitle;
	@JsonIgnore
	String mDescription;
	@JsonIgnore
	String mLink;
	//note this is not boolean
	@JsonIgnore
	String mExpired;
	
	@JsonIgnore
	DateTime mExpiryDate;
	@JsonIgnore
	DateTime mLastUpdated; 
	
	public int getmPromoID() {
		return mPromoID;
	}
	public Promotion setmPromoID(int mPromoID) {
		this.mPromoID = mPromoID;
		
		return this;
	}
		
	public String getmInsurerID() {
		return mInsurerID;
	}
	public Promotion setmInsurerID(String mInsurerID) {
		this.mInsurerID = mInsurerID;
		
		return this;
	}
	
	public String getmThumbnail() {
		return mThumbnail;
	}
	public Promotion setmThumbnail(String mThumbnail) {
		this.mThumbnail = mThumbnail;
		
		return this;
	}
	public String getmDiscount() {
		return mDiscount;
	}
	public Promotion setmDiscount(String mDiscount) {
		this.mDiscount = mDiscount;
		
		return this;
	}
	public String getmTitle() {
		return mTitle;
	}
	public Promotion setmTitle(String mTitle) {
		this.mTitle = mTitle;
		
		return this;
	}
	public String getmDescription() {
		return mDescription;
	}
	public Promotion setmDescription(String mDescription) {
		this.mDescription = mDescription;
		
		return this;
	}
	public String getmLink() {
		return mLink;
	}
	public Promotion setmLink(String mLink) {
		this.mLink = mLink;
		
		return this;
	}
	public String getmExpired() {
		return mExpired;
	}
	public Promotion setmExpired(String mExpired) {
		this.mExpired = mExpired;
		
		return this;
	}
	public DateTime getmExpiryDate() {
		return mExpiryDate;
	}
	public Promotion setmExpiryDate(DateTime mExpiryDate) {
		this.mExpiryDate = mExpiryDate;
		
		return this;
	}
	public DateTime getmLastUpdated() {
		return mLastUpdated;
	}
	public Promotion setmLastUpdated(DateTime mLastUpdated) {
		this.mLastUpdated = mLastUpdated;
		
		return this;
	}
	
	
}
