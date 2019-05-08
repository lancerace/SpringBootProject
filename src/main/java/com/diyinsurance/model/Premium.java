package com.diyinsurance.model;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "mGender", "mMinAge", "mCoverageName", "mDurationName", "mSavingduration", "mPremiumTerm",
		"mPremiumAmount", "mRetirementPayOutAmount", "mRetirementPayoutDuration", "mRanking", "mPremID" })
// @JsonPropertyOrder,short form of manipulating the order of jsonString that is
// passed to the client rather than going through
// the more messy way by instantiating new LinkedHashMap(); and invoking
// x number of .put() based on the number of properties for the model e.g
// (obj.put("mGender", );bj.put("mAge", value)obj.put("mCoverage", value) )

public class Premium {

	@JsonIgnore
	String mProductID;
	String mGender, mCoverageName, mDurationName, mPremiumTerm, mSavingduration, mRetirementPayOutAmount,
			mRetirementPayoutDuration;

	int mMinAge, mPremiumAmount, mRanking, mPremID;
	@JsonIgnore
	DateTime mLastUpdated;

	public int getmPremID() {
		return mPremID;
	}

	public Premium setmPremID(int mPremID) {
		this.mPremID = mPremID;

		return this;
	}

	public String getmProductID() {
		return mProductID;
	}

	public Premium setmProductID(String mProductID) {
		this.mProductID = mProductID;

		return this;
	}

	public String getmGender() {
		return mGender;
	}

	public Premium setmGender(String mGender) {
		this.mGender = mGender;

		return this;
	}

	public String getmCoverageName() {
		return mCoverageName;
	}

	public Premium setmCoverageName(String mCoverageName) {
		this.mCoverageName = mCoverageName;

		return this;
	}

	public String getmDurationName() {
		return mDurationName;
	}

	public Premium setmDurationName(String mDurationName) {
		this.mDurationName = mDurationName;

		return this;
	}

	public String getmPremiumTerm() {
		return mPremiumTerm;
	}

	public Premium setmPremiumTerm(String mPremiumTerm) {
		this.mPremiumTerm = mPremiumTerm;

		return this;
	}

	public String getmSavingduration() {
		return mSavingduration;
	}

	public Premium setmSavingduration(String mSavingduration) {
		this.mSavingduration = mSavingduration;

		return this;
	}

	public String getmRetirementPayOutAmount() {
		return mRetirementPayOutAmount;
	}

	public Premium setmRetirementPayOutAmount(String mRetirementPayOutAmount) {
		this.mRetirementPayOutAmount = mRetirementPayOutAmount;

		return this;
	}

	public String getmRetirementPayoutDuration() {
		return mRetirementPayoutDuration;
	}

	public Premium setmRetirementPayoutDuration(String mRetirementPayoutDuration) {
		this.mRetirementPayoutDuration = mRetirementPayoutDuration;

		return this;
	}

	public int getmMinAge() {
		return mMinAge;
	}

	public Premium setmMinAge(int mMinAge) {
		this.mMinAge = mMinAge;

		return this;
	}

	public int getmPremiumAmount() {
		return mPremiumAmount;
	}

	public Premium setmPremiumAmount(int mPremiumAmount) {
		this.mPremiumAmount = mPremiumAmount;

		return this;
	}

	public int getmRanking() {
		return mRanking;
	}

	public Premium setmRanking(int mRanking) {
		this.mRanking = mRanking;

		return this;
	}

	public DateTime getmLastUpdated() {
		return mLastUpdated;
	}

	public Premium setmLastUpdated(DateTime mLastUpdated) {
		this.mLastUpdated = mLastUpdated;

		return this;
	}

}
