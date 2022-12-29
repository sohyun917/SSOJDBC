package com.kh.jdbc.day01.student.run;

import java.util.List;

import com.kh.jdbc.day01.student.controller.StudentController;
import com.kh.jdbc.day01.student.model.vo.Student;
import com.kh.jdbc.day01.student.view.StudentView;

public class StudentRun {
	public static void main(String [] args) {
		StudentView sView = new StudentView();
		StudentController sCon = new StudentController();
		Student student = null;
		List<Student> sList = null;
		String studentId = "";
		String studentName = "";
		int result = 0;
		done :
		while(true) {
			int choice = sView.mainMenu();
			switch(choice) {
			case 1 :
				//전체조회
				sList = sCon.printAll();
				if(!sList.isEmpty()) {
					sView.showAll(sList);					
				}else {
					sView.displayError("데이터가 존재하지 않습니다.");
				}
				break;
			case 2 : 
				//아이디로 조회
				studentId = sView.inputStudentId("검색");
				student = sCon.printOneById(studentId);
				if(student != null) {
					sView.showOne(student);					
				}else {
					sView.displayError("일치하는 데이터가 없습니다.");
				}
				break;
			case 3 : 
				//이름으로 조회
				studentName = sView.inputStudentName("검색");
				sList = sCon.printAllByName(studentName);
				//sList는 절대 null이 될 수 없기 때문에(sList = new ArrayList<Student>니까 항상 객체가 생성되어있는 상태)
				//비어있는지 아닌지 보고 출력해준다.
				if(!sList.isEmpty()) {
					sView.showAll(sList);					
				}else {
					sView.displayError("일치하는 데이터가 없습니다.");
				}
				break;
			case 4 : 
				//회원가입
				student = sView.inputStudent();
				result = sCon.registerStudent(student);
				if(result > 0) {
					sView.displaySuccess("가입이 완료되었습니다.");
				}else {
					sView.displayError("가입에 실패하였습니다.");
				}
				break;
			case 5 : 
				//회원정보수정
				studentId = sView.inputStudentId("수정");
				student = sCon.printOneById(studentId);
				if(student != null) {
					student = sView.modifyStudent(student);
//					student.setStudentId(studentId);
					sCon.modifyStudent(student);
				}else {
					sView.displayError("일치하는 학생이 없습니다.");
				}
				break;
			case 6 : 
				//회원탈퇴
				studentId = sView.inputStudentId("삭제");
				result = sCon.removeStudent(studentId);
				if(result > 0) {
					sView.displaySuccess("탈퇴 완료");
				}else {
					sView.displayError("탈퇴되지 않았습니다.");
				}
				break;
			case 0 : 
				//프로그램 종료
				break done;
			default : 
				sView.printMessage("잘못입력하셨습니다. 1 ~ 6 사이의 수를 입력해주세요");
				//잘못입력하셨습니다. 1~6사이의 수를 입력해주세요
				break;
			}
		}
		
//		StudentController sCon = new StudentController();
//		StudentView sView = new StudentView();
//		//List<Student> sList = sCon.printAll();
//		//sView.showAll(sList);
		
//		//데이터는 이미 준비되어있다
//		Student student = new Student();
//		student.setStudentId("khuser02");
//		student.setStudentName("이용자");
//		student.setStudentPwd("pass02");
//		student.setAge(22);
//		student.setGender("M");
//		Student student = sView.inputStudent();
//		//데이터 준비 완료
//		int result = sCon.registerMember(student);
//		if(result>0) {
//			System.out.println("SUCCESS!!!!!!");
//		}else {
//			System.out.println("FAILED!!!!!!");
//		}

	}
}
