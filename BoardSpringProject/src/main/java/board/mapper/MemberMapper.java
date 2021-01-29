package board.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import board.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	MemberDTO login(HashMap<String, Object> map);

	

}
