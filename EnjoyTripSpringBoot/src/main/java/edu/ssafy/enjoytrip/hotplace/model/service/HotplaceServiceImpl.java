package edu.ssafy.enjoytrip.hotplace.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import edu.ssafy.enjoytrip.hotplace.model.HotplaceDto;
import edu.ssafy.enjoytrip.hotplace.model.mapper.HotplaceMapper;

@Service
public class HotplaceServiceImpl implements HotplaceService{

	private HotplaceMapper hotplaceMapper;
	
	
	public HotplaceServiceImpl(HotplaceMapper hotplaceMapper) {
		super();
		this.hotplaceMapper = hotplaceMapper;
	}

	@Override
	public void writeHotplace(HotplaceDto hotplaceDto) throws SQLException {
		hotplaceMapper.writeHotplace(hotplaceDto);
	}

	@Override
	public List<HotplaceDto> listHotplace() throws SQLException {
		return hotplaceMapper.listHotplace();
	}

	@Override
	public void modifyHotplace(HotplaceDto hotplaceDto) throws SQLException {
		hotplaceMapper.modifyHotplace(hotplaceDto);

	}

	@Override
	public void deleteHotplace(String id) throws SQLException {
		hotplaceMapper.deleteHotplace(id);
	}

	@Override
	public HotplaceDto getHotplace(String id) throws SQLException {
		return hotplaceMapper.getHotplace(id);
	}

	@Override
	public List<HotplaceDto> orderByLike(String id) throws SQLException {
		return hotplaceMapper.orderByLike(id);
	}

	@Override
	public String getHotplacId(HotplaceDto hotplaceDto) throws SQLException {
		return hotplaceMapper.getHotplacId(hotplaceDto);
	}

}
