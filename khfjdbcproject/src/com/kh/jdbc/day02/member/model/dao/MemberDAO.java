package com.kh.jdbc.day02.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
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
		Member member = new Member();
		List<Member> mList = null;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(sql);
			mList = new ArrayList<Member>();
			while(rset.next()) {
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
		String sql = "INSERT INTO MEMBER_TBL VALUES('"+member.getMemberId()+"','"+member.getMemberPwd()+"','"+member.getMemberName()+"','"+member.getMemberGender()+"',"+member.getMemberAge()+",'"+member.getMemberEmail()+"','"+member.getMemberPhone()+"','"+member.getMemberAddress()+"','"+member.getMemberHobby()+"',DEFAULT)";
		int result = 0;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
