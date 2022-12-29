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
		DONE :
		while(true) {
			int choice = mView.mainMenu();
			switch(choice) {
			case 1 : 
				mList = mCon.printAll();
				mView.showAllMember(mList);
				break;
			case 2 : break;
			case 3 : break;
			case 4 : 
				member = mView.insertMember();
				int result = mCon.registerMember(member);
				if(result > 0) {
					mView.displaySuccess();
				}else {
					mView.displayError();
				}
				break;
			case 5 : break;
			case 6 : break;
			case 0 : break DONE;
			default : break;
			}
		}
	}
}
