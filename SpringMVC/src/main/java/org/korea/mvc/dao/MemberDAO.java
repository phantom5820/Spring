package org.korea.mvc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.korea.mvc.dto.MemberDTO;
import org.springframework.stereotype.Service;

import config.DBManager;

@Service
public class MemberDAO {
	private DBManager manager;
	

	public MemberDAO(DBManager manager) {
		super();
		manager = new DBManager();
	}
	
	public MemberDTO login(String id, String pass) {
		MemberDTO dto = null;
		String sql = "select * from member where id = ? and pass = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = manager.getConnection().prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new MemberDTO(rs.getString(1), null, rs.getString(3), rs.getInt(4), rs.getInt(5));
			}
			System.out.println("login - MemberDTO");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dto;
	}
	

	
	
	





	
	
}
