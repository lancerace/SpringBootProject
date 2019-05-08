package com.diyinsurance.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.diyinsurance.DataSource.dataSource;
import com.diyinsurance.model.Tag;

@Repository
public class TagDAO extends dataSource{	
	
	public List<Tag> getAllTags(){	
		return this.jbT.query("call getAllTags()", new tagMapper());
	}
	
	
	
	public class tagMapper implements RowMapper<Tag>{

		@Override
		public Tag mapRow(ResultSet rs, int rowNo) throws SQLException {
			
			return new Tag().setmTagID(rs.getInt("tag_id"))
			    .setmTagName(rs.getString("tag_name"));	
		
		}

	}
	
	
}
