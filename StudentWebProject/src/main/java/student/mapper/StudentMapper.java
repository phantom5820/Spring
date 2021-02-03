package student.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import student.dto.StudentDTO;

@Mapper
public interface StudentMapper {

	List<StudentDTO> selectSearchStudent(HashMap<String, Object> map);

	List<StudentDTO> selectAllStudent();

	int insertLog(HashMap<String, Object> map);

}
