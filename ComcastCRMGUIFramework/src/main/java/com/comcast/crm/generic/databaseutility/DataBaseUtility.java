package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {
	Connection conn;
	public void getDbConnection(String url,String username,String password) {
		try {
		//get the driver object from mysql
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		
	     conn=DriverManager.getConnection(url,username,password);
		}
		catch(Exception e) {
			
		}
	
	}
	
	public void getConnectionn()
	{
		try {
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/advsel","root","root");
		}
		catch(Exception e) {
			
		}
	}
	public void closeDbConnection() throws SQLException {
		try {
		conn.close();
		}
		catch(Exception e) {
			
		}
	}
	public ResultSet executeSelectQuery(String Query) throws SQLException
	{
		ResultSet result=null;
		try {
	        Statement stat=conn.createStatement();
	        result=stat.executeQuery(Query);
		}
		catch(Exception e)
		{
			
		}
		return result;
	 
	}
	
	public int executeNonselectQuery(String Query)
	
	{
		int result=0;
		try {
		Statement stat=conn.createStatement();
		stat.executeUpdate(Query);
		}
		catch(Exception e) {
			
		}
		return result;
		
		}
}


