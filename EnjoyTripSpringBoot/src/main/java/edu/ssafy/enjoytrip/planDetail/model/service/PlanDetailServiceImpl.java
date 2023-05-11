package edu.ssafy.enjoytrip.planDetail.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.ssafy.enjoytrip.planDetail.model.PlanDetailDto;
import edu.ssafy.enjoytrip.planDetail.model.mapper.PlanDetailMapper;

@Service
public class PlanDetailServiceImpl implements PlanDetailService{

	
	private PlanDetailMapper planDetailMapper;
	
	
	public PlanDetailServiceImpl(PlanDetailMapper planDetailMapper) {
		super();
		this.planDetailMapper = planDetailMapper;
	}

	@Override
	public void writePlanDetai(PlanDetailDto planDetailDto) throws SQLException {
		planDetailMapper.writePlanDetai(planDetailDto);
	}

	// 플랜 번호로 경로 마지막 여행지 찾기 (추가 위해)
	public PlanDetailDto getRouteList(String planId) throws SQLException {
		return planDetailMapper.getRouteList(planId);
	}
	  
	@Override
	public List<PlanDetailDto> listPlanDetail(String planId) throws SQLException {
		return planDetailMapper.listPlanDetail(planId);
	}

	@Override
	public void modifyPlanDetail(PlanDetailDto planDetailDto) throws SQLException {
		planDetailMapper.modifyPlanDetail(planDetailDto);
	}

	@Override
	public void deletePlanDetail(String detailId) throws SQLException {
		planDetailMapper.deletePlanDetail(detailId);
		
	}

	@Override
	public PlanDetailDto getPlanDetail(String planId, String dataNum, String route) throws SQLException {
		return planDetailMapper.getPlanDetail(planId, dataNum, route);
	}

	@Override
	public PlanDetailDto getPlanDetailById(String detailId) throws SQLException {
		return planDetailMapper.getPlanDetailById(detailId);
	}

}
