package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

	public class DB {

		
		public static Connection conn = null;
		
		
		
		public static Connection getConnection() {
		try {	
			if(conn == null) {
				Properties props = LoadProperties();
				String url = props.getProperty("dburl");
				
				conn = DriverManager.getConnection(url, props);
			}
			
			return conn;
		 }catch(SQLException e)	{
			 throw new DbException(e.getMessage());
		 }
		
		}
		
		public static void closseConnection(Connection conn) {
			if(conn!= null) {
				
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		public static void closseStatement(Statement st) {
			if(st != null) {
				
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		public static void closseResultSet(ResultSet rs) {
			if(rs != null) {
				
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		public static void clossePreparedStatement(PreparedStatement st) {
			if(st!= null) {
				
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		public static Properties LoadProperties() {
			
			try(FileInputStream fs = new FileInputStream("db.properties")) {
			   
				Properties props = new Properties();
				props.load(fs);
				
				return props;
				
			}catch(IOException e) {
				throw new DbException(e.getMessage());
			}
			
		}
	}


