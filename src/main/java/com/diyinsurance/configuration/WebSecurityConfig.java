package com.diyinsurance.configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.diyinsurance.dao.ProfileDAO;
import com.diyinsurance.handler.CustomAuthenticationSuccessHandler;
import com.diyinsurance.model.Profile;
import com.diyinsurance.Service.userDetailsService;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

@Configuration
// @ComponentScan("com.diyinsurance")
@EnableWebSecurity
/*
 * Spring security via WebSecurityConfig class provided a "framework" to handle
 * login, logout authentication rather than using HTTP-Response.redirect from
 * normal servlet w/o spring framework and coding authentication from scratch.
 * 
 * 
 * Once spring security is added,all sensitive endpoints exposed over HTTP will
 * be protected. In other words,all URL mapping are protected by CSRF support
 * which is enabled by default which establish an extra layering of security.
 * This is to make sure the form data is coming from your app and not from
 * somewhere else. It is a feature of Spring Security, and is turned on by
 * default by Spring Boot. DOCUMENTATION:
 * http://docs.spring.io/spring-security/site/docs/current/reference/html/csrf.
 * html In other words, it require HTTP parameter + cookie authentication
 */

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	/*
	 * Documentation: https://spring.io/guides/gs/securing-web/ as of 8/7/2016
	 */

	@Autowired
	DataSource dataSource;

	@Autowired
	userDetailsService mUserDetailService;
	@Autowired
	ServletContext mServletContext;

	@Autowired
	CustomAuthenticationSuccessHandler mCustomAuthenticationSuccessHandler;

	private RedirectStrategy rs = new DefaultRedirectStrategy();

	// @Override
	// public void configure(WebSecurity webSecurity) throws Exception
	// {
	// webSecurity
	// .ignoring()
	// // ... whatever is here is ignored by All of Spring Security
	// }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * Documentation references :
		 * http://docs.spring.io/spring-security/site/docs/3.2.x/guides/form.
		 * html
		 * =====================================================================
		 * Specify which which URL paths should be secured or not in this
		 * function Any other URL path must be authenticated. The login form is
		 * under /login, permitted for all to access. On login failure,redirect
		 * to /login?error. Permitted for all to access. On logout, redirect to
		 * /logout + delete cookies. as for successful attempt --> "/" URL.
		 * Permitted for all to access. ===================== Default
		 * configuration ===================== http .authorizeRequests()
		 * .anyRequest().authenticated() .and() .formLogin() .and()
		 * .httpBasic();
		 * 
		 * In spring security configuration, ordering matters. Permitting
		 * specific action need to be declared first, anything that is less
		 * specific should be put after it.Any code after
		 * anyRequest().authenticated() need to be authenticated.
		 *
		 *
		 */

		http.authorizeRequests()

				/*
				 * permit static resources from spring security,by default all
				 * are restricted to access
				 * 
				 * This allows anyone to access a URL that begins with
				 * resources/. Since this is where our css, javascript, and
				 * images are stored all our static resources are viewable by
				 * anyone.
				 * 
				 * Set login page to only allow anonymous access,else invoke
				 * deny access URL specified in deniedAccessHandler()
				 */

				.antMatchers(HttpMethod.POST, "/recaptchaValidation").permitAll()
				.antMatchers("/css/**", "/js/**", "/images/**").permitAll()

				/*
				 * by default spring security does not allow server to REST API
				 * method.Specify specific post url and permit it.
				 */

				// .anoynmous() Allows anonymous configuring how an anonymous
				// user is represented, which user by default is given
				// ROLE_ANONYMOUS
				//.antMatchers("/login").anonymous().antMatchers("/login").access("hasRole('ROLE_ANONYMOUS')")
				.antMatchers("/login").anonymous().antMatchers("/login").permitAll()
				//.antMatchers("/**").access("hasRole('ROLE_USER')").anyRequest().authenticated().and()
				.antMatchers("/**").permitAll()
				/*
				 * login configuration
				 * =============================================================
				 * Authentication is done via spring security framework login
				 * method is HTTP Post by default.make sure frontend for client
				 * browser is requesting via HTTP POST. The username and
				 * password should be present on the HTTP parameter
				 */

				//.formLogin().loginPage("/login").loginProcessingUrl("/auth/login").usernameParameter("username")
				//.passwordParameter("password")

				/*
				 * 
				 * Custom authenticationEntryPoint here ,by default,spring
				 * handle the configuration such as redirecting user to login
				 * page again if attempted to access " protected resources "
				 * without authentication
				 * 
				 * 
				 */
				// .and()
				// .exceptionHandling()
				// .authenticationEntryPoint(new AuthenticationEntryPoint() {
				//
				// @Override
				// public void commence(HttpServletRequest request,
				// HttpServletResponse response,
				// AuthenticationException arg2) throws IOException,
				// ServletException {
				// response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				// }
				// })

				.and().formLogin().successHandler(new SimpleUrlAuthenticationSuccessHandler() {

					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) throws IOException, ServletException {
						/*
						 * Purpose of configuration on successHandler for custom
						 * behaviour was:
						 * 
						 * For normal form submission via <Form action="POST">
						 * w/o event.preventDefault(). HTTP response is able to
						 * redirect user to desired webpage with HTTP status
						 * code 302(Redirection). However,in term of form
						 * submission via Ajax, browser take care of HTTP 302
						 * automatically and hence ajax response will return
						 * HTTP 200,containing the result of response indicated
						 * from the server.
						 * 
						 * 
						 * Conclusion 1.In short,browser does not allow handling
						 * of HTTP 302 in ajax which is the nature of redirect
						 * /according to the spec by W3.org standard.
						 * 
						 * 2.When your XHR request returns a Redirect response
						 * (HTTP Status 301, 302, 303, 307), the XMLHttpRequest
						 * automatically follows the redirected URL and returns
						 * the status code of that URL.
						 * 
						 * Documentation:
						 * http://stackoverflow.com/questions/5344145/how-to-get
						 * -response-status-code-from-jquery-ajax Documentation:
						 * https://www.w3.org/TR/XMLHttpRequest/#infrastructure-
						 * for-the-send()-method
						 */

						// clear response 302/extra attribute since ajax does
						// not redirect for
						// response
						clearAuthenticationAttributes(request);
						// Collection<? extends GrantedAuthority> roleList =
						// SecurityContextHolder.getContext()
						// .getAuthentication().getAuthorities();
						// System.out.println("check user existed by role" +
						// roleList);
						// if (roleList.contains(new
						// SimpleGrantedAuthority("ROLE_USER"))) {
						// response.sendRedirect("/article");

					}
					//

				}

				// redirect to url upon login failure
				).failureUrl("/failLogin")

				.and().exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
					// override accessdeniedlogic from spring security
					@Override
					public void handle(HttpServletRequest request, HttpServletResponse response,
							AccessDeniedException e) throws IOException, ServletException {
						// TODO Auto-generated method stub
						System.out.println("denied access invoked");

						// if user logged in,redirect to home page
						Collection<? extends GrantedAuthority> roleList = SecurityContextHolder.getContext()
								.getAuthentication().getAuthorities();
						System.out.println("check user existed by role" + roleList);

						response.sendRedirect(mServletContext.getContextPath() + "/article");
					}
				})

				.and().csrf().disable()
				/*
				 * remember me configuration. In this case, configuration is
				 * based on persistent token approach rather than simple hash
				 * token approach tokenRepository: It specifies
				 * PersistentTokenRepository which is used to query
				 * persistent_logins table.
				 * 
				 * The mechanism will be able to identify the user across
				 * multiple sessions – so the first thing to understand is that
				 * Remember Me only kicks in after the session times out.
				 */

				.rememberMe().tokenRepository(persistentTokenRepository())
				// token expiration in seconds,set to 2hrs currently
				.tokenValiditySeconds(7200).rememberMeParameter("remember-me")

				// logout configuration
				.and().logout().logoutUrl("/logout").invalidateHttpSession(true)
				// delete cookie upon logout, as for JSESSIONID, it will
				// invalidate automatically once end of session
				.deleteCookies("remember-me").logoutSuccessUrl("/login");
	}

	// @Override
	// public void configure(final WebSecurity web) throws Exception {
	// web.ignoring().antMatchers("/resources/**");
	// }

	// persistent token configuration
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		/*
		 * JdbcTokenREpositoryImp stores tokens to a database. used token will
		 * be removed and a new token will be auto generated to the DB for usage
		 */
		JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		tokenRepositoryImpl.setDataSource(dataSource);
		return tokenRepositoryImpl;
	}

	// @Autowired
	// public void configureGlobal(AuthenticationManagerBuilder auth) throws
	// Exception {

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		System.out.println("configureGlobal invoked");
		// auth.jdbcAuthentication().dataSource(dataSource)
		// .usersByUsernameQuery("select username,password, enabled from profile
		// where username=?")
		// .authoritiesByUsernameQuery("select username, role from profile where
		// username=?");

		auth.userDetailsService(mUserDetailService);

		// auth.inMemoryAuthentication()
		// .passwordEncoder(new BCryptPasswordEncoder())
		// .withUser("okookko").password("1234")
		// .roles("USER");

	}

	/*
	 * This function is used to sets up an in-memory user store with a single
	 * user. That user is given a username of "user", a password of
	 * "password",encrypted with bCryptPasswordEncoder and a role of "USER".
	 */
	// auth.inMemoryAuthentication()
	//// .passwordEncoder(new BCryptPasswordEncoder())
	// .withUser("user").password("password")
	// .roles("USER");

	// @Component
	// public class CustomAuthenticationProvider implements
	// AuthenticationProvider {
	//
	// @Override
	// public Authentication authenticate(Authentication authentication)
	// throws AuthenticationException {
	// String name = authentication.getName();
	// String password = authentication.getCredentials().toString();
	//
	// if (shouldAuthenticateAgainstThirdPartySystem()) {
	// // use the credentials and authenticate against the third-party system
	// return new UsernamePasswordAuthenticationToken(name, password, new
	// ArrayList<>());
	// } else {
	// return null;
	// }
	// }
	//
	// @Override
	// public boolean supports(Class<?> authentication) {
	// return authentication.equals(UsernamePasswordAuthenticationToken.class);
	// }
	// }
	//

}

