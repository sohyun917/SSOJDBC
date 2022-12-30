package com.kh.jdbc.day02.member.run;

import java.util.List;

import com.kh.jdbc.day02.member.controller.MemberController;
import com.kh.jdbc.day02.member.model.vo.Member;
import com.kh.jdbc.day02.member.view.MemberView;

public class MemberRun {
	public static void main(String [] args) {
		MemberView mView = new MemberView();
		MemberController mCon = new MemberController();
		Member member = new Member();
		List<Member> mList = null;
		String memberId = "";
		String memberName = "";
		int result = 0;
		DONE :
		while(true) {
			int choice = mView.mainMenu();
			switch(choice) {
			case 1 : 
				mList = mCon.printAll();
				mView.showAllMember(mList);
				break;
			case 2 : 
				memberId = mView.inputMemberId("검색");
				member = mCon.printOneById(memberId);
				if(member != null) {
					mView.showOne(member);					
				}else {
					mView.displayError("입력하신 아이디가 존재하지 않습니다.");
				}
				break;
			case 3 : 
				memberName = mView.inputMemberName();
				mList = mCon.printAllByName(memberName);
				if(member != null) {
					mView.showAllMember(mList);
				}
				break;
			case 4 : 
				member = mView.insertMember();
				result = mCon.registerMember(member);
				if(result > 0) {
					mView.displaySuccess("가입성공");
				}else {
					mView.displayError("가입실패");
				}
				break;
			case 5 : 
				//수정
				memberId = mView.inputMemberId("회원정보를 수정");
				member = mCon.printOneById(memberId);
				if(member != null) {
					member = mView.modifyMember(member);
					mCon.modifyMember(member);
				}else {
					mView.displayError("입력하신 아이디가 존재하지 않습니다.");
				}
				break;
			case 6 : 
				//탈퇴
				memberId = mView.inputMemberId("탈퇴");
				result = mCon.removeMember(memberId);
				if(result > 0) {
					mView.displaySuccess("탈퇴성공");
				}else {
					mView.displayError("탈퇴실패");
				}
				break;
			case 7 : 
				member = mView.inputLoginInfo();
				result = mCon.checkInfo(member);
				if(result > 0) {
					//로그인성공
					mView.displaySuccess("로그인성공");
				}else {
					//로그인실패
					mView.displayError("로그인실패");
				}
				break;
			case 0 : break DONE;
			default : break;
			}
		}
	}
}
