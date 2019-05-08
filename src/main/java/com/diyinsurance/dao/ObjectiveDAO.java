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
import com.diyinsurance.model.Objective;



@Repository
public class ObjectiveDAO extends dataSource {

	

	
	public List<Objective> getAllObjective(){
		
		
		return jbT.query("call getAllObjective()", new ObjectiveMapper());
		
	}
	// annoymous inner class
	public class ObjectiveMapper implements RowMapper<Objective> {

		public Objective mapRow(ResultSet rs, int rowNo) throws SQLException {
			// TODO Auto-generated method stub		
			Objective o = new Objective();
			o.setmObjectiveID(rs.getInt("objective_id"))
			 .setmObjectiveName(rs.getString("objective_name"));
			return o;
		}

	}
	
}
