package com.aoest.Test.util;

import java.sql.*;

public class DBUtil {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public Connection getConnection() {
		
		String driver ="com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://loaclhost:3306/test?useUnicod=true&characterEncoding=utf-8";
		String user = "root";
		String pwd = "123456";
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url,user,pwd);
		}catch(Exception e){
			return null;
		}
		return conn;
	}
	
	public void closeAll() {
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(pstmt != null) {
			try {
				pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(conn != null) {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public ResultSet executeQuery(String preparedSql, Object[] param) {
		try {
			pstmt = conn.prepareStatement(preparedSql);
			
			if(param != null) {
				for(int i = 0;i < param.length;i++) {
					pstmt.setObject(i +1, param[i]);
				}
			}
			
			rs = pstmt.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 执行SQL语句
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException 
	 */
    
	public int executeUpdate(String preparedSql,Object[] param) {
		int num = 0;
		
		try {
			this.getConnection();
			
			pstmt = conn.prepareStatement(preparedSql);
			
			if(param != null) {
				for(int i =0;i < param.length;i++) {
					pstmt.setObject(i +1,param[i]);
				}
			}
			
			num = pstmt.executeUpdate();
		}catch (SQLException  e) {
			e.printStackTrace();
		}finally {
			this.closeAll();
		}
		return num;
	}
	
	public static void main(String[] args) {
		DBUtil dbUtil = new DBUtil();
		System.out.println(dbUtil.getConnection());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
