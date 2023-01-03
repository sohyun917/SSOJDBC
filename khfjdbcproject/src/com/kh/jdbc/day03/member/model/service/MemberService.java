package com.kh.jdbc.day03.member.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.jdbc.common.JDBCTemplate;
import com.kh.jdbc.day03.member.model.dao.MemberDAO;
import com.kh.jdbc.day03.member.model.vo.Member;

public class MemberService {
// DB연결관리, 트랜잭션 관리 담당
	
	//private JDBCTemplate jdbcTemplate;
	private MemberDAO mDao;
	
	public MemberService() {
		//jdbcTemplate = JDBCTemplate.getDriverLoad();
		mDao = new MemberDAO();		//생략하지 말기!
	}
	/**
	 * 회원전체조회
	 * @return
	 */
	public List<Member> selectAll() {
		List<Member> mList = null;
//		MemberDAO mDao = new MemberDAO();
//		JDBCTemplate jdbcTemplate = JDBCTemplate.getDriverLoad();
		Connection conn = JDBCTemplate.getConnection();
		mList = mDao.selectAll(conn);
//		jdbcTemplate.close(conn);
		return mList;
	}
	/**
	 * 회원가입
	 * @param member
	 * @return
	 */
	public int insertMember(Member member) {
//		MemberDAO mDao = new MemberDAO();
//		JDBCTemplate jdbcTemplate = JDBCTemplate.getDriverLoad();
		Connection conn = JDBCTemplate.getConnection();
		int result = mDao.insertMember(conn, member);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
//		jdbcTemplate.close(conn);
		return result;
	}
	/**
	 * 회원정보수정
	 * @param member
	 * @return
	 */
	public int updateMember(Member member) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mDao.updateMember(conn, member);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
//		JDBCTemplate.close(conn);
		return result;
	}
	/**
	 * 아이디로 조회
	 * @param memberId
	 * @return
	 */
	public Member selectOneById(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		Member member = mDao.selectOneById(conn,memberId);
//		JDBCTemplate.close(conn);
		return member;
	}
	/**
	 * 탈퇴
	 * @param memberId
	 * @return
	 */
	public int deleteMember(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mDao.deleteMember(conn, memberId);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}
	/**
	 * 이름으로 조회
	 * @param memberName
	 * @return
	 */
	public List<Member> seletOneByName(String memberName) {
		Connection conn = JDBCTemplate.getConnection();
		List<Member> mList = mDao.selecOneByName(conn, memberName);
		return mList;
	}
}
