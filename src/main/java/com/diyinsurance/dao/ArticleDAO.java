package com.diyinsurance.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.apache.commons.io.IOUtils;
import com.diyinsurance.DataSource.dataSource;
import com.diyinsurance.model.Article;

@Repository
public class ArticleDAO extends dataSource {

	public List<Article> getAllArticles() {

		return jbT.query("call getAllArticles()", new ArticleMapper());

	}

	public Article getOneArticle(int mArticleID) {
		String SQL = "Call getOneArticle(?)";

		Article mArticle = jbT.queryForObject(SQL, new Object[] { mArticleID }, new ArticleMapper());

		return mArticle;
	}

	public String insertOneArticle(String mTitle, int mProfileID, String mSummary, String mKeyWords, int mTagID,
			String mPublish, String mHtmlMarkup,String mThumbnail) {

		String SQL = " call insertOneArticle(?,?,?,?,?,?,?,?) ";

		/*
		 * return 1 if successful insert to db Server response produce type
		 * ="text/plain", hence return type has to be in string format which was
		 * concatenated with + ""
		 */
		String mArticleID = jbT.queryForObject(SQL,
				new Object[] { mTitle, mProfileID, mSummary, mKeyWords, mTagID, mPublish, mHtmlMarkup,mThumbnail}, String.class);
		// jbT.update(SQL,
		// mTitle,mProfileID,mSummary,mKeyWords,mTagID,mPublish,mHtmlMarkup);
		return mArticleID;
	}
	
	public String updateOneArticle(String mTitle, int mProfileID, String mSummary, String mKeyWords, String mTagID,
			String mPublish, String mHtmlMarkup,String mThumbnail,int mArticleID){
		
		String SQL = "call updateOneArticle(?,?,?,?,?,?,?,?,?)";

		return jbT.update(SQL, mTitle,mProfileID,mSummary,mKeyWords,mTagID,mPublish,mHtmlMarkup,mThumbnail,mArticleID) +"";

	}

	public int deleteOneArticle(int mArticleID) {
		String SQL = " call deleteOneArticle(?) ";

		return jbT.update(SQL, mArticleID);
	}
	
	// annoymous inner class
	public class ArticleMapper implements RowMapper<Article> {

		public Article mapRow(ResultSet rs, int rowNo) throws SQLException {
			// TODO Auto-generated method stub

			String htmlMarkup = "";
			InputStream a;
		
			try {
				a = rs.getBlob("htmlMarkup").getBinaryStream();
				htmlMarkup = IOUtils.toString(a, "UTF-8");
			
				a.close();
			} catch (NullPointerException e) {
				/* previous article data for htmlMarkup does not have tinymce 'markup'(aka server side to save article content
				 * using HTMLEditor),hence there is nullpointer.
				 *  ignore the error as nth is wrong in this
				 */
				
                //e.printStackTrace();
				htmlMarkup = "";
			} catch (IOException e) {
				 e.printStackTrace();
			} finally {			
			}
			return new Article(rs.getInt("art_id"), rs.getInt("profile_id"), rs.getInt("tag_id"), rs.getString("title"),
					rs.getString("summary"), rs.getString("keywords"), rs.getString("publish"),
					rs.getString("tag_name"),htmlMarkup, 
					(rs.getString("thumbnail") == null) ? "No file selected" : rs.getString("thumbnail"),
					rs.getDate("date"));
		}

	}
}
