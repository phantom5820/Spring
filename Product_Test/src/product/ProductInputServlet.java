package product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.ProductDAO;
import dto.ProductDTO;

/**
 * Servlet implementation class ProductInputServlet
 */
@WebServlet("/productInput.do")
public class ProductInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductInputServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		response.setCharacterEncoding("utf-8");
		String pno = request.getParameter("pno");
		String pname = request.getParameter("pname");
		String maker = request.getParameter("maker");
		int price = Integer.parseInt(request.getParameter("price"));
		int ea = Integer.parseInt(request.getParameter("ea"));
		try {
			if(Character.isDigit(price)==false || Character.isDigit(ea)==false) throw new ServletException("금액과 재고는 숫자만 입력하세요");
			if(price < 0 || ea < 0) throw new Exception(); 
			ProductDAO.getInstance().insertProduct(new ProductDTO(pno, pname, price, ea, maker));
			ArrayList<ProductDTO> list = ProductDAO.getInstance().selectProductAllList();
			JSONArray arr = new JSONArray(list);//json으로 변형
			JSONObject obj = new JSONObject();
			obj.put("result", arr);
			response.getWriter().write(obj.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			response.setStatus(1002);
			response.getWriter().write("입력한 데이터가 잘못되었습니다.");
		}catch(Exception e) {
			//클라이언트 ajax 사용시 에러 전달 방법
			response.setStatus(1003);
			response.getWriter().write("금액이나 재고는 양수만 입력하세요.");//에러메세지 전송
		}
	
		//request.getRequestDispatcher("product_manager.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
