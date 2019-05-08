package com.diyinsurance.DataSource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class dataSource {

	
	protected JdbcTemplate jbT;
		@Autowired
		public void setDataSource(DataSource dataSource) {
			this.jbT = new JdbcTemplate(dataSource);

		}
	
}
