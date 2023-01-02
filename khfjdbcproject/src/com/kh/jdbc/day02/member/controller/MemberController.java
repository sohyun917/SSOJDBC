package com.kh.jdbc.day02.member.controller;

import java.util.List;

import com.kh.jdbc.day02.member.model.dao.MemberDAO;
import com.kh.jdbc.day02.member.model.vo.Member;

public class MemberController {
	/**
	 * 전체멤버조회
	 * @return
	 */
	public List<Member> printAll() {
		MemberDAO mDao = new MemberDAO();
		List<Member> mList = mDao.seletAll();
		return mList;
	}
	/**
	 * 회원등록
	 * @param member
	 * @return
	 */
	public int registerMember(Member member) {
		MemberDAO mDao = new MemberDAO();
		int result = mDao.insertMember(member);
		return result;
	}
	/**
	 * 아이디로 조회
	 * @param memberId
	 * @return
	 */
	public Member printOneById(String memberId) {
		MemberDAO mDao = new MemberDAO();
		Member member = mDao.selectOneById(memberId);
		return member;
	}
	/**
	 * 이름으로 조회
	 * @param memberName
	 * @return
	 */
	public List<Member> printAllByName(String memberName) {
		MemberDAO mDao = new MemberDAO();
		List<Member> mList = mDao.selectAllByName(memberName);
		return mList;
	}
	/**
	 * 회원탈퇴
	 * @param memberId
	 * @return
	 */
	public int removeMember(String memberId) {
		MemberDAO mDao = new MemberDAO();
		int result = mDao.deleteStudent(memberId);
		return result;
	}
	/**
	 * 회원정보수정
	 * @param member
	 * @return
	 */
	public int modifyMember(Member member) {
		MemberDAO mDao = new MemberDAO();
		int result = mDao.updateMember(member);
		return result;
	}
	/**
	 * 로그인
	 * @param member
	 * @return
	 */
	public int checkInfo(Member member) {
		MemberDAO mDao = new MemberDAO();
		int result = mDao.checkLogin(member);
		return result;
	}
}
