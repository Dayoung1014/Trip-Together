package edu.ssafy.enjoytrip.hotplace.model.service;

import java.sql.SQLException;
import java.util.List;

import edu.ssafy.enjoytrip.hotplace.model.HotplaceFileDto;
 

public interface HotplaceFileService {

	//hotplace의 사진 등록
	void registImg(HotplaceFileDto hotplaceFileDto) throws SQLException;
	
	//hotplace의 사진 삭제
	void deleteImg(String id) throws SQLException;
	
 
	//hotplace id로 사진 가져오기
	List<HotplaceFileDto> listImg(String hotplaceId) throws SQLException;
	
}
