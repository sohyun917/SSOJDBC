package com.kh.jdbc.day01.student.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day01.student.model.vo.Student;

public class StudentDAO {
	
	public List<Student> selectAll() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";	//고정된 주소
		String user = "STUDENT";
		String password = "STUDENT";
		String sql = "SELECT * FROM STUDENT_TBL";
		Student student = null;
		List<Student> sList = null;
		try {
			//1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");	//이것도 고정된 값
			//2. DB 연결 생성
			Connection conn = DriverManager.getConnection(url, user, password);	//CONNECT는 인터페이스라 객체생성이 안됨. 그래서 DRIVERMANAGER사용한것
			//3. 쿼리문 실행준비(Statement 객체 생성)
			Statement stmt = conn.createStatement();
			//4. 쿼리문 실행 및 결과 받기
			ResultSet rset = stmt.executeQuery(sql);
			sList = new ArrayList<Student>();	//배열의 할당
			//후처리(rset은 그냥 쓸 수 없기 때문에 후처리 해줘야해)
			while(rset.next()) {
				student = new Student();
				student.setStudentId(rset.getNString("STUDENT_ID"));
				student.setStudentPwd(rset.getString("STUDENT_PWD"));
				student.setStudentName(rset.getString("STUDENT_NAME"));
				student.setGender(rset.getString("GENDER"));
				student.setAge(rset.getInt("AGE"));	//age는 데이터타입이 int이기 때문에 getInt써줘야해
				student.setEmail(rset.getString("EMAIL"));
				student.setPhone(rset.getString("PHONE"));
				student.setAddress(rset.getString("ADDRESS"));
				student.setHobby(rset.getString("HOBBY"));
				student.setEnrollDate(rset.getDate("ENROLL_DATE"));	//enroll_date는 데이터타입이 date니까 getDate로 써줘야해
				sList.add(student);	//위의 값들을 list에 넣어서 cotroller로 보내줄 준비
				//System.out.println("ID : " 			 + rset.getNString("STUDENT_ID"));	//STUDENT_ID가 가진 필드값
//				System.out.println("STUDENT_PWD : "	 + rset.getNString("STUDENT_PWD"));	//필드값
//				System.out.println("STUDENT_NAME : " + rset.getNString("STUDENT_NAME"));	//필드값
//				System.out.println("GENDER : " 		 + rset.getNString("GENDER"));	//필드값
//				System.out.println("AGE : " 		 + rset.getNString("AGE"));	//필드값
//				System.out.println("EMAIL : " 		 + rset.getNString("EMAIL"));	//필드값
//				System.out.println("PHONE : " 		 + rset.getNString("PHONE"));	//필드값
//				System.out.println("ADDRESS : " 	 + rset.getNString("ADDRESS"));	//필드값
//				System.out.println("HOBBY : "  		 + rset.getNString("HOBBY"));	//필드값
//				System.out.println("ENROLLDATE : "   + rset.getNString("ENROLL_DATE"));	//필드값
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
		//return을 여러번 하기 어려우니까 이걸 vo에 담아서 controller로 보내줄꺼야.
		return sList;
	}
	
	public int insertMember(Student student) {

		
		//1. 드라이버 등록
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "STUDENT";
		String password = "STUDENT";
		
		String sql = "INSERT INTO STUDENT_TBL VALUES("
				+ "'"+student.getStudentId()+"',"
					+ "'"+student.getStudentPwd()+"',"
						+ "'"+student.getStudentName()+"',"
							+ "'"+student.getGender()+"',"
								+student.getAge()+","
									+ "'"+student.getEmail()+"',"
										+ "'"+student.getPhone()+"',"
											+ "'"+student.getAddress()+"',"
												+ "'"+student.getHobby()+"',"
													+ "SYSDATE)";
		//위처럼 문자를 토막낼때는 '가 잘 찍혀있는지 확인해야해.
		//'"+변수넣기+"'
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url,user,password);
			Statement stmt = conn.createStatement();
			//쿼리문 실행 - DML중 INSERT를 하는거야(INSERT,UPDATE,DELETE다 똑같아)
			//executeUpdate는 성공하면 행의 숫자가, 실패하면 0이 반환된다.
			//sql에서 0개 행이(가) 업데이트되었습니다.에서의 0과 같은 의미
			result = stmt.executeUpdate(sql);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}


//select->executeQuery->resultSet을 받고 dml->executeUpdate->int로 받는다.