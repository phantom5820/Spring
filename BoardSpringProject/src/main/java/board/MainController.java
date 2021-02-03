package board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.dto.BoardDTO;
import board.dto.CommentDTO;
import board.dto.FileDTO;
import board.dto.MemberDTO;
import board.service.BoardService;
import board.service.MemberService;
import board.vo.PaggingVO;

@Controller
public class MainController {
	private MemberService memberService;
	private BoardService boardService;
	public MainController(MemberService memberService, BoardService boardService) {
		super();
		this.memberService = memberService;
		this.boardService = boardService;
	}

	@RequestMapping("/")
	public String main(HttpServletRequest request) {
		return index(request);
	}
	
	@RequestMapping("/index.do")
	public String index(HttpServletRequest request) {
		int page = 1;
		//페이지 셋팅
		if(request.getParameter("pageNo") != null)
			page = Integer.parseInt(request.getParameter("pageNo"));
		List<BoardDTO> list = boardService.selectBoardList(page);//글목록 읽어옴
		int count = boardService.selectCount();
		PaggingVO vo = new PaggingVO(count, page);
		request.setAttribute("list", list);
		request.setAttribute("pagging", vo);
		System.out.println(list.toString());
		return "main";
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request, HttpSession session) {
		session.invalidate();
		return main(request);
	}
	
	@RequestMapping("/loginView.do")
	public String loginView() {
		return "login";
	}


	@RequestMapping("/login.do")
	public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		MemberDTO dto = memberService.login(id, pass);
		// HttpSession session = request.getSession();
		if (dto != null) {
			session.setAttribute("login", true);
			session.setAttribute("id", dto.getId());
			session.setAttribute("name", dto.getName());
			session.setAttribute("grade", dto.getGrade());
			session.setMaxInactiveInterval(10 * 60);
			System.out.println("로그인 성공");
			return index(request);
		} else {
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<script>alert('아이디 비번을 확인하세요');history.back();</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	@RequestMapping("/updateMemberView.do")
	public String updateMemberView(HttpServletRequest request, HttpSession session) {

		String id = (String) session.getAttribute("id");

		// 세션 정보가 유효한지 체크
		if (id == null)
			return "login";

		// 회원정보 조회
		MemberDTO dto = memberService.selectMember(id);
		request.setAttribute("dto", dto);

		return "member_update_view";
	}

	@RequestMapping("/updateMemberAction.do")
	public String updateMemberAction(HttpServletRequest request, HttpServletResponse response) {
		String id = (String) request.getSession().getAttribute("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));

		if (id == null) {
			// 세션이 만료된 경우
			try {
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().write(
						"<script>alert('세션이 만료되었습니다." + " 다시 로그인해 주세요');" + "location.href='login.do';</script>");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		memberService.updateMember(new MemberDTO(id, pass, name, age));

		return "main";

	}

	@RequestMapping("/memberAdminMain.do")
	public String memberAdminMain(HttpServletRequest request) {
		List<MemberDTO> list = memberService.selectAllMember();
		request.setAttribute("list", list);
		return "member_manage_main";
	}

	@RequestMapping("/memberSerach.do")
	public String memberAdminSearch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String kind = request.getParameter("kind");
		String search = request.getParameter("search");
		List<MemberDTO> list = memberService.selectSearchMember(kind, search);
		response.setContentType("text/html;charset=utf-8");
		JSONArray array = new JSONArray(list);
		JSONObject obj = new JSONObject();
		obj.put("result", array);
		if(list.size()>0) {
			obj.put("responseCode", 200);
			obj.put("responseMessage", "Search Succes");
		}
		else {
			obj.put("responseCode", 500);
			obj.put("responseMessage", "Search Fail");
		}
			
		try {
			response.getWriter().write(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/memberAjaxUpdate.do")
	public String memberAjaxUpdate(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String grade = request.getParameter("grade");

		MemberDTO dto = new MemberDTO(id, null, name, age, grade);
		int count = memberService.updateMemberAdmin(dto);
		try {
			if (count == 1)
				response.getWriter().write("true");
			else
				response.getWriter().write("false");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/memberAjaxDelete.do")
	public String memberAjaxDelete(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		int count = memberService.deleteMemberAdmin(id);
		try {
			response.getWriter().write(count + "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping("/sendLog.do")
	public String sendLog(HttpServletRequest request, HttpServletResponse response) {
		String log_date = request.getParameter("log_date");
		int code_number = Integer.parseInt(request.getParameter("code_number"));
		String message = request.getParameter("message");
		System.out.println(log_date + " " + code_number + " " + message);
		int count = memberService.insertLog(log_date,code_number,message);
		System.out.println(count);
		try {
			response.getWriter().write("add count + "+count);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/boardView.do")
	public String boardView(HttpServletRequest request) {
		//게시글 하나 읽어오기
		//1.request에서 게시글 번호 읽어오고
		int bno = 0;
		if(request.getParameter("bno") != null)
			bno = Integer.parseInt(request.getParameter("bno"));
		else
			bno = (int)request.getAttribute("bno");
		//1-1. 해당 게시글 조회수 증가
		boardService.addCount(bno);
		//2. DB 해당 게시글 정보를 읽어온다.
		BoardDTO dto = boardService.selectBoard(bno);//글목록 읽어옴
		//2-1. 댓글 불러오는 부분
		List<CommentDTO> list = boardService.selectBoardComment(bno);
		//2-2 첨부파일 로드 부분
		 
		//3. request에 BoardDTO 저장
		request.setAttribute("board", dto);
		request.setAttribute("comment", list);
		
		
		return "board_detail_view";
	}
	@RequestMapping("/insertComment.do")						//ajax로 넘겨주기때문에 리스폰스
	public String insertComment(HttpServletRequest request, HttpServletResponse response) {
		int bno = Integer.parseInt(request.getParameter("bno"));
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		boardService.insertComment(new CommentDTO(bno, content, writer));
		
		
		return null;
	}
	@RequestMapping("/commentLike.do")
	public String commentLike(HttpServletRequest request) {
		int cno = Integer.parseInt(request.getParameter("cno"));
		boardService.updateCommentLike(cno);
		return boardView(request);
	}
	@RequestMapping("/commentHate.do")
	public String commentHate(HttpServletRequest request) {
		int cno = Integer.parseInt(request.getParameter("cno"));
		boardService.updateCommentHate(cno);
		return boardView(request);
	}
	
	@RequestMapping("/plusLikeHate.do")
	public String plusLikeHate(HttpServletRequest request, HttpServletResponse response) {
		int bno = Integer.parseInt((String)request.getParameter("bno"));
		int mode = Integer.parseInt((String)request.getParameter("mode"));
		int count = 0;
		count = boardService.addBoardLikeHate(mode, bno);
		try {
			
			response.getWriter().write(String.valueOf(count));
//			response.getWriter().write(count + ""); //바로 위에꺼랑 같은것이다. 숫자를 문자열로 바꿔주는 부분이다.
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping("/boardWriteView.do")
	public String boardWriteView() {
		return "board_write_view";
	}

	@RequestMapping("/boardWriteAction.do")
	public String boardWriteAction(MultipartHttpServletRequest request) {
		//글번호 먼저 발급
		int bno = boardService.newBno();
		//글쓸때 제목 작성자 내용을 읽어온다
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		//글쓴 내용을 등록해준다
		boardService.insertBoard(new BoardDTO(bno, title, writer, content));
		request.setAttribute("bno", bno);
		
		//파일목록 불러오기
		List<MultipartFile> fileList = request.getFiles("file");
		System.out.println(fileList.size());
		String path = "c:\\fileupload\\"+writer+"\\";
		ArrayList<FileDTO> fList = new ArrayList<FileDTO>();
		
		for(MultipartFile mf : fileList) {
			String originalFileName = mf.getOriginalFilename();
			long fileSize = mf.getSize();
			if(fileSize == 0) continue; //파일사이즈가 0이면 파일을 못 읽어온것이다
			System.out.println("originalFileName : " + originalFileName);
			System.out.println("fileSize : " + fileSize);
			
			try {
			//파일 업로드
			String safeFile = path + originalFileName;
			fList.add(new FileDTO(bno, writer, originalFileName));
			File parentPath = new File(path);
			if(!parentPath.exists()) parentPath.mkdirs(); //경로 생성
				mf.transferTo(new File(safeFile));
			
			
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		boardService.insertFileList(fList);//올린파일 DB에 관리하는 부분
		
		return boardView(request);
	}

}