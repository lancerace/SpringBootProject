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
import com.diyinsurance.model.Insurer;



@Repository
public class InsurerDAO extends dataSource {

	

	public List<Insurer> getAllInsurer() {
 
		return jbT.query("call getAllInsurer()", new InsurerMapper());
	
	}
	
	// annoymous inner class 
	public class InsurerMapper implements RowMapper<Insurer> {

		public Insurer mapRow(ResultSet rs, int rowNo) throws SQLException {
			// TODO Auto-generated method stub

			Insurer mInsurer = new Insurer();
			mInsurer.setmInsurerID(rs.getString("insurer_id")).setmInsurerName(rs.getString("insurer_name"))
					.setmLogo(rs.getString("logo")).setmURL(rs.getString("url"));

			// mInsurer.setmLastUpdated(rs.getString());

			return mInsurer;

		}

	}

}
