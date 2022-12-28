package com.kh.jdbc.day01.student.controller;

import com.kh.jdbc.day01.student.model.dao.StudentDAO;

public class StudentController {
	
	public void printAll() {
		StudentDAO sDao = new StudentDAO();
		sDao.selectAll();
	}
}
