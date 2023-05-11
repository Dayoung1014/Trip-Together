package edu.ssafy.enjoytrip.attraction.model.service;

import java.sql.SQLException;
import java.util.*;

import edu.ssafy.enjoytrip.attraction.model.AttractionDto;
import edu.ssafy.enjoytrip.attraction.model.GugunDto;
import edu.ssafy.enjoytrip.attraction.model.SidoDto;

public interface AttractionService {
	// 지역 가져오기
	public List<SidoDto> listSido();
	
	// 구군 가져오기
	public List<GugunDto> listGugun(String sidoCode);
	
	public List<AttractionDto> listAttraction(String type, String sidoCode, String gugunCode)throws SQLException;
	
	public AttractionDto getAttraction(String contentId)throws SQLException;
	
}
