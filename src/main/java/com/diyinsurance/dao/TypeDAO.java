package com.diyinsurance.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.diyinsurance.DataSource.dataSource;
import com.diyinsurance.model.TypeObject;

@Repository
public class TypeDAO extends dataSource{

		// List = javax.awt
		public List<TypeObject> getAllType() {
			return this.jbT.query("call getAllType()", new TypeMapper());
		}
		
		// annoymous inner class
		public class TypeMapper implements RowMapper<TypeObject> {
			public TypeObject mapRow(ResultSet rs, int rowNo) throws SQLException {
				// TODO Auto-generated method stub

				TypeObject mType = new TypeObject();
				
				mType.setmTypeID(rs.getInt("type_id"))
				     .setmTypeName(rs.getString("type_name"));

				// mInsurer.setmLastUpdated(rs.getString());

				return mType;
			}
			
			
		}

	
}
