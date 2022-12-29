package com.kh.jdbc.day02.member.controller;

import java.util.List;

import com.kh.jdbc.day02.member.model.dao.MemberDAO;
import com.kh.jdbc.day02.member.model.vo.Member;

public class MemberController {
	public List<Member> printAll() {
		MemberDAO mDao = new MemberDAO();
		List<Member> mList = mDao.seletAll();
		return mList;
	}
	
	public int registerMember(Member member) {
		MemberDAO mDao = new MemberDAO();
		int result = mDao.insertMember(member);
		return result;
	}
}
