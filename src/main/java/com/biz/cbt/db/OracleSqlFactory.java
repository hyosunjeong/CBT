package com.biz.cbt.db;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.biz.cbt.dao.CbtDao;

public class OracleSqlFactory {

	SqlSessionFactory seesionFactory;
	
	public SqlSessionFactory getSessionFactory() {

		return this.seesionFactory;
		
	}
	public OracleSqlFactory() {
		Properties props = new Properties();
		
		props.put("DRIVER", DBContract.Drvier);
		props.put("URL", DBContract.url);
		props.put("USER", DBContract.user);
		props.put("PASSWORD", DBContract.password);
		
		
		DataSourceFactory dataSourceFactory = new CbtDataSourceFactory();
		dataSourceFactory.setProperties(props);
		
		DataSource dataSource = dataSourceFactory.getDataSource();
		
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		
		Environment environment = new Environment("GradeENV",transactionFactory,dataSource);
		
		Configuration config = new Configuration(environment);
		config.addMapper(CbtDao.class);
		
		this.seesionFactory = new SqlSessionFactoryBuilder().build(config);
		
		
		
	}
	
}
