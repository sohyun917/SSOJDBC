package com.kh.jdbc.day01.student.run;

import com.kh.jdbc.day01.student.controller.StudentController;

public class StudentRun {
	public static void main(String [] args) {
		StudentController sCon = new StudentController();
		sCon.printAll();
	}
}
