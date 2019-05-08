package com.diyinsurance.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.diyinsurance.dao.ArticleDAO;
import com.diyinsurance.dao.ProfileDAO;
//import com.diyinsurance.dao.InsurerDAO;
//import com.diyinsurance.dao.ObjectiveDAO;
//import com.diyinsurance.dao.PremiumDAO;
//import com.diyinsurance.dao.ProductDAO;
//import com.diyinsurance.dao.PromotionDAO;
//import com.diyinsurance.dao.PurposeDAO;
import com.diyinsurance.dao.TagDAO;
//import com.diyinsurance.dao.TypeDAO;
import com.diyinsurance.model.Article;
import com.diyinsurance.model.Tag;
//import com.diyinsurance.model.Insurer;
//import com.diyinsurance.model.Objective;
//import com.diyinsurance.model.Premium;
//import com.diyinsurance.model.Product;
//import com.diyinsurance.model.Promotion;
//import com.diyinsurance.model.Purpose;
//import com.diyinsurance.model.Tag;
//import com.diyinsurance.model.TypeObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

@RestController // (Annotation for @Controller+@ResponseBody)
// @EnableWebMvc // resolving error 406
public class SpringController {

	// Abstract all DAOs to sessionfactory next time *need to read on it*
	// @Autowired
	// private ProductDAO pDAO;
	// @Autowired
	// private InsurerDAO iDAO;
	// @Autowired
	// private PurposeDAO purposeDAO;
	// @Autowired
	// private ObjectiveDAO oDAO;
	// @Autowired
	// private TypeDAO tDAO;
	// @Autowired
	// private PromotionDAO promoDAO;
	// @Autowired
	// private PremiumDAO premDAO;
	@Autowired
	private ArticleDAO aDAO;
	@Autowired
	private TagDAO tagDAO;
	@Autowired
	private ProfileDAO profileDAO;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	AuthenticationManager authenticationManager;
	
	
	@Bean
	public SecurityContextRepository securityContextRepository() {
	    return new HttpSessionSecurityContextRepository();
	}
	@Autowired
	SecurityContextRepository repository;
	
	
//	@Autowired
//	RememberMeServices rememberMeServices;
	private Gson gson = new Gson();



	@RequestMapping(value = "/article/getAllArticle", method = RequestMethod.POST, produces = "application/json")
	public List<Article> getAllArticles() {

		return aDAO.getAllArticles();
	}

	@RequestMapping(value = "/article/getOneArticle", method = RequestMethod.POST, produces = "application/json")
	public Article getOneArticle(@RequestParam(value = "mArticleID", required = false) int mArticleID) {

		return aDAO.getOneArticle(mArticleID);
	}

	@RequestMapping(value = "/article/insertOneArticle", method = RequestMethod.POST, produces = "text/plain")
	public String insertOneArticle(@RequestParam(value = "mArticle", required = false) String mJsonData) {
		System.out.println(mJsonData);
		// convert jsonData from ajax.post to jsonobject for usage
		JsonObject jObj = gson.fromJson(mJsonData, JsonObject.class);

		// enable profile selection through ddl. will be hardcoded from now
		String mArticleID = aDAO.insertOneArticle(jObj.get("mTitle").getAsString(), 1,
				jObj.get("mSummary").getAsString(), jObj.get("mKeywords").getAsString(), jObj.get("mTagID").getAsInt(),
				jObj.get("mPublish").getAsString(), jObj.get("mHtmlMarkup").getAsString(),
				jObj.get("mThumbnail").getAsString());

		System.out.println("mArticleID is:" + mArticleID);
		/*
		 * Save htmlmarkup to file configure path here to save article
		 * htmlMarkup to jsp file file path.
		 * D:\WorkSpace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\
		 * wtpwebapps\diyinsurance\WEB-INF ,actual file path during app
		 * deployment.
		 */
		String path = servletContext.getRealPath("/WEB-INF/articles/");
		System.out.println("path is.." + path);

		File file = new File(path, mArticleID + ".jsp");
		try {
			if (!file.exists()) {
				System.out.println("file doesnt exist");
				file.createNewFile();

			} else
				System.out.println("existed..");
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(jObj.get("mHtmlMarkup").getAsString());

			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mArticleID;
	}

	@RequestMapping(value = "/article/updateOneArticle", method = RequestMethod.POST, produces = "text/plain")
	public String updateOneArticle(@RequestParam(value = "mArticle", required = false) String mArticleJSON) {
		System.out.println("updateOneArticle invoked");
		JsonObject jObj = gson.fromJson(mArticleJSON, JsonObject.class);
		System.out.println(jObj);
		// 2nd parameter =mProfileID
		return aDAO.updateOneArticle(jObj.get("mTitle").getAsString(), 1, jObj.get("mSummary").getAsString(),
				jObj.get("mKeywords").getAsString(), jObj.get("mTagID").getAsString(),
				jObj.get("mPublish").getAsString(), jObj.get("mHtmlMarkup").getAsString(),
				jObj.get("mThumbnail").getAsString(), jObj.get("mArticleID").getAsInt());
	}

	@RequestMapping(value = "/article/deleteOneArticle", method = RequestMethod.POST, produces = "application/json")
	public int deleteOneArticle(@RequestParam(value = "mID", required = false) int mArticleID) {
		return aDAO.deleteOneArticle(mArticleID);
	}

	@RequestMapping(value = "/auth/login", method = RequestMethod.POST, produces = "application/json")
	public String login(@RequestParam(value = "username", required = false) String mUsername,
			@RequestParam(value = "password", required = false) String mPassword,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("/auth/login invoked");
		
		System.out.println(mUsername);
		System.out.println(mPassword);
		
		Authentication authenticationToken = new UsernamePasswordAuthenticationToken(mUsername, mPassword);
		   try {
		       Authentication authentication = authenticationManager.authenticate(authenticationToken);
		        SecurityContextHolder.getContext().setAuthentication(authentication);
		        return "{\"status\": true}";
		           } catch (AuthenticationException ex) {

		        	   return "{\"status\": false, \"error\": \"Bad Credentials\"}";
		        	   
		   }
		
		   
		   
		   
		
//		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(mUsername, mPassword);
//		try {
//			Authentication auth = authenticationManager.authenticate(token);
//			SecurityContextHolder.getContext().setAuthentication(auth);
//			
//			repository.saveContext(SecurityContextHolder.getContext(), request, response);
//			rememberMeServices.loginSuccess(request, response, auth);
//			return "{\"status\": true}";
//		} catch (BadCredentialsException ex) {
//			return "{\"status\": false, \"error\": \"Bad Credentials\"}";
//		}
	}

	// //Spring Security see this :
	// @RequestMapping(value = "/auth/login", method = RequestMethod.GET)
	// public ModelAndView login(
	// @RequestParam(value = "error", required = false) String error,
	// @RequestParam(value = "logout", required = false) String logout) {
	//
	// ModelAndView model = new ModelAndView();
	// if (error != null) {
	// model.addObject("error", "Invalid username and password!");
	// }
	//
	// if (logout != null) {
	// model.addObject("msg", "You've been logged out successfully.");
	// }
	// model.setViewName("login");
	//
	// return model;
	//
	// }
	//

	// @RequestMapping(value ="/register", method = RequestMethod.POST, produces
	// =
	// "application/json")
	// public String login(@RequestParam(value = "mProfile", required = false)
	// String mProfileJSON) {
	// JsonObject jObj = gson.fromJson(mProfileJSON, JsonObject.class);
	//
	// return profileDAO.registerOneMember(jObj.get("mUsername").getAsString(),
	// jObj.get("mPassword").getAsString(),
	// jObj.get("mFirstName").getAsString(),
	// jObj.get("mLastName").getAsString(),
	// jObj.get("mDesc").getAsString(), jObj.get("mTitle").getAsString(),
	// jObj.get("mEmail").getAsString());
	// }

	@RequestMapping(value = "/recaptchaValidation", method = RequestMethod.POST, produces = "application/json")
	public String recaptchaValidation(@RequestParam(value = "data", required = false) String JSONData) {

		JsonObject jObj, jObj2 = null;
		String captchaResponse, secret, url;
		URL obj;
		jObj = gson.fromJson(JSONData, JsonObject.class);
		captchaResponse = jObj.get("response").getAsString();
		secret = jObj.get("secret").getAsString();
		url = "https://www.google.com/recaptcha/api/siteverify";
		System.out.println("invoke repcatchavalidation");
		try {
			obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

			// add request header
			con.setRequestMethod("POST");
			// con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			String postParams = "secret=" + secret + "&response=" + captchaResponse;

			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + postParams);
			System.out.println("Response Code : " + responseCode);

			// read HttpURLConnection response content from post request via
			// (setDoOutput())
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			// append each new line as a single line
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			/*
			 * print result expected response JSON result from google/api {
			 * "success": true|false, "challenge_ts": timestamp, // timestamp of
			 * the challenge load (ISO format yyyy-MM-dd'T'HH:mm:ssZZ)
			 * "hostname": string, // the hostname of the site where the
			 * reCAPTCHA was solved "error-codes": [...] // optional }
			 */
			System.out.println(response.toString());
			jObj2 = gson.fromJson(response.toString(), JsonObject.class);
			System.out.println("recaptchaValidation: " + jObj2.get("success").getAsString());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jObj2.get("success").getAsString();

	}

	@RequestMapping(value = "/article/getAllTags", method = RequestMethod.POST, produces = "application/json")
	public List<Tag> getAllTags() {
		return tagDAO.getAllTags();
	}
}
