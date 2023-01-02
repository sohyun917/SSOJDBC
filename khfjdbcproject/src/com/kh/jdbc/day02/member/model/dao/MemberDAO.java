package com.kh.jdbc.day02.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day02.member.model.vo.Member;

public class MemberDAO {
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER = "STUDENT";
	private final String PASSWORD = "STUDENT";
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	
	public List<Member> seletAll() {
		String sql = "SELECT * FROM MEMBER_TBL";
		Member member = null;
		//Member member = new Member(); 이렇게 위에 객체생성을 하면 안되는 이유..??
		List<Member> mList = null;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(sql);
			mList = new ArrayList<Member>();
			while(rset.next()) {
					member = new Member();
					member.setMemberId(rset.getString("MEMBER_ID"));
					member.setMemberPwd(rset.getString("MEMBER_PWD"));
					member.setMemberName(rset.getString("MEMBER_NAME"));
					member.setMemberGender(rset.getString("MEMBER_GENDER"));
					member.setMemberAge(rset.getInt("MEMBER_AGE"));
					member.setMemberEmail(rset.getString("MEMBER_EMAIL"));
					member.setMemberPhone(rset.getString("MEMBER_PHONE"));
					member.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
					member.setMemberHobby(rset.getString("MEMBER_HOBBY"));
					member.setMemberDate(rset.getTimestamp("MEMBER_DATE"));
					mList.add(member);
			}
			conn.close();
			stmt.close();
			rset.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mList;
	}

	public int insertMember(Member member) {
		String sql = "INSERT INTO MEMBER_TBL VALUES(?,?,?,?,?,?,?,?,?,DEFAULT)";
		
//		String sql = "INSERT INTO MEMBER_TBL "
//						+ "VALUES('"+member.getMemberId()+"','"
//								+ ""+member.getMemberPwd()+"','"
//										+ ""+member.getMemberName()+"','"
//												+ ""+member.getMemberGender()+"',"
//														+ ""+member.getMemberAge()+",'"
//																+ ""+member.getMemberEmail()+"','"
//																		+ ""+member.getMemberPhone()+"','"
//																				+ ""+member.getMemberAddress()+"','"
//																						+ ""+member.getMemberHobby()+"',DEFAULT)";
		int result = 0;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			pstmt.setString(4, member.getMemberGender());
			pstmt.setString(6, member.getMemberEmail());
			pstmt.setString(8, member.getMemberAddress());
			pstmt.setString(3, member.getMemberName());
			pstmt.setInt(5, member.getMemberAge());
			pstmt.setString(7, member.getMemberPhone());
			pstmt.setString(9, member.getMemberHobby());	//쿼리문 실행준비
			//*****괄호안의 인덱스값이 중요한거지 선언순서가 중요한것은 아니다.
			result = pstmt.executeUpdate();					//쿼리문 실행
			//*****pstmt의 경우 result에서 sql이안 들어간다. 컴파일시에 sql이 들어간다.
			pstmt.close();
			conn.close();
//			Statement stmt = conn.createStatement();
//			result = stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Member selectOneById(String memberId) {
		Member member = null;
		String sql = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '"+memberId+"'";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(sql);
			if(rset.next()) {
				member = new Member();
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPwd(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setMemberGender(rset.getString("MEMBER_GENDER"));
				member.setMemberAge(rset.getInt("MEMBER_AGE"));
				member.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				member.setMemberPhone(rset.getString("MEMBER_PHONE"));
				member.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				member.setMemberHobby(rset.getString("MEMBER_HOBBY"));
				member.setMemberDate(rset.getTimestamp("MEMBER_DATE"));
			}
			rset.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

	public List<Member> selectAllByName(String memberName) {
		String sql = "SELECT * FROM MEMBER_TBL WHERE MEMBER_NAME LIKE ?";
		List<Member> mList = null;
		//Member member = null;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+memberName+"%");
			ResultSet rset = pstmt.executeQuery();
			mList = new ArrayList<Member>();
			while(rset.next()) {
				Member member = new Member();
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPwd(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setMemberGender(rset.getString("MEMBER_GENDER"));
				member.setMemberAge(rset.getInt("MEMBER_AGE"));
				member.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				member.setMemberPhone(rset.getString("MEMBER_PHONE"));
				member.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				member.setMemberHobby(rset.getString("MEMBER_HOBBY"));
				member.setMemberDate(rset.getTimestamp("MEMBER_DATE"));
				mList.add(member);
				
			}
			conn.close();
			pstmt.close();
			rset.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mList;
	}

	public int deleteStudent(String memberId) {
		String sql = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?";
		int result = 0;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
			conn.close();
			pstmt.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int updateMember(Member member) {
		String sql = "UPDATE MEMBER_TBL SET MEMBER_PWD = ?,MEMBER_EMAIL = ?, MEMBER_PHONE = ?, MEMBER_ADDRESS = ?,MEMBER_HOBBY = ? WHERE MEMBER_ID = ?";
		int result = 0;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberPwd());
			pstmt.setString(2, member.getMemberEmail());
			pstmt.setString(3, member.getMemberPhone());
			pstmt.setString(4, member.getMemberAddress());
			pstmt.setString(5, member.getMemberHobby());
			pstmt.setString(6, member.getMemberId());
			result = pstmt.executeUpdate();
			
			conn.close();
			pstmt.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int checkLogin(Member member) {
		//아이디와 비밀번호가 일치한 행의 갯수를 받고싶으니까 count를 사용하고
		//컬럼명을 count(*)로 쓸 수 없으니까 별칭으로 M_COUNT라고 만들어준다.
		String query = "SELECT COUNT(*) AS M_COUNT FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PWD = ?";
		int result = 0;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			ResultSet rset = pstmt.executeQuery();
			//로그인이 성공하면 1의 값을, 실패하면 0을 반환할거야.
			if(rset.next()) {
				result = rset.getInt("M_COUNT");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
