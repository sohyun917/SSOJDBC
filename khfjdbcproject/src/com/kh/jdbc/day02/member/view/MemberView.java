package com.kh.jdbc.day02.member.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day02.member.model.vo.Member;
//142365순으로 만들어보자
public class MemberView {
	public int mainMenu() {
		System.out.println("====== 회원관리 프로그램 ======");
		System.out.println("1. 회원전체조회");
		System.out.println("2. 회원 아이디로 조회");
		System.out.println("3. 회원 이름으로 조회");
		System.out.println("4. 회원가입");
		System.out.println("5. 회원 정보 수정");
		System.out.println("6. 회원 탈퇴");
		System.out.println("0. 프로그램 종료");
		Scanner sc = new Scanner(System.in);
		System.out.print("메뉴 선택 : ");
		int choice = sc.nextInt();
		return choice;
	}
	
	public void showAllMember(List<Member> mList) {
		System.out.println("====== 회원전체조회 ======");
		for(Member mOne : mList) {
			System.out.println("아이디 : " + mOne.getMemberId());
			System.out.println("비밀번호 : " + mOne.getMemberPwd());
			System.out.println("이름 : " + mOne.getMemberName());
			System.out.println("성별 : " + mOne.getMemberGender());
			System.out.println("나이 : " + mOne.getMemberAge());
			System.out.println("이메일 : " + mOne.getMemberEmail());
			System.out.println("전화번호 : " + mOne.getMemberPhone());
			System.out.println("주소 : " + mOne.getMemberAddress());
			System.out.println("취미 : " + mOne.getMemberHobby());
			System.out.println("가입날짜 : " + mOne.getMemberDate());
			System.out.println("----------------------------------");
		}
	}

	public Member insertMember() {
		System.out.println("====== 회원가입 ======");
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 : ");
		String memberId = sc.next();
		System.out.print("비밀번호 : ");
		String memberPwd = sc.next();
		System.out.print("이름 : ");
		String memberName = sc.next();
		System.out.print("성별 : ");
		String memberGender = sc.next();
		System.out.print("나이 : ");
		int memberAge = sc.nextInt();
		System.out.print("이메일 : ");
		String memberEmail = sc.next();
		System.out.print("전화번호 : ");
		String memberPhone = sc.next();
		System.out.print("주소 : ");
		sc.nextLine();
		String memberAddress = sc.nextLine();
		System.out.print("취미 : ");
		String memberHobby = sc.next();
		Member member = new Member(memberId, memberPwd, memberName, memberGender, memberAge, memberEmail, memberPhone, memberAddress, memberHobby, null);
		return member;
	}
	
	public void displaySuccess() {
		System.out.println("[서비스 성공] :");
	}
	
	public void displayError() {
		System.out.println("[서비스 실패] : ");
	}
}
