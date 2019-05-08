package com.diyinsurance.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.diyinsurance.DataSource.dataSource;
import com.diyinsurance.dao.ArticleDAO.ArticleMapper;
import com.diyinsurance.model.Article;
import com.diyinsurance.model.Profile;

//respository make this class a canditate for component scanning
@Repository
public class ProfileDAO extends dataSource
// implements UserDetailsService
{

	public Profile Login(String mUsername, String mPassword) {
		String SQL = "Call login(?,?)";

		Profile mProfile = jbT.queryForObject(SQL, new Object[] { mUsername, mPassword }, new ProfileMapper());

		return mProfile;
	}

	public String registerOneMember(String mUsername, String mPassword, String mFirstName, String mLastName,
			String mDesc, String mTitle, String mEmail) {

		String SQL = "call RegisterOneMember(?,?,?,?,?,?,?,?)";

		String mProfileID = jbT.queryForObject(SQL,
				new Object[] { mUsername, mPassword, mFirstName, mLastName, mDesc, mTitle, mEmail }, String.class);

		return mProfileID;
	}

	public Profile getOneProfile(String mUsername, String mPassword) {

		String SQL = "Call getOneProfile(?,?)";

		return jbT.queryForObject(SQL, new Object[] { mUsername, mPassword }, new ProfileMapper());
	}

	public Profile getProfileInfo(String mUsername) {
		String SQL = "Call getProfileInfo(?)";

		return jbT.queryForObject(SQL, new Object[] { mUsername }, new ProfileMapper());

	}

	// annoymous inner class
	public class ProfileMapper implements RowMapper<Profile> {
		public Profile mapRow(ResultSet rs, int rowNo) throws SQLException {

			Profile mProfile = new Profile();

			return mProfile.setmProfileID(rs.getInt("profile_id")).setmAuthor(rs.getString("author"))
					.setmDesc(rs.getString("desc")).setmTitle(rs.getString("title")).setmEmail(rs.getString("email"))
					.setmPassword(rs.getString("password")).setmUsername(rs.getString("username"))
					.setRole(rs.getString("role")).setEnabled(rs.getString("enabled"));

		}
	}

}
