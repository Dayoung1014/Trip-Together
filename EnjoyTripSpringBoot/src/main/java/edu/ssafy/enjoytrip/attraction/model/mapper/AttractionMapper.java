package edu.ssafy.enjoytrip.attraction.model.mapper;

import java.sql.SQLException;
import java.util.*;

import org.apache.ibatis.annotations.Mapper;

import edu.ssafy.enjoytrip.attraction.model.AttractionDto;
import edu.ssafy.enjoytrip.attraction.model.GugunDto;
import edu.ssafy.enjoytrip.attraction.model.SidoDto;

@Mapper
public interface AttractionMapper {
	// 지역 가져오기
	public List<SidoDto> listSido();
	
	// 구군 가져오기
	public List<GugunDto> listGugun(String sidoCode);
	
	// 지역, 구군, 분류로 리스트 가져오기
	public List<AttractionDto> listAttraction(String type, String sidoCode, String gugunCode)throws SQLException;

	
	public AttractionDto getAttraction(String contentId)throws SQLException;

}
