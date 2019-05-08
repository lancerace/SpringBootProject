package com.diyinsurance.Service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.diyinsurance.dao.UserDAO;
import com.diyinsurance.model.UserInfo;

@Service
public class userDetailsService implements UserDetailsService {

	@Autowired
	UserDAO mUserDAO;

	/*
	 * just supply with username and role from db table and abstraacted method from spring security will do all
	 * the authentication based on username and password + role based
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername(String mUsername) throws UsernameNotFoundException {

		System.out.println("check username:" + mUsername);
		UserInfo mUserInfo = mUserDAO.getUsername(mUsername);

		GrantedAuthority authority = new SimpleGrantedAuthority(mUserInfo.getmRole());
		// Spring security User class,not self-defined Model user class
		UserDetails userDetails = new User(mUserInfo.getmUsername(), mUserInfo.getmPassword(), Arrays.asList(authority));
		return userDetails;
	}

}
