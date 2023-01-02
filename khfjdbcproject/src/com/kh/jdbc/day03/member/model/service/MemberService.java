package com.kh.jdbc.day03.member.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.jdbc.common.JDBCTemplate;
import com.kh.jdbc.day03.member.model.dao.MemberDAO;
import com.kh.jdbc.day03.member.model.vo.Member;

public class MemberService {
// DB연결관리, 트랜잭션 관리 담당
	
	private JDBCTemplate jdbcTemplate;
	private MemberDAO mDao;
	
	public MemberService() {
		jdbcTemplate = JDBCTemplate.getDriverLoad();
		mDao = new MemberDAO();		//생략하지 말기!
	}
	
	public List<Member> selectAll() {
		List<Member> mList = null;
//		MemberDAO mDao = new MemberDAO();
//		JDBCTemplate jdbcTemplate = JDBCTemplate.getDriverLoad();
		Connection conn = jdbcTemplate.getConnection();
		mList = mDao.selectAll(conn);
		jdbcTemplate.close(conn);
		return mList;
	}
	
	public int insertMember(Member member) {
//		MemberDAO mDao = new MemberDAO();
//		JDBCTemplate jdbcTemplate = JDBCTemplate.getDriverLoad();
		Connection conn = jdbcTemplate.getConnection();
		int result = mDao.insertMember(conn, member);
		if(result > 0) {
			jdbcTemplate.commit(conn);
		} else {
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}
	
	public int updateMember(Member member) {
		Connection conn = jdbcTemplate.getConnection();
		int result = mDao.updateMember(conn, member);
		if(result > 0) {
			jdbcTemplate.commit(conn);
		}else {
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}

	public Member selectOneById(String memberId) {
		Connection conn = jdbcTemplate.getConnection();
		Member member = mDao.selectOneById(conn,memberId);
		jdbcTemplate.close(conn);
		return member;
	}
}
