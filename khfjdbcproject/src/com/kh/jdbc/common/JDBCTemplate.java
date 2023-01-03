package com.kh.jdbc.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// DB접속 담당
// 싱글톤 패턴기법
import java.util.Properties;
public class JDBCTemplate {
	
	private static Properties prop;
//	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
//	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
//	private static final String USER = "STUDENT";
//	private static final String PASSWORD = "STUDENT";
//	
//	private static JDBCTemplate instance;
	private static Connection conn;
	
//	public JDBCTemplate() {
//		try {
//		} 
//	}
	
//	public static JDBCTemplate getDriverLoad() {
//		if(instance == null) {
//			instance = new JDBCTemplate();
//		}
//		return instance;
//	}
	//static메소드 안에 있는 변수는 static변수여야해 그래서 URL, USER, PASSWORD도 static으로 변경해줌.
	//수정을 하려할 때 아이디를 입력하면 자꾸 연결종료가 되는 문제를 해결하기 위해 conn.isClosed()를 추가함.
	public static Connection getConnection() {
			try {
				prop = new Properties();
				FileReader reader = new FileReader("resources/dev.properties");
				prop.load(reader);
				String url = prop.getProperty("url");
				String user = prop.getProperty("user");
				String password = prop.getProperty("password");
				if(conn == null || conn.isClosed()) {
					Class.forName(prop.getProperty("driver"));
				conn = DriverManager.getConnection(url, user, password);		
				conn.setAutoCommit(false);	//오토커밋해제!!!
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return conn;
	}
	//static으로 만들어줌으로써 객체생성없이 사용할 수 있게 됨.
	//연결해제
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//커밋
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
				conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//롤백
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
