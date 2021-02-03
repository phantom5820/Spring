package student;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import student.dto.StudentDTO;
import student.service.StudentService;

@Controller
public class MainController {
	private StudentService studentService;

	public MainController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@RequestMapping("/")
	public String student(Model model) {
		List<StudentDTO> list = studentService.selectAllStudent();
		model.addAttribute("list",list);
		return "student_search";
	}
	
	@RequestMapping("/studentSerach.do")
	public String studentSerach(HttpServletRequest request, HttpServletResponse response) {
		String kind = request.getParameter("kind");
		String search = request.getParameter("search");
		List<StudentDTO> list = studentService.selectSearchStudent(kind, search);
		response.setContentType("text/html;charset=utf-8");
		JSONArray array = new JSONArray(list);
		JSONObject obj = new JSONObject();
		System.out.println("result : " + array);
		obj.put("result", array);
		if(list.size()>0) {
			obj.put("Code", 200);
			obj.put("Message", "정상적으로 조회되었습니다.");
		}
		else {
			obj.put("Code", 500);
			obj.put("result", "None");
			obj.put("Message", "조회된 데이터가 없습니다.");
			
		}
		
		try {
			response.getWriter().write(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	@RequestMapping("sendLog.do")
	public String sendLog(HttpServletRequest request, HttpServletResponse response) {
		String log_date = request.getParameter("log_date");
		int error_code = Integer.parseInt(request.getParameter("error_code"));
		String content = request.getParameter("content");
		System.out.println(log_date + " " + error_code + " " + content);
		int count = studentService.insertLog(log_date,error_code,content);
		System.out.println(count);
		try {
			response.getWriter().write("add count + "+count);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}

