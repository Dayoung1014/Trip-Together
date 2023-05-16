package edu.ssafy.enjoytrip.hotplace.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.ssafy.enjoytrip.hotplace.model.HotplaceFileDto;
import edu.ssafy.enjoytrip.hotplace.model.mapper.HotplaceFileMapper;

@Service
public class HotplaceFileServiceImpl implements HotplaceFileService{

	private HotplaceFileMapper hotplaceFileMapper;

	public HotplaceFileServiceImpl(HotplaceFileMapper hotplaceFileMapper) {
		super();
		this.hotplaceFileMapper = hotplaceFileMapper;
	}
 
	@Override
	public void registImg(HotplaceFileDto hotplaceFileDto) throws SQLException {
		hotplaceFileMapper.registImg(hotplaceFileDto);
	}

	@Override
	public void deleteImg(String id) throws SQLException {
		hotplaceFileMapper.deleteImg(id);
	} 

	@Override
	public List<HotplaceFileDto> listImg(String hotplaceId) throws SQLException {
		return hotplaceFileMapper.listImg(hotplaceId);
	}
	
	
}
