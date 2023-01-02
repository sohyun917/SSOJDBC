package com.kh.jdbc.day03.member.run;

import java.util.List;

import com.kh.jdbc.day03.member.controller.MemberController;
import com.kh.jdbc.day03.member.model.vo.Member;
import com.kh.jdbc.day03.member.view.MemberView;

public class MemberRun {
	
	public static void main(String [] args) {
		MemberView mView = new MemberView();
		MemberController mCon = new MemberController();
		Member member = new Member();
		List<Member> mList = null;
		int result = 0;
		String memberId = "";
		gone :
		while(true) {
			int choice = mView.mainMenu();
			switch(choice) {
			case 1 : 
				mList = mCon.printAll();
				if(mList.size() > 0) {
					mView.showAllMember(mList);
				}else {
					mView.displayError("회원정보가 없습니다.");
				}
				break;
			case 2 : 
				//아이디로 조회
				break;
			case 3 : 
				//이름으로 조회
				break;
			case 4 : 
				member = mView.insertMember();
				result = mCon.registerMember(member);
				if(result > 0) {
					mView.displaySuccess("가입성공");
				}else {
					mView.displayError("회원가입이 완료되지 않았습니다.");
				}
				break;
			case 5 : 
				//회원정보수정
				memberId = mView.inputMemberId("수정");
				member = mCon.printOneById(memberId);
				if(member != null) {
					member = mView.modifyMember(memberId);
					result = mCon.modifyMember(member);
					if(result > 0) {
						mView.displaySuccess("수정완료");						
					}else {
						mView.displayError("정보수정이 완료되지 않았습니다.");
					}
				}else {
					mView.displayError("존재하지 않는 회원입니다.");
				}
				break;
			case 6 :
				//회원탈퇴
				break;
			case 0 : break gone;
			}
		}
		
	}
}
