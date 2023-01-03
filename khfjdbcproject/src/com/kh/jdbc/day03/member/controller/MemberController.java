package com.kh.jdbc.day03.member.controller;

import java.util.List;

import com.kh.jdbc.day03.member.model.service.MemberService;
import com.kh.jdbc.day03.member.model.vo.Member;

public class MemberController {
	
	private MemberService mService;
	
	public MemberController() {
		mService = new MemberService();	//까먹지 말기! -> Null PointerException
	}
	/**
	 * 회원전체조회 Con
	 * @return List<Member>
	 */
	public List<Member> printAll() {
		List<Member> mList = mService.selectAll();
		return mList;
	}
	/**
	 * 회원가입 Con
	 * @param member
	 * @return result
	 */
	public int registerMember(Member member) {
		int result = mService.insertMember(member);
		return result;
	}
	/**
	 * 회원정보수정 Con
	 * @param member
	 * @return result
	 */
	public int modifyMember(Member member) {
		int result = mService.updateMember(member);
		return result;
	}
	/**
	 * 아이디로 조회 Con
	 * @param memberId
	 * @return Member
	 */
	public Member printOneById(String memberId) {
		Member member = mService.selectOneById(memberId);
		return member;
	}
	/**
	 * 탈퇴 Con
	 * @param memberId
	 * @return result
	 */
	public int removeMember(String memberId) {
		int result = mService.deleteMember(memberId);
		return result;
	}
	/**
	 * 이름으로 조회 Con
	 * @param memberName
	 * @return List<Member>
	 */
	public List<Member> printOneByName(String memberName){
		List<Member> mList = mService.seletOneByName(memberName);
		return mList;
	}
	
}
