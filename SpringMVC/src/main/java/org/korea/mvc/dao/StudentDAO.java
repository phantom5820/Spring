package org.korea.mvc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.korea.mvc.dto.StudentDTO;
import org.springframework.stereotype.Service;

import config.DBManager;

@Service
public class StudentDAO {
	private DBManager manager;

	public StudentDAO(DBManager manager) {
		super();
		this.manager = manager;
		System.out.println("studentDAO 생성");
	}
	
	public ArrayList<StudentDTO> selectAllStudent(){
		ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
		String sql = "select * from student";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = manager.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new StudentDTO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.close(pstmt, rs);
		}
		return list;
	}
	
}
