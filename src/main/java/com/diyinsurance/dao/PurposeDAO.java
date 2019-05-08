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
import com.diyinsurance.model.Purpose;




@Repository
public class PurposeDAO extends dataSource {



	public List<Purpose> getAllPurpose() {

		
		return this.jbT.query("call getAllPurpose()", new PurposeMapper());	
	}

	// annoymous inner class
	public class PurposeMapper implements RowMapper<Purpose> {

		public Purpose mapRow(ResultSet rs, int rowNo) throws SQLException {

			Purpose mPurpose = new Purpose();
			mPurpose.setmPurposeID(rs.getInt("purpose_id")).setmPurposeName(rs.getString("purpose_name"));
			return mPurpose;

		}

	}

	
}
