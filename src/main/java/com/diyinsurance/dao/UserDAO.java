package com.diyinsurance.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.diyinsurance.DataSource.dataSource;
import com.diyinsurance.model.UserInfo;

//respository make this class a canditate for component scanning
@Repository
public class UserDAO extends dataSource{

	public UserInfo getUsername(String mUsername) {
		String SQL = "Call getUsername(?)";

		return jbT.queryForObject(SQL, new Object[] { mUsername }, new UserMapper());

	}
	
	
	
	// annoymous inner class
		public class UserMapper implements RowMapper<UserInfo> {
			public UserInfo mapRow(ResultSet rs, int rowNo) throws SQLException {
				UserInfo mUser = new UserInfo();
				return mUser.setmUsername(rs.getString("username")).setmPassword(rs.getString("password"))
						.setmRole(rs.getString("role")).setmEnabled(rs.getInt("enabled"));
			}
		}
	
}
		
		
