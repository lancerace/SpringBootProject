package com.diyinsurance.controller;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
public class FileController {
	// read image in file input stream, convert to image and save to
	// /resources/static/image folder.
	// multipartfile support many types. eg( video,jpg,jpeg,bla bla bla )
	@Autowired
	private ServletContext servletContext;

//	private String imageFileName="default";

	// ResponseEntity contain all http request response related
	@RequestMapping(value = "/article/uploadThumbnail", method = RequestMethod.POST, consumes = "multipart/form-data")
	public ResponseEntity<?> uploadThumbnail(@RequestParam("uploadfile") MultipartFile uploadfile,
			@RequestParam("articleID") String articleID) {

		// Get the filename and build the local file path (be sure that the
		// application have write permissions on such directory)
		System.out.println("uploadthumbnail invoked");
		System.out.println("articleID is: " + articleID);
		String path = servletContext.getRealPath("/WEB-INF/articles/images/" + articleID);
		System.out.println("filename is:" + uploadfile.getOriginalFilename());

		/*
		 * 1. change filename to "thumbnail.[extension]" regardless of the name
		 * and save to destination folder based on fontend structure for
		 * synchronization purposes e.g
		 * http://www.diyinsurance.com.sg/portal/articles/images/20/thumbnail.
		 * jpg . 
		 * 2. split string by regex = '.' using pattern.quote as dot is
		 * consider a special regular expression
		 */
		String filename = uploadfile.getOriginalFilename();
		String[] tempFileName = filename.split(Pattern.quote("."));
		tempFileName[0] = "thumbnail";
		filename = tempFileName[0] + "." + tempFileName[1];

		// Save the file locally
		try {
			FileUtils.writeByteArrayToFile(new File(path, filename), uploadfile.getBytes());
			System.out.println("Byte is:" + uploadfile.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<>(HttpStatus.OK);
	} // method uploadFile

	@RequestMapping(value = "/article/saveImageName", method = RequestMethod.POST, produces = "application/json")
	public void saveImageName(@RequestParam(value = "fName", required = false) String fileName) {

		System.out.println("saveImageName mapping file name is " + fileName);
		// save imagename to override /articleimageUpload default imageName from
		// tinymce Plugin (e.g. bl0b1.jpg,bl0b2.jpg)
//		imageFileName = fileName;

	}

	@RequestMapping(value = "/article/imageUpload", method = RequestMethod.POST, produces = "application/json")
	public String getImageUpload(@RequestParam(value = "file", required = false) MultipartFile image,
			@RequestParam(value="filename",required = false)String imageFileName) {

		// craft path for image to store in desired folder
		String path = servletContext.getRealPath("/WEB-INF/classes/static/images");
		System.out.println("invoked imageupload");
		System.out.println(path);
		System.out.println(image.getContentType());

		// file name is blob.x ,blob.x. unable to retrieve original filename
		// upon uploading
		// File file = new File(path, image.getOriginalFilename());
		System.out.println("orginal image name is:" + image.getOriginalFilename());
		System.out.println("file Name is " + imageFileName);

		try {
			FileUtils.writeByteArrayToFile(new File(path, imageFileName), image.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonObject o = new JsonObject();
		// testing first.
		
		//replace value when project is on production.
		String diyinsuranceDomainURL = "http://localhost:8080";

		System.out.println("context Path is:" + servletContext.getContextPath());
		o.addProperty("location", diyinsuranceDomainURL + servletContext.getContextPath() + "/images/" + imageFileName);
		//for froala format
		
		
//		o.addProperty("link", diyinsuranceDomainURL + servletContext.getContextPath() + "/images/" + imageFileName);
		//return location of images stored in server 
		return new Gson().toJson(o);
	}

}
