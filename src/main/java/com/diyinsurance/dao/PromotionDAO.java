package com.diyinsurance.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.diyinsurance.DataSource.dataSource;
import com.diyinsurance.model.Promotion;



@Repository
public class PromotionDAO extends dataSource {

	
	

	
		public List<Promotion> getAllPromo() {
	 
			return this.jbT.query("call getAllPromotion()", new PromotionMapper());
		}

		
		// annoymous inner class
		public class PromotionMapper implements RowMapper<Promotion> {

			public Promotion mapRow(ResultSet rs, int rowNo) throws SQLException {
				// TODO Auto-generated method stub
				
				
				
				Promotion mPromo = new Promotion();
				mPromo.setmPromoID(rs.getInt("promo_id"))
				.setmInsurerID(rs.getString("insurer_id"))
				.setmThumbnail(rs.getString("thumbnail"))
				.setmDiscount(rs.getString("promo_discount"))
				.setmTitle(rs.getString("promo_title"))
				.setmDescription("desc")
				.setmLink(rs.getString("link"))
				.setmExpired(rs.getString("expired"));
				
				return mPromo;
			}

		}

		
}
