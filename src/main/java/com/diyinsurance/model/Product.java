package com.diyinsurance.model;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//@JsonPropertyOrder({ "mProductID", "mProductName", "mProductDescription", "mFeatures", "mInsurerName", "mPurposeName",
//		"mObjectiveName", "mTypeName", "mWhyBuy", "mPayOut", "mCashPayOutFrequency", "mCoverageDuration",
//		"mPremiumDuration", "mUnderwriting", "mPromotionName", "mStatus", "mAuthorized", "mRebate", "mCashValue" })

@JsonPropertyOrder({ "mProductID", "mProductName", "mProductDescription", "mFeatures", "mWhyBuy", "mPayOut",
		"mCashPayOutFrequency", "mCoverageDuration", "mPremiumDuration", "mUnderwriting", "mStatus", "mAuthorized",
		"mRebate", "mCashValue", "mInsurer", "mPurpose","mObjective", "mType","mPromotion"})

public class Product {
	String mProductID, mProductName, mProductDescription, mWhyBuy, mPayOut, mUnderwriting, mRebate, mCashValue,
			mCashPayOutFrequency, mCoverageDuration, mPremiumDuration, mFeatures, mStatus;

	// String mInsurerName, mPurposeName, mObjectiveName, mTypeName,
	// mPromotionName;

	Insurer mInsurer;
	Purpose mPurpose;
	Objective mObjective;
	TypeObject mType;
	Promotion mPromotion;

	int mAuthorized;
	@JsonIgnore
	int mSearchCount;
	@JsonIgnore
	DateTime mLastUpdated;

	public String getmProductID() {
		return mProductID;
	}

	// public String getmPromotionName() {
	// return mPromotionName;
	// }
	//
	// public Product setmPromotionName(String mPromotionName) {
	// this.mPromotionName = mPromotionName;
	// return this;
	// }
	//
	// public String getmInsurerName() {
	// return mInsurerName;
	// }
	//
	// public Product setmInsurerName(String mInsurerName) {
	// this.mInsurerName = mInsurerName;
	// return this;
	// }

	public Insurer getmInsurer() {
		return mInsurer;
	}

	public void setmInsurer(Insurer mInsurer) {
		this.mInsurer = mInsurer;
	}

	public Purpose getmPurpose() {
		return mPurpose;
	}

	public void setmPurpose(Purpose mPurpose) {
		this.mPurpose = mPurpose;
	}

	public Objective getmObjective() {
		return mObjective;
	}

	public void setmObjective(Objective mObjective) {
		this.mObjective = mObjective;
	}

	public TypeObject getmType() {
		return mType;
	}

	public void setmType(TypeObject mType) {
		this.mType = mType;
	}

	public Promotion getmPromotion() {
		return mPromotion;
	}

	public void setmPromotion(Promotion mPromotion) {
		this.mPromotion = mPromotion;
	}

	// public String getmPurposeName() {
	// return mPurposeName;
	// }
	//
	// public Product setmPurposeName(String mPurposeName) {
	// this.mPurposeName = mPurposeName;
	// return this;
	// }
	//
	// public String getmObjectiveName() {
	// return mObjectiveName;
	// }
	//
	// public Product setmObjectiveName(String mObjectiveName) {
	// this.mObjectiveName = mObjectiveName;
	// return this;
	// }
	//
	// public String getmTypeName() {
	// return mTypeName;
	// }
	//
	// public Product setmTypeName(String mTypeName) {
	// this.mTypeName = mTypeName;
	// return this;
	// }

	public void setmProductID(String mProductID) {
		this.mProductID = mProductID;
	}

	public String getmProductName() {
		return mProductName;
	}

	public void setmProductName(String mProductName) {
		this.mProductName = mProductName;
	}

	public String getmProductDescription() {
		return mProductDescription;
	}

	public void setmProductDescription(String mProductDescription) {
		this.mProductDescription = mProductDescription;
	}

	public int getmSearchCount() {
		return mSearchCount;
	}

	public void setmSearchCount(int mSearchCount) {
		this.mSearchCount = mSearchCount;
	}

	public String getmWhyBuy() {
		return mWhyBuy;
	}

	public void setmWhyBuy(String mWhyBuy) {
		this.mWhyBuy = mWhyBuy;
	}

	public String getmPayOut() {
		return mPayOut;
	}

	public void setmPayOut(String mPayOut) {
		this.mPayOut = mPayOut;
	}

	public String getmUnderwriting() {
		return mUnderwriting;
	}

	public void setmUnderwriting(String mUnderwriting) {
		this.mUnderwriting = mUnderwriting;
	}

	public String getmRebate() {
		return mRebate;
	}

	public void setmRebate(String mRebate) {
		this.mRebate = mRebate;
	}

	public String getmCashValue() {
		return mCashValue;
	}

	public void setmCashValue(String mCashValue) {
		this.mCashValue = mCashValue;
	}

	public String getmCashPayOutFrequency() {
		return mCashPayOutFrequency;
	}

	public void setmCashPayOutFrequency(String mCashPayOutFrequency) {
		this.mCashPayOutFrequency = mCashPayOutFrequency;
	}

	public String getmCoverageDuration() {
		return mCoverageDuration;
	}

	public void setmCoverageDuration(String mCoverageDuration) {
		this.mCoverageDuration = mCoverageDuration;
	}

	public String getmPremiumDuration() {
		return mPremiumDuration;
	}

	public void setmPremiumDuration(String mPremiumDuration) {
		this.mPremiumDuration = mPremiumDuration;
	}

	public String getmFeatures() {
		return mFeatures;
	}

	public void setmFeatures(String mFeatures) {
		this.mFeatures = mFeatures;
	}

	public String getmStatus() {
		return mStatus;
	}

	public void setmStatus(String mStatus) {
		this.mStatus = mStatus;
	}

	public int getmAuthorized() {
		return mAuthorized;
	}

	public void setmAuthorized(int mAuthorized) {
		this.mAuthorized = mAuthorized;
	}

	public DateTime getmLastUpdated() {
		return mLastUpdated;
	}

	public void setmLastUpdated(DateTime mLastUpdated) {
		this.mLastUpdated = mLastUpdated;
	}

	// public Insurer getmInsurer() {
	// return mInsurer;
	// }
	// public void setmInsurer(Insurer mInsurer) {
	// this.mInsurer = mInsurer;
	// }
	// public Purpose getmPurpose() {
	// return mPurpose;
	// }
	// public void setmPurpose(Purpose mPurpose) {
	// this.mPurpose = mPurpose;
	// }
	// public Objective getmObjective() {
	// return mObjective;
	// }
	// public void setmObjective(Objective mObjective) {
	// this.mObjective = mObjective;
	// }
	// public TypeObject getmType() {
	// return mType;
	// }
	// public void setmType(TypeObject mType) {
	// this.mType = mType;
	// }
	// public Promotion getmPromotion() {
	// return mPromotion;
	// }
	// public void setmPromotion(Promotion mPromotion) {
	// this.mPromotion = mPromotion;
	// }

	@Override
	public String toString() {

		return "Product name is : " + this.mProductName;

	}
}
