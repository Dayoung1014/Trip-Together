package edu.ssafy.enjoytrip.hotplace.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.ssafy.enjoytrip.hotplace.model.HotplaceDto;

@Mapper
public interface HotplaceMapper {
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
		
		// 글 내용으로 글 id 가져오기 (file 저장 시 id 넣어주기 위해)
		String getHotplacId(HotplaceDto hotplaceDto) throws SQLException;
		
		// 좋아요 순 정렬 
		List<HotplaceDto> orderByLike(String id) throws SQLException;
}
