package com.diyinsurance.model;

/*User class declared as UserInfo for naming because Spring security has already taken 'User' naming convention for
UserDetailsService
*/
public class UserInfo {
	String mUsername, mPassword, mRole;
	int mEnabled;

	public String getmUsername() {
		return mUsername;
	}

	public UserInfo setmUsername(String mUsername) {
		this.mUsername = mUsername;
		return this;
	}

	public String getmPassword() {
		return mPassword;
	}

	public UserInfo setmPassword(String mPassword) {
		this.mPassword = mPassword;
		return this;
	}

	public String getmRole() {
		return mRole;
	}

	public UserInfo setmRole(String mRole) {
		this.mRole = mRole;
		return this;
	}

	public int getmEnabled() {
		return mEnabled;
	}

	public UserInfo setmEnabled(int mEnabled) {
		this.mEnabled = mEnabled;
		return this;
	}

}
