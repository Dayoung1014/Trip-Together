package edu.ssafy.enjoytrip.attraction.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import edu.ssafy.enjoytrip.attraction.model.AttractionDto;
import edu.ssafy.enjoytrip.attraction.model.GugunDto;
import edu.ssafy.enjoytrip.attraction.model.SidoDto;
import edu.ssafy.enjoytrip.attraction.model.mapper.AttractionMapper;

@Service
public class AttractionServiceImpl implements AttractionService {

	private AttractionMapper attractionMapper;
	
	public AttractionServiceImpl(AttractionMapper attractionMapper) {
		super();
		this.attractionMapper = attractionMapper;
	}
	
	// 지역 가져오기
	public List<SidoDto> listSido() {
		return attractionMapper.listSido();
	}
	
	// 구군 가져오기
	public List<GugunDto> listGugun(String sidoCode) {
		return attractionMapper.listGugun(sidoCode);
	}
	


	@Override
	public List<AttractionDto> listAttraction(String type, String sidoCode, String gugunCode) throws SQLException {
		return attractionMapper.listAttraction(type, sidoCode, gugunCode);
	}

	@Override
	public AttractionDto getAttraction(String contentId) throws SQLException {
		return attractionMapper.getAttraction(contentId);
	}

}
