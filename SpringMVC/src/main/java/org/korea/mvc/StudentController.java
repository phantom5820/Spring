package org.korea.mvc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.korea.mvc.dto.StudentDTO;
import org.korea.mvc.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;

public class StudentController {
	private StudentService service;

	public StudentController(StudentService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping("/student.do")
	public String studentMain(HttpServletRequest request) {
		ArrayList<StudentDTO> list = service.selectAllStudent();
		request.setAttribute("list", list);
		return "student_manager";
	}
}	
