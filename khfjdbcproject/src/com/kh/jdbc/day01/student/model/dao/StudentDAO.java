package com.kh.jdbc.day01.student.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDAO {
	
	public void selectAll() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";	//고정된 주소
		String user = "STUDENT";
		String password = "STUDENT";
		String sql = "SELECT * FROM STUDENT_TBL";
		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");	//이것도 고정된 값
			//2. DB 연결 생성
			Connection conn = DriverManager.getConnection(url, user, password);
			//3. 쿼리문 실행준비(Statement 객체 생성)
			Statement stmt = conn.createStatement();
			//4. 쿼리문 실행 및 결과 받기
			ResultSet rset = stmt.executeQuery(sql);
			//후처리(rset은 그냥 쓸 수 없기 때문에 후처리 해줘야해)
			while(rset.next()) {
				System.out.println("ID : " + rset.getNString("STUDENT_ID"));	//STUDENT_ID가 가진 필드값
			}
			//6. 자원해제
			rset.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
