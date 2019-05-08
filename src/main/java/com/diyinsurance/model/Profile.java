package com.diyinsurance.model;

public class Profile {
int mProfileID;
String mAuthor,mDesc,mTitle,mEmail,mPassword,mUsername,role,enabled;

public String getRole() {
	return role;
}
public Profile setRole(String role) {
	this.role = role;
	
	return this;
}
public String getEnabled() {
	return enabled;
}
public Profile setEnabled(String enabled) {
	this.enabled = enabled;
	
	return this;
}
public int getmProfileID() {
	return mProfileID;
}
public Profile setmProfileID(int mProfileID) {
	this.mProfileID = mProfileID;
	
	return this;
}
public String getmAuthor() {
	return mAuthor;
}
public Profile setmAuthor(String mAuthor) {
	this.mAuthor = mAuthor;
	
	return this;
}
public String getmDesc() {
	return mDesc;
}
public Profile setmDesc(String mDesc) {
	this.mDesc = mDesc;
	
	return this;
}
public String getmTitle() {
	return mTitle;
}
public Profile setmTitle(String mTitle) {
	this.mTitle = mTitle;
	
	return this;
}
public String getmEmail() {
	return mEmail;
}
public Profile setmEmail(String mEmail) {
	this.mEmail = mEmail;
	
	return this;
}
public String getmPassword() {
	return mPassword;
}
public Profile setmPassword(String mPassword) {
	this.mPassword = mPassword;
	
	return this;
}
public String getmUsername() {
	return mUsername;
}
public Profile setmUsername(String mUsername) {
	this.mUsername = mUsername;
	
	return this;
}
}
