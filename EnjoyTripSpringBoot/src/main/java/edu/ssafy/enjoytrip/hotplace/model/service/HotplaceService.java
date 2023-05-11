package edu.ssafy.enjoytrip.hotplace.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import edu.ssafy.enjoytrip.hotplace.model.HotplaceDto;

public interface HotplaceService {
		// 등록 
		void writeHotplace(HotplaceDto hotplaceDto) throws SQLException;
		
		// 조회 
		List<HotplaceDto> listHotplace() throws SQLException;
		
		// 수정 
		void modifyHotplace(HotplaceDto hotplaceDto) throws SQLException;
		
		// 삭제 
		void deleteHotplace(String id) throws SQLException;
		
		// 글 번호로 글 가져오기 
		HotplaceDto getHotplace(String id) throws SQLException;
		
		// 좋아요 순 정렬 
		List<HotplaceDto> orderByLike(String id) throws SQLException;
}
