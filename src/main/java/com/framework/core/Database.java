package com.framework.core;

import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	static Database database=null;
	PropertiesReader properties= PropertiesReader.getInstance();

	static String JDBC_DRIVER = null;  
	static String DB_URL = null;

	static String userName = null;
	static String password = null;

	static Connection connection = null;
	static Statement statement = null;
	static ResultSet resultSet=null;

	private Database() throws Exception
	{
		JDBC_DRIVER=properties.getValue("JDBCDriver");
		DB_URL=properties.getValue("DatabaseURL");
		userName=properties.getValue("DBUserName");
		password=properties.getValue("DBPassword");
		
		
		Class.forName(JDBC_DRIVER);
		connection = DriverManager.getConnection(DB_URL,userName,password);
		statement = connection.createStatement();
	}


	public static Database getDBInstance()
	{
		if(database==null)
		{
			try {
				database= new Database();
			} catch (Exception e) {
				e.printStackTrace();
				Log.getLogInstance().info(e.getMessage());
			}
		}

		return database;
	}

	public static ResultSet executeDBquery(String query)
	{
		try {
			resultSet=statement.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
			Log.getLogInstance().info(e.getMessage());	
		}

		return resultSet;
	}
	
	public void closeDatabaseConnection(Annotation annotation) throws Exception
	{
		if(annotation.toString().contains("@io.cucumber.java.After("))
		{
			try {
				if(statement!=null){statement.close();}
				if(connection!=null){connection.close();}
			} catch (Exception e) {
				e.printStackTrace();
				Log.getLogInstance().info(e.getMessage());	
			}
			
		}
	}






}
