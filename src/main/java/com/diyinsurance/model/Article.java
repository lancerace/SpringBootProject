package com.diyinsurance.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({ "mArticleID","mTitle","mSummary","mKeywords","mTagName","mPublish","mDate","mHtmlMarkup","mThumbnail",
	                      "mProfileID","mTagID"})
public class Article {
	
	int mArticleID, mProfileID, mTagID;
	String mTitle, mSummary, mKeywords, mPublish,mTagName,mThumbnail;
    Date mDate;
    String mHtmlMarkup;

    public Article(int mArticleID, int mProfileID, int mTagID, String mTitle, String mSummary, String mKeywords,
			String mPublish, String mTagName,String mHtmlMarkup,String mThumbnail, Date mDate) {
		this.mArticleID = mArticleID;
		this.mProfileID = mProfileID;
		this.mTagID = mTagID;
		this.mTitle = mTitle;
		this.mSummary = mSummary;
		this.mKeywords = mKeywords;
		this.mPublish = mPublish;
		this.mTagName = mTagName;
		this.mHtmlMarkup = mHtmlMarkup;
		this.mThumbnail = mThumbnail;
		this.mDate = mDate;
	}
    


	public String getmHtmlMarkup() {
		return mHtmlMarkup;
	}



	public void setmHtmlMarkup(String mHtmlMarkup) {
		this.mHtmlMarkup = mHtmlMarkup;
	}



	public String getmThumbnail() {
		return mThumbnail;
	}

	public void setmThumbnail(String mThumbnail) {
		this.mThumbnail = mThumbnail;
	}

	public String getmTagName() {
		return mTagName;
	}

	public void setmTagName(String mTagName) {
		this.mTagName = mTagName;
	}

	public int getmArticleID() {
		return mArticleID;
	}

	public Article setmArticleID(int mArticleID) {
		this.mArticleID = mArticleID;
		
		return this;
	}

	public int getmProfileID() {
		return mProfileID;
	}

	public Article setmProfileID(int mProfileID) {
		this.mProfileID = mProfileID;
		return this;
	}

	public int getmTagID() {
		return mTagID;
	}

	public Article setmTagID(int mTagID) {
		this.mTagID = mTagID;
		return this;
	}

	public String getmTitle() {
		return mTitle;
	}

	public Article setmTitle(String mTitle) {
		this.mTitle = mTitle;
		return this;
	}

	public String getmSummary() {
		return mSummary;
	}

	public Article setmSummary(String mSummary) {
		this.mSummary = mSummary;
		return this;
	}

	public String getmKeywords() {
		return mKeywords;
	}

	public Article setmKeywords(String mKeywords) {
		this.mKeywords = mKeywords;
		return this;
	}

	public String getmPublish() {
		return mPublish;
	}

	public Article setmPublish(String mPublish) {
		this.mPublish = mPublish;
		return this;
	}

	public Date getmDate() {
		return mDate;
	}

	public Article setmDate(Date mDate) {
		this.mDate = mDate;
		return this;
	}
	
	

}
