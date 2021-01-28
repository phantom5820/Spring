package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.json.JSONArray;

import config.DBManager;
import dto.ProductDTO;

public class ProductDAO {
	private static ProductDAO instance = new ProductDAO();
	private Connection conn;

	private ProductDAO() {
		conn = DBManager.getInstance().getConn();
	}

	public static ProductDAO getInstance() {
		if (instance == null)
			instance = new ProductDAO();
		return instance;
	}

	public ArrayList<ProductDTO> selectProductAllList() {
		String sql = "select * from product";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new ProductDTO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4),rs.getString(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	
	public ProductDTO selectProduct(String pno) {
		String sql = "select * from product where p.pno = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductDTO dto = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new ProductDTO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}

	public ArrayList<ProductDTO> selectProductName(String pname) throws Exception {
		String sql = "select * from product where product_name like '%'||?||'%'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pname);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new ProductDTO(rs.getString(1), rs.getString(2), rs.getInt(3),rs.getInt(4), rs.getString(5)));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() == 0)
			throw new Exception("데이터가 없습니다.");

		return list;
	}

	public void insertProduct(ProductDTO productDTO) throws SQLException {
		String sql = "insert into product values(?,?,?,?,?)";
		PreparedStatement pstmt = null;
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, productDTO.getPno());
		pstmt.setString(2, productDTO.getPname());
		pstmt.setInt(3, productDTO.getPrice());
		pstmt.setInt(4, productDTO.getPrice());
		pstmt.setString(5, productDTO.getMaker());
		pstmt.executeUpdate();
		pstmt.close();
	}

}







