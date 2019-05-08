package com.diyinsurance.handler;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private ServletContext mServletContext;
	private RedirectStrategy rs = new DefaultRedirectStrategy();

	// override default spring security onsuccessURL configuration in this
	// class method via(AuthenticationSuccessHandler)
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException, ServletException {

		// do some logic here if you want something to be done whenever
		// the user successfully logs in.
		System.out.println("invoked onauthenticationSuccess");
		System.out.println(mServletContext.getContextPath() + "/article");
		// set our response to OK status

		if (httpServletResponse.isCommitted())
			System.out.println("response has alr been committed,unable to redirect");

		httpServletResponse.setStatus(HttpServletResponse.SC_OK);

		// since we have created our custom success handler, its up to us to
		// where
		// we will redirect the user after successfully login
		// httpServletResponse.sendRedirect("/article");
		rs.sendRedirect(httpServletRequest, httpServletResponse, "/article");
	}
	//
	// protected void handle(HttpServletRequest httpServletRequest,
	// HttpServletResponse httpServletResponse, Authentication authentication)
	// throws IOException, ServletException ) throw IOException{
	// String targetUrl = determineTargetUrl(authentication);
	//
	// if(httpServletResponse.isCommitted()){
	//
	//
	// }
	//
	// }

}
